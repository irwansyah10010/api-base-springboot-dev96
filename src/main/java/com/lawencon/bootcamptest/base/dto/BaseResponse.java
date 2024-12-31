package com.lawencon.bootcamptest.base.dto;

import com.lawencon.bootcamptest.base.dto.error.ErrorResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    @NonNull private T data;
    private Integer status;
    private ErrorResponse error;
}
