package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class MyContractModel implements Serializable {
    /**
     * records : [{"id":"1411986770802905088","name":"商户名称","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/03/16253009550771625300772843.png","status":1,"statusTitle":"默认状态","createdAt":"2021-07-05 17:54:14"},{"id":"1411989826286260224","name":"商户名称1","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/05/16254795519841625479367281.png","status":1,"statusTitle":"默认状态","createdAt":"2021-07-05 18:06:23"},{"id":"1412963273288060928","name":"商户名称1","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/07/16256385343791625638348687.png","status":2,"statusTitle":"处理中","createdAt":"2021-07-08 10:34:31"},{"id":"1413389770008170496","name":"商户名称","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/03/16253009550771625300772843.png","status":2,"statusTitle":"处理中","createdAt":"2021-07-09 14:49:15"},{"id":"1413786573702565888","name":"商户名称1","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/05/16254795519841625479367281.png","status":1,"statusTitle":"默认状态","createdAt":"2021-07-10 17:06:01"},{"id":"1413786841714397184","name":"商户名称1","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/05/16254795519841625479367281.png","status":1,"statusTitle":"默认状态","createdAt":"2021-07-10 17:07:05"}]
     * total : 6
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
         * id : 1415557230765412352
         * name : 测试商户1
         * image :
         * contractType : device_add
         * typeTitle : 设备添加
         * status : 1
         * statusTitle : 待处理
         * createdAt : 2021-07-15 14:21:58
         */

        private String id;
        private String name;
        private String image;
        private String contractType;
        private String typeTitle;
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

        public String getContractType() {
            return contractType;
        }

        public void setContractType(String contractType) {
            this.contractType = contractType;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
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
