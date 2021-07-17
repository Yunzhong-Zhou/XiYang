package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/10.
 */
public class DeviceDetailModel implements Serializable {
    /**
     * classic : 0
     * continueNotMovingSalesDays : 0
     * continueOfflineDays : 0
     * continueOnlineDays : 0
     * currentDyOrders : 0
     * currentDyRevenue : 0
     * hostName : string
     * id : string
     * installTime : 2021-07-17T08:21:11.299Z
     * lastOfflineTime : 2021-07-17T08:21:11.299Z
     * maintainer : string
     * network : 0
     * storeAddress : string
     * storeIndustry : string
     * storeLevel : 0
     * storeName : string
     * storeRegion : string
     * totalOrders : 0
     * totalRevenue : 0
     */

    private String classic;
    private String continueNotMovingSalesDays;
    private String continueOfflineDays;
    private String continueOnlineDays;
    private String currentDyOrders;
    private String currentDyRevenue;
    private String hostName;
    private String id;
    private String installTime;
    private String lastOfflineTime;
    private String maintainer;
    private String network;
    private String storeAddress;
    private String storeIndustry;
    private String storeLevel;
    private String storeName;
    private String storeRegion;
    private String totalOrders;
    private String totalRevenue;

    public String getClassic() {
        return classic;
    }

    public void setClassic(String classic) {
        this.classic = classic;
    }

    public String getContinueNotMovingSalesDays() {
        return continueNotMovingSalesDays;
    }

    public void setContinueNotMovingSalesDays(String continueNotMovingSalesDays) {
        this.continueNotMovingSalesDays = continueNotMovingSalesDays;
    }

    public String getContinueOfflineDays() {
        return continueOfflineDays;
    }

    public void setContinueOfflineDays(String continueOfflineDays) {
        this.continueOfflineDays = continueOfflineDays;
    }

    public String getContinueOnlineDays() {
        return continueOnlineDays;
    }

    public void setContinueOnlineDays(String continueOnlineDays) {
        this.continueOnlineDays = continueOnlineDays;
    }

    public String getCurrentDyOrders() {
        return currentDyOrders;
    }

    public void setCurrentDyOrders(String currentDyOrders) {
        this.currentDyOrders = currentDyOrders;
    }

    public String getCurrentDyRevenue() {
        return currentDyRevenue;
    }

    public void setCurrentDyRevenue(String currentDyRevenue) {
        this.currentDyRevenue = currentDyRevenue;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getLastOfflineTime() {
        return lastOfflineTime;
    }

    public void setLastOfflineTime(String lastOfflineTime) {
        this.lastOfflineTime = lastOfflineTime;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreIndustry() {
        return storeIndustry;
    }

    public void setStoreIndustry(String storeIndustry) {
        this.storeIndustry = storeIndustry;
    }

    public String getStoreLevel() {
        return storeLevel;
    }

    public void setStoreLevel(String storeLevel) {
        this.storeLevel = storeLevel;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreRegion() {
        return storeRegion;
    }

    public void setStoreRegion(String storeRegion) {
        this.storeRegion = storeRegion;
    }

    public String getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(String totalOrders) {
        this.totalOrders = totalOrders;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
