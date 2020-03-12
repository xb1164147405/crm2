package com.xb.crm.mapper;

import com.xb.crm.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:02
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Repository
public interface UserMapper {

    public User findUserByUsername(String username);

    public int findCountByMap(Map<String,Object> map);

    public List<User> findListByMap(Map<String,Object> map);

    public void insertUser(User user);

    public void insertUserAndRole(int userId,Integer roleId);

    public User findUserAndRoleByUserId(Integer userId);


    public User findUserByUserId(int id);

    public void updateUser(User user);

    public void deleteRolesByUserId(int userId);
}
