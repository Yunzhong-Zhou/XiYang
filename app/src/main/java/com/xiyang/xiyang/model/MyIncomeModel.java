package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/6.
 */
public class MyIncomeModel implements Serializable {
    /**
     * availableMoney : 0.00
     * toBePayMoney : null
     * cumulativeRevenue : null
     * equipmentRevenue : null
     * recommendedRevenue : null
     * cumulativeConsumption : null
     * recommendedShare : null
     * cumulativeWithdrawal : null
     * operationMaintenanceShare : null
     * totalRevenue : null
     */

    private String availableMoney;
    private String toBePayMoney;
    private String cumulativeRevenue;
    private String equipmentRevenue;
    private String recommendedRevenue;
    private String cumulativeConsumption;
    private String recommendedShare;
    private String cumulativeWithdrawal;
    private String operationMaintenanceShare;
    private String totalRevenue;

    public String getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(String availableMoney) {
        this.availableMoney = availableMoney;
    }

    public String getToBePayMoney() {
        return toBePayMoney;
    }

    public void setToBePayMoney(String toBePayMoney) {
        this.toBePayMoney = toBePayMoney;
    }

    public String getCumulativeRevenue() {
        return cumulativeRevenue;
    }

    public void setCumulativeRevenue(String cumulativeRevenue) {
        this.cumulativeRevenue = cumulativeRevenue;
    }

    public String getEquipmentRevenue() {
        return equipmentRevenue;
    }

    public void setEquipmentRevenue(String equipmentRevenue) {
        this.equipmentRevenue = equipmentRevenue;
    }

    public String getRecommendedRevenue() {
        return recommendedRevenue;
    }

    public void setRecommendedRevenue(String recommendedRevenue) {
        this.recommendedRevenue = recommendedRevenue;
    }

    public String getCumulativeConsumption() {
        return cumulativeConsumption;
    }

    public void setCumulativeConsumption(String cumulativeConsumption) {
        this.cumulativeConsumption = cumulativeConsumption;
    }

    public String getRecommendedShare() {
        return recommendedShare;
    }

    public void setRecommendedShare(String recommendedShare) {
        this.recommendedShare = recommendedShare;
    }

    public String getCumulativeWithdrawal() {
        return cumulativeWithdrawal;
    }

    public void setCumulativeWithdrawal(String cumulativeWithdrawal) {
        this.cumulativeWithdrawal = cumulativeWithdrawal;
    }

    public String getOperationMaintenanceShare() {
        return operationMaintenanceShare;
    }

    public void setOperationMaintenanceShare(String operationMaintenanceShare) {
        this.operationMaintenanceShare = operationMaintenanceShare;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
