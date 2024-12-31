package com.lawencon.bootcamptest.business.user.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.bootcamptest.base.dto.BaseRequest;
import com.lawencon.bootcamptest.base.dto.BaseResponse;
import com.lawencon.bootcamptest.base.dto.attribute.Search;
import com.lawencon.bootcamptest.base.dto.error.ErrorResponse;
import com.lawencon.bootcamptest.base.dto.error.FieldError;
import com.lawencon.bootcamptest.business.role.dao.RoleDao;
import com.lawencon.bootcamptest.business.role.model.Role;
import com.lawencon.bootcamptest.business.user.dao.UserDao;
import com.lawencon.bootcamptest.business.user.dto.create.UserCreate;
import com.lawencon.bootcamptest.business.user.dto.delete.UserHarddelete;
import com.lawencon.bootcamptest.business.user.dto.delete.UserSoftdelete;
import com.lawencon.bootcamptest.business.user.dto.detail.UserResponse;
import com.lawencon.bootcamptest.business.user.dto.list.UsersRequest;
import com.lawencon.bootcamptest.business.user.dto.list.UsersResponse;
import com.lawencon.bootcamptest.business.user.model.StatusUser;
import com.lawencon.bootcamptest.business.user.model.User;

@Service
public class UserService implements UserDetailsService{

    @Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	

	@Transactional(rollbackOn = Exception.class)
	public BaseResponse<UserCreate> createAndUpdate(BaseRequest<UserCreate> baseRequest){
		User createUser = new User();
		UserCreate user = baseRequest.getData();
		User userByUsername = userDao.getByIdAndDetach(User.class, user.getUsername()); // change boolean

		/**
		* list error
		**/ 
		List<FieldError> fieldErrors = new ArrayList<>();
		ErrorResponse errorResponse = new ErrorResponse();


		/**
		*  create validasi data
		**/

		/*
		* check user is exist
		*/
		if(Optional.ofNullable(userByUsername).isPresent() && baseRequest.getTypeRequest().equals("ADD"))
			errorResponse.setMessage("Username "+user.getUsername()+" is exist");

		/*
		 * check role is null
		 */
		Role role = roleDao.getByIdAndDetach(Role.class, user.getRole());
		if(!Optional.ofNullable(role).isPresent())
			fieldErrors.add(new FieldError("role", "role isn't available"));
				    
		/*
		* check username
		*/
		
		/*
		* check fullName
		*/
		
		/*
		* check placeOfBirth
		*/
		
		/*
		* check dateOfBirth
		*/
		
		/*
		* check email
		*/
		
		/*
		* check phoneNumber
		*/
		
		/*
		* check address
		*/
		
		/**
		 * set 
		 */
		BaseResponse<UserCreate> baseResponse = new BaseResponse<UserCreate>();

		if(!fieldErrors.isEmpty() || errorResponse.getMessage() != null){
			errorResponse.setFields(fieldErrors);

			baseResponse.setData(new UserCreate());
			baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			baseResponse.setError(errorResponse);

			return baseResponse;
		}

		if(baseRequest.getTypeRequest().equals("EDIT"))
			createUser=userDao.getById(User.class, user.getUsername());
		
		createUser.setRole(role);
		createUser.setUsername(user.getUsername());
		createUser.setFullName(user.getFullName());
		createUser.setPlaceOfBirth(user.getPlaceOfBirth());
		createUser.setDateOfBirth(user.getDateOfBirth());
		createUser.setEmail(user.getEmail());
		createUser.setPhoneNumber(user.getPhoneNumber());
		createUser.setAddress(user.getAddress());		
		
		User userSave;
		if(baseRequest.getTypeRequest().equals("EDIT"))
			userSave = userDao.update(createUser);
		else{
			StatusUser statusUser = new StatusUser();
			statusUser.setEnabled(false);
			statusUser.setIsLoginWeb(false);
			statusUser.setInvalidLoginCounter(0);
			
			StatusUser statusSave = userDao.save(statusUser);

			if(Optional.ofNullable(statusSave).isPresent())
				createUser.setStatusUser(statusUser);
			
			userSave = userDao.save(createUser);
		}


		if(Optional.ofNullable(userSave).isPresent()){
			user.setStatusId(userSave.getStatusUser().getId());

			baseResponse.setData(user);
		}

		return baseResponse;
	}

	@Transactional(rollbackOn = Exception.class)
	public BaseResponse<UserSoftdelete> delete(BaseRequest<UserSoftdelete> baseRequest){
		String username = baseRequest.getData().getUsername();
		Boolean softDelete = userDao.isSoftDelete(username);
		
		BaseResponse<UserSoftdelete> baseResponse = new BaseResponse<>();
		if(softDelete){
			UserSoftdelete userSoftdelete = new UserSoftdelete();
			userSoftdelete.setUsername(username);

			baseResponse.setData(userSoftdelete);

			return baseResponse;
		}

		baseResponse.setError(new ErrorResponse("User isn't found"));
		baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());

		return baseResponse;

	}


	/**
	 * 
	 * @param baseRequest
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public BaseResponse<UserHarddelete> hardDelete(BaseRequest<UserHarddelete> baseRequest){
		Search search = baseRequest.getData().getSearch();

		Boolean isDelete = userDao.isDelete(search, User.class);
		
		BaseResponse<UserHarddelete> baseResponse = new BaseResponse<>();
		if(isDelete){
			UserHarddelete userHarddelete = new UserHarddelete();
			userHarddelete.setSearch(search);

			baseResponse.setData(userHarddelete);

			return baseResponse;
		}

		baseResponse.setError(new ErrorResponse("User isn't found"));
		baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());

		return baseResponse;
	}

	/*
	 * 
	 */
	public BaseResponse<List<UsersResponse>> getAll(){
		return new BaseResponse<>(userDao.getAll());
	}
	
	/*
	 * 
	 */
	public BaseResponse<List<UsersResponse>> getAll(Integer page, Integer limit){
		return new BaseResponse<>(userDao.getAll(page, limit));
	}

	/*
	 * 
	 */
	public BaseResponse<List<UsersResponse>> getAll(BaseRequest<UsersRequest> usersRequest,Integer page, Integer limit){

		List<String> fields = Arrays.asList("username");

		// create validasi data

		Search search = usersRequest.getData().getSearch();
		List<String> fieldNows = search.getFields();

		if(fields.equals(fieldNows))
			return new BaseResponse<>(userDao.getAll((String) search.getValue(),page, limit));

		BaseResponse<List<UsersResponse>> baseResponse = new BaseResponse<>(new ArrayList<>());
		baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());

		ErrorResponse errorResponse = new ErrorResponse("wrong fields");

		/*
		 * check detail field
		 */
		fieldNows.forEach(field ->{
			
		});
		baseResponse.setError(errorResponse);

		return baseResponse;
	}



    public BaseResponse<UserResponse> getByUsername(String username){
        return new BaseResponse<>(userDao.getByUsername(username).get());
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userDao.loadUsername(username);

        if(userByUsername.isPresent()){
            User user = userByUsername.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPass(), null);
        }

		throw new  UsernameNotFoundException("username and password is wrong");
	}
}