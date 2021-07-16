package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/8.
 */
public class Fragment1Model_m implements Serializable {
    /**
     * organId : 1414885152384065537
     * organName : null
     * areaNumber : 2
     * cmNumber : 1
     * bdmNumber : 1
     * bdNumber : 1
     * merchantNumber : 2
     * storeNumber : 1
     * deviceNumber : 0
     * money : 0
     */

    private String organId;
    private String organName;
    private String areaNumber;
    private String cmNumber;
    private String bdmNumber;
    private String bdNumber;
    private String merchantNumber;
    private String storeNumber;
    private String deviceNumber;
    private String money;

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getCmNumber() {
        return cmNumber;
    }

    public void setCmNumber(String cmNumber) {
        this.cmNumber = cmNumber;
    }

    public String getBdmNumber() {
        return bdmNumber;
    }

    public void setBdmNumber(String bdmNumber) {
        this.bdmNumber = bdmNumber;
    }

    public String getBdNumber() {
        return bdNumber;
    }

    public void setBdNumber(String bdNumber) {
        this.bdNumber = bdNumber;
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
