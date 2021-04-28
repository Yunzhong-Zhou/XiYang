package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/3/29.
 */
public class Fragment1Model implements Serializable {
    /**
     * manageMerchantNum : 10
     * signMerchantNum : 10
     * recommendMerchantNum : 10
     * money : 10000
     * waitSign : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:03:08"}]
     * waitCheck : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:03:08"}]
     * hasRefuse : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:03:08"}]
     * hasChecked : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:03:08"}]
     * merchants : [{"id":"1","name":"万豪酒店","image":"a.png","deviceNum":"30","address":"重庆市沙坪坝","status":"1","statusTitle":"已签约","money":"30000"}]
     */

    private String manageMerchantNum;
    private String signMerchantNum;
    private String recommendMerchantNum;
    private String money;
    private List<WaitSignBean> waitSign;
    private List<WaitCheckBean> waitCheck;
    private List<HasRefuseBean> hasRefuse;
    private List<HasCheckedBean> hasChecked;
    private List<MerchantsBean> merchants;

    public String getManageMerchantNum() {
        return manageMerchantNum;
    }

    public void setManageMerchantNum(String manageMerchantNum) {
        this.manageMerchantNum = manageMerchantNum;
    }

    public String getSignMerchantNum() {
        return signMerchantNum;
    }

    public void setSignMerchantNum(String signMerchantNum) {
        this.signMerchantNum = signMerchantNum;
    }

    public String getRecommendMerchantNum() {
        return recommendMerchantNum;
    }

    public void setRecommendMerchantNum(String recommendMerchantNum) {
        this.recommendMerchantNum = recommendMerchantNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<WaitSignBean> getWaitSign() {
        return waitSign;
    }

    public void setWaitSign(List<WaitSignBean> waitSign) {
        this.waitSign = waitSign;
    }

    public List<WaitCheckBean> getWaitCheck() {
        return waitCheck;
    }

    public void setWaitCheck(List<WaitCheckBean> waitCheck) {
        this.waitCheck = waitCheck;
    }

    public List<HasRefuseBean> getHasRefuse() {
        return hasRefuse;
    }

    public void setHasRefuse(List<HasRefuseBean> hasRefuse) {
        this.hasRefuse = hasRefuse;
    }

    public List<HasCheckedBean> getHasChecked() {
        return hasChecked;
    }

    public void setHasChecked(List<HasCheckedBean> hasChecked) {
        this.hasChecked = hasChecked;
    }

    public List<MerchantsBean> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<MerchantsBean> merchants) {
        this.merchants = merchants;
    }

    public static class WaitSignBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:03:08
         */

        private String id;
        private String name;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class WaitCheckBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:03:08
         */

        private String id;
        private String name;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class HasRefuseBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:03:08
         */

        private String id;
        private String name;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class HasCheckedBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:03:08
         */

        private String id;
        private String name;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class MerchantsBean {
        /**
         * id : 1
         * name : 万豪酒店
         * image : a.png
         * deviceNum : 30
         * address : 重庆市沙坪坝
         * status : 1
         * statusTitle : 已签约
         * money : 30000
         */

        private String id;
        private String name;
        private String image;
        private String deviceNum;
        private String address;
        private String status;
        private String statusTitle;
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
    }
}
