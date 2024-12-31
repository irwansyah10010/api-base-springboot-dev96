package com.lawencon.bootcamptest.business.user.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.bootcamptest.base.model.BaseMaster;
import com.lawencon.bootcamptest.business.file.model.File;
import com.lawencon.bootcamptest.business.role.model.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="tb_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseMaster{

    @Id
    @Column(name = "username")
    private String username;

	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "place_of_birth")
	private String placeOfBirth;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

    @Column(name = "email")
	private String email;
	
	@Column(name="pass")
	private String pass;

    @Column(name = "phone_number")
    private String phoneNumber;
	
	@Column(name = "address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="profile_picture_id")
	private File profilePicture;

	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusUser statusUser;
	
}
