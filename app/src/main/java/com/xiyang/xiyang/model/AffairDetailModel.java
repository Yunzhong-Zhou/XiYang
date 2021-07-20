package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class AffairDetailModel implements Serializable {
    /**
     * addDeivceTransactionsVo : {"name":"门店名称1","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png","childName":"分店名称1","extra":"{\"name\": \"\", \"sole\": null, \"areaId\": \"\", \"cityId\": \"\", \"reason\": \"\", \"account\": \"\", \"address\": \"\", \"logoUrl\": \"\", \"deviceNum\": 10, \"expressNo\": \"\", \"inStoreId\": \"\", \"licenseNo\": \"\", \"storeUnit\": \"\", \"expressWay\": \"\", \"industryId\": \"\", \"outStoreId\": \"\", \"provinceId\": \"\", \"verifyedAt\": null, \"companyName\": \"\", \"contactName\": \"\", \"expressName\": \"\", \"renewalTime\": null, \"signExpired\": null, \"warehouseId\": \"\", \"contactPhone\": \"\", \"storeCapping\": \"\", \"renewalPeriod\": null, \"storeFreeTime\": \"\", \"certificateUrl\": \"\", \"storeUnitPrice\": \"\"}","num":10,"status":"待处理","transcationType":"主机安装","transcastionId":"1415218517946535936","createTime":"2021-07-14 15:56:03","deviceType":"新增主机","applyId":"1415218496647860224"}
     * deviceInstallLogVoList : [{"storeName":"门店名称1","storeChildName":"分店名称1","roomName":"","deviceNo":"hostTestdevice4","installTime":null},{"storeName":"门店名称1","storeChildName":"分店名称1","roomName":"","deviceNo":"hostTestdevice2","installTime":null},{"storeName":"门店名称1","storeChildName":"分店名称1","roomName":"","deviceNo":"hostTestdevice3","installTime":null},{"storeName":"门店名称1","storeChildName":"分店名称1","roomName":"","deviceNo":"hostTestdevice1","installTime":null}]
     */

    private AddDeivceTransactionsVoBean addDeivceTransactionsVo;
    private List<DeviceInstallLogVoListBean> deviceInstallLogVoList;

    public AddDeivceTransactionsVoBean getAddDeivceTransactionsVo() {
        return addDeivceTransactionsVo;
    }

    public void setAddDeivceTransactionsVo(AddDeivceTransactionsVoBean addDeivceTransactionsVo) {
        this.addDeivceTransactionsVo = addDeivceTransactionsVo;
    }

    public List<DeviceInstallLogVoListBean> getDeviceInstallLogVoList() {
        return deviceInstallLogVoList;
    }

    public void setDeviceInstallLogVoList(List<DeviceInstallLogVoListBean> deviceInstallLogVoList) {
        this.deviceInstallLogVoList = deviceInstallLogVoList;
    }

    public static class AddDeivceTransactionsVoBean {
        /**
         * name : 门店名称1
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png
         * childName : 分店名称1
         * extra : {"name": "", "sole": null, "areaId": "", "cityId": "", "reason": "", "account": "", "address": "", "logoUrl": "", "deviceNum": 10, "expressNo": "", "inStoreId": "", "licenseNo": "", "storeUnit": "", "expressWay": "", "industryId": "", "outStoreId": "", "provinceId": "", "verifyedAt": null, "companyName": "", "contactName": "", "expressName": "", "renewalTime": null, "signExpired": null, "warehouseId": "", "contactPhone": "", "storeCapping": "", "renewalPeriod": null, "storeFreeTime": "", "certificateUrl": "", "storeUnitPrice": ""}
         * num : 10
         * status : 待处理
         * transcationType : 主机安装
         * transcastionId : 1415218517946535936
         * createTime : 2021-07-14 15:56:03
         * deviceType : 新增主机
         * applyId : 1415218496647860224
         */

        private String name;
        private String image;
        private String childName;
        private String extra;
        private String num;
        private String status;
        private String transcationType;
        private String transcastionId;
        private String createTime;
        private String deviceType;
        private String applyId;
        private String storeId;
        private String getType;

        public String getGetType() {
            return getType;
        }

        public void setGetType(String getType) {
            this.getType = getType;
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

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTranscationType() {
            return transcationType;
        }

        public void setTranscationType(String transcationType) {
            this.transcationType = transcationType;
        }

        public String getTranscastionId() {
            return transcastionId;
        }

        public void setTranscastionId(String transcastionId) {
            this.transcastionId = transcastionId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getApplyId() {
            return applyId;
        }

        public void setApplyId(String applyId) {
            this.applyId = applyId;
        }
    }

    public static class DeviceInstallLogVoListBean {
        /**
         * storeName : 门店名称1
         * storeChildName : 分店名称1
         * roomName :
         * deviceNo : hostTestdevice4
         * installTime : null
         */

        private String storeName;
        private String storeChildName;
        private String roomName;
        private String deviceNo;
        private String installTime;

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreChildName() {
            return storeChildName;
        }

        public void setStoreChildName(String storeChildName) {
            this.storeChildName = storeChildName;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public String getInstallTime() {
            return installTime;
        }

        public void setInstallTime(String installTime) {
            this.installTime = installTime;
        }
    }
}
