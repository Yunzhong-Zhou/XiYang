package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/25.
 */
public class ApproveDetailModel implements Serializable {
    /**
     * head : {"id":"27","name":"商户名称周","image":"http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F20581441f6eb25ec75b27e20dca59f8c.png?e=1619342284&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:sC6kK7h4cjLHNKHxttjKNA_QoXc=","contactTitle":"商户签约","status":"0","statusTitle":"已提交"}
     * base : {"contactTitle":"商户签约","file":null,"extra":{"sole":"1","signTime":"1619280000000","licenseNo":"12445677898655","renewalPeriod":"2","licenseNoImage":"http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F7f7ca2d37abf6bab6ccfde69c823cfae.png?e=1619342525&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:r1KUXiMYSjfnXjHUTX2oMUNIgvQ="},"status":"0","statusTitle":"已提交","createdAt":"2021-04-25 16:22:08"}
     * verifyLog : [{"id":"27","name":"bdm周","head":"http://localhost:8000/head/head.png","remark":null,"image":["a.png"],"status":"0","statusTitle":"已提交","createdAt":"2021-04-25 16:22:08"}]
     */

    private HeadBean head;
    private BaseBean base;
    private List<VerifyLogBean> verifyLog;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public BaseBean getBase() {
        return base;
    }

    public void setBase(BaseBean base) {
        this.base = base;
    }

    public List<VerifyLogBean> getVerifyLog() {
        return verifyLog;
    }

    public void setVerifyLog(List<VerifyLogBean> verifyLog) {
        this.verifyLog = verifyLog;
    }

    public static class HeadBean {
        /**
         * id : 27
         * name : 商户名称周
         * image : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F20581441f6eb25ec75b27e20dca59f8c.png?e=1619342284&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:sC6kK7h4cjLHNKHxttjKNA_QoXc=
         * contactTitle : 商户签约
         * status : 0
         * statusTitle : 已提交
         */

        private String id;
        private String name;
        private String image;
        private String contactTitle;
        private String status;
        private String statusTitle;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getContactTitle() {
            return contactTitle;
        }

        public void setContactTitle(String contactTitle) {
            this.contactTitle = contactTitle;
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

    public static class BaseBean {
        /**
         * contactTitle : 商户签约
         * file : null
         * extra : {"sole":"1","signTime":"1619280000000","licenseNo":"12445677898655","renewalPeriod":"2","licenseNoImage":"http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F7f7ca2d37abf6bab6ccfde69c823cfae.png?e=1619342525&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:r1KUXiMYSjfnXjHUTX2oMUNIgvQ="}
         * status : 0
         * statusTitle : 已提交
         * createdAt : 2021-04-25 16:22:08
         */

        private String contactTitle;
        private String file;
        private ExtraBean extra;
        private String status;
        private String statusTitle;
        private String createdAt;

        public String getContactTitle() {
            return contactTitle;
        }

        public void setContactTitle(String contactTitle) {
            this.contactTitle = contactTitle;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public static class ExtraBean {
            /**
             * sole : 1
             * signTime : 1619280000000
             * licenseNo : 12445677898655
             * renewalPeriod : 2
             * licenseNoImage : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F7f7ca2d37abf6bab6ccfde69c823cfae.png?e=1619342525&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:r1KUXiMYSjfnXjHUTX2oMUNIgvQ=
             */

            private String sole;
            private String signTime;
            private String licenseNo;
            private String renewalPeriod;
            private String licenseNoImage;

            public String getSole() {
                return sole;
            }

            public void setSole(String sole) {
                this.sole = sole;
            }

            public String getSignTime() {
                return signTime;
            }

            public void setSignTime(String signTime) {
                this.signTime = signTime;
            }

            public String getLicenseNo() {
                return licenseNo;
            }

            public void setLicenseNo(String licenseNo) {
                this.licenseNo = licenseNo;
            }

            public String getRenewalPeriod() {
                return renewalPeriod;
            }

            public void setRenewalPeriod(String renewalPeriod) {
                this.renewalPeriod = renewalPeriod;
            }

            public String getLicenseNoImage() {
                return licenseNoImage;
            }

            public void setLicenseNoImage(String licenseNoImage) {
                this.licenseNoImage = licenseNoImage;
            }
        }
    }

    public static class VerifyLogBean {
        /**
         * id : 27
         * name : bdm周
         * head : http://localhost:8000/head/head.png
         * remark : null
         * image : ["a.png"]
         * status : 0
         * statusTitle : 已提交
         * createdAt : 2021-04-25 16:22:08
         */

        private String id;
        private String name;
        private String head;
        private String remark;
        private String status;
        private String statusTitle;
        private String createdAt;
        private List<String> image;

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

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }
    }
}
