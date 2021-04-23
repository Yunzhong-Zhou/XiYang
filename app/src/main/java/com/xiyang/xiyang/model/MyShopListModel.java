package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/22.
 */
public class MyShopListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 21
         * name : 阿斯顿马丁
         * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F3e1d4ce08e17545ebba5de382bf2c603.png?e=1619066410&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:aE_L0oN60nU8SDRRAwVagLrzwbE=
         * deviceNum : 30
         * address : 110000120000130000
         * status : 1
         * statusTitle : 待签约
         * money : 30000
         */

        private String id;
        private String name;
        private String image;
        private String deviceNum;
        private String address;
        private String status;
        private String statusTitle;
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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
