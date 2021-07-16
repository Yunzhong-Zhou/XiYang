package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/8.
 */
public class SubordinateModel implements Serializable {
    /**
     * userId : 1415121041232207873
     * organId : 1415121040921829377
     * scopeOrganId : 1415121041248985089
     * username : 19000000003
     * organName : CM
     * avatar : null
     * name : 黄CM1
     * parentOrganId : 1414885152384065537
     * parentOrganName : RM
     * parentUserId : 1414885152698638337
     * parentUserName : 黄RM2
     * email : null
     * phone : null
     * type : null
     * gender : null
     * joinTime : null
     * birthday : null
     * idCard : null
     * bankCard : null
     * address : null
     * political : null
     * education : null
     * fireTime : null
     * areaIds : null
     * statisticInfo : {"organId":"1415121040921829377","organName":null,"areaNumber":1,"cmNumber":0,"bdmNumber":1,"bdNumber":1,"merchantNumber":2,"storeNumber":1,"deviceNumber":0,"money":0}
     */

    private String userId;
    private String organId;
    private String scopeOrganId;
    private String username;
    private String organName;
    private String avatar;
    private String name;
    private String parentOrganId;
    private String parentOrganName;
    private String parentUserId;
    private String parentUserName;
    private String email;
    private String phone;
    private String type;
    private String gender;
    private String joinTime;
    private String birthday;
    private String idCard;
    private String bankCard;
    private String address;
    private String political;
    private String education;
    private String fireTime;
    private String areaIds;
    private StatisticInfoBean statisticInfo;

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

    public String getParentOrganName() {
        return parentOrganName;
    }

    public void setParentOrganName(String parentOrganName) {
        this.parentOrganName = parentOrganName;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getParentUserName() {
        return parentUserName;
    }

    public void setParentUserName(String parentUserName) {
        this.parentUserName = parentUserName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getFireTime() {
        return fireTime;
    }

    public void setFireTime(String fireTime) {
        this.fireTime = fireTime;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public StatisticInfoBean getStatisticInfo() {
        return statisticInfo;
    }

    public void setStatisticInfo(StatisticInfoBean statisticInfo) {
        this.statisticInfo = statisticInfo;
    }

    public static class StatisticInfoBean {
        /**
         * organId : 1415121040921829377
         * organName : null
         * areaNumber : 1
         * cmNumber : 0
         * bdmNumber : 1
         * bdNumber : 1
         * merchantNumber : 2
         * storeNumber : 1
         * deviceNumber : 0
         * money : 0
         */

        private String organId;
        private String organName;
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

        public String getOrganName() {
            return organName;
        }

        public void setOrganName(String organName) {
            this.organName = organName;
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
