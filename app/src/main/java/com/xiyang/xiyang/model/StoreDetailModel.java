package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/23.
 */
public class StoreDetailModel implements Serializable {
    /**
     * id : 17
     * merchantId : 21
     * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2Fd5b55904ea197961c5b321782ed98323.png?e=1619089273&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:xvukO0NU2PCO5JbzXxDtaBXnR8Q=
     * deviceNum : 0
     * deviceOlineNum : 0
     * deviceOffLineNum : 0
     * money : 0.00
     * base : {"status":"0","statusTitle":"合作中","name":"阿斯顿马丁","address":"阿斯顿1","createdAt":"2021-04-22 18:01:49"}
     * owner : {"name":null,"mobile":"18306043086"}
     * bd : {"name":"admin3","mobile":"18306043086"}
     * pricing : {"unitPrice":"1","systemPrice":"1000","systemRenewal":"1500","systemCapping":"5000","storeUnitPrice":"0.00","storeCapping":"0.00"}
     */

    private String id;
    private String merchantId;
    private String image;
    private String deviceNum;
    private String deviceOlineNum;
    private String deviceOffLineNum;
    private String money;
    private BaseBean base;
    private OwnerBean owner;
    private BdBean bd;
    private PricingBean pricing;

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

    public String getDeviceOlineNum() {
        return deviceOlineNum;
    }

    public void setDeviceOlineNum(String deviceOlineNum) {
        this.deviceOlineNum = deviceOlineNum;
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

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public BdBean getBd() {
        return bd;
    }

    public void setBd(BdBean bd) {
        this.bd = bd;
    }

    public PricingBean getPricing() {
        return pricing;
    }

    public void setPricing(PricingBean pricing) {
        this.pricing = pricing;
    }

    public static class BaseBean {
        /**
         * status : 0
         * statusTitle : 合作中
         * name : 阿斯顿马丁
         * address : 阿斯顿1
         * createdAt : 2021-04-22 18:01:49
         */

        private String status;
        private String statusTitle;
        private String name;
        private String address;
        private String createdAt;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class OwnerBean {
        /**
         * name : null
         * mobile : 18306043086
         */

        private String name;
        private String mobile;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public static class BdBean {
        /**
         * name : admin3
         * mobile : 18306043086
         */

        private String name;
        private String mobile;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public static class PricingBean {
        /**
         * unitPrice : 1
         * systemPrice : 1000
         * systemRenewal : 1500
         * systemCapping : 5000
         * storeUnitPrice : 0.00
         * storeCapping : 0.00
         */

        private String unitPrice;
        private String systemPrice;
        private String systemRenewal;
        private String systemCapping;
        private String storeUnitPrice;
        private String storeCapping;

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
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

        public String getSystemCapping() {
            return systemCapping;
        }

        public void setSystemCapping(String systemCapping) {
            this.systemCapping = systemCapping;
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
    }
}
