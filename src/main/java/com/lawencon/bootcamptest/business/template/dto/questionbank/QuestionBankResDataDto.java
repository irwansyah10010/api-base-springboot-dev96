package com.lawencon.bootcamptest.business.template.dto.questionbank;

import java.util.List;

import com.lawencon.bootcamptest.business.template.dto.questionmultiplechoice.QuestionMultipleChoiceResDataDto;

import lombok.Data;

@Data
public class QuestionBankResDataDto {
    
    private String id;
    private String question;
    private String questionType;

    private List<QuestionMultipleChoiceResDataDto> multipleChoice;
}
