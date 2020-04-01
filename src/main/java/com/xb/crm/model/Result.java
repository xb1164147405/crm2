package com.xb.crm.model;

import java.io.Serializable;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/31 20:36
 * @history: 1.2020/3/31 created by xiongbiao
 */

public class Result<T> implements Serializable {
    /**
     * 返回状态,success:成功 error:错误
     */
    private String code = "success";

    /**
     * 返回消息,错误时必须有值
     */
    private String message;

    public Result(){

    }

    public Result(String code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回数据
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String status) {
        this.code = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
