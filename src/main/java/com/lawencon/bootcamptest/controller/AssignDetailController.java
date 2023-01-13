package com.lawencon.bootcamptest.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bootcamptest.dto.BaseDeleteResDto;
import com.lawencon.bootcamptest.dto.BaseInsertResDto;
import com.lawencon.bootcamptest.dto.BaseListResDto;
import com.lawencon.bootcamptest.dto.BaseUpdateResDto;
import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailReqDeleteDto;
import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailReqInsertDto;
import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailReqUpdateDto;
import com.lawencon.bootcamptest.service.AssignDetailService;

@RestController
@RequestMapping("assign-details")
public class AssignDetailController {
    
    private AssignDetailService assignDetailService;

    public AssignDetailController(AssignDetailService assignDetailService){
        this.assignDetailService = assignDetailService;
    }

    @GetMapping
    public BaseListResDto getAll(@RequestParam(value = "assignId",required = false) String assignId,@RequestParam(value = "page",required = false) Integer page,@RequestParam(value = "limit",required = false) Integer limit,
    @RequestParam(value = "ascending",required = false) Boolean ascending,
    @RequestParam(value = "isActive",required = false,defaultValue = "true") Boolean isActive){
        BaseListResDto  baseListResDto = assignDetailService.getAll(assignId, page, limit, ascending, isActive);

        return baseListResDto;
    }

    @PostMapping
    public BaseInsertResDto save(@RequestBody AssignDetailReqInsertDto assignDetailReqInsertDto){
        BaseInsertResDto baseInsertResDto = assignDetailService.save(assignDetailReqInsertDto);

        return baseInsertResDto;
    }

    @PutMapping
    public BaseUpdateResDto update(@RequestBody AssignDetailReqUpdateDto assignDetailReqUpdateDto){
        BaseUpdateResDto baseUpdateResDto = assignDetailService.update(assignDetailReqUpdateDto);


        return baseUpdateResDto;
    }


    @DeleteMapping
    public BaseDeleteResDto delete(@RequestBody AssignDetailReqDeleteDto deleteDto){
        BaseDeleteResDto baseDeleteResDto = assignDetailService.hardDelete(deleteDto);

        return baseDeleteResDto;
    }

}
