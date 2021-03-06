package com.xb.crm.service.impl;

import com.alibaba.fastjson.JSON;
import com.xb.crm.mapper.RoleMapper;
import com.xb.crm.model.CURDResult;
import com.xb.crm.model.Role;
import com.xb.crm.service.IRoleService;
import com.xb.crm.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {

    private final static Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 缓存时间（秒）
     */
    @Value(value = "${spring.redis.cache.time}")
    private long CACHE_TIME;

    @Override
    public List<Role> findAllRoles() {
        boolean hasKey = redisUtil.hasKey("findAllRoles");
        if (hasKey){
            LOG.info("======从redis缓存中读取所有角色信息======");
            List<Role> allRolesList = redisUtil.getListObj("findAllRoles",Role.class);
            return allRolesList;
        }
        LOG.info("======从数据库中获取所有角色数据======");
        List<Role> allRolesList = roleMapper.findAllRoles();
        redisUtil.set("findAllRoles", JSON.toJSONString(allRolesList),CACHE_TIME);
        return allRolesList;
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
        /*String[] pmsArr = ids.split(",");
        for (String pmsId : pmsArr){
            if (!StringUtils.isEmpty(pmsId)){
                roleMapper.insertRolePermission(role.getId(),Integer.parseInt(pmsId));
            }

        }*/
        /*批处理插入*/
        if (!StringUtils.isEmpty(ids)){
            roleMapper.insertRolePermissionBatch(role.getId(),ids);
        }
        LOG.info("======角色数据修改或插入成功并删除相关所有缓存======");
        redisUtil.del("findAllRoles");
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

    @Override
    public CURDResult deleteByRoleId(String role_id) {
        CURDResult result = new CURDResult();
        try {
            List<Integer> permissionIdList = roleMapper.findPermissionIdByRoleId(Integer.valueOf(role_id));
            if (permissionIdList.size() != 0){
                StringBuffer permissionIds = new StringBuffer();
                for (Integer permissionid : permissionIdList){
                    permissionIds.append(permissionid).append(",");
                }
                permissionIds.deleteCharAt(permissionIds.length()-1);
                roleMapper.deletePermissionByPermissIdsBatch(permissionIds.toString());
            }
            roleMapper.deleteRoleByRoleId(Integer.valueOf(role_id));
            LOG.info("======角色删除成功并删除相关所有缓存======");
            redisUtil.del("findAllRoles");
        } catch (NumberFormatException e) {
            result.setSuccess(0);
            result.setMsg("删除角色失败：" + e);
            return result;
        }
        return result;
    }
}
