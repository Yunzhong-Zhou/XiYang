package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/22.
 */
public class ShopDetailModel implements Serializable {
    /**
     * id : 21
     * name : 阿斯顿马丁
     * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F3e1d4ce08e17545ebba5de382bf2c603.png?e=1619066410&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:aE_L0oN60nU8SDRRAwVagLrzwbE=
     * deviceNum : 30
     * address : 啊水电费阿斯顿
     * status : 1
     * statusTitle : 待签约
     * money : 30000
     * base : {"name":"万豪酒店","account":"万豪酒店","companyName":"万豪酒店","contactPhone":"1582323","contactName":"万豪","licenseNo":"xxxxx","insduty":"酒店","city":"重庆","address":"美丽阳光3期","level":"A级","slag":"头部","source":"","toPublic":"","isCard":"","logoUrl":"","countData":{"storeNum":"0","deviceNum":"0","money":"0","profit":"0"},"applyData":{"inviteCode":"444","inviteType":"外部","applyAt":""},"signData":{"contract":"203434","sole":"是","renewalTime":"2021-04-22 12:05:38","renewalPeriod":"2年","userName":"张三","signType":"直营","verifyedAt":"2021-04-22 12:05:38"},"bdData":{"name":"hsm","type":"直营"}}
     * contract : [{"id":"1","name":"签约合同","status":"1","statusTitle":"已通过","createdAt":"2021-04-22 12:05:38"}]
     * store : [{"id":"1","name":"万豪酒店","image":"a.png","deviceNum":"30","address":"重庆市沙坪坝","money":"30000"}]
     */

    private String id;
    private String name;
    private String image;
    private String deviceNum;
    private String address;
    private String status;
    private String statusTitle;
    private String money;
    private BaseBean base;
    private List<ContractBean> contract;
    private List<StoreBean> store;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<ContractBean> getContract() {
        return contract;
    }

    public void setContract(List<ContractBean> contract) {
        this.contract = contract;
    }

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public static class BaseBean {
        /**
         * name : 万豪酒店
         * account : 万豪酒店
         * companyName : 万豪酒店
         * contactPhone : 1582323
         * contactName : 万豪
         * licenseNo : xxxxx
         * insduty : 酒店
         * city : 重庆
         * address : 美丽阳光3期
         * level : A级
         * slag : 头部
         * source :
         * toPublic :
         * isCard :
         * logoUrl :
         * countData : {"storeNum":"0","deviceNum":"0","money":"0","profit":"0"}
         * applyData : {"inviteCode":"444","inviteType":"外部","applyAt":""}
         * signData : {"contract":"203434","sole":"是","renewalTime":"2021-04-22 12:05:38","renewalPeriod":"2年","userName":"张三","signType":"直营","verifyedAt":"2021-04-22 12:05:38"}
         * bdData : {"name":"hsm","type":"直营"}
         */

        private String name;
        private String account;
        private String companyName;
        private String contactPhone;
        private String contactName;
        private String licenseNo;
        private String insduty;
        private String city;
        private String address;
        private String level;
        private String slag;
        private String source;
        private String toPublic;
        private String isCard;
        private String logoUrl;
        private CountDataBean countData;
        private ApplyDataBean applyData;
        private SignDataBean signData;
        private BdDataBean bdData;

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

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getToPublic() {
            return toPublic;
        }

        public void setToPublic(String toPublic) {
            this.toPublic = toPublic;
        }

        public String getIsCard() {
            return isCard;
        }

        public void setIsCard(String isCard) {
            this.isCard = isCard;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
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

        public static class CountDataBean {
            /**
             * storeNum : 0
             * deviceNum : 0
             * money : 0
             * profit : 0
             */

            private String storeNum;
            private String deviceNum;
            private String money;
            private String profit;

            public String getStoreNum() {
                return storeNum;
            }

            public void setStoreNum(String storeNum) {
                this.storeNum = storeNum;
            }

            public String getDeviceNum() {
                return deviceNum;
            }

            public void setDeviceNum(String deviceNum) {
                this.deviceNum = deviceNum;
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
             * inviteCode : 444
             * inviteType : 外部
             * applyAt :
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
             * contract : 203434
             * sole : 是
             * renewalTime : 2021-04-22 12:05:38
             * renewalPeriod : 2年
             * userName : 张三
             * signType : 直营
             * verifyedAt : 2021-04-22 12:05:38
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
             * name : hsm
             * type : 直营
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

    public static class ContractBean {
        /**
         * id : 1
         * name : 签约合同
         * status : 1
         * statusTitle : 已通过
         * createdAt : 2021-04-22 12:05:38
         */

        private String id;
        private String name;
        private String status;
        private String statusTitle;
        private String createdAt;

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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class StoreBean {
        /**
         * id : 1
         * name : 万豪酒店
         * image : a.png
         * deviceNum : 30
         * address : 重庆市沙坪坝
         * money : 30000
         */

        private String id;
        private String name;
        private String image;
        private String deviceNum;
        private String address;
        private String money;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
