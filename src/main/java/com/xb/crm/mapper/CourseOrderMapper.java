package com.xb.crm.mapper;

import com.xb.crm.model.CourseOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/21 19:46
 * @history: 1.2020/2/21 created by xiongbiao
 */
@Repository
public interface CourseOrderMapper {

    public int findCountByMap(Map<String,Object> map);

    public List<CourseOrder> findListByMap(Map<String,Object> map);

    public void insertOrder(CourseOrder order);

    public CourseOrder findByOrderId(String order_id);

    public void deleteByOrderId(String order_id);

    public void updateOrder(CourseOrder order);
}
