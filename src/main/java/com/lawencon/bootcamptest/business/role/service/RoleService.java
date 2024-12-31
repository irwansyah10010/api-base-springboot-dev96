package com.lawencon.bootcamptest.business.role.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.bootcamptest.base.dto.BaseResponse;
import com.lawencon.bootcamptest.business.role.dao.RoleDao;
import com.lawencon.bootcamptest.business.role.dto.detail.RoleResponse;
import com.lawencon.bootcamptest.business.role.dto.list.RolesResponse;
import com.lawencon.bootcamptest.business.role.model.Role;

@Service
public class RoleService {
    
    @Autowired
    private RoleDao roleDao;

    public BaseResponse<List<RolesResponse>> getAll(){
        return new BaseResponse<>(roleDao.getAll());
    }

    public BaseResponse<RoleResponse> getByRoleCode(String roleCode){
        
        Optional<RoleResponse> byRoleCode = roleDao.getByRoleCode(roleCode);

        return new BaseResponse<>(byRoleCode.get());
    }

}
