package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/16.
 */
public class WorkListDetailModel implements Serializable {
    /**
     * id : 1414776532601278464
     * type : 1
     * status : 1
     * failureReason : 启动不了
     * storeName : 门店12345
     * storeImage : string
     * createName : 1200020020003
     * userTypeName : BD
     * remark : 发个滚滚滚
     * images : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/13/16261439847091626143792176.png
     * createTime : 2021-07-13 10:39:45
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

    public int getTakeOverFlag() {
        return takeOverFlag;
    }

    public void setTakeOverFlag(int takeOverFlag) {
        this.takeOverFlag = takeOverFlag;
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
}
