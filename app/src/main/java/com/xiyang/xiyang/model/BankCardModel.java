package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/6/30.
 */
public class BankCardModel implements Serializable {
    /**
     * id : 1410430385191522304
     * bankId : 1402903932405157888
     * bankName : 平安银行
     * bankLogo : pa.png
     * userId : 1410143973189623809
     * bankUserName : 阿斯顿马丁
     * bankCardNumber : 1875588878888
     * tradePasswordFlag : true
     */

    private String id;
    private String bankId;
    private String bankName;
    private String bankLogo;
    private String userId;
    private String bankUserName;
    private String bankCardNumber;
    private boolean tradePasswordFlag = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public boolean isTradePasswordFlag() {
        return tradePasswordFlag;
    }

    public void setTradePasswordFlag(boolean tradePasswordFlag) {
        this.tradePasswordFlag = tradePasswordFlag;
    }
}
