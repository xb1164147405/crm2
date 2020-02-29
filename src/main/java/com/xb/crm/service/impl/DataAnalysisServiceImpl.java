package com.xb.crm.service.impl;

import com.xb.crm.mapper.DataAnalysisMapper;
import com.xb.crm.model.MonthIncome;
import com.xb.crm.service.IDataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/29 16:45
 * @history: 1.2020/2/29 created by xiongbiao
 */
@Service
public class DataAnalysisServiceImpl implements IDataAnalysisService {

    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @Override
    public List<MonthIncome> getMonthIncomes() {
        return dataAnalysisMapper.getMonthIncomes();
    }
}
