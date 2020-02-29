package com.xb.crm.service;

import com.xb.crm.model.CourseOrder;
import com.xb.crm.model.PageResult;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/21 20:40
 * @history: 1.2020/2/21 created by xiongbiao
 */

public interface ICourseOrderService {

    public PageResult<CourseOrder> findPageResult(CourseOrder condition,int page,int pageSize);

    /**
     * 保存订单
     * @param order
     */
    public void save(CourseOrder order);

    public CourseOrder findByOrderId(String order_id);

    public void deleteByOrderId(String order_id);

    public void updateOrder(CourseOrder order);
}
