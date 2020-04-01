package com.xb.crm.service.impl;

import com.xb.crm.mapper.UserMapper;
import com.xb.crm.model.CURDResult;
import com.xb.crm.model.PageResult;
import com.xb.crm.model.User;
import com.xb.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:08
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public PageResult<User> findPageResult(User condition, int page, int pageSize) {
        PageResult<User> result = new PageResult<>();
        result.setCode(0);
        Map<String,Object> params = new HashMap<>();
        //模糊查询条件添加
        params.put("condition",condition);
        //select * from t_course_order limit 0,10;
        params.put("start",(page-1) * pageSize);
        params.put("pageSize",pageSize);
        //获取总记录数据
        result.setCount(userMapper.findCountByMap(params));
        //获取查询的数据
        List<User> list = userMapper.findListByMap(params);
        result.setData(list);
        return result;
    }

    @Override
    public CURDResult save(User user, Integer[] roleIds) {
        CURDResult result = new CURDResult();
        if (user.getId() == 0){
            String validStr = user.validUser();
            if (validStr != null){
                result.setSuccess(0);
                result.setMsg(validStr);
                return result;
            }
            //插入新用户
            if (userMapper.findUserByUsername(user.getUsername()) != null){
                result.setSuccess(0);
                result.setMsg("用户名已被占用。");
                return result;
            }
            userMapper.insertUser(user);
            /*for (int roleId : roleIds){
                userMapper.insertUserAndRole(user.getId(),roleId);
            }*/
            //角色批插入
            if (roleIds != null && roleIds.length != 0){
                userMapper.insertUserAndRolesBatch(user.getId(),roleIds);
            }
        }else {
            //更新用户数据
            //判断修改的角色名称在原角色中是否存在
            User beforeUser = userMapper.findUserByUserId(user.getId());
            User hasUser = userMapper.findUserByUsername(user.getUsername());
            if (hasUser != null && !Objects.equals(beforeUser.getUsername(),hasUser.getUsername())){
                result.setSuccess(0);
                result.setMsg("该用户名已被占用。");
                return result;
            }
            String validStr = user.validUser();
            if (validStr != null){
                result.setSuccess(0);
                result.setMsg(validStr);
                return result;
            }
            //修改用户数据
            userMapper.updateUser(user);
            //删除以前用户的角色数据
            userMapper.deleteRolesByUserId(user.getId());
            //插入新的角色数据
            if (roleIds != null && roleIds.length != 0){
                /*for (int roleId : roleIds){
                    userMapper.insertUserAndRole(user.getId(),roleId);
                }*/
                userMapper.insertUserAndRolesBatch(user.getId(),roleIds);
            }
        }

        return result;
    }

    @Override
    public User findUserAndRolesByUserId(Integer userId) {
        return userMapper.findUserAndRoleByUserId(userId);
    }

    @Override
    public CURDResult deleteUserByUserId(String userId) {
        CURDResult result = new CURDResult();
        try {
            userMapper.deleteRolesByUserId(Integer.valueOf(userId));
            userMapper.deleteUserByUserId(Integer.valueOf(userId));
        } catch (NumberFormatException e) {
            result.setSuccess(0);
            result.setMsg(e.toString());
        }
        return result;
    }
}
