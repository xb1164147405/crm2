package com.xb.crm.model;

/**
 * @Description: <p>客户实体类</p>
 * @author: xiongbiao
 * @since: 2020/4/14 21:05
 * @history: 1.2020/4/14 created by xiongbiao
 */

public class Customer {

    private int id;
    private String name;
    private int age;
    private String wechat_name;
    private String wechat_no;
    private String qq_no;
    private String tel;
    private String address;
    private String hobby;
    private String occupation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", wechat_name='" + wechat_name + '\'' +
                ", wechat_no='" + wechat_no + '\'' +
                ", qq_no='" + qq_no + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
