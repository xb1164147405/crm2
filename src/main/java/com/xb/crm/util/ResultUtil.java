package com.xb.crm.util;

import com.xb.crm.model.Result;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/31 20:36
 * @history: 1.2020/3/31 created by xiongbiao
 */

public class ResultUtil {
    public static final String SUCCESS = "200";
    public static final String ERROR = "500";
    public static final String SUCCESS_MESSAGE = "成功";
    public static final String ERROR_MESSAGE = "错误";

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setMessage(SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public static Result error(String msg, Object data) {
        return error(ERROR, msg, data);
    }

    public static Result error(Object data) {
        return error(ERROR, ERROR_MESSAGE, data);
    }

    public static Result error() {
        return error(ERROR, ERROR_MESSAGE, null);
    }
}