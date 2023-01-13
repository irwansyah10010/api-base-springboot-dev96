package com.lawencon.bootcamptest.dto.assigndetail;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssignDetailReqInsertDto {
    
    private Float score;
    private String status;
    private String note;

    @NotNull(message = "assign is must not null")
    private String assignId;

    @NotNull(message = "question package is must not null")
    private String questionPackageId;
}
