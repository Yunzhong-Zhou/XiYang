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
        private String status;
        private String storeName;
        private String type;
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
        /**
         * storeId : 1411188080041320450
         * createTime : 1626156631000
         */

        private String storeId;
        private String storeAddress;
        /**
         * storeImage : string
         * storeTotalDeviceNumber : 0
         * storeTotalRevenue : 0
         */

        private String storeImage;
        private String storeTotalDeviceNumber;
        private String storeTotalRevenue;
        /**
         * currentOrganCode : BDM
         * currentName : 黄BDM
         */

        private String currentOrganCode;
        private String currentName;

        private String transferCreateUserOrganCode;
        private String transferCreateUserName;

        public String getTransferCreateUserOrganCode() {
            return transferCreateUserOrganCode;
        }

        public void setTransferCreateUserOrganCode(String transferCreateUserOrganCode) {
            this.transferCreateUserOrganCode = transferCreateUserOrganCode;
        }

        public String getTransferCreateUserName() {
            return transferCreateUserName;
        }

        public void setTransferCreateUserName(String transferCreateUserName) {
            this.transferCreateUserName = transferCreateUserName;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
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

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStoreImage() {
            return storeImage;
        }

        public void setStoreImage(String storeImage) {
            this.storeImage = storeImage;
        }

        public String getStoreTotalDeviceNumber() {
            return storeTotalDeviceNumber;
        }

        public void setStoreTotalDeviceNumber(String storeTotalDeviceNumber) {
            this.storeTotalDeviceNumber = storeTotalDeviceNumber;
        }

        public String getStoreTotalRevenue() {
            return storeTotalRevenue;
        }

        public void setStoreTotalRevenue(String storeTotalRevenue) {
            this.storeTotalRevenue = storeTotalRevenue;
        }

        public String getCurrentOrganCode() {
            return currentOrganCode;
        }

        public void setCurrentOrganCode(String currentOrganCode) {
            this.currentOrganCode = currentOrganCode;
        }

        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            this.currentName = currentName;
        }
    }
}
