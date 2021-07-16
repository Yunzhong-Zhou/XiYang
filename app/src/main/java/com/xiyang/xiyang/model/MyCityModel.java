package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/11.
 */
public class MyCityModel implements Serializable {
    /**
     * organId : 1414885152384065537
     * organName : null
     * regionId : 140000
     * regionName : 山西省
     * regionLevel : 1
     * cmNumber : 0
     * bdmNumber : 0
     * bdNumber : 0
     * merchantNumber : 0
     * storeNumber : 0
     * deviceNumber : 0
     * revenue : 0
     */

    private String organId;
    private String organName;
    private String regionId;
    private String regionName;
    private String regionLevel;
    private String cmNumber;
    private String bdmNumber;
    private String bdNumber;
    private String merchantNumber;
    private String storeNumber;
    private String deviceNumber;
    private String revenue;

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(String regionLevel) {
        this.regionLevel = regionLevel;
    }

    public String getCmNumber() {
        return cmNumber;
    }

    public void setCmNumber(String cmNumber) {
        this.cmNumber = cmNumber;
    }

    public String getBdmNumber() {
        return bdmNumber;
    }

    public void setBdmNumber(String bdmNumber) {
        this.bdmNumber = bdmNumber;
    }

    public String getBdNumber() {
        return bdNumber;
    }

    public void setBdNumber(String bdNumber) {
        this.bdNumber = bdNumber;
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}
