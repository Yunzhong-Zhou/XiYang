package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/3/26.
 */
public class Fragment4Model_P implements Serializable {
    private String title;
    private String money;
    private String num;
    private String zd;

    public Fragment4Model_P(String title, String money, String num, String zd) {
        this.title = title;
        this.money = money;
        this.num = num;
        this.zd = zd;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getZd() {
        return zd;
    }

    public void setZd(String zd) {
        this.zd = zd;
    }

    @Override
    public String toString() {
        return "Fragment4Model_P{" +
                "title='" + title + '\'' +
                ", money='" + money + '\'' +
                ", num='" + num + '\'' +
                ", zd='" + zd + '\'' +
                '}';
    }
}
