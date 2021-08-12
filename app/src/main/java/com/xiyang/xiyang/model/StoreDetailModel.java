package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/23.
 */
public class StoreDetailModel implements Serializable {
    /**
     * showPointBtn : null
     * deviceNumber : 1
     * runDeviceNumber : 0
     * offLineDeviceNumber : 1
     * revenue : 0
     * profitSharing : null
     * storeInfo : {"id":"1424918321987588096","storeSn":"857cee007b6f4186903861f5d4653b19","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/08/10/16285619760451628561756228.png","name":"门店1","childName":"分店1","contactName":"阿斯顿","contactPhone":"14738684849","account":"14000000000","provinceId":140000,"provinceName":"山西省","cityId":140500,"cityName":"晋城市","areaName":"沁水县","areaId":140521,"address":"黄山大道重庆高科总部广场西南侧约220米","industryId":"1410880790564311040","industryName":"水上娱乐","level":0,"userId":"1424917244172963840","userTypeId":"1424918322005381122","merchantId":"1424911989959954432","businessHours":"00:00~24:00","latitude":29.61397,"longitude":106.49105,"bdAdminScopeOrganId":"1422085020760354817","bdAdminId":"1422085020756160513","bdAdminName":"bd001","bdOrganName":"BD","addBdAdminScopeOrganId":"1422085020760354817","addBdAdminId":"1422085020756160513","addBdAdminName":"bd001","addBdOrganName":"BD","storeShareRate":20,"workerShareRate":30,"deviceShareRate":30,"merchantShareRate":10,"status":0,"statusStr":"合作中","needInstallDeviceNum":0,"isLoss":0,"type":1,"arrearsMoney":0,"visitStatus":1,"visitStatusStr":"待拜访","contractStatus":1,"maxOrderNum":0,"createTime":"2021-08-10 10:19:36","updateTime":"2021-08-11 09:51:13","delFlag":0,"transferStatus":1,"transferStatusStr":"无划转信息","installStatusStr":"安装中","installStatus":2}
     * storeDeviceStatistic : {"bindNumber":1,"operationNumber":0,"onLineNumber":0,"offLineNumber":1,"lostNumber":1,"lastBindTime":null,"waitAddNumber":4,"waitRecycleNumber":0,"waitSwapNumber":0}
     * revenueInfo : {"totalRevenue":0,"totalOrderNumber":0,"todayRevenue":0,"todayOrderNumber":0,"lastMonthMovablePinNumber":0,"lastMonthOnlineNumber":0,"thisMonthMovablePinNumber":0,"thisMonthOnlineNumber":0,"thirtyDayVisitNumber":0,"thisMonthVisited":null,"thisMonthVisitTime":null,"lastMonthLogo":null,"logo":null}
     * chargesInfo : {"firstHour":null,"systemRenewal":null,"systemDayCap":null,"pricingUnit":null,"freeTime":null,"customUnitPrice":null,"customUnitCap":null,"storeShareRatio":null,"employeeShareRatio":0,"deviceShareRatio":0,"merchantShareRatio":0}
     */

    private String showPointBtn;
    private String deviceNumber;
    private String runDeviceNumber;
    private String offLineDeviceNumber;
    private String revenue;
    private String profitSharing;
    private StoreInfoBean storeInfo;
    private StoreDeviceStatisticBean storeDeviceStatistic;
    private RevenueInfoBean revenueInfo;
    private ChargesInfoBean chargesInfo;

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

    public StoreDeviceStatisticBean getStoreDeviceStatistic() {
        return storeDeviceStatistic;
    }

    public void setStoreDeviceStatistic(StoreDeviceStatisticBean storeDeviceStatistic) {
        this.storeDeviceStatistic = storeDeviceStatistic;
    }

    public RevenueInfoBean getRevenueInfo() {
        return revenueInfo;
    }

    public void setRevenueInfo(RevenueInfoBean revenueInfo) {
        this.revenueInfo = revenueInfo;
    }

