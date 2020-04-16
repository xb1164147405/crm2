package com.xb.crm.service;

import com.xb.crm.model.Customer;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/4/15 11:44
 * @history: 1.2020/4/15 created by xiongbiao
 */

public interface IStuInfoService {
    public List<Customer> findAllCustomer();
}
