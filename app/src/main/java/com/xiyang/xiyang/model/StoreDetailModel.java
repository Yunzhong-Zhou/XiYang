package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/23.
 */
public class StoreDetailModel implements Serializable {
    /**
     * deviceNumber : 0
     * runDeviceNumber : 0
     * offLineDeviceNumber : 0
     * revenue : 1500
     * storeInfo : {"id":"1412691587682668544","storeSn":"10e5cc8fd18b43a886e0e1025cc236ce","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/07/16256468952971625646708054.png","name":"门店名称","childName":"分店名称","contactName":"阿斯顿马丁","contactPhone":"157885855788","account":"23432234566","provinceId":140000,"cityId":140500,"cityName":"晋城市","areaId":140521,"address":"北新区洪湖西路51号","industryId":"1409756530408034304","userId":"1412691587779137536","userTypeId":"1412691587792859138","merchantId":"1411240609229967360","merchantName":"商户名称","businessHours":"06:30~24:00","latitude":29.607459,"longitude":106.502774,"bdAdminScopeOrganId":"1410143973256732673","bdAdminId":"1410143973189623809","addBdAdminScopeOrganId":"1410143973256732673","addBdAdminId":"1410143973189623809","storeShareRate":10,"workerShareRate":10,"deviceShareRate":20,"status":0,"needInstallDeviceNum":0,"isLoss":0,"type":1,"visitStatus":1,"createTime":"2021-07-07 16:34:56","updateTime":"2021-07-07 16:34:56","delFlag":0,"transferStatus":1}
     */

    private String deviceNumber;
    private String runDeviceNumber;
    private String offLineDeviceNumber;
    private String revenue;
    private StoreInfoBean storeInfo;

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

    public StoreInfoBean getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfoBean storeInfo) {
        this.storeInfo = storeInfo;
    }

    public static class StoreInfoBean implements Serializable{
        /**
         * id : 1412691587682668544
         * storeSn : 10e5cc8fd18b43a886e0e1025cc236ce
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/07/16256468952971625646708054.png
         * name : 门店名称
         * childName : 分店名称
         * contactName : 阿斯顿马丁
         * contactPhone : 157885855788
         * account : 23432234566
         * provinceId : 140000
         * cityId : 140500
         * cityName : 晋城市
         * areaId : 140521
         * address : 北新区洪湖西路51号
         * industryId : 1409756530408034304
         * userId : 1412691587779137536
         * userTypeId : 1412691587792859138
         * merchantId : 1411240609229967360
         * merchantName : 商户名称
         * businessHours : 06:30~24:00
         * latitude : 29.607459
         * longitude : 106.502774
         * bdAdminScopeOrganId : 1410143973256732673
         * bdAdminId : 1410143973189623809
         * addBdAdminScopeOrganId : 1410143973256732673
         * addBdAdminId : 1410143973189623809
         * storeShareRate : 10
         * workerShareRate : 10
         * deviceShareRate : 20
         * status : 0
         * needInstallDeviceNum : 0
         * isLoss : 0
         * type : 1
         * visitStatus : 1
         * createTime : 2021-07-07 16:34:56
         * updateTime : 2021-07-07 16:34:56
         * delFlag : 0
         * transferStatus : 1
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
        private String cityId;
        private String cityName;
        private String areaId;
        private String address;
        private String industryId;
        private String userId;
        private String userTypeId;
        private String merchantId;
        private String merchantName;
        private String businessHours;
        private String latitude;
        private String longitude;
        private String bdAdminScopeOrganId;
        private String bdAdminId;
        private String addBdAdminScopeOrganId;
        private String addBdAdminId;
        private String storeShareRate;
        private String workerShareRate;
        private String deviceShareRate;
        private String status;
        private String needInstallDeviceNum;
        private String isLoss;
        private String type;
        private String visitStatus;
        private String createTime;
        private String updateTime;
        private String delFlag;
        private String transferStatus;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(String visitStatus) {
            this.visitStatus = visitStatus;
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
    }
}
