package com.xb.crm.mapper;

import com.xb.crm.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 16:36
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Repository
public interface PermissionMapper {

    public List<Permission> findAllMenus();

    public List<Permission> findAllPermission();

    public List<Permission> findAllParentNode();

    public void insert(Permission permission);

    public int findMaxZIndex();

    public Permission findPermissionById(int id);

    public List<Permission> findPermissionListByUserId(Integer userId);
}
