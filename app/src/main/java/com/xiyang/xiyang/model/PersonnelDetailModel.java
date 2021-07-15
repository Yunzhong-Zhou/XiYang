package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/25.
 */
public class PersonnelDetailModel implements Serializable {
    /**
     * purchaseApplicantAvatar :
     * purchaseApplicantName : 黄CM1
     * purchaseApplicantPhoneNumber : 19000000003
     * purchaseApplicantOrganCode : CM
     * status : 1
     * sn : null
     * warehouseName : 测试仓库1
     * purchaseQuantity : 100
     * deliveryTime : 2021-07-15 00:00:00
     * createTime : 2021-07-15 12:51:52
     */

    private String purchaseApplyLogId;
    private String purchaseApplicantAvatar;
    private String purchaseApplicantName;
    private String purchaseApplicantPhoneNumber;
    private String purchaseApplicantOrganCode;
    private int status;
    private String sn;
    private String warehouseName;
    private String purchaseQuantity;
    private String deliveryTime;
    private String createTime;
    private List<ListBean> list;

    public String getPurchaseApplyLogId() {
        return purchaseApplyLogId;
    }

    public void setPurchaseApplyLogId(String purchaseApplyLogId) {
        this.purchaseApplyLogId = purchaseApplyLogId;
    }

    public String getPurchaseApplicantAvatar() {
        return purchaseApplicantAvatar;
    }

    public void setPurchaseApplicantAvatar(String purchaseApplicantAvatar) {
        this.purchaseApplicantAvatar = purchaseApplicantAvatar;
    }

    public String getPurchaseApplicantName() {
        return purchaseApplicantName;
    }

    public void setPurchaseApplicantName(String purchaseApplicantName) {
        this.purchaseApplicantName = purchaseApplicantName;
    }

    public String getPurchaseApplicantPhoneNumber() {
        return purchaseApplicantPhoneNumber;
    }

    public void setPurchaseApplicantPhoneNumber(String purchaseApplicantPhoneNumber) {
        this.purchaseApplicantPhoneNumber = purchaseApplicantPhoneNumber;
    }

    public String getPurchaseApplicantOrganCode() {
        return purchaseApplicantOrganCode;
    }

    public void setPurchaseApplicantOrganCode(String purchaseApplicantOrganCode) {
        this.purchaseApplicantOrganCode = purchaseApplicantOrganCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(String purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * approvalAvatar : string
         * approvalImages : string
         * approvalName : string
         * approvalRemark : string
         * approvalStatus : 0
         * approvalTime : 2021-07-15T05:39:50.672Z
         */

        private String approvalAvatar;
        private String approvalImages;
        private String approvalName;
        private String approvalRemark;
        private int approvalStatus;
        private String approvalTime;

        public String getApprovalAvatar() {
            return approvalAvatar;
        }

        public void setApprovalAvatar(String approvalAvatar) {
            this.approvalAvatar = approvalAvatar;
        }

        public String getApprovalImages() {
            return approvalImages;
        }

        public void setApprovalImages(String approvalImages) {
            this.approvalImages = approvalImages;
        }

        public String getApprovalName() {
            return approvalName;
        }

        public void setApprovalName(String approvalName) {
            this.approvalName = approvalName;
        }

        public String getApprovalRemark() {
            return approvalRemark;
        }

        public void setApprovalRemark(String approvalRemark) {
            this.approvalRemark = approvalRemark;
        }

        public int getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(int approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public String getApprovalTime() {
            return approvalTime;
        }

        public void setApprovalTime(String approvalTime) {
            this.approvalTime = approvalTime;
        }
    }
}
