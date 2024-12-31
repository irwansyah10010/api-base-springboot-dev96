package com.lawencon.bootcamptest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.lawencon.bootcamptest.base.dto.BaseResponse;
import com.lawencon.bootcamptest.business.role.dao.RoleDao;
import com.lawencon.bootcamptest.business.role.dto.detail.RoleResponse;
import com.lawencon.bootcamptest.business.role.dto.list.RolesResponse;
import com.lawencon.bootcamptest.business.role.model.Role;
import com.lawencon.bootcamptest.business.role.service.RoleService;

@SpringBootTest
public class RoleServiceTest {
    
    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleService roleService;

    private RoleResponse mockRole;

    private List<RolesResponse> mockRoles;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        mockRoles = Arrays.asList(
                new RolesResponse("AD","Admin", true),
                new RolesResponse("SU","Super",true)
        );


        mockRole = new RoleResponse("AD", "Admin", "desc", null);
    }

    @Test
    public void testGetAll(){

        // present mock
        when(roleDao.getAll()).thenReturn(mockRoles);

        // call service
        BaseResponse<List<RolesResponse>> all = roleService.getAll();

        // verification result
        assertNotNull(all);
        assertEquals(2, all.getData().size());
        assertEquals("AD", all.getData().get(0).getRoleCode());

        // verify
        verify(roleDao, times(1)).getAll();
    }

    @Test
    public void testGetByRoleCode(){

        // present mock
        when(roleDao.getByRoleCode("AD")).thenReturn(Optional.of(mockRole));

        // call service
        BaseResponse<RoleResponse> byRoleCode = roleService.getByRoleCode("AD");

        // verification result
        assertNotNull(byRoleCode);
        assertEquals(byRoleCode.getData().getRoleName(), mockRole.getRoleName());

        // verify
        verify(roleDao,times(1) ).getByRoleCode("AD");
    }
}
