package com.xb.crm.web.security;

import com.xb.crm.model.Permission;
import com.xb.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Description: <p>启动web安全功能</p>
 * @author: xiongbiao
 * @since: 2020/3/2 9:13
 * @history: 1.2020/3/2 created by xiongbiao
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IPermissionService permissionService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置spring-security支持iframe
        http.headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();
        //允许ajax的post请求
        http.csrf().disable();
        //加载所有的权限数据
        List<Permission> permissionList = permissionService.findAllPermission();
        for (Permission permission : permissionList){
            //设置什么样的路径需要什么权限标识
            if (!StringUtils.isEmpty(permission.getPath())){
                http.authorizeRequests()
                        .antMatchers(permission.getPath()).hasAnyAuthority(permission.getAuthorization_flag());
            }
        }
        http .authorizeRequests()
                /*代表静态资源不需要拦截*/
                .antMatchers("/img/**","/login/**","/js/**","/css/**","/images/*","/fonts/**","/**/*.png","/**/*.jpg").permitAll()
                /*代表login不需要拦截*/
                .antMatchers("/login").permitAll()
                /*设置所有访问路径都需要请求认证*/
                .antMatchers("/**").fullyAuthenticated()
                .and()
                .formLogin()
                /*设置自己的登录页面*/
                .loginPage("/login")
                /*登录失败携带参数错误跳转*/
                .failureUrl("/login?error=true")
                /*登录成功后跳转至指定页面*/
                .successForwardUrl("/index");
                /*设置自己的登录错误页面*/
//                .failureUrl("/login-error");

    }
/*加了这个方法就登录就无法跳转到首页*/
//    @Override
//    public void configure(WebSecurity web){
//        web.ignoring().antMatchers("/login/**","/img/**");
//    }
}
