package com.lawencon.bootcamptest.base;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseModel{
	public static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Version
	@Column(name = "versions")
	private Integer version;

	@PrePersist
	public void prePersist(){
		this.createdAt = LocalDateTime.now();
	}

	@PostPersist
	public void postPersist(){
		this.updatedAt = LocalDateTime.now();
	}
}
