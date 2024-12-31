package com.lawencon.bootcamptest.business.template.controller;
// package com.lawencon.bootcamptest.business.controller;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.lawencon.bootcamptest.business.dto.BaseDeleteResDto;
// import com.lawencon.bootcamptest.business.dto.BaseInsertResDto;
// import com.lawencon.bootcamptest.business.dto.BaseListResDto;
// import com.lawencon.bootcamptest.business.dto.BaseUpdateResDto;
// import com.lawencon.bootcamptest.business.dto.questionbank.QuestionBankDeleteReqData;
// import com.lawencon.bootcamptest.business.dto.questionbank.QuestionBankInsertReqDto;
// import com.lawencon.bootcamptest.business.dto.questionbank.QuestionBankUpdateReqDto;
// import com.lawencon.bootcamptest.business.service.QuestionBankService;

// @RestController
// @RequestMapping("question-banks")
// public class QuestionBankController {
    
//     private QuestionBankService questionBankService;

//     public QuestionBankController(QuestionBankService questionBankService){
//         this.questionBankService = questionBankService;
//     }

//     @GetMapping
//     public BaseListResDto getAll(@RequestParam(value = "startPosition",required = false) Integer startPosition,
//                                 @RequestParam(value = "limit",required = false) Integer limit,
//                                 @RequestParam(value = "ascending",required = false) Boolean ascending,
//                                 @RequestParam(value = "isActive",required = false,defaultValue = "true") Boolean isActive){

//         BaseListResDto baseListResDto = questionBankService.getAll(startPosition, limit, ascending, isActive);

//         return baseListResDto;
//     }


//     @PostMapping
//     public BaseInsertResDto save(@RequestBody QuestionBankInsertReqDto resDto){
//         BaseInsertResDto baseInsertResDto = questionBankService.save(resDto);

//         return baseInsertResDto;
//     }

//     @PutMapping
//     public BaseUpdateResDto update(@RequestBody QuestionBankUpdateReqDto resDto){
//         BaseUpdateResDto baseUpdateResDto = questionBankService.update(resDto);

//         return baseUpdateResDto;
//     }

//     @PutMapping("/change-is-active")
//     public BaseDeleteResDto softDelete(@RequestBody QuestionBankDeleteReqData resDto){
//         BaseDeleteResDto baseDeleteResDto = questionBankService.softDelete(resDto);

//         return baseDeleteResDto;
//     }

//     @DeleteMapping
//     public BaseDeleteResDto hardDelete(@RequestBody QuestionBankDeleteReqData resDto){
//         BaseDeleteResDto baseDeleteResDto = questionBankService.hardDelete(resDto);

//         return baseDeleteResDto;
//     }

// }
