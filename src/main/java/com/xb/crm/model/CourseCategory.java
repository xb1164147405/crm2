package com.xb.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.StringUtils;



/**
 * @Description: <P>课程分类模型</P>
 * @author: xiongbiao
 * @since: 2020/3/1 15:42
 * @history: 1.2020/3/1 created by xiongbiao
 */

public class CourseCategory {

    private int id;
    private String course_name;
//    @DateTimeFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String publish_datetime;
    private int status;
    private double price;
    private String description;
    private String course_author;
    private String photo_name;
    /**
     * 库存量
     */
    private int inventory;

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getPublish_datetime() {
        return publish_datetime;
    }

    public void setPublish_datetime(String publish_datetime) {
        this.publish_datetime = publish_datetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCourse_author() {
        return course_author;
    }

    public void setCourse_author(String course_author) {
        this.course_author = course_author;
    }

    public String valid(){
        if (StringUtils.isEmpty(this.course_name)){
            return "课程名称不能为空。";
        }
        if (StringUtils.isEmpty(this.course_author)){
            return "作者不能为空。";
        }
        if (StringUtils.isEmpty(this.inventory)){
            return "商品库存量不能为空。";
        }
        if (StringUtils.isEmpty(this.price)){
            return "商品价格不能为空。";
        }
        return null;
    }

    @Override
    public String toString() {
        return "CourseCategory{" +
                "id=" + id +
                ", course_name='" + course_name + '\'' +
                ", publish_datetime=" + publish_datetime +
                ", status=" + status +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", course_author='" + course_author + '\'' +
                ", photo_name='" + photo_name + '\'' +
                ", inventory=" + inventory +
                '}';
    }

}
