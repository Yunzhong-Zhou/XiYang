package com.xiyang.xiyang.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class AffairDetailModel implements Serializable {
    /**
     * id : 1415216539774357504
     * transactionNo : 80282acd-7b03-4e0d-8213-75dd237efb5a
     * type : 1
     * typeName : 新增设备
     * relationId : 1415216539682082816
     * status : 2
     * storeName : 门店名称1-1
     * storeImage : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png
     * logistic : {"id":"1418487037506580482","type":2,"relationType":1,"relationId":"1415216539682082816","expressWay":2,"status":3,"receiveName":"啊水电费阿斯顿","phone":"1684587877788","provinceId":110000,"provinceName":"北京","cityId":110100,"cityName":"北京市","areaId":110105,"areaName":"朝阳区","address":"阿道夫更丰富","expressInfo":[{"applyId":"1415216539682082816","transportId":"123123","transportCompany":"123","sendTime":"2021-07-25 18:43:27","sendAdminId":"1414885152698638337","sendAdminScopeOrganId":"6","signTime":"2021-07-26 02:47:00","signAdminId":"1415128701008584706","signAdminScopeOrganId":"1415128701016973313"},{"applyId":"1415216539682082816","transportId":"1231213","transportCompany":"123","sendTime":"2021-07-24 10:08:47","sendAdminId":"1414885152698638337","sendAdminScopeOrganId":"6","signTime":"2021-07-25 18:17:32","signAdminId":"1414885152698638337","signAdminScopeOrganId":"6"}],"checkTime":"2021-07-26 10:47:00","checkAdminScopeOrganId":"1415128701016973313","checkAdminId":"1415128701008584706","sendAdminId":"1414885152698638337","sendAdminScopeOrganId":"6","adminId":"1415128701008584706","createTime":"2021-07-23 16:23:58","updateTime":"2021-07-26 10:47:00","delFlag":0}
     * list : [{"id":"1419588049709506561","type":1,"storeId":"1413329610791325696","deviceId":"1410512101939744768","deviceHostName":"hostTestdevice1","roomId":"1417038789582393344","roomFullName":"门店名称1-1·A1·B1·C1·D1","deviceApplyId":"1415216539682082816","status":1,"deviceModuleId":"0","installTime":"2021-07-26 17:19:00","createTime":"2021-07-26 17:19:00"}]
     * installType : 安装中
     * installNum : 43
     * installedNum : 1
     */

    private String id;
    private String transactionNo;
    private String type;
    private String typeName;
    private String relationId;
    private String contractId;
    private String status;
    private String storeId;
    private String storeName;
    private String storeImage;
    private LogisticBean logistic;
    private String installType;
    private String installNum;
    private String installedNum;
    private List<ListBean> list;
    private String createTime;
    /**
     * contract : {"deviceNum":1,"file":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/28/1627440347220订单流程.pdf","contractType":"device_exchange","storeId":"1413329610791325696","inStoreId":"1415855164790804480","outStoreId":"1413329610791325696"}
     */

    private ContractBean contract;


    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LogisticBean getLogistic() {
        return logistic;
    }

    public void setLogistic(LogisticBean logistic) {
        this.logistic = logistic;
    }

    public String getInstallType() {
        return installType;
    }

    public void setInstallType(String installType) {
        this.installType = installType;
    }

    public String getInstallNum() {
        return installNum;
    }

    public void setInstallNum(String installNum) {
        this.installNum = installNum;
    }

    public String getInstalledNum() {
        return installedNum;
    }

    public void setInstalledNum(String installedNum) {
        this.installedNum = installedNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public ContractBean getContract() {
        return contract;
    }

    public void setContract(ContractBean contract) {
        this.contract = contract;
    }

    public static class LogisticBean {
        /**
         * id : 1418487037506580482
         * type : 2
         * relationType : 1
         * relationId : 1415216539682082816
         * expressWay : 2
         * status : 3
         * receiveName : 啊水电费阿斯顿
         * phone : 1684587877788
         * provinceId : 110000
         * provinceName : 北京
         * cityId : 110100
         * cityName : 北京市
         * areaId : 110105
         * areaName : 朝阳区
         * address : 阿道夫更丰富
         * expressInfo : [{"applyId":"1415216539682082816","transportId":"123123","transportCompany":"123","sendTime":"2021-07-25 18:43:27","sendAdminId":"1414885152698638337","sendAdminScopeOrganId":"6","signTime":"2021-07-26 02:47:00","signAdminId":"1415128701008584706","signAdminScopeOrganId":"1415128701016973313"},{"applyId":"1415216539682082816","transportId":"1231213","transportCompany":"123","sendTime":"2021-07-24 10:08:47","sendAdminId":"1414885152698638337","sendAdminScopeOrganId":"6","signTime":"2021-07-25 18:17:32","signAdminId":"1414885152698638337","signAdminScopeOrganId":"6"}]
         * checkTime : 2021-07-26 10:47:00
         * checkAdminScopeOrganId : 1415128701016973313
         * checkAdminId : 1415128701008584706
         * sendAdminId : 1414885152698638337
         * sendAdminScopeOrganId : 6
         * adminId : 1415128701008584706
         * createTime : 2021-07-23 16:23:58
         * updateTime : 2021-07-26 10:47:00
         * delFlag : 0
         */

        private String id;
        private String type;
        private String relationType;
        private String relationId;
        private String expressWay;
        private String status;
        private String receiveName;
        private String phone;
        private String provinceId;
        private String provinceName;
        private String cityId;
        private String cityName;
        private String areaId;
        private String areaName;
        private String address;
        private String checkTime;
        private String checkAdminScopeOrganId;
        private String checkAdminId;
        private String sendAdminId;
        private String sendAdminScopeOrganId;
        private String adminId;
        private String createTime;
        private String updateTime;
        private String delFlag;
        private List<ExpressInfoBean> expressInfo;
        private String voucher;
        private String warehouseName;
        private String warehouseId;

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getVoucher() {
            return voucher;
        }

        public void setVoucher(String voucher) {
            this.voucher = voucher;
        }

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

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
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

        public List<ExpressInfoBean> getExpressInfo() {
            return expressInfo;
        }

        public void setExpressInfo(List<ExpressInfoBean> expressInfo) {
            this.expressInfo = expressInfo;
        }

        public static class ExpressInfoBean {
            /**
             * applyId : 1415216539682082816
             * transportId : 123123
             * transportCompany : 123
             * sendTime : 2021-07-25 18:43:27
             * sendAdminId : 1414885152698638337
             * sendAdminScopeOrganId : 6
             * signTime : 2021-07-26 02:47:00
             * signAdminId : 1415128701008584706
             * signAdminScopeOrganId : 1415128701016973313
             */

            private String logisticId;
            private String transportId;
            private String transportCompany;
            private String sendTime;
            private String sendAdminId;
            private String sendAdminScopeOrganId;
            private String signTime;
            private String signAdminId;
            private String signAdminScopeOrganId;

            public String getLogisticId() {
                return logisticId;
            }

            public void setLogisticId(String logisticId) {
                this.logisticId = logisticId;
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

    public static class ListBean {
        /**
         * id : 1419588049709506561
         * type : 1
         * storeId : 1413329610791325696
         * deviceId : 1410512101939744768
         * deviceHostName : hostTestdevice1
         * roomId : 1417038789582393344
         * roomFullName : 门店名称1-1·A1·B1·C1·D1
         * deviceApplyId : 1415216539682082816
         * status : 1
         * deviceModuleId : 0
         * installTime : 2021-07-26 17:19:00
         * createTime : 2021-07-26 17:19:00
         */

        private String id;
        private String type;
        private String storeId;
        private String deviceId;
        private String deviceHostName;
        private String roomId;
        private String roomFullName;
        private String deviceApplyId;
        private String status;
        private String deviceModuleId;
        private String installTime;
        private String createTime;

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

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceHostName() {
            return deviceHostName;
        }

        public void setDeviceHostName(String deviceHostName) {
            this.deviceHostName = deviceHostName;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomFullName() {
            return roomFullName;
        }

        public void setRoomFullName(String roomFullName) {
            this.roomFullName = roomFullName;
        }

        public String getDeviceApplyId() {
            return deviceApplyId;
        }

        public void setDeviceApplyId(String deviceApplyId) {
            this.deviceApplyId = deviceApplyId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeviceModuleId() {
            return deviceModuleId;
        }

        public void setDeviceModuleId(String deviceModuleId) {
            this.deviceModuleId = deviceModuleId;
        }

        public String getInstallTime() {
            return installTime;
        }

        public void setInstallTime(String installTime) {
            this.installTime = installTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

    public static class ContractBean {
        /**
         * deviceNum : 1
         * file : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/28/1627440347220订单流程.pdf
         * contractType : device_exchange
         * storeId : 1413329610791325696
         * inStoreId : 1415855164790804480
         * outStoreId : 1413329610791325696
         */

        private String deviceNum;
        private String file;
        private String contractType;
        @SerializedName("storeId")
        private String storeIdX;
        private String inStoreId;
        private String outStoreId;
        private String reasonId;
        private String warehouseId;

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getReasonId() {
            return reasonId;
        }

        public void setReasonId(String reasonId) {
            this.reasonId = reasonId;
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getContractType() {
            return contractType;
        }

        public void setContractType(String contractType) {
            this.contractType = contractType;
        }

        public String getStoreIdX() {
            return storeIdX;
        }

        public void setStoreIdX(String storeIdX) {
            this.storeIdX = storeIdX;
        }

        public String getInStoreId() {
            return inStoreId;
        }

        public void setInStoreId(String inStoreId) {
            this.inStoreId = inStoreId;
        }

        public String getOutStoreId() {
            return outStoreId;
        }

        public void setOutStoreId(String outStoreId) {
            this.outStoreId = outStoreId;
        }
    }
}
