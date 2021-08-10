package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/23.
 */
public class StoreDetailModel implements Serializable {
    /**
     * showPointBtn : null
     * deviceNumber : 2
     * runDeviceNumber : 0
     * offLineDeviceNumber : 2
     * revenue : 0
     * profitSharing : null
     * storeInfo : {"id":"1424543238064443392","storeSn":"572c9fa2ee534184923091e3c897e53f","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/08/09/16284725490311628472329941.png","name":"门店1","childName":"分店1","contactName":"阿斯顿马丁","contactPhone":"18354645445","account":"15277262372828","provinceId":130000,"provinceName":"河北省","cityId":130800,"cityName":"承德市","areaName":"承德县","areaId":130821,"address":"北新区洪湖西路51号","industryId":"1410880790564311040","industryName":"水上娱乐","level":0,"userId":"1424543238097866752","userTypeId":"1424543238094807041","merchantId":"1424540989594406912","merchantName":"商户1","businessHours":"00:00~24:00","latitude":29.607459,"longitude":106.502774,"bdAdminScopeOrganId":"1422085020760354817","bdAdminId":"1422085020756160513","bdAdminName":"bd001","addBdAdminScopeOrganId":"1422085020760354817","addBdAdminId":"1422085020756160513","storeShareRate":20,"workerShareRate":30,"deviceShareRate":40,"merchantShareRate":10,"status":0,"statusStr":"合作中","needInstallDeviceNum":0,"isLoss":0,"type":1,"arrearsMoney":0,"visitStatus":1,"visitStatusStr":"待拜访","contractStatus":1,"maxOrderNum":0,"createTime":"2021-08-09 09:29:09","updateTime":"2021-08-09 09:29:09","delFlag":0,"transferStatus":1,"installStatusStr":"无划转信息","installStatus":2}
     * storeDeviceStatistic : null
     * revenueInfo : null
     * chargesInfo : null
     */

    private String showPointBtn;
    private String deviceNumber;
    private String runDeviceNumber;
    private String offLineDeviceNumber;
    private String revenue;
    private String profitSharing;
    private StoreInfoBean storeInfo;
    private String storeDeviceStatistic;
    private String revenueInfo;
    private String chargesInfo;

    public String getShowPointBtn() {
        return showPointBtn;
    }

