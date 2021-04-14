package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/2/10.
 */

public class BankCardSettingModel implements Serializable {
    /**
     * isSetBank : 1
     * bankList : [{"id":"1","name":"建设银行"},{"id":"2","name":"工商银行"}]
     * banks : [{"id":"4","bankTitle":"工商银行","accountName":"阿斯顿","card":"45687588555885","icon":"gsyh.png"}]
     */

    private String isSetBank;
    private List<BankListBean> bankList;
    private List<BanksBean> banks;

    public String getIsSetBank() {
        return isSetBank;
    }

    public void setIsSetBank(String isSetBank) {
        this.isSetBank = isSetBank;
    }

    public List<BankListBean> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankListBean> bankList) {
        this.bankList = bankList;
    }

    public List<BanksBean> getBanks() {
        return banks;
    }

    public void setBanks(List<BanksBean> banks) {
        this.banks = banks;
    }

    public static class BankListBean {
        /**
         * id : 1
         * name : 建设银行
         */

        private String id;
        private String name;

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
    }

    public static class BanksBean {
        /**
         * id : 4
         * bankTitle : 工商银行
         * accountName : 阿斯顿
         * card : 45687588555885
         * icon : gsyh.png
         */

        private String id;
        private String bankTitle;
        private String accountName;
        private String card;
        private String icon;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBankTitle() {
            return bankTitle;
        }

        public void setBankTitle(String bankTitle) {
            this.bankTitle = bankTitle;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
