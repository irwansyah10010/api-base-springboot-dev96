package com.lawencon.bootcamptest.base.model;



import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class BaseMaster extends BaseEntity{
	
	// @Id
	// @GeneratedValue(generator = "uuid2")
	// @GenericGenerator(name = "uuid2", strategy = "uuid2")
	// private String id;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@PrePersist
	public void prePersistMaster(){
		this.isActive = true;
	}

}
