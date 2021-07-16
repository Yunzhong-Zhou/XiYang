package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/5/7.
 */
public class MyVisitListModel implements Serializable {
    /**
     * records : [{"id":"1414870458077069314","type":1,"storeId":null,"isBusiness":null,"reportStatus":null,"way":null,"contractName":null,"contractMobile":null,"reason":null,"bdAdminScopeOrganId":null,"bdAdminId":null,"bdAdminName":null,"feedback":null,"isAdver":null,"remark":null,"images":null,"extra":null,"storeName":"门店123","address":null,"isIntention":null,"visitTime":null,"createTime":1626166379000,"updateTime":null,"delFlag":null},{"id":"1415960964122234882","type":1,"storeId":null,"isBusiness":null,"reportStatus":null,"way":null,"contractName":null,"contractMobile":null,"reason":null,"bdAdminScopeOrganId":null,"bdAdminId":null,"bdAdminName":null,"feedback":null,"isAdver":null,"remark":null,"images":null,"extra":null,"storeName":"信达门店3","address":null,"isIntention":null,"visitTime":null,"createTime":1626426376000,"updateTime":null,"delFlag":null}]
     * total : 2
     * size : 10
     * current : 1
     * orders : []
     * optimizeCountSql : true
     * hitCount : false
     * countId : null
     * maxLimit : null
     * searchCount : true
     * pages : 1
     */

    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 1414870458077069314
         * type : 1
         * storeId : null
         * isBusiness : null
         * reportStatus : null
         * way : null
         * contractName : null
         * contractMobile : null
         * reason : null
         * bdAdminScopeOrganId : null
         * bdAdminId : null
         * bdAdminName : null
         * feedback : null
         * isAdver : null
         * remark : null
         * images : null
         * extra : null
         * storeName : 门店123
         * address : null
         * isIntention : null
         * visitTime : null
         * createTime : 1626166379000
         * updateTime : null
         * delFlag : null
         */

        private String id;
        private int type;
        private String storeId;
        private String isBusiness;
        private String reportStatus;
        private String way;
        private String contractName;
        private String contractMobile;
        private String reason;
        private String bdAdminScopeOrganId;
        private String bdAdminId;
        private String bdAdminName;
        private String feedback;
        private String isAdver;
        private String remark;
        private String images;
        private String extra;
        private String storeName;
        private String address;
        private String isIntention;
        private String visitTime;
        private long createTime;
        private String updateTime;
        private String delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getIsBusiness() {
            return isBusiness;
        }

        public void setIsBusiness(String isBusiness) {
            this.isBusiness = isBusiness;
        }

        public String getReportStatus() {
            return reportStatus;
        }

        public void setReportStatus(String reportStatus) {
            this.reportStatus = reportStatus;
        }

        public String getWay() {
            return way;
        }

        public void setWay(String way) {
            this.way = way;
        }

        public String getContractName() {
            return contractName;
        }

        public void setContractName(String contractName) {
            this.contractName = contractName;
        }

        public String getContractMobile() {
            return contractMobile;
        }

        public void setContractMobile(String contractMobile) {
            this.contractMobile = contractMobile;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getBdAdminScopeOrganId() {
            return bdAdminScopeOrganId;
        }

        public void setBdAdminScopeOrganId(String bdAdminScopeOrganId) {
            this.bdAdminScopeOrganId = bdAdminScopeOrganId;
        }

        public String getBdAdminId() {
            return bdAdminId;
        }

        public void setBdAdminId(String bdAdminId) {
            this.bdAdminId = bdAdminId;
        }

        public String getBdAdminName() {
            return bdAdminName;
        }

        public void setBdAdminName(String bdAdminName) {
            this.bdAdminName = bdAdminName;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getIsAdver() {
            return isAdver;
        }

        public void setIsAdver(String isAdver) {
            this.isAdver = isAdver;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIsIntention() {
            return isIntention;
        }

        public void setIsIntention(String isIntention) {
            this.isIntention = isIntention;
        }

        public String getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(String visitTime) {
            this.visitTime = visitTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
