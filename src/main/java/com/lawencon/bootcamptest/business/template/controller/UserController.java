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
// import com.lawencon.bootcamptest.business.dto.user.UserDeleteReqDto;
// import com.lawencon.bootcamptest.business.dto.user.UserInsertReqDto;
// import com.lawencon.bootcamptest.business.dto.user.UserUpdateReqDto;
// import com.lawencon.bootcamptest.business.service.UserService;

// @RestController
// @RequestMapping("users")
// public class UserController {

// 	private UserService userService;
	
// 	public UserController(UserService userService) {
// 		this.userService = userService;
// 	}
	
// 	@GetMapping("/role-code")
// 	public BaseListResDto getByRoleCode(@RequestParam("roleCode") String roleCode,
// 			@RequestParam(value="page",required = false) Integer page,
// 			@RequestParam(value="limit",required = false) Integer limit,
// 			@RequestParam(value="ascending",required = false) Boolean ascending,
// 			@RequestParam(value="isActive",required = false,defaultValue = "true") Boolean isActive) {
		
// 		BaseListResDto baseListDto = userService.getByRoleCode(roleCode, page, limit, ascending, isActive);
		
// 		return baseListDto;
// 	}

// 	@PostMapping
// 	public BaseInsertResDto save(@RequestBody UserInsertReqDto userReqDto){
// 		BaseInsertResDto insertResDto = userService.save(userReqDto);

// 		return insertResDto;
// 	}

// 	@PutMapping
// 	public BaseUpdateResDto update(@RequestBody UserUpdateReqDto userReqDto){
// 		BaseUpdateResDto updateResDto = userService.update(userReqDto);

// 		return updateResDto;
// 	}

// 	@PutMapping("/change-is-active")
// 	public BaseDeleteResDto softDelete(@RequestBody UserDeleteReqDto userReqDto){
// 		BaseDeleteResDto deleteResDto = userService.softDelete(userReqDto);

// 		return deleteResDto;
// 	}

// 	@DeleteMapping
// 	public BaseDeleteResDto hardDelete(@RequestBody UserDeleteReqDto userReqDto){
// 		BaseDeleteResDto deleteResDto = userService.hardDelete(userReqDto);

// 		return deleteResDto;
// 	}
// }
