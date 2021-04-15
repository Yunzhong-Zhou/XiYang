package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/6/25.
 */
public class HelpModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * name : 充值一直不到账？
         * desc : 充值一直不到账？充值一直不到账？充值一直不到账？充值一直不到账？充值一直不到账？充值一直不到账？
         */

        private String id;
        private String name;
        private String desc;

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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
