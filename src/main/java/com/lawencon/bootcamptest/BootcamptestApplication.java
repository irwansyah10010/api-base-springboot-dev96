package com.lawencon.bootcamptest;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lawencon.bootcamptest.business.role.dto.detail.RoleResponse;
import com.lawencon.bootcamptest.util.AssignUtil;

@SpringBootApplication
public class BootcamptestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcamptestApplication.class, args);
	}


}