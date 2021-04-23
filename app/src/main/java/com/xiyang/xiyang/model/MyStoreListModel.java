package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/23.
 */
public class MyStoreListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 17
         * name : 阿斯顿马丁
         * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2Fd5b55904ea197961c5b321782ed98323.png?e=1619089273&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:xvukO0NU2PCO5JbzXxDtaBXnR8Q=
         * deviceNum : 0
         * address : 阿斯顿1
         * money : 0.00
         */

        private String id;
        private String name;
        private String image;
        private String deviceNum;
        private String address;
        private String money;
        private String visitStatus;

        public String getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(String visitStatus) {
            this.visitStatus = visitStatus;
        }

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
