package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cretin.tools.scancode.CaptureActivity;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.lihang.ShadowLayout;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.AffairDetailModel;
import com.xiyang.xiyang.model.CommonModel;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.FileUtil;
import com.xiyang.xiyang.utils.MyChooseImages;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by Mr.Z on 2021/3/30.
 * 事务详情
 */
public class AffairDetailActivity extends BaseActivity {
    String id = "", apply_Type = "1";
    int type = 1, item_wuliugongsi = -1;
    List<CommonModel.ListBean> list_wuliugongsi = new ArrayList<>();
    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;

    AffairDetailModel model;
    TextView tv_name, tv_shop, tv_num, tv_addr;
    ImageView imageView1, imageView2;
    /**
     * 事务信息
     */
    LinearLayout ll_shopinfo;
    private RecyclerView rv_shiwu;
    List<KeyValueModel> list_shiwu = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_shiwu;
    /**
     * 申领信息
     */
    LinearLayout ll_contract, ll_shenlingfangshi, ll_shouhuoren, ll_lianxifangshi, ll_shouhuodizhi,
            ll_xiangxidizhi, ll_wuliugongsi, ll_kuaididanhao, ll_tuihuicangku, ll_qianshouren, ll_qianshourendianhua,
            ll_qianshoudizhi;
    EditText et_shouhuoren, et_lianxifangshi, et_xiangxidizhi, et_kuaididanhao;
    ImageView iv_shangchuanzhaopian;
    TextView tv_shenlingxinxi, tv_shenlingfangshi, tv_shouhuodizhi, tv_dengdaifahuo, tv_fangshi, tv_wuliugongsi,
            tv_shangchuanzhaopian, tv_tuihuicangku, tv_qianshouren, tv_qianshourendianhua, tv_qianshoudizhi, tv_confirm;
    ShadowLayout sl_daifahuo;
    private RecyclerView rv_wuliu;
    List<AffairDetailModel.LogisticBean.ExpressInfoBean> list_wuliu = new ArrayList<>();
    CommonAdapter<AffairDetailModel.LogisticBean.ExpressInfoBean> mAdapter_wuliu;
    String expressWay = "1";//邮寄方式 1-自取，2-邮寄
    List<String> list_fangshi = new ArrayList<>();
    int itme_fangshi = 0;
    File imgfile = null;
    //省市
    CityConfig cityConfig = null;
    CityPickerView mPicker = new CityPickerView();
    String receiveName = "", phone = "", provinceId = "", cityId = "", areaId = "", address = "", scene = "", transportId = "", transportCompany = "";
    /**
     * 安装记录
     */
    ShadowLayout sl_scan;
    LinearLayout ll_shenhe;
    RecyclerView rv_anzhuang;
    List<AffairDetailModel.ListBean> list_anzhuang = new ArrayList<>();
    CommonAdapter<AffairDetailModel.ListBean> mAdapter_anzhuang;
    TextView iv_scan, tv_scan, tv_allnum, tv_innum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affairdetail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                params.clear();
//                params.put("id", id);
                request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        ll_tab3 = findViewByID_My(R.id.ll_tab3);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_tab3.setOnClickListener(this);
        tv_tab1 = findViewByID_My(R.id.tv_tab1);
        tv_tab2 = findViewByID_My(R.id.tv_tab2);
        tv_tab3 = findViewByID_My(R.id.tv_tab3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);

        tv_name = findViewByID_My(R.id.tv_name);
        tv_shop = findViewByID_My(R.id.tv_shop);
        tv_num = findViewByID_My(R.id.tv_num);
        tv_addr = findViewByID_My(R.id.tv_addr);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        /**
         * 事务信息
         */
        ll_shopinfo = findViewByID_My(R.id.ll_shopinfo);
        rv_shiwu = findViewByID_My(R.id.rv_shiwu);
        rv_shiwu.setLayoutManager(new LinearLayoutManager(this));

