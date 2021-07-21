package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/22.
 */
public class ShopDetailModel implements Serializable {
    /**
     * base : {"id":"1415133648994308096","name":"测试商户1","account":"19000000006","companyName":"公司名称1","contactPhone":"19000000006","contactName":"阿斯顿马丁","insduty":null,"city":null,"address":"阿斯顿马丁路德金","logoUrl":"","licenseNo":"12345677777655","certificateUrl":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/14/16262299988521626229796507.png","provinceId":110000,"cityId":110100,"areaId":110105,"status":4,"industryId":null,"level":null,"tag":null,"isPublic":null,"isBindBank":null,"sources":0}
     * countData : {"deviceNum":0,"storeNum":2,"money":0,"profit":0}
     * applyData : {"inviteCode":null,"inviteType":null,"applyAt":null}
     * signData : {"contract":"56a95e48-858e-4bfd-85cc-86ba4a660bf9","sole":1,"renewalTime":null,"renewalPeriod":3,"userName":"黄BD","signType":"merchant_sign","verifyedAt":null}
     * bdData : {"name":"黄BD","type":null}
     * storesList : [{"id":"1415855164790804480","storeSn":"a3c9c891e5724dd69b86271bb6e0c002","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/16/16264011506861626400954357.png","name":"信达门店3","address":"黄山大道重庆高科总部广场西南侧约220米","userTypeId":"1415855164825493505","visitStatus":1,"contractStatus":1,"transferStatus":1,"totalRevenue":0,"deviceNumber":0},{"id":"1417012983346892800","storeSn":"20e6c29745874802b99ffb4cf5316b5c","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/19/16266771961741626676993642.png","name":"信达门店4","address":"黄山大道重庆高科总部广场西南侧约220米","userTypeId":"1417012983427727361","visitStatus":1,"contractStatus":1,"transferStatus":1,"totalRevenue":0,"deviceNumber":0}]
     * contractsList : [{"id":"1415137301742358528","status":3,"type":"merchant_sign","createTime":"2021-07-14 10:33:19"},{"id":"1415557230765412352","status":2,"type":"device_add","createTime":"2021-07-15 14:21:58"},{"id":"1415557321324630016","status":2,"type":"device_add","createTime":"2021-07-15 14:22:20"}]
     */

    private BaseBean base;
    private CountDataBean countData;
    private ApplyDataBean applyData;
    private SignDataBean signData;
    private BdDataBean bdData;
    private List<StoresListBean> storesList;
    private List<ContractsListBean> contractsList;

    public BaseBean getBase() {
        return base;
    }

    public void setBase(BaseBean base) {
        this.base = base;
    }

    public CountDataBean getCountData() {
        return countData;
    }

    public void setCountData(CountDataBean countData) {
        this.countData = countData;
    }

    public ApplyDataBean getApplyData() {
        return applyData;
    }

    public void setApplyData(ApplyDataBean applyData) {
        this.applyData = applyData;
    }

    public SignDataBean getSignData() {
        return signData;
    }

    public void setSignData(SignDataBean signData) {
        this.signData = signData;
    }

    public BdDataBean getBdData() {
        return bdData;
    }

    public void setBdData(BdDataBean bdData) {
        this.bdData = bdData;
    }

    public List<StoresListBean> getStoresList() {
        return storesList;
    }

    public void setStoresList(List<StoresListBean> storesList) {
        this.storesList = storesList;
    }

    public List<ContractsListBean> getContractsList() {
        return contractsList;
    }

    public void setContractsList(List<ContractsListBean> contractsList) {
        this.contractsList = contractsList;
    }

    public static class BaseBean {
        /**
         * id : 1415133648994308096
         * name : 测试商户1
         * account : 19000000006
         * companyName : 公司名称1
         * contactPhone : 19000000006
         * contactName : 阿斯顿马丁
         * insduty : null
         * city : null
         * address : 阿斯顿马丁路德金
         * logoUrl :
         * licenseNo : 12345677777655
         * certificateUrl : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/14/16262299988521626229796507.png
         * provinceId : 110000
         * cityId : 110100
         * areaId : 110105
         * status : 4
         * industryId : null
         * level : null
         * tag : null
         * isPublic : null
         * isBindBank : null
         * sources : 0
         */

        private String id;
        private String name;
        private String account;
        private String companyName;
        private String contactPhone;
        private String contactName;
        private String insduty;
        private String city;
        private String address;
        private String logoUrl;
        private String licenseNo;
        private String certificateUrl;
        private String provinceId;
        private String cityId;
        private String areaId;
        private String status;
        private String industryId;
        private String level;
        private String tag;
        private String isPublic;
        private String isBindBank;
        private String sources;
        private String transfterStatus;
        private String showPointBtn;
        private String signStatus;

