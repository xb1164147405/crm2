package com.xb.crm.service.impl;

import com.xb.crm.mapper.CourseCategoryMapper;
import com.xb.crm.model.CourseCategory;
import com.xb.crm.model.Result;
import com.xb.crm.service.ICourseCategoryService;
import com.xb.crm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    @Override
    public CourseCategory findPriceByCourseName(String courseName) {
        return courseCategoryMapper.findPriceByCourseName(courseName);
    }

    @Override
    public Result saveCourseCategory(CourseCategory courseCategory) {
            //插入操作
            String validStr = courseCategory.valid();
            if (validStr != null){
                return ResultUtil.error(validStr);
            }
            try {
                //验证是否已存在该商品
                CourseCategory isExist = courseCategoryMapper.findCourseCategoryByName(courseCategory.getCourse_name());
                if (!Objects.isNull(isExist)){
                    return ResultUtil.error("该商品名称已存在！");
                }
                courseCategoryMapper.insertCourseCategory(courseCategory);
            } catch (Exception e) {
                return ResultUtil.error(e);
            }

        return ResultUtil.success("商品信息插入成功！");
    }

    @Override
    public CourseCategory findCourseCategoryByGoodId(Integer goodsId) {
        return courseCategoryMapper.findCourseCategoryByGoodId(goodsId);
    }

    @Override
    public Result editPhotoById(Integer id, String photoName) {
        try {
            courseCategoryMapper.editPhotoById(id,photoName);
        } catch (Exception e) {
            return ResultUtil.error(e);
        }
        return ResultUtil.success();
    }

    @Override
    public Result editGoodsInfo(CourseCategory courseCategory) {
        if (Objects.isNull(courseCategory)){
            return ResultUtil.error("商品对象为空。");
        }
        //更新操作
        String validStr = courseCategory.valid();
        if (validStr != null){
            return ResultUtil.error(validStr);
        }
        //验证是否已存在该商品
        try {
            CourseCategory isExist = courseCategoryMapper.findCourseCategoryByName(courseCategory.getCourse_name());
            if (!Objects.isNull(isExist)){
                return ResultUtil.error("该商品名称已存在！");
            }
            courseCategoryMapper.updateCourseCategory(courseCategory);
        } catch (Exception e) {
            return ResultUtil.error(e);
        }
        return ResultUtil.success();
    }

    @Override
    public Result deleteGoodsById(int id) {
        try {
            courseCategoryMapper.deleteGoodsById(id);
        } catch (Exception e) {
            return ResultUtil.error(e);
        }
        return ResultUtil.success();
    }
}
