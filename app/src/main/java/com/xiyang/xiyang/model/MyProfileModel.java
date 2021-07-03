package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/10.
 */

public class MyProfileModel implements Serializable {
    /**
     * avatar :
     * name : 1200020020003
     * phone : 1200020020003
     * gender : 1
     * belongingRegion : null
     * joinTime : 2021.06.29
     * belongingCM : 1200020020001
     * belongingRM : RM2
     * belongingBDM : 1200020020002
     */

    private String avatar;
    private String name;
    private String phone;
    private String gender;
    private String belongingRegion;
    private String joinTime;
    private String belongingCM;
    private String belongingRM;
    private String belongingBDM;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBelongingRegion() {
        return belongingRegion;
    }

    public void setBelongingRegion(String belongingRegion) {
        this.belongingRegion = belongingRegion;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getBelongingCM() {
        return belongingCM;
    }

    public void setBelongingCM(String belongingCM) {
        this.belongingCM = belongingCM;
    }

    public String getBelongingRM() {
        return belongingRM;
    }

    public void setBelongingRM(String belongingRM) {
        this.belongingRM = belongingRM;
    }

    public String getBelongingBDM() {
        return belongingBDM;
    }

    public void setBelongingBDM(String belongingBDM) {
        this.belongingBDM = belongingBDM;
    }
}
