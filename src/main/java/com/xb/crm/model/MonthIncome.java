package com.xb.crm.model;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/2/29 16:34
 * @history: 1.2020/2/29 created by xiongbiao
 */

public class MonthIncome {

    /**
     * 月份
     */
    private String date;

    /**
     * 每月总订单数
     */
    private String total;

    /**
     * 每月总收入
     */
    private String income;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "MonthIncome{" +
                "date='" + date + '\'' +
                ", total='" + total + '\'' +
                ", income='" + income + '\'' +
                '}';
    }
}
