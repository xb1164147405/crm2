package com.xb.crm.service.impl;

import com.xb.crm.mapper.CourseOrderMapper;
import com.xb.crm.model.CourseOrder;
import com.xb.crm.model.PageResult;
import com.xb.crm.service.ICourseOrderService;
import com.xb.crm.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/21 20:42
 * @history: 1.2020/2/21 created by xiongbiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseOrderServiceImpl implements ICourseOrderService {

    private final static Logger LOG = LoggerFactory.getLogger(CourseOrderServiceImpl.class);

    @Autowired
    private CourseOrderMapper courseOrderMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public PageResult<CourseOrder> findPageResult(CourseOrder condition, int page, int pageSize) {
        PageResult<CourseOrder> result = new PageResult<>();
        result.setCode(0);
        Map<String,Object> params = new HashMap<>();
        //模糊查询条件添加
        params.put("condition",condition);
        //select * from t_course_order limit 0,10;
        params.put("start",(page-1) * pageSize);
        params.put("pageSize",pageSize);
        //获取总记录数据
        int totalCount = courseOrderMapper.findCountByMap(params);
        //获取查询的数据
        List<CourseOrder> list = courseOrderMapper.findListByMap(params);
        result.setCount(totalCount);
        result.setData(list);
        return result;
    }

    @Override
    public void save(CourseOrder order) {
        courseOrderMapper.insertOrder(order);
        LOG.info("======订单数据保存成功并删除相关缓存======");
        redisUtil.del("getMonthIncomes");
    }

    @Override
    public CourseOrder findByOrderId(String order_id) {
        return courseOrderMapper.findByOrderId(order_id);
    }

    @Override
    public void deleteByOrderId(String order_id) {
        courseOrderMapper.deleteByOrderId(order_id);
        LOG.info("======订单数据删除成功并删除相关缓存======");
        redisUtil.del("getMonthIncomes");
    }

    @Override
    public void updateOrder(CourseOrder order) {
        courseOrderMapper.updateOrder(order);
        LOG.info("======订单数据更新成功并删除相关缓存======");
        redisUtil.del("getMonthIncomes");
    }

    /**
     * 获取订单总量
     * @return
     */
    @Override
    public Long findCourseOrderTotal() {
        return courseOrderMapper.findCourseOrderTotal();
    }

    /**
     * 获取总销售额
     * @return
     */
    @Override
    public Long findTotalSales() {
        return courseOrderMapper.findTotalSales();
    }

    /**
     * 获取最近一月的销售额度
     * @return
     */
    @Override
    public Long findMonthTotalSales() {
        return courseOrderMapper.findMonthTotalSales();
    }
}
