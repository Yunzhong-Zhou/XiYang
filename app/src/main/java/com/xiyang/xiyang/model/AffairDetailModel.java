package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class AffairDetailModel implements Serializable {
    /**
     * addDeivceTransactionsVo : {"name":"门店名称1","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png","childName":"分店名称1","extra":"{\"sole\": null, \"reason\": \"\", \"logoUrl\": \"\", \"deviceNum\": 10, \"expressNo\": \"\", \"inStoreId\": \"\", \"licenseNo\": \"\", \"expressWay\": \"\", \"outStoreId\": \"\", \"expressName\": \"\", \"renewalTime\": null, \"signExpired\": null, \"warehouseId\": \"\", \"renewalPeriod\": null, \"certificateUrl\": \"\"}","num":10,"status":"待处理","transcationType":"主机安装","transcastionId":"1414776614721556480","createTime":"2021-07-14 10:00:26","deviceType":"新增主机","applyId":"1414776614658641920","contractsExtra":{"sole":null,"reason":"","signTime":null,"renewalTime":null,"renewalPeriod":null,"signExpired":null,"licenseNo":"","certificateUrl":"","logoUrl":"","deviceNum":10,"outStoreId":"","inStoreId":"","warehouseId":"","expressWay":"","expressNo":"","expressName":"","name":null,"companyName":null,"contactName":null,"contactPhone":null,"provinceId":null,"cityId":null,"areaId":null,"account":null,"address":null,"industryId":null,"storeUnit":null,"storeFreeTime":null,"storeUnitPrice":null,"storeCapping":null,"verifyedAt":null}}
     * deviceInstallLogVoList : []
     */

    private AddDeivceTransactionsVoBean addDeivceTransactionsVo;
    private List<?> deviceInstallLogVoList;

    public AddDeivceTransactionsVoBean getAddDeivceTransactionsVo() {
        return addDeivceTransactionsVo;
    }

    public void setAddDeivceTransactionsVo(AddDeivceTransactionsVoBean addDeivceTransactionsVo) {
        this.addDeivceTransactionsVo = addDeivceTransactionsVo;
    }

    public List<?> getDeviceInstallLogVoList() {
        return deviceInstallLogVoList;
    }

    public void setDeviceInstallLogVoList(List<?> deviceInstallLogVoList) {
        this.deviceInstallLogVoList = deviceInstallLogVoList;
    }

    public static class AddDeivceTransactionsVoBean {
        /**
         * name : 门店名称1
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/09/16257990118051625798824232.png
         * childName : 分店名称1
         * extra : {"sole": null, "reason": "", "logoUrl": "", "deviceNum": 10, "expressNo": "", "inStoreId": "", "licenseNo": "", "expressWay": "", "outStoreId": "", "expressName": "", "renewalTime": null, "signExpired": null, "warehouseId": "", "renewalPeriod": null, "certificateUrl": ""}
         * num : 10
         * status : 待处理
         * transcationType : 主机安装
         * transcastionId : 1414776614721556480
         * createTime : 2021-07-14 10:00:26
         * deviceType : 新增主机
         * applyId : 1414776614658641920
         * contractsExtra : {"sole":null,"reason":"","signTime":null,"renewalTime":null,"renewalPeriod":null,"signExpired":null,"licenseNo":"","certificateUrl":"","logoUrl":"","deviceNum":10,"outStoreId":"","inStoreId":"","warehouseId":"","expressWay":"","expressNo":"","expressName":"","name":null,"companyName":null,"contactName":null,"contactPhone":null,"provinceId":null,"cityId":null,"areaId":null,"account":null,"address":null,"industryId":null,"storeUnit":null,"storeFreeTime":null,"storeUnitPrice":null,"storeCapping":null,"verifyedAt":null}
         */

        private String name;
        private String image;
        private String childName;
        private String num;
        private String status;
        private String transcationType;
        private String transcastionId;
        private String createTime;
        private String deviceType;
        private String applyId;
        private ContractsExtraBean contractsExtra;
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

        public ContractsExtraBean getContractsExtra() {
            return contractsExtra;
        }

        public void setContractsExtra(ContractsExtraBean contractsExtra) {
            this.contractsExtra = contractsExtra;
        }

        public static class ContractsExtraBean {
            /**
             * sole : null
             * reason :
             * signTime : null
             * renewalTime : null
             * renewalPeriod : null
             * signExpired : null
             * licenseNo :
             * certificateUrl :
             * logoUrl :
             * deviceNum : 10
             * outStoreId :
             * inStoreId :
             * warehouseId :
             * expressWay :
             * expressNo :
             * expressName :
             * name : null
             * companyName : null
             * contactName : null
             * contactPhone : null
             * provinceId : null
             * cityId : null
             * areaId : null
             * account : null
             * address : null
             * industryId : null
             * storeUnit : null
             * storeFreeTime : null
             * storeUnitPrice : null
             * storeCapping : null
             * verifyedAt : null
             */

            private String sole;
            private String reason;
            private String signTime;
            private String renewalTime;
            private String renewalPeriod;
            private String signExpired;
            private String licenseNo;
            private String certificateUrl;
            private String logoUrl;
            private String deviceNum;
            private String outStoreId;
            private String inStoreId;
            private String warehouseId;
            private String expressWay;
            private String expressNo;
            private String expressName;
            private String name;
            private String companyName;
            private String contactName;
            private String contactPhone;
            private String provinceId;
            private String cityId;
            private String areaId;
            private String account;
            private String address;
            private String industryId;
            private String storeUnit;
            private String storeFreeTime;
            private String storeUnitPrice;
            private String storeCapping;
            private String verifyedAt;

            public String getSole() {
                return sole;
            }

            public void setSole(String sole) {
                this.sole = sole;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getSignTime() {
                return signTime;
            }

            public void setSignTime(String signTime) {
                this.signTime = signTime;
            }

            public String getRenewalTime() {
                return renewalTime;
            }

            public void setRenewalTime(String renewalTime) {
                this.renewalTime = renewalTime;
            }

            public String getRenewalPeriod() {
                return renewalPeriod;
            }

            public void setRenewalPeriod(String renewalPeriod) {
                this.renewalPeriod = renewalPeriod;
            }

            public String getSignExpired() {
                return signExpired;
            }

            public void setSignExpired(String signExpired) {
                this.signExpired = signExpired;
            }

            public String getLicenseNo() {
                return licenseNo;
            }

            public void setLicenseNo(String licenseNo) {
                this.licenseNo = licenseNo;
            }

            public String getCertificateUrl() {
                return certificateUrl;
            }

            public void setCertificateUrl(String certificateUrl) {
                this.certificateUrl = certificateUrl;
            }

            public String getLogoUrl() {
                return logoUrl;
            }

            public void setLogoUrl(String logoUrl) {
                this.logoUrl = logoUrl;
            }

            public String getDeviceNum() {
                return deviceNum;
            }

            public void setDeviceNum(String deviceNum) {
                this.deviceNum = deviceNum;
            }

            public String getOutStoreId() {
                return outStoreId;
            }

            public void setOutStoreId(String outStoreId) {
                this.outStoreId = outStoreId;
            }

            public String getInStoreId() {
                return inStoreId;
            }

            public void setInStoreId(String inStoreId) {
                this.inStoreId = inStoreId;
            }

            public String getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(String warehouseId) {
                this.warehouseId = warehouseId;
            }

            public String getExpressWay() {
                return expressWay;
            }

            public void setExpressWay(String expressWay) {
                this.expressWay = expressWay;
            }

            public String getExpressNo() {
                return expressNo;
            }

            public void setExpressNo(String expressNo) {
                this.expressNo = expressNo;
            }

            public String getExpressName() {
                return expressName;
            }

            public void setExpressName(String expressName) {
                this.expressName = expressName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public String getContactPhone() {
                return contactPhone;
            }

            public void setContactPhone(String contactPhone) {
                this.contactPhone = contactPhone;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getIndustryId() {
                return industryId;
            }

            public void setIndustryId(String industryId) {
                this.industryId = industryId;
            }

            public String getStoreUnit() {
                return storeUnit;
            }

            public void setStoreUnit(String storeUnit) {
                this.storeUnit = storeUnit;
            }

            public String getStoreFreeTime() {
                return storeFreeTime;
            }

            public void setStoreFreeTime(String storeFreeTime) {
                this.storeFreeTime = storeFreeTime;
            }

            public String getStoreUnitPrice() {
                return storeUnitPrice;
            }

            public void setStoreUnitPrice(String storeUnitPrice) {
                this.storeUnitPrice = storeUnitPrice;
            }

            public String getStoreCapping() {
                return storeCapping;
            }

            public void setStoreCapping(String storeCapping) {
                this.storeCapping = storeCapping;
            }

            public String getVerifyedAt() {
                return verifyedAt;
            }

            public void setVerifyedAt(String verifyedAt) {
                this.verifyedAt = verifyedAt;
            }
        }
    }
}
