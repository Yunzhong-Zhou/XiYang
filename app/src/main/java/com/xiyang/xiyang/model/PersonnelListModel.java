package com.xiyang.xiyang.model;

import com.google.gson.annotations.SerializedName;

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
        private String status;
        private String adminId;
        private String name;
        private String num;
        private String fetchAt;
        /**
         * adminName : 黄BDM
         * oldParentId : 1415121041232207873
         * oldParentName : 黄CM1
         * oldOrganId : 1415121678435065858
         * newParentId : 1415121041232207873
         * newParentName : 黄CM1
         * nextAdminId : 0
         * type : 6
         * applyAdminId : 1414885152698638337
         * applyAdminName : 黄RM2
         * extra :
         * createTime : 2021-07-19 17:45:05
         * auditors : 6
         * status : PENDING
         */

        private String adminName;
        private String oldParentId;
        private String oldParentName;
        private String oldOrganId;
        private String newParentId;
        private String newParentName;
        private String nextAdminId;
        private String type;
        private String applyAdminId;
        private String applyAdminName;
        private String extra;
        private String createTime;
        private String auditors;
        /**
         * oldRegionIds : 130000
         * oldRegions : [{"id":"130000","name":"河北省","level":1}]
         * newRegionIds : 140000,150000
         * newRegions : [{"id":"140000","name":"山西省","level":1}]
         */

        private String oldRegionIds;
        private String newRegionIds;
        private List<OldRegionsBean> oldRegions;
        private List<NewRegionsBean> newRegions;
        /**
         * newOrganCode : BDM
         */
        private String oldOrganCode;
        private String newOrganCode;

        public String getOldOrganCode() {
            return oldOrganCode;
        }

        public void setOldOrganCode(String oldOrganCode) {
            this.oldOrganCode = oldOrganCode;
        }

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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

        public String getAdminName() {
            return adminName;
        }

        public void setAdminName(String adminName) {
            this.adminName = adminName;
        }

        public String getOldParentId() {
            return oldParentId;
        }

        public void setOldParentId(String oldParentId) {
            this.oldParentId = oldParentId;
        }

        public String getOldParentName() {
            return oldParentName;
        }

        public void setOldParentName(String oldParentName) {
            this.oldParentName = oldParentName;
        }

        public String getOldOrganId() {
            return oldOrganId;
        }

        public void setOldOrganId(String oldOrganId) {
            this.oldOrganId = oldOrganId;
        }

        public String getNewParentId() {
            return newParentId;
        }

        public void setNewParentId(String newParentId) {
            this.newParentId = newParentId;
        }

        public String getNewParentName() {
            return newParentName;
        }

        public void setNewParentName(String newParentName) {
            this.newParentName = newParentName;
        }

        public String getNextAdminId() {
            return nextAdminId;
        }

        public void setNextAdminId(String nextAdminId) {
            this.nextAdminId = nextAdminId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getApplyAdminId() {
            return applyAdminId;
        }

        public void setApplyAdminId(String applyAdminId) {
            this.applyAdminId = applyAdminId;
        }

        public String getApplyAdminName() {
            return applyAdminName;
        }

        public void setApplyAdminName(String applyAdminName) {
            this.applyAdminName = applyAdminName;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getAuditors() {
            return auditors;
        }

        public void setAuditors(String auditors) {
            this.auditors = auditors;
        }

        public String getOldRegionIds() {
            return oldRegionIds;
        }

        public void setOldRegionIds(String oldRegionIds) {
            this.oldRegionIds = oldRegionIds;
        }

        public String getNewRegionIds() {
            return newRegionIds;
        }

        public void setNewRegionIds(String newRegionIds) {
            this.newRegionIds = newRegionIds;
        }

        public List<OldRegionsBean> getOldRegions() {
            return oldRegions;
        }

        public void setOldRegions(List<OldRegionsBean> oldRegions) {
            this.oldRegions = oldRegions;
        }

        public List<NewRegionsBean> getNewRegions() {
            return newRegions;
        }

        public void setNewRegions(List<NewRegionsBean> newRegions) {
            this.newRegions = newRegions;
        }

        public String getNewOrganCode() {
            return newOrganCode;
        }

        public void setNewOrganCode(String newOrganCode) {
            this.newOrganCode = newOrganCode;
        }

        public static class OldRegionsBean {
            /**
             * id : 130000
             * name : 河北省
             * level : 1
             */

            @SerializedName("id")
            private String idX;
            @SerializedName("name")
            private String nameX;
            private int level;

            public String getIdX() {
                return idX;
            }

            public void setIdX(String idX) {
                this.idX = idX;
            }

            public String getNameX() {
                return nameX;
            }

            public void setNameX(String nameX) {
                this.nameX = nameX;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }
        }

        public static class NewRegionsBean {
            /**
             * id : 140000
             * name : 山西省
             * level : 1
             */

            @SerializedName("id")
            private String idX;
            @SerializedName("name")
            private String nameX;
            private int level;

            public String getIdX() {
                return idX;
            }

            public void setIdX(String idX) {
                this.idX = idX;
            }

            public String getNameX() {
                return nameX;
            }

            public void setNameX(String nameX) {
                this.nameX = nameX;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }
        }
    }
}
