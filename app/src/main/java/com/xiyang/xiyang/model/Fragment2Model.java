package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/3/29.
 */
public class Fragment2Model implements Serializable {
    /**
     * storeNumber : 1
     * notVisitNumber : 0
     * notInstallNumber : 0
     * revenue : 0
     * waitVisitStores : [{"id":"1415158482327441408","name":"测试商户2"}]
     * waitTransferStores : [{"id":"1415158482327441408","name":"测试商户2"}]
     * waitContractStores : [{"id":"1415158482327441408","name":"测试商户2"}]
     * stores : [{"id":"1415195140754116608","storeSn":"080865599b3d49d8bd84aa5747a47e3c","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/14/16262437889431626243582286.png","name":"信达门店2","address":"黄山大道重庆高科总部广场西南侧约220米","userTypeId":"1415195140788797442","visitStatus":1,"contractStatus":1,"transferStatus":1}]
     */

    private String storeNumber;
    private String notVisitNumber;
    private String notInstallNumber;
    private String revenue;
    private List<WaitVisitStoresBean> waitVisitStores;
    private List<WaitTransferStoresBean> waitTransferStores;
    private List<WaitContractStoresBean> waitContractStores;
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

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public List<WaitVisitStoresBean> getWaitVisitStores() {
        return waitVisitStores;
    }

    public void setWaitVisitStores(List<WaitVisitStoresBean> waitVisitStores) {
        this.waitVisitStores = waitVisitStores;
    }

    public List<WaitTransferStoresBean> getWaitTransferStores() {
        return waitTransferStores;
    }

    public void setWaitTransferStores(List<WaitTransferStoresBean> waitTransferStores) {
        this.waitTransferStores = waitTransferStores;
    }

    public List<WaitContractStoresBean> getWaitContractStores() {
        return waitContractStores;
    }

    public void setWaitContractStores(List<WaitContractStoresBean> waitContractStores) {
        this.waitContractStores = waitContractStores;
    }

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class WaitVisitStoresBean {
        /**
         * id : 1415158482327441408
         * name : 测试商户2
         */

        private String id;
        private String name;
        private String createTime;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

    public static class WaitTransferStoresBean {
        /**
         * id : 1415158482327441408
         * name : 测试商户2
         */

        private String id;
        private String name;
        private String createTime;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

    public static class WaitContractStoresBean {
        /**
         * id : 1415158482327441408
         * name : 测试商户2
         */

        private String id;
        private String name;
        private String createTime;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

    public static class StoresBean {
        /**
         * id : 1415195140754116608
         * storeSn : 080865599b3d49d8bd84aa5747a47e3c
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/14/16262437889431626243582286.png
         * name : 信达门店2
         * address : 黄山大道重庆高科总部广场西南侧约220米
         * userTypeId : 1415195140788797442
         * visitStatus : 1
         * contractStatus : 1
         * transferStatus : 1
         */

        private String id;
        private String storeSn;
        private String image;
        private String name;
        private String address;
        private String userTypeId;
        private String visitStatus;
        private String contractStatus;
        private String transferStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoreSn() {
            return storeSn;
        }

        public void setStoreSn(String storeSn) {
            this.storeSn = storeSn;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getUserTypeId() {
            return userTypeId;
        }

        public void setUserTypeId(String userTypeId) {
            this.userTypeId = userTypeId;
        }

        public String getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(String visitStatus) {
            this.visitStatus = visitStatus;
        }

        public String getContractStatus() {
            return contractStatus;
        }

        public void setContractStatus(String contractStatus) {
            this.contractStatus = contractStatus;
        }

        public String getTransferStatus() {
            return transferStatus;
        }

        public void setTransferStatus(String transferStatus) {
            this.transferStatus = transferStatus;
        }
    }
}
