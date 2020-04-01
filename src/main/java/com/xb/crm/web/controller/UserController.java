package com.xb.crm.web.controller;

import com.xb.crm.model.CURDResult;
import com.xb.crm.model.PageResult;
import com.xb.crm.model.Role;
import com.xb.crm.model.User;
import com.xb.crm.service.IRoleService;
import com.xb.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    public String list(){
        return "user/list";
    }

    @RequestMapping("/add")
    public String add(Model model){
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles",roles);
        return "user/add";
    }

    @RequestMapping("/edit")
    public String edit(Model model,Integer userId){
        List<Role> roles = roleService.findAllRoles();
        User user = userService.findUserAndRolesByUserId(userId);
        Map<String,String> saveRoleNames = new HashMap<>();
        for (Role role:user.getRoles()){
            //存储角色名称用于复选框判断是否存在该角色进行勾选
            saveRoleNames.put(role.getName(),role.getName());
        }
        model.addAttribute("roles",roles);
        model.addAttribute("user",user);
        model.addAttribute("roleNameMap",saveRoleNames);
        return "user/edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public CURDResult save(User user,Integer[] roleIds){
        CURDResult result = new CURDResult();
        result = userService.save(user,roleIds);
        return result;
    }

    @RequestMapping("/listJson")
    @ResponseBody
    public PageResult<User> listJson(User condition, int page, int limit){
        return userService.findPageResult(condition, page, limit);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CURDResult delete(String userId){
        CURDResult result = new CURDResult();
        result = userService.deleteUserByUserId(userId);
        return result;
    }


}
