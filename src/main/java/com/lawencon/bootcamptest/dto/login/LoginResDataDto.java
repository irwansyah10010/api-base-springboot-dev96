package com.lawencon.bootcamptest.dto.login;

import lombok.Data;

@Data
public class LoginResDataDto {
    
    private String id;
    private String fullName;
    private String roleCode;

    private String token;
}
