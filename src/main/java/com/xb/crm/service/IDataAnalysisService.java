package com.xb.crm.service;

import com.xb.crm.model.HobbyData;
import com.xb.crm.model.MapModel;
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

    public List<HobbyData> getHobbyData();

    /**
     * 获取地图分布数据
     * @return
     */
    public List<MapModel> getMapData();
}
