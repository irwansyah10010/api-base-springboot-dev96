package com.lawencon.bootcamptest.business.template.dto.questionmultiplechoice;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class QuestionMultipleChoiceDeleteReqDto {
    @NotNull(message = "id is must not null")
    private String id;
    private Boolean isActive;
}
