package com.xb.crm.service.impl;

import com.xb.crm.mapper.UserMapper;
import com.xb.crm.model.PageResult;
import com.xb.crm.model.User;
import com.xb.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:08
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Service
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
}
