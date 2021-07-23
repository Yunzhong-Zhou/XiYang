package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/19.
 */
public class SelectMyCityModel implements Serializable {
    /**
     * id : 0
     * name : string
     * level : 0
     */

    private String id;
    private String name;
    private String level;
    private boolean isxuanzhong = false;

    public boolean isIsxuanzhong() {
        return isxuanzhong;
    }

    public void setIsxuanzhong(boolean isxuanzhong) {
        this.isxuanzhong = isxuanzhong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
