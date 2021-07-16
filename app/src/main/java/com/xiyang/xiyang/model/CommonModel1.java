package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/19.
 * 通用模型
 */
public class CommonModel1 implements Serializable {
    /**
     * VisitTypeEnum : {"tag":"拜访方式","list":[{"code":1,"description":"面谈"},{"code":2,"description":"电话"}]}
     */

    private VisitTypeEnumBean VisitTypeEnum;
    /**
     * BusinessSituationEnum : {"tag":"营业情况","list":[{"code":0,"description":"未营业"},{"code":1,"description":"营业中"}]}
     */

    private BusinessSituationEnumBean BusinessSituationEnum;
    /**
     * CooperationRiskEnum : {"tag":"合作风险","list":[{"code":1,"description":"门店倒闭"}]}
     */

    private CooperationRiskEnumBean CooperationRiskEnum;
    /**
     * OperationTypeEnum : {"tag":"运营类型","list":[{"code":0,"description":"未知"},{"code":1,"description":"合作"}]}
     */

    private OperationTypeEnumBean OperationTypeEnum;
    /**
     * VisitReasonEnum : {"tag":"拜访原因","list":[{"code":1,"description":"日常拜访"}]}
     */

    private VisitReasonEnumBean VisitReasonEnum;
    /**
     * VisitFeedbackEnum : {"tag":"拜访反馈","list":[{"code":1,"description":"清洁设备外观"}]}
     */
    private VisitFeedbackEnumBean VisitFeedbackEnum;

    public VisitTypeEnumBean getVisitTypeEnum() {
        return VisitTypeEnum;
    }

    public void setVisitTypeEnum(VisitTypeEnumBean VisitTypeEnum) {
        this.VisitTypeEnum = VisitTypeEnum;
    }

    public BusinessSituationEnumBean getBusinessSituationEnum() {
        return BusinessSituationEnum;
    }

    public void setBusinessSituationEnum(BusinessSituationEnumBean BusinessSituationEnum) {
        this.BusinessSituationEnum = BusinessSituationEnum;
    }

    public CooperationRiskEnumBean getCooperationRiskEnum() {
        return CooperationRiskEnum;
    }

    public void setCooperationRiskEnum(CooperationRiskEnumBean CooperationRiskEnum) {
        this.CooperationRiskEnum = CooperationRiskEnum;
    }

    public static class ListBean {
        /**
         * code : 1
         * description : 清洁设备外观
         */

        private String code;
        private String description;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


    public OperationTypeEnumBean getOperationTypeEnum() {
        return OperationTypeEnum;
    }

    public void setOperationTypeEnum(OperationTypeEnumBean OperationTypeEnum) {
        this.OperationTypeEnum = OperationTypeEnum;
    }

    public VisitReasonEnumBean getVisitReasonEnum() {
        return VisitReasonEnum;
    }

    public void setVisitReasonEnum(VisitReasonEnumBean VisitReasonEnum) {
        this.VisitReasonEnum = VisitReasonEnum;
    }

    public static class OperationTypeEnumBean {
        /**
         * tag : 运营类型
         * list : [{"code":0,"description":"未知"},{"code":1,"description":"合作"}]
         */

        private String tag;
        private List<ListBean> list;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
    }



    public VisitFeedbackEnumBean getVisitFeedbackEnum() {
        return VisitFeedbackEnum;
    }

    public void setVisitFeedbackEnum(VisitFeedbackEnumBean VisitFeedbackEnum) {
        this.VisitFeedbackEnum = VisitFeedbackEnum;
    }

    public static class VisitFeedbackEnumBean {

        private String tag;
        private List<ListBean> list;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
    }

    public static class VisitReasonEnumBean {
        /**
         * tag : 拜访原因
         * list : [{"code":1,"description":"日常拜访"}]
         */

        private String tag;
        private List<ListBean> list;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
    }

    public static class VisitTypeEnumBean {
        /**
         * tag : 拜访方式
         * list : [{"code":1,"description":"面谈"},{"code":2,"description":"电话"}]
         */

        private String tag;
        private List<ListBean> list;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
    }

    public static class BusinessSituationEnumBean {
        /**
         * tag : 营业情况
         * list : [{"code":0,"description":"未营业"},{"code":1,"description":"营业中"}]
         */

        private String tag;
        private List<ListBean> list;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
    }

    public static class CooperationRiskEnumBean {
        /**
         * tag : 合作风险
         * list : [{"code":1,"description":"门店倒闭"}]
         */

        private String tag;
        private List<ListBean> list;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
    }

}
