package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/6/26.
 */
public class LoginModel implements Serializable {
    /**
     * accessToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjAuMTA4XC9hcHBcL3VzZXJcL2xvZ2luXC9wYXNzd29yZCIsImlhdCI6MTYxODM4NzU1MywiZXhwIjoxNjIwMTg3NTUzLCJuYmYiOjE2MTgzODc1NTMsImp0aSI6IlZYeXFXQWJTNXJvR1g1VlciLCJzdWIiOjEyLCJwcnYiOiI2YmU1Zjk1Y2RkNWUwNTZlOTA3NzVkZDQ2MjZkMTI3MzEwODNlMzM0In0.z2_Hgn8kSyLFjQ5F_CP9XwQdaUb2TxrRAPxinmv0gNI
     * tokenType : bearer
     * expiresIn : 1800000
     * roleType : bd
     * mobile : 18306043086
     * nickname : admin3
     * head : http://zhitiekj.oo/head/head.png
     */

    private String accessToken;
    private String tokenType;
    private String expiresIn;
    private String roleType;
    private String mobile;
    private String nickname;
    private String head;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}
