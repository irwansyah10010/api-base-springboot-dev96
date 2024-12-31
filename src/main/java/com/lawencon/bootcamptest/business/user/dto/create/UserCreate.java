package com.lawencon.bootcamptest.business.user.dto.create;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserCreate {
    private String username;
	private String fullName;
	private String placeOfBirth;
    private LocalDate dateOfBirth;
	private String email;
    private String phoneNumber;
    private String address;

    private String role;

    private String profile;

    private String statusId;
}
