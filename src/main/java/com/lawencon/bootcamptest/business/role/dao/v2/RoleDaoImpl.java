package com.lawencon.bootcamptest.business.role.dao.v2;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.bootcamptest.base.BaseDao;
import com.lawencon.bootcamptest.business.role.dao.RoleDao;
import com.lawencon.bootcamptest.business.role.dto.detail.RoleResponse;
import com.lawencon.bootcamptest.business.role.dto.list.RolesResponse;
import com.lawencon.bootcamptest.business.role.model.Role;

@Repository
@Profile("use-base")
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao{
    
    @Override
    public List<RolesResponse> getAll(){
        
        initiated(Role.class,
        "roleCode",
        "roleName",
        "isActive");

        return getAll(RolesResponse.class);
    }

    @Override
    public Optional<RoleResponse> getByRoleCode(String roleCode) throws Exception {
        initiated(Role.class,
        "roleCode",
        "roleName",
        "isActive", 
        "description");

        searchEqual(new HashMap<>(){{
            put("roleCode", roleCode);
        }}, "AND");

        return getSingleResult(RoleResponse.class);
    }
}
