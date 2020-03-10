package com.xb.crm.web.controller;

import com.xb.crm.model.Permission;
import com.xb.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @RequestMapping("/index")
    public String index(Model model){
        //获取菜单
        List<Permission> menusList = permissionService.findAllMenus();
        for (Permission permission : menusList){
            System.out.println(permission.getName());
        }

        model.addAttribute("menusList",menusList);
        return "index";
    }

    @RequestMapping("/403")
    public String error403(){
        return "403";
    }
}
