package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.lawencon.bootcamptest.base.ValidationService;
import com.lawencon.bootcamptest.constant.OptionalBuilder;
import com.lawencon.bootcamptest.dao.RoleDao;
import com.lawencon.bootcamptest.dao.UserDao;
import com.lawencon.bootcamptest.dto.BaseDeleteResDto;
import com.lawencon.bootcamptest.dto.BaseInsertResDto;
import com.lawencon.bootcamptest.dto.BaseListResDto;
import com.lawencon.bootcamptest.dto.BaseUpdateResDto;
import com.lawencon.bootcamptest.dto.role.RoleResDataDto;
import com.lawencon.bootcamptest.dto.user.UserDeleteReqDto;
import com.lawencon.bootcamptest.dto.user.UserInsertReqDto;
import com.lawencon.bootcamptest.dto.user.UserResDataDto;
import com.lawencon.bootcamptest.dto.user.UserUpdateReqDto;
import com.lawencon.bootcamptest.model.Role;
import com.lawencon.bootcamptest.model.User;

@Service
public class UserService {

	private UserDao userDao;
	private RoleDao roleDao;
	
	public UserService(UserDao userDao,RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}
	
	public BaseListResDto getByRoleCode(String roleCode,Integer page,Integer limit,Boolean ascending,
			Boolean isActive) {

		// get all
		Map<OptionalBuilder,Object> options = new HashMap<>();

		Map<String,Object> wheres = new HashMap<>();
		Map<String,Object> joins = new HashMap<>();

		joins.put("tb_role", "id");

		options.put(OptionalBuilder.JOIN, joins);
		options.put(OptionalBuilder.ID, "role_id");
		
		wheres.put("tb_user.is_active", isActive);
		wheres.put("role_code", roleCode);

		options.put(OptionalBuilder.WHERE, wheres);
		options.put(OptionalBuilder.RELATION, "AND");

		Integer countOfUser =  userDao.getCountOfAllBy("tb_user", options);
		
		options.put(OptionalBuilder.LIMIT,limit);
		options.put(OptionalBuilder.PAGE,page);

		List<User> users = userDao.getAllBy("tb_user", User.class, options);
		
		List<UserResDataDto> userResDataDtos = new ArrayList<>();
		
		BaseListResDto baseDto = new BaseListResDto();
		
		for(int i=0;i < users.size();i++) {
			User user = users.get(i);
			Role role = users.get(i).getRole();
			
			UserResDataDto userResDataDto = new UserResDataDto();
			userResDataDto.setId(user.getId());
			userResDataDto.setFullName(user.getFullName());
			userResDataDto.setPlaceOfBirth(user.getPlaceOfBirth());
			userResDataDto.setDateOfBirth(user.getDateOfBirth());
			userResDataDto.setAddress(user.getAddress());
			userResDataDto.setEmail(user.getEmail());
			
			RoleResDataDto roleResDataDto = new RoleResDataDto();
			roleResDataDto.setId(role.getId());
			roleResDataDto.setRoleCode(role.getRoleCode());
			roleResDataDto.setRoleName(role.getRoleName());
			
			userResDataDto.setRole(roleResDataDto);
			userResDataDtos.add(userResDataDto);
		}
		
		baseDto.setData(userResDataDtos);
		baseDto.setModel(User.class);
		baseDto.setPage(page);
		baseDto.setLimit(limit);
		baseDto.setTotal(countOfUser);

		List<String> listCondition = new ArrayList<>();
		listCondition.add("is-active-by-user ="+isActive);
		listCondition.add("and");
		listCondition.add("role-code ="+roleCode);

		baseDto.setCondition(listCondition);
		
		return baseDto;
	}

	@Transactional(rollbackFor = SQLException.class)
	public BaseInsertResDto save(UserInsertReqDto userReqDto){

		BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

		User user = new User();
		Role role = roleDao.getByIdAndDetach(Role.class, userReqDto.getRoleId());
		Optional<Role> optRole = Optional.ofNullable(role);

		User userInsert = null;
		if(optRole.isPresent()){
			user.setFullName(userReqDto.getFullName());
			user.setPlaceOfBirth(userReqDto.getPlaceOfBirth());
			user.setDateOfBirth(userReqDto.getDateOfBirth());
			user.setAddress(userReqDto.getAddress());
			user.setEmail(userReqDto.getEmail());
			user.setPass(userReqDto.getPass());
	
			user.setCreatedBy("1");
			user.setRole(role);

			userInsert = userDao.save(user);
		}else{
			baseInsertResDto.setMessage("Role not available");
		}

		if(userInsert != null){
			baseInsertResDto.setId(userInsert.getId());
			baseInsertResDto.setMessage("Data saved");
		}else{
			baseInsertResDto.setMessage("Data failed to save");
		}

		return baseInsertResDto;
	}

