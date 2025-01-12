package com.lawencon.bootcamptest.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.lawencon.bootcamptest.base.model.BaseEntity;

import liquibase.pro.packaged.T;

public abstract class BaseCrud {

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

	public <T extends BaseEntity> T getById(Class<T> entityClass, String id) {
		T clazz = getConnection().find(entityClass, id);

		if(clazz != null){
			return clazz;	
		}
		
		return null;
	}
	
	public <T extends BaseEntity> T getByIdAndDetach(Class<T> entityClass, String id) {
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
