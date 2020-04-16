package com.xb.crm.service.impl;

import com.xb.crm.mapper.StuInfoMapper;
import com.xb.crm.model.Customer;
import com.xb.crm.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StuInfoServiceImpl implements IStuInfoService {

    @Autowired
    private StuInfoMapper stuInfoMapper;

    @Override
    public List<Customer> findAllCustomer() {
        return stuInfoMapper.findAllCustomer();
    }
}
