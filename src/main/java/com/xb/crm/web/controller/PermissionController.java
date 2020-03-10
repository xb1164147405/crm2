package com.xb.crm.web.controller;

import com.xb.crm.model.CURDResult;
import com.xb.crm.model.PageResult;
import com.xb.crm.model.Permission;
import com.xb.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
@RequestMapping(value = "/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping(value = "/list")
    public String list(){
        return "permission/list";
    }


    @RequestMapping(value = "/add")
    public String add(Model model){
        //获取父功能节点数据
        List<Permission> allParentNode = permissionService.findAllParentNode();
        model.addAttribute("allParentNode",allParentNode);
        return "permission/add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public CURDResult save(Permission permission){
        //TODO 参数校验
        CURDResult result = new CURDResult();
        result = permissionService.insert(permission);

        return result;
    }

    /**
     *
     *
     * @return
     */
    @RequestMapping("/listJson")
    @ResponseBody
    public PageResult<Permission> listJson(Permission condition){
        PageResult<Permission> result = new PageResult<>();
        result.setData(permissionService.findAllPermission());
        return result;
    }
}
