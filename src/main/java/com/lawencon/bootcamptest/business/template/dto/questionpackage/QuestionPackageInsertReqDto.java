package com.lawencon.bootcamptest.business.template.dto.questionpackage;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.lawencon.bootcamptest.business.template.dto.questionbank.QuestionBankResDataDto;

import lombok.Data;

@Data
public class QuestionPackageInsertReqDto {
    
    @NotBlank(message = "code is must required")
    private String questionPackageCode;

    @NotBlank(message = "name is must required")
	private String questionPackageName;

    private List<QuestionBankResDataDto> questionBanks;
}