	@Transactional(rollbackFor = SQLException.class)
	public BaseUpdateResDto update(UserUpdateReqDto userReqDto){
		BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();
		//ValidationService valService = new ValidationService();

		User user = userDao.getByIdAndDetach(User.class, userReqDto.getId());
		Optional<User> optUser = Optional.ofNullable(user);

		if(optUser.isPresent()){

			//Map<Object,Object> val = new HashMap<>();

			// val.put(user.getFullName(), userReqDto.getFullName());
			// val.put(user.getPlaceOfBirth(), userReqDto.getPlaceOfBirth());
			// val.put(user.getDateOfBirth(), userReqDto.getDateOfBirth());
			// val.put(user.getAddress(), userReqDto.getAddress());

			// valService.validationUpdate(val);

			if(userReqDto.getFullName() != null && !user.getFullName().equals(userReqDto.getFullName())){
				user.setFullName(userReqDto.getFullName());
			}

			if(userReqDto.getPlaceOfBirth() != null && !user.getPlaceOfBirth().equals(userReqDto.getPlaceOfBirth())){
				user.setPlaceOfBirth(userReqDto.getPlaceOfBirth());
			}

			if(userReqDto.getDateOfBirth() != null  && !user.getDateOfBirth().equals(userReqDto.getDateOfBirth())){
				user.setDateOfBirth(userReqDto.getDateOfBirth());
			}

			if(userReqDto.getAddress() != null){
				user.setAddress(userReqDto.getAddress());
			}
	
			if(userReqDto.getRoleId() != null && !user.getRole().getId().equals(userReqDto.getRoleId())){
				Role role = roleDao.getByIdAndDetach(Role.class, userReqDto.getRoleId());
				user.setRole(role);
			}
	
			if(user.getVersion() == userReqDto.getVersion()){
				User userUpdate = userDao.update(user);
		
				if(userUpdate != null){
					baseUpdateResDto.setVersion(userUpdate.getVersion());
					baseUpdateResDto.setMessage("Data update");
				}else{
					baseUpdateResDto.setMessage("Data failed to update");
				}
			}else{
				baseUpdateResDto.setMessage("Version not same");	
			}
		}else{
			baseUpdateResDto.setMessage("Data user not available");
		}

		return baseUpdateResDto;
	}


	@Transactional(rollbackFor = SQLException.class)
	public BaseDeleteResDto softDelete(UserDeleteReqDto userReqDto){
		BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();

		User user = userDao.getByIdAndDetach(User.class, userReqDto.getId());
		Optional<User> optUser = Optional.ofNullable(user);

		if(optUser.isPresent()){
			user.setIsActive(userReqDto.getIsActive());
	
			User userUpdate = userDao.update(user);
			if(userUpdate != null){
				baseDeleteResDto.setMessage("Data deleted");
			}else{
				baseDeleteResDto.setMessage("Data failed to update");
			}
		}else{
			baseDeleteResDto.setMessage("Data user not available");
		}

		return baseDeleteResDto;
	}

	@Transactional(rollbackFor = SQLException.class)
	public BaseDeleteResDto hardDelete(UserDeleteReqDto userReqDto){
		BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();

		Map<OptionalBuilder,Object> options = new HashMap<>();
		Map<String,Object> wheres = new HashMap<>();

		wheres.put("id", userReqDto.getId());
		
		options.put(OptionalBuilder.WHERE, wheres);
		options.put(OptionalBuilder.RELATION, "AND");

		if(!options.isEmpty()){
	
			Boolean userDelete = userDao.removeBy("tb_user",User.class,options);
			if(userDelete){
				baseDeleteResDto.setMessage("Data delete permanent");
			}else{
				baseDeleteResDto.setMessage("Data failed to delete");
			}
		}else{
			baseDeleteResDto.setMessage("No option delete");
		}

		return baseDeleteResDto;
	}
}