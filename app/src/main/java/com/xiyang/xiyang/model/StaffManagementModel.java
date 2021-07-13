package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/28.
 */
public class StaffManagementModel implements Serializable {
    private List<UserListVOListBean> userListVOList;

    public List<UserListVOListBean> getUserListVOList() {
        return userListVOList;
    }

    public void setUserListVOList(List<UserListVOListBean> userListVOList) {
        this.userListVOList = userListVOList;
    }

    public static class UserListVOListBean {
        /**
         * userId : 1414852679646187521
         * userImg : http://xiyang-oms.oss-cn-shanghai.aliyuncs.com/2021/07/13/16261621397061626161946847.png
         * userName : 信达
         * phone : 187558888888
         * totalMoney : 0
         */

        private String userId;
        private String userImg;
        private String userName;
        private String phone;
        private String totalMoney;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }
    }
}
