package com.xiyang.xiyang.net;

/**
 * URL路径处理类
 */
public class URLs {
    //测试地址
    public static String IMGHOST = "http://192.168.0.108";//图片地址
    public static String HOST = "http://192.168.0.108";//接口地址
    //正式地址
//    public static String IMGHOST = "http://img.leqi.cool/";//图片地址
//    public static String HOST = "http://app.leqi.cool";//接口地址


    public static final String PROJECT_NAME = "";
    public static final String API = "/app/";

    //headrs验证信息
    public static final String APIKEY = "4l7MbUSW73enFSuL3kEQCiCa9596MgKm";
    public static final String XVERSION = "1.0.0";
    public static final String Accept = "application/json";
    public static final String ClientType = "android";


    //获取上传文件token
    public static final String UpLoadToken = HOST + API + "upload/get/token";
    //更新
    public static final String Upgrade = HOST + API + "article/sys-upgrade";
    //引导页
    public static final String Guide = HOST + API + "app-banner/index";
    //发送验证码
    public static final String Code = HOST + API + "sms/send";
    //验证码登录
    public static final String Login1 = HOST + API + "user/login/sms";
    //登录
    public static final String Login2 = HOST + API + "user/login/password";
    //绑定手机号
    public static final String BindingPhone = HOST + API + "/api/v1/user/binding_phone";
    //图片上传
    public static final String UpFile = HOST + API + "/apiupload/picture";
    //退出登录
    public static final String LoginOut = HOST + API + "user/logout";
    //搜索
    public static final String Search = HOST + API + "/api/v1/public/get_store_search_list";
    //收藏
    public static final String ShouChang = HOST + API + "/api/v1/user/user_collection_increase";
    //取消收藏
    public static final String QuXiaoShouChang = HOST + API + "/api/v1/user/user_collection_del";
    //加入购物车
    public static final String ADDShop = HOST + API + "/api/v1/public/user_cart_add";
    //评价列表
    public static final String PingJiaList = HOST + API + "/api/v1/public/get_goods_eval_list";
    //服务分类列表
    public static final String ServiceList_all = HOST + API + "/api/v1/public/service_list_all";
    /**
     * ********************************商户*****************************************
     */
    public static final String Fragment1 = HOST + API + "merchant";
    //商户列表
    public static final String ShopList = HOST + API + "merchant/record";
    //商户详情
    public static final String ShopDetail = HOST + API + "merchant/detail";
    /**
     * ********************************门店*****************************************
     */
    public static final String Fragment2 = HOST + API + "/api/v1/techn/techn_accept_order_list";
    //接车
    public static final String AddJieChe = HOST + API + "/api/v1/techn/techn_sedan_increase";
    //待分配
    public static final String Fragment2_1 = HOST + API + "/api/v1/techn/techn_sedan_list";
    //待施工=进行中=待复检=待完工=已提车
    public static final String Fragment2_2 = HOST + API + "/api/v1/techn/techn_sedan_waitfor_list";
    //添加项目
    public static final String AddProject = HOST + API + "/api/v1/techn/techn_testing_details_increase";
    //修改项目
    public static final String ChageProject = HOST + API + "/api/v1/techn/techn_testing_details_modify";
    //修改项目状态
    public static final String ChageProjectType = HOST + API + "/api/v1/techn/techn_sedan_testing_details_modify_state";
    //修改服务状态
    public static final String ChageServiceType = HOST + API + "/api/v1/techn/techn_sedan_order_service_modify_state";
    //修改订单状态
    public static final String ChageOrderType = HOST + API + "/api/v1/techn/techn_sedan_modify_state";
    //救援列表
    public static final String Fragment2_3 = HOST + API + "/api/v1/techn/techn_rescue_list";
    //救援状态
    public static final String JiuYuan_Type = HOST + API + "/api/v1/techn/techn_rescue_query";
    //立即救援
    public static final String JiuYuan_Now = HOST + API + "/api/v1/techn/techn_rescue_save";
    //统计数据
    public static final String Fragment2_TongJi = HOST + API + "/api/v1/techn/console_statistics";
    //签到-提交
    public static final String SignIn_Up = HOST + API + "/api/v1/techn/techn_signin";
    //签到-当天签到情况
    public static final String SignIn_Today = HOST + API + "/api/v1/techn/techn_signin_day";
    //签到-记录
    public static final String SignIn_List = HOST + API + "/api/v1/techn/techn_signin_list_page";
    //预计完成时间
    public static final String YuJiTime = HOST + API + "/api/v1/techn/save_estimate_time";
    //预计保养时间
    public static final String BaoYangTime = HOST + API + "/api/v1/techn/set_maintain_time";
    //修改服务内容
    public static final String ChangeService = HOST + API + "/api/v1/techn/techn_sedan_order_service_modify_str";
    /**
     * ********************************设备*****************************************
     */
    public static final String Fragment3 = HOST + API + "/api/v1/techn/techn_forum_post_page_list";
    //发布活动
    public static final String AddHuoDong = HOST + API + "/api/v1/techn/techn_activity_increase";
    //发布招聘
    public static final String AddZhaoPin = HOST + API + "/api/v1/techn/techn_forum_recruit_increase";
    //发布技术交流
    public static final String AddJiShuJiaoLiu = HOST + API + "/api/v1/techn/techn_exchange_increase";
    //技术交流详情
    public static final String JiShuJiaoLiuDetail = HOST + API + "/api/v1/techn/techn_post_details";
    //技术交流详情-回复
    public static final String JiShuJiaoLiuDetail_HuiFu = HOST + API + "/api/v1/techn/techn_forum_reply_increase";
    //技术交流详情-加入
    public static final String JiShuJiaoLiuDetail_JiaRu = HOST + API + "/api/v1/techn/techn_circle_plus";
    //发布维修案例
    public static final String AddWeiXiuAnLi = HOST + API + "/api/v1/techn/techn_forum_case_increase";
    //发布机友求助
    public static final String AddJiYouQiuZhu = HOST + API + "/api/v1/techn/techn_help_increase";
    //机友求助-回复
    public static final String JiYouQiuZhu_HuiFu = HOST + API + "/api/v1/techn/techn_forum_reply_increase";
    //发布工具租赁
    public static final String AddGongJuZuLin = HOST + API + "/api/v1/techn/techn_tool_increase";
    //发布店铺出租
    public static final String AddDianPuChuZu = HOST + API + "/api/v1/techn/techn_shop_lease_increase";
    //发布库存配件
    public static final String AddKuCunPeiJian = HOST + API + "/api/v1/techn/techn_parts_increase";
    //喜欢
    public static final String Like = HOST + API + "/api/v1/techn/techn_activity_fabulous";
    /**
     * ********************************数据*****************************************
     */
    public static final String Fragment4 = HOST + API + "/api/v1/techn/techn_forum_post_page_list";
    /**
     * ********************************我的*****************************************
     */
    public static final String Fragment5 = HOST + API + "user/center";
    //银行卡设置
    public static final String BankCard = HOST + API + "user/bind/bank";
    //修改个人信息
    public static final String ChangeProfile = HOST + API + "user/profile";
    //修改登录密码
    public static final String ChangePassword = HOST + API + "user/forget/password";
    //交易密码
    public static final String TransactionPassword = HOST + API + "user/set/trade/password";
    //我的收益
    public static final String MyIncome = HOST + API + "wallet";
    //收入列表
    public static final String WalletIn = HOST + API + "wallet/in";
    //支出列表
    public static final String WalletOut = HOST + API + "wallet/out";
    //提现
    public static final String TakeCash = HOST + API + "withdrawal";
    //提现列表
    public static final String TakeCashList = HOST + API + "withdrawal/record";
    //提现详情
    public static final String TakeCashDetail = HOST + API + "withdrawal/detail";
    //帮助列表
    public static final String Help = HOST + API + "help";
    //在线客服
    public static final String OnlineService = HOST + API + "onlineConsult";
    //创建留言
    public static final String AddMessage = HOST + API + "online/consult/add";
    //关于我们
    public static final String About = HOST + API + "about";
    //分润记录
    public static final String ShareProfitList = HOST + API + "order/profit";
    //工单列表
    public static final String WorkList = HOST + API + "work/orders/record";
    //添加设备工单
    public static final String AddDeviceWorkList = HOST + API + "work/orders/device/fault";
    //添加订单工单
    public static final String AddOrderList = HOST + API + "app/work/orders/order";
    //添加其他工单
    public static final String AddOtherList = HOST + API + "work/orders/other";
    //接手工单
    public static final String WorkList_JieShou = HOST + API + "work/orders/fetch";
    //工单详情
    public static final String WorkListDetail = HOST + API + "work/orders/detail";
    //我的车辆
    public static final String MyCar = HOST + API + "/api/v1/user/user_sedan_list";
    //获取汽车品牌
    public static final String CarNameList = HOST + API + "/api/v1/public/user_sedan_brand_list";
    //添加车辆
    public static final String AddCar = HOST + API + "/api/v1/user/user_sedan_add";
    //足迹列表
    public static final String Footprint = HOST + API + "/api/v1/user/user_footprint_list_page";
    //门店详情
    public static final String StoreDetail = HOST + API + "/api/v1/public/get_store_details";
    //问答
    public static final String StoreDetail_WenDa = HOST + API + "/api/v1/public/user_store_ques_ans_list";
    //门店服务分类列表
    public static final String ServiceList_Store = HOST + API + "/api/v1/public/get_store_service_content";
    //回复问答
    public static final String HuiDa = HOST + API + "/api/v1/public/user_store_ques_ans_add";
    //删除足迹
    public static final String DeleteFootprint = HOST + API + "/api/v1/user/user_footprint_del";
    //删除收藏
    public static final String DeleteCollect = HOST + API + "/api/v1/user/user_collection_del";
    //我的报价
    public static final String MyBaoJia = HOST + API + "/api/v1/techn/techn_inquiry_list";
    //我的发布
    public static final String MyPublish = HOST + API + "/api/v1/techn/techn_wo_release_list";
    //删除报价
    public static final String DeleteBaoJia = HOST + API + "/api/v1/techn/techn_inquiry_list";
    //提交报价
    public static final String UpBaoJia = HOST + API + "/api/v1/user/inquiry_demand_project_offer";
    //订单详情
    public static final String OrderDetail = HOST + API + "/api/v1/techn/techn_sedan_details";
    //添加服务
    public static final String ADDService = HOST + API + "/api/v1/techn/add_order_server";
    //店铺商品列表-选择商品
    public static final String SelectGoods = HOST + API + "/api/v1/public/get_store_goods";
    //添加商品
    public static final String ADDGoods = HOST + API + "/api/v1/techn/add_order_server_goods";
    //添加商品-自助添加
    public static final String ADDGoods_zizhu = HOST + API + "/api/v1/techn/add_goods";
    //删除服务
    public static final String DeleteService = HOST + API + "/api/v1/techn/techn_sedan_order_service_del";
    //删除商品
    public static final String DeleteGoods = HOST + API + "/api/v1/techn/techn_sedan_goods_del";
    //删除项目
    public static final String DeleteProject = HOST + API + "/api/v1/techn/testing_details_del";
    //店铺所属技师
    public static final String JiShiList = HOST + API + "/api/v1/techn/techn_store_list";
    //分配技师
    public static final String FenPeiJiShi = HOST + API + "/api/v1/techn/techn_sedan_distribute";
    //我的评价
    public static final String MyComment = HOST + API + "/api/v1/techn/techn_eval_list";
    //投诉记录
    public static final String ComplaintList = HOST + API + "/api/v1/techn/techn_complaint_list";
    //客服列表
    public static final String ServiceCenter = HOST + API + "/api/v1/user/get_kf_list";
    //申述
    public static final String Appeal = HOST + API + "/api/v1/techn/techn_complaint_increase";
    //回复
    public static final String Reply = HOST + API + "/api/v1/techn/techn_eval_add_reply";
    //优惠券
    public static final String Coupon = HOST + API + "/api/v1/user/user_coupon_list";
    //使用优惠券
    public static final String USE_Coupon = HOST + API + "/api/v1/techn/use_coupon";

    /**
     * 拼接请求路径
     *
     * @PARAM URI
     * @RETURN
     */
    public static String getURL(String uri) {
        return HOST + PROJECT_NAME + API + uri;
    }
}
