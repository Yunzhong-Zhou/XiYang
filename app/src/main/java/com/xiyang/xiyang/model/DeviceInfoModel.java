package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/7/13.
 */
public class DeviceInfoModel implements Serializable {
    /**
     * id : 1410512101939744768
     * moduleName : 3ad1af90f5e242aea9ccf85f8bfa93f0
     * hostName : hostTestdevice1
     * storeId : 1411188080041320450
     * storeName : 门店12345
     * storeAddress : 583
     */

    private String id;
    private String moduleName;
    private String hostName;
    private String storeId;
    private String storeName;
    private String storeAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
