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
        private String type;
        private String name;
        private String descs;
        private String adminId;
        private String createdTime;
        private String updatedTime;
        private String delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String isType() {
            return type;
        }

        public void setType(String type) {
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

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
