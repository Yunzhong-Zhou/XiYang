package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/5/8.
 */
public class SubordinateModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        boolean isXuanZhong = false;

        public boolean isXuanZhong() {
            return isXuanZhong;
        }

        public void setXuanZhong(boolean xuanZhong) {
            isXuanZhong = xuanZhong;
        }

        /**
         * id : 71
         * name : bdm周
         * head : null
         * mobile : null
         * address :
         * money : 0.00
         * deviceNum : 30
         * storeNum : 30
         * bdNum : 2
         * bdmNum : 0
         */

        private String id;
        private String name;
        private String head;
        private String mobile;
        private String address;
        private String money;
        private String deviceNum;
        private String storeNum;
        private String bdNum;
        private String bdmNum;

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

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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
    }
}
