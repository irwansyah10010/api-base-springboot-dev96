package com.lawencon.bootcamptest.business.role.dao.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lawencon.bootcamptest.base.BaseDao;
import com.lawencon.bootcamptest.business.role.dao.RoleDao;
import com.lawencon.bootcamptest.business.role.dto.detail.RoleResponse;
import com.lawencon.bootcamptest.business.role.dto.list.RolesResponse;
import com.lawencon.bootcamptest.business.role.model.Role;

@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao{
    
    @Override
    public List<RolesResponse> getAll(){
        List<RolesResponse> roles = new ArrayList<>();

        CriteriaBuilder cb = getConnection().getCriteriaBuilder();
        CriteriaQuery<Object[]> createQuery = cb.createQuery(Object[].class);

        Root<Role> roleCrit = createQuery.from(Role.class);
        roleCrit.alias("r");

        createQuery.multiselect(roleCrit.get("roleCode"), 
                                roleCrit.get("roleName"), 
                                roleCrit.get("isActive"));

        /* hql */
        TypedQuery<Object[]> query = getConnection().createQuery(createQuery);
        List<Object[]> resultList = query.getResultList();

        /* native */
        // Query nativeQuery = getConnection().createNativeQuery("select role_code, role_name, is_active from tb_role");

        // @SuppressWarnings("unchecked")
        // List<Object> resultList = nativeQuery.getResultList();

        resultList.forEach(result ->{
            Object[] obj = (Object[]) result;

            RolesResponse role = new RolesResponse();
            role.setRoleCode((String) obj[0]);
            role.setRoleName((String) obj[1]);
            role.setIsActive((Boolean) obj[2]);

            roles.add(role);
        });

        return roles;
    }

    @Override
    public Optional<RoleResponse> getByRoleCode(String roleCode) throws NoResultException {
        RoleResponse role = null;

        CriteriaBuilder cb = getConnection().getCriteriaBuilder();
        CriteriaQuery<Object[]> createQuery = cb.createQuery(Object[].class);

        Root<Role> roleCrit = createQuery.from(Role.class);
        roleCrit.alias("r");

        createQuery.multiselect(roleCrit.get("roleCode"), 
                                roleCrit.get("roleName"), 
                                roleCrit.get("description"));
        createQuery.where(cb.equal(roleCrit.get("roleCode"), roleCode));

        TypedQuery<Object[]> query = getConnection().createQuery(createQuery);

        try{
            Object[] obj = query.getSingleResult();   
            role = new RoleResponse();
    
            role.setRoleCode(roleCode);
            role.setRoleName((String) obj[1]);
            role.setDescription((String) obj[2]);
    
            return Optional.of(role);

        } catch(NoResultException e){
            throw new NoResultException("Role not found");
        }

    }
}
