package com.xb.crm.service;

import com.xb.crm.model.CourseCategory;
import com.xb.crm.model.Result;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/1 16:00
 * @history: 1.2020/3/1 created by xiongbiao
 */

public interface ICourseCategoryService {

    public List<CourseCategory> findAllCourseCategory();

    public CourseCategory findPriceByCourseName(String courseName);

    public Result saveCourseCategory(CourseCategory courseCategory);

    public CourseCategory findCourseCategoryByGoodId(Integer goodsId);

    public Result editPhotoById(Integer id, String photoName);

    public Result editGoodsInfo(CourseCategory courseCategory);

    public Result deleteGoodsById(int id);
}
