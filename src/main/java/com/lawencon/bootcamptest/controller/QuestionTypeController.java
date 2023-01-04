package com.lawencon.bootcamptest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bootcamptest.dto.BaseListResDto;
import com.lawencon.bootcamptest.service.QuestionTypeService;

@RestController
@RequestMapping("question-types")
public class QuestionTypeController {

	private QuestionTypeService questionTypeService;
	
	public QuestionTypeController(QuestionTypeService questionTypeService) {
		this.questionTypeService = questionTypeService;
	}
	
	@GetMapping
	public BaseListResDto getAll() {
		BaseListResDto baseListDto = questionTypeService.getAll();
		
		return baseListDto;
	}
}
