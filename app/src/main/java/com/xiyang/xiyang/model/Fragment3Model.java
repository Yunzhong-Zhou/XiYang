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
     * waitingInstallList : [{"id":"1415583044974284800","storeName":null,"createTime":1626336273000,"status":1}]
     * waitingRecycleList : [{"id":"1415583044974284800","storeName":null,"createTime":1626336273000,"status":1}]
     * waitingSwapList : [{"id":"1415583044974284800","storeName":null,"createTime":1626336273000,"status":1}]
     */

    private String totalNum;
    private String upLineNum;
    private String onLineNum;
    private String offLineNum;
    private List<WaitingInstallListBean> waitingInstallList;
    private List<WaitingRecycleListBean> waitingRecycleList;
    private List<WaitingSwapListBean> waitingSwapList;

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

    public static class WaitingInstallListBean {
        /**
         * id : 1415583044974284800
         * storeName : null
         * createTime : 1626336273000
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
         * id : 1415583044974284800
         * storeName : null
         * createTime : 1626336273000
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
         * id : 1415583044974284800
         * storeName : null
         * createTime : 1626336273000
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
}
