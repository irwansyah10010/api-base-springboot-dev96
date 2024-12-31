package com.lawencon.bootcamptest.business.template.dto.questionpackage;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.lawencon.bootcamptest.business.template.dto.questionbank.QuestionBankResDataDto;

import lombok.Data;

@Data
public class QuestionPackageUpdateReqDto {

    @NotNull(message = "Id not null")
    private String id;

    private String questionPackageCode;
	private String questionPackageName;

    private List<QuestionBankResDataDto> questionBanks;


    private Integer version;
}
