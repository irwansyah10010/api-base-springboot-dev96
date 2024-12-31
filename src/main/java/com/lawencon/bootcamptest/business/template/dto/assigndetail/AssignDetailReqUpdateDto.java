package com.lawencon.bootcamptest.business.template.dto.assigndetail;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssignDetailReqUpdateDto {
    @NotNull(message = "id is must not null")
    private String id;
    private String status;
    private String note;
    private Float score;

    @NotNull(message = "version is must not null")
    private Integer version;
}
