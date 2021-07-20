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
    //发送验证码-登录
    public static final String Code_denglu = HOST + "/user/sendSms";
    //发送验证码-个人中心
    public static final String Code_yonghu = HOST + "/user/sms/verificationCode/issue";
    //发送验证码-交易密码
    public static final String Code_jiaoyi = HOST + "/user/tradePassword/verificationCode/issue";
    //发送验证码-银行卡
    public static final String Code_yinhangka = HOST + "/user/bank/verificationCode/issue";
    //发送验证码-提现
    public static final String Code_tixian = HOST + "/sms/manage/verificationCode/issue";
    //发送验证码-添加员工
    public static final String Code_Staff = HOST + "/applet/user/sendCode";
    //验证码登录
    public static final String Login1 = HOST + "/user/login";
    //登录
    public static final String Login2 = HOST + "/user/login";
    //绑定手机号
    public static final String BindingPhone = HOST + API + "/api/v1/user/binding_phone";
    //图片上传
    public static final String UpFile = HOST + "/aliyunoss/file/manage/upload";
    //退出登录
    public static final String LoginOut = HOST + "/applet/user/loginOut";
    //行业列表（2级）
    public static final String Industry = HOST + "/baseinfo/industry/dropDown/";
    //省市区列表（3级）
    public static final String Region = HOST + "/baseinfo/china/region/dropDown";
    //公共参数获取
    public static final String Common = HOST + "/baseinfo/tags/getByCategory/";
    //公共-仓库
    public static final String Warehouse = HOST + API + "warehouse";
    //枚举列表
    public static final String MeiJuList = HOST + "/service-ucenter/enum/list/all";
    //服务分类列表
    public static final String ServiceList_all = HOST + API + "/api/v1/public/service_list_all";
    /**
     * ********************************商户*****************************************
     */
    public static final String Fragment1 = HOST + "/bd/merchants/app/merchant";
    //添加商户
    public static final String AddShop = HOST + "/bd/merchants/add";
    //添加商户-检测账号
    public static final String AddShop_Detect = HOST + "/user/verifyPhone";
    //商户列表
    public static final String ShopList = HOST + "/bd/merchants/list";
    //商户详情
    public static final String ShopDetail = HOST + "/bd/merchants/app/merchant/detail/";
    //添加合同-签约
    public static final String AddContract_qianyue = HOST + "/bd/bdservice/signContracts";
    //添加合同-新增
    public static final String AddContract_xinzeng = HOST + "/bd/bdservice/applyContracts";
    //添加合同-回收
    public static final String AddContract_huishou = HOST + "/bd/bdservice/reduceContracts";
    //添加合同-换绑
    public static final String AddContract_huanbang = HOST + "/bd/bdservice/changeBindContracts";
    //添加合同-修改
    public static final String AddContract_xiugai = HOST + "/bd/bdservice/updateMerchantContracts";
    //添加合同-续签
    public static final String AddContract_xuqian = HOST + "/bd/bdservice/addRenewalContracts";
    //添加合同-取消
    public static final String AddContract_quxiao = HOST + "/bd/bdservice/cancelContracts";
    //添加合同-调价
    public static final String AddContract_tiaojia = HOST + "/bd/bdservice/adjustPriceContracts";
    //我的合同列表
    public static final String MyContract = HOST + "/bd/bdservice/getContractsListInfo";
    //合同详情
    public static final String ContractDetail = HOST + "/bd/bdservice/getContractsDetailInfo/";
    //划转商户
    public static final String TransferShop = HOST + "/bd/merchant/transfer/add/apply";
    /**
     * ********************************管理*****************************************
     */
    public static final String Fragment1_m = HOST + "/service-ucenter/organ/home";
    //员工负责城市-无统计数据
    public static final String StaffCity = HOST + "/organ/region/listLoginOrganUserCity";
    //选择权限城市-有统计数据
    public static final String MyCity = HOST + "/service-ucenter/organ/region/getLoginOrganUserCityStatistic";
    //添加员工
    public static final String AddStaff = HOST + API + "user/add";
    //我的下级员工
    public static final String Subordinate = HOST + "/service-ucenter/organ/statisticOrganUserLevelHr";
    //员工详情
    public static final String StaffDetail = HOST + API + "manage/subordinate/detail";
    //调整上级
    public static final String AdjustSuperior = HOST + "/service-ucenter/organ/change/apply/upper";
    //调整市场
    public static final String AdjustMarket = HOST + "/service-ucenter/organ/change/apply/market";
    //调整岗位
    public static final String AdjustJob = HOST + "/service-ucenter/organ/change/apply/upOrDown";
    //选择员工
    public static final String SelectStaff = HOST + API + "manage/subordinate";
    //采购审批列表
    public static final String CaiGouList = HOST + "/purchase/purchase/apply/page";
    //采购审批详情
    public static final String CaiGouDetail = HOST + "/purchase/purchase/apply/detail/";
    //人事记录
    public static final String PersonnelList = HOST + "/service-ucenter/organ/change/apply/list";
    //仓库信息
    public static final String CangKu = HOST + "/purchase/purchase/CmWarehouse/get";
    //采购申请
    public static final String AddBuy = HOST + "/purchase/purchase/apply/add";
    /**
     * ********************************分派*****************************************
     */
    public static final String Fragment2_m = HOST + API + "point";
    //商户分派列表
    public static final String DispatchShopList = HOST + "/bd/merchant/transfer/manageUser/page";
    //商户上报
    public static final String DispatchShop_ShangBao = HOST + "/bd/merchant/transfer/report/";
    //商户指派
    public static final String DispatchShop = HOST + "/bd/merchant/transfer/assignMerchant";
    //门店分派列表
    public static final String DispatchStoreList = HOST + "/bd/store/transfer/manageUser/page";
    //门店上报
    public static final String DispatchStore_ShangBao = HOST + "/bd/store/transfer/report/";
    //门店指派
    public static final String DispatchStore = HOST + "/bd/store/transfer/assignMerchant";
    //工单分派列表
    public static final String DispatchWorkList = HOST + "/bd/work/orders/manageUser/page";
    //工单上报
    public static final String DispatchWork_ShangBao = HOST + "/bd/work/orders/report/";
    //工单指派
    public static final String DispatchWork = HOST + "/bd/work/orders/assignWorkOrder";

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
    //审核采购申请
    public static final String ApproveContract_CaiGou = HOST + "/purchase/purchase/apply/handle";

    /**
     * ********************************门店*****************************************
     */
    public static final String Fragment2 = HOST + "/bd/store/home";
    //拜访列表
    public static final String MyVisitList = HOST + "/bd/store/visit/list";
    //拜访详情
    public static final String VisitDetail = HOST + "/bd/store/visit/getById/";
    //门店列表
    public static final String MyStoreList = HOST + "/bd/store/listStore";
    //添加门店
    public static final String AddStore = HOST + "/bd/store/addStore";
    //门店详情
    public static final String StoreDetail = HOST + "/bd/store/select/";
    //房号管理
    public static final String RoomNoManagement = HOST + "/bd/storeRoom/appQueryRoomByStoreIdAndParentId";
    //添加房号
    public static final String AddRoom = HOST + "/bd/storeRoom/appAddRoom";
    //修改房号
    public static final String ChageRoom = HOST + "/bd/storeRoom/appEditRoom";
    //删除房号
    public static final String DeleteRoom = HOST + "/bd/storeRoom/appDeleteRoom";
    //员工管理
    public static final String StaffManagement = HOST + "/user/appQueryUserList";
    //添加员工
    public static final String AddStaff_BD = HOST + "/user/appSaveFontUser";
    //删除员工
    public static final String DeleteStaff = HOST + "/user/appDeleteUser";
    //关闭门店
    public static final String CloseStore = HOST + "/bd/store/close";
    //划转门店
    public static final String TransferStore = HOST + "/bd/store/transfer/add";
    //修改账户信息
    public static final String ChangeStoreAccount = HOST + API + "store/account";

    /**
     * ********************************设备*****************************************
     */
    public static final String Fragment3 = HOST + "/device/bd/homePageData";
    //工单列表
    public static final String WorkList = HOST + "/bd/work/orders/page";
    //工单列表-待接工单
    public static final String WorkList_DaiJie = HOST + "/bd/work/orders/missed/page";
    //根据设备序列号获取设备信息
    public static final String DeviceInfo = HOST + "/device/getByHostName/";
    //添加工单-故障列表
    public static final String AddWorkList_GuZhang = HOST + "/bd/work/orders/failureReasonList/";
    //添加设备工单
    public static final String AddDeviceWorkList = HOST + "/bd/work/orders/reportDeviceFailure";
    //添加订单工单
    public static final String AddOrderList = HOST + API + "/bd/work/orders/reportOrderFailure";
    //添加其他工单
    public static final String AddOtherList = HOST + API + "/bd/work/orders/reportOtherFailure";
    //接手工单
    public static final String WorkList_JieShou = HOST + "/bd/work/orders/takeOver/";
    //工单详情
    public static final String WorkListDetail = HOST + "/bd/work/orders/get/detail/";
    //处理工单
    public static final String ChangeWorkList = HOST + "/bd/work/orders/deal";
    //添加拜访-远程
    public static final String AddVisit_YuanCheng = HOST + "/bd/store/visit/addRemotely";
    //添加拜访-上门
    public static final String AddVisit_ShangMen = HOST + "/bd/store/visit/addOnSite";
    //添加拜访-陌生
    public static final String AddVisit_MoSheng = HOST + "/bd/store/visit/addStrange";
    //事务列表
    public static final String AffairList = HOST + "/bd/transaction/listTransaction";
    //事务详情
    public static final String AffairDetail = HOST + "/bd/store/getDeviceTranscations/";
    //事务详情-申领-邮寄方式
    public static final String AffairDetail_ShenLing = HOST + "/bd/device/apply/add/logistic";
    //事务详情-签收
    public static final String AffairDetail_QianShou = HOST + "/bd/device/apply/sign/logistic/";
    //设备详情
    public static final String DeviceDetail = HOST + "/service-device/device/sysUser/detail/";
    //安装设备
    public static final String InstallDevice = HOST + "/device/install/install";
    //开启设备
    public static final String Device = HOST + "/device/aliyuniot/controlDevice";
    //调试设备
    public static final String DebugDevice = HOST + API + "/service-device/properties";
    //控制设备
    public static final String DeviceSetUp = HOST + API + "/service-device/operate";
    //换绑设备
    public static final String ChangeTieDevice = HOST + "/service-device/device/deviceChangeBind";

    /**
     * ********************************数据*****************************************
     */
    public static final String Fragment4 = HOST + API + "/api/v1/techn/techn_forum_post_page_list";
    /**
     * ********************************我的*****************************************
     */
    public static final String Fragment5 = HOST + "/user/getMemberInfo";
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
    public static final String MyIncome = HOST + "/finace/balance/record/summary/data";
    //我的收益列表
    public static final String MyIncomeList = HOST + "/finace/balance/record/balanceChangePage";
    //提现-信息
    public static final String TakeCash_info = HOST + "/finace/user/withdrawalApply/withdrawalPageData";
    //提现
    public static final String TakeCash = HOST + "/finace/user/withdrawalApply/withdrawal";
    //提现列表
    public static final String TakeCashList = HOST + "/finace/user/withdrawalApply/withdrawalRecord";
    //提现详情
    public static final String TakeCashDetail = HOST + "/finace/user/withdrawalApply/withdrawalRecord/detail/";
    //帮助列表
    public static final String Help = HOST + "/applet/help/queryProblemList";
    //在线客服
    public static final String OnlineService = HOST + "/baseinfo/onlineConsult/page";
    //创建留言
    public static final String AddMessage = HOST + "/baseinfo/onlineConsult/create";
    //关于我们
    public static final String About = HOST + API + "about";
    //分润记录
    public static final String ShareProfitList = HOST + "/order/consumerOrder/appOpenQueryOderSharePage";
    //我的设备列表
    public static final String MyDevice = HOST + "/service-device/device/sysUser/page";
    //系统公告
    public static final String Information = HOST + "/applet/notice/openPage";

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
