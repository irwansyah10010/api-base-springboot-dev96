package com.lawencon.bootcamptest.business.template.controller;
// package com.lawencon.bootcamptest.business.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.lawencon.bootcamptest.business.dto.BaseListResDto;
// import com.lawencon.bootcamptest.business.dto.BaseSingleResDto;
// import com.lawencon.bootcamptest.business.service.RoleService;

// @RestController
// @RequestMapping("roles")
// public class RoleController {

// 	@Autowired
// 	private RoleService roleService;
	
// 	@GetMapping
// 	public BaseListResDto getAll(@RequestParam(value="isActive",required = false) Boolean isActive){
// 		return roleService.getAll(isActive);
// 	}

// 	@GetMapping("/id/{id}")
// 	public BaseSingleResDto getById(@PathVariable("id") String id){
// 		return roleService.getById(id);
// 	}
// }
