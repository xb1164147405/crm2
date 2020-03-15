package com.xb.crm.service.impl;

import com.xb.crm.mapper.CourseCategoryMapper;
import com.xb.crm.model.CourseCategory;
import com.xb.crm.service.ICourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/1 16:01
 * @history: 1.2020/3/1 created by xiongbiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseCategoryServiceImpl implements ICourseCategoryService {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategory> findAllCourseCategory() {
        return courseCategoryMapper.findAllCourseCategory();
    }
}
