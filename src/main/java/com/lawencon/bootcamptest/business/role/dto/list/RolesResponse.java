package com.lawencon.bootcamptest.business.role.dto.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesResponse {
    
    private String roleCode;
    private String roleName;
    private Boolean isActive;
}
