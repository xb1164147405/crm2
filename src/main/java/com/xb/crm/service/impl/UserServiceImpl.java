package com.xb.crm.service.impl;

import com.xb.crm.mapper.UserMapper;
import com.xb.crm.model.User;
import com.xb.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
