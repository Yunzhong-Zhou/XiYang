package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/3/29.
 */
public class Fragment3Model implements Serializable {
    /**
     * totalNum : 10
     * onlineTotalNum : 10
     * offlineNum : 10
     * waitInstall : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:23:25"}]
     * waitRecovery : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:23:25"}]
     * waitSwap : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:23:25"}]
     * devices : [{"id":"1","name":"万豪酒店","image":"a.png","deviceSn":"30","address":"重庆市沙坪坝","money":"30000"}]
     */

    private String totalNum;
    private String upLineNum;
    private String onLineNum;
    private String offLineNum;
    private List<WaitInstallBean> waitInstall;
    private List<WaitRecoveryBean> waitRecovery;
    private List<WaitSwapBean> waitSwap;
    private List<DevicesBean> devices;

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getUpLineNum() {
        return upLineNum;
    }

    public void setUpLineNum(String upLineNum) {
        this.upLineNum = upLineNum;
    }

    public String getOnLineNum() {
        return onLineNum;
    }

    public void setOnLineNum(String onLineNum) {
        this.onLineNum = onLineNum;
    }

    public String getOffLineNum() {
        return offLineNum;
    }

    public void setOffLineNum(String offLineNum) {
        this.offLineNum = offLineNum;
    }

    public List<WaitInstallBean> getWaitInstall() {
        return waitInstall;
    }

    public void setWaitInstall(List<WaitInstallBean> waitInstall) {
        this.waitInstall = waitInstall;
    }

    public List<WaitRecoveryBean> getWaitRecovery() {
        return waitRecovery;
    }

    public void setWaitRecovery(List<WaitRecoveryBean> waitRecovery) {
        this.waitRecovery = waitRecovery;
    }

    public List<WaitSwapBean> getWaitSwap() {
        return waitSwap;
    }

    public void setWaitSwap(List<WaitSwapBean> waitSwap) {
        this.waitSwap = waitSwap;
    }

    public List<DevicesBean> getDevices() {
        return devices;
    }

    public void setDevices(List<DevicesBean> devices) {
        this.devices = devices;
    }

    public static class WaitInstallBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:23:25
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

    public static class WaitRecoveryBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:23:25
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

    public static class WaitSwapBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:23:25
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

    public static class DevicesBean {
        /**
         * id : 1
         * name : 万豪酒店
         * image : a.png
         * deviceSn : 30
         * address : 重庆市沙坪坝
         * money : 30000
         */

        private String id;
        private String name;
        private String image;
        private String deviceSn;
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

        public String getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(String deviceSn) {
            this.deviceSn = deviceSn;
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
