package com.lawencon.bootcamptest.business.user.dao;

import java.util.List;

import com.lawencon.bootcamptest.business.user.dto.list.UsersResponse;

public interface UserDao {
    public List<UsersResponse> getAll();

    public List<UsersResponse> getAll(Integer page, Integer limit);

    public List<UsersResponse> getAll(String username, Integer page, Integer limit);

    public Boolean isSoftDelete(String username);

    public Boolean isActive(String username);
}
