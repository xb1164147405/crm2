package com.xb.crm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xb.crm.mapper.PermissionMapper;
import com.xb.crm.model.CURDResult;
import com.xb.crm.model.Permission;
import com.xb.crm.service.IPermissionService;
import com.xb.crm.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    private final static Logger LOG = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 缓存时间（秒）
     */
    @Value(value = "${spring.redis.cache.time}")
    private long CACHE_TIME;

    @Override
    public List<Permission> findAllMenus(){
        boolean hasKey = redisUtil.hasKey("allMenus");
        if (hasKey){
           LOG.info("======从redis缓存中读取菜单======");
           List<Permission> allMenus = redisUtil.getListObj("allMenus",Permission.class);
           return allMenus;
        }
        LOG.info("======从数据库中获取菜单======");
        List<Permission> allMenus = permissionMapper.findAllMenus();
        redisUtil.set("allMenus", JSON.toJSONString(allMenus),CACHE_TIME);
        return allMenus;
    }

    @Override
    public List<Permission> findAllPermission() {
        boolean hasKey = redisUtil.hasKey("findAllPermission");
        if (hasKey){
            LOG.info("======从redis缓存中读取所有权限信息======");
            List<Permission> allPermissionList = redisUtil.getListObj("findAllPermission",Permission.class);
            return allPermissionList;
        }
        LOG.info("======从数据库中获取所有权限信息======");
        List<Permission> allPermissionList = permissionMapper.findAllPermission();
        redisUtil.set("findAllPermission", JSON.toJSONString(allPermissionList),CACHE_TIME);
        return allPermissionList;
    }

//    @Override
//    public List<Permission> findAllParentNode() {
//        boolean hasKey = redisUtil.hasKey("findAllParentNode");
//        if (hasKey){
//            LOG.info("======从redis缓存中读取所有节点信息======");
//            List<Permission> allPermissionList = new ArrayList<>();
//            String allPermissionListJsonStr =  String.valueOf(redisUtil.get("findAllParentNode"));
//            allPermissionList = JSON.parseArray(allPermissionListJsonStr,Permission.class);
//            return allPermissionList;
//        }
//        LOG.info("======从数据库中获取所有父节点信息======");
//        List<Permission> allParentNodes = permissionMapper.findAllParentNode();
//        redisUtil.set("findAllParentNode", JSON.toJSONString(allParentNodes),CACHE_TIME);
//        return allParentNodes;
//    }

    @Override
    public List<Permission> findAllParentNode() {
        boolean hasKey = redisUtil.hasKey("findAllParentNode");
        if (hasKey){
            LOG.info("======从redis缓存中读取所有节点信息======");
            List<Permission> allPermissionList = redisUtil.getListObj(
                    "findAllParentNode",Permission.class);
            return allPermissionList;
        }
        LOG.info("======从数据库中获取所有父节点信息======");
        List<Permission> allParentNodes = permissionMapper.findAllParentNode();
        redisUtil.set("findAllParentNode", JSON.toJSONString(allParentNodes),CACHE_TIME);
        return allParentNodes;
    }

    @Override
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
        LOG.info("======权限数据插入或更改成功并删除相关缓存======");
        redisUtil.del("allMenus","findAllPermission","findAllParentNode");
        return curdResult;
    }

    @Override
    public List<Permission> findPermissionListByUserId(Integer userId) {
        LOG.info("======SpringSecurity从数据库中获取所有权限信息======");
        List<Permission> allPermissions = permissionMapper.findPermissionListByUserId(userId);
        return allPermissions;
    }

    @Override
    public CURDResult deletePermissionByPermId(String permissionId) {
        CURDResult result = new CURDResult();
        try {
            //先删除权限角色关联表的信息
            permissionMapper.deleteRoleAndPermByPermissionId(Integer.valueOf(permissionId));

            //再删除对应权限
            permissionMapper.deletePermissionByPermissionId(Integer.valueOf(permissionId));
            LOG.info("======权限数据删除成功并删除相关缓存======");
            redisUtil.del("allMenus","findAllPermission","findAllParentNode");
        } catch (NumberFormatException e) {
            result.setSuccess(0);
            result.setMsg(e.toString());
        }
        return result;
    }
}
