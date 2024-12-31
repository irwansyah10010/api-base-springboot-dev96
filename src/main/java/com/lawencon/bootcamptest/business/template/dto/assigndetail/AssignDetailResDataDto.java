package com.lawencon.bootcamptest.business.template.dto.assigndetail;

import com.lawencon.bootcamptest.business.template.dto.questionpackage.QuestionPackageResDataDto;

import lombok.Data;

@Data
public class AssignDetailResDataDto {
    private String id;
    private Float score;
    private String status;
    private String note;
    private QuestionPackageResDataDto questionPackage;
}
