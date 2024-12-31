package com.lawencon.bootcamptest.base.dto.attribute;

import lombok.Data;

@Data
public class Paging {
    private Integer countOfData;
    private Integer page;
    private Integer limit; 
}
