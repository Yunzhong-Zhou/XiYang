package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/28.
 */
public class MyDeviceListModel implements Serializable {

    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * aliyunStatus : 0
         * hostName : string
         * id : string
         * storeAddress : string
         * storeImage : string
         * storeName : string
         * totalRevenue : 0
         */

        private String aliyunStatus;
        private String hostName;
        private String id;
        private String storeAddress;
        private String storeImage;
        private String storeName;
        private String totalRevenue;

        public String getAliyunStatus() {
            return aliyunStatus;
        }

        public void setAliyunStatus(String aliyunStatus) {
            this.aliyunStatus = aliyunStatus;
        }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getStoreImage() {
            return storeImage;
        }

        public void setStoreImage(String storeImage) {
            this.storeImage = storeImage;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getTotalRevenue() {
            return totalRevenue;
        }

        public void setTotalRevenue(String totalRevenue) {
            this.totalRevenue = totalRevenue;
        }
    }
}
