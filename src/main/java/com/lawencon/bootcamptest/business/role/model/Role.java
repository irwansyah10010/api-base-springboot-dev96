package com.lawencon.bootcamptest.business.role.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.model.BaseMaster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseMaster{

	@Id
	@Column(name="role_code")
	private String roleCode;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="description")
	private String description;
}
