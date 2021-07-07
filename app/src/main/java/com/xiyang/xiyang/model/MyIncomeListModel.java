package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/7/6.
 */
public class MyIncomeListModel implements Serializable {
    /**
     * size : 0
     * total : 0
     * current : 0
     * records : [{"id":"string","logType":0,"availableAmount":0,"createTimeStr":"string"}]
     * pages : 0
     * searchCount : true
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
         * id : string
         * logType : 0
         * availableAmount : 0
         * createTimeStr : string
         */

        private String id;
        private String logType;
        private String availableAmount;
        private String createTimeStr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogType() {
            return logType;
        }

        public void setLogType(String logType) {
            this.logType = logType;
        }

        public String getAvailableAmount() {
            return availableAmount;
        }

        public void setAvailableAmount(String availableAmount) {
            this.availableAmount = availableAmount;
        }

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }
    }
}
