package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/11.
 */
public class KeyValueModel_m implements Serializable {
    public KeyValueModel_m(String key, String value, String danwei) {
        this.key = key;
        this.value = value;
        this.danwei = danwei;
    }

    String key;
    String value;
    String danwei;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }
}
