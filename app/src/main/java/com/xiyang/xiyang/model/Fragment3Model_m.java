package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/25.
 */
public class Fragment3Model_m implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 27
         * name : 商户名称周
         * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F20581441f6eb25ec75b27e20dca59f8c.png?e=1619342284&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:sC6kK7h4cjLHNKHxttjKNA_QoXc=
         * typeTite : 商户签约
         * type : merchant_sign
         * status : 0
         * statusTitle : 已提交
         * createdAt : 2021-04-25 16:22:08
         */

        private String id;
        private String name;
        private String image;
        private String typeTite;
        private String type;
        private String status;
        private String statusTitle;
        private String createdAt;

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

        public String getTypeTite() {
            return typeTite;
        }

        public void setTypeTite(String typeTite) {
            this.typeTite = typeTite;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
