package com.lawencon.bootcamptest.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bootcamptest.dto.BaseDeleteResDto;
import com.lawencon.bootcamptest.dto.BaseInsertResDto;
import com.lawencon.bootcamptest.dto.BaseListResDto;
import com.lawencon.bootcamptest.dto.BaseUpdateResDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageDeleteReqDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageInsertReqDto;
import com.lawencon.bootcamptest.dto.questionpackage.QuestionPackageUpdateReqDto;
import com.lawencon.bootcamptest.service.QuestionPackageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("question-packages")
public class QuestionPackageController {
    
    private QuestionPackageService questionPackageService;

    public QuestionPackageController(QuestionPackageService questionPackageService){
        this.questionPackageService = questionPackageService;
    }

    @GetMapping
    public BaseListResDto getAll(@RequestParam(value = "questionTypeCode",required = false) String questionTypeCode,
        @RequestParam(value = "startPosition",required = false) Integer startPosition,
        @RequestParam(value = "limit",required = false) Integer limit,
        @RequestParam(value = "isActive",required = false) Boolean isActive){
            
        BaseListResDto baseListDto = questionPackageService.getAll(questionTypeCode,startPosition,limit,isActive);

        return baseListDto;
    }

    @PostMapping
    public BaseInsertResDto save(@RequestBody QuestionPackageInsertReqDto bankInsertResDto ) {
        BaseInsertResDto baseInsertResDto = questionPackageService.save(bankInsertResDto);
        
        return baseInsertResDto;
    }

    @PutMapping
    public BaseUpdateResDto update(@RequestBody QuestionPackageUpdateReqDto resDto ) {
        BaseUpdateResDto baseUpdateResDto = questionPackageService.update(resDto);
        
        return baseUpdateResDto;
    }

    @PutMapping("change-is-active")
    public BaseDeleteResDto softDelete(@RequestBody QuestionPackageDeleteReqDto resDto ) {
        BaseDeleteResDto baseUpdateResDto = questionPackageService.softDelete(resDto);
        
        return baseUpdateResDto;
    }

    @DeleteMapping
    public BaseDeleteResDto hardDelete(@RequestBody QuestionPackageDeleteReqDto resDto) {
        BaseDeleteResDto baseUpdateResDto = questionPackageService.hardDelete(resDto);
        
        return baseUpdateResDto;
    }
}
