package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/3/26.
 */
public class Fragment5Model implements Serializable {
    /**
     * userinfo : {"deviceNum":"0","merchantNum":"0","jobTitle":"BD","id":"1410143973189623809","avatar":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/05/16254487151381625448531949.png","storeNum":"0","totalRevenueMoney":"0"}
     */

    private UserinfoBean userinfo;

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * deviceNum : 0
         * merchantNum : 0
         * jobTitle : BD
         * id : 1410143973189623809
         * avatar : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/05/16254487151381625448531949.png
         * storeNum : 0
         * totalRevenueMoney : 0
         */

        private String deviceNum;
        private String merchantNum;
        private String jobTitle;
        private String id;
        private String avatar;
        private String storeNum;
        private String totalRevenueMoney;
        private String name;
        private String availableMoney;
        private String totalMoney;
        private String monthRevenueMoney;

        public String getAvailableMoney() {
            return availableMoney;
        }

        public void setAvailableMoney(String availableMoney) {
            this.availableMoney = availableMoney;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getMonthRevenueMoney() {
            return monthRevenueMoney;
        }

        public void setMonthRevenueMoney(String monthRevenueMoney) {
            this.monthRevenueMoney = monthRevenueMoney;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public String getMerchantNum() {
            return merchantNum;
        }

        public void setMerchantNum(String merchantNum) {
            this.merchantNum = merchantNum;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(String storeNum) {
            this.storeNum = storeNum;
        }

        public String getTotalRevenueMoney() {
            return totalRevenueMoney;
        }

        public void setTotalRevenueMoney(String totalRevenueMoney) {
            this.totalRevenueMoney = totalRevenueMoney;
        }
    }
}
