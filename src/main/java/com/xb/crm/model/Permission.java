package com.xb.crm.model;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 16:38
 * @history: 1.2020/3/6 created by xiongbiao
 */

public class Permission {
    /**
     * 编号ID
     */
    private int id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 访问路径
     */
    private String path;
    /**
     * '授权标识'
     */
    private String authorization_flag;
    /**
     * '父级菜单'
     */
    private int parent_id;
    /**
     * '是否属于导航菜单1:是 0:不是',
     */
    private int is_menu;
    /**
     * '菜单排序'
     */
    private int z_index;
    /**
     * 子菜单
     */
    private List<Permission> subMenus;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthorization_flag() {
        return authorization_flag;
    }

    public void setAuthorization_flag(String authorization_flag) {
        this.authorization_flag = authorization_flag;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getIs_menu() {
        return is_menu;
    }

    public void setIs_menu(int is_menu) {
        this.is_menu = is_menu;
    }

    public int getZ_index() {
        return z_index;
    }

    public void setZ_index(int z_index) {
        this.z_index = z_index;
    }

    public List<Permission> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Permission> subMenus) {
        this.subMenus = subMenus;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", authorization_flag='" + authorization_flag + '\'' +
                ", parent_id=" + parent_id +
                ", is_menu=" + is_menu +
                ", z_index=" + z_index +
                '}';
    }

    //用于zTree的pid获取
    public int getpId(){
        return parent_id;
    }
}
