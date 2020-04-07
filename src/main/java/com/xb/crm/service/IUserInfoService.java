package com.xb.crm.service;

import com.xb.crm.model.HeadPhoto;
import com.xb.crm.model.Result;
import com.xb.crm.model.User;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/4/1 11:02
 * @history: 1.2020/4/1 created by xiongbiao
 */

public interface IUserInfoService {

    public Result saveFilePath(String newName, String userId);

    public HeadPhoto findHeadPhotoByUserId(int userId);

    public Result updatePasswordByUserId(User user);
}
