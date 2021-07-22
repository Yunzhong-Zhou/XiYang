package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/28.
 */
public class DeviceListModel_Position implements Serializable {
    private List<LocationDeviceVoListBean> locationDeviceVoList;

    public List<LocationDeviceVoListBean> getLocationDeviceVoList() {
        return locationDeviceVoList;
    }

    public void setLocationDeviceVoList(List<LocationDeviceVoListBean> locationDeviceVoList) {
        this.locationDeviceVoList = locationDeviceVoList;
    }

    public static class LocationDeviceVoListBean implements Serializable{
        /**
         * storeId : 1413329610791325696
         * storeName : 门店名称1-1
         * storeImg : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png
         * storeAddress : 才有意义唱歌找不到大不大
         * deviceStatus : 0
         * deviceHostName : hostTestdevice1
         * totalMoney : 0
         */

        private String storeId;
        private String storeName;
        private String storeImg;
        private String storeAddress;
        private String deviceStatus;
        private String deviceHostName;
        private String totalMoney;
        private String differNum;

        public String getDifferNum() {
            return differNum;
        }

        public void setDifferNum(String differNum) {
            this.differNum = differNum;
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

        public String getStoreImg() {
            return storeImg;
        }

        public void setStoreImg(String storeImg) {
            this.storeImg = storeImg;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(String deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public String getDeviceHostName() {
            return deviceHostName;
        }

        public void setDeviceHostName(String deviceHostName) {
            this.deviceHostName = deviceHostName;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }
    }
}
