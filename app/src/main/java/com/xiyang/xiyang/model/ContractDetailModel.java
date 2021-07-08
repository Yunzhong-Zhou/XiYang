package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class ContractDetailModel implements Serializable {
    /**
     * contractsVo : {"id":"1412963273288060928","contractNumber":"f7514dd5-8cd7-4840-bcfa-5964b23092f6","name":"商户名称1","image":null,"contractType":"merchant_sign","status":"1","statusTitle":null,"createdAt":null,"merchantsName":null,"signExpied":null,"isExclusive":null,"approvalTime":null,"contractsFile":null,"uploadQualification":null}
     * workFlowApplylogOperateVo : [{"userName":"1200020020002","auditTime":null,"auditStat":0,"reason":null}]
     * workFlowApplyLogVo : {"id":"1412963273666646017","type":"merchant_sign","typeTitle":null,"image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/07/16256385343791625638348687.png","status":0,"statusTitle":null,"createTime":"2021-07-08 10:34:31","name":"商户名称1"}
     */

    private ContractsVoBean contractsVo;
    private WorkFlowApplyLogVoBean workFlowApplyLogVo;
    private List<WorkFlowApplylogOperateVoBean> workFlowApplylogOperateVo;

    public ContractsVoBean getContractsVo() {
        return contractsVo;
    }

    public void setContractsVo(ContractsVoBean contractsVo) {
        this.contractsVo = contractsVo;
    }

    public WorkFlowApplyLogVoBean getWorkFlowApplyLogVo() {
        return workFlowApplyLogVo;
    }

    public void setWorkFlowApplyLogVo(WorkFlowApplyLogVoBean workFlowApplyLogVo) {
        this.workFlowApplyLogVo = workFlowApplyLogVo;
    }

    public List<WorkFlowApplylogOperateVoBean> getWorkFlowApplylogOperateVo() {
        return workFlowApplylogOperateVo;
    }

    public void setWorkFlowApplylogOperateVo(List<WorkFlowApplylogOperateVoBean> workFlowApplylogOperateVo) {
        this.workFlowApplylogOperateVo = workFlowApplylogOperateVo;
    }

    public static class ContractsVoBean {
        /**
         * id : 1412963273288060928
         * contractNumber : f7514dd5-8cd7-4840-bcfa-5964b23092f6
         * name : 商户名称1
         * image : null
         * contractType : merchant_sign
         * status : 1
         * statusTitle : null
         * createdAt : null
         * merchantsName : null
         * signExpied : null
         * isExclusive : null
         * approvalTime : null
         * contractsFile : null
         * uploadQualification : null
         */

        private String id;
        private String contractNumber;
        private String name;
        private String image;
        private String contractType;
        private String status;
        private String statusTitle;
        private String createdAt;
        private String merchantsName;
        private String signExpied;
        private String isExclusive;
        private String approvalTime;
        private String contractsFile;
        private String uploadQualification;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContractNumber() {
            return contractNumber;
        }

        public void setContractNumber(String contractNumber) {
            this.contractNumber = contractNumber;
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

        public String getMerchantsName() {
            return merchantsName;
        }

        public void setMerchantsName(String merchantsName) {
            this.merchantsName = merchantsName;
        }

        public String getSignExpied() {
            return signExpied;
        }

        public void setSignExpied(String signExpied) {
            this.signExpied = signExpied;
        }

        public String getIsExclusive() {
            return isExclusive;
        }

        public void setIsExclusive(String isExclusive) {
            this.isExclusive = isExclusive;
        }

        public String getApprovalTime() {
            return approvalTime;
        }

        public void setApprovalTime(String approvalTime) {
            this.approvalTime = approvalTime;
        }

        public String getContractsFile() {
            return contractsFile;
        }

        public void setContractsFile(String contractsFile) {
            this.contractsFile = contractsFile;
        }

        public String getUploadQualification() {
            return uploadQualification;
        }

        public void setUploadQualification(String uploadQualification) {
            this.uploadQualification = uploadQualification;
        }
    }

    public static class WorkFlowApplyLogVoBean {
        /**
         * id : 1412963273666646017
         * type : merchant_sign
         * typeTitle : null
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/07/16256385343791625638348687.png
         * status : 0
         * statusTitle : null
         * createTime : 2021-07-08 10:34:31
         * name : 商户名称1
         */

        private String id;
        private String type;
        private String typeTitle;
        private String image;
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

    public static class WorkFlowApplylogOperateVoBean {
        /**
         * userName : 1200020020002
         * auditTime : null
         * auditStat : 0
         * reason : null
         */

        private String userName;
        private String auditTime;
        private String auditStat;
        private String reason;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public String getAuditStat() {
            return auditStat;
        }

        public void setAuditStat(String auditStat) {
            this.auditStat = auditStat;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
