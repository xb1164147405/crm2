package com.xb.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @Description:
 * @author: xiongbiao
 * @since: 2020/3/6 9:59
 * @history: 1.2020/3/6 created by xiongbiao
 */

public class User {

    private int id;

    private String username;

    private String password;
    /**
     * 锁住状态；0 未琐  1锁住
     */
    private int locked;

    @JsonIgnore
    private List<Role> roles;

    public String getRoleNames(){
        StringBuffer sb = new StringBuffer();
        if (roles.size() > 0){
            sb.append("[");
            for (Role role : roles){
                sb.append(role.getName() + ",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");
        }

        return sb.toString();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                '}';
    }
}
