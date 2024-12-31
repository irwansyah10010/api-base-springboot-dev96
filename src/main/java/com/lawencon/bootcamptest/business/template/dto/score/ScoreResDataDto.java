package com.lawencon.bootcamptest.business.template.dto.score;

import com.lawencon.bootcamptest.business.template.dto.assigndetail.AssignDetailResDataDto;
import com.lawencon.bootcamptest.business.template.dto.questionpackagedetail.QuestionPackageDetailResDataDto;

import lombok.Data;

@Data
public class ScoreResDataDto {
    
    private QuestionPackageDetailResDataDto questionPackageDetailResDataDto;
    private AssignDetailResDataDto assignDetailResDataDto;
    private Float score;
}
