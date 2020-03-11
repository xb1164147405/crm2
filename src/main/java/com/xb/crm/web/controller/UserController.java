package com.xb.crm.web.controller;

import com.xb.crm.model.PageResult;
import com.xb.crm.model.Role;
import com.xb.crm.model.User;
import com.xb.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/11 15:47
 * @history: 1.2020/3/11 created by xiongbiao
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
    public String list(){
        return "user/list";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/listJson")
    @ResponseBody
    public PageResult<User> listJson(User condition, int page, int limit){
        return userService.findPageResult(condition, page, limit);
    }
}
