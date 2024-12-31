package com.lawencon.bootcamptest.business.login.dto;

import lombok.Data;

@Data
public class LoginResDataDto {
    private String id;
    private String fullName;
    private String roleCode;
    private String token;
}
