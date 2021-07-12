package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/13.
 */
public class DeviceModel implements Serializable {
    /**
     * iotDeviceCurrentData : {"ResetStrainerLife":"null","PowerSwitch":"1","PmValue":"48","PhotocatalysisSwitch":"1","WindSpeed":"2","ChildLockSwitch":"0","AutoModeSwitch":"1","ReaminTime":"0","Sleep":"0","Timer":"0","IonsSwitch":"1","StrainerLife":"2946","Humidity":"74","ResetStrainer":"6","GeoLocation":"altitude:0,CoordinateSystem:2,latitude:29.6141387,longitude:106.4905952","AirLevel":"1","Tempature":"26"}
     */

    private IotDeviceCurrentDataBean iotDeviceCurrentData;

    public IotDeviceCurrentDataBean getIotDeviceCurrentData() {
        return iotDeviceCurrentData;
    }

    public void setIotDeviceCurrentData(IotDeviceCurrentDataBean iotDeviceCurrentData) {
        this.iotDeviceCurrentData = iotDeviceCurrentData;
    }

    public static class IotDeviceCurrentDataBean {
        /**
         * ResetStrainerLife : null
         * PowerSwitch : 1
         * PmValue : 48
         * PhotocatalysisSwitch : 1
         * WindSpeed : 2
         * ChildLockSwitch : 0
         * AutoModeSwitch : 1
         * ReaminTime : 0
         * Sleep : 0
         * Timer : 0
         * IonsSwitch : 1
         * StrainerLife : 2946
         * Humidity : 74
         * ResetStrainer : 6
         * GeoLocation : altitude:0,CoordinateSystem:2,latitude:29.6141387,longitude:106.4905952
         * AirLevel : 1
         * Tempature : 26
         */

        private String ResetStrainerLife;
        private String PowerSwitch;
        private String PmValue;
        private String PhotocatalysisSwitch;
        private String WindSpeed;
        private String ChildLockSwitch;
        private String AutoModeSwitch;
        private String ReaminTime;
        private String Sleep;
        private String Timer;
        private String IonsSwitch;
        private String StrainerLife;
        private String Humidity;
        private String ResetStrainer;
        private String GeoLocation;
        private String AirLevel;
        private String Tempature;

        public String getResetStrainerLife() {
            return ResetStrainerLife;
        }

        public void setResetStrainerLife(String ResetStrainerLife) {
            this.ResetStrainerLife = ResetStrainerLife;
        }

        public String getPowerSwitch() {
            return PowerSwitch;
        }

        public void setPowerSwitch(String PowerSwitch) {
            this.PowerSwitch = PowerSwitch;
        }

        public String getPmValue() {
            return PmValue;
        }

        public void setPmValue(String PmValue) {
            this.PmValue = PmValue;
        }

        public String getPhotocatalysisSwitch() {
            return PhotocatalysisSwitch;
        }

        public void setPhotocatalysisSwitch(String PhotocatalysisSwitch) {
            this.PhotocatalysisSwitch = PhotocatalysisSwitch;
        }

        public String getWindSpeed() {
            return WindSpeed;
        }

        public void setWindSpeed(String WindSpeed) {
            this.WindSpeed = WindSpeed;
        }

        public String getChildLockSwitch() {
            return ChildLockSwitch;
        }

        public void setChildLockSwitch(String ChildLockSwitch) {
            this.ChildLockSwitch = ChildLockSwitch;
        }

        public String getAutoModeSwitch() {
            return AutoModeSwitch;
        }

        public void setAutoModeSwitch(String AutoModeSwitch) {
            this.AutoModeSwitch = AutoModeSwitch;
        }

        public String getReaminTime() {
            return ReaminTime;
        }

        public void setReaminTime(String ReaminTime) {
            this.ReaminTime = ReaminTime;
        }

        public String getSleep() {
            return Sleep;
        }

        public void setSleep(String Sleep) {
            this.Sleep = Sleep;
        }

        public String getTimer() {
            return Timer;
        }

        public void setTimer(String Timer) {
            this.Timer = Timer;
        }

        public String getIonsSwitch() {
            return IonsSwitch;
        }

        public void setIonsSwitch(String IonsSwitch) {
            this.IonsSwitch = IonsSwitch;
        }

        public String getStrainerLife() {
            return StrainerLife;
        }

        public void setStrainerLife(String StrainerLife) {
            this.StrainerLife = StrainerLife;
        }

        public String getHumidity() {
            return Humidity;
        }

        public void setHumidity(String Humidity) {
            this.Humidity = Humidity;
        }

        public String getResetStrainer() {
            return ResetStrainer;
        }

        public void setResetStrainer(String ResetStrainer) {
            this.ResetStrainer = ResetStrainer;
        }

        public String getGeoLocation() {
            return GeoLocation;
        }

        public void setGeoLocation(String GeoLocation) {
            this.GeoLocation = GeoLocation;
        }

        public String getAirLevel() {
            return AirLevel;
        }

        public void setAirLevel(String AirLevel) {
            this.AirLevel = AirLevel;
        }

        public String getTempature() {
            return Tempature;
        }

        public void setTempature(String Tempature) {
            this.Tempature = Tempature;
        }
    }
}
