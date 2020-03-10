package com.xb.crm.web.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: <p>启动web安全功能</p>
 * @author: xiongbiao
 * @since: 2020/3/2 9:13
 * @history: 1.2020/3/2 created by xiongbiao
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置spring-security支持iframe
        http.headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();
        //允许ajax的post请求
        http.csrf().disable();
        http .authorizeRequests()
                /*.antMatchers("/css/**", "/index").permitAll()*/
                /*访问user下的路径需要USER角色的权限*/
                /*.antMatchers("/user/**").hasRole("USER")*/
                .antMatchers("/courseorder/list").hasAnyAuthority("COURSEORDER_READ")
                .antMatchers("/courseorder/detail").hasAnyAuthority("COURSEORDER_READ")
                .antMatchers("/courseorder/listJson").hasAnyAuthority("COURSEORDER_READ")
                .antMatchers("/courseorder/add").hasAnyAuthority("COURSEORDER_ADD")
                .antMatchers("/courseorder/detele").hasAnyAuthority("COURSEORDER_DETELE")
                .antMatchers("/courseorder/save").hasAnyAuthority("COURSEORDER_SAVE")
                .antMatchers("/courseorder/edit").hasAnyAuthority("COURSEORDER_EDIT")
                /*代表login不需要拦截*/
                .antMatchers("/login").permitAll()
                /*设置所有访问路径都需要请求认证*/
                .antMatchers("/**").fullyAuthenticated()
                .and()
                .formLogin()
                /*设置自己的登录页面*/
                .loginPage("/login")
                /*登录成功后跳转至指定页面*/
                .successForwardUrl("/index")
                /*设置自己的登录错误页面*/
                .failureUrl("/login-error");
    }
}
