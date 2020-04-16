package com.xb.crm.service.impl;

import com.xb.crm.mapper.DataAnalysisMapper;
import com.xb.crm.model.HobbyData;
import com.xb.crm.model.MonthIncome;
import com.xb.crm.service.IDataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/29 16:45
 * @history: 1.2020/2/29 created by xiongbiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataAnalysisServiceImpl implements IDataAnalysisService {

    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @Override
    public List<MonthIncome> getMonthIncomes() {
        return dataAnalysisMapper.getMonthIncomes();
    }

    @Override
    public List<HobbyData> getHobbyData() {
        return dataAnalysisMapper.getHobbyData();
    }
}
