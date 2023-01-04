package com.lawencon.bootcamptest.dto.questionbank;

import java.util.List;

import com.lawencon.bootcamptest.dto.questionmultiplechoice.QuestionMultipleChoiceResDataDto;

import lombok.Data;

@Data
public class QuestionBankResDataDto {
    
    private String id;
    private String question;
    private String questionType;

    private List<QuestionMultipleChoiceResDataDto> multipleChoice;
}
