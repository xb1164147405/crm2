package com.xb.crm.service;

import com.xb.crm.model.CURDResult;
import com.xb.crm.model.Permission;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 17:18
 * @history: 1.2020/3/6 created by xiongbiao
 */

public interface IPermissionService {

    public List<Permission> findAllMenus();

    public List<Permission> findAllPermission();

    public List<Permission> findAllParentNode();

    public CURDResult insert(Permission permission);

    public List<Permission> findPermissionListByUserId(Integer userId);

    public CURDResult deletePermissionByPermId(String permissionId);
}
