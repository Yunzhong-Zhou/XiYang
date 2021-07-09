package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/27.
 */
public class RoomNoManagementModel implements Serializable {
    private List<StoreRoomsListBean> storeRoomsList;

    public List<StoreRoomsListBean> getStoreRoomsList() {
        return storeRoomsList;
    }

    public void setStoreRoomsList(List<StoreRoomsListBean> storeRoomsList) {
        this.storeRoomsList = storeRoomsList;
    }

    public static class StoreRoomsListBean {
        /**
         * id : 1413390170258018304
         * name : 区域1
         * fullName :
         * parentId : 0
         * level : 1
         * storeId : 1412691587682668544
         * createTime : 2021-07-09 14:50:51
         * updateTime : 2021-07-09 14:50:51
         * delFlag : 0
         */

        private String id;
        private String name;
        private String fullName;
        private String parentId;
        private int level;
        private String storeId;
        private String createTime;
        private String updateTime;
        private String delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
