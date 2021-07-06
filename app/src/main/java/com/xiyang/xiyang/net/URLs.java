package com.xiyang.xiyang.net;

/**
 * URL路径处理类
 */
public class URLs {
    //测试地址
//    public static String IMGHOST = "http://test.xiyangkeji.net";//图片地址
//    public static String HOST = "http://test.xiyangkeji.net";//接口地址
    public static String IMGHOST = "http://192.168.0.199:8082";//图片地址
    public static String HOST = "http://192.168.0.199:8082";//接口地址
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
    //发送验证码-个人中心
    public static final String Code_yonghu = HOST + "/user/sms/verificationCode/issue";
    //发送验证码-交易密码
    public static final String Code_jiaoyi = HOST + "/user/tradePassword/verificationCode/issue";
    //发送验证码-银行卡
    public static final String Code_yinhangka = HOST + "/user/bank/verificationCode/issue";
    //发送验证码-提现
    public static final String Code_tixian = HOST + "/sms/manage/verificationCode/issue";
    //验证码登录
    public static final String Login1 = HOST + "/user/login";
    //登录
    public static final String Login2 = HOST + "/user/login";
    //绑定手机号
    public static final String BindingPhone = HOST + API + "/api/v1/user/binding_phone";
    //图片上传
    public static final String UpFile = HOST + "/aliyunoss/file/manage/upload";
    //退出登录
    public static final String LoginOut = HOST + "/auth/token/logout";
    //行业列表（2级）
    public static final String Industry = HOST + "/baseinfo/industry/dropDown/";
    //省市区列表（3级）
    public static final String Region = HOST + "/baseinfo/china/region/dropDown";
    //公共参数获取
    public static final String Common = HOST + API + "common";
    //公共-仓库
    public static final String Warehouse = HOST + API + "warehouse";
    //评价列表
    public static final String PingJiaList = HOST + API + "/api/v1/public/get_goods_eval_list";
    //服务分类列表
    public static final String ServiceList_all = HOST + API + "/api/v1/public/service_list_all";
    /**
     * ********************************商户*****************************************
     */
    public static final String Fragment1 = HOST + API + "merchant";
    //添加商户
    public static final String AddShop = HOST + "/bd/merchants/Merchants";
    //添加商户-检测账号
    public static final String AddShop_Detect = HOST + "/bdservice/v2/Merchants";
    //商户列表
    public static final String ShopList = HOST + "/bd/merchants/app/merchant/record";
    //商户详情
    public static final String ShopDetail = HOST + "/bd/merchants/app/merchant/detail/";
    //添加合同-签约
    public static final String AddContract_qianyue = HOST + "/bd/bdservice/signContracts";
    //添加合同-新增
    public static final String AddContract_xinzeng = HOST + "/bd/bdservice/signContracts";
    //添加合同-回收
    public static final String AddContract_huishou = HOST + "/bd/bdservice/signContracts";
    //添加合同-换绑
    public static final String AddContract_huanbang = HOST + "/bd/bdservice/signContracts";
    //添加合同-修改
    public static final String AddContract_xiugai = HOST + "/bd/bdservice/signContracts";
    //添加合同-续签
    public static final String AddContract_xuqian = HOST + "/bd/bdservice/signContracts";
    //添加合同-取消
    public static final String AddContract_quxiao = HOST + "/bd/bdservice/signContracts";
    //添加合同-调价
    public static final String AddContract_tiaojia = HOST + "/bd/bdservice/signContracts";
    //我的合同列表
    public static final String MyContract = HOST + "/bd/bdservice/getContractsListInfo";
    //合同详情
    public static final String ContractDetail = HOST + API + "merchant/detail";
    //划转商户
    public static final String TransferShop = HOST + API + "merchant/transfer";
    /**
     * ********************************管理*****************************************
     */
    public static final String Fragment1_m = HOST + API + "manage";
    //选择权限城市
    public static final String MyCity = HOST + API + "user/position/record";
    //添加员工
    public static final String AddStaff = HOST + API + "user/add";
    //我的下级员工
    public static final String Subordinate = HOST + API + "manage/subordinate";
    //员工详情
    public static final String StaffDetail = HOST + API + "manage/subordinate/detail";
    //我的城市
    public static final String MyCity_RM = HOST + API + "rm/city";
    //我的市区
    public static final String MyCity_CM = HOST + API + "cm/area";
    //调整上级
    public static final String AdjustSuperior = HOST + API + "manage/change/role";
    //调整岗位
    public static final String AdjustJob = HOST + API + "manage/change/role";
    //选择员工
    public static final String SelectStaff = HOST + API + "manage/subordinate";
    //人事记录
    public static final String PersonnelList = HOST + API + "manage/subordinate";
    /**
     * ********************************分派*****************************************
     */
    public static final String Fragment2_m = HOST + API + "point";
    public static final String DispatchShop = HOST + API + "point";

