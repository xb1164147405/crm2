package com.xb.crm.service;

import com.xb.crm.model.CURDResult;
import com.xb.crm.model.Role;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:07
 * @history: 1.2020/3/6 created by xiongbiao
 */

public interface IRoleService {

    public List<Role> findAllRoles();

    public CURDResult insert(Role role, String ids);

    public Role findRoleByRoleId(int roleId);

    public String findPermissionIdsByRoleId(int roleId);
}
