package com.lawencon.bootcamptest.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lawencon.bootcamptest.base.dto.attribute.Search;
import com.lawencon.bootcamptest.base.model.BaseEntity;
import com.lawencon.bootcamptest.base.model.BaseMaster;

public abstract class BaseRepository{

    @PersistenceContext
    private EntityManager em;

    public EntityManager getConnection(){
        return em;
    }
    
    public <T> T save(T entityClass) {
    	getConnection().persist(entityClass);
    	
    	return entityClass;
    }
    
    public <T> T update(T entityClass) {
    	entityClass = getConnection().merge(entityClass);
    	getConnection().flush();
    	
    	return entityClass;
    }

	public <T extends BaseEntity> T getById(Class<T> entityClass,String id) {
    	T clazz = getConnection().find(entityClass, id);

		if(clazz != null){
			return clazz;	
		}
    	
    	return null;
    }
    
	public <T extends BaseEntity, BaseMaster> T getByIdAndDetach(Class<T> entityClass, String id) {
    	T clazz = null;
		try {
			clazz = getConnection().find(entityClass, id);
			getConnection().detach(clazz);

			return clazz;
		} catch (Exception e) {
			return null;
		}
    }

	public <T>Boolean isDelete(Search search, Class<T> clazz){
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

	// public <T extends BaseEntity> T getReferennceById(Class<T> entityClass,String id) {
    // 	T clazz = getConnection().getReference(entityClass, id);
    // 	getConnection().detach(clazz);
    	
    // 	return clazz;
    // }
    
    // @SuppressWarnings("unchecked")
	// public <T> List<T> getAllBy(String entitas,Class<T> clazz,Map<OptionalBuilder, ?> options) {
    // 	Query query =  builderQuery(entitas,true,clazz,options);
    	
	// 	Map<String,?> wheres =(Map<String,?>) options.get(OptionalBuilder.WHERE);
	// 	Optional<Map<String,?>> optWheres = Optional.ofNullable(wheres);

	// 	String relation = (String) options.get(OptionalBuilder.RELATION);
	// 	Optional<String> optRelation = Optional.ofNullable(relation);
    	
    // 	if(optRelation.isPresent() && optWheres.isPresent()) {    		
    // 		for(Entry<String, ?> column:optWheres.get().entrySet()) {
    // 			query = query.setParameter(column.getKey(), column.getValue());
    // 		}
    // 	}
    	
    // 	return query.getResultList();
    // }

	// public Integer getCountOfAllBy(String entitas,Map<OptionalBuilder, ?> options) {
	// 	// set obj
    // 	Query query =  builderQuery(entitas,false,null,options);
	// 	Object objCount = null;
	// 	Integer countOfData = 0;

	// 	@SuppressWarnings("unchecked")
	// 	Map<String,?> wheres =(Map<String,?>) options.get(OptionalBuilder.WHERE);
	// 	Optional<Map<String,?>> optWheres = Optional.ofNullable(wheres);

	// 	String relation = (String) options.get(OptionalBuilder.RELATION);
	// 	Optional<String> optRelation = Optional.ofNullable(relation);
    	
    // 	if(optRelation.isPresent() && optWheres.isPresent()) {    		
    // 		for(Entry<String, ?> column:optWheres.get().entrySet()) {
    // 			query = query.setParameter(column.getKey(), column.getValue());
    // 		}
    // 	}

	// 	try {
	// 		objCount = query.getSingleResult();
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}

	// 	if(objCount != null){
	// 		Object obj = (Object) objCount;

	// 		countOfData = Integer.parseInt(obj.toString());
	// 	}
    	
    // 	return countOfData;
    // }

	// public <T>Boolean removeBy(String entitas,Class<T> clazz,Map<OptionalBuilder,?> options){

	// 	StringBuilder sql = new StringBuilder();

	// 	sql.append("DELETE from ")
	// 	.append(entitas).append(" ");

	// 	List<String> sqlList = new ArrayList<>();

	// 	@SuppressWarnings("unchecked")
	// 	Map<String,?> wheres =  (Map<String, ?>) options.get(OptionalBuilder.WHERE);
	// 	Optional<Map<String,?>> optWhere = Optional.ofNullable(wheres);

	// 	String relation =  (String) options.get(OptionalBuilder.RELATION);
	// 	Optional<String> optRelation = Optional.ofNullable(relation);

	// 	if(options != null){

	// 		if(optWhere.isPresent() && optRelation.isPresent()){
	// 			for(Entry<String, ?> column:wheres.entrySet()) {
	// 				StringBuilder s = new StringBuilder();
	// 				s.append(column.getKey()).append(" = ")
	// 				.append(":")
	// 				.append(column.getKey());

	// 				sqlList.add(s.toString());
	// 			}

	// 			for (int i = 0; i < sqlList.size(); i++) {
	// 				if(i == 0){
	// 					sql.append("WHERE ");
	// 				}

	// 				sql.append(sqlList.get(i))
	// 				.append(" ");	

	// 				if(i < sqlList.size() - 1){
	// 					sql.append(relation).append(" ");
	// 				}
	// 			}
	// 		}
	// 	}

	// 	Query sqlQuery = getConnection().createNativeQuery(sql.toString());
		
	// 	if(options != null){

	// 		for(Entry<String, ?> column:wheres.entrySet()) {
	// 			sqlQuery = sqlQuery.setParameter(column.getKey(), column.getValue());
	// 		}
	// 	}

	// 	boolean isDelete = false;

	// 	// List<T> list = getAllBy(entitas,clazz,options);

