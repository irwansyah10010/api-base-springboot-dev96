package com.lawencon.bootcamptest.business.role.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    
    private String roleCode;
    private String roleName;
    private String description;
    private Object features;
}
