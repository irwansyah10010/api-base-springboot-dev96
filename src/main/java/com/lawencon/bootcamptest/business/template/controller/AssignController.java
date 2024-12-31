package com.lawencon.bootcamptest.business.template.controller;
// package com.lawencon.bootcamptest.business.controller;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.lawencon.bootcamptest.business.dto.BaseDeleteResDto;
// import com.lawencon.bootcamptest.business.dto.BaseInsertResDto;
// import com.lawencon.bootcamptest.business.dto.BaseListResDto;
// import com.lawencon.bootcamptest.business.dto.assign.AssignReqDeleteDto;
// import com.lawencon.bootcamptest.business.dto.assign.AssignReqInsertDto;
// import com.lawencon.bootcamptest.business.service.AssignService;

// @RestController
// @RequestMapping("assigns")
// public class AssignController {
    
//     private AssignService assignService;

//     public AssignController(AssignService assignService){
//         this.assignService = assignService;
//     }


//     @GetMapping
//     public BaseListResDto getAll(@RequestParam(value = "page",required = false) Integer page,@RequestParam(value = "limit",required = false) Integer limit,
//     @RequestParam(value = "ascending",required = false) Boolean ascending,
//     @RequestParam(value = "isActive",required = false,defaultValue = "true") Boolean isActive){
//         BaseListResDto baseListResDto = assignService.getAll(page, limit, ascending, isActive);

//         return baseListResDto;
//     }

//     @PostMapping
//     public BaseInsertResDto save(@RequestBody AssignReqInsertDto assignReqInsertDto){
//         BaseInsertResDto baseInsertResDto = assignService.save(assignReqInsertDto);

//         return baseInsertResDto;
//     }


//     @DeleteMapping
//     public BaseDeleteResDto delete(@RequestBody AssignReqDeleteDto deleteDto){
//         BaseDeleteResDto baseDeleteResDto = assignService.hardDelete(deleteDto);

//         return baseDeleteResDto;
//     }
// }
