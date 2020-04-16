package com.xb.crm.web.controller;

import com.xb.crm.model.Customer;
import com.xb.crm.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description: <p>客户信息控制器</p>
 * @author: xiongbiao
 * @since: 2020/2/21 10:38
 * @history: 1.2020/2/21 created by xiongbiao
 */
@Controller
@RequestMapping("/student")
public class StuInfoController {

    @Autowired
    private IStuInfoService stuInfoService;

    @RequestMapping("/list")
    public String list(Model model){
        List<Customer> customerList =stuInfoService.findAllCustomer();
        model.addAttribute("customerList",customerList);
        return "stuinfo/list";
    }
}
