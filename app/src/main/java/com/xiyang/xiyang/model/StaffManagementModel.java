package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/28.
 */
public class StaffManagementModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 100058
         * account : 15754854547
         * name : 姓名
         * head : http://qqxfw8hz4.hn-bkt.clouddn.com/store%2F1f31682973210ee1bb3b0094de5c9c55.png?e=1619596109&token=rgQDQF0oBAnkAgYtqdqY8iyjc3cjnnQEifQJULC1:Vb_7pBAPKU0CwC4cxmVSbx_F0tc=
         * department : 1
         * money : 0.00
         */

        private String id;
        private String account;
        private String name;
        private String head;
        private String department;
        private String money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
