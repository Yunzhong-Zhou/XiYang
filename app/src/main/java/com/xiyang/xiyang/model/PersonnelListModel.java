package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/5/26.
 */
public class PersonnelListModel implements Serializable {
    /**
     * records : [{"id":"1415534555934363648","purchaseApplyLogId":"1415534556806778880","sn":"CGSQ20210715125152154GWNT","status":1,"adminId":"1415121041232207873","name":"黄CM1","num":100,"fetchAt":"2021-07-15 00:00:00"}]
     * total : 0
     * size : 10
     * current : 1
     * orders : []
     * optimizeCountSql : true
     * hitCount : false
     * countId : null
     * maxLimit : null
     * searchCount : true
     * pages : 0
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
         * id : 1415534555934363648
         * purchaseApplyLogId : 1415534556806778880
         * sn : CGSQ20210715125152154GWNT
         * status : 1
         * adminId : 1415121041232207873
         * name : 黄CM1
         * num : 100
         * fetchAt : 2021-07-15 00:00:00
         */

        private String id;
        private String purchaseApplyLogId;
        private String sn;
        private int status;
        private String adminId;
        private String name;
        private String num;
        private String fetchAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPurchaseApplyLogId() {
            return purchaseApplyLogId;
        }

        public void setPurchaseApplyLogId(String purchaseApplyLogId) {
            this.purchaseApplyLogId = purchaseApplyLogId;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getFetchAt() {
            return fetchAt;
        }

        public void setFetchAt(String fetchAt) {
            this.fetchAt = fetchAt;
        }
    }
}
