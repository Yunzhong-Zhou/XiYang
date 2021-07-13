package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class AffairListModel implements Serializable {
    /**
     * records : [{"id":"1413042378897494016","name":" 黄仕明测试门店","type":1,"typeTitle":"新增设备","image":"17100000001.png","status":1,"statusTitle":"待处理","deviceNum":10},{"id":"1414776614721556480","name":"门店名称1","type":1,"typeTitle":"新增设备","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png","status":1,"statusTitle":"待处理","deviceNum":10}]
     * total : 2
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
         * id : 1413042378897494016
         * name :  黄仕明测试门店
         * type : 1
         * typeTitle : 新增设备
         * image : 17100000001.png
         * status : 1
         * statusTitle : 待处理
         * deviceNum : 10
         */

        private String id;
        private String name;
        private int type;
        private String typeTitle;
        private String image;
        private int status;
        private String statusTitle;
        private int deviceNum;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
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

        public int getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(int deviceNum) {
            this.deviceNum = deviceNum;
        }
    }
}
