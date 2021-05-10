package com.xiyang.xiyang.model;

/**
 * Created by Mr.Z on 2021/5/10.
 */
public class DebugDeviceModel {
    /**
     * device : {"windSpeed":"3","airLevel":"0","sleep":"0","pmValue":"0","tempature":"33","humidity":"0","autoModeSwitch":"0","ionsSwitch":"0","photocatalysisSwitch":"0","childLockSwitch":"0","powerSwitch":"0","airLevelDesc":"优"}
     * isSelfUse : 0
     */

    private DeviceBean device;
    private String isSelfUse;

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    public String getIsSelfUse() {
        return isSelfUse;
    }

    public void setIsSelfUse(String isSelfUse) {
        this.isSelfUse = isSelfUse;
    }

    public static class DeviceBean {
        /**
         * windSpeed : 3
         * airLevel : 0
         * sleep : 0
         * pmValue : 0
         * tempature : 33
         * humidity : 0
         * autoModeSwitch : 0
         * ionsSwitch : 0
         * photocatalysisSwitch : 0
         * childLockSwitch : 0
         * powerSwitch : 0
         * airLevelDesc : 优
         */

        private String windSpeed;
        private String airLevel;
        private String sleep;
        private String pmValue;
        private String tempature;
        private String humidity;
        private String autoModeSwitch;
        private String ionsSwitch;
        private String photocatalysisSwitch;
        private String childLockSwitch;
        private String powerSwitch;
        private String airLevelDesc;

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getAirLevel() {
            return airLevel;
        }

        public void setAirLevel(String airLevel) {
            this.airLevel = airLevel;
        }

        public String getSleep() {
            return sleep;
        }

        public void setSleep(String sleep) {
            this.sleep = sleep;
        }

        public String getPmValue() {
            return pmValue;
        }

        public void setPmValue(String pmValue) {
            this.pmValue = pmValue;
        }

        public String getTempature() {
            return tempature;
        }

        public void setTempature(String tempature) {
            this.tempature = tempature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getAutoModeSwitch() {
            return autoModeSwitch;
        }

        public void setAutoModeSwitch(String autoModeSwitch) {
            this.autoModeSwitch = autoModeSwitch;
        }

        public String getIonsSwitch() {
            return ionsSwitch;
        }

        public void setIonsSwitch(String ionsSwitch) {
            this.ionsSwitch = ionsSwitch;
        }

        public String getPhotocatalysisSwitch() {
            return photocatalysisSwitch;
        }

        public void setPhotocatalysisSwitch(String photocatalysisSwitch) {
            this.photocatalysisSwitch = photocatalysisSwitch;
        }

        public String getChildLockSwitch() {
            return childLockSwitch;
        }

        public void setChildLockSwitch(String childLockSwitch) {
            this.childLockSwitch = childLockSwitch;
        }

        public String getPowerSwitch() {
            return powerSwitch;
        }

        public void setPowerSwitch(String powerSwitch) {
            this.powerSwitch = powerSwitch;
        }

        public String getAirLevelDesc() {
            return airLevelDesc;
        }

        public void setAirLevelDesc(String airLevelDesc) {
            this.airLevelDesc = airLevelDesc;
        }
    }
}
