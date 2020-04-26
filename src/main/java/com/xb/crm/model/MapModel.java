package com.xb.crm.model;

import java.io.Serializable;

/**
 * @Description: <p>地图分布结果bean</p>
 * @author: xiongbiao
 * @since: 2020/4/26 15:46
 * @history: 1.2020/4/26 created by xiongbiao
 */

public class MapModel implements Serializable {

    private static final long serialVersionUID = 988157758820630903L;
    /**
     * 省份
     */
    private String name;

    /**
     * 人数
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MapModel{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
