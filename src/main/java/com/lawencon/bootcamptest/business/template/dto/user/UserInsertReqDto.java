package com.lawencon.bootcamptest.business.template.dto.user;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserInsertReqDto {
    
	@NotBlank(message = "fullname is must required")
	private String fullName;

	@NotBlank(message = "placeOfBirth is must required")
	private String placeOfBirth;

	@NotBlank(message = "dateOfBirth is must required")
	private LocalDate dateOfBirth;

	private String address;

	@Email(message = "email is must required")
	private String email;

	@NotBlank(message = "Password is must filled")
	private String pass;

    private String roleId;

	private String fileId;
}
