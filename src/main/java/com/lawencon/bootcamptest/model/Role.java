package com.lawencon.bootcamptest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_role")
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseModel{

	@Column(name="role_code")
	private String roleCode;
	
	@Column(name="role_name")
	private String roleName;	
}
