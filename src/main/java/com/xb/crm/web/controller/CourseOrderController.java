package com.xb.crm.web.controller;

import com.xb.crm.model.CURDResult;
import com.xb.crm.model.CourseOrder;
import com.xb.crm.model.PageResult;
import com.xb.crm.service.ICourseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/add")
    public String add(){
        return "courseorder/add";
    }


    @RequestMapping("/detail")
    public String detail(Model model, String order_id){
        CourseOrder order = courseOrderService.findByOrderId(order_id);
        model.addAttribute("order",order);
        return "courseorder/detail";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CURDResult detail(String order_id){
        CURDResult result = new CURDResult();
        try {
            courseOrderService.deleteByOrderId(order_id);
        } catch (Exception e) {
            result.setSuccess(0);
            result.setMsg(e.toString());
        }
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public CURDResult save(CourseOrder order){
        CURDResult result = new CURDResult();
        try {
            courseOrderService.save(order);
        } catch (Exception e) {
            result.setSuccess(0);
            result.setMsg("数据插入失败" + e);
        }
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
    public PageResult<CourseOrder> listJson(int page,int limit){
        PageResult<CourseOrder> result = courseOrderService.findPageResult(null,page,limit);

        return result;
    }
}
