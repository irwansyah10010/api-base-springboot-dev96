package com.lawencon.bootcamptest.business.role.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bootcamptest.base.dto.BaseResponse;
import com.lawencon.bootcamptest.base.dto.error.ErrorResponse;
import com.lawencon.bootcamptest.business.role.dto.detail.RoleResponse;
import com.lawencon.bootcamptest.business.role.dto.list.RolesResponse;
import com.lawencon.bootcamptest.business.role.service.RoleService;



@RestController
@RequestMapping("role/")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    /*
     * get all
     * Done
     */
    @GetMapping("")
    ResponseEntity<BaseResponse<List<RolesResponse>>> getAll(){
        BaseResponse<List<RolesResponse>> roles;

        try {
            roles = roleService.getAll();

            roles.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            roles = new BaseResponse<>(new ArrayList<>());

            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());
            
            roles.setError(new ErrorResponse(ofNullable.orElse("no message")));
            roles.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    /*
     * get detail by role
     * 
     */
    @GetMapping("{roleCode}")
    public ResponseEntity<BaseResponse<RoleResponse>> getByRoleCode(@PathVariable("roleCode") String roleCode) {
        BaseResponse<RoleResponse> role;

        try {
            role = roleService.getByRoleCode(roleCode);

            role.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            role = new BaseResponse<>(new RoleResponse());
            
            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());

            role.setError(new ErrorResponse(ofNullable.orElse("Message is null")));
            role.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(role, HttpStatus.OK);
        }

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    

}
