package com.xb.crm.mapper;

import com.xb.crm.model.User;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:02
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Repository
public interface UserMapper {

    public User findUserByUsername(String username);
}