    public ChargesInfoBean getChargesInfo() {
        return chargesInfo;
    }

    public void setChargesInfo(ChargesInfoBean chargesInfo) {
        this.chargesInfo = chargesInfo;
    }

    public static class StoreInfoBean implements  Serializable{
        /**
         * id : 1424918321987588096
         * storeSn : 857cee007b6f4186903861f5d4653b19
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/08/10/16285619760451628561756228.png
         * name : 门店1
         * childName : 分店1
         * contactName : 阿斯顿
         * contactPhone : 14738684849
         * account : 14000000000
         * provinceId : 140000
         * provinceName : 山西省
         * cityId : 140500
         * cityName : 晋城市
         * areaName : 沁水县
         * areaId : 140521
         * address : 黄山大道重庆高科总部广场西南侧约220米
         * industryId : 1410880790564311040
         * industryName : 水上娱乐
         * level : 0
         * userId : 1424917244172963840
         * userTypeId : 1424918322005381122
         * merchantId : 1424911989959954432
         * businessHours : 00:00~24:00
         * latitude : 29.61397
         * longitude : 106.49105
         * bdAdminScopeOrganId : 1422085020760354817
         * bdAdminId : 1422085020756160513
         * bdAdminName : bd001
         * bdOrganName : BD
         * addBdAdminScopeOrganId : 1422085020760354817
         * addBdAdminId : 1422085020756160513
         * addBdAdminName : bd001
         * addBdOrganName : BD
         * storeShareRate : 20
         * workerShareRate : 30
         * deviceShareRate : 30
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
         * createTime : 2021-08-10 10:19:36
         * updateTime : 2021-08-11 09:51:13
         * delFlag : 0
         * transferStatus : 1
         * transferStatusStr : 无划转信息
         * installStatusStr : 安装中
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
        private String bdOrganName;
        private String addBdAdminScopeOrganId;
        private String addBdAdminId;
        private String addBdAdminName;
        private String addBdOrganName;
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
        private String contractStatus;
        private String maxOrderNum;
        private String createTime;
        private String updateTime;
        private String delFlag;
        private String transferStatus;
        private String transferStatusStr;
        private String installStatusStr;
        private String installStatus;
        private String lastVisitTime;

        public String getLastVisitTime() {
            return lastVisitTime;
        }

        public void setLastVisitTime(String lastVisitTime) {
            this.lastVisitTime = lastVisitTime;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
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

        public String getBdOrganName() {
            return bdOrganName;
        }

        public void setBdOrganName(String bdOrganName) {
            this.bdOrganName = bdOrganName;
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

        public String getAddBdAdminName() {
            return addBdAdminName;
        }

        public void setAddBdAdminName(String addBdAdminName) {
            this.addBdAdminName = addBdAdminName;
        }

        public String getAddBdOrganName() {
            return addBdOrganName;
        }

        public void setAddBdOrganName(String addBdOrganName) {
            this.addBdOrganName = addBdOrganName;
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

        public String getTransferStatusStr() {
            return transferStatusStr;
        }

        public void setTransferStatusStr(String transferStatusStr) {
            this.transferStatusStr = transferStatusStr;
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

    public static class StoreDeviceStatisticBean implements Serializable{
        /**
         * bindNumber : 1
         * operationNumber : 0
         * onLineNumber : 0
         * offLineNumber : 1
         * lostNumber : 1
         * lastBindTime : null
         * waitAddNumber : 4
         * waitRecycleNumber : 0
         * waitSwapNumber : 0
         */

        private String bindNumber;
        private String operationNumber;
        private String onLineNumber;
        private String offLineNumber;
        private String lostNumber;
        private String lastBindTime;
        private String waitAddNumber;
        private String waitRecycleNumber;
        private String waitSwapNumber;

        public String getBindNumber() {
            return bindNumber;
        }

        public void setBindNumber(String bindNumber) {
            this.bindNumber = bindNumber;
        }

        public String getOperationNumber() {
            return operationNumber;
        }

