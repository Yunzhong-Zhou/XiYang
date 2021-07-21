package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/7/21.
 */
public class Device2StoreModel implements Serializable {
    private List<DeviceListBean> deviceList;

    public List<DeviceListBean> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DeviceListBean> deviceList) {
        this.deviceList = deviceList;
    }

    public static class DeviceListBean {
        /**
         * storeFullName : 门店名称1-1·A1·B1·C1·D1
         * deviceHostName : hostTestdevice1
         * deviceId : 1410512101939744768
         */

        private String storeFullName;
        private String deviceHostName;
        private String deviceId;

        public String getStoreFullName() {
            return storeFullName;
        }

        public void setStoreFullName(String storeFullName) {
            this.storeFullName = storeFullName;
        }

        public String getDeviceHostName() {
            return deviceHostName;
        }

        public void setDeviceHostName(String deviceHostName) {
            this.deviceHostName = deviceHostName;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
    }
}
