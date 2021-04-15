package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/6.
 */
public class MyIncomeModel implements Serializable {
    /**
     * id : 12
     * roleType : bd
     * nickname : admin3
     * head : http://zhitiekj.oo/head/head.png
     * amount : 0.00
     * totalMoney : 0.00
     * recommendMoney : 0.00
     * operateMoney : 0.00
     * out : [{"id":1,"type":"1","title":"充值","money":"1000","createdAt":"2021-03-25 14:04:54"}]
     * in : [{"id":1,"type":"1","title":"充值","money":"1000","createdAt":"2021-03-25 14:04:54"}]
     */

    private String id;
    private String roleType;
    private String nickname;
    private String head;
    private String amount;
    private String totalMoney;
    private String recommendMoney;
    private String operateMoney;
    private List<OutBean> out;
    private List<InBean> in;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getRecommendMoney() {
        return recommendMoney;
    }

    public void setRecommendMoney(String recommendMoney) {
        this.recommendMoney = recommendMoney;
    }

    public String getOperateMoney() {
        return operateMoney;
    }

    public void setOperateMoney(String operateMoney) {
        this.operateMoney = operateMoney;
    }

    public List<OutBean> getOut() {
        return out;
    }

    public void setOut(List<OutBean> out) {
        this.out = out;
    }

    public List<InBean> getIn() {
        return in;
    }

    public void setIn(List<InBean> in) {
        this.in = in;
    }

    public static class OutBean {
        /**
         * id : 1
         * type : 1
         * title : 充值
         * money : 1000
         * createdAt : 2021-03-25 14:04:54
         */

        private int id;
        private String type;
        private String title;
        private String money;
        private String createdAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class InBean {
        /**
         * id : 1
         * type : 1
         * title : 充值
         * money : 1000
         * createdAt : 2021-03-25 14:04:54
         */

        private int id;
        private String type;
        private String title;
        private String money;
        private String createdAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }
}
