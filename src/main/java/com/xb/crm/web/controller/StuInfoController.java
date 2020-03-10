package com.xb.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/21 10:38
 * @history: 1.2020/2/21 created by xiongbiao
 */
@Controller
@RequestMapping("/student")
public class StuInfoController {

    @RequestMapping("/list")
    public String list(){
        return "stuinfo/list";
    }
}
