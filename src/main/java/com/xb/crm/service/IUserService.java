package com.xb.crm.service;

import com.xb.crm.model.User;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:07
 * @history: 1.2020/3/6 created by xiongbiao
 */

public interface IUserService {

    public User findUserByUsername(String username);
}