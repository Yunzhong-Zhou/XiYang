package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/16.
 */
public class WorkListDetailModel implements Serializable {
    /**
     * baseInfo : {"id":"21","type":"设备工单","storeName":"第一家","storeCover":"http://qqxfw8hz4.hn-bkt.clouddn.com/md.png?e=1618649168&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:xfv9ZN0s-R6nk3U8QyEHdipVX30=","storeAddres":"重庆是沙坪坝","problemType":"设备故障，连不上网","channel":"app/电话","createdUser":"张三","createdUserRole":"CM","createdUserCity":"重庆","createdAt":"2021-04-17 15:46:08","reamrk":"2021-04-17 15:46:08","images":["a.png","b.png"],"status":"1","statusTitle":"待接单"}
     * dealList : [{"id":"1","reamrk":"2021-04-17 15:46:08","createdAt":"2021-04-17 15:46:08","images":["a.png","b.png"],"status":"1","statusTitle":"处理完成"}]
     */

    private BaseInfoBean baseInfo;
    private List<DealListBean> dealList;

    public BaseInfoBean getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfoBean baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<DealListBean> getDealList() {
        return dealList;
    }

    public void setDealList(List<DealListBean> dealList) {
        this.dealList = dealList;
    }

    public static class BaseInfoBean {
        /**
         * id : 21
         * type : 设备工单
         * storeName : 第一家
         * storeCover : http://qqxfw8hz4.hn-bkt.clouddn.com/md.png?e=1618649168&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:xfv9ZN0s-R6nk3U8QyEHdipVX30=
         * storeAddres : 重庆是沙坪坝
         * problemType : 设备故障，连不上网
         * channel : app/电话
         * createdUser : 张三
         * createdUserRole : CM
         * createdUserCity : 重庆
         * createdAt : 2021-04-17 15:46:08
         * reamrk : 2021-04-17 15:46:08
         * images : ["a.png","b.png"]
         * status : 1
         * statusTitle : 待接单
         */

        private String id;
        private String type;
        private String storeName;
        private String storeCover;
        private String storeAddres;
        private String problemType;
        private String channel;
        private String createdUser;
        private String createdUserRole;
        private String createdUserCity;
        private String createdAt;
        private String reamrk;
        private String status;
        private String fetch;
        private String statusTitle;
        private List<String> images;

        public String getFetch() {
            return fetch;
        }

        public void setFetch(String fetch) {
            this.fetch = fetch;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreCover() {
            return storeCover;
        }

        public void setStoreCover(String storeCover) {
            this.storeCover = storeCover;
        }

        public String getStoreAddres() {
            return storeAddres;
        }

        public void setStoreAddres(String storeAddres) {
            this.storeAddres = storeAddres;
        }

        public String getProblemType() {
            return problemType;
        }

        public void setProblemType(String problemType) {
            this.problemType = problemType;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public String getCreatedUserRole() {
            return createdUserRole;
        }

        public void setCreatedUserRole(String createdUserRole) {
            this.createdUserRole = createdUserRole;
        }

        public String getCreatedUserCity() {
            return createdUserCity;
        }

        public void setCreatedUserCity(String createdUserCity) {
            this.createdUserCity = createdUserCity;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getReamrk() {
            return reamrk;
        }

        public void setReamrk(String reamrk) {
            this.reamrk = reamrk;
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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class DealListBean {
        /**
         * id : 1
         * reamrk : 2021-04-17 15:46:08
         * createdAt : 2021-04-17 15:46:08
         * images : ["a.png","b.png"]
         * status : 1
         * statusTitle : 处理完成
         */

        private String id;
        private String reamrk;
        private String createdAt;
        private String status;
        private String statusTitle;
        private List<String> images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReamrk() {
            return reamrk;
        }

        public void setReamrk(String reamrk) {
            this.reamrk = reamrk;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
