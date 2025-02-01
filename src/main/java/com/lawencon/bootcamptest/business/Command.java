package com.lawencon.bootcamptest.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lawencon.bootcamptest.business.user.dao.UserDao;

@Component
public class Command implements CommandLineRunner{
    
    @Autowired
	private UserDao userDao;

    private void show(){
		
	

		System.out.println("True");
	}

    @Override
    public void run(String... args) throws Exception {
        // show();
    }
}