    /**
     * ********************************审批*****************************************
     */
    public static final String Fragment3_m = HOST + API + "approval";
    //合同审批
    public static final String ApproveContractList = HOST + "/bd/bdservice/app/approval/contract";
    //审核详情
    public static final String ApproveDetail = HOST + "/bd/bdservice/app/bdm/verfiy/detail/";
    //审核合同
    public static final String ApproveContract = HOST + "/bd/bdservice/app/approval/deal";
    /**
     * ********************************门店*****************************************
     */
    public static final String Fragment2 = HOST + API + "store";
    //拜访列表
    public static final String MyVisitList = HOST + API + "store/visit/record";
    //拜访详情
    public static final String VisitDetail = HOST + API + "store/visit/detail";
    //门店列表
    public static final String MyStoreList = HOST + API + "store/record";
    //添加门店
    public static final String AddStore = HOST + API + "store/add";
    //门店详情
    public static final String StoreDetail = HOST + API + "store/detail";
    //房号管理
    public static final String RoomNoManagement = HOST + "/applet/room/queryRoomByStoreIdAndParentId";
    //添加房号
    public static final String AddRoom = HOST + "/applet/room/addStoreRoom";
    //修改房号
    public static final String ChageRoom = HOST + "/applet/room/editStoreRoom";
    //删除房号
    public static final String DeleteRoom = HOST + "/applet/room/deleteStoreRoom";
    //员工管理
    public static final String StaffManagement = HOST + "/applet/user/queryUserList";
    //添加员工
    public static final String AddStaff_BD = HOST + API + "store/worker/add";
    //删除员工
    public static final String DeleteStaff = HOST + API + "store/worker/delete";
    //关闭门店
    public static final String CloseStore = HOST + API + "store/close";
    //划转门店
    public static final String TransferStore = HOST + API + "store/transfer";
    //修改账户信息
    public static final String ChangeStoreAccount = HOST + API + "store/account";
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
    public static final String Fragment3 = HOST + API + "device";
    //工单列表
    public static final String WorkList = HOST + API + "work/orders/record";
    //添加设备工单
    public static final String AddDeviceWorkList = HOST + API + "work/orders/device/fault";
    //添加订单工单
    public static final String AddOrderList = HOST + API + "work/orders/order";
    //添加其他工单
    public static final String AddOtherList = HOST + API + "work/orders/other";
    //接手工单
    public static final String WorkList_JieShou = HOST + API + "work/orders/fetch";
    //工单详情
    public static final String WorkListDetail = HOST + API + "work/orders/detail";
    //处理工单
    public static final String ChangeWorkList = HOST + API + "work/orders/deal";
    //添加拜访
    public static final String AddVisit = HOST + API + "store/visit/add";
    //事务列表
    public static final String AffairList = HOST + API + "transactions";
    //事务详情
    public static final String AffairDetail = HOST + API + "transactions/detail";
    //事务详情-申领-邮寄方式
    public static final String AffairDetail_ShenLing = HOST + API + "device/apply/update";
    //设备详情
    public static final String DeviceDetail = HOST + API + "device/detail";
    //安装设备
    public static final String InstallDevice = HOST + API + "device/install/add";
    //调试设备
    public static final String DebugDevice = HOST + API + "device/properties";
    //控制设备
    public static final String DeviceSetUp = HOST + API + "device/operate";
    //换绑设备
    public static final String ChangeTieDevice = HOST + API + "device/exchange/add";
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
    public static final String Fragment5 = HOST + "/user/center";
    //银行卡设置
    public static final String BankCard = HOST + "/finace/user/bank/get";
    //银行列表
    public static final String BankList = HOST + "/finace/banks/supportList";
    //银行卡设置
    public static final String BankCardSet = HOST + "/finace/user/bank/bind";
    //个人信息
    public static final String MyProfile = HOST + "/user/set/center/getAppPersonalData";
    //修改个人信息
    public static final String ChangeProfile = HOST + "/user/set/center/updateAppPersonalData";
    //修改登录密码
    public static final String ChangePassword = HOST + "/user/set/center/setLoginPassword";
    //交易密码
    public static final String TransactionPassword = HOST + "/finace/user/tradePassword/setTradePassword";
    //我的收益
    public static final String MyIncome = HOST + API + "wallet";
    //收入列表
    public static final String WalletIn = HOST + API + "wallet/in";
    //支出列表
    public static final String WalletOut = HOST + API + "wallet/out";
    //提现-信息
    public static final String TakeCash_info = HOST + "/finace/user/withdrawalApply/withdrawalPageData";
    //提现
    public static final String TakeCash = HOST + "/finace/user/withdrawalApply/withdrawal";
    //提现列表
    public static final String TakeCashList = HOST + "/finace/user/withdrawalApply/withdrawalRecord";
    //提现详情
    public static final String TakeCashDetail = HOST + "/finace/user/withdrawalApply/withdrawalRecord/detail/";
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
    //我的设备列表
    public static final String MyDevice = HOST + API + "device/record";
    //系统公告
    public static final String Information = HOST + "/applet/notice/openPage";
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
