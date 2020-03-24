package com.xb.crm.model;

import java.util.Date;

/**
 * @Description: <P>课程分类模型</P>
 * @author: xiongbiao
 * @since: 2020/3/1 15:42
 * @history: 1.2020/3/1 created by xiongbiao
 */

public class CourseCategory {

    private int id;
    private String course_name;
    private Date publish_datetime;
    private int status;
    private String course_author;

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

    public Date getPublish_datetime() {
        return publish_datetime;
    }

    public void setPublish_datetime(Date publish_datetime) {
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

    @Override
    public String toString() {
        return "CourseCategory{" +
                "id=" + id +
                ", course_name='" + course_name + '\'' +
                ", publish_datetime=" + publish_datetime +
                ", status=" + status +
                ", course_author='" + course_author + '\'' +
                '}';
    }
}
