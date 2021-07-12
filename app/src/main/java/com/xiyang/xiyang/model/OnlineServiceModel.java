package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2017/9/12.
 * 在线留言列表
 */

public class OnlineServiceModel implements Serializable {
    /**
     * records : [{"message":"test","type":1,"createTime":"2021-06-10 09:25:16","l":2},{"message":"123","type":2,"createTime":"2021-07-07 15:57:14","l":2},{"message":"123","type":2,"createTime":"2021-07-07 17:06:41","l":2},{"message":"123","type":2,"createTime":"2021-07-07 17:32:22","l":2},{"message":"234","type":1,"createTime":"2021-07-10 11:10:51","l":2},{"message":"1234","type":1,"createTime":"2021-07-10 11:10:49","l":2},{"message":"123","type":2,"createTime":"2021-07-08 08:52:12","l":2},{"message":"1234","type":1,"createTime":"2021-07-10 11:10:47","l":2},{"message":"11111","type":1,"createTime":"2021-07-09 18:11:17","l":2},{"message":"你好李艳","type":1,"createTime":"2021-07-09 18:11:29","l":2}]
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
         * message : test
         * type : 1
         * createTime : 2021-06-10 09:25:16
         * l : 2
         */

        private String message;
        private int type;
        private String createTime;
        private int l;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getL() {
            return l;
        }

        public void setL(int l) {
            this.l = l;
        }
    }
}
