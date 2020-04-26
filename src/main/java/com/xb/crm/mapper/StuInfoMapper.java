package com.xb.crm.mapper;


import com.xb.crm.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:02
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Repository
public interface StuInfoMapper {


    public List<Customer> findAllCustomer();

    public Long findStuInfoTotal();
}
