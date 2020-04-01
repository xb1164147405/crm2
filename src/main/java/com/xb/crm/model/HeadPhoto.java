package com.xb.crm.model;

/**
 * @Description: <p>头像存储</p>
 * @author: xiongbiao
 * @since: 2020/4/1 10:58
 * @history: 1.2020/4/1 created by xiongbiao
 */

public class HeadPhoto {
    private int id;

    private String file_name;

    private int user_id;

    @Override
    public String toString() {
        return "HeadPhoto{" +
                "id=" + id +
                ", file_name='" + file_name + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
