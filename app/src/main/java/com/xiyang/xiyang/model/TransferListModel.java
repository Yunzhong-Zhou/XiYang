package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/16.
 */
public class TransferListModel implements Serializable {
    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {

        /**
         * id : 1422380311697821696
         * merchantId : 1422093472395038720
         * merchantName : 商户名称1
         * merchantAddress : 阿斯顿马丁SD风格
         * status : 1
         * reasonId : 5
         * transferReason : 商户划转理由
         * sn : SHHZ20210803101427516CjNo
         * createTime : 2021-08-03 10:14:28
         */

        private String id;
        private String merchantId;
        private String merchantName;
        private String merchantAddress;
        private String status;
        private String reasonId;
        private String transferReason;
        private String sn;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantAddress() {
            return merchantAddress;
        }

        public void setMerchantAddress(String merchantAddress) {
            this.merchantAddress = merchantAddress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReasonId() {
            return reasonId;
        }

        public void setReasonId(String reasonId) {
            this.reasonId = reasonId;
        }

        public String getTransferReason() {
            return transferReason;
        }

        public void setTransferReason(String transferReason) {
            this.transferReason = transferReason;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
