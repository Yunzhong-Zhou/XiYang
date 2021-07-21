package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/3/29.
 */
public class Fragment3Model implements Serializable {
    /**
     * totalNum : 0
     * upLineNum : 0
     * onLineNum : 0
     * offLineNum : 0
     * waitingInstallList : [{"id":"1415218496647860224","storeName":"门店名称1-1","createTime":"2021-07-14 15:55:58","status":1},{"id":"1414776614658641920","storeName":"门店名称1-1","createTime":null,"status":1}]
     * waitingRecycleList : [{"id":"1415218496647860224","storeName":"门店名称1-1","createTime":"2021-07-14 15:55:58","status":1}]
     * waitingSwapList : [{"id":"1415218496647860224","storeName":"门店名称1-1","createTime":"2021-07-14 15:55:58","status":1}]
     * deviceList : [{"id":"1410512101939744768","aliyunStatus":null,"hostName":"hostTestdevice1","storeId":"1413329610791325696","storeImage":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png","storeName":"门店名称1-1","storeAddress":"才有意义唱歌找不到大不大","totalRevenue":null}]
     */

    private String totalNum;
    private String upLineNum;
    private String onLineNum;
    private String offLineNum;
    private List<WaitingInstallListBean> waitingInstallList;
    private List<WaitingRecycleListBean> waitingRecycleList;
    private List<WaitingSwapListBean> waitingSwapList;
    private List<DeviceListBean> deviceList;

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

    public List<WaitingInstallListBean> getWaitingInstallList() {
        return waitingInstallList;
    }

    public void setWaitingInstallList(List<WaitingInstallListBean> waitingInstallList) {
        this.waitingInstallList = waitingInstallList;
    }

    public List<WaitingRecycleListBean> getWaitingRecycleList() {
        return waitingRecycleList;
    }

    public void setWaitingRecycleList(List<WaitingRecycleListBean> waitingRecycleList) {
        this.waitingRecycleList = waitingRecycleList;
    }

    public List<WaitingSwapListBean> getWaitingSwapList() {
        return waitingSwapList;
    }

    public void setWaitingSwapList(List<WaitingSwapListBean> waitingSwapList) {
        this.waitingSwapList = waitingSwapList;
    }

    public List<DeviceListBean> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DeviceListBean> deviceList) {
        this.deviceList = deviceList;
    }

    public static class WaitingInstallListBean {
        /**
         * id : 1415218496647860224
         * storeName : 门店名称1-1
         * createTime : 2021-07-14 15:55:58
         * status : 1
         */

        private String id;
        private String storeName;
        private String createTime;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class WaitingRecycleListBean {
        /**
         * id : 1415218496647860224
         * storeName : 门店名称1-1
         * createTime : 2021-07-14 15:55:58
         * status : 1
         */

        private String id;
        private String storeName;
        private String createTime;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class WaitingSwapListBean {
        /**
         * id : 1415218496647860224
         * storeName : 门店名称1-1
         * createTime : 2021-07-14 15:55:58
         * status : 1
         */

        private String id;
        private String storeName;
        private String createTime;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class DeviceListBean {
        /**
         * id : 1410512101939744768
         * aliyunStatus : null
         * hostName : hostTestdevice1
         * storeId : 1413329610791325696
         * storeImage : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png
         * storeName : 门店名称1-1
         * storeAddress : 才有意义唱歌找不到大不大
         * totalRevenue : null
         */

        private String id;
        private String aliyunStatus;
        private String hostName;
        private String storeId;
        private String storeImage;
        private String storeName;
        private String storeAddress;
        private String totalRevenue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAliyunStatus() {
            return aliyunStatus;
        }

        public void setAliyunStatus(String aliyunStatus) {
            this.aliyunStatus = aliyunStatus;
        }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStoreImage() {
            return storeImage;
        }

        public void setStoreImage(String storeImage) {
            this.storeImage = storeImage;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getTotalRevenue() {
            return totalRevenue;
        }

        public void setTotalRevenue(String totalRevenue) {
            this.totalRevenue = totalRevenue;
        }
    }
}