        public void setOperationNumber(String operationNumber) {
            this.operationNumber = operationNumber;
        }

        public String getOnLineNumber() {
            return onLineNumber;
        }

        public void setOnLineNumber(String onLineNumber) {
            this.onLineNumber = onLineNumber;
        }

        public String getOffLineNumber() {
            return offLineNumber;
        }

        public void setOffLineNumber(String offLineNumber) {
            this.offLineNumber = offLineNumber;
        }

        public String getLostNumber() {
            return lostNumber;
        }

        public void setLostNumber(String lostNumber) {
            this.lostNumber = lostNumber;
        }

        public String getLastBindTime() {
            return lastBindTime;
        }

        public void setLastBindTime(String lastBindTime) {
            this.lastBindTime = lastBindTime;
        }

        public String getWaitAddNumber() {
            return waitAddNumber;
        }

        public void setWaitAddNumber(String waitAddNumber) {
            this.waitAddNumber = waitAddNumber;
        }

        public String getWaitRecycleNumber() {
            return waitRecycleNumber;
        }

        public void setWaitRecycleNumber(String waitRecycleNumber) {
            this.waitRecycleNumber = waitRecycleNumber;
        }

        public String getWaitSwapNumber() {
            return waitSwapNumber;
        }

        public void setWaitSwapNumber(String waitSwapNumber) {
            this.waitSwapNumber = waitSwapNumber;
        }
    }

    public static class RevenueInfoBean implements Serializable{
        /**
         * totalRevenue : 0
         * totalOrderNumber : 0
         * todayRevenue : 0
         * todayOrderNumber : 0
         * lastMonthMovablePinNumber : 0
         * lastMonthOnlineNumber : 0
         * thisMonthMovablePinNumber : 0
         * thisMonthOnlineNumber : 0
         * thirtyDayVisitNumber : 0
         * thisMonthVisited : null
         * thisMonthVisitTime : null
         * lastMonthLogo : null
         * logo : null
         */

        private String totalRevenue;
        private String totalOrderNumber;
        private String todayRevenue;
        private String todayOrderNumber;
        private String lastMonthMovablePinNumber;
        private String lastMonthOnlineNumber;
        private String thisMonthMovablePinNumber;
        private String thisMonthOnlineNumber;
        private String thirtyDayVisitNumber;
        private String thisMonthVisited;
        private String thisMonthVisitTime;
        private String lastMonthLogo;
        private String logo;

        public String getTotalRevenue() {
            return totalRevenue;
        }

        public void setTotalRevenue(String totalRevenue) {
            this.totalRevenue = totalRevenue;
        }

        public String getTotalOrderNumber() {
            return totalOrderNumber;
        }

        public void setTotalOrderNumber(String totalOrderNumber) {
            this.totalOrderNumber = totalOrderNumber;
        }

        public String getTodayRevenue() {
            return todayRevenue;
        }

        public void setTodayRevenue(String todayRevenue) {
            this.todayRevenue = todayRevenue;
        }

        public String getTodayOrderNumber() {
            return todayOrderNumber;
        }

        public void setTodayOrderNumber(String todayOrderNumber) {
            this.todayOrderNumber = todayOrderNumber;
        }

        public String getLastMonthMovablePinNumber() {
            return lastMonthMovablePinNumber;
        }

        public void setLastMonthMovablePinNumber(String lastMonthMovablePinNumber) {
            this.lastMonthMovablePinNumber = lastMonthMovablePinNumber;
        }

        public String getLastMonthOnlineNumber() {
            return lastMonthOnlineNumber;
        }

        public void setLastMonthOnlineNumber(String lastMonthOnlineNumber) {
            this.lastMonthOnlineNumber = lastMonthOnlineNumber;
        }

        public String getThisMonthMovablePinNumber() {
            return thisMonthMovablePinNumber;
        }

        public void setThisMonthMovablePinNumber(String thisMonthMovablePinNumber) {
            this.thisMonthMovablePinNumber = thisMonthMovablePinNumber;
        }

