package com.xb.crm.util;

import java.util.UUID;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/30 22:12
 * @history: 1.2020/3/30 created by xiongbiao
 */

public class UUIDUtil {
    /**
     * 生成UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString()
                .replaceAll("-","")
                .toUpperCase();
    }
}
