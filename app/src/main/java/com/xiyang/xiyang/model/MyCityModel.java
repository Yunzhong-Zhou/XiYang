package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/5/11.
 */
public class MyCityModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 210102
         * name : 沈阳市和平区
         * cmName :
         * merchantNum : 0
         * storeNum : 0
         * deviceNum : 0
         */

        private String id;
        private String name;
        private String cmName;
        private String merchantNum;
        private String storeNum;
        private String deviceNum;

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

        public String getCmName() {
            return cmName;
        }

        public void setCmName(String cmName) {
            this.cmName = cmName;
        }

        public String getMerchantNum() {
            return merchantNum;
        }

        public void setMerchantNum(String merchantNum) {
            this.merchantNum = merchantNum;
        }

        public String getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(String storeNum) {
            this.storeNum = storeNum;
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }
    }
}
