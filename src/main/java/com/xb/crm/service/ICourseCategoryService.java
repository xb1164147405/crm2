package com.xb.crm.service;

import com.xb.crm.model.CourseCategory;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/1 16:00
 * @history: 1.2020/3/1 created by xiongbiao
 */

public interface ICourseCategoryService {
    //TODO 课程类型的维护页面CRUD
    public List<CourseCategory> findAllCourseCategory();
}