        public String getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(String signStatus) {
            this.signStatus = signStatus;
        }

        public String getTransfterStatus() {
            return transfterStatus;
        }

        public void setTransfterStatus(String transfterStatus) {
            this.transfterStatus = transfterStatus;
        }

        public String getShowPointBtn() {
            return showPointBtn;
        }

        public void setShowPointBtn(String showPointBtn) {
            this.showPointBtn = showPointBtn;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getInsduty() {
            return insduty;
        }

        public void setInsduty(String insduty) {
            this.insduty = insduty;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIndustryId() {
            return industryId;
        }

        public void setIndustryId(String industryId) {
            this.industryId = industryId;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getIsPublic() {
            return isPublic;
        }

        public void setIsPublic(String isPublic) {
            this.isPublic = isPublic;
        }

        public String getIsBindBank() {
            return isBindBank;
        }

        public void setIsBindBank(String isBindBank) {
            this.isBindBank = isBindBank;
        }

        public String getSources() {
            return sources;
        }

        public void setSources(String sources) {
            this.sources = sources;
        }
    }

    public static class CountDataBean {
        /**
         * deviceNum : 0
         * storeNum : 2
         * money : 0
         * profit : 0
         */

        private String deviceNum;
        private String storeNum;
        private String money;
        private String profit;

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public String getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(String storeNum) {
            this.storeNum = storeNum;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getProfit() {
            return profit;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }
    }

    public static class ApplyDataBean {
        /**
         * inviteCode : null
         * inviteType : null
         * applyAt : null
         */

        private String inviteCode;
        private String inviteType;
        private String applyAt;

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getInviteType() {
            return inviteType;
        }

        public void setInviteType(String inviteType) {
            this.inviteType = inviteType;
        }

        public String getApplyAt() {
            return applyAt;
        }

        public void setApplyAt(String applyAt) {
            this.applyAt = applyAt;
        }
    }

    public static class SignDataBean {
        /**
         * contract : 56a95e48-858e-4bfd-85cc-86ba4a660bf9
         * sole : 1
         * renewalTime : null
         * renewalPeriod : 3
         * userName : 黄BD
         * signType : merchant_sign
         * verifyedAt : null
         */

        private String contract;
        private String sole;
        private String renewalTime;
        private String renewalPeriod;
        private String userName;
        private String signType;
        private String verifyedAt;

        public String getContract() {
            return contract;
        }

        public void setContract(String contract) {
            this.contract = contract;
        }

        public String getSole() {
            return sole;
        }

        public void setSole(String sole) {
            this.sole = sole;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getVerifyedAt() {
            return verifyedAt;
        }

        public void setVerifyedAt(String verifyedAt) {
            this.verifyedAt = verifyedAt;
        }
    }

    public static class BdDataBean {
        /**
         * name : 黄BD
         * type : null
         */

        private String name;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class StoresListBean {
        /**
         * id : 1415855164790804480
         * storeSn : a3c9c891e5724dd69b86271bb6e0c002
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/16/16264011506861626400954357.png
         * name : 信达门店3
         * address : 黄山大道重庆高科总部广场西南侧约220米
         * userTypeId : 1415855164825493505
         * visitStatus : 1
         * contractStatus : 1
         * transferStatus : 1
         * totalRevenue : 0
         * deviceNumber : 0
         */

        private String id;
        private String storeSn;
        private String image;
        private String name;
        private String address;
        private String userTypeId;
        private String visitStatus;
        private String contractStatus;
        private String transferStatus;
        private String totalRevenue;
        private String deviceNumber;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoreSn() {
            return storeSn;
        }

        public void setStoreSn(String storeSn) {
            this.storeSn = storeSn;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserTypeId() {
            return userTypeId;
        }

        public void setUserTypeId(String userTypeId) {
            this.userTypeId = userTypeId;
        }

        public String getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(String visitStatus) {
            this.visitStatus = visitStatus;
        }

        public String getContractStatus() {
            return contractStatus;
        }

        public void setContractStatus(String contractStatus) {
            this.contractStatus = contractStatus;
        }

        public String getTransferStatus() {
            return transferStatus;
        }

        public void setTransferStatus(String transferStatus) {
            this.transferStatus = transferStatus;
        }

        public String getTotalRevenue() {
            return totalRevenue;
        }

        public void setTotalRevenue(String totalRevenue) {
            this.totalRevenue = totalRevenue;
        }

        public String getDeviceNumber() {
            return deviceNumber;
        }

        public void setDeviceNumber(String deviceNumber) {
            this.deviceNumber = deviceNumber;
        }
    }

    public static class ContractsListBean {
        /**
         * id : 1415137301742358528
         * status : 3
         * type : merchant_sign
         * createTime : 2021-07-14 10:33:19
         */

        private String id;
        private String status;
        private String type;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
