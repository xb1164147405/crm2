package com.xb.crm.service.impl;

import com.alibaba.fastjson.JSON;
import com.xb.crm.mapper.CourseCategoryMapper;
import com.xb.crm.model.CourseCategory;
import com.xb.crm.model.Result;
import com.xb.crm.service.ICourseCategoryService;
import com.xb.crm.util.RedisUtil;
import com.xb.crm.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final static Logger LOG = LoggerFactory.getLogger(CourseCategoryServiceImpl.class);

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 缓存时间（秒）
     */
    @Value(value = "${spring.redis.cache.time}")
    private long CACHE_TIME;


    @Override
    public List<CourseCategory> findAllCourseCategory() {
        boolean hasKey = redisUtil.hasKey("findAllCourseCategory");
        if (hasKey){
            LOG.info("======从redis缓存中读取课程商品数据======");
            List<CourseCategory> courseCategoryList = redisUtil.getListObj("findAllCourseCategory",CourseCategory.class);
            return courseCategoryList;
        }
        LOG.info("======从数据库中获取课程商品数据======");
        List<CourseCategory> courseCategoryList = courseCategoryMapper.findAllCourseCategory();
        redisUtil.set("findAllCourseCategory", JSON.toJSONString(courseCategoryList),CACHE_TIME);
        return courseCategoryList;
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
                redisUtil.del("findAllCourseCategory");
                LOG.info("======课程商品数据插入成功并删除相关缓存======");
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
            redisUtil.del("findAllCourseCategory");
            LOG.info("======课程商品图片数据修改成功并删除相关缓存======");
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
            redisUtil.del("findAllCourseCategory");
            LOG.info("======课程商品数据修改成功并删除相关缓存======");
        } catch (Exception e) {
            return ResultUtil.error(e);
        }
        return ResultUtil.success();
    }

    @Override
    public Result deleteGoodsById(int id) {
        try {
            courseCategoryMapper.deleteGoodsById(id);
            redisUtil.del("findAllCourseCategory");
            LOG.info("======课程商品数据删除成功并删除相关缓存======");
        } catch (Exception e) {
            return ResultUtil.error(e);
        }
        return ResultUtil.success();
    }
}
