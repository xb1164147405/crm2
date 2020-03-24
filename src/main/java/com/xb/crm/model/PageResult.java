package com.xb.crm.model;

import java.util.List;

/**
 * @Description:返回数据表格封装类
 * @author: xiongbiao
 * @since: 2020/2/21 16:29
 * @history: 1.2020/2/21 created by xiongbiao
 */

public class PageResult<T> {

    /**
     * 0 success
     * 1 failure
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 总记录数
     */
    private int count;

    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
