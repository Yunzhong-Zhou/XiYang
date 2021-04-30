package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/19.
 * 通用模型
 */
public class CommonModel implements Serializable {

    /**
     * 通用列表
     */
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String id;
        private String name;
        private String parentId;

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

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }


    /**
     * 添加工单-故障
     */
    private List<WorkOrderTypeBean> workOrderType;

    public List<WorkOrderTypeBean> getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(List<WorkOrderTypeBean> workOrderType) {
        this.workOrderType = workOrderType;
    }

    public static class WorkOrderTypeBean {
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

    /**
     * 处理工单-结果
     */
    private List<StatusBean> status;

    public List<StatusBean> getStatus() {
        return status;
    }

    public void setStatus(List<StatusBean> status) {
        this.status = status;
    }

    public static class StatusBean {
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

    /**
     * 签约期限
     */
    private List<WorkOrderTypeBean> renewalPeriod;

    public List<WorkOrderTypeBean> getRenewalPeriod() {
        return renewalPeriod;
    }

    public void setRenewalPeriod(List<WorkOrderTypeBean> renewalPeriod) {
        this.renewalPeriod = renewalPeriod;
    }

    /**
     * 减少原因
     */
    private List<WorkOrderTypeBean> merchantRecoverReason;

    public List<WorkOrderTypeBean> getMerchantRecoverReason() {
        return merchantRecoverReason;
    }

    public void setMerchantRecoverReason(List<WorkOrderTypeBean> merchantRecoverReason) {
        this.merchantRecoverReason = merchantRecoverReason;
    }

    /**
     * 取消原因
     */
    private List<WorkOrderTypeBean> merchantCancelReason;

    public List<WorkOrderTypeBean> getMerchantCancelReason() {
        return merchantCancelReason;
    }

    public void setMerchantCancelReason(List<WorkOrderTypeBean> merchantCancelReason) {
        this.merchantCancelReason = merchantCancelReason;
    }
}
