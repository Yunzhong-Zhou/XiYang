package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/7/21.
 */
public class DeviceRoomModel implements Serializable {
    /**
     * childName : 分店名称1
     * name : 门店名称1-1
     * storeId : 1413329610791325696
     * roomName : A1·B1·C1·D1
     * roomId : 1417038789582393344
     */

    private String childName;
    private String name;
    private String storeId;
    private String roomName;
    private String roomId;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
