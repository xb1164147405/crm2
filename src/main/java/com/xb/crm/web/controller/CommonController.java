package com.xb.crm.web.controller;

import com.xb.crm.model.CommonBeanRequest;
import com.xb.crm.model.CourseOrder;
import com.xb.crm.model.Customer;
import com.xb.crm.service.ICourseOrderService;
import com.xb.crm.service.IStuInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: <p>公共开放接口不做拦截</p>
 * @author: xiongbiao
 * @since: 2020/4/26 22:23
 * @history: 1.2020/4/26 created by xiongbiao
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ICourseOrderService courseOrderService;

    @Autowired
    private IStuInfoService stuInfoService;

    @RequestMapping("/addOrder")
    @ResponseBody
    public String addOrder(@RequestBody CommonBeanRequest commonBeanRequest){
        CourseOrder courseOrder = new CourseOrder();
        BeanUtils.copyProperties(commonBeanRequest,courseOrder);
        courseOrderService.save(courseOrder);
        Customer customer = new Customer();
        BeanUtils.copyProperties(commonBeanRequest,customer);
        stuInfoService.insert(customer);
        return null;
    }

}