        public String getThisMonthOnlineNumber() {
            return thisMonthOnlineNumber;
        }

        public void setThisMonthOnlineNumber(String thisMonthOnlineNumber) {
            this.thisMonthOnlineNumber = thisMonthOnlineNumber;
        }

        public String getThirtyDayVisitNumber() {
            return thirtyDayVisitNumber;
        }

        public void setThirtyDayVisitNumber(String thirtyDayVisitNumber) {
            this.thirtyDayVisitNumber = thirtyDayVisitNumber;
        }

        public String getThisMonthVisited() {
            return thisMonthVisited;
        }

        public void setThisMonthVisited(String thisMonthVisited) {
            this.thisMonthVisited = thisMonthVisited;
        }

        public String getThisMonthVisitTime() {
            return thisMonthVisitTime;
        }

        public void setThisMonthVisitTime(String thisMonthVisitTime) {
            this.thisMonthVisitTime = thisMonthVisitTime;
        }

        public String getLastMonthLogo() {
            return lastMonthLogo;
        }

        public void setLastMonthLogo(String lastMonthLogo) {
            this.lastMonthLogo = lastMonthLogo;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }

    public static class ChargesInfoBean implements Serializable{
        /**
         * firstHour : null
         * systemRenewal : null
         * systemDayCap : null
         * pricingUnit : null
         * freeTime : null
         * customUnitPrice : null
         * customUnitCap : null
         * storeShareRatio : null
         * employeeShareRatio : 0
         * deviceShareRatio : 0
         * merchantShareRatio : 0
         */

        private String firstHour;
        private String systemRenewal;
        private String systemDayCap;
        private String pricingUnit;
        private String freeTime;
        private String customUnitPrice;
        private String customUnitCap;
        private String storeShareRatio;
        private String employeeShareRatio;
        private String deviceShareRatio;
        private String merchantShareRatio;

        public String getFirstHour() {
            return firstHour;
        }

        public void setFirstHour(String firstHour) {
            this.firstHour = firstHour;
        }

        public String getSystemRenewal() {
            return systemRenewal;
        }

        public void setSystemRenewal(String systemRenewal) {
            this.systemRenewal = systemRenewal;
        }

        public String getSystemDayCap() {
            return systemDayCap;
        }

        public void setSystemDayCap(String systemDayCap) {
            this.systemDayCap = systemDayCap;
        }

        public String getPricingUnit() {
            return pricingUnit;
        }

        public void setPricingUnit(String pricingUnit) {
            this.pricingUnit = pricingUnit;
        }

        public String getFreeTime() {
            return freeTime;
        }

        public void setFreeTime(String freeTime) {
            this.freeTime = freeTime;
        }

        public String getCustomUnitPrice() {
            return customUnitPrice;
        }

        public void setCustomUnitPrice(String customUnitPrice) {
            this.customUnitPrice = customUnitPrice;
        }

        public String getCustomUnitCap() {
            return customUnitCap;
        }

        public void setCustomUnitCap(String customUnitCap) {
            this.customUnitCap = customUnitCap;
        }

        public String getStoreShareRatio() {
            return storeShareRatio;
        }

        public void setStoreShareRatio(String storeShareRatio) {
            this.storeShareRatio = storeShareRatio;
        }

        public String getEmployeeShareRatio() {
            return employeeShareRatio;
        }

        public void setEmployeeShareRatio(String employeeShareRatio) {
            this.employeeShareRatio = employeeShareRatio;
        }

        public String getDeviceShareRatio() {
            return deviceShareRatio;
        }

        public void setDeviceShareRatio(String deviceShareRatio) {
            this.deviceShareRatio = deviceShareRatio;
        }

        public String getMerchantShareRatio() {
            return merchantShareRatio;
        }

        public void setMerchantShareRatio(String merchantShareRatio) {
            this.merchantShareRatio = merchantShareRatio;
        }
    }
}
