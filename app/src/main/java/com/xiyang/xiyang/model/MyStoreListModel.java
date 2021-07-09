package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/23.
 */
public class MyStoreListModel implements Serializable {
    /**
     * records : [{"id":"1412691587682668544","name":"门店名称","address":"北新区洪湖西路51号","visitStatus":1}]
     * total : 1
     * size : 10
     * current : 1
     * orders : []
     * optimizeCountSql : true
     * hitCount : false
     * searchCount : true
     * pages : 1
     */

    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 1412691587682668544
         * name : 门店名称
         * address : 北新区洪湖西路51号
         * visitStatus : 1
         */

        private String id;
        private String name;
        private String address;
        private String visitStatus;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(String visitStatus) {
            this.visitStatus = visitStatus;
        }
    }
}
