package com.xb.crm.web.controller;

import com.xb.crm.model.CourseOrder;
import com.xb.crm.model.PageResult;
import com.xb.crm.service.ICourseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/21 9:56
 * @history: 1.2020/2/21 created by xiongbiao
 */
@Controller
@RequestMapping("/courseorder")
public class CourseOrderController {

    @Autowired
    private ICourseOrderService courseOrderService;

    @RequestMapping("/list")
    public String list(){
        return "courseorder/list";
    }

    @RequestMapping("/listJson")
    @ResponseBody
    public PageResult<CourseOrder> listJson(){
        PageResult<CourseOrder> result = courseOrderService.findPageResult(null,1,8);

        return result;
    }
}
