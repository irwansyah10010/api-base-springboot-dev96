package com.lawencon.bootcamptest.business.template.dto.user;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserUpdateReqDto {

    @NotNull(message = "Id is must not null")
    private String id;
	private String fullName;
	private String placeOfBirth;
	private LocalDate dateOfBirth;
	private String address;
    private String roleId;
	private String fileId;

    @NotNull(message = "Version not available")
    private Integer version;
}
