package com.lawencon.bootcamptest.business.user.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.bootcamptest.business.user.dto.detail.UserResponse;
import com.lawencon.bootcamptest.business.user.dto.list.UsersResponse;
import com.lawencon.bootcamptest.business.user.model.User;

public interface UserDao{

    public Optional<UserResponse> getByUsername(String username) throws Exception;

    public List<UsersResponse> getAll();

    public List<UsersResponse> getAll(String username);

    public List<UsersResponse> getAllAndActive();

    public List<UsersResponse> getAllAndActive(Integer page, Integer limit);

    public List<UsersResponse> getAllAndActive(String username, Integer page, Integer limit);

    public Boolean isSoftDelete(String username);

    public Boolean isActive(String username);

    public Boolean isDelete(String username);

    public User getByIdAndDetach( String id);

    public User update(User entityClass);

    public User save(User entityClass);

}
