package com.xb.crm.service;

import com.xb.crm.model.MonthIncome;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/29 16:44
 * @history: 1.2020/2/29 created by xiongbiao
 */

public interface IDataAnalysisService {

    public List<MonthIncome> getMonthIncomes();
}
