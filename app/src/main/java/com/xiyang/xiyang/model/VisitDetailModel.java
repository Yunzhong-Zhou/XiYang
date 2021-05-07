package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/5/7.
 */
public class VisitDetailModel implements Serializable {
    /**
     * id : 5
     * type : 上门拜访
     * storeName : 门店名称周
     * storeCover : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F161e4bda16efdf907846347ebf3605dc.png?e=1619495042&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:s45gMniysuMy-thQHy25XsVmreQ=
     * storeAddres : 阿斯顿马丁路德金
     * createdUser : 张三
     * contractName : 李四
     * contractMobile : 1544xxxx
     * createdAt : 2021-05-07 10:57:56
     * extra : null
     * images : ["a.png","b.png"]
     */

    private String id;
    private String type;
    private String storeName;
    private String storeCover;
    private String storeAddres;
    private String createdUser;
    private String contractName;
    private String contractMobile;
    private String createdAt;
    private String extra;
    private List<String> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCover() {
        return storeCover;
    }

    public void setStoreCover(String storeCover) {
        this.storeCover = storeCover;
    }

    public String getStoreAddres() {
        return storeAddres;
    }

    public void setStoreAddres(String storeAddres) {
        this.storeAddres = storeAddres;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractMobile() {
        return contractMobile;
    }

    public void setContractMobile(String contractMobile) {
        this.contractMobile = contractMobile;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
