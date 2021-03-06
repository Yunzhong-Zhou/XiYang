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
    /**
     * inStoreName : string
     * outQuantity : 0
     * outStoreName : string
     * reason : string
     * records : [{"auditImage":"string","auditName":"string","auditStat":0,"auditTime":"2021-07-23T01:55:46.332Z","imager":"string","reason":"string"}]
     * recoverQuantity : 0
     * status : 0
     * warehouseName : string
     */

    private String inStoreName;
    private String outQuantity;
    private String outStoreName;
    private String reason;
    private String recoverQuantity;
    private String warehouseName;
    /**
     * records : [{"auditImage":"string","auditName":"string","auditStat":0,"auditTime":"2021-07-23T02:36:32.988Z","imager":"string","reason":"string"}]
     * renewalPeriod : 0
     * renewalTime : 2021-07-23T02:36:32.988Z
     * sole : 0
     * status : 0
     */

    private String renewalPeriod;
    private String renewalTime;

    /**
     * outQuantity : 0
     * records : [{"auditImage":"string","auditName":"string","auditStat":0,"auditTime":"2021-07-23T02:26:45.935Z","imager":"string","reason":"string"}]
     * recoverQuantity : 0
     * status : 0
     */

    private String cancelReason;
    /**
     * billingUnit : string
     * dailyTopPrice : 0
     * firstHour : 0
     * freeTime : 0
     * reasonName : string
     * records : [{"auditImage":"string","auditName":"string","auditStat":0,"auditTime":"2021-07-23T02:43:46.152Z","imager":"string","reason":"string"}]
     * renewalTime : 0
     * status : 0
     * storePriceIncrease : 0
     * storeTopPrice : 0
     */

    private String billingUnit;
    private String dailyTopPrice;
    private String firstHour;
    private String freeTime;
    private String reasonName;
    private String storePriceIncrease;
    private String storeTopPrice;


    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

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

    public String getInStoreName() {
        return inStoreName;
    }

    public void setInStoreName(String inStoreName) {
        this.inStoreName = inStoreName;
    }

    public String getOutQuantity() {
        return outQuantity;
    }

    public void setOutQuantity(String outQuantity) {
        this.outQuantity = outQuantity;
    }

    public String getOutStoreName() {
        return outStoreName;
    }

    public void setOutStoreName(String outStoreName) {
        this.outStoreName = outStoreName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRecoverQuantity() {
        return recoverQuantity;
    }

    public void setRecoverQuantity(String recoverQuantity) {
        this.recoverQuantity = recoverQuantity;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getRenewalPeriod() {
        return renewalPeriod;
    }

    public void setRenewalPeriod(String renewalPeriod) {
        this.renewalPeriod = renewalPeriod;
    }

    public String getRenewalTime() {
        return renewalTime;
    }

    public void setRenewalTime(String renewalTime) {
        this.renewalTime = renewalTime;
    }

    public String getBillingUnit() {
        return billingUnit;
    }

    public void setBillingUnit(String billingUnit) {
        this.billingUnit = billingUnit;
    }

    public String getDailyTopPrice() {
        return dailyTopPrice;
    }

    public void setDailyTopPrice(String dailyTopPrice) {
        this.dailyTopPrice = dailyTopPrice;
    }

    public String getFirstHour() {
        return firstHour;
    }

    public void setFirstHour(String firstHour) {
        this.firstHour = firstHour;
    }

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public String getStorePriceIncrease() {
        return storePriceIncrease;
    }

    public void setStorePriceIncrease(String storePriceIncrease) {
        this.storePriceIncrease = storePriceIncrease;
    }

    public String getStoreTopPrice() {
        return storeTopPrice;
    }

    public void setStoreTopPrice(String storeTopPrice) {
        this.storeTopPrice = storeTopPrice;
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
