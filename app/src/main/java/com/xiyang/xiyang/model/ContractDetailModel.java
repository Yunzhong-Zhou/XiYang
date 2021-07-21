package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class ContractDetailModel implements Serializable {
    /**
     * contractsVo : {"id":"1416981047563915264","contractNumber":"457a11e7-2558-4a2c-8c24-bf9fee543413","storeId":"1415855164790804480","name":null,"image":null,"contractType":null,"typeTitle":null,"status":1,"statusTitle":null,"createdAt":null,"signedAt":null,"signedDateLimit":null,"isOnlyOperation":null,"contractsFileUrl":null,"listContractsAddFileVos":null,"storesName":null,"appendMun":null,"auditTime":null,"createTime":"Mon Jul 19 12:39:43 CST 2021","contractsFile":null,"type":"device_recover","contractStatus":null}
     * workFlowApplylogOperateVo : [{"userName":"19000000004","auditTime":null,"auditStat":null,"reason":null}]
     */

    private ContractsVoBean contractsVo;
    private List<WorkFlowApplylogOperateVoBean> workFlowApplylogOperateVo;

    public ContractsVoBean getContractsVo() {
        return contractsVo;
    }

    public void setContractsVo(ContractsVoBean contractsVo) {
        this.contractsVo = contractsVo;
    }

    public List<WorkFlowApplylogOperateVoBean> getWorkFlowApplylogOperateVo() {
        return workFlowApplylogOperateVo;
    }

    public void setWorkFlowApplylogOperateVo(List<WorkFlowApplylogOperateVoBean> workFlowApplylogOperateVo) {
        this.workFlowApplylogOperateVo = workFlowApplylogOperateVo;
    }

    public static class ContractsVoBean {
        /**
         * id : 1416981047563915264
         * contractNumber : 457a11e7-2558-4a2c-8c24-bf9fee543413
         * storeId : 1415855164790804480
         * name : null
         * image : null
         * contractType : null
         * typeTitle : null
         * status : 1
         * statusTitle : null
         * createdAt : null
         * signedAt : null
         * signedDateLimit : null
         * isOnlyOperation : null
         * contractsFileUrl : null
         * listContractsAddFileVos : null
         * storesName : null
         * appendMun : null
         * auditTime : null
         * createTime : Mon Jul 19 12:39:43 CST 2021
         * contractsFile : null
         * type : device_recover
         * contractStatus : null
         */

        private String id;
        private String contractNumber;
        private String storeId;
        private String name;
        private String image;
        private String contractType;
        private String typeTitle;
        private String status;
        private String statusTitle;
        private String createdAt;
        private String signedAt;
        private String signedDateLimit;
        private String isOnlyOperation;
        private String contractsFileUrl;
        private String listContractsAddFileVos;
        private String storesName;
        private String appendMun;
        private String auditTime;
        private String createTime;
        private String contractsFile;
        private String type;
        private String contractStatus;

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

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
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

        public String getSignedAt() {
            return signedAt;
        }

        public void setSignedAt(String signedAt) {
            this.signedAt = signedAt;
        }

        public String getSignedDateLimit() {
            return signedDateLimit;
        }

        public void setSignedDateLimit(String signedDateLimit) {
            this.signedDateLimit = signedDateLimit;
        }

        public String getIsOnlyOperation() {
            return isOnlyOperation;
        }

        public void setIsOnlyOperation(String isOnlyOperation) {
            this.isOnlyOperation = isOnlyOperation;
        }

        public String getContractsFileUrl() {
            return contractsFileUrl;
        }

        public void setContractsFileUrl(String contractsFileUrl) {
            this.contractsFileUrl = contractsFileUrl;
        }

        public String getListContractsAddFileVos() {
            return listContractsAddFileVos;
        }

        public void setListContractsAddFileVos(String listContractsAddFileVos) {
            this.listContractsAddFileVos = listContractsAddFileVos;
        }

        public String getStoresName() {
            return storesName;
        }

        public void setStoresName(String storesName) {
            this.storesName = storesName;
        }

        public String getAppendMun() {
            return appendMun;
        }

        public void setAppendMun(String appendMun) {
            this.appendMun = appendMun;
        }

        public String getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getContractsFile() {
            return contractsFile;
        }

        public void setContractsFile(String contractsFile) {
            this.contractsFile = contractsFile;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContractStatus() {
            return contractStatus;
        }

        public void setContractStatus(String contractStatus) {
            this.contractStatus = contractStatus;
        }
    }

    public static class WorkFlowApplylogOperateVoBean {
        /**
         * userName : 19000000004
         * auditTime : null
         * auditStat : null
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
