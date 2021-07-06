package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/25.
 */
public class ApproveContractListModel implements Serializable {
    /**
     * records : [{"id":"1411214988161572866","type":"1","status":0,"statusTitle":"需要审核","createTime":"2021-07-03 14:47:27","name":"11111111"},{"id":"1411217586331574274","type":"1","status":0,"statusTitle":"需要审核","createTime":"2021-07-03 14:57:46","name":"11111111"},{"id":"1411218392921366529","type":"1","status":0,"statusTitle":"需要审核","createTime":"2021-07-03 15:00:43","name":"11111111"},{"id":"1411218726238511106","type":"1","status":1,"statusTitle":"审核通过","createTime":"2021-07-03 15:02:18","name":"11111111"},{"id":"1411950920137392130","type":"1","status":1,"statusTitle":"审核通过","createTime":"2021-07-05 15:31:47","name":"何总商户"},{"id":"1411960617112387585","type":"1","status":1,"statusTitle":"审核通过","createTime":"2021-07-05 16:10:19","name":"何总商户"},{"id":"1411960849556520961","type":"1","status":1,"statusTitle":"审核通过","createTime":"2021-07-05 16:11:14","name":"何总商户"},{"id":"1411986771147943937","type":"1","status":0,"statusTitle":"需要审核","createTime":"2021-07-05 17:54:14","name":"商户名称"},{"id":"1411989826388029442","type":"1","status":0,"statusTitle":"需要审核","createTime":"2021-07-05 18:06:23","name":"商户名称1"}]
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
         * id : 1411214988161572866
         * type : 1
         * status : 0
         * statusTitle : 需要审核
         * createTime : 2021-07-03 14:47:27
         * name : 11111111
         */

        private String id;
        private String type;
        private int status;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
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
