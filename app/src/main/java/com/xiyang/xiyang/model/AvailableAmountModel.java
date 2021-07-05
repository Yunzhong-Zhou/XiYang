package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class AvailableAmountModel implements Serializable {
    /**
     * availableMoney : null
     * taxRate : 0.001
     * bankLogo : aa.png
     * bankUserName : 阿斯顿马丁
     * bankCardNumber : 4568445745785888588
     */

    private String availableMoney;
    private String taxRate;
    private String bankLogo;
    private String bankUserName;
    private String bankCardNumber;

    public String getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(String availableMoney) {
        this.availableMoney = availableMoney;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }
}
