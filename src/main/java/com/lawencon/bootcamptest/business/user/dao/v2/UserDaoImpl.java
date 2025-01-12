package com.lawencon.bootcamptest.business.user.dao.v2;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.JoinType;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.bootcamptest.base.BaseDao;
import com.lawencon.bootcamptest.business.file.model.File;
import com.lawencon.bootcamptest.business.role.model.Role;
import com.lawencon.bootcamptest.business.user.dto.detail.UserResponse;
import com.lawencon.bootcamptest.business.user.dto.list.UsersResponse;
import com.lawencon.bootcamptest.business.user.model.StatusUser;
import com.lawencon.bootcamptest.business.user.model.User;

@Repository
@Profile("use-base")
public class UserDaoImpl extends BaseDao<User>{
    
    public Optional<UserResponse> getByUsername(String username) throws Exception{
        initiated(User.class,
        "username",
        "fullName",
        "placeOfBirth",
        "dateOfBirth",
        "email",
        "phoneNumber",
        "address",
        "isActive"
        );

        addJoinField(Role.class, 
            JoinType.INNER,
             "role",
             "roleCode","roleName"); // note kombinasi field

        addJoinField(File.class, 
        JoinType.LEFT,
        "profilePicture",
        "file");

        addJoinField(StatusUser.class, 
        JoinType.LEFT,
        "statusUser",
        "id", "enabled", "isLoginWeb");

        searchEqual(new HashMap<>(){{
            put("username", username);
        }}, "AND");

        return getSingleResult(UserResponse.class);
    }

    private void buildGetAll(){
        initiated(User.class,
            "username",
            "fullName",
            "placeOfBirth",
            "email",
            "isActive"
        );

        addJoinField(Role.class, 
            JoinType.INNER,
             "role",
             "roleCode","roleName"); // note kombinasi field

        addJoinField(File.class, 
        JoinType.LEFT,
        "profilePicture",
        "file");

        addJoinField(StatusUser.class, 
        JoinType.LEFT,
        "statusUser",
        "enabled", "isLoginWeb");
    }

    public List<UsersResponse> getAll(){
        buildGetAll();

        return getAll();
    }

    public List<UsersResponse> getAll(int page, int limit){
        buildGetAll();

        return getAll(page, limit);
    }
}
