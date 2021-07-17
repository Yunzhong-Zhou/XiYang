package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/22.
 */
public class ShopDetailModel implements Serializable {
    /**
     * base : {"id":"1415133648994308096","name":"测试商户1","account":"19000000006","companyName":"公司名称1","contactPhone":"19000000006","contactName":"阿斯顿马丁","insduty":null,"city":null,"address":"阿斯顿马丁路德金","logoUrl":"","licenseNo":"12345677777655","certificateUrl":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/14/16262299988521626229796507.png"}
     * countData : {"deviceNum":0,"storeNum":1,"money":0,"profit":0}
     * applyData : {"inviteCode":null,"inviteType":null,"applyAt":null}
     * signData : {"contract":"56a95e48-858e-4bfd-85cc-86ba4a660bf9","sole":1,"renewalTime":null,"renewalPeriod":3,"userName":"黄BD","signType":"merchant_sign","verifyedAt":null}
     * bdData : {"name":"黄BD","type":null}
     */

    private BaseBean base;
    private CountDataBean countData;
    private ApplyDataBean applyData;
    private SignDataBean signData;
    private BdDataBean bdData;

    public BaseBean getBase() {
        return base;
    }

    public void setBase(BaseBean base) {
        this.base = base;
    }

    public CountDataBean getCountData() {
        return countData;
    }

    public void setCountData(CountDataBean countData) {
        this.countData = countData;
    }

    public ApplyDataBean getApplyData() {
        return applyData;
    }

    public void setApplyData(ApplyDataBean applyData) {
        this.applyData = applyData;
    }

    public SignDataBean getSignData() {
        return signData;
    }

    public void setSignData(SignDataBean signData) {
        this.signData = signData;
    }

    public BdDataBean getBdData() {
        return bdData;
    }

    public void setBdData(BdDataBean bdData) {
        this.bdData = bdData;
    }

    public static class BaseBean {
        /**
         * id : 1415133648994308096
         * name : 测试商户1
         * account : 19000000006
         * companyName : 公司名称1
         * contactPhone : 19000000006
         * contactName : 阿斯顿马丁
         * insduty : null
         * city : null
         * address : 阿斯顿马丁路德金
         * logoUrl :
         * licenseNo : 12345677777655
         * certificateUrl : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/14/16262299988521626229796507.png
         */

        private String id;
        private String name;
        private String account;
        private String companyName;
        private String contactPhone;
        private String contactName;
        private String insduty;
        private String city;
        private String address;
        private String logoUrl;
        private String licenseNo;
        private String certificateUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getInsduty() {
            return insduty;
        }

        public void setInsduty(String insduty) {
            this.insduty = insduty;
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

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public String getCertificateUrl() {
            return certificateUrl;
        }

        public void setCertificateUrl(String certificateUrl) {
            this.certificateUrl = certificateUrl;
        }
    }

    public static class CountDataBean {
        /**
         * deviceNum : 0
         * storeNum : 1
         * money : 0
         * profit : 0
         */

        private String deviceNum;
        private String storeNum;
        private String money;
        private String profit;

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public String getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(String storeNum) {
            this.storeNum = storeNum;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getProfit() {
            return profit;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }
    }

    public static class ApplyDataBean {
        /**
         * inviteCode : null
         * inviteType : null
         * applyAt : null
         */

        private String inviteCode;
        private String inviteType;
        private String applyAt;

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getInviteType() {
            return inviteType;
        }

        public void setInviteType(String inviteType) {
            this.inviteType = inviteType;
        }

        public String getApplyAt() {
            return applyAt;
        }

        public void setApplyAt(String applyAt) {
            this.applyAt = applyAt;
        }
    }

    public static class SignDataBean {
        /**
         * contract : 56a95e48-858e-4bfd-85cc-86ba4a660bf9
         * sole : 1
         * renewalTime : null
         * renewalPeriod : 3
         * userName : 黄BD
         * signType : merchant_sign
         * verifyedAt : null
         */

        private String contract;
        private String sole;
        private String renewalTime;
        private String renewalPeriod;
        private String userName;
        private String signType;
        private String verifyedAt;

        public String getContract() {
            return contract;
        }

        public void setContract(String contract) {
            this.contract = contract;
        }

        public String getSole() {
            return sole;
        }

        public void setSole(String sole) {
            this.sole = sole;
        }

        public String getRenewalTime() {
            return renewalTime;
        }

        public void setRenewalTime(String renewalTime) {
            this.renewalTime = renewalTime;
        }

        public String getRenewalPeriod() {
            return renewalPeriod;
        }

        public void setRenewalPeriod(String renewalPeriod) {
            this.renewalPeriod = renewalPeriod;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getVerifyedAt() {
            return verifyedAt;
        }

        public void setVerifyedAt(String verifyedAt) {
            this.verifyedAt = verifyedAt;
        }
    }

    public static class BdDataBean {
        /**
         * name : 黄BD
         * type : null
         */

        private String name;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
