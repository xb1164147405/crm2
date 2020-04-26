package com.xb.crm.mapper;

import com.xb.crm.model.HobbyData;
import com.xb.crm.model.MapModel;
import com.xb.crm.model.MonthIncome;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/29 16:46
 * @history: 1.2020/2/29 created by xiongbiao
 */
@Repository
public interface DataAnalysisMapper {

    /**
     * 获取收入与时间数据
     * @return
     */
    public List<MonthIncome> getMonthIncomes();

    /**
     * 获取爱好统计数据
     * @return
     */
    public List<HobbyData> getHobbyData();

    /**
     * 获取地图分布数据
     * @return
     */
    public List<MapModel> getMapData();
}
