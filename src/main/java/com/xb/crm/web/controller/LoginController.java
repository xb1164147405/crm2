package com.xb.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/1 21:09
 * @history: 1.2020/3/1 created by xiongbiao
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(){

        return "login-error";
    }
}
