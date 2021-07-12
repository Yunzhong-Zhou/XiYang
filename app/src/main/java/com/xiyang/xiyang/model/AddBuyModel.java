package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/7/12.
 */
public class AddBuyModel implements Serializable {
    /**
     * warehouse : {"id":"1","warehouseId":"1","num":10,"usedNum":0,"scopeOrganId":"1410143188464705538","name":"测试仓库1"}
     */

    private WarehouseBean warehouse;

    public WarehouseBean getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseBean warehouse) {
        this.warehouse = warehouse;
    }

    public static class WarehouseBean {
        /**
         * id : 1
         * warehouseId : 1
         * num : 10
         * usedNum : 0
         * scopeOrganId : 1410143188464705538
         * name : 测试仓库1
         */

        private String id;
        private String warehouseId;
        private int num;
        private int usedNum;
        private String scopeOrganId;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getUsedNum() {
            return usedNum;
        }

        public void setUsedNum(int usedNum) {
            this.usedNum = usedNum;
        }

        public String getScopeOrganId() {
            return scopeOrganId;
        }

        public void setScopeOrganId(String scopeOrganId) {
            this.scopeOrganId = scopeOrganId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
