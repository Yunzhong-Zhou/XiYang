package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/29.
 */
public class AffairDetailModel implements Serializable {
    /**
     * id : 17
     * sn : 2021042948551001
     * transactionName : 新增设备
     * merchantName : 商户名称周
     * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F20581441f6eb25ec75b27e20dca59f8c.png?e=1619342284&amp;token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:sC6kK7h4cjLHNKHxttjKNA_QoXc=
     * deviceNum : 20
     * address : 阿斯顿马丁路德金阿斯顿
     * status : 0
     * statusTitle : 处理中
     * contract : {"deviceType":"主机","storeName":"门店名称周","installNum":"20","createdAt":"2021-04-28 17:18:22"}
     * apply : {"type":"0","typeTitle":"未知","name":null,"mobile":null,"addres":"000","addressDetail":null,"voucher":"","expressNo":"","expressCompany":"","status":"0","statusTitle":"初始状态"}
     * install : [{"id":""}]
     */

    private String id;
    private String sn;
    private String transactionName;
    private String merchantName;
    private String image;
    private String deviceNum;
    private String address;
    private String status;
    private String statusTitle;
    private ContractBean contract;
    private ApplyBean apply;
    private List<InstallBean> install;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public ContractBean getContract() {
        return contract;
    }

    public void setContract(ContractBean contract) {
        this.contract = contract;
    }

    public ApplyBean getApply() {
        return apply;
    }

    public void setApply(ApplyBean apply) {
        this.apply = apply;
    }

    public List<InstallBean> getInstall() {
        return install;
    }

    public void setInstall(List<InstallBean> install) {
        this.install = install;
    }

    public static class ContractBean {
        /**
         * deviceType : 主机
         * storeName : 门店名称周
         * installNum : 20
         * createdAt : 2021-04-28 17:18:22
         */

        private String deviceType;
        private String storeName;
        private String installNum;
        private String createdAt;

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getInstallNum() {
            return installNum;
        }

        public void setInstallNum(String installNum) {
            this.installNum = installNum;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class ApplyBean {
        /**
         * type : 0
         * typeTitle : 未知
         * name : null
         * mobile : null
         * addres : 000
         * addressDetail : null
         * voucher :
         * expressNo :
         * expressCompany :
         * status : 0
         * statusTitle : 初始状态
         */

        private String type;
        private String typeTitle;
        private String name;
        private String mobile;
        private String addres;
        private String addressDetail;
        private String voucher;
        private String expressNo;
        private String expressCompany;
        private String status;
        private String statusTitle;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddres() {
            return addres;
        }

        public void setAddres(String addres) {
            this.addres = addres;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getVoucher() {
            return voucher;
        }

        public void setVoucher(String voucher) {
            this.voucher = voucher;
        }

        public String getExpressNo() {
            return expressNo;
        }

        public void setExpressNo(String expressNo) {
            this.expressNo = expressNo;
        }

        public String getExpressCompany() {
            return expressCompany;
        }

        public void setExpressCompany(String expressCompany) {
            this.expressCompany = expressCompany;
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
    }

    public static class InstallBean {
        /**
         * id :
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
