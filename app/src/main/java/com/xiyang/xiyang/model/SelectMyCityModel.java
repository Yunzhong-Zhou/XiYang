package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/19.
 */
public class SelectMyCityModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 210100
         * name : 沈阳市
         * parentId : 210000
         */
        private boolean isxuanzhong = false;
        private String id;
        private String name;
        private String parentId;

        public boolean isIsxuanzhong() {
            return isxuanzhong;
        }

        public void setIsxuanzhong(boolean isxuanzhong) {
            this.isxuanzhong = isxuanzhong;
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

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
