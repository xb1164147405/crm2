package com.xb.crm.service.impl;

import com.xb.crm.mapper.CourseOrderMapper;
import com.xb.crm.model.CourseOrder;
import com.xb.crm.model.PageResult;
import com.xb.crm.service.ICourseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CourseOrderServiceImpl implements ICourseOrderService {

    @Autowired
    private CourseOrderMapper courseOrderMapper;

    @Override
    public PageResult<CourseOrder> findPageResult(CourseOrder condition, int page, int pageSize) {
        PageResult<CourseOrder> result = new PageResult<>();
        result.setCode(0);
        Map<String,Object> params = new HashMap<>();
        //select * from t_course_order limit 0,10;
        params.put("start",(page-1) * pageSize);
        params.put("pageSize",pageSize);
        //获取总记录数据
        int totalCount = courseOrderMapper.findCountByMap(params);
        result.setCount(totalCount);
        //获取查询的数据
        List<CourseOrder> list = courseOrderMapper.findListByMap(params);
        result.setData(list);
        return result;
    }

    @Override
    public void save(CourseOrder order) {
        courseOrderMapper.insertOrder(order);
    }

    @Override
    public CourseOrder findByOrderId(String order_id) {
        return courseOrderMapper.findByOrderId(order_id);
    }

    @Override
    public void deleteByOrderId(String order_id) {
        courseOrderMapper.deleteByOrderId(order_id);
    }
}
