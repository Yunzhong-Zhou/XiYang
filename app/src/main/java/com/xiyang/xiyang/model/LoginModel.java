package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/6/26.
 */
public class LoginModel implements Serializable {
    /**
     * accessToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjAuMTA0XC9hcHBcL3VzZXJcL2xvZ2luXC9wYXNzd29yZCIsImlhdCI6MTYxNzc5MTA2MCwiZXhwIjoxNjM1NzkxMDYwLCJuYmYiOjE2MTc3OTEwNjAsImp0aSI6InRUVHRaZXdxZ0o3ak9tQjMiLCJzdWIiOjEzLCJwcnYiOiI2YmU1Zjk1Y2RkNWUwNTZlOTA3NzVkZDQ2MjZkMTI3MzEwODNlMzM0In0.nxjaOcpxJ2cHtxVkhvoCLvyphkmKiJg5Oyy_waGQcUY
     * tokenType : bearer
     * expiresIn : 18000000
     * roleType : 1
     * level : 0
     * nickname : http://192.168.0.103/head/head.png
     * head : admin8
     */

    private String accessToken;
    private String tokenType;
    private String expiresIn;
    private String roleType;
    private String level;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
