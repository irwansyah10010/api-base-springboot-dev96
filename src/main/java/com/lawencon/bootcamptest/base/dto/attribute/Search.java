package com.lawencon.bootcamptest.base.dto.attribute;

import java.util.List;

import lombok.Data;

@Data
public class Search {
    private List<String> fields;
    private Object value;
}