        /**
         * 申领信息
         */
        ll_contract = findViewByID_My(R.id.ll_contract);
        tv_shenlingxinxi = findViewByID_My(R.id.tv_shenlingxinxi);
        ll_shenlingfangshi = findViewByID_My(R.id.ll_shenlingfangshi);
        tv_shenlingfangshi = findViewByID_My(R.id.tv_shenlingfangshi);
        tv_fangshi = findViewByID_My(R.id.tv_fangshi);
        ll_shouhuoren = findViewByID_My(R.id.ll_shouhuoren);
        et_shouhuoren = findViewByID_My(R.id.et_shouhuoren);
        ll_lianxifangshi = findViewByID_My(R.id.ll_lianxifangshi);
        et_lianxifangshi = findViewByID_My(R.id.et_lianxifangshi);
        ll_shouhuodizhi = findViewByID_My(R.id.ll_shouhuodizhi);
        tv_shouhuodizhi = findViewByID_My(R.id.tv_shouhuodizhi);
        ll_xiangxidizhi = findViewByID_My(R.id.ll_xiangxidizhi);
        et_xiangxidizhi = findViewByID_My(R.id.et_xiangxidizhi);
        tv_shangchuanzhaopian = findViewByID_My(R.id.tv_shangchuanzhaopian);
        iv_shangchuanzhaopian = findViewByID_My(R.id.iv_shangchuanzhaopian);
        ll_kuaididanhao = findViewByID_My(R.id.ll_kuaididanhao);
        et_kuaididanhao = findViewByID_My(R.id.et_kuaididanhao);
        ll_wuliugongsi = findViewByID_My(R.id.ll_wuliugongsi);
        tv_wuliugongsi = findViewByID_My(R.id.tv_wuliugongsi);
        ll_tuihuicangku = findViewByID_My(R.id.ll_tuihuicangku);
        ll_qianshouren = findViewByID_My(R.id.ll_qianshouren);
        ll_qianshourendianhua = findViewByID_My(R.id.ll_qianshourendianhua);
        ll_qianshoudizhi = findViewByID_My(R.id.ll_qianshoudizhi);
        tv_tuihuicangku = findViewByID_My(R.id.tv_tuihuicangku);
        tv_qianshouren = findViewByID_My(R.id.tv_qianshouren);
        tv_qianshourendianhua = findViewByID_My(R.id.tv_qianshourendianhua);
        tv_qianshoudizhi = findViewByID_My(R.id.tv_qianshoudizhi);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        sl_daifahuo = findViewByID_My(R.id.sl_daifahuo);
        rv_wuliu = findViewByID_My(R.id.rv_wuliu);
        rv_wuliu.setLayoutManager(new LinearLayoutManager(this));
        tv_dengdaifahuo = findViewByID_My(R.id.tv_dengdaifahuo);
        //预先加载仿iOS滚轮实现的全部数据
        mPicker.init(this);
        cityConfig = new CityConfig.Builder()
                .title("选择地址")//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#eaeaea")//标题栏背景色
                .confirTextColor("#10A589")//确认按钮文字颜色
                .confirmText(getString(R.string.app_confirm))//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#10A589")//取消按钮文字颜色
                .cancelText(getString(R.string.app_cancel))//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(false)//是否显示半透明背景
                .visibleItemsCount(7)//显示item的数量
                .province("北京市")//默认显示的省份
                .city("北京市")//默认显示省份下面的城市
                .district("朝阳区")//默认显示省市下面的区县数据
                .provinceCyclic(false)//省份滚轮是否可以循环滚动
                .cityCyclic(false)//城市滚轮是否可以循环滚动
                .districtCyclic(false)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.textView1)//自定义item布局里面的textViewid
                .drawShadows(false)//滚轮不显示模糊效果
                .setLineColor("#80CDCDCE")//中间横线的颜色
                .setLineHeigh(2)//中间横线的高度
                .setShowGAT(false)//是否显示港澳台数据，默认不显示
                .build();


        //设置自定义的属性配置
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province1, CityBean city1, DistrictBean district1) {
                //省份province//城市city//地区district
                /*province = province1.getName().toString();
                city = city1.getName().toString();
                district = district1.getName().toString();

                textView1.setText(province1.getName().toString());
                textView2.setText(city1.getName().toString());
                textView3.setText(district1.getName().toString());*/
                areaId = province1.getName() + city1.getName() + district1.getName();
                tv_shouhuodizhi.setText(province1.getName() + city1.getName() + district1.getName());
            }

            @Override
            public void onCancel() {
            }
        });
        /**
         * 安装记录
         */
        sl_scan = findViewByID_My(R.id.sl_scan);
        ll_shenhe = findViewByID_My(R.id.ll_shenhe);
        rv_anzhuang = findViewByID_My(R.id.rv_anzhuang);
        rv_anzhuang.setLayoutManager(new LinearLayoutManager(this));
        iv_scan = findViewByID_My(R.id.iv_scan);
        tv_scan = findViewByID_My(R.id.tv_scan);
        tv_allnum = findViewByID_My(R.id.tv_allnum);
        tv_innum = findViewByID_My(R.id.tv_innum);


        changeUI();

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.ll_scan:
                //扫码
                /*ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(this, config);*/
                switch (model.getType()) {//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
                    case "1":
                        //主机
                        bundle.putString("transactionId", model.getRelationId());
                        bundle.putString("storeId", model.getStoreId());
                        bundle.putString("storeName", model.getStoreName());
                        CommonUtil.gotoActivityWithData(AffairDetailActivity.this, InstallDeviceActivity.class, bundle);
                        break;
                    case "2":
                        //4G模块
                        bundle.putString("transactionId", model.getRelationId());
                        CommonUtil.gotoActivityWithData(AffairDetailActivity.this, Change4GModuleActivity.class, bundle);
                        break;
                    case "3":
                        //过滤网

                        break;
                    case "4":
                        //回收
                        bundle.putString("relationId", model.getId());
//                        bundle.putString("contractId", model.getContractId());
                        CommonUtil.gotoActivityWithData(AffairDetailActivity.this, UntieDeviceActivity.class, bundle);
                        break;
                    case "5":
                        //换绑
                        bundle.putString("relationId", model.getRelationId());
                        bundle.putString("contractId", model.getContractId());
                        CommonUtil.gotoActivityWithData(AffairDetailActivity.this, ChangeTieDeviceActivity.class, bundle);
                        break;
                }
                break;
            case R.id.ll_tab1:
                //商户信息
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //合同信息
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //审核合同
                type = 3;
                changeUI();
                break;
            case R.id.iv_shangchuanzhaopian:
                //上传图片
                MyChooseImages.showPhotoDialog(AffairDetailActivity.this);
                break;
            case R.id.tv_shenlingfangshi:
                //选择申领方式
                dialogList_fangshi();
                break;
            case R.id.tv_shouhuodizhi:
                //选择地址
