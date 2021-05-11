package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/11.
 */
public class StaffDetailModel implements Serializable {
    /**
     * id : 70
     * name : cm周
     * head : http://localhost:8000/head/head.png
     * mobile : 18306043082
     * sex :
     * money : 0.00
     * deviceNum : 0
     * storeNum : 0
     * merchantNum : 0
     * bdNum : 0
     * bdmNum : 1
     * account : 18306043082
     * joinAt : 2021-05-11 14:49:39
     * parentName : 大区周
     */

    private String id;
    private String name;
    private String head;
    private String mobile;
    private String sex;
    private String money;
    private String deviceNum;
    private String storeNum;
    private String merchantNum;
    private String bdNum;
    private String bdmNum;
    private String account;
    private String joinAt;
    private String parentName;
    private String regionName;
    private String role;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getBdNum() {
        return bdNum;
    }

    public void setBdNum(String bdNum) {
        this.bdNum = bdNum;
    }

    public String getBdmNum() {
        return bdmNum;
    }

    public void setBdmNum(String bdmNum) {
        this.bdmNum = bdmNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getJoinAt() {
        return joinAt;
    }

    public void setJoinAt(String joinAt) {
        this.joinAt = joinAt;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
