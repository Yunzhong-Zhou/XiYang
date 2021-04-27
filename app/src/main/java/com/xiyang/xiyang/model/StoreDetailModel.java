package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/23.
 */
public class StoreDetailModel implements Serializable {
    /**
     * id : 19
     * merchantId : 27
     * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F161e4bda16efdf907846347ebf3605dc.png?e=1619495042&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:s45gMniysuMy-thQHy25XsVmreQ=
     * deviceNum : 0
     * deviceOnlineNum : 0
     * deviceOffLineNum : 0
     * money : 0.00
     * base : {"name":"门店名称周","contact":"阿斯顿马丁","contactMobile":"1586445457588","city":"","address":"阿斯顿马丁路德金","level":"A级","slag":"头部","merchantId":"27","merchantName":"商户名称周","instudy":null,"businessHours":"00:00~24:00","region":"华北大区","signName":"bd周","signType":"直营","bdName":"bd周","bdType":"直营","bdmName":"bdm周","cmName":"cm周","rmName":"大区周","createdAt":"2021-04-27 10:44:03","onlineAt":"","status":"0","statusTitle":"合作中","isTransfter":"否","installStatus":"待安装"}
     * device : {"bind":"0","onlineTotal":"0","onlineNow":"0","offline":"0","lastBind":"","waitAdd":"10台","waitRecycled":"1台","waidTransfer":"2台"}
     * revenueInformation : {"orderNum":"","todayMoney":"","todayOrderNum":"","lastMonthSalesDays":"","lastMonthOnline":"","thisMonthSalesDays":"","thisMonthOnline":"","visitDays":"","thisMonthVisit":"是","thisMonthVisitAt":"","lastMonthMark":"","latestHeadWaistLogo":""}
     * fees : {"scale":"20%","unitPrice":"1","systemCapping":"5000","systemUnitPrice":"1500","storeUnitPrice":"0.00","storeCapping":"0.00","freeTime":"5分钟","systemPrice":"1000","systemRenewal":"1500"}
     */

    private String id;
    private String merchantId;
    private String image;
    private String deviceNum;
    private String deviceOnlineNum;
    private String deviceOffLineNum;
    private String money;
    private BaseBean base;
    private DeviceBean device;
    private RevenueInformationBean revenueInformation;
    private FeesBean fees;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceOnlineNum() {
        return deviceOnlineNum;
    }

    public void setDeviceOnlineNum(String deviceOnlineNum) {
        this.deviceOnlineNum = deviceOnlineNum;
    }

    public String getDeviceOffLineNum() {
        return deviceOffLineNum;
    }

