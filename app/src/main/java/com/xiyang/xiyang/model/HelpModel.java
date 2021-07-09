package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/6/25.
 */
public class HelpModel implements Serializable {
    private List<ProblemVoListBean> problemVoList;

    public List<ProblemVoListBean> getProblemVoList() {
        return problemVoList;
    }

    public void setProblemVoList(List<ProblemVoListBean> problemVoList) {
        this.problemVoList = problemVoList;
    }

    public static class ProblemVoListBean {
        /**
         * id : 1402916293669163008
         * type : true
         * name : 设备启动不了
         * descs : 设备启动不了
         * adminId : 1
         * createdTime : null
         * updatedTime : null
         * delFlag : 0
         */

        private String id;
        private boolean type;
        private String name;
        private String descs;
        private int adminId;
        private Object createdTime;
        private Object updatedTime;
        private int delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
        }

        public Object getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(Object createdTime) {
            this.createdTime = createdTime;
        }

        public Object getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(Object updatedTime) {
            this.updatedTime = updatedTime;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }
    }
}
