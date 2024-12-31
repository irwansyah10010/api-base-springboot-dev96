package com.lawencon.bootcamptest.business.user.dto.list;

import com.lawencon.bootcamptest.base.dto.attribute.Search;

import lombok.Data;

@Data
public class UsersRequest {
    
    private Search search;
}
