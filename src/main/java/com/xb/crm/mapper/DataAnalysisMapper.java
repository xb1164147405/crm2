package com.xb.crm.mapper;

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

    public List<MonthIncome> getMonthIncomes();
}
