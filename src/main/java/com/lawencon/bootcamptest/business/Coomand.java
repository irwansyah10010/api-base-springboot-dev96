package com.lawencon.bootcamptest.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lawencon.bootcamptest.business.user.dao.v1.UserDaoImpl;
import com.lawencon.bootcamptest.business.user.dto.detail.UserResponseV2;

@Component
public class Coomand implements CommandLineRunner{
    
    @Autowired
	private UserDaoImpl userDao;

    private void show(){
		
	

		System.out.println("True");
	}

    @Override
    public void run(String... args) throws Exception {
        // show();
    }
}
