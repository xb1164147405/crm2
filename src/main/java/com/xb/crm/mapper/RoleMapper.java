package com.xb.crm.mapper;

import com.xb.crm.model.Role;
import com.xb.crm.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:02
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Repository
public interface RoleMapper {

    public List<Role> findAllRoles();

    public void insertRole(Role role);


    public void insertRolePermission(int roleId, int permissionId);
}
