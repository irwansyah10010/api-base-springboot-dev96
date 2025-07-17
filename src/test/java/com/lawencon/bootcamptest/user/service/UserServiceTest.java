package com.lawencon.bootcamptest.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.lawencon.bootcamptest.base.dto.BaseRequest;
import com.lawencon.bootcamptest.base.dto.BaseResponse;
import com.lawencon.bootcamptest.base.dto.attribute.Search;
import com.lawencon.bootcamptest.business.role.dao.v1.RoleDaoImplV1;
import com.lawencon.bootcamptest.business.role.model.Role;
import com.lawencon.bootcamptest.business.user.dao.v1.UserDaoImplV1;
import com.lawencon.bootcamptest.business.user.dto.create.UserCreate;
import com.lawencon.bootcamptest.business.user.dto.delete.UserHarddelete;
import com.lawencon.bootcamptest.business.user.dto.delete.UserSoftdelete;
import com.lawencon.bootcamptest.business.user.dto.detail.UserResponse;
import com.lawencon.bootcamptest.business.user.dto.list.UsersResponse;
import com.lawencon.bootcamptest.business.user.model.User;
import com.lawencon.bootcamptest.business.user.service.UserService;

@SpringBootTest
public class UserServiceTest {
    
    @Mock
    private UserDaoImplV1 userDao;

    @Mock
    private RoleDaoImplV1 roleDao;

    @InjectMocks
    private UserService userService;

    private UserResponse mockUserResponse;

    private List<UsersResponse> mockUsersResponses;

    private User mockUser;
    private UserCreate mockUserCreate;
    private Role roleCreate;

    private UserCreate mockUserUpdate;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        // single
        mockUserResponse = new UserResponse();
        mockUserResponse.setUsername("Username");
        mockUserResponse.setFullName("FullName");
        mockUserResponse.setPlaceOfBirth("eOfBirth");
        mockUserResponse.setDateOfBirth(LocalDate.of(2012, 12, 12));
        mockUserResponse.setEmail("setEmail");
        mockUserResponse.setPhoneNumber("neNumber");
        mockUserResponse.setAddress("tAddress");
        mockUserResponse.setRole(".setRole");
        mockUserResponse.setProfile("tProfile");

        // list
        UsersResponse usersResponse1 = new UsersResponse();
        usersResponse1.setUsername("Username_1");
        usersResponse1.setFullName("FullName_1");
        usersResponse1.setPlaceOfBirth("eOfBirth_1");
        usersResponse1.setEmail("setEmail_1");
        usersResponse1.setRole(".setRole_1");
        usersResponse1.setProfile("tProfile_1");
        usersResponse1.setIsActive(true);

        UsersResponse usersResponse2 = new UsersResponse();
        usersResponse2.setUsername("Username_2");
        usersResponse2.setFullName("FullName_2");
        usersResponse2.setPlaceOfBirth("eOfBirth_2");
        usersResponse2.setEmail("setEmail_2");
        usersResponse2.setRole(".setRole_2");
        usersResponse2.setProfile("tProfile_2");
        usersResponse1.setIsActive(true);

        mockUsersResponses = Arrays.asList(
            usersResponse1,
            usersResponse2
        );

        // user create and update base request
        roleCreate = new Role("setRole", "Rolename", "description");
        
        // user create
        mockUser = new User();
        mockUser.setUsername("Username");
        mockUser.setFullName("FullName");
        mockUser.setPlaceOfBirth("eOfBirth");
        mockUser.setDateOfBirth(LocalDate.of(2012, 12, 12));
        mockUser.setEmail("setEmail");
        mockUser.setPhoneNumber("neNumber");
        mockUser.setAddress("tAddress");
        mockUser.setRole(roleCreate);
        mockUser.setIsActive(true);

        mockUserCreate = new UserCreate();
        mockUserCreate.setUsername("Username");
        mockUserCreate.setFullName("FullName");
        mockUserCreate.setPlaceOfBirth("eOfBirth");
        mockUserCreate.setDateOfBirth(LocalDate.of(2012, 12, 12));
        mockUserCreate.setEmail("setEmail");
        mockUserCreate.setPhoneNumber("neNumber");
        mockUserCreate.setAddress("tAddress");
        mockUserCreate.setRole(roleCreate.getRoleCode());

