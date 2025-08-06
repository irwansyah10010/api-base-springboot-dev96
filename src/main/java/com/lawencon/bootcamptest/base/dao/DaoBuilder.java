package com.lawencon.bootcamptest.base.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;

public class DaoBuilder<T> {

    @PersistenceContext
	private EntityManager em;

    CriteriaBuilder criteriaBuilder;
	
	CriteriaQuery<Object[]> criteriaQuery;
	CriteriaDelete<T> criteriaDelete;
	CriteriaUpdate<T> criteriaUpdate;


    public EntityManager getConnection(){
		return em;
	}

    public void clearBuilder(){
        criteriaBuilder=null;
	
        criteriaQuery=null;
        criteriaDelete=null;
		criteriaUpdate=null;
    }

    public void initBuilder() {
        criteriaBuilder = getConnection().getCriteriaBuilder();
    }

    public void initCriteriaQuery() {
		initBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Object[].class);
    }

	public void initCriteriaDelete(Class<T> clazzEntity) {
		initBuilder();
		criteriaDelete = criteriaBuilder.createCriteriaDelete(clazzEntity);
	}

	public void initCriteriaUpdate(Class<T> classEntity){
		initBuilder();
		criteriaUpdate = criteriaBuilder.createCriteriaUpdate(classEntity);
	}

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public CriteriaQuery<Object[]> getCriteriaQuery() {
        return criteriaQuery;
    }

	public CriteriaDelete<T> getCriteriaDelete() {
		return criteriaDelete;
	}

	public CriteriaUpdate<T> getCriteriaUpdate() {
		return criteriaUpdate;
	}
    
    
	public T save(T entityClass) {
		getConnection().persist(entityClass);
		
		return entityClass;
	}
	
	
	public T update(T entityClass) {
		entityClass = getConnection().merge(entityClass);
		getConnection().flush();
		
		return entityClass;
	}

	
	public T getById(Class<T> entityClass, String id) {
		T clazz = getConnection().find(entityClass, id);

		if(clazz != null){
			return clazz;	
		}
		
		return null;
	}
	
	
	public T getByIdAndDetach(Class<T> entityClass, String id) {
		T clazz = null;
		try {
			clazz = getConnection().find(entityClass, id);
			getConnection().detach(clazz);

			return clazz;
		} catch (Exception e) {
			return null;
		}
	}
}
