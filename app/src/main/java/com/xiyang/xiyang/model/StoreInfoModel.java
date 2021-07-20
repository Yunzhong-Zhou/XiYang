package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/7/20.
 */
public class StoreInfoModel implements Serializable {
    /**
     * sysOverTimeUnit : 1
     * sysStartPrice : 1000
     * sysUnitPrice : 1000
     * sysFreeTime : 300
     * sysMaxPrice : 5000
     * storeUnitPrice : null
     * storeFreeTime : null
     * storeMaxPrice : null
     * storeOverTimeUnit : null
     */

    private String sysOverTimeUnit;
    private String sysStartPrice;
    private String sysUnitPrice;
    private String sysFreeTime;
    private String sysMaxPrice;
    private String storeUnitPrice;
    private String storeFreeTime;
    private String storeMaxPrice;
    private String storeOverTimeUnit;

    public String getSysOverTimeUnit() {
        return sysOverTimeUnit;
    }

    public void setSysOverTimeUnit(String sysOverTimeUnit) {
        this.sysOverTimeUnit = sysOverTimeUnit;
    }

    public String getSysStartPrice() {
        return sysStartPrice;
    }

    public void setSysStartPrice(String sysStartPrice) {
        this.sysStartPrice = sysStartPrice;
    }

    public String getSysUnitPrice() {
        return sysUnitPrice;
    }

    public void setSysUnitPrice(String sysUnitPrice) {
        this.sysUnitPrice = sysUnitPrice;
    }

    public String getSysFreeTime() {
        return sysFreeTime;
    }

    public void setSysFreeTime(String sysFreeTime) {
        this.sysFreeTime = sysFreeTime;
    }

    public String getSysMaxPrice() {
        return sysMaxPrice;
    }

    public void setSysMaxPrice(String sysMaxPrice) {
        this.sysMaxPrice = sysMaxPrice;
    }

    public String getStoreUnitPrice() {
        return storeUnitPrice;
    }

    public void setStoreUnitPrice(String storeUnitPrice) {
        this.storeUnitPrice = storeUnitPrice;
    }

    public String getStoreFreeTime() {
        return storeFreeTime;
    }

    public void setStoreFreeTime(String storeFreeTime) {
        this.storeFreeTime = storeFreeTime;
    }

    public String getStoreMaxPrice() {
        return storeMaxPrice;
    }

    public void setStoreMaxPrice(String storeMaxPrice) {
        this.storeMaxPrice = storeMaxPrice;
    }

    public String getStoreOverTimeUnit() {
        return storeOverTimeUnit;
    }

    public void setStoreOverTimeUnit(String storeOverTimeUnit) {
        this.storeOverTimeUnit = storeOverTimeUnit;
    }
}
