package com.lawencon.bootcamptest.base.dao.build;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.bootcamptest.base.constanta.LogicalOperator;
import com.lawencon.bootcamptest.base.dao.DaoBuilder;
import com.lawencon.bootcamptest.base.dao.build.filter.DefinitionSearch;
import com.lawencon.bootcamptest.base.dao.build.getter.DefinitionJoin;
import com.lawencon.bootcamptest.base.dao.build.getter.InitialGetter;
import com.lawencon.bootcamptest.base.dao.build.updateordelete.InitialUpdateDelete;
import com.lawencon.bootcamptest.base.dto.attribute.Search;
import com.lawencon.bootcamptest.util.AssignUtil;

public class BaseDao<T> extends DaoBuilder<T>
implements InitialGetter, InitialUpdateDelete,
DefinitionJoin, DefinitionSearch{

	@Autowired
	private AssignUtil assignUtil;

	Predicate conjunction;
	Predicate search;

	Root<T> entityRoot;
	Map<Class<?>, Join<T,?>> joinRoot;

	List<String> fieldnames;
	Map<Class<?>, List<String>> joinFieldnames;

	/**
	 * initial builder
	 * 
	 * @param clazzEntity -> class entity build
	 * @param fieldnames -> shown fields needed
	 * @return this
	 * 
	 */
	public InitialGetter initGetter(Class<T> clazzEntity, String...fieldnames){
		initCriteriaQuery();

		entityRoot = getCriteriaQuery().from(clazzEntity);

		this.fieldnames = Arrays.asList(fieldnames);

		return this;
	}

	public InitialUpdateDelete initDelete(Class<T> entityClass){
		initCriteriaDelete(entityClass);
		entityRoot = getCriteriaDelete().from(entityClass);

		return this;
	}

	public InitialUpdateDelete initUpdate(Class<T> entityClass){
		initCriteriaUpdate(entityClass);
		getCriteriaUpdate().from(entityClass);

		return this;
	}

	
	/***
	 * close all variable
	 */
	public void close(){
		clearBuilder();
	
		conjunction=null;
	
		entityRoot=null;
		joinRoot=null;
	
		fieldnames=null;
		joinFieldnames=null;
	}

	/**
	 * initial join
	 */
	@Override
	public DefinitionJoin join(){
		joinRoot = new LinkedHashMap<>();
		joinFieldnames = new LinkedHashMap<>();

		return this;
	}
	
	/**
	 * initial search
	 */
	@Override
	public DefinitionSearch search() {
		conjunction = getCriteriaBuilder().conjunction();

		return this;
	}

	 /*
	 * end initial search
	 */

	/*
	* close join with on direct initialGetter
	*/
	@Override
	public InitialGetter closeJoin() {
		return this;
	}

	/**
	 * Create inner join
	 * 
	 * @param clazzEntity -> entity class that join with initial class
	 * @param fieldnames -> fieldnames[0] = field that joined, fieldnames[n > 0] = shown fields needed
	 * @return
	 */
	@Override
	public DefinitionJoin innerJoin(Class<?> clazzEntity, String...fieldname){
		if(fieldname.length > 1){
			Join<T,?> join = entityRoot.join(fieldname[0], JoinType.INNER);
	
			String[] showField = Arrays.copyOfRange(fieldname, 1, fieldname.length);
			List<String> showFieldList = Arrays.asList(showField);

			joinRoot.put(clazzEntity, join);
			joinFieldnames.put(clazzEntity, showFieldList);
		}

		return this;
	}

	/**
	 * Create outer join (uncomplete)
	 * 
	 * @param clazzEntity -> entity class that join with initial class
	 * @param fieldnames -> fieldnames[0] = field that joined, fieldnames[n > 0] = shown fields needed
	 * @return
	 */
	@Override
	public DefinitionJoin outerJoin(Class<?> clazzEntity, String...fieldname){
		if(fieldname.length > 1){
			Join<T,?> join = entityRoot.join(fieldname[0], JoinType.RIGHT);
	
			String[] showField = Arrays.copyOfRange(fieldname, 1, fieldname.length);
			List<String> showFieldList = Arrays.asList(showField);

			joinRoot.put(clazzEntity, join);
			joinFieldnames.put(clazzEntity, showFieldList);
		}

		return this;
	}

	/**
	 * Create right join
	 * 
	 * @param clazzEntity -> entity class that join with initial class
	 * @param fieldnames -> fieldnames[0] = field that joined, fieldnames[n > 0] = shown fields needed
	 * @return
	 */
	@Override
	public DefinitionJoin rightJoin(Class<?> clazzEntity, String...fieldname){
		if(fieldname.length > 1){
			Join<T,?> join = entityRoot.join(fieldname[0], JoinType.RIGHT);
	
			String[] showField = Arrays.copyOfRange(fieldname, 1, fieldname.length);
			List<String> showFieldList = Arrays.asList(showField);

			joinRoot.put(clazzEntity, join);
			joinFieldnames.put(clazzEntity, showFieldList);
		}

		return this;
	}

	/**
	 * Create left join
	 * 
	 * @param clazzEntity -> entity class that join with initial class
	 * @param fieldnames -> fieldnames[0] = field that joined, fieldnames[n > 0] = shown fields needed
	 * @return
	 */
	@Override
	public DefinitionJoin leftJoin(Class<?> clazzEntity, String...fieldname){
		if(fieldname.length > 1){
			Join<T,?> join = entityRoot.join(fieldname[0], JoinType.LEFT);
	
			String[] showField = Arrays.copyOfRange(fieldname, 1, fieldname.length);
			List<String> showFieldList = Arrays.asList(showField);

			joinRoot.put(clazzEntity, join);
			joinFieldnames.put(clazzEntity, showFieldList);
		}

		return this;
	}

	/*
	* end interface join
	*/


	/*
	* create definition search
	*/

	/**
	 * close search
	 * 
	 */
	@Override
	public InitialGetter closeSearch() {
		// search = null;
		return this;
	}

	/*
	 * relation among search after type search like equal, notEqual etc
	 */
	@Override
	public DefinitionSearch and() {
		conjunction = getCriteriaBuilder().and(conjunction, search);
		return this;
	}

	/*
	 * relation among search after type search like equal, notEqual etc
	*/
	@Override
	public DefinitionSearch or() {
		conjunction = getCriteriaBuilder().or(conjunction, search);
		return this;
	}
	/*
	* end create definition search
	*/
	/*
	 * interface search
	 */
	public void searchEqual(Map<String, Object> lookUp, LogicalOperator relation){
		if(!Optional.ofNullable(conjunction).isPresent())
			conjunction = getCriteriaBuilder().conjunction();

		for(Entry<String , Object> data: lookUp.entrySet()){
			Path<?> path = entityRoot.get(data.getKey());
			Predicate equal = getCriteriaBuilder().equal(path, data.getValue()); 

			conjunction = relation.equals(LogicalOperator.AND)? getCriteriaBuilder().and(conjunction, equal):getCriteriaBuilder().or(conjunction, equal);  
		}
	}

	/**
	 * Look for base on field
	 * 
	 * @param field -> field 
	 * @param value -> value
	 * @return
	 */
	@Override
	public DefinitionSearch equal(String field,Object value) {
		Path<?> path = entityRoot.get(field);
		search = getCriteriaBuilder().equal(path, value); 

		return this;
	}

	/**
	 * Look for base on field
	 * 
	 * @param field -> field 
	 * @param value -> value
	 * @return
	 */
	@Override
	public DefinitionSearch notEqual(String field,Object value) {
		Path<?> path = entityRoot.get(field);
		search = getCriteriaBuilder().notEqual(path, value); 

		return this;
	}

	/**
	 * Look for base on field
	 * 
	 * @param field -> field 
	 * @param value -> value
	 * @return
	 */
	@Override
	public DefinitionSearch likeFront(String field,String value) {
		Path<String> path = entityRoot.get(field);
		search = getCriteriaBuilder().like(path, value+"%"); 

		return this;
	}

	/**
	 * Look for base on field
	 * 
	 * @param field -> field 
	 * @param value -> value
	 * @return
	 */
	@Override
	public DefinitionSearch unlikeFront(String field,String value) {
		Path<String> path = entityRoot.get(field);
		search = getCriteriaBuilder().notLike(path, value+"%"); 

		return this;
	}

	/**
	 * Look for base on field
	 * 
	 * @param field -> field 
	 * @param value -> value
	 * @return
	 */
	@Override
	public DefinitionSearch likeBack(String field,String value) {
		Path<String> path = entityRoot.get(field);
		search = getCriteriaBuilder().like(path, "%"+value); 

		return this;
	}

	/**
	 * Look for base on field
	 * 
	 * @param field -> field 
	 * @param value -> value
	 * @return
	 */
	@Override
	public DefinitionSearch unlikeBack(String field,String value) {
		Path<String> path = entityRoot.get(field);
		search = getCriteriaBuilder().notLike(path, "%"+value); 

		return this;
	}
	/*
	 * end interface search
	 */

	/**
	 * 
	 * get all initial field and relation field
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

		return getCriteriaBuilder().array(path);
	}

	/**
	 * build select and where(if there)
	 * 
	 * @return
	 */
	public TypedQuery<Object[]> build(){
		if(Optional.ofNullable(conjunction).isPresent())
			getCriteriaQuery().where(conjunction);
		
		getCriteriaQuery().select(selectedField(fieldnames, joinFieldnames));

		return getConnection().createQuery(getCriteriaQuery());
	}

	/**
	 * build select, where(if there) and addtional page limit
	 * @param page
	 * @param limit
	 * @return
	 */
	public TypedQuery<Object[]> build(int page, int limit){
		Integer start = limit * (page - 1);
        Integer last = start + limit;

		return build().setFirstResult(start).setMaxResults(last);
	}

	/**
	 * get data as dto class(sort var field base on initial and join data)
	 * 
	 * @param clazzDto -> class that using as take off data
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

		close();

		return resultList;
	}

	/**
	 * get list data as dto class(sort var field base on initial and join data)
	 * 
	 * @param clazzDto -> class that using as take off data
	 * @param page -> page
	 * @param limit -> limit
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

		close();
		return resultList;
	}

	/**
	 * get single data as dto class(sort var field base on initial and join data)
	 * 
	 * @param clazzDto -> class that using as take off data
	 * @return
	 */
	public <R> Optional<R> getSingleResult(Class<R> clazzDto) throws Exception {

		TypedQuery<Object[]> query = build();
		
		try {
			Object[] result = query.getSingleResult();

			Constructor<R> constructor = clazzDto.getDeclaredConstructor();
	
			R obj = constructor.newInstance();

			assignUtil.objToClass(result, obj);

			close();

			return Optional.ofNullable(obj);	
		} catch (NoResultException e){
			close();
			throw new NoResultException("data not found");
		} catch (Exception e) {
			close();
			throw new Exception(e.getMessage());
		}
	}
		

	public Boolean delete(){
		getCriteriaDelete().where(conjunction);
		
		Query query = getConnection().createQuery(getCriteriaDelete());

		close();

		return query.executeUpdate() > 0;
	}

	public T updated(T entity){
		getCriteriaUpdate().where(conjunction);
		
		getConnection().merge(entity);
		getConnection().flush();

		close();

		return entity;
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