    public void setDeviceOffLineNum(String deviceOffLineNum) {
        this.deviceOffLineNum = deviceOffLineNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public BaseBean getBase() {
        return base;
    }

    public void setBase(BaseBean base) {
        this.base = base;
    }

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    public RevenueInformationBean getRevenueInformation() {
        return revenueInformation;
    }

    public void setRevenueInformation(RevenueInformationBean revenueInformation) {
        this.revenueInformation = revenueInformation;
    }

    public FeesBean getFees() {
        return fees;
    }

    public void setFees(FeesBean fees) {
        this.fees = fees;
    }

    public static class BaseBean implements Serializable {
        /**
         * name : 门店名称周
         * contact : 阿斯顿马丁
         * contactMobile : 1586445457588
         * city :
         * address : 阿斯顿马丁路德金
         * level : A级
         * slag : 头部
         * merchantId : 27
         * merchantName : 商户名称周
         * instudy : null
         * businessHours : 00:00~24:00
         * region : 华北大区
         * signName : bd周
         * signType : 直营
         * bdName : bd周
         * bdType : 直营
         * bdmName : bdm周
         * cmName : cm周
         * rmName : 大区周
         * createdAt : 2021-04-27 10:44:03
         * onlineAt :
         * status : 0
         * statusTitle : 合作中
         * isTransfter : 否
         * installStatus : 待安装
         */

        private String name;
        private String contact;
        private String contactMobile;
        private String city;
        private String address;
        private String level;
        private String slag;
        private String merchantId;
        private String merchantName;
        private String instudy;
        private String businessHours;
        private String region;
        private String signName;
        private String signType;
        private String bdName;
        private String bdType;
        private String bdmName;
        private String cmName;
        private String rmName;
        private String createdAt;
        private String onlineAt;
        private String status;
        private String statusTitle;
        private String isTransfter;
        private String installStatus;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getContactMobile() {
            return contactMobile;
        }

        public void setContactMobile(String contactMobile) {
            this.contactMobile = contactMobile;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getSlag() {
            return slag;
        }

        public void setSlag(String slag) {
            this.slag = slag;
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

        public String getInstudy() {
            return instudy;
        }

        public void setInstudy(String instudy) {
            this.instudy = instudy;
        }

        public String getBusinessHours() {
            return businessHours;
        }

        public void setBusinessHours(String businessHours) {
            this.businessHours = businessHours;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getSignName() {
            return signName;
        }

        public void setSignName(String signName) {
            this.signName = signName;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getBdName() {
            return bdName;
        }

        public void setBdName(String bdName) {
            this.bdName = bdName;
        }

        public String getBdType() {
            return bdType;
        }

        public void setBdType(String bdType) {
            this.bdType = bdType;
        }

        public String getBdmName() {
            return bdmName;
        }

        public void setBdmName(String bdmName) {
            this.bdmName = bdmName;
        }

        public String getCmName() {
            return cmName;
        }

        public void setCmName(String cmName) {
            this.cmName = cmName;
        }

        public String getRmName() {
            return rmName;
        }

        public void setRmName(String rmName) {
            this.rmName = rmName;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getOnlineAt() {
            return onlineAt;
        }

        public void setOnlineAt(String onlineAt) {
            this.onlineAt = onlineAt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusTitle() {
            return statusTitle;
        }

        public void setStatusTitle(String statusTitle) {
            this.statusTitle = statusTitle;
        }

        public String getIsTransfter() {
            return isTransfter;
        }

        public void setIsTransfter(String isTransfter) {
            this.isTransfter = isTransfter;
        }

        public String getInstallStatus() {
            return installStatus;
        }

        public void setInstallStatus(String installStatus) {
            this.installStatus = installStatus;
        }
    }

    public static class DeviceBean implements Serializable {
        /**
         * bind : 0
         * onlineTotal : 0
         * onlineNow : 0
         * offline : 0
         * lastBind :
         * waitAdd : 10台
         * waitRecycled : 1台
         * waidTransfer : 2台
         */

        private String bind;
        private String onlineTotal;
        private String onlineNow;
        private String offline;
        private String lastBind;
        private String waitAdd;
        private String waitRecycled;
        private String waidTransfer;

        public String getBind() {
            return bind;
        }

        public void setBind(String bind) {
            this.bind = bind;
        }

        public String getOnlineTotal() {
            return onlineTotal;
        }

        public void setOnlineTotal(String onlineTotal) {
            this.onlineTotal = onlineTotal;
        }

        public String getOnlineNow() {
            return onlineNow;
        }

        public void setOnlineNow(String onlineNow) {
            this.onlineNow = onlineNow;
        }

        public String getOffline() {
            return offline;
        }

        public void setOffline(String offline) {
            this.offline = offline;
        }

        public String getLastBind() {
            return lastBind;
        }

        public void setLastBind(String lastBind) {
            this.lastBind = lastBind;
        }

        public String getWaitAdd() {
            return waitAdd;
        }

        public void setWaitAdd(String waitAdd) {
            this.waitAdd = waitAdd;
        }

        public String getWaitRecycled() {
            return waitRecycled;
        }

        public void setWaitRecycled(String waitRecycled) {
            this.waitRecycled = waitRecycled;
        }

        public String getWaidTransfer() {
            return waidTransfer;
        }

        public void setWaidTransfer(String waidTransfer) {
            this.waidTransfer = waidTransfer;
        }
    }

    public static class RevenueInformationBean implements Serializable {
        /**
         * orderNum :
         * todayMoney :
         * todayOrderNum :
         * lastMonthSalesDays :
         * lastMonthOnline :
         * thisMonthSalesDays :
         * thisMonthOnline :
         * visitDays :
         * thisMonthVisit : 是
         * thisMonthVisitAt :
         * lastMonthMark :
         * latestHeadWaistLogo :
         */

        private String orderNum;
        private String todayMoney;
        private String todayOrderNum;
        private String lastMonthSalesDays;
        private String lastMonthOnline;
        private String thisMonthSalesDays;
        private String thisMonthOnline;
        private String visitDays;
        private String thisMonthVisit;
        private String thisMonthVisitAt;
        private String lastMonthMark;
        private String latestHeadWaistLogo;

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getTodayMoney() {
            return todayMoney;
        }

        public void setTodayMoney(String todayMoney) {
            this.todayMoney = todayMoney;
        }

        public String getTodayOrderNum() {
            return todayOrderNum;
        }

        public void setTodayOrderNum(String todayOrderNum) {
            this.todayOrderNum = todayOrderNum;
        }

        public String getLastMonthSalesDays() {
            return lastMonthSalesDays;
        }

        public void setLastMonthSalesDays(String lastMonthSalesDays) {
            this.lastMonthSalesDays = lastMonthSalesDays;
        }

        public String getLastMonthOnline() {
            return lastMonthOnline;
        }

        public void setLastMonthOnline(String lastMonthOnline) {
            this.lastMonthOnline = lastMonthOnline;
        }

        public String getThisMonthSalesDays() {
            return thisMonthSalesDays;
        }

        public void setThisMonthSalesDays(String thisMonthSalesDays) {
            this.thisMonthSalesDays = thisMonthSalesDays;
        }

        public String getThisMonthOnline() {
            return thisMonthOnline;
        }

        public void setThisMonthOnline(String thisMonthOnline) {
            this.thisMonthOnline = thisMonthOnline;
        }

        public String getVisitDays() {
            return visitDays;
        }

        public void setVisitDays(String visitDays) {
            this.visitDays = visitDays;
        }

        public String getThisMonthVisit() {
            return thisMonthVisit;
        }

        public void setThisMonthVisit(String thisMonthVisit) {
            this.thisMonthVisit = thisMonthVisit;
        }

        public String getThisMonthVisitAt() {
            return thisMonthVisitAt;
        }

        public void setThisMonthVisitAt(String thisMonthVisitAt) {
            this.thisMonthVisitAt = thisMonthVisitAt;
        }

        public String getLastMonthMark() {
            return lastMonthMark;
        }

        public void setLastMonthMark(String lastMonthMark) {
            this.lastMonthMark = lastMonthMark;
        }

        public String getLatestHeadWaistLogo() {
            return latestHeadWaistLogo;
        }

        public void setLatestHeadWaistLogo(String latestHeadWaistLogo) {
            this.latestHeadWaistLogo = latestHeadWaistLogo;
        }
    }

    public static class FeesBean implements Serializable {
        /**
         * scale : 20%
         * unitPrice : 1
         * systemCapping : 5000
         * systemUnitPrice : 1500
         * storeUnitPrice : 0.00
         * storeCapping : 0.00
         * freeTime : 5分钟
         * systemPrice : 1000
         * systemRenewal : 1500
         */

        private String scale;
        private String unitPrice;
        private String systemCapping;
        private String systemUnitPrice;
        private String storeUnitPrice;
        private String storeCapping;
        private String freeTime;
        private String systemPrice;
        private String systemRenewal;

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getSystemCapping() {
            return systemCapping;
        }

        public void setSystemCapping(String systemCapping) {
            this.systemCapping = systemCapping;
        }

        public String getSystemUnitPrice() {
            return systemUnitPrice;
        }

        public void setSystemUnitPrice(String systemUnitPrice) {
            this.systemUnitPrice = systemUnitPrice;
        }

        public String getStoreUnitPrice() {
            return storeUnitPrice;
        }

        public void setStoreUnitPrice(String storeUnitPrice) {
            this.storeUnitPrice = storeUnitPrice;
        }

        public String getStoreCapping() {
            return storeCapping;
        }

        public void setStoreCapping(String storeCapping) {
            this.storeCapping = storeCapping;
        }

        public String getFreeTime() {
            return freeTime;
        }

        public void setFreeTime(String freeTime) {
            this.freeTime = freeTime;
        }

        public String getSystemPrice() {
            return systemPrice;
        }

        public void setSystemPrice(String systemPrice) {
            this.systemPrice = systemPrice;
        }

        public String getSystemRenewal() {
            return systemRenewal;
        }

        public void setSystemRenewal(String systemRenewal) {
            this.systemRenewal = systemRenewal;
        }
    }
}
