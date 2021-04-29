package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class AffairListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 18
         * name : 新增设备
         * storeName : 门店名称周
         * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F161e4bda16efdf907846347ebf3605dc.png?e=1619495042&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:s45gMniysuMy-thQHy25XsVmreQ=
         * deviceNum : 10
         * statusTitle : 处理中
         */

        private String id;
        private String name;
        private String storeName;
        private String image;
        private String deviceNum;
        private String statusTitle;

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

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
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

        public String getStatusTitle() {
            return statusTitle;
        }

        public void setStatusTitle(String statusTitle) {
            this.statusTitle = statusTitle;
        }
    }
}
