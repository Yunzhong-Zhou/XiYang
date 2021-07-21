package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/11.
 */
public class StaffDetailModel implements Serializable {
    /**
     * userId : 1415121041232207873
     * organId : 1415121040921829377
     * scopeOrganId : 1415121041248985089
     * username : 19000000003
     * organName : CM
     * avatar :
     * name : 黄CM1
     * parentOrganId : 1414885152384065537
     * phone : 19000000003
     * gender : 1
     * joinTime : 2021-07-13 00:00:00
     * statisticInfo : {"organId":"1415121040921829377","areaNumber":1,"cmNumber":0,"bdmNumber":1,"bdNumber":1,"merchantNumber":3,"storeNumber":3,"deviceNumber":0,"money":0}
     * availableMoney : 0
     * availableIndicators : 0
     * usedIndicators : 0
     * childCompany : 分公司一
     */

    private String userId;
    private String organId;
    private String scopeOrganId;
    private String username;
    private String organName;
    private String avatar;
    private String name;
    private String parentOrganId;
    private String phone;
    private String gender;
    private String joinTime;
    private StatisticInfoBean statisticInfo;
    private String availableMoney;
    private String availableIndicators;
    private String usedIndicators;
    private String childCompany;
    private String warehouseId;
    private String warehouseName;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getScopeOrganId() {
        return scopeOrganId;
    }

    public void setScopeOrganId(String scopeOrganId) {
        this.scopeOrganId = scopeOrganId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentOrganId() {
        return parentOrganId;
    }

    public void setParentOrganId(String parentOrganId) {
        this.parentOrganId = parentOrganId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public StatisticInfoBean getStatisticInfo() {
        return statisticInfo;
    }

    public void setStatisticInfo(StatisticInfoBean statisticInfo) {
        this.statisticInfo = statisticInfo;
    }

    public String getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(String availableMoney) {
        this.availableMoney = availableMoney;
    }

    public String getAvailableIndicators() {
        return availableIndicators;
    }

    public void setAvailableIndicators(String availableIndicators) {
        this.availableIndicators = availableIndicators;
    }

    public String getUsedIndicators() {
        return usedIndicators;
    }

    public void setUsedIndicators(String usedIndicators) {
        this.usedIndicators = usedIndicators;
    }

    public String getChildCompany() {
        return childCompany;
    }

    public void setChildCompany(String childCompany) {
        this.childCompany = childCompany;
    }

    public static class StatisticInfoBean {
        /**
         * organId : 1415121040921829377
         * areaNumber : 1
         * cmNumber : 0
         * bdmNumber : 1
         * bdNumber : 1
         * merchantNumber : 3
         * storeNumber : 3
         * deviceNumber : 0
         * money : 0
         */

        private String organId;
        private String areaNumber;
        private String cmNumber;
        private String bdmNumber;
        private String bdNumber;
        private String merchantNumber;
        private String storeNumber;
        private String deviceNumber;
        private String money;

        public String getOrganId() {
            return organId;
        }

        public void setOrganId(String organId) {
            this.organId = organId;
        }

        public String getAreaNumber() {
            return areaNumber;
        }

        public void setAreaNumber(String areaNumber) {
            this.areaNumber = areaNumber;
        }

        public String getCmNumber() {
            return cmNumber;
        }

        public void setCmNumber(String cmNumber) {
            this.cmNumber = cmNumber;
        }

        public String getBdmNumber() {
            return bdmNumber;
        }

        public void setBdmNumber(String bdmNumber) {
            this.bdmNumber = bdmNumber;
        }

        public String getBdNumber() {
            return bdNumber;
        }

        public void setBdNumber(String bdNumber) {
            this.bdNumber = bdNumber;
        }

        public String getMerchantNumber() {
            return merchantNumber;
        }

        public void setMerchantNumber(String merchantNumber) {
            this.merchantNumber = merchantNumber;
        }

        public String getStoreNumber() {
            return storeNumber;
        }

        public void setStoreNumber(String storeNumber) {
            this.storeNumber = storeNumber;
        }

        public String getDeviceNumber() {
            return deviceNumber;
        }

        public void setDeviceNumber(String deviceNumber) {
            this.deviceNumber = deviceNumber;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
