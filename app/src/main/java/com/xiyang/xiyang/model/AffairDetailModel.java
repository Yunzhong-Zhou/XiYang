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
    /**
     * logistics : {"id":"1418484733290180610","type":2,"relationType":1,"relationId":"1415214942927327232","expressWay":1,"status":2,"voucher":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/23/16270280884271627027885216.png","checkAdminScopeOrganId":"1415128701016973313","checkAdminId":"1415128701008584706","adminId":"1415128701008584706","createTime":"2021-07-23 16:14:48","delFlag":0}
     */

    private LogisticsBean logistics;

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

    public LogisticsBean getLogistics() {
        return logistics;
    }

    public void setLogistics(LogisticsBean logistics) {
        this.logistics = logistics;
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


    public static class LogisticsBean {
        /**
         * id : 1418484733290180610
         * type : 2
         * relationType : 1
         * relationId : 1415214942927327232
         * expressWay : 1
         * status : 2
         * voucher : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/23/16270280884271627027885216.png
         * checkAdminScopeOrganId : 1415128701016973313
         * checkAdminId : 1415128701008584706
         * adminId : 1415128701008584706
         * createTime : 2021-07-23 16:14:48
         * delFlag : 0
         */

        private String id;
        private String type;
        private String relationType;
        private String relationId;
        private String expressWay;
        private String status;
        private String voucher;
        private String checkAdminScopeOrganId;
        private String checkAdminId;
        private String adminId;
        private String createTime;
        private String delFlag;
        /**
         * type : 2
         * relationType : 1
         * expressWay : 2
         * status : 1
         * receiveName : 啊水电费阿斯顿
         * phone : 1684587877788
         * provinceId : 110000
         * cityId : 110100
         * areaId : 110105
         * address : 阿道夫更丰富
         * delFlag : 0
         */

        private String receiveName;
        private String phone;
        private int provinceId;
        private int cityId;
        private int areaId;
        private String address;
        private List<ExpressInfoBean> expressInfo;


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

        public String getRelationType() {
            return relationType;
        }

        public void setRelationType(String relationType) {
            this.relationType = relationType;
        }

        public String getRelationId() {
            return relationId;
        }

        public void setRelationId(String relationId) {
            this.relationId = relationId;
        }

        public String getExpressWay() {
            return expressWay;
        }

        public void setExpressWay(String expressWay) {
            this.expressWay = expressWay;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVoucher() {
            return voucher;
        }

        public void setVoucher(String voucher) {
            this.voucher = voucher;
        }

        public String getCheckAdminScopeOrganId() {
            return checkAdminScopeOrganId;
        }

        public void setCheckAdminScopeOrganId(String checkAdminScopeOrganId) {
            this.checkAdminScopeOrganId = checkAdminScopeOrganId;
        }

        public String getCheckAdminId() {
            return checkAdminId;
        }

        public void setCheckAdminId(String checkAdminId) {
            this.checkAdminId = checkAdminId;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<ExpressInfoBean> getExpressInfo() {
            return expressInfo;
        }

        public void setExpressInfo(List<ExpressInfoBean> expressInfo) {
            this.expressInfo = expressInfo;
        }

        public static class ExpressInfoBean {
            /**
             * applyId : 1415216539682082816
             * transportId : 123436
             * transportCompany : 天天
             * sendTime : 2021-07-23 08:39:01
             * sendAdminId : 1414885152698638337
             * sendAdminScopeOrganId : 6
             * signTime : null
             * signAdminId : null
             * signAdminScopeOrganId : null
             */

            private String applyId;
            private String transportId;
            private String transportCompany;
            private String sendTime;
            private String sendAdminId;
            private String sendAdminScopeOrganId;
            private String signTime;
            private String signAdminId;
            private String signAdminScopeOrganId;

            public String getApplyId() {
                return applyId;
            }

            public void setApplyId(String applyId) {
                this.applyId = applyId;
            }

            public String getTransportId() {
                return transportId;
            }

            public void setTransportId(String transportId) {
                this.transportId = transportId;
            }

            public String getTransportCompany() {
                return transportCompany;
            }

            public void setTransportCompany(String transportCompany) {
                this.transportCompany = transportCompany;
            }

            public String getSendTime() {
                return sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }

            public String getSendAdminId() {
                return sendAdminId;
            }

            public void setSendAdminId(String sendAdminId) {
                this.sendAdminId = sendAdminId;
            }

            public String getSendAdminScopeOrganId() {
                return sendAdminScopeOrganId;
            }

            public void setSendAdminScopeOrganId(String sendAdminScopeOrganId) {
                this.sendAdminScopeOrganId = sendAdminScopeOrganId;
            }

            public String getSignTime() {
                return signTime;
            }

            public void setSignTime(String signTime) {
                this.signTime = signTime;
            }

            public String getSignAdminId() {
                return signAdminId;
            }

            public void setSignAdminId(String signAdminId) {
                this.signAdminId = signAdminId;
            }

            public String getSignAdminScopeOrganId() {
                return signAdminScopeOrganId;
            }

            public void setSignAdminScopeOrganId(String signAdminScopeOrganId) {
                this.signAdminScopeOrganId = signAdminScopeOrganId;
            }
        }
    }
}
