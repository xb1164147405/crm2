package com.xb.crm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: <p>开发接口请求参数对象</p>
 * @author: xiongbiao
 * @since: 2020/4/27 8:52
 * @history: 1.2020/4/27 created by xiongbiao
 */

public class CommonBeanRequest  implements Serializable {
    private static final long serialVersionUID = -8515073816483097906L;

    /**
     * 客户数据
     */

    private String name;
    private int age;
    private String wechat_name;
    private String wechat_no;
    private String qq_no;
    private String tel;
    private String address;
    private String hobby;
    private String occupation;
    /**
     * 订单数据
     */
    private String wechat_mark;
    private String course_name;
    private String course_price;
    private String order_date;
    private Date update_datetime;
    private String remark;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWechat_name() {
        return wechat_name;
    }

    public void setWechat_name(String wechat_name) {
        this.wechat_name = wechat_name;
    }

    public String getWechat_no() {
        return wechat_no;
    }

    public void setWechat_no(String wechat_no) {
        this.wechat_no = wechat_no;
    }

    public String getQq_no() {
        return qq_no;
    }

    public void setQq_no(String qq_no) {
        this.qq_no = qq_no;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getWechat_mark() {
        return wechat_mark;
    }

    public void setWechat_mark(String wechat_mark) {
        this.wechat_mark = wechat_mark;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_price() {
        return course_price;
    }

    public void setCourse_price(String course_price) {
        this.course_price = course_price;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public Date getUpdate_datetime() {
        return update_datetime;
    }

    public void setUpdate_datetime(Date update_datetime) {
        this.update_datetime = update_datetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CommonBeanRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", wechat_name='" + wechat_name + '\'' +
                ", wechat_no='" + wechat_no + '\'' +
                ", qq_no='" + qq_no + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                ", occupation='" + occupation + '\'' +
                ", wechat_mark='" + wechat_mark + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_price='" + course_price + '\'' +
                ", order_date='" + order_date + '\'' +
                ", update_datetime=" + update_datetime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
