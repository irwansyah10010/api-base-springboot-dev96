package com.lawencon.bootcamptest.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.bootcamptest.base.dto.attribute.Search;
import com.lawencon.bootcamptest.util.AssignUtil;

public abstract class BaseDao<T> extends BaseCrud{

	@Autowired
	private AssignUtil assignUtil;

	CriteriaBuilder criteriaBuilder;
	CriteriaQuery<Object[]> criteriaQuery;
	
	CriteriaQuery<T> criteriaQueryDelete;

	Predicate conjunction; // use
	Map<String, Object>[] lookUp; // not use
	String relation = "AND"; // AND , OR // use
	

	Root<T> entityRoot; // use
	Map<Class<?>, Join<T,?>> joinRoot;

	List<String> fieldnames; // use
	Map<Class<?>, List<String>> joinFieldnames; // use
	
	public void buildCriteria(){
		criteriaBuilder = getConnection().getCriteriaBuilder();
	}


	public void createQuery(){
		criteriaQuery = criteriaBuilder.createQuery(Object[].class);
	}

	// public void createQueryDelete(){
	// 	criteriaQueryDelete = criteriaBuilder.createCriteriaDelete();
	// }

	public void initiated(Class<T> clazzEntity, String...fieldnames){
		buildCriteria();
		createQuery();

		entityRoot = criteriaQuery.from(clazzEntity);

		this.fieldnames = Arrays.asList(fieldnames);
	}

	public void addJoinField(Class<?> clazzEntity, JoinType joinType, String...fieldname){
		if(!Optional.ofNullable(joinRoot).isPresent())
			joinRoot = new HashMap<>();

		if(!Optional.ofNullable(joinFieldnames).isPresent())
			joinFieldnames = new HashMap<>();


		if(fieldname.length > 1){
			Join<T,?> join = entityRoot.join(fieldname[0], joinType);
	
			String[] showField = Arrays.copyOfRange(fieldname, 1, fieldname.length);
			List<String> showFieldList = Arrays.asList(showField);

			joinRoot.put(clazzEntity, join);
			joinFieldnames.put(clazzEntity, showFieldList);
		}
	}

	public void searchEqual(Map<String, Object> lookUp, String relation){
		if(!Optional.ofNullable(conjunction).isPresent())
			conjunction = criteriaBuilder.conjunction();

		for(Entry<String , Object> data: lookUp.entrySet()){
			Path<?> path = entityRoot.get(data.getKey());
			Predicate equal = criteriaBuilder.equal(path, data.getValue()); 

			conjunction = relation.equals("AND")? criteriaBuilder.and(conjunction, equal):criteriaBuilder.or(conjunction, equal);  
		}
	}


	/**
	 * 
	 * @param fieldnames
	 * @param joinFieldnames
	 * @return
	 */
	public CompoundSelection<Object[]> selectedField(List<String> fieldnames, Map<Class<?>, List<String>> joinFieldnames){
		int totalJoin = 0;
		if(Optional.ofNullable(joinFieldnames).isPresent())
			totalJoin = joinFieldnames.values().stream().mapToInt(List::size).sum();

		Path<?>[] path = new Path[fieldnames.size() + totalJoin];

		int count = 0;
		for(String field : fieldnames){
			path[count++] = entityRoot.get(field);
		}

		if(Optional.ofNullable(joinFieldnames).isPresent()){
			for(Entry<Class<?>, List<String>> joinField : joinFieldnames.entrySet()){
				for(String field:joinField.getValue()){
					path[count++] = joinRoot.get(joinField.getKey()).get(field);
				}
			}
		}

		return criteriaBuilder.array(path);
	}

	/**
	 * 
	 * @return
	 */
	public TypedQuery<Object[]> build(){
		if(Optional.ofNullable(conjunction).isPresent())
			criteriaQuery.where(conjunction);
		
		criteriaQuery.select(selectedField(fieldnames, joinFieldnames));

		return getConnection().createQuery(criteriaQuery);
	}

	public TypedQuery<Object[]> build(int page, int limit){
		Integer start = limit * (page - 1);
        Integer last = start + limit;

		return build().setFirstResult(start).setMaxResults(last);
	}

	/**
	 * 
	 * @param <R>
	 * @param clazzDto
	 * @return
	 */
	public <R>List<R> getAll(Class<R> clazzDto){

		TypedQuery<Object[]> query = build();
		List<Object[]> getResult = query.getResultList();

		List<R> resultList = new ArrayList<>();

		// setter
		getResult.forEach(t ->{
			try {
				Constructor<R> constructor = clazzDto.getDeclaredConstructor();
	
				R obj = constructor.newInstance();
				
				assignUtil.objToClass(t, obj);

				resultList.add(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} );

		return resultList;
	}

	/**
	 * 
	 * @param <R>
	 * @param clazzDto
	 * @param page
	 * @param limit
	 * @return
	 */
	public <R>List<R> getAll(Class<R> clazzDto, int page, int limit){

		TypedQuery<Object[]> query = build(page, limit);
		List<Object[]> getResult = query.getResultList();

		List<R> resultList = new ArrayList<>();

		// setter
		getResult.forEach(t ->{
			try {
				Constructor<R> constructor = clazzDto.getDeclaredConstructor();
	
				R obj = constructor.newInstance();
				
				assignUtil.objToClass(t, obj);

				resultList.add(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} );

		return resultList;
	}

	public <R> Optional<R> getSingleResult(Class<R> clazzDto) throws Exception {

		TypedQuery<Object[]> query = build();
		
		try {
			Object[] result = query.getSingleResult();

			Constructor<R> constructor = clazzDto.getDeclaredConstructor();
	
			R obj = constructor.newInstance();

			assignUtil.objToClass(result, obj);

			return Optional.ofNullable(obj);	
		} catch (NoResultException e){
			throw new NoResultException("User not found");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
		
	public Boolean isDelete(Search search, Class<T> clazz){
		List<String> fields = search.getFields();
		CriteriaBuilder criteriaBuilder = getConnection().getCriteriaBuilder();
		CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(clazz);

		Root<T> entityRoot = criteriaDelete.from(clazz);

		Predicate conjunction = criteriaBuilder.conjunction();

		for (String field : fields) {
			Predicate equal = criteriaBuilder.equal(entityRoot.get(field), search.getValue());
			conjunction = criteriaBuilder.and(conjunction,equal);
		}

		criteriaDelete.where(conjunction);
		
		Query query = getConnection().createQuery(criteriaDelete);

		int executeUpdate = query.executeUpdate();

		return executeUpdate > 0;
	}

}
