package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/7/16.
 */
public class DispatchListModel implements Serializable {

    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * createTime : 2021-07-16T09:49:34.962Z
         * id : string
         * status : 0
         * storeName : string
         * type : 0
         */

        private String createTime;
        private String id;
        private int status;
        private String storeName;
        private int type;
        /**
         * merchantId : 1415158482327441408
         * merchantName : 测试商户2
         * merchantLogoUrl : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/14/16262350485301626234711429.png
         * merchantAddress : 阿斯顿马丁路德金
         * merchantUserId : null
         * merchantTotalRevenue : null
         * merchantTotalStoresNumber : null
         */

        private String merchantId;
        private String merchantName;
        private String merchantLogoUrl;
        private String merchantAddress;
        private String merchantUserId;
        private String merchantTotalRevenue;
        private String merchantTotalStoresNumber;



        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantLogoUrl() {
            return merchantLogoUrl;
        }

        public void setMerchantLogoUrl(String merchantLogoUrl) {
            this.merchantLogoUrl = merchantLogoUrl;
        }

        public String getMerchantAddress() {
            return merchantAddress;
        }

        public void setMerchantAddress(String merchantAddress) {
            this.merchantAddress = merchantAddress;
        }

        public String getMerchantUserId() {
            return merchantUserId;
        }

        public void setMerchantUserId(String merchantUserId) {
            this.merchantUserId = merchantUserId;
        }

        public String getMerchantTotalRevenue() {
            return merchantTotalRevenue;
        }

        public void setMerchantTotalRevenue(String merchantTotalRevenue) {
            this.merchantTotalRevenue = merchantTotalRevenue;
        }

        public String getMerchantTotalStoresNumber() {
            return merchantTotalStoresNumber;
        }

        public void setMerchantTotalStoresNumber(String merchantTotalStoresNumber) {
            this.merchantTotalStoresNumber = merchantTotalStoresNumber;
        }
    }
}
