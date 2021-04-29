package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class MyContractModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 38
         * contractType : 设备添加
         * name : 门店名称周
         * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F161e4bda16efdf907846347ebf3605dc.png?e=1619495042&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:s45gMniysuMy-thQHy25XsVmreQ=
         * status : 0
         * statusTitle :
         * createdAt : 2021-04-28 17:18:22
         */

        private String id;
        private String contractType;
        private String name;
        private String image;
        private String status;
        private String statusTitle;
        private String createdAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContractType() {
            return contractType;
        }

        public void setContractType(String contractType) {
            this.contractType = contractType;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }
}
