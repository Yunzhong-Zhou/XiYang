package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/10.
 */
public class DeviceDetailModel implements Serializable {
    /**
     * deviceName : 383742cabd714f2ea37b0ad83fbe9c35
     * storeName : 门店名称周
     * merchantName : 阿斯顿马丁路德金
     */

    private String deviceName;
    private String storeName;
    private String merchantName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
