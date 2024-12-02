package com.lawencon.bootcamptest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


// umum, penjuruan(IT, kesehatan dll)
@Entity
@Table(name="tb_test_category")
@Data
@EqualsAndHashCode(callSuper = false)
public class TestCategory extends BaseModel{

	@Column(name="test_code")
	private String testTypeCode;
	
	@Column(name="test_name")
	private String testTypeName;

	@Column(name = "is_active")
	private Boolean isActive;

	@PrePersist
	public void prePersistLocal(){
		this.isActive = true;
	}
}
