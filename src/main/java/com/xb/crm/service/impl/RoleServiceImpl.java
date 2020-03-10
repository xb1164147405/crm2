package com.xb.crm.service.impl;

import com.xb.crm.mapper.RoleMapper;
import com.xb.crm.model.CURDResult;
import com.xb.crm.model.Role;
import com.xb.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:08
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRoles() {
        return roleMapper.findAllRoles();
    }

    @Override
    public CURDResult insert(Role role, String ids) {
        //TODO 判断该角色是否存在
        //插入角色并返回自增ID属性赋值给role参数
        roleMapper.insertRole(role);
        String[] pmsArr = ids.split(",");
        for (String pmsId : pmsArr){
            roleMapper.insertRolePermission(role.getId(),Integer.parseInt(pmsId));
        }
        return null;
    }
}
