package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/16.
 */
public class AddWorkListModel implements Serializable {
    private List<WorkOrderTypeBean> workOrderType;

    public List<WorkOrderTypeBean> getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(List<WorkOrderTypeBean> workOrderType) {
        this.workOrderType = workOrderType;
    }

    public static class WorkOrderTypeBean {
        /**
         * key : 1
         * val : 网络断网
         */

        private String key;
        private String val;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }
}
