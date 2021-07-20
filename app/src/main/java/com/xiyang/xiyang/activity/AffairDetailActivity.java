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
    String id = "";
    int type = 1;
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
    LinearLayout ll_contract, ll_shenlingfangshi, ll_shouhuoren, ll_lianxifangshi, ll_shouhuodizhi, ll_xiangxidizhi;
    EditText et_shouhuoren, et_lianxifangshi, et_xiangxidizhi;
    ImageView iv_shangchuanzhaopian;
    TextView tv_shenlingxinxi, tv_shenlingfangshi, tv_shouhuodizhi, tv_dengdaifahuo, tv_quedingqianshou,
            tv_shangchuanzhaopian, tv_confirm;
    ShadowLayout sl_wuliu;
    private RecyclerView rv_wuliu;
    List<KeyValueModel> list_wuliu = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_wuliu;
    int expressWay = 1;//邮寄方式 1-自取，2-邮寄
    List<String> list_fangshi = new ArrayList<>();
    int itme_fangshi = 0;
    File imgfile = null;
    //省市
    CityConfig cityConfig = null;
    CityPickerView mPicker = new CityPickerView();
    String receiveName = "", phone = "",provinceId = "",cityId="", areaId = "", address = "", scene = "";
    /**
     * 安装记录
     */
    LinearLayout ll_shenhe;
    RecyclerView rv_anzhuang;
//    List<AffairDetailModel.InstallBean> list_anzhuang = new ArrayList<>();
//    CommonAdapter<AffairDetailModel.InstallBean> mAdapter_anzhuang;
    TextView iv_scan, tv_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affairdetail);
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
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        sl_wuliu = findViewByID_My(R.id.sl_wuliu);
        rv_wuliu = findViewByID_My(R.id.rv_wuliu);
        rv_wuliu.setLayoutManager(new LinearLayoutManager(this));
        tv_dengdaifahuo = findViewByID_My(R.id.tv_dengdaifahuo);
        tv_quedingqianshou = findViewByID_My(R.id.tv_quedingqianshou);
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
        ll_shenhe = findViewByID_My(R.id.ll_shenhe);
        rv_anzhuang = findViewByID_My(R.id.rv_anzhuang);
        rv_anzhuang.setLayoutManager(new LinearLayoutManager(this));
        iv_scan = findViewByID_My(R.id.iv_scan);
        tv_scan = findViewByID_My(R.id.tv_scan);

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
                bundle.putString("transactionId", model.getAddDeivceTransactionsVo().getApplyId());
                bundle.putString("storeId", model.getAddDeivceTransactionsVo().getStoreId());
                bundle.putString("storeName", model.getAddDeivceTransactionsVo().getName());
                CommonUtil.gotoActivityWithData(AffairDetailActivity.this, InstallDeviceActivity.class, bundle);
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

            case R.id.tv_confirm:
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    switch (expressWay) {
                        case 1:
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
                                    params.put("relationId", model.getAddDeivceTransactionsVo().getApplyId());
                                    params.put("expressWay", expressWay + "");//1-自取，2-邮寄
                                    params.put("voucher", response);
                                        /*params.put("expressNo", "");
                                        params.put("expressCompany", "");
                                        params.put("receiveName", "");
                                        params.put("phone", "");
                                        params.put("areaId", "");
                                        params.put("address", "");*/
//                                        params.put("scene", "chooseExpressWay");//修改类别 chooseExpressWay-选择邮寄方式，confirm-确认收货
                                    requestUpData(params);
                                }
                            });

                            break;
                        case 2:
                            //邮寄
                            params.clear();
                            params.put("relationId", model.getAddDeivceTransactionsVo().getApplyId());
                            params.put("expressWay", expressWay + "");//1-自取，2-邮寄
//                            params.put("expressNo", "");
//                            params.put("expressCompany", "");
                            params.put("receiveName", receiveName);//收货人
                            params.put("phone", phone);//收货人联系方式
                            params.put("provinceId", provinceId);//省
                            params.put("cityId", cityId);//市
                            params.put("areaId", areaId);//区
                            params.put("address", address);
