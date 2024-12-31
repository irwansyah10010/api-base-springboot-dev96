package com.lawencon.bootcamptest.business.user.dto.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    
    private String username;
	private String fullName;
	private String placeOfBirth;
	private String email;

    private String role;

    private String profile;

    private Boolean enabled;
    private Boolean isLoginWeb;
}
