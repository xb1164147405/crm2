package com.xb.crm.service.impl;

import com.xb.crm.mapper.RoleMapper;
import com.xb.crm.model.CURDResult;
import com.xb.crm.model.Role;
import com.xb.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

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
        CURDResult result = new CURDResult();
        if (role.getId() == 0){
            //插入操作
            //判断该角色是否存在
            Role roleByRoleName = roleMapper.findRoleByRoleName(role.getName());
            if (roleByRoleName != null){
                result.setSuccess(0);
                result.setMsg("该角色已存在。");
                return result;
            }
            //插入角色并返回自增ID属性赋值给role参数
            roleMapper.insertRole(role);
        }else{
            //更新数据
            //判断修改的角色名称在原角色中是否存在
            Role beforeRole = roleMapper.findRoleByRoleId(role.getId());
            Role hasRole = roleMapper.findRoleByRoleName(role.getName());
            if (hasRole != null && !Objects.equals(beforeRole.getName(),hasRole.getName())){
                result.setSuccess(0);
                result.setMsg("该角色已存在。");
                return result;
            }

            //修改角色的名称和描述
            roleMapper.updateRole(role);

            //先删除旧的权限
            roleMapper.deletePremissionByRoleId(role.getId());
        }
        String[] pmsArr = ids.split(",");
        for (String pmsId : pmsArr){
            if (!StringUtils.isEmpty(pmsId)){
                roleMapper.insertRolePermission(role.getId(),Integer.parseInt(pmsId));
            }

        }

        return result;
    }

    @Override
    public Role findRoleByRoleId(int roleId) {
        return roleMapper.findRoleByRoleId(roleId);
    }

    @Override
    public String findPermissionIdsByRoleId(int roleId) {
        List<Integer> permissionIdArr = roleMapper.findPermissionIdByRoleId(roleId);
        if (permissionIdArr.size() == 0){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer("");
        for (Integer id : permissionIdArr){
            stringBuffer.append(id).append(",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);

        return stringBuffer.toString();
    }
}
