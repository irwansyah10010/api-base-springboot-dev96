package com.lawencon.bootcamptest.dto.assigndetail;

import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageResDataDto;

import lombok.Data;

@Data
public class AssignDetailResDataDto {
    private String id;
    private Float score;
    private String status;
    private String note;
    private QuestionPackageResDataDto questionPackage;
}
