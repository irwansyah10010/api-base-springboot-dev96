package com.lawencon.bootcamptest.model.person;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.BaseModel;
import com.lawencon.bootcamptest.model.Role;
import com.lawencon.bootcamptest.model.doc.File;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel{

	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "place_of_birth")
	private String placeOfBirth;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name="pass")
	private String pass;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="file_id")
	private File file;
	
	@ManyToOne
	@JoinColumn(name="cv_id")
	private File cv;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@PrePersist
	public void prePersistLocal(){
		this.isActive = true;
	}
	
}
