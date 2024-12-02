package com.lawencon.bootcamptest.dto.user;

import java.time.LocalDate;

import com.lawencon.bootcamptest.dto.role.RoleResDataDto;
import com.lawencon.bootcamptest.model.doc.File;

import lombok.Data;

@Data
public class UserResDataDto {

	private String id;
	private String fullName;
	private String placeOfBirth;
	private LocalDate dateOfBirth;
	private String address;
	private String email;
	private RoleResDataDto role;
	
	private File file;
	private File cv;
}
