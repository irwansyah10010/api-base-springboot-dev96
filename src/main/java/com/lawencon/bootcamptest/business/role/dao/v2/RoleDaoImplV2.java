package com.lawencon.bootcamptest.business.role.dao.v2;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.bootcamptest.base.dao.build.BaseDao;
import com.lawencon.bootcamptest.business.role.dao.RoleDao;
import com.lawencon.bootcamptest.business.role.dto.detail.RoleResponse;
import com.lawencon.bootcamptest.business.role.dto.list.RolesResponse;
import com.lawencon.bootcamptest.business.role.model.Role;

@Repository
@Profile("v2")
public class RoleDaoImplV2 extends BaseDao<Role> implements RoleDao {

    @Override
    public List<RolesResponse> getAll(){
        
        initGetter(Role.class,
        "roleCode",
        "roleName",
        "isActive");

        return getAll(RolesResponse.class);
    }

    @Override
    public Optional<RoleResponse> getByRoleCode(String roleCode) throws Exception {
        initGetter(Role.class,
        "roleCode",
        "roleName", 
        "description"
        // "features"
        ).search().equal("roleCode", roleCode).and().closeSearch();

        return getSingleResult(RoleResponse.class);
    }

    @Override
    public Role getByIdAndDetach(String id) {
        return super.getByIdAndDetach(Role.class, id);
    }
}