	// 	// if(!list.isEmpty()){
	// 	// 	if(sqlQuery.executeUpdate() > 0){
	// 	// 		isDelete = true;
	// 	// 	}
	// 	// }


	// 	return sqlQuery.executeUpdate() > 0;
	// }

	// /**
	//  * 
	//  * @param entitas
	//  * @return
	//  */
	// private <T> Query builderQuery(String entitas,boolean isData,Class<T> clazz,Map<OptionalBuilder, ?> options){
	// 	StringBuilder sql = new StringBuilder();
    	
	// 	String columnData = (isData)?"* ":"count(*) ";

    // 	sql.append("SELECT ")
	// 	.append(columnData).append(" ")
	// 	.append("FROM ")
    // 	.append(entitas).append(" ");
    	
    // 	List<String> sqlList = new ArrayList<>();
	// 	Optional<Map<OptionalBuilder,?>> opt = Optional.ofNullable(options);

	// 	// options
	// 	if(opt.isPresent()) {

	// 		Object id = options.get(OptionalBuilder.ID);
	// 		Optional<Object> optId = Optional.ofNullable(id);
	
	// 		// <fk,value>
	// 		@SuppressWarnings("unchecked")
	// 		Map<String,?> joins =(Map<String,?>) options.get(OptionalBuilder.JOIN);
	// 		Optional<Map<String,?>> optJoins = Optional.ofNullable(joins);

	// 		// opt join
	// 		if(optId.isPresent() && optJoins.isPresent()){
				
	// 			for(Entry<String, ?> column:optJoins.get().entrySet()) {
	// 				StringBuilder joinString = new StringBuilder();

	// 				joinString.append(column.getKey()).append(" ")
	// 					.append("ON ");

	// 				if(column.getValue() instanceof Map){
	// 					@SuppressWarnings("unchecked")
	// 					Map<OptionalBuilder, ?> tableOthers = (Map<OptionalBuilder, ?>) column.getValue();
	// 					Optional<Map<OptionalBuilder, ?>> optTableOthers = Optional.ofNullable(tableOthers);

	// 					// get table other
	// 					if(optTableOthers.isPresent()){
	// 						Object idOther = tableOthers.get(OptionalBuilder.ID_OTHER);
	// 						Optional<Object> optIdOther = Optional.ofNullable(idOther);

	// 						String tableOther = (String) tableOthers.get(OptionalBuilder.TABLE_OTHER);
	// 						Optional<String> optTableOther = Optional.ofNullable(tableOther);

	// 						Object idFk = tableOthers.get(OptionalBuilder.ID_FK);
	// 						Optional<Object> optIdFk = Optional.ofNullable(idFk);

	// 						// check attribut table other
	// 						if(optIdOther.isPresent() && optTableOther.isPresent() && optIdFk.isPresent()){
	// 							joinString.append(column.getKey()).append(".").append(idFk)
	// 							.append(" = ")
	// 							.append(tableOther).append(".").append(idOther)
	// 							.append(" ");
	// 						}
	// 					}
	// 				}else{
	// 					joinString.append(entitas).append(".").append(id)
	// 					.append(" = ")
	// 					.append(column.getKey()).append(".").append(column.getValue())
	// 					.append(" ");
	// 				}
					
	// 				sqlList.add(joinString.toString());
	// 			}

	// 			for (int i = 0; i < sqlList.size(); i++) {
	// 				sql.append("INNER JOIN ")
	// 				.append(sqlList.get(i));
	// 			}
	// 		}

	// 		sqlList = new ArrayList<>();
	
	// 		@SuppressWarnings("unchecked")
	// 		Map<String,?> wheres =(Map<String,?>) options.get(OptionalBuilder.WHERE);
	// 		Optional<Map<String,?>> optWheres = Optional.ofNullable(wheres);
	
	// 		String relation = (String) options.get(OptionalBuilder.RELATION);
	// 		Optional<String> optRelation = Optional.ofNullable(relation);

	// 		// opt where
	// 		if(optWheres.isPresent() && optRelation.isPresent()){
				
	// 			for(Entry<String, ?> column:optWheres.get().entrySet()) {
	// 				String s = column.getKey()+"= :"+column.getKey();
					
	// 				sqlList.add(s);
	// 			}

	// 			for (int i = 0; i < sqlList.size(); i++) {
    			
	// 				if(i == 0) {
	// 					sql.append("WHERE ");
	// 				}
					
	// 				sql.append(sqlList.get(i)).append(" ");
					
	// 				if(i < sqlList.size() - 1) {
	// 					sql.append(optRelation.get()).append(" ");
	// 				}
	// 			}
	// 		}
	// 	}

	// 	// page and limit
	// 	Integer page = (Integer) options.get(OptionalBuilder.PAGE);
	// 	Optional<Integer> optPage = Optional.ofNullable(page);

	// 	Integer limit = (Integer) options.get(OptionalBuilder.LIMIT);
	// 	Optional<Integer> optLimit = Optional.ofNullable(limit);
    	
	// 	if(optLimit.isPresent()){

	// 		int start = optPage.isPresent()? (limit * (page - 1)):0;

	// 		sql.append("LIMIT ")
	// 		.append(limit).append(" ")
	// 		.append("OFFSET ")
	// 		.append(start);
	// 	}

	// 	// set adding where query
		
	// 	Query query = null;

	// 	if(isData){
	// 		query = getConnection().createNativeQuery(sql.toString(),clazz);
	// 	}else{
	// 		query = getConnection().createNativeQuery(sql.toString());
	// 	}

	// 	return query;
	// }

	

	
}