        // 
        mockUserUpdate = new UserCreate();
        mockUserUpdate.setUsername("Username");
        mockUserUpdate.setFullName("FullName rename");
        mockUserUpdate.setPlaceOfBirth("eOfBirth");
        mockUserUpdate.setDateOfBirth(LocalDate.of(2012, 12, 12));
        mockUserUpdate.setEmail("setEmail");
        mockUserUpdate.setPhoneNumber("neNumber");
        mockUserUpdate.setAddress("tAddress");
        mockUserUpdate.setRole(roleCreate.getRoleCode());
    }

    @Test
    public void testGetAll(){

        // present mock
        when(userDao.getAllAndActive()).thenReturn(mockUsersResponses);

        // call service
        BaseResponse<List<UsersResponse>> all = userService.getAll();

        // verification result
        assertNotNull(all);
        assertEquals(2, all.getData().size());
        assertEquals("Username_1", all.getData().get(0).getUsername());

        // verify
        verify(userDao, times(1)).getAllAndActive();
    }

    @Test
    public void testGetByUsername() throws Exception{

        // present mock
        when(userDao.getByUsername("Username")).thenReturn(Optional.of(mockUserResponse));

        // call service
        BaseResponse<UserResponse> byUsername = userService.getByUsername("Username");

        // verification result 
        assertNotNull(byUsername);
        assertEquals(byUsername.getData().getFullName(), mockUserResponse.getFullName());

        // verify
        verify(userDao, times(1)).getByUsername("Username");
    }

    @Test
    public void create(){
        // Mock behavior untuk simulasikan create
        when(roleDao.getByIdAndDetach(roleCreate.getRoleCode())).thenReturn(roleCreate);
        when(userDao.save(any(User.class))).thenReturn(mockUser);
        
        // Panggil service untuk membuat data
        BaseRequest<UserCreate> baseRequest = new BaseRequest<>();
        baseRequest.setData(mockUserCreate);
        baseRequest.setTypeRequest("ADD");
        BaseResponse<UserCreate> response = userService.createAndUpdate(baseRequest);

        // Verifikasi hasil
        assertNotNull(response);
        assertEquals(null, response.getError());

        // Verifikasi bahwa metode save() dipanggil satu kali
        verify(roleDao, times(1)).getByIdAndDetach(roleCreate.getRoleCode());
        verify(userDao, times(1)).save(any(User.class));
    }

    @Test
    public void update(){
        // Mock behavior untuk simulasikan update
        when(roleDao.getByIdAndDetach(roleCreate.getRoleCode())).thenReturn(roleCreate);
        when(userDao.getByIdAndDetach(mockUserUpdate.getUsername())).thenReturn(mockUser);
        when(userDao.update(any(User.class))).thenReturn(mockUser);
        
        // Panggil service untuk membuat data
        BaseRequest<UserCreate> baseRequest = new BaseRequest<>();
        baseRequest.setData(mockUserUpdate);
        baseRequest.setTypeRequest("EDIT");
        BaseResponse<UserCreate> response = userService.createAndUpdate(baseRequest);

        // Verifikasi hasil
        assertNotNull(response);
        assertEquals(null, response.getError());
        assertNotEquals(mockUser, mockUserUpdate);

        // Verifikasi bahwa jumlah metode mock yang dipanggil
        verify(roleDao, times(1)).getByIdAndDetach(roleCreate.getRoleCode());
        verify(userDao, times(1)).getByIdAndDetach(mockUserUpdate.getUsername());
        verify(userDao, times(1)).update(any(User.class));
    }

    @Test
    public void softDelete(){
        // Mock behavior untuk simulasikan userDao soft delete
        when(userDao.isSoftDelete(mockUser.getUsername())).thenReturn(true);

        // Panggil service untuk membuat data
        BaseRequest<UserSoftdelete> baseRequest = new BaseRequest<>();
        UserSoftdelete userSoftdelete = new UserSoftdelete();
        userSoftdelete.setUsername("Username");
        baseRequest.setData(userSoftdelete);
        BaseResponse<UserSoftdelete> response = userService.delete(baseRequest);

        // verifikasi hasil
        assertNotNull(response);
        assertEquals(null, response.getError());

        // verifikasi jumlah pemanggilan method
        verify(userDao, times(1)).isSoftDelete(mockUser.getUsername());
    }

    @Test
    public void hardDelete(){
        // Mock behavior untuk simulasikan hard delete
        when(userDao.isDelete(mockUser.getUsername())).thenReturn(true);

        // panggil service untuk delete data
        Search search = new Search();
        search.setFields(Arrays.asList("username"));
        search.setValue(mockUser.getUsername());

        BaseRequest<UserHarddelete> baseRequest = new BaseRequest<>();
        UserHarddelete userHarddelete = new UserHarddelete();
        userHarddelete.setSearch(search);
        baseRequest.setData(userHarddelete);
        BaseResponse<UserHarddelete> response = userService.hardDelete(baseRequest);

        // verifikasi hasil
        assertNotNull(response);
        assertEquals(null, response.getError());

        // verifikasi jumlah pemanggilan method
        verify(userDao, times(1)).isDelete(mockUser.getUsername());
    }

    @Test
    public void activation(){
        // Mock behavior untuk simulasikan userDao soft delete
        when(userDao.isActive(mockUser.getUsername())).thenReturn(true);

        // Panggil service untuk membuat data
        BaseRequest<UserSoftdelete> baseRequest = new BaseRequest<>();
        UserSoftdelete userSoftdelete = new UserSoftdelete();
        userSoftdelete.setUsername("Username");
        baseRequest.setData(userSoftdelete);
        BaseResponse<UserSoftdelete> response = userService.activation(baseRequest);

        // verifikasi hasil
        assertNotNull(response);
        assertEquals(null, response.getError());

        // verifikasi jumlah pemanggilan method
        verify(userDao, times(1)).isActive(mockUser.getUsername());
    }
}
