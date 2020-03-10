package com.xb.crm.web.security;

import com.xb.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/5 19:43
 * @history: 1.2020/3/5 created by xiongbiao
 */
@Configuration
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //用户认证
        System.out.println("用户名：" + username);

        //1:根据用户名从数据库查找，有没有这个用户信息
        com.xb.crm.model.User dbUser = userService.findUserByUsername(username);
        if (dbUser == null){
            //用户不存在
            return null;
        }
        //2：如果有用户信息，返回一个User对象
        String pwd = passwordEncoder.encode(dbUser.getPassword());
        System.out.println("密码加密后：" + pwd);
        return new User(username,
                //密码是从数据中获取，而且密码需要加密器进行加密
                pwd,
                //给当前用户进行授权
                getCurrentUserAuthorities());
    }

    /**
     * 获取当前用户拥有的权限
     * @return
     */
    public Collection<? extends GrantedAuthority> getCurrentUserAuthorities(){

        List<GrantedAuthority> list = new ArrayList();
        list.add(new SimpleGrantedAuthority("COURSEORDER_READ"));
        list.add(new SimpleGrantedAuthority("COURSEORDER_DELETE"));
        return list;
    }
}
