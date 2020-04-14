package com.xb.crm.mapper;

import com.xb.crm.model.CourseCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: <p>课程分类</p>
 * @author: xiongbiao
 * @since: 2020/3/1 15:53
 * @history: 1.2020/3/1 created by xiongbiao
 */
@Repository
public interface CourseCategoryMapper {

    /**
     * 查找所有的课程类别
     * @return
     */
    public List<CourseCategory> findAllCourseCategory();

    public CourseCategory findPriceByCourseName(String courseName);

    public void insertCourseCategory(CourseCategory courseCategory);

    public CourseCategory findCourseCategoryByName(String courseName);

    public CourseCategory findCourseCategoryByGoodId(Integer goodsId);

    public void updateCourseCategory(CourseCategory courseCategory);

    public void editPhotoById(Integer id, String photoName);

    public void deleteGoodsById(int id);
}
