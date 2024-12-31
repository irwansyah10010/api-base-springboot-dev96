package com.lawencon.bootcamptest.base.dto.error;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private List<FieldError> fields;
    @NonNull private String message;
}
