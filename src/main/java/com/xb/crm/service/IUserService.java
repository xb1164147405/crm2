package com.xb.crm.service;

import com.xb.crm.model.CURDResult;
import com.xb.crm.model.PageResult;
import com.xb.crm.model.User;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:07
 * @history: 1.2020/3/6 created by xiongbiao
 */

public interface IUserService {

    public User findUserByUsername(String username);

    public PageResult<User> findPageResult(User condition, int page, int pageSize);

    public CURDResult save(User user, Integer[] roleIds);

    public User findUserAndRolesByUserId(Integer userId);
}
