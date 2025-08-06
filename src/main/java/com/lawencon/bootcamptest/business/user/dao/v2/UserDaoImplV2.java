package com.lawencon.bootcamptest.business.user.dao.v2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.bootcamptest.base.dao.build.BaseDao;
import com.lawencon.bootcamptest.business.file.model.File;
import com.lawencon.bootcamptest.business.role.model.Role;
import com.lawencon.bootcamptest.business.user.dao.UserDao;
import com.lawencon.bootcamptest.business.user.dto.detail.UserResponse;
import com.lawencon.bootcamptest.business.user.dto.list.UsersResponse;
import com.lawencon.bootcamptest.business.user.model.StatusUser;
import com.lawencon.bootcamptest.business.user.model.User;


@Repository
@Profile("v2")
public class UserDaoImplV2 extends BaseDao<User> implements UserDao{
    
    public Optional<User> loadUsername(String username){
        Query nativeQuery = this.getConnection()
                    .createNativeQuery("SELECT * FROM tb_user where username = :username", User.class)
                    .setParameter("username", username);

        User singleResult = (User) nativeQuery.getSingleResult();

        return Optional.ofNullable(singleResult);
    }

    @Override
    public Optional<UserResponse> getByUsername(String username) throws Exception{
        initGetter(User.class,
        "username",
        "fullName","placeOfBirth","dateOfBirth","email","phoneNumber","address","isActive")
        .join()
            .innerJoin(Role.class, 
            "role",
                "roleCode","roleName")

            .leftJoin(File.class, 
            "profilePicture",
                "file")

            .leftJoin(StatusUser.class, 
            "statusUser",
                "id", "enabled", "isLoginWeb").closeJoin()
            
            .search().equal("username", username).and().closeSearch();


        return getSingleResult(UserResponse.class);
    }

    void buildGetAll(){
        initGetter(User.class,
        "username","fullName","placeOfBirth","email","isActive")
        .join()
            .innerJoin(Role.class, 
             "role",
             "roleCode")
            .leftJoin(File.class, 
        "profilePicture",
            "file")
            .leftJoin(StatusUser.class, 
            "statusUser",
            "enabled", "isLoginWeb")
        .closeJoin();
    }

    void buildGetAllAndIsActive(Boolean isActive){
        buildGetAll();
        
        search()
        .equal("isActive", isActive).and()
        .closeSearch();
    }


    @Override
    public List<UsersResponse> getAll() {
        buildGetAll();

        return getAll(UsersResponse.class);
    }

    @Override
    public List<UsersResponse> getAll(String username) {
        buildGetAll();
        
        search()
        .likeFront("username", username).and()
        .closeSearch();

        return getAll(UsersResponse.class);
    }

    @Override
    public List<UsersResponse> getAllAndActive() {
        buildGetAllAndIsActive(true);
        
        return getAll(UsersResponse.class);
    }

    @Override
    public List<UsersResponse> getAllAndActive(Integer page, Integer limit) {
        buildGetAllAndIsActive(true);
        
        return getAll(UsersResponse.class, page, limit);
    }

    @Override
    public List<UsersResponse> getAllAndActive(String username, Integer page, Integer limit) {
        buildGetAllAndIsActive(true);
        
        search()
        .likeFront("username", username).and()
        .closeSearch();

        return getAll(UsersResponse.class, page, limit);
    }

    @Override
    public Boolean isSoftDelete(String username) {
        Map<String, Object> data = new HashMap<>();
        data.put("isActive", false);

        initUpdate(User.class)
            .setFieldsUpdate(data)
                .search().equal("username", username).and().closeSearch();

        return updated();
    }

    @Override
    public Boolean isActive(String username) {
        Map<String, Object> data = new HashMap<>();
        data.put("isActive", true);

        initUpdate(User.class)
            .setFieldsUpdate(data)
                .search().equal("username", username).and().closeSearch();

        return updated();
    }

    @Override
    public Boolean isDelete(String username) {
        initDelete(User.class)
            .search().equal("username", username).and()
            .closeSearch();
        
        return delete();
    }

    
    @Override
    public User getByIdAndDetach(String id) {
        return super.getByIdAndDetach(User.class,id);
    }

    @Override
    public User update(User entityClass) {
        return super.update(entityClass);
    }


    @Override
    public User save(User entityClass) {
        
        return super.save(entityClass);
    }

}
