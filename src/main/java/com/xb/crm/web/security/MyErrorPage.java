package com.xb.crm.web.security;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/5 21:52
 * @history: 1.2020/3/5 created by xiongbiao
 */
@Configuration
public class MyErrorPage implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //添加错误页面
        //无权限时跳转到403页面
        registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/403"));
    }
}
