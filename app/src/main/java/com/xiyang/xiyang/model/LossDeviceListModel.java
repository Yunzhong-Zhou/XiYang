package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/8/11.
 */
public class LossDeviceListModel implements Serializable {
    /**
     * fullName : string
     * hostName : string
     * lossTime : 2021-08-11T08:31:52.285Z
     */

    private String fullName;
    private String hostName;
    private String lossTime;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLossTime() {
        return lossTime;
    }

    public void setLossTime(String lossTime) {
        this.lossTime = lossTime;
    }
}