    public void setShowPointBtn(String showPointBtn) {
        this.showPointBtn = showPointBtn;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getRunDeviceNumber() {
        return runDeviceNumber;
    }

    public void setRunDeviceNumber(String runDeviceNumber) {
        this.runDeviceNumber = runDeviceNumber;
    }

    public String getOffLineDeviceNumber() {
        return offLineDeviceNumber;
    }

    public void setOffLineDeviceNumber(String offLineDeviceNumber) {
        this.offLineDeviceNumber = offLineDeviceNumber;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getProfitSharing() {
        return profitSharing;
    }

    public void setProfitSharing(String profitSharing) {
        this.profitSharing = profitSharing;
    }

    public StoreInfoBean getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfoBean storeInfo) {
        this.storeInfo = storeInfo;
    }

    public String getStoreDeviceStatistic() {
        return storeDeviceStatistic;
    }

    public void setStoreDeviceStatistic(String storeDeviceStatistic) {
        this.storeDeviceStatistic = storeDeviceStatistic;
    }

    public String getRevenueInfo() {
        return revenueInfo;
    }

    public void setRevenueInfo(String revenueInfo) {
        this.revenueInfo = revenueInfo;
    }

    public String getChargesInfo() {
        return chargesInfo;
    }

    public void setChargesInfo(String chargesInfo) {
        this.chargesInfo = chargesInfo;
    }

    public static class StoreInfoBean implements Serializable{
        /**
         * id : 1424543238064443392
         * storeSn : 572c9fa2ee534184923091e3c897e53f
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/08/09/16284725490311628472329941.png
         * name : 门店1
         * childName : 分店1
         * contactName : 阿斯顿马丁
         * contactPhone : 18354645445
         * account : 15277262372828
         * provinceId : 130000
         * provinceName : 河北省
         * cityId : 130800
         * cityName : 承德市
         * areaName : 承德县
         * areaId : 130821
         * address : 北新区洪湖西路51号
         * industryId : 1410880790564311040
         * industryName : 水上娱乐
         * level : 0
         * userId : 1424543238097866752
         * userTypeId : 1424543238094807041
         * merchantId : 1424540989594406912
         * merchantName : 商户1
         * businessHours : 00:00~24:00
         * latitude : 29.607459
         * longitude : 106.502774
         * bdAdminScopeOrganId : 1422085020760354817
         * bdAdminId : 1422085020756160513
         * bdAdminName : bd001
         * addBdAdminScopeOrganId : 1422085020760354817
         * addBdAdminId : 1422085020756160513
         * storeShareRate : 20
         * workerShareRate : 30
         * deviceShareRate : 40
         * merchantShareRate : 10
         * status : 0
         * statusStr : 合作中
         * needInstallDeviceNum : 0
         * isLoss : 0
         * type : 1
         * arrearsMoney : 0
         * visitStatus : 1
         * visitStatusStr : 待拜访
         * contractStatus : 1
         * maxOrderNum : 0
         * createTime : 2021-08-09 09:29:09
         * updateTime : 2021-08-09 09:29:09
         * delFlag : 0
         * transferStatus : 1
         * installStatusStr : 无划转信息
         * installStatus : 2
         */

        private String id;
        private String storeSn;
        private String image;
        private String name;
        private String childName;
        private String contactName;
        private String contactPhone;
        private String account;
        private String provinceId;
        private String provinceName;
        private String cityId;
        private String cityName;
        private String areaName;
        private String areaId;
        private String address;
        private String industryId;
        private String industryName;
        private String level;
        private String userId;
        private String userTypeId;
        private String merchantId;
        private String merchantName;
        private String businessHours;
        private String latitude;
        private String longitude;
        private String bdAdminScopeOrganId;
        private String bdAdminId;
        private String bdAdminName;
        private String addBdAdminScopeOrganId;
        private String addBdAdminId;
        private String storeShareRate;
        private String workerShareRate;
        private String deviceShareRate;
        private String merchantShareRate;
        private String status;
        private String statusStr;
        private String needInstallDeviceNum;
        private String isLoss;
        private String type;
        private String arrearsMoney;
        private String visitStatus;
        private String visitStatusStr;
        private String lastVisitTime;
        private String contractStatus;
        private String maxOrderNum;
        private String createTime;
        private String updateTime;
        private String delFlag;
        private String transferStatus;
        private String transferStatusStr;
        private String installStatusStr;
        private String installStatus;

        public String getTransferStatusStr() {
            return transferStatusStr;
        }

        public void setTransferStatusStr(String transferStatusStr) {
            this.transferStatusStr = transferStatusStr;
        }

        public String getLastVisitTime() {
            return lastVisitTime;
        }

        public void setLastVisitTime(String lastVisitTime) {
            this.lastVisitTime = lastVisitTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoreSn() {
            return storeSn;
        }

        public void setStoreSn(String storeSn) {
            this.storeSn = storeSn;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIndustryId() {
            return industryId;
        }

        public void setIndustryId(String industryId) {
            this.industryId = industryId;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserTypeId() {
            return userTypeId;
        }

        public void setUserTypeId(String userTypeId) {
            this.userTypeId = userTypeId;
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

        public String getBusinessHours() {
            return businessHours;
        }

        public void setBusinessHours(String businessHours) {
            this.businessHours = businessHours;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
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

        public String getAddBdAdminScopeOrganId() {
            return addBdAdminScopeOrganId;
        }

        public void setAddBdAdminScopeOrganId(String addBdAdminScopeOrganId) {
            this.addBdAdminScopeOrganId = addBdAdminScopeOrganId;
        }

        public String getAddBdAdminId() {
            return addBdAdminId;
        }

        public void setAddBdAdminId(String addBdAdminId) {
            this.addBdAdminId = addBdAdminId;
        }

        public String getStoreShareRate() {
            return storeShareRate;
        }

        public void setStoreShareRate(String storeShareRate) {
            this.storeShareRate = storeShareRate;
        }

        public String getWorkerShareRate() {
            return workerShareRate;
        }

        public void setWorkerShareRate(String workerShareRate) {
            this.workerShareRate = workerShareRate;
        }

        public String getDeviceShareRate() {
            return deviceShareRate;
        }

        public void setDeviceShareRate(String deviceShareRate) {
            this.deviceShareRate = deviceShareRate;
        }

        public String getMerchantShareRate() {
            return merchantShareRate;
        }

        public void setMerchantShareRate(String merchantShareRate) {
            this.merchantShareRate = merchantShareRate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public String getNeedInstallDeviceNum() {
            return needInstallDeviceNum;
        }

        public void setNeedInstallDeviceNum(String needInstallDeviceNum) {
            this.needInstallDeviceNum = needInstallDeviceNum;
        }

        public String getIsLoss() {
            return isLoss;
        }

        public void setIsLoss(String isLoss) {
            this.isLoss = isLoss;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getArrearsMoney() {
            return arrearsMoney;
        }

        public void setArrearsMoney(String arrearsMoney) {
            this.arrearsMoney = arrearsMoney;
        }

        public String getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(String visitStatus) {
            this.visitStatus = visitStatus;
        }

        public String getVisitStatusStr() {
            return visitStatusStr;
        }

        public void setVisitStatusStr(String visitStatusStr) {
            this.visitStatusStr = visitStatusStr;
        }

        public String getContractStatus() {
            return contractStatus;
        }

        public void setContractStatus(String contractStatus) {
            this.contractStatus = contractStatus;
        }

        public String getMaxOrderNum() {
            return maxOrderNum;
        }

        public void setMaxOrderNum(String maxOrderNum) {
            this.maxOrderNum = maxOrderNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
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

        public String getTransferStatus() {
            return transferStatus;
        }

        public void setTransferStatus(String transferStatus) {
            this.transferStatus = transferStatus;
        }

        public String getInstallStatusStr() {
            return installStatusStr;
        }

        public void setInstallStatusStr(String installStatusStr) {
            this.installStatusStr = installStatusStr;
        }

        public String getInstallStatus() {
            return installStatus;
        }

        public void setInstallStatus(String installStatus) {
            this.installStatus = installStatus;
        }
    }
}
