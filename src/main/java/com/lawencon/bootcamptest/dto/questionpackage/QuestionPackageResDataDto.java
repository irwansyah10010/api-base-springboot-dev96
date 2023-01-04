package com.lawencon.bootcamptest.dto.questionpackage;

import java.util.List;

import com.lawencon.bootcamptest.dto.questionpackagedetail.QuestionPackageDetailResDataDto;

import lombok.Data;

@Data
public class QuestionPackageResDataDto {

    private String id;
	private String questionPackageCode;
	private String questionPackageName;

	private List<QuestionPackageDetailResDataDto> listOfQuestionPackageDetail;

	private Integer totalOfQuestion;
}
