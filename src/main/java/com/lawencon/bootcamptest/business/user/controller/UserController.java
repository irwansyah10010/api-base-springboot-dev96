package com.lawencon.bootcamptest.business.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bootcamptest.base.dto.BaseRequest;
import com.lawencon.bootcamptest.base.dto.BaseResponse;
import com.lawencon.bootcamptest.base.dto.error.ErrorResponse;
import com.lawencon.bootcamptest.business.user.dto.create.UserCreate;
import com.lawencon.bootcamptest.business.user.dto.delete.UserHarddelete;
import com.lawencon.bootcamptest.business.user.dto.delete.UserSoftdelete;
import com.lawencon.bootcamptest.business.user.dto.detail.UserResponse;
import com.lawencon.bootcamptest.business.user.dto.list.UsersRequest;
import com.lawencon.bootcamptest.business.user.dto.list.UsersResponse;
import com.lawencon.bootcamptest.business.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("user/")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    ResponseEntity<BaseResponse<UserResponse>> getByUsername(@PathVariable("username") String username){
        BaseResponse<UserResponse> user;

        try {
            user = userService.getByUsername(username);

            user.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            user = new BaseResponse<>(new UserResponse());

            System.out.println(e.getLocalizedMessage());

            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());
            
            user.setError(new ErrorResponse(ofNullable.orElse("no message")));
            user.setStatus(e.getMessage().contains("not found")?HttpStatus.BAD_REQUEST.value():HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("")
    ResponseEntity<BaseResponse<List<UsersResponse>>> getAll(@RequestBody(required = false) BaseRequest<UsersRequest> usersRequest, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "limit", defaultValue = "5") Integer limit){
        BaseResponse<List<UsersResponse>> users;

        try {
            if(Optional.ofNullable(usersRequest).isPresent())
                users = userService.getAll(usersRequest,page, limit);
            else{
                users = userService.getAll(page, limit);
            }

            if(Optional.ofNullable(users.getStatus()).isEmpty())
                users.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            users = new BaseResponse<>(new ArrayList<>());

            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());
            
            users.setError(new ErrorResponse(ofNullable.orElse("no message")));
            users.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<BaseResponse<UserCreate>> save(@RequestBody BaseRequest<UserCreate> userCreate) {
        BaseResponse<UserCreate> baseResponse;
        userCreate.setTypeRequest("ADD");
        try {
            baseResponse = userService.createAndUpdate(userCreate);
            baseResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            baseResponse = new BaseResponse<>(new UserCreate());

            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());
            
            baseResponse.setError(new ErrorResponse(ofNullable.orElse("no message")));
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    
    @PutMapping("")
    ResponseEntity<BaseResponse<UserCreate>> edit(@RequestBody BaseRequest<UserCreate> userCreate) {
        BaseResponse<UserCreate> baseResponse;
        userCreate.setTypeRequest("EDIT");
        try {
            baseResponse = userService.createAndUpdate(userCreate);
            baseResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            baseResponse = new BaseResponse<>(new UserCreate());

            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());
            
            baseResponse.setError(new ErrorResponse(ofNullable.orElse("no message")));
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("")
    ResponseEntity<BaseResponse<UserSoftdelete>> delete(@RequestBody BaseRequest<UserSoftdelete> userSoftdelete) {
        BaseResponse<UserSoftdelete> baseResponse;
        try {
            baseResponse = userService.delete(userSoftdelete);
            baseResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            baseResponse = new BaseResponse<>(new UserSoftdelete());

            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());
            
            baseResponse.setError(new ErrorResponse(ofNullable.orElse("no message")));
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("is-active")
    ResponseEntity<BaseResponse<UserSoftdelete>> activation(@RequestBody BaseRequest<UserSoftdelete> userSoftdelete) {
        BaseResponse<UserSoftdelete> baseResponse;
        try {
            baseResponse = userService.activation(userSoftdelete);
            baseResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            baseResponse = new BaseResponse<>(new UserSoftdelete());

            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());
            
            baseResponse.setError(new ErrorResponse(ofNullable.orElse("no message")));
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("hard-delete")
    ResponseEntity<BaseResponse<UserHarddelete>> hardDelete(@RequestBody BaseRequest<UserHarddelete> userHarddelete) {
        BaseResponse<UserHarddelete> baseResponse;
        try {
            baseResponse = userService.hardDelete(userHarddelete);
            baseResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            baseResponse = new BaseResponse<>(new UserHarddelete());

            Optional<String> ofNullable = Optional.ofNullable(e.getMessage());
            
            baseResponse.setError(new ErrorResponse(ofNullable.orElse("no message")));
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
