package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/3/29.
 */
public class Fragment2Model implements Serializable {
    /**
     * storeNum : 10
     * waitVisitedNum : 10
     * waitInstallNum : 10
     * money : 10000
     * waitVisited : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:16:59"}]
     * waitTransferred : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:16:59"}]
     * waitShow : [{"id":"id","name":"喜来登酒店","createdAt":"2021-04-28 16:16:59"}]
     * stores : [{"id":"1","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"2","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"3","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"4","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"5","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"6","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"7","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"8","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"9","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"10","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"11","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"12","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"13","image":"md.png","title":"第一家","deviceNum":"0","address":"重庆市沙坪坝区"},{"id":"14","image":"a.png","title":"乐高早教","deviceNum":"0","address":null},{"id":"15","image":"a.png","title":"乐高早教","deviceNum":"0","address":null},{"id":"16","image":"a.png","title":"宇通酒店","deviceNum":"0","address":"重庆市江北区信达国际"},{"id":"17","image":"http://qqxfw8hz4.hn-bkt.clouddn.com/store%2Fd5b55904ea197961c5b321782ed98323.png?e=1619089273&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:xvukO0NU2PCO5JbzXxDtaBXnR8Q=","title":"阿斯顿马丁","deviceNum":"0","address":"阿斯顿1"},{"id":"18","image":"store/4422ec739b6b634540a9eef1277f25f8.png","title":"糖小糖","deviceNum":"0","address":"你就"},{"id":"19","image":"http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F161e4bda16efdf907846347ebf3605dc.png?e=1619495042&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:s45gMniysuMy-thQHy25XsVmreQ=","title":"门店名称周","deviceNum":"0","address":"阿斯顿马丁路德金"}]
     */

    private String storeNumber;
    private String notVisitNumber;
    private String notInstallNumber;
    private String money;
    private List<WaitVisitedBean> waitVisited;
    private List<WaitTransferredBean> waitTransferred;
    private List<WaitShowBean> waitShow;
    private List<StoresBean> stores;

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getNotVisitNumber() {
        return notVisitNumber;
    }

    public void setNotVisitNumber(String notVisitNumber) {
        this.notVisitNumber = notVisitNumber;
    }

    public String getNotInstallNumber() {
        return notInstallNumber;
    }

    public void setNotInstallNumber(String notInstallNumber) {
        this.notInstallNumber = notInstallNumber;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<WaitVisitedBean> getWaitVisited() {
        return waitVisited;
    }

    public void setWaitVisited(List<WaitVisitedBean> waitVisited) {
        this.waitVisited = waitVisited;
    }

    public List<WaitTransferredBean> getWaitTransferred() {
        return waitTransferred;
    }

    public void setWaitTransferred(List<WaitTransferredBean> waitTransferred) {
        this.waitTransferred = waitTransferred;
    }

    public List<WaitShowBean> getWaitShow() {
        return waitShow;
    }

    public void setWaitShow(List<WaitShowBean> waitShow) {
        this.waitShow = waitShow;
    }

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class WaitVisitedBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:16:59
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

    public static class WaitTransferredBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:16:59
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

    public static class WaitShowBean {
        /**
         * id : id
         * name : 喜来登酒店
         * createdAt : 2021-04-28 16:16:59
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

    public static class StoresBean {
        /**
         * id : 1
         * image : md.png
         * title : 第一家
         * deviceNum : 0
         * address : 重庆市沙坪坝区
         */

        private String id;
        private String image;
        private String title;
        private String deviceNum;
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
    }
}