//                            params.put("scene", "chooseExpressWay");//修改类别 chooseExpressWay-选择邮寄方式，confirm-确认收货
                            requestUpData(params);
                            break;
                    }
                }
                break;
            case R.id.tv_quedingqianshou:
                //确定签收
                showProgress(true, getString(R.string.app_loading1));
                params.clear();
                /*params.put("transactionId", model.getAddDeivceTransactionsVo().getApplyId());
                params.put("expressWay", expressWay + "");
                params.put("expressNo", "");
                params.put("expressCompany", "");
                params.put("scene", "confirm");//修改类别 chooseExpressWay-选择邮寄方式，confirm-确认收货*/
                requestQiansShou(params,model.getAddDeivceTransactionsVo().getApplyId());
                break;
        }
    }

    @Override
    protected void initData() {
        list_fangshi.add("自取");
        list_fangshi.add("邮寄");

        id = getIntent().getStringExtra("id");
        requestServer();
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
        OkhttpUtil.okHttpGet(URLs.AffairDetail+id, params, headerMap, new CallBackUtil<AffairDetailModel>() {
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
                tv_name.setText(response.getAddDeivceTransactionsVo().getName());
                tv_shop.setText(response.getAddDeivceTransactionsVo().getDeviceType());
                tv_num.setText(response.getAddDeivceTransactionsVo().getStatus());
                tv_addr.setText(response.getAddDeivceTransactionsVo().getNum() + "台");
                Glide.with(AffairDetailActivity.this)
                        .load(model.getAddDeivceTransactionsVo().getImage())
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
                list_shiwu.add(new KeyValueModel("类型设备", response.getAddDeivceTransactionsVo().getTranscationType()));
                list_shiwu.add(new KeyValueModel("安装门店", response.getAddDeivceTransactionsVo().getName()));
                list_shiwu.add(new KeyValueModel("安装台数", response.getAddDeivceTransactionsVo().getNum() + "台"));
                list_shiwu.add(new KeyValueModel("创建时间", response.getAddDeivceTransactionsVo().getCreateTime()));
                list_shiwu.add(new KeyValueModel("事务ID", response.getAddDeivceTransactionsVo().getApplyId()));

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
                /*list_anzhuang = response.getInstall();
                mAdapter_anzhuang = new CommonAdapter<AffairDetailModel.InstallBean>
                        (AffairDetailActivity.this, R.layout.item_affairedetail_anzhuang, list_anzhuang) {
                    @Override
                    protected void convert(ViewHolder holder, AffairDetailModel.InstallBean model, int position) {
                        holder.setText(R.id.textView1, model.getAddr());
                        holder.setText(R.id.textView2, model.getSn());
//                        holder.setText(R.id.textView3, model.get);
                        holder.setText(R.id.textView4, model.getInstallAt() + "");

                        holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("deviceName", model.getSn());
                                CommonUtil.gotoActivityWithData(AffairDetailActivity.this, DeviceDetailActivity.class, bundle, false);
                            }
                        });

                    }
                };
                rv_anzhuang.setAdapter(mAdapter_anzhuang);*/

            }
        });
    }

    private boolean match() {
        switch (expressWay) {
            case 1:
                //自取
                if (imgfile == null) {
                    myToast("请选择上传照片");
                    return false;
                }
                break;
            case 2:
                //邮寄
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
        sl_wuliu.setVisibility(View.GONE);

        tv_confirm.setVisibility(View.GONE);
        tv_shenlingfangshi.setClickable(false);
        iv_shangchuanzhaopian.setClickable(false);
        switch (model.getAddDeivceTransactionsVo().getGetType()) {
            case "0":
                //未选择邮寄方式
                tv_confirm.setVisibility(View.VISIBLE);
                tv_shenlingfangshi.setClickable(true);
                iv_shangchuanzhaopian.setClickable(true);
                break;
            case "1":
                //自取
                expressWay = 1;
                Glide.with(AffairDetailActivity.this)
                        .load(model.getAddDeivceTransactionsVo().getImage())
//                                .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AffairDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_shangchuanzhaopian);//加载图片
                break;
            case "2":
                //邮寄
                expressWay = 2;
//                et_shouhuoren.setText(model.getApply().getName());
//                et_lianxifangshi.setText(model.getApply().getMobile());
//                tv_shouhuodizhi.setText(model.getApply().getAddres());
//                et_xiangxidizhi.setText(model.getApply().getAddressDetail());
                et_shouhuoren.setFocusable(false);
                et_lianxifangshi.setFocusable(false);
                tv_shouhuodizhi.setClickable(false);
                et_xiangxidizhi.setFocusable(false);
                break;
        }
        switch (expressWay) {
            case 1:
                //自取
                tv_shenlingxinxi.setText("申领信息");
                tv_shenlingfangshi.setText("自取");
                tv_shangchuanzhaopian.setVisibility(View.VISIBLE);
                iv_shangchuanzhaopian.setVisibility(View.VISIBLE);

                break;
            case 2:
                //邮寄
                tv_shenlingxinxi.setText("收件信息");
                tv_shenlingfangshi.setText("邮寄");
                ll_shouhuoren.setVisibility(View.VISIBLE);
                ll_lianxifangshi.setVisibility(View.VISIBLE);
                ll_shouhuodizhi.setVisibility(View.VISIBLE);
                ll_xiangxidizhi.setVisibility(View.VISIBLE);

                /*switch (model.getApply().getStatus()) {
                    case "1":
                        //待发货
                        sl_wuliu.setVisibility(View.VISIBLE);
                        tv_dengdaifahuo.setVisibility(View.VISIBLE);
                        rv_wuliu.setVisibility(View.GONE);
                        tv_quedingqianshou.setVisibility(View.GONE);
                        break;
                    case "2":
                        //已发货
                        sl_wuliu.setVisibility(View.VISIBLE);
                        tv_dengdaifahuo.setVisibility(View.GONE);
                        rv_wuliu.setVisibility(View.VISIBLE);
                        tv_quedingqianshou.setVisibility(View.VISIBLE);
                        break;
                    case "3":
                        //已签收
                        sl_wuliu.setVisibility(View.VISIBLE);
                        tv_dengdaifahuo.setVisibility(View.GONE);
                        rv_wuliu.setVisibility(View.VISIBLE);
                        tv_quedingqianshou.setVisibility(View.GONE);
                        break;
                }
                if (!model.getApply().getExpressNo().equals("")) {
                    //有快递单号，显示快递信息
                    list_wuliu.clear();
                    list_wuliu.add(new KeyValueModel("快递公司", model.getApply().getExpressCompany()));
                    list_wuliu.add(new KeyValueModel("物流单号", model.getApply().getExpressNo()));
                    list_wuliu.add(new KeyValueModel("签收状态", model.getApply().getStatusTitle()));

                    mAdapter_wuliu = new CommonAdapter<KeyValueModel>
                            (AffairDetailActivity.this, R.layout.item_keyvalue, list_wuliu) {
                        @Override
                        protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                            holder.setText(R.id.tv_kay, model.getKey());
                            holder.setText(R.id.tv_value, "" + model.getValue());
                        }
                    };
                    rv_wuliu.setAdapter(mAdapter_wuliu);
                }*/
                break;
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
     * 提交邮寄方式
     * @param params
     */
    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.AffairDetail_ShenLing, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
     * @param params
     */
    private void requestQiansShou(Map<String, String> params,String id) {
        OkhttpUtil.okHttpGet(URLs.AffairDetail_QianShou+id, params, headerMap, new CallBackUtil<String>() {
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

                expressWay = position + 1;//邮寄方式 1-自取，2-邮寄
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
