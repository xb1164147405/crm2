package com.xb.crm.model;

/**
 * @Description:增删改查返回结果对象
 * @author: xiongbiao
 * @since: 2020/2/22 17:18
 * @history: 1.2020/2/22 created by xiongbiao
 */

public class CURDResult {

    /**
     * //默认表示成功
     */
    private int success = 1;

    private String msg = "";

    public CURDResult() {
    }

    public CURDResult(int success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
