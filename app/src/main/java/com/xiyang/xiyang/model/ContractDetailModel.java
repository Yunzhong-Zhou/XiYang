package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class ContractDetailModel implements Serializable {
    /**
     * checkTime : 2021-07-22T09:21:17.945Z
     * contractNumber : string
     * contractUrl : string
     * createTime : 2021-07-22T09:21:17.945Z
     * id : string
     * image : string
     * merchantAccount : string
     * merchantAddress : string
     * merchantCityName : string
     * merchantCompanyName : string
     * merchantContactName : string
     * merchantContactPhone : string
     * merchantIndustry : string
     * merchantLicenseNo : string
     * merchantLogoUrl : string
     * merchantName : string
     * name : string
     * qualificationsImageUrl : string
     * records : [{"auditImage":"string","auditName":"string","auditStat":0,"auditTime":"2021-07-22T09:21:17.945Z","imager":"string","reason":"string"}]
     * signPeriod : 0
     * signTime : 2021-07-22T09:21:17.945Z
     * sole : 0
     * status : 0
     * typeName : string
     */

    private String checkTime;
    private String contractNumber;
    private String contractUrl;
    private String createTime;
    private String id;
    private String image;
    private String merchantAccount;
    private String merchantAddress;
    private String merchantCityName;
    private String merchantCompanyName;
    private String merchantContactName;
    private String merchantContactPhone;
    private String merchantIndustry;
    private String merchantLicenseNo;
    private String merchantLogoUrl;
    private String merchantName;
    private String name;
    private String qualificationsImageUrl;
    private String signPeriod;
    private String signTime;
    private String sole;
    private String status;
    private String typeName;
    private List<RecordsBean> records;
    /**
     * addQuantity : 0
     * applyType : 0
     * records : [{"auditImage":"string","auditName":"string","auditStat":0,"auditTime":"2021-07-22T09:49:34.889Z","imager":"string","reason":"string"}]
     * status : 0
     * storeName : string
     */

    private String addQuantity;
    private String applyType;
    private String storeName;

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantCityName() {
        return merchantCityName;
    }

    public void setMerchantCityName(String merchantCityName) {
        this.merchantCityName = merchantCityName;
    }

    public String getMerchantCompanyName() {
        return merchantCompanyName;
    }

    public void setMerchantCompanyName(String merchantCompanyName) {
        this.merchantCompanyName = merchantCompanyName;
    }

    public String getMerchantContactName() {
        return merchantContactName;
    }

    public void setMerchantContactName(String merchantContactName) {
        this.merchantContactName = merchantContactName;
    }

    public String getMerchantContactPhone() {
        return merchantContactPhone;
    }

    public void setMerchantContactPhone(String merchantContactPhone) {
        this.merchantContactPhone = merchantContactPhone;
    }

    public String getMerchantIndustry() {
        return merchantIndustry;
    }

    public void setMerchantIndustry(String merchantIndustry) {
        this.merchantIndustry = merchantIndustry;
    }

    public String getMerchantLicenseNo() {
        return merchantLicenseNo;
    }

    public void setMerchantLicenseNo(String merchantLicenseNo) {
        this.merchantLicenseNo = merchantLicenseNo;
    }

    public String getMerchantLogoUrl() {
        return merchantLogoUrl;
    }

    public void setMerchantLogoUrl(String merchantLogoUrl) {
        this.merchantLogoUrl = merchantLogoUrl;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualificationsImageUrl() {
        return qualificationsImageUrl;
    }

    public void setQualificationsImageUrl(String qualificationsImageUrl) {
        this.qualificationsImageUrl = qualificationsImageUrl;
    }

    public String getSignPeriod() {
        return signPeriod;
    }

    public void setSignPeriod(String signPeriod) {
        this.signPeriod = signPeriod;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSole() {
        return sole;
    }

    public void setSole(String sole) {
        this.sole = sole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public String getAddQuantity() {
        return addQuantity;
    }

    public void setAddQuantity(String addQuantity) {
        this.addQuantity = addQuantity;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public static class RecordsBean {
        /**
         * auditImage : string
         * auditName : string
         * auditStat : 0
         * auditTime : 2021-07-22T09:21:17.945Z
         * imager : string
         * reason : string
         */

        private String auditImage;
        private String auditName;
        private String auditStat;
        private String auditTime;
        private String imager;
        private String reason;

        public String getAuditImage() {
            return auditImage;
        }

        public void setAuditImage(String auditImage) {
            this.auditImage = auditImage;
        }

        public String getAuditName() {
            return auditName;
        }

        public void setAuditName(String auditName) {
            this.auditName = auditName;
        }

        public String getAuditStat() {
            return auditStat;
        }

        public void setAuditStat(String auditStat) {
            this.auditStat = auditStat;
        }

        public String getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public String getImager() {
            return imager;
        }

        public void setImager(String imager) {
            this.imager = imager;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
