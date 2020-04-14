package com.xb.crm.web.controller;


import com.xb.crm.model.CourseCategory;
import com.xb.crm.model.Result;
import com.xb.crm.service.ICourseCategoryService;
import com.xb.crm.util.PhotoUploadUtil;
import com.xb.crm.util.ResultUtil;
import com.xb.crm.util.UUIDUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/11 15:47
 * @history: 1.2020/3/11 created by xiongbiao
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private ICourseCategoryService courseCategoryService;

    @RequestMapping("/list")
    public String list(Model model){
        List<CourseCategory> allCourseCategory = courseCategoryService.findAllCourseCategory();
        model.addAttribute("allCourseCategory",allCourseCategory);
        return "goods/list";
    }

    @RequestMapping("/findPrice")
    @ResponseBody
    public Result findPrice(String courseName){
        CourseCategory courseCategory = courseCategoryService.findPriceByCourseName(courseName);
        if (Objects.isNull(courseCategory)){
            return ResultUtil.error("课程对象为空");
        }
        return ResultUtil.success(courseCategory.getPrice());
    }

    @RequestMapping("/add")
    public String add(){
        return "goods/add";
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestParam(value = "file") MultipartFile multipartFile,
                       HttpServletRequest request,
                       CourseCategory courseCategory){
        if (!Objects.isNull(multipartFile)){
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/upload";
            String fileName = multipartFile.getOriginalFilename();
            //分割原文件名后缀
            String[] suffixArr = fileName.split("[.]");
            String newName = UUIDUtil.getUUID() + "." +suffixArr[1];
            int size = (int) multipartFile.getSize();
            System.out.println(fileName + "-->" + size);
            if (multipartFile.isEmpty() && courseCategory.getId() == 0){
                return ResultUtil.error("上传文件为空!");
            }
            String savePath = path + "/" + newName;
            try {
                PhotoUploadUtil.uploadFile(multipartFile,savePath);
            } catch (Exception e) {
                LOG.error("图片上传出现异常：{}",e);
                return ResultUtil.error("图片上传出现异常:"+e);
            }
            courseCategory.setPhoto_name(newName);
        }

        Result result;
        result = courseCategoryService.saveCourseCategory(courseCategory);
        if (!Objects.equals(ResultUtil.SUCCESS,result.getCode())){
            return ResultUtil.error(result.getData());
        }
        return ResultUtil.success("商品信息插入或修改成功！");
    }

    @GetMapping("/edit")
    public String edit(@Param("id") String id,Model model){
        CourseCategory courseCategory = courseCategoryService.findCourseCategoryByGoodId(Integer.valueOf(id));
        model.addAttribute("courseCategory",courseCategory);
        return "goods/edit";
    }


    @GetMapping("/editPhoto")
    public String editPhoto(@Param("id") String id,Model model){
//        CourseCategory courseCategory = courseCategoryService.findCourseCategoryByGoodId(Integer.valueOf(id));
        model.addAttribute("id",id);
        return "goods/editPhoto";
    }

    @PostMapping("/editGoodsInfo")
    @ResponseBody
    public Result editGoodsInfo(CourseCategory courseCategory){
        Result result;
        result = courseCategoryService.editGoodsInfo(courseCategory);
        if (!Objects.equals(ResultUtil.SUCCESS,result.getCode())){
            return ResultUtil.error(result.getData());
        }
        return ResultUtil.success("商品信息插入或修改成功！");
    }

    @PostMapping("/savePhoto")
    @ResponseBody
    public Result savePhoto(@RequestParam(value = "file") MultipartFile multipartFile,
                            CourseCategory courseCategory){
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/upload";
        String fileName = multipartFile.getOriginalFilename();
        //分割原文件名后缀
        String[] suffixArr = fileName.split("[.]");
        String newName = UUIDUtil.getUUID() + "." +suffixArr[1];
        int size = (int) multipartFile.getSize();
        System.out.println(fileName + "-->" + size);
        if (multipartFile.isEmpty()){
            return ResultUtil.error("上传文件为空!");
        }
        String savePath = path + "/" + newName;
        try {
            PhotoUploadUtil.uploadFile(multipartFile,savePath);
        } catch (Exception e) {
            LOG.error("图片上传出现异常：{}",e);
            return ResultUtil.error("图片上传出现异常:"+e);
        }
        Result result;
        result = courseCategoryService.editPhotoById(Integer.valueOf(courseCategory.getId()),newName);
        if (!Objects.equals(ResultUtil.SUCCESS,result.getCode())){
            return ResultUtil.error(result.getData());
        }
        return ResultUtil.success();
    }

    @PostMapping("/deleteGoods")
    @ResponseBody
    public Result deleteGoods(int id){
        System.out.println("id:"+id);
        Result result = null;
        try {
            result = courseCategoryService.deleteGoodsById(id);
        } catch (Exception e) {
            return ResultUtil.error(result.getData());
        }
        return ResultUtil.success();
    }

//    @RequestMapping("/listJson")
//    @ResponseBody
//    public Result listJson(Model model){
//        List<CourseCategory> allCourseCategory = courseCategoryService.findAllCourseCategory();
//        if (Objects.isNull(allCourseCategory) || allCourseCategory.isEmpty()){
//            return ResultUtil.error("无商品！");
//        }
//        model.addAttribute("allCourseCategory",allCourseCategory);
//        return ResultUtil.success();
//    }
}
