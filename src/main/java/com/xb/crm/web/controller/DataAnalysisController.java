package com.xb.crm.web.controller;

import com.xb.crm.model.MonthIncome;
import com.xb.crm.service.IDataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: <p>收入分析控制器</p>
 * @author: xiongbiao
 * @since: 2020/2/29 11:09
 * @history: 1.2020/2/29 created by xiongbiao
 */
@Controller
@RequestMapping("/orderreportform")
public class DataAnalysisController {

    @Autowired
    private IDataAnalysisService dataAnalysisService;

    @RequestMapping("/list")
    public String income(){
        return "dataanalysis/income";
    }

    @RequestMapping("/monthIncome")
    @ResponseBody
    public List<MonthIncome> monthIncomes(){
        List<MonthIncome> list = dataAnalysisService.getMonthIncomes();

        return list;

    }

}
