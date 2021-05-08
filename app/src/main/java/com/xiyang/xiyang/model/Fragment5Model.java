package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/3/26.
 */
public class Fragment5Model implements Serializable {
    /**
     * id : 71
     * roleType : bdm
     * nickname : bdm周
     * mobile : 18306043083
     * head : http://localhost:8000/head/head.png
     * amount : 0.00
     * merchantsNum : 0
     * storesNum : 0
     * deviceNum : 0
     */

    private String id;
    private String roleType;
    private String nickname;
    private String mobile;
    private String head;
    private String amount;
    private String merchantsNum;
    private String storesNum;
    private String deviceNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMerchantsNum() {
        return merchantsNum;
    }

    public void setMerchantsNum(String merchantsNum) {
        this.merchantsNum = merchantsNum;
    }

    public String getStoresNum() {
        return storesNum;
    }

    public void setStoresNum(String storesNum) {
        this.storesNum = storesNum;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }
}
