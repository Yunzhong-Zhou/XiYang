package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/5/10.
 */
public class DeviceDetailModel implements Serializable {
    /**
     * id : 1410512101939744768
     * hostName : hostTestdevice1
     * storeId : 1424918794245246976
     * storeName : 门店2
     * merchantId : 1424913384423428096
     * merchantName : 商户2
     * installTime : 2021-08-10 10:30:24
     * network : 1
     * storeRegion : 河北省沧州市沧县
     * storeAddress : 黄山大道与嵩山南路交叉路口往南约150米
     * storeIndustry : 酒店·景点
     * classic : -1
     * maintainer : -
     * lastOfflineTime : null
     * continueOnlineDays : 0
     * continueOfflineDays : 0
     * continueNotMovingSalesDays : 0
     * totalOrders : 0
     * currentDyOrders : 0
     * totalRevenue : 0
     * currentDyRevenue : 0
     * storeLevel : 0
     */

    private String id;
    private String hostName;
    private String storeId;
    private String storeName;
    private String merchantId;
    private String merchantName;
    private String installTime;
    private String network;
    private String storeRegion;
    private String storeAddress;
    private String storeIndustry;
    private String classic;
    private String maintainer;
    private String lastOfflineTime;
    private String continueOnlineDays;
    private String continueOfflineDays;
    private String continueNotMovingSalesDays;
    private String totalOrders;
    private String currentDyOrders;
    private String totalRevenue;
    private String currentDyRevenue;
    private String storeLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getStoreRegion() {
        return storeRegion;
    }

    public void setStoreRegion(String storeRegion) {
        this.storeRegion = storeRegion;
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

    public String getClassic() {
        return classic;
    }

    public void setClassic(String classic) {
        this.classic = classic;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getLastOfflineTime() {
        return lastOfflineTime;
    }

    public void setLastOfflineTime(String lastOfflineTime) {
        this.lastOfflineTime = lastOfflineTime;
    }

    public String getContinueOnlineDays() {
        return continueOnlineDays;
    }

    public void setContinueOnlineDays(String continueOnlineDays) {
        this.continueOnlineDays = continueOnlineDays;
    }

    public String getContinueOfflineDays() {
        return continueOfflineDays;
    }

    public void setContinueOfflineDays(String continueOfflineDays) {
        this.continueOfflineDays = continueOfflineDays;
    }

    public String getContinueNotMovingSalesDays() {
        return continueNotMovingSalesDays;
    }

    public void setContinueNotMovingSalesDays(String continueNotMovingSalesDays) {
        this.continueNotMovingSalesDays = continueNotMovingSalesDays;
    }

    public String getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(String totalOrders) {
        this.totalOrders = totalOrders;
    }

    public String getCurrentDyOrders() {
        return currentDyOrders;
    }

    public void setCurrentDyOrders(String currentDyOrders) {
        this.currentDyOrders = currentDyOrders;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getCurrentDyRevenue() {
        return currentDyRevenue;
    }

    public void setCurrentDyRevenue(String currentDyRevenue) {
        this.currentDyRevenue = currentDyRevenue;
    }

    public String getStoreLevel() {
        return storeLevel;
    }

    public void setStoreLevel(String storeLevel) {
        this.storeLevel = storeLevel;
    }
}
