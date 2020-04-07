package com.xb.crm.mapper;

import com.xb.crm.model.HeadPhoto;
import com.xb.crm.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 10:02
 * @history: 1.2020/3/6 created by xiongbiao
 */
@Repository
public interface UserInfoMapper {


    public HeadPhoto findHeadPhotoByUserId(Integer userId);

    public void insertHeadPhoto(String newName, Integer userId);

    public void deleteHeadPhotoByUserId(Integer userId);

    public void updatePasswordByUserId(User user);
}
