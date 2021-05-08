package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/8.
 */
public class Fragment1Model_m implements Serializable {
    /**
     * base : {"id":"71","bdNum":"2","bdmNum":"0","cmNum":"0","deviceNum":"30","storeNum":"30","money":"0.00"}
     */

    private BaseBean base;

    public BaseBean getBase() {
        return base;
    }

    public void setBase(BaseBean base) {
        this.base = base;
    }

    public static class BaseBean {
        /**
         * id : 71
         * bdNum : 2
         * bdmNum : 0
         * cmNum : 0
         * deviceNum : 30
         * storeNum : 30
         * money : 0.00
         */

        private String id;
        private String bdNum;
        private String bdmNum;
        private String cmNum;
        private String deviceNum;
        private String storeNum;
        private String money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBdNum() {
            return bdNum;
        }

        public void setBdNum(String bdNum) {
            this.bdNum = bdNum;
        }

        public String getBdmNum() {
            return bdmNum;
        }

        public void setBdmNum(String bdmNum) {
            this.bdmNum = bdmNum;
        }

        public String getCmNum() {
            return cmNum;
        }

        public void setCmNum(String cmNum) {
            this.cmNum = cmNum;
        }

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
    }
}
