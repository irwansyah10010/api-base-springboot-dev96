package com.lawencon.bootcamptest.dto.score;

import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailResDataDto;
import com.lawencon.bootcamptest.dto.questionpackagedetail.QuestionPackageDetailResDataDto;

import lombok.Data;

@Data
public class ScoreResDataDto {
    
    private QuestionPackageDetailResDataDto questionPackageDetailResDataDto;
    private AssignDetailResDataDto assignDetailResDataDto;
    private Float score;
}
