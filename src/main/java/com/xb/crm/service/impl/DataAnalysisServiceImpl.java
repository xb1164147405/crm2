package com.xb.crm.service.impl;

import com.alibaba.fastjson.JSON;
import com.xb.crm.mapper.DataAnalysisMapper;
import com.xb.crm.model.HobbyData;
import com.xb.crm.model.MapModel;
import com.xb.crm.model.MonthIncome;
import com.xb.crm.service.IDataAnalysisService;
import com.xb.crm.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final static Logger LOG = LoggerFactory.getLogger(DataAnalysisServiceImpl.class);
    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 缓存时间（秒）
     */
    @Value(value = "${spring.redis.cache.time}")
    private long CACHE_TIME;

    @Override
    public List<MonthIncome> getMonthIncomes() {
        boolean hasKey = redisUtil.hasKey("getMonthIncomes");
        if (hasKey){
            LOG.info("======从redis缓存中读取月收入数据======");
            //缓存的删除由订单实现类的增删改方法控制
            List<MonthIncome> monthIncomeList = redisUtil.getListObj("getMonthIncomes",MonthIncome.class);
            return monthIncomeList;
        }
        LOG.info("======从数据库中获取月收入数据======");
        List<MonthIncome> monthIncomeList = dataAnalysisMapper.getMonthIncomes();
        redisUtil.set("getMonthIncomes", JSON.toJSONString(monthIncomeList),CACHE_TIME);
        return monthIncomeList;
    }

    @Override
    public List<HobbyData> getHobbyData() {
        boolean hasKey = redisUtil.hasKey("getHobbyData");
        if (hasKey){
            LOG.info("======从redis缓存中读取爱好统计数据======");
            //缓存的删除由订单实现类的增删改方法控制
            List<HobbyData> hobbyDataList = redisUtil.getListObj("getHobbyData",HobbyData.class);
            return hobbyDataList;
        }
        LOG.info("======从数据库中获取爱好统计数据======");
        List<HobbyData> hobbyDataList = dataAnalysisMapper.getHobbyData();
        redisUtil.set("getHobbyData", JSON.toJSONString(hobbyDataList),CACHE_TIME);
        return hobbyDataList;
    }

    @Override
    public List<MapModel> getMapData() {
        return dataAnalysisMapper.getMapData();
    }
}
