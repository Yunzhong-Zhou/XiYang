package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/3/29.
 */
public class Fragment1Model implements Serializable {
    /**
     * manageMerchantNum : 0
     * signMerchantNum : 2
     * recommendMerchantNum : 0
     * money : 100000
     * waitSign : [{"id":"1411240609229967360","name":"商户名称","createTime":null},{"id":"1411989698812973056","name":"商户名称1","createTime":null}]
     * waitCheck : []
     * hasRefuse : []
     * hasChecked : []
     * merchantsList : [{"id":"1411240609229967360","name":"商户名称","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/03/16253009550771625300772843.png","deviceNum":null,"address":"阿斯顿马丁路德金阿斯顿","status":"3","statusTitle":null,"money":null,"bddescription":null},{"id":"1411989698812973056","name":"商户名称1","image":"http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/05/16254795519841625479367281.png","deviceNum":null,"address":"啊水电费阿斯顿","status":"3","statusTitle":null,"money":null,"bddescription":null}]
     */

    private String manageMerchantNum;
    private String signMerchantNum;
    private String recommendMerchantNum;
    private String money;
    private List<WaitSignBean> waitSign;
    private List<WaitSignBean> waitCheck;
    private List<WaitSignBean> hasRefuse;
    private List<WaitSignBean> hasChecked;
    private List<MerchantsListBean> merchantsList;

    public String getManageMerchantNum() {
        return manageMerchantNum;
    }

    public void setManageMerchantNum(String manageMerchantNum) {
        this.manageMerchantNum = manageMerchantNum;
    }

    public String getSignMerchantNum() {
        return signMerchantNum;
    }

    public void setSignMerchantNum(String signMerchantNum) {
        this.signMerchantNum = signMerchantNum;
    }

    public String getRecommendMerchantNum() {
        return recommendMerchantNum;
    }

    public void setRecommendMerchantNum(String recommendMerchantNum) {
        this.recommendMerchantNum = recommendMerchantNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<WaitSignBean> getWaitSign() {
        return waitSign;
    }

    public void setWaitSign(List<WaitSignBean> waitSign) {
        this.waitSign = waitSign;
    }

    public List<WaitSignBean> getWaitCheck() {
        return waitCheck;
    }

    public void setWaitCheck(List<WaitSignBean> waitCheck) {
        this.waitCheck = waitCheck;
    }

    public List<WaitSignBean> getHasRefuse() {
        return hasRefuse;
    }

    public void setHasRefuse(List<WaitSignBean> hasRefuse) {
        this.hasRefuse = hasRefuse;
    }

    public List<WaitSignBean> getHasChecked() {
        return hasChecked;
    }

    public void setHasChecked(List<WaitSignBean> hasChecked) {
        this.hasChecked = hasChecked;
    }

    public List<MerchantsListBean> getMerchantsList() {
        return merchantsList;
    }

    public void setMerchantsList(List<MerchantsListBean> merchantsList) {
        this.merchantsList = merchantsList;
    }

    public static class WaitSignBean {
        /**
         * id : 1411240609229967360
         * name : 商户名称
         * createTime : null
         */

        private String id;
        private String name;
        private String createTime;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

    public static class MerchantsListBean {
        /**
         * id : 1411240609229967360
         * name : 商户名称
         * image : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/03/16253009550771625300772843.png
         * deviceNum : null
         * address : 阿斯顿马丁路德金阿斯顿
         * status : 3
         * statusTitle : null
         * money : null
         * bddescription : null
         */

        private String id;
        private String name;
        private String image;
        private String storeNum;
        private String address;
        private String status;
        private String statusTitle;
        private String money;
        private String bddescription;

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

        public String getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(String storeNum) {
            this.storeNum = storeNum;
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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getBddescription() {
            return bddescription;
        }

        public void setBddescription(String bddescription) {
            this.bddescription = bddescription;
        }
    }
}
