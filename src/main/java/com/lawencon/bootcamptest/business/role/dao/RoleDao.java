package com.lawencon.bootcamptest.business.role.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.bootcamptest.business.role.dto.detail.RoleResponse;
import com.lawencon.bootcamptest.business.role.dto.list.RolesResponse;
import com.lawencon.bootcamptest.business.role.model.Role;

public interface RoleDao {
    
    public List<RolesResponse> getAll();

    public Optional<RoleResponse> getByRoleCode(String roleCode) throws Exception;

    public Role getByIdAndDetach(String id);
}
