package com.xb.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/20 13:07
 * @history: 1.2020/2/20 created by xiongbiao
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/403")
    public String error403(){
        return "403";
    }
}
