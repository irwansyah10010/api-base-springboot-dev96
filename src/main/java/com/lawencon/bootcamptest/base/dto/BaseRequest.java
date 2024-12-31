package com.lawencon.bootcamptest.base.dto;

import lombok.Data;

@Data
public class BaseRequest<T> {

    private T data;
    private String typeRequest;
}
