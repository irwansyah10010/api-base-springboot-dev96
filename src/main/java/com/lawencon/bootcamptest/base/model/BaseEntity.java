package com.lawencon.bootcamptest.base.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity{
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_at")
	private Long createdAt;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "updated_at")
	private Long updatedAt;
	
	@Version
	@Column(name = "versions")
	private Integer version;

	@PrePersist
	public void preEntityPersist(){
		this.createdAt = System.currentTimeMillis();
	}

	@PostPersist
	public void postEntityPersist(){
		this.updatedAt = System.currentTimeMillis();
	}
}
