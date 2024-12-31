package com.lawencon.bootcamptest.business.template.dto.questionbank;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.lawencon.bootcamptest.business.template.dto.questionmultiplechoice.QuestionMultipleChoiceResDataDto;

import lombok.Data;

@Data
public class QuestionBankUpdateReqDto {

    @NotNull(message = "id not null")
    private String id;

    private String question;

    private String questionTypeId;

    private List<QuestionMultipleChoiceResDataDto> multipleChoices;

    @NotNull(message = "version not null")
    private Integer version;
}
