package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/6/26.
 */
public class LoginModel implements Serializable {
    /**
     * jobTitle : BD
     * nickname : 1200020020003
     * mobile : 1200020020003
     * avatar :
     * tokentype : jwt
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4aXlhbmctdXNlciIsImlhdCI6MTYyNTAzOTg1OSwiZXhwIjoxNjI2MjQ5NDU5LCJpZCI6IjI1MGJhMmQwZWY4MDViMDliODU3ODRmMTJmZjUzNTZkYWUwNDBjMzUzMjU2ODUxNmQyOGNhZTFjNTgxMTc1MGEiLCJuaWNrbmFtZSI6IjEyMDAwMjAwMjAwMDMiLCJyb2xlVHlwZSI6IiIsInVzZXJUeXBlIjoiMSJ9.Hl-itCMS6-j4lT4zgC-r519WjWp-YyXNxju1jUXar6uwrv-qqluZgYK7leIT2oD9UeZ__fER3SgQfIZYVaCWwQ
     */

    private String jobTitle;
    private String nickname;
    private String mobile;
    private String avatar;
    private String tokentype;
    private String token;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTokentype() {
        return tokentype;
    }

    public void setTokentype(String tokentype) {
        this.tokentype = tokentype;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
