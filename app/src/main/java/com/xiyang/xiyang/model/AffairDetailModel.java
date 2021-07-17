package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class AffairDetailModel implements Serializable {
    /**
     * type : 1
     * isApply : 2
     * status : 1
     * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png
     * storesId : 1413329610791325696
     * address : 才有意义唱歌找不到大不大
     * storesName : 门店名称1
     * storesDeviceNum : 0
     * applyNum : 10
     * createTime : 1626228026000
     * transactionId : 1414776614721556480
     * transactionNumber : a47bf14f-a70f-40f6-ac7d-88769c4d15da
     * selectedDeviceList : []
     */

    private String type;
    private String isApply;
    private String status;
    private String image;
    private String storesId;
    private String address;
    private String storesName;
    private String storesDeviceNum;
    private String applyNum;
    private String createTime;
    private String transactionId;
    private String transactionNumber;
    private List<?> selectedDeviceList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsApply() {
        return isApply;
    }

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStoresId() {
        return storesId;
    }

    public void setStoresId(String storesId) {
        this.storesId = storesId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }

    public String getStoresDeviceNum() {
        return storesDeviceNum;
    }

    public void setStoresDeviceNum(String storesDeviceNum) {
        this.storesDeviceNum = storesDeviceNum;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public List<?> getSelectedDeviceList() {
        return selectedDeviceList;
    }

    public void setSelectedDeviceList(List<?> selectedDeviceList) {
        this.selectedDeviceList = selectedDeviceList;
    }
}
