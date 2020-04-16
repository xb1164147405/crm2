package com.xb.crm.web.controller;

import com.xb.crm.model.HeadPhoto;
import com.xb.crm.model.Permission;
import com.xb.crm.model.User;
import com.xb.crm.service.IPermissionService;
import com.xb.crm.service.IUserInfoService;
import com.xb.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/20 13:07
 * @history: 1.2020/2/20 created by xiongbiao
 */
@Controller
public class IndexController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/index")
    //@Cacheable(cacheNames = "SYSTEMGUIMENU",key = "1234")
    public String index(Model model){
        //获取菜单
        List<Permission> menusList = permissionService.findAllMenus();
        for (Permission permission : menusList){
            System.out.println(permission.getName());
        }
        //获取用户登录名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //获取用户对象
        User user = userService.findUserByUsername(username);
        //获取用户头像信息对象
        HeadPhoto headPhoto = userInfoService.findHeadPhotoByUserId(user.getId());
        if (Objects.isNull(headPhoto)){
            headPhoto = new HeadPhoto();
            //使用默认头像
            headPhoto.setFile_name("head.jpg");
            model.addAttribute("headPhoto",headPhoto);
        }else {
            model.addAttribute("headPhoto",headPhoto);
        }
        model.addAttribute("username",username);
        model.addAttribute("menusList",menusList);
        return "index";
    }

    @RequestMapping("/403")
    public String error403(){
        return "403";
    }
}
