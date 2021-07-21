package com.xiyang.xiyang.model;

import com.google.gson.annotations.SerializedName;

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
    private String status;
    private String sn;
    private String warehouseName;
    private String purchaseQuantity;
    private String deliveryTime;
    private String createTime;
    private List<ListBean> list;
    /**
     * id : 1417042777162883073
     * adminId : 1415121041232207873
     * adminName : 黄CM1
     * oldParentOrganId : 1414885152384065537
     * oldParentId : 1414885152698638337
     * oldParentName : 黄RM2
     * oldOrganId : 1415121040921829377
     * oldOrganCode : BD
     * newParentId : 0
     * nextAdminId : 0
     * nextOrganId : 0
     * type : 6
     * applyAdminId : 1414885152698638337
     * applyAdminName : 黄RM2
     * applyAdminScopeOrganId : 1414885152698638338
     * crossRegional : 1
     * dataFollow : 0
     * above : 0
     * extra :
     * updateTime : 2021-07-19 16:45:00
     * delFlag : 0
     * auditInfos : [{"id":"1417042777175465985","sn":"1417042777149149184","adminId":"10","adminName":"分公司一","createTime":"2021-07-19 16:45:00","updateTime":"2021-07-19 16:45:00"}]
     */

    private String id;
    private String adminId;
    private String adminName;
    private String adminPhone;
    private String adminAvatar;
    private String adminOrganCode;
    private String oldParentOrganId;
    private String oldParentId;
    private String oldParentName;
    private String oldOrganId;
    private String oldOrganCode;
    private String newParentId;
    private String newParentName;
    private String newOrganCode;
    private String nextAdminId;
    private String nextOrganId;
    private String type;
    private String applyAdminId;
    private String applyAdminName;
    private String applyAdminScopeOrganId;
    private String crossRegional;
    private String dataFollow;
    private String above;
    private String extra;
    private String updateTime;
    private String delFlag;
    private List<AuditInfosBean> auditInfos;

    public String getAdminOrganCode() {
        return adminOrganCode;
    }

    public void setAdminOrganCode(String adminOrganCode) {
        this.adminOrganCode = adminOrganCode;
    }

    public String getAdminAvatar() {
        return adminAvatar;
    }

    public void setAdminAvatar(String adminAvatar) {
        this.adminAvatar = adminAvatar;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getNewOrganCode() {
        return newOrganCode;
    }

    public void setNewOrganCode(String newOrganCode) {
        this.newOrganCode = newOrganCode;
    }

    public String getNewParentName() {
        return newParentName;
    }

    public void setNewParentName(String newParentName) {
        this.newParentName = newParentName;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getOldParentOrganId() {
        return oldParentOrganId;
    }

    public void setOldParentOrganId(String oldParentOrganId) {
        this.oldParentOrganId = oldParentOrganId;
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

    public String getOldOrganCode() {
        return oldOrganCode;
    }

    public void setOldOrganCode(String oldOrganCode) {
        this.oldOrganCode = oldOrganCode;
    }

    public String getNewParentId() {
        return newParentId;
    }

    public void setNewParentId(String newParentId) {
        this.newParentId = newParentId;
    }

    public String getNextAdminId() {
        return nextAdminId;
    }

    public void setNextAdminId(String nextAdminId) {
        this.nextAdminId = nextAdminId;
    }

    public String getNextOrganId() {
        return nextOrganId;
    }

    public void setNextOrganId(String nextOrganId) {
        this.nextOrganId = nextOrganId;
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

    public String getApplyAdminScopeOrganId() {
        return applyAdminScopeOrganId;
    }

    public void setApplyAdminScopeOrganId(String applyAdminScopeOrganId) {
        this.applyAdminScopeOrganId = applyAdminScopeOrganId;
    }

    public String getCrossRegional() {
        return crossRegional;
    }

    public void setCrossRegional(String crossRegional) {
        this.crossRegional = crossRegional;
    }

    public String getDataFollow() {
        return dataFollow;
    }

    public void setDataFollow(String dataFollow) {
        this.dataFollow = dataFollow;
    }

    public String getAbove() {
        return above;
    }

    public void setAbove(String above) {
        this.above = above;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<AuditInfosBean> getAuditInfos() {
        return auditInfos;
    }

    public void setAuditInfos(List<AuditInfosBean> auditInfos) {
        this.auditInfos = auditInfos;
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
        private String approvalStatus;
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

        public String getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(String approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public String getApprovalTime() {
            return approvalTime;
        }

        public void setApprovalTime(String approvalTime) {
            this.approvalTime = approvalTime;
        }
    }

    public static class AuditInfosBean {
        /**
         * id : 1417042777175465985
         * sn : 1417042777149149184
         * adminId : 10
         * adminName : 分公司一
         * createTime : 2021-07-19 16:45:00
         * updateTime : 2021-07-19 16:45:00
         */

        private String id;
        @SerializedName("sn")
        private String snX;
        private String adminId;
        private String adminName;
        @SerializedName("createTime")
        private String createTimeX;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSnX() {
            return snX;
        }

        public void setSnX(String snX) {
            this.snX = snX;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getAdminName() {
            return adminName;
        }

        public void setAdminName(String adminName) {
            this.adminName = adminName;
        }

        public String getCreateTimeX() {
            return createTimeX;
        }

        public void setCreateTimeX(String createTimeX) {
            this.createTimeX = createTimeX;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
