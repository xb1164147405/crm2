package com.xb.crm.web.controller;

import com.xb.crm.model.Result;
import com.xb.crm.model.Role;
import com.xb.crm.model.User;
import com.xb.crm.service.IUserInfoService;
import com.xb.crm.service.IUserService;
import com.xb.crm.util.ResultUtil;
import com.xb.crm.util.UUIDUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/25 18:55
 * @history: 1.2020/3/25 created by xiongbiao
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/listInfo")
    public String listInfo(@Param("username") String username, Model model){
        User user = userService.findUserByUsername(username);
        //包含角色对象
        User userAndRoles = userService.findUserAndRolesByUserId(user.getId());
        List<Role> roles = userAndRoles.getRoles();
        if (!Objects.isNull(roles) && !roles.isEmpty()){
            StringBuffer sb = new StringBuffer();
            for (Role role : roles){
                sb.append(role.getName()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            model.addAttribute("roles",sb.toString());
        }else {
            model.addAttribute("roles","普通用户");
        }
        model.addAttribute("user",user);
        return "userinfo/edit";

    }

    @RequestMapping(value = "/uploadHead", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result uploadImg(
            @RequestParam(value = "file") MultipartFile[] files,  //这样接收文件
            String userId,
            HttpServletRequest request
    ) {
        //TODO 服务器路径的实现
//        String path = "D:\\graduationProject\\springbootProject\\crm\\src\\main\\resources\\static\\upload";
        //获取Classes目录绝对路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/upload";
        System.out.println(path);
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            //分割原文件名后缀
            String[] suffixArr = fileName.split("[.]");
            String newName = UUIDUtil.getUUID() + "." +suffixArr[1];
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            if(file.isEmpty()){
                return ResultUtil.error("文件已存在！",null);
            }else{
                String savePath = path + "/" + newName;
                File dest = new File(savePath);
                //判断文件父目录是否存在
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    LOG.error("头像上传出现异常：{}", e);
                    return ResultUtil.error("userId:" + userId + "头像上传出现异常："+e,null);
                }
            }
            Result result = userInfoService.saveFilePath(newName,userId);
            if (Objects.equals(result.getCode(),ResultUtil.ERROR)){
                return ResultUtil.error("userId:" + userId + "。头像插入失败",result);
            }
        }
        return ResultUtil.success("上传成功。");
    }
}
