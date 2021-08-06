package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/16.
 */
public class WorkListDetailModel implements Serializable {
    /**
     * id : 1414829573425729536
     * type : 1
     * status : 1
     * takeOverFlag : 1
     * failureReason : 无法启动
     * storeName : 门店12345
     * storeImage : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/07/162564415751220210707154600120.png
     * createName : 1200020020003
     * userTypeName : BD
     * remark : 师父父发过火
     * images : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/13/16261566307301626156431816.png
     * createTime : 1626156631000
     * dealList : [{"status":null,"adminId":"1415121678749638657","name":"黄BDM","avatar":"","remark":"师父父发过火","images":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/13/16261566307301626156431816.png","createTime":1626493091000}]
     */

    private String id;
    private int type;
    private int status;
    private int takeOverFlag;
    private String failureReason;
    private String storeName;
    private String storeImage;
    private String createName;
    private String userTypeName;
    private String remark;
    private String images;
    private String createTime;
    private String reportUserName;
    private String reportUserOrganCode;
    private List<DealListBean> dealList;
    private boolean currentUser;

    public boolean isCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(boolean currentUser) {
        this.currentUser = currentUser;
    }

    public String getReportUserOrganCode() {
        return reportUserOrganCode;
    }

    public void setReportUserOrganCode(String reportUserOrganCode) {
        this.reportUserOrganCode = reportUserOrganCode;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTakeOverFlag() {
        return takeOverFlag;
    }

    public void setTakeOverFlag(int takeOverFlag) {
        this.takeOverFlag = takeOverFlag;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<DealListBean> getDealList() {
        return dealList;
    }

    public void setDealList(List<DealListBean> dealList) {
        this.dealList = dealList;
    }

    public static class DealListBean {
        /**
         * status : null
         * adminId : 1415121678749638657
         * name : 黄BDM
         * avatar :
         * remark : 师父父发过火
         * images : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/13/16261566307301626156431816.png
         * createTime : 1626493091000
         */

        private String status;
        private String adminId;
        private String name;
        private String avatar;
        private String remark;
        private String images;
        private String createTime;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
