package com.xb.crm.model;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/4/15 21:19
 * @history: 1.2020/4/15 created by xiongbiao
 */

public class HobbyData {
    /**
     * 爱好
     */
    private String hobby;
    /**
     * 数量
     */
    private String total;

    @Override
    public String toString() {
        return "HobbyData{" +
                "hobby='" + hobby + '\'' +
                ", total='" + total + '\'' +
                '}';
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
