package com.xb.crm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description: <p>图片上传工具类</p>
 * @author: xiongbiao
 * @since: 2020/4/9 16:30
 * @history: 1.2020/4/9 created by xiongbiao
 */

public class PhotoUploadUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PhotoUploadUtil.class);
    /**
     *
     * @param file 文件对象
     * @param savePath 保存路径
     */
    public static void uploadFile(MultipartFile file,String savePath){
        File dest = new File(savePath);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
        }catch (Exception e) {
            LOG.error("图片保存出现异常：{}",e);
        }
    }
}
