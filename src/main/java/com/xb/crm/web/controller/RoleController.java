package com.xb.crm.web.controller;

import com.xb.crm.model.CURDResult;
import com.xb.crm.model.PageResult;
import com.xb.crm.model.Permission;
import com.xb.crm.model.Role;
import com.xb.crm.service.IPermissionService;
import com.xb.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/7 16:17
 * @history: 1.2020/3/7 created by xiongbiao
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @RequestMapping(value = "/list")
    public String list(){
        return "role/list";
    }


    @RequestMapping(value = "/add")
    public String add(){

        return "role/add";
    }

    @RequestMapping("edit")
    public String edit(Model model,int roleId){
        Role role = roleService.findRoleByRoleId(roleId);
        model.addAttribute("role",role);
        model.addAttribute("ids",roleService.findPermissionIdsByRoleId(roleId));
        return "role/edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public CURDResult save(Role role,String ids){
        //TODO 参数校验
        CURDResult result = new CURDResult();
        result = roleService.insert(role, ids);
        return result;
    }

    /**
     *
     * @param page 显示的当前页
     * @param limit 每次显示多少条
     * @return
     */
    @RequestMapping("/listJson")
    @ResponseBody
    public PageResult<Role> listJson(Role condition, int page, int limit){
        PageResult<Role> result = new PageResult<>();
        result.setData(roleService.findAllRoles());
        return result;
    }
}
