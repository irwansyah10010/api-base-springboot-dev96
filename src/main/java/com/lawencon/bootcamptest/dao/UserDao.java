package com.lawencon.bootcamptest.dao;

import java.util.Optional;

import com.lawencon.bootcamptest.base.BaseRepository;
import com.lawencon.bootcamptest.model.person.User;

public class UserDao extends BaseRepository{
    
    public Optional<User> getByEmail(String email){
        
        this.getConnection().createNativeQuery("SELECT ");

        return null;
    }
}
