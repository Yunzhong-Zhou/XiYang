package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/5/7.
 */
public class MyVisitListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 5
         * name : 门店名称周
         * type : 2
         * typeTitle : 上门拜访
         * visitedAt : 2021-05-07 10:22:17
         */

        private String id;
        private String name;
        private String type;
        private String typeTitle;
        private String visitedAt;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }

        public String getVisitedAt() {
            return visitedAt;
        }

        public void setVisitedAt(String visitedAt) {
            this.visitedAt = visitedAt;
        }
    }
}
