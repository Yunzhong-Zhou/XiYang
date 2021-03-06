package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/25.
 */
public class ApproveContractListModel implements Serializable {
    /**
     * records : [{"id":"1411986771147943937","type":"merchant_sign","typeTitle":"商户签约","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/03/16253009550771625300772843.png","status":0,"statusTitle":"需要审核","createTime":"2021-07-05 17:54:14","name":"商户名称"},{"id":"1411989826388029442","type":"merchant_sign","typeTitle":"商户签约","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/05/16254795519841625479367281.png","status":0,"statusTitle":"需要审核","createTime":"2021-07-05 18:06:23","name":"商户名称1"}]
     * total : 2
     * size : 10
     * current : 1
     * orders : []
     * optimizeCountSql : true
     * hitCount : false
     * countId : null
     * maxLimit : null
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
         * id : 1411986771147943937
         * type : merchant_sign
         * typeTitle : 商户签约
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/03/16253009550771625300772843.png
         * status : 0
         * statusTitle : 需要审核
         * createTime : 2021-07-05 17:54:14
         * name : 商户名称
         */

        private String id;
        private String type;
        private String typeTitle;
        private String image;
        private String status;
        private String statusTitle;
        private String createTime;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
