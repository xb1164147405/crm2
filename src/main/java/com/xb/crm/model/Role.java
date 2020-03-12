package com.xb.crm.model;


/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/9 16:20
 * @history: 1.2020/3/9 created by xiongbiao
 */

public class Role {

    private int id;

    private String name;

    private String description;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
