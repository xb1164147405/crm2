package com.xb.crm.service.impl;

import com.xb.crm.mapper.UserInfoMapper;
import com.xb.crm.model.HeadPhoto;
import com.xb.crm.model.Result;
import com.xb.crm.model.User;
import com.xb.crm.service.IUserInfoService;
import com.xb.crm.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/4/1 11:03
 * @history: 1.2020/4/1 created by xiongbiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Result saveFilePath(String newName, String userId) {
        //先查询该用户是否已上传过头像 有则先删除再保存
        HeadPhoto headPhoto = userInfoMapper.findHeadPhotoByUserId(Integer.valueOf(userId));
        if (Objects.isNull(headPhoto)){
            //headPhoto为空则做插入
            try {
                userInfoMapper.insertHeadPhoto(newName,Integer.valueOf(userId));
            } catch (NumberFormatException e) {
                return ResultUtil.error("头像信息插入数据库错误：" + e);
            }
        }else{
            //不为空则先删除后插入
            try {
                userInfoMapper.deleteHeadPhotoByUserId(Integer.valueOf(userId));
                userInfoMapper.insertHeadPhoto(newName,Integer.valueOf(userId));
            } catch (NumberFormatException e) {
               return ResultUtil.error("头像信息插入数据库错误：" + e);
            }
        }
        return ResultUtil.success("头像信息插入数据成功！");
    }

    @Override
    public HeadPhoto findHeadPhotoByUserId(int userId) {
        return userInfoMapper.findHeadPhotoByUserId(userId);
    }

    @Override
    public Result updatePasswordByUserId(User user) {
        Result result = new Result();
        try {
            userInfoMapper.updatePasswordByUserId(user);
        } catch (Exception e) {
            return ResultUtil.error(e.toString(),null);
        }
        return ResultUtil.success();
    }
}
