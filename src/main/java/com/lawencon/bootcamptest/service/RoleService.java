package com.lawencon.bootcamptest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lawencon.bootcamptest.constant.OptionalBuilder;
import com.lawencon.bootcamptest.dao.RoleDao;
import com.lawencon.bootcamptest.dto.BaseListResDto;
import com.lawencon.bootcamptest.dto.BaseSingleResDto;
import com.lawencon.bootcamptest.dto.role.RoleResDataDto;
import com.lawencon.bootcamptest.model.Role;

@Service
public class RoleService {

	private RoleDao roleDao;
	
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public BaseListResDto getAll(Boolean isActive){
		Map<OptionalBuilder,Object> options = new HashMap<>();

		Map<String,Object> wheres = new HashMap<>();
		wheres.put("is_active", true);

		options.put(OptionalBuilder.WHERE, wheres);
		options.put(OptionalBuilder.RELATION, "AND");

		List<Role> roles =  roleDao.getAllBy("tb_role",Role.class,options);
		Integer countOfRole =  roleDao.getCountOfAllBy("tb_role",options);
		
		List<RoleResDataDto> roleResDataDtos = new ArrayList<>();
		
		BaseListResDto baseDto = new BaseListResDto();
		
		for(int i=0;i < roles.size();i++) {
			Role role = roles.get(i);
			
			RoleResDataDto roleResDataDto = new RoleResDataDto();
			roleResDataDto.setId(role.getId());
			roleResDataDto.setRoleName(role.getRoleName());
			roleResDataDto.setRoleCode(role.getRoleCode());
			
			roleResDataDtos.add(roleResDataDto);
		}
		
		baseDto.setData(roleResDataDtos);
		baseDto.setTotal(countOfRole);
		baseDto.setModel(Role.class);
		
		return baseDto;
	}

	public BaseSingleResDto getById(String id) {
		Role role =  roleDao.getByIdAndDetach(Role.class, id);

		BaseSingleResDto baseSingleDto = new BaseSingleResDto();
		RoleResDataDto dataDto = new RoleResDataDto();

		List<String> condition = new ArrayList<>();
		condition.add("id equals "+id);
		
		if(role != null){
			dataDto.setId(id);
			dataDto.setRoleName(role.getRoleName());
			dataDto.setRoleCode(role.getRoleCode());
		}

		baseSingleDto.setModel(Role.class);
		baseSingleDto.setData(dataDto);
		baseSingleDto.setCondition(condition);

		return baseSingleDto;
	}
}
