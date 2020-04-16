package com.xb.crm.service.impl;

import com.xb.crm.mapper.PermissionMapper;
import com.xb.crm.model.CURDResult;
import com.xb.crm.model.Permission;
import com.xb.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 17:19
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Cacheable(cacheNames = "GUIMENU",key = "1234",unless = "#result.size()<=0")
    public List<Permission> findAllMenus() {
        List<Permission> allMenus = permissionMapper.findAllMenus();
        for (Permission allMenu : allMenus) {
            System.out.println("SVC:"+allMenu);
        }
        return allMenus;
    }

    @Override
    public List<Permission> findAllPermission() {
        return permissionMapper.findAllPermission();
    }

    @Override
    public List<Permission> findAllParentNode() {
        return permissionMapper.findAllParentNode();
    }

    @Override
    @CacheEvict(cacheNames = "GUIMENU",key = "1234")
    public CURDResult insert(Permission permission) {
        CURDResult curdResult = new CURDResult();
        //1:更改authorization_flag
        if (permission.getParent_id() == 0){
            //如果是根菜单，授权标识和路径都是null
            permission.setAuthorization_flag(null);
            permission.setPath(null);
        }else {
            if (StringUtils.isEmpty(permission.getPath())){
                //即未输入路径时
                curdResult.setSuccess(0);
                curdResult.setMsg("非根节点，路径不能为空.");
                return curdResult;
            }
            String[] splitPath = permission.getPath().split("/");
            StringBuffer stringBuffer = new StringBuffer(permission.getAuthorization_flag());
            stringBuffer.append("_");
            stringBuffer.append(splitPath[1]);
            permission.setAuthorization_flag(stringBuffer.toString().toUpperCase());
            System.out.println(permission.getAuthorization_flag());
        }

        //2:设置z_index
        //如果是根节点则z_index是数据库中的z_index+1；
        //如果不是根节点则z_index为父节点的z_index;
        if (permission.getParent_id() == 0){
            int maxZIndex = permissionMapper.findMaxZIndex() + 1;
            permission.setZ_index(maxZIndex);
        }else {
            //通过id找到节点
            //因为选项所在的标签名字是parent_id所以把permission的id注入到了parent_id中
            Permission parentNode = permissionMapper.findPermissionById(permission.getParent_id());
            permission.setZ_index(parentNode.getZ_index());
        }
        System.out.println(permission);

        //3:将数据插入到数据库
        permissionMapper.insert(permission);
        curdResult.setMsg("插入成功！");
        return curdResult;
    }

    @Override
    public List<Permission> findPermissionListByUserId(Integer userId) {
        return permissionMapper.findPermissionListByUserId(userId);
    }

    @Override
    public CURDResult deletePermissionByPermId(String permissionId) {
        CURDResult result = new CURDResult();
        try {
            //先删除权限角色关联表的信息
            permissionMapper.deleteRoleAndPermByPermissionId(Integer.valueOf(permissionId));

            //再删除对应权限
            permissionMapper.deletePermissionByPermissionId(Integer.valueOf(permissionId));
        } catch (NumberFormatException e) {
            result.setSuccess(0);
            result.setMsg(e.toString());
        }
        return result;
    }
}
