package com.lawencon.bootcamptest.business.template.dto.questionpackage;

import java.util.List;

import com.lawencon.bootcamptest.business.template.dto.questionpackagedetail.QuestionPackageDetailResDataDto;

import lombok.Data;

@Data
public class QuestionPackageResDataDto {

    private String id;
	private String questionPackageCode;
	private String questionPackageName;

	private List<QuestionPackageDetailResDataDto> listOfQuestionPackageDetail;

	private Integer totalOfQuestion;
}