//                mPicker.showCityPicker();
                dialogList_chengshi("0");
                break;
            case R.id.tv_wuliugongsi:
                //物流公司
                dialogList_wuliugongsi(tv_wuliugongsi);
                break;

            case R.id.tv_confirm:
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    switch (expressWay) {
                        case "1":
                            //自取
                            Map<String, File> fileMap = new HashMap<>();
                            fileMap.put("file", imgfile);
                            params.clear();
                            OkhttpUtil.okHttpUploadMapFile(URLs.UpFile, fileMap, "file", params, headerMap, new CallBackUtil<String>() {
                                @Override
                                public String onParseResponse(Call call, Response response) {
                                    return null;
                                }

                                @Override
                                public void onFailure(Call call, Exception e, String err) {
                                    hideProgress();
                                    myToast("图片上传失败" + err);
                                }

                                @Override
                                public void onResponse(String response) {
                                    params.put("relationId", model.getRelationId());
                                    params.put("expressWay", expressWay + "");//1-自取，2-邮寄
                                    params.put("relationType", apply_Type);
                                    params.put("voucher", response);
                                    requestUpData(params, URLs.AffairDetail_ShenLing);
                                }
                            });

                            break;
                        case "2":
                            //邮寄
                            params.clear();
                            if (apply_Type.equals("4")) {//回收
                                params.put("relationId", model.getRelationId());
                                params.put("logisticId", model.getLogistic().getId());
                                params.put("warehouseId", model.getContract().getWarehouseId());
                                params.put("transportId", transportId);
                                params.put("transportCompany", transportCompany);
                                params.put("relationType", apply_Type);
                                requestUpData(params, URLs.AffairDetail_FaHuo);
                            } else {
                                params.put("relationId", model.getRelationId());
                                params.put("expressWay", expressWay + "");//1-自取，2-邮寄
                                params.put("relationType", apply_Type);
                                params.put("receiveName", receiveName);//收货人
                                params.put("phone", phone);//收货人联系方式
                                params.put("provinceId", provinceId);//省
                                params.put("cityId", cityId);//市
                                params.put("areaId", areaId);//区
                                params.put("address", address);
                                requestUpData(params, URLs.AffairDetail_ShenLing);
                            }
                            break;
                    }
                }
                break;
        }
    }

    @Override
    protected void initData() {
        list_fangshi.add("自取");
        list_fangshi.add("邮寄");

        id = getIntent().getStringExtra("id");
        apply_Type = getIntent().getStringExtra("apply_Type");
        sl_scan.setVisibility(View.VISIBLE);
        ll_tab2.setVisibility(View.VISIBLE);
        tv_fangshi.setText("申领方式");
        switch (apply_Type) {//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
            case "1":
                //主机
                iv_scan.setText("安装设备");
                tv_tab3.setText("安装记录");
                break;
            case "2":
                //4G模块
                iv_scan.setText("更换4G模组");
                tv_tab3.setText("更换记录");
                break;
            case "3":
                //过滤网
                sl_scan.setVisibility(View.GONE);
                break;
            case "4":
                //回收
                iv_scan.setText("回收设备");
                tv_tab3.setText("回收记录");
                tv_fangshi.setText("退回方式");
                requestWuLiu(URLs.Common + "LOGISTICS_COMPANY", params);//物流公司
                break;
            case "5":
                //换绑
                ll_tab2.setVisibility(View.GONE);
                iv_scan.setText("换绑设备");
                tv_tab3.setText("换绑记录");
                break;
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
//        params.put("id", id);
        request(params);
    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.AffairDetail + apply_Type + "/" + id, params, headerMap, new CallBackUtil<AffairDetailModel>() {
            @Override
            public AffairDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                finish();
            }

            @Override
            public void onResponse(AffairDetailModel response) {
                hideProgress();
                model = response;
                tv_name.setText(response.getStoreName());
                tv_shop.setText(response.getTypeName());
                tv_num.setText(response.getInstallType());
                tv_addr.setText(response.getInstallNum() + "台");
                Glide.with(AffairDetailActivity.this)
                        .load(model.getStoreImage())
//                                .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AffairDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片

                /**
                 * 事务信息
                 */
                list_shiwu.clear();
                switch (response.getType()) {//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
                    case "1":
                        //主机
                    case "2":
                        //4G模块
                    case "3":
                        //过滤网
                        list_shiwu.add(new KeyValueModel("类型设备", response.getTypeName()));
                        list_shiwu.add(new KeyValueModel("安装门店", response.getStoreName()));
                        list_shiwu.add(new KeyValueModel("安装台数", response.getInstallNum() + "台"));
                        list_shiwu.add(new KeyValueModel("创建时间", response.getCreateTime()));
                        list_shiwu.add(new KeyValueModel("事务ID", response.getId()));
                        break;
                    case "4":
                        //回收
                        list_shiwu.add(new KeyValueModel("门店名称", response.getStoreName()));
                        list_shiwu.add(new KeyValueModel("选择数量", response.getContract().getDeviceNum() + "台"));
                        list_shiwu.add(new KeyValueModel("减少原因", response.getContract().getReasonId()));
                        list_shiwu.add(new KeyValueModel("返回仓库", response.getContract().getWarehouseId()));
                        list_shiwu.add(new KeyValueModel("审核时间", response.getCreateTime()));
                        list_shiwu.add(new KeyValueModel("创建时间", response.getCreateTime()));

                        break;
                    case "5":
                        //换绑
                        list_shiwu.add(new KeyValueModel("转出门店", response.getContract().getOutStoreId()));
                        list_shiwu.add(new KeyValueModel("转入门店", response.getContract().getInStoreId()));
                        list_shiwu.add(new KeyValueModel("换绑台数", response.getContract().getDeviceNum() + "台"));
                        list_shiwu.add(new KeyValueModel("创建时间", response.getCreateTime()));
                        list_shiwu.add(new KeyValueModel("事务ID", response.getId()));
                        break;
                }

                mAdapter_shiwu = new CommonAdapter<KeyValueModel>
                        (AffairDetailActivity.this, R.layout.item_keyvalue, list_shiwu) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_shiwu.setAdapter(mAdapter_shiwu);

                /**
                 * 申领信息
                 */
                changeUI();

                /**
                 * 安装记录
                 */
                tv_allnum.setText(response.getInstallNum());
                tv_innum.setText(response.getInstalledNum());
                if (response.getList() != null) {
                    list_anzhuang = response.getList();
                    mAdapter_anzhuang = new CommonAdapter<AffairDetailModel.ListBean>
                            (AffairDetailActivity.this, R.layout.item_affairedetail_anzhuang, list_anzhuang) {
                        @Override
                        protected void convert(ViewHolder holder, AffairDetailModel.ListBean model, int position) {
                            holder.setText(R.id.textView1, model.getRoomFullName());
                            holder.setText(R.id.textView2, model.getDeviceHostName());
                            holder.setText(R.id.textView4, model.getInstallTime() + "");

                            if (response.getType().equals("2") || response.getType().equals("3")) {
                                holder.setText(R.id.textView3, "已安装");
                            }

                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    switch (response.getType()) {//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
                                        case "1":
                                            //主机
                                            //                                bundle.putString("deviceName", model.getDeviceNo());
                                            bundle.putString("deviceName", model.getDeviceId());
                                            CommonUtil.gotoActivityWithData(AffairDetailActivity.this, DeviceDetailActivity.class, bundle, false);
                                            break;
                                        case "2":
                                            //4G模块
                                            bundle.putString("deviceName", model.getDeviceHostName());
                                            bundle.putString("4GModule", model.getDeviceModuleId());
                                            CommonUtil.gotoActivityWithData(AffairDetailActivity.this, Change4GModuleActivity.class, bundle, false);
                                            break;
                                        case "3":
                                            //过滤网
                                            break;
                                        case "4":
                                            //回收

                                            break;
                                        case "5":
                                            //换绑

                                            break;
                                    }


                                }
                            });
                        }
                    };
                    rv_anzhuang.setAdapter(mAdapter_anzhuang);
                }

            }
        });
    }

    private boolean match() {
        switch (expressWay) {
            case "1":
                //自取
                if (imgfile == null) {
                    myToast("请选择上传照片");
                    return false;
                }
                break;
            case "2":
                //邮寄
                if (apply_Type.equals("4")) {//回收
                    if (TextUtils.isEmpty(transportCompany)) {
                        myToast("请选择物流公司");
                        return false;
                    }
                    transportId = et_kuaididanhao.getText().toString().trim();
                    if (TextUtils.isEmpty(transportId)) {
                        myToast("请输入快递单号");
                        return false;
                    }

                } else {
                    receiveName = et_shouhuoren.getText().toString().trim();
                    if (TextUtils.isEmpty(receiveName)) {
                        myToast("请输入收货人");
                        return false;
                    }
                    phone = et_lianxifangshi.getText().toString().trim();
                    if (TextUtils.isEmpty(phone)) {
                        myToast("请输入收货人联系方式");
                        return false;
                    }
                    if (TextUtils.isEmpty(areaId)) {
                        myToast("请选择收货地址");
                        return false;
                    }
                    address = et_xiangxidizhi.getText().toString().trim();
                    if (TextUtils.isEmpty(address)) {
                        myToast("请输入详细地址");
                        return false;
                    }
                }

                break;
        }

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("事务详情");
    }

    private void changeUI() {
        switch (type) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);

                ll_shopinfo.setVisibility(View.VISIBLE);
                ll_contract.setVisibility(View.GONE);
                ll_shenhe.setVisibility(View.GONE);

                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);

                ll_shopinfo.setVisibility(View.GONE);
                ll_contract.setVisibility(View.VISIBLE);
                ll_shenhe.setVisibility(View.GONE);

                changeShenLingUI();

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);

                ll_shopinfo.setVisibility(View.GONE);
                ll_contract.setVisibility(View.GONE);
                ll_shenhe.setVisibility(View.VISIBLE);

                break;

        }
    }

    private void changeShenLingUI() {
        ll_shouhuoren.setVisibility(View.GONE);
        ll_lianxifangshi.setVisibility(View.GONE);
        ll_shouhuodizhi.setVisibility(View.GONE);
        ll_xiangxidizhi.setVisibility(View.GONE);
        tv_shangchuanzhaopian.setVisibility(View.GONE);
        iv_shangchuanzhaopian.setVisibility(View.GONE);
        sl_daifahuo.setVisibility(View.GONE);
        rv_wuliu.setVisibility(View.GONE);

        tv_confirm.setVisibility(View.GONE);
        tv_shenlingfangshi.setClickable(false);
        iv_shangchuanzhaopian.setClickable(false);

        ll_wuliugongsi.setVisibility(View.GONE);
        ll_kuaididanhao.setVisibility(View.GONE);
        ll_tuihuicangku.setVisibility(View.GONE);
        ll_qianshouren.setVisibility(View.GONE);
        ll_qianshourendianhua.setVisibility(View.GONE);
        ll_qianshoudizhi.setVisibility(View.GONE);

        if (model.getLogistic() != null && model.getLogistic().getExpressWay() != null) {
            //TODO 已经设置了邮寄方式
            expressWay = model.getLogistic().getExpressWay();
            switch (model.getLogistic().getExpressWay()) {
                case "1":
                    //自取
                    tv_shenlingxinxi.setText("申领信息");
                    tv_shenlingfangshi.setText("自取");
                    tv_shangchuanzhaopian.setVisibility(View.VISIBLE);
                    iv_shangchuanzhaopian.setVisibility(View.VISIBLE);
                    Glide.with(AffairDetailActivity.this)
                            .load(model.getLogistic().getVoucher())
//                                .fitCenter()
                            .apply(RequestOptions.bitmapTransform(new
                                    RoundedCorners(CommonUtil.dip2px(AffairDetailActivity.this, 10))))
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.zanwutupian)//加载失败
                            .into(iv_shangchuanzhaopian);//加载图片
                    break;
                case "2":
                    //邮寄
                    if (apply_Type.equals("4")) {//回收
                        tv_confirm.setVisibility(View.VISIBLE);
                        tv_shenlingxinxi.setText("物流信息");
                        tv_shenlingfangshi.setText("邮寄");
                        ll_wuliugongsi.setVisibility(View.VISIBLE);
                        ll_kuaididanhao.setVisibility(View.VISIBLE);
                        ll_tuihuicangku.setVisibility(View.VISIBLE);
                        ll_qianshouren.setVisibility(View.VISIBLE);
                        ll_qianshourendianhua.setVisibility(View.VISIBLE);
                        ll_qianshoudizhi.setVisibility(View.VISIBLE);

                        tv_tuihuicangku.setText(model.getLogistic().getWarehouseName());
                        tv_qianshouren.setText(model.getLogistic().getReceiveName());
                        tv_qianshourendianhua.setText(model.getLogistic().getPhone());
                        tv_qianshoudizhi.setText(model.getLogistic().getProvinceName()
                                + model.getLogistic().getCityName()
                                + model.getLogistic().getAreaName()
                                + model.getLogistic().getAddress());

                    } else {
                        tv_shenlingxinxi.setText("收件信息");
                        tv_shenlingfangshi.setText("邮寄");
                        ll_shouhuoren.setVisibility(View.VISIBLE);
                        ll_lianxifangshi.setVisibility(View.VISIBLE);
                        ll_shouhuodizhi.setVisibility(View.VISIBLE);
                        ll_xiangxidizhi.setVisibility(View.VISIBLE);

                        et_shouhuoren.setText(model.getLogistic().getReceiveName());
                        et_lianxifangshi.setText(model.getLogistic().getPhone());
                        tv_shouhuodizhi.setText(model.getLogistic().getProvinceName() + model.getLogistic().getCityName() + model.getLogistic().getAreaName());
                        et_xiangxidizhi.setText(model.getLogistic().getAddress());
                        et_shouhuoren.setFocusable(false);
                        et_lianxifangshi.setFocusable(false);
                        tv_shouhuodizhi.setClickable(false);
                        et_xiangxidizhi.setFocusable(false);
                    }
                    //物流列表
                    if (model.getLogistic() != null && model.getLogistic().getExpressInfo() != null && model.getLogistic().getExpressInfo().size() > 0) {
                        //有物流
                        rv_wuliu.setVisibility(View.VISIBLE);

                        list_wuliu = model.getLogistic().getExpressInfo();
                        mAdapter_wuliu = new CommonAdapter<AffairDetailModel.LogisticBean.ExpressInfoBean>
                                (AffairDetailActivity.this, R.layout.item_wuliu, list_wuliu) {
                            @Override
                            protected void convert(ViewHolder holder, AffairDetailModel.LogisticBean.ExpressInfoBean bean, int position) {
                                holder.setText(R.id.tv1, bean.getTransportCompany());
                                holder.setText(R.id.tv2, bean.getTransportId());
                                if (bean.getSignTime() != null) {
                                    //已签收
                                    holder.setText(R.id.tv3, "已签收");
                                    holder.getView(R.id.tv_quedingqianshou).setVisibility(View.GONE);
                                } else {
                                    //已发货
                                    holder.setText(R.id.tv3, "待签收");
                                    if (apply_Type.equals("4")) {//回收
                                        holder.getView(R.id.tv_quedingqianshou).setVisibility(View.GONE);
                                    } else {
                                        holder.getView(R.id.tv_quedingqianshou).setVisibility(View.VISIBLE);
                                    }
                                }

                                holder.getView(R.id.tv_quedingqianshou).setOnClickListener(v -> {
                                    //确定签收
                                    showProgress(true, getString(R.string.app_loading1));
                                    params.clear();
                                    /*params.put("transactionId", model.getAddDeivceTransactionsVo().getApplyId());
                                    params.put("expressWay", expressWay + "");
                                    params.put("expressNo", "");
                                    params.put("expressCompany", "");
                                    params.put("scene", "confirm");//修改类别 chooseExpressWay-选择邮寄方式，confirm-确认收货*/
                                    requestQiansShou(params, bean.getLogisticId() + "/" + bean.getTransportId());
                                });
                            }
                        };
                        rv_wuliu.setAdapter(mAdapter_wuliu);
                    } else {
                        //待发货
                        sl_daifahuo.setVisibility(View.VISIBLE);
                        tv_dengdaifahuo.setVisibility(View.VISIBLE);
                    }
                    break;
            }

        } else {
            tv_confirm.setVisibility(View.VISIBLE);
            tv_shenlingfangshi.setClickable(true);
            iv_shangchuanzhaopian.setClickable(true);
            et_lianxifangshi.setText(localUserInfo.getPhonenumber());
            //TODO 未选择邮寄方式
            if (apply_Type.equals("4")) {//回收
                switch (expressWay) {
                    case "1":
                        //自取
                        tv_shenlingxinxi.setText("物流信息");
                        tv_shenlingfangshi.setText("自取");
                        tv_shangchuanzhaopian.setVisibility(View.VISIBLE);
                        iv_shangchuanzhaopian.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        //邮寄
                        tv_shenlingxinxi.setText("物流信息");
                        tv_shenlingfangshi.setText("邮寄");
                        ll_wuliugongsi.setVisibility(View.VISIBLE);
                        ll_kuaididanhao.setVisibility(View.VISIBLE);
                        ll_tuihuicangku.setVisibility(View.VISIBLE);
                        ll_qianshouren.setVisibility(View.VISIBLE);
                        ll_qianshourendianhua.setVisibility(View.VISIBLE);
                        ll_qianshoudizhi.setVisibility(View.VISIBLE);

                        tv_tuihuicangku.setText(model.getLogistic().getWarehouseName());
                        tv_qianshouren.setText(model.getLogistic().getReceiveName());
                        tv_qianshourendianhua.setText(model.getLogistic().getPhone());
                        tv_qianshoudizhi.setText(model.getLogistic().getProvinceName()
                                + model.getLogistic().getCityName()
                                + model.getLogistic().getAreaName()
                                + model.getLogistic().getAddress());
                        break;
                }
            } else {
                switch (expressWay) {
                    case "1":
                        //自取
                        tv_shenlingxinxi.setText("申领信息");
                        tv_shenlingfangshi.setText("自取");
                        tv_shangchuanzhaopian.setVisibility(View.VISIBLE);
                        iv_shangchuanzhaopian.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        //邮寄
                        tv_shenlingxinxi.setText("收件信息");
                        tv_shenlingfangshi.setText("邮寄");
                        ll_shouhuoren.setVisibility(View.VISIBLE);
                        ll_lianxifangshi.setVisibility(View.VISIBLE);
                        ll_shouhuodizhi.setVisibility(View.VISIBLE);
                        ll_xiangxidizhi.setVisibility(View.VISIBLE);
                        break;
                }
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String imgpath = null;
            Uri uri = null;
            switch (requestCode) {
                case CaptureActivity.REQUEST_CODE_SCAN:
                    //二维码扫码
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                        //{"deviceName": "641708882ef84e09995d70440e12ebf9"}
                        MyLogger.i("扫码返回", result);
                        /*try {
                            JSONObject mJsonObject = new JSONObject(result);
                            deviceName = mJsonObject.getString("deviceName");
                            tv_shebeiguzhang.setText(deviceName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            myToast("解析出错");
                        }*/
                    }
                    break;
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imgpath = uri.getPath();
                    MyLogger.i(">>>>>>>>>选取的文件路径：" + imgpath + ">>>>>后缀名：" + FileUtils.getFileExtension(imgpath));
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    imgpath = FileUtil.getPath(this, uri);
                    MyLogger.i(">>>>>>>>>选取的文件路径：" + imgpath + ">>>>>后缀名：" + FileUtils.getFileExtension(imgpath));
                    break;

            }
            if (imgpath != null) {
//                showProgress(true, getString(R.string.app_loading1));
//                imgfile = new File(uri.getPath());
                //压缩
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //如果是拍照，则旋转
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }
                imgfile = FileUtil.bytesToImageFile(AffairDetailActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));
                Glide.with(AffairDetailActivity.this)
                        .load(imgfile)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AffairDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(iv_shangchuanzhaopian);//加载图片
            }
        }
    }

    /**
     * 获取物流信息
     *
     * @param params
     */
    private void requestWuLiu(String url, Map<String, String> params) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<List<CommonModel.ListBean>>() {
            @Override
            public List<CommonModel.ListBean> onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(List<CommonModel.ListBean> response) {
                hideProgress();
                item_wuliugongsi = -1;
                list_wuliugongsi = response;
            }
        });

    }

    /**
     * 选择签约期限
     */
    private void dialogList_wuliugongsi(TextView textView) {
        dialog.contentView(R.layout.dialog_list_center)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.CENTER)
                .dimAmount(0.5f)
                .show();
        RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                (AffairDetailActivity.this, R.layout.item_help, list_wuliugongsi) {
            @Override
            protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getName());
                if (item_wuliugongsi == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_wuliugongsi = position;
                textView.setText(list_wuliugongsi.get(position).getName());
                transportCompany = list_wuliugongsi.get(position).getName();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_list.setAdapter(adapter);
    }

    /**
     * 提交邮寄方式
     *
     * @param params
     */
    private void requestUpData(Map<String, String> params, String url) {
        OkhttpUtil.okHttpPostJson(url, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                myToast("提交成功");
                hideProgress();
                requestServer();
            }
        });
    }

    /**
     * 提交签收
     *
     * @param params
     */
    private void requestQiansShou(Map<String, String> params, String id) {
        OkhttpUtil.okHttpGet(URLs.AffairDetail_QianShou + id, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                myToast("签收成功");
                hideProgress();
                requestServer();
            }
        });
    }

    /**
     * 选择申领方式
     */
    private void dialogList_fangshi() {
        dialog.contentView(R.layout.dialog_list_center)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.CENTER)
                .dimAmount(0.5f)
                .show();
        RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<String> adapter = new CommonAdapter<String>
                (AffairDetailActivity.this, R.layout.item_help, list_fangshi) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (itme_fangshi == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                itme_fangshi = position;

                expressWay = position + 1 + "";//邮寄方式 1-自取，2-邮寄
                changeShenLingUI();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_list.setAdapter(adapter);
    }

    /**
     * 选择城市
     */
    List<CommonModel.ListBean> list_chengshi = new ArrayList<>();
    int maxIdex_chengshi = 1;
    String string_chengshi = "";

    private void dialogList_chengshi(String parentId) {
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
        params.put("id", parentId);
        params.put("level", maxIdex_chengshi + "");
        OkhttpUtil.okHttpPostJson(URLs.Region, GsonUtils.toJson(params), headerMap, new CallBackUtil<CommonModel>() {
            @Override
            public CommonModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(CommonModel response) {
                hideProgress();
                list_chengshi = response.getList();
                dialog.contentView(R.layout.dialog_list_center)
//                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                CommonUtil.dip2px(AffairDetailActivity.this, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(false)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(AffairDetailActivity.this));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (AffairDetailActivity.this, R.layout.item_help, list_chengshi) {
                    @Override
                    protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model.getName());
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        string_chengshi = string_chengshi + list_chengshi.get(position).getName() + "-";
                        switch (maxIdex_chengshi) {
                            case 3:
                                //区
                                //最后一个，赋值
                                if (!string_chengshi.equals("")) {
                                    string_chengshi = string_chengshi.substring(0, string_chengshi.length() - 1);
                                }
                                tv_shouhuodizhi.setText(string_chengshi);
                                areaId = list_chengshi.get(position).getId();
                                //初始化
                                string_chengshi = "";
                                maxIdex_chengshi = 1;

                                dialog.dismiss();
                                break;
                            case 2:
                                //市
                                cityId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 3;
                                dialogList_chengshi(list_chengshi.get(position).getId());
                                break;
                            case 1:
                                //省
                                provinceId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 2;
                                dialogList_chengshi(list_chengshi.get(position).getId());
                                break;
                        }

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_list.setAdapter(adapter);

            }
        });
    }
}
