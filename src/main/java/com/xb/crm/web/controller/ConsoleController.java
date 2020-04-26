package com.xb.crm.web.controller;

import com.xb.crm.model.MapModel;
import com.xb.crm.service.ICourseOrderService;
import com.xb.crm.service.IDataAnalysisService;
import com.xb.crm.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/4/26 8:52
 * @history: 1.2020/4/26 created by xiongbiao
 */
@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private ICourseOrderService courseOrderService;

    @Autowired
    private IStuInfoService stuInfoService;

    @Autowired
    private IDataAnalysisService dataAnalysisService;

    @RequestMapping("/list")
    public String list(Model model){
        //获取订单总量
        Long courseOrderTotal = courseOrderService.findCourseOrderTotal();
        model.addAttribute("courseOrderTotal",courseOrderTotal);

        //获取客户总数
        Long stuInfoTotal = stuInfoService.findStuInfoTotal();
        model.addAttribute("stuInfoTotal",stuInfoTotal);

        //销售总额
        Long totalSales = courseOrderService.findTotalSales();
        model.addAttribute("totalSales",totalSales);

        //最近一月销售额度
        Long monthTotalSales = courseOrderService.findMonthTotalSales();
        if (monthTotalSales == null){
            model.addAttribute("monthTotalSales",0);
        }else {
            model.addAttribute("monthTotalSales",monthTotalSales);
        }
        return "dataanalysis/consoleList";
    }

    /**
     * 获取省份分布数据
     * @return
     */
    @PostMapping("/getMapData")
    @ResponseBody
    public List<MapModel> getMapData(){
        List<MapModel> mapModelList = dataAnalysisService.getMapData();
        for (MapModel mapModel : mapModelList){
            if (Objects.equals("黑龙",mapModel.getName())){
                mapModel.setName("黑龙江");
            }
            if (Objects.equals("内蒙",mapModel.getName())){
                mapModel.setName("内蒙古");
            }
            if (Objects.equals("南海",mapModel.getName())){
                mapModel.setName("南海诸岛");
            }
        }
        return mapModelList;
    }
}
