package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.model.StoreDetailModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/30.
 * 门店详情
 */
public class StoreDetailActivity extends BaseActivity {
    String id = "";
    int type = 1;
    TextView tv_tab1, tv_tab2, tv_tab3, tv_tab4;
    LinearLayout ll_tab1, ll_tab2, ll_tab3, ll_tab4;
    View view1, view2, view3, view4;

    StoreDetailModel model;
    ImageView imageView;
    TextView textView1, textView2, textView3, textView4, right_btn1;
    /**
     * 门店信息
     */
    LinearLayout ll_storeinfo;

    private RecyclerView rv_mendian;
    List<KeyValueModel> list_mendian = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_mendian;
    /**
     * 设备信息
     */
    LinearLayout ll_deviceinfo;

    private RecyclerView rv_shebei;
    List<KeyValueModel> list_shebei = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_shebei;
    /**
     * 营收信息
     */
    LinearLayout ll_yingshouinfo;
    private RecyclerView rv_yingshou;
    List<KeyValueModel> list_yingshou = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_yingshou;
    /**
     * 收费标准
     */
    LinearLayout ll_shoufeiinfo;
    private RecyclerView rv_shoufei;
    List<KeyValueModel> list_shoufei = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_shoufei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);

        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
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
                params.put("id", id);
                request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        ll_tab3 = findViewByID_My(R.id.ll_tab3);
        ll_tab4 = findViewByID_My(R.id.ll_tab4);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_tab3.setOnClickListener(this);
        ll_tab4.setOnClickListener(this);
        tv_tab1 = findViewByID_My(R.id.tv_tab1);
        tv_tab2 = findViewByID_My(R.id.tv_tab2);
        tv_tab3 = findViewByID_My(R.id.tv_tab3);
        tv_tab4 = findViewByID_My(R.id.tv_tab4);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        view4 = findViewByID_My(R.id.view4);

        right_btn1 = findViewByID_My(R.id.right_btn1);
        if (localUserInfo.getUserJob().equals("bd"))
            right_btn1.setVisibility(View.INVISIBLE);
        else right_btn1.setVisibility(View.VISIBLE);

        imageView = findViewByID_My(R.id.imageView);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);

        /**
         * 门店信息
         */
        ll_storeinfo = findViewByID_My(R.id.ll_storeinfo);
        rv_mendian = findViewByID_My(R.id.rv_mendian);
        rv_mendian.setLayoutManager(new LinearLayoutManager(this));

        /**
         * 设备信息
         */
        ll_deviceinfo = findViewByID_My(R.id.ll_deviceinfo);
        rv_shebei = findViewByID_My(R.id.rv_shebei);
        rv_shebei.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 营收信息
         */
        ll_yingshouinfo = findViewByID_My(R.id.ll_yingshouinfo);
        rv_yingshou = findViewByID_My(R.id.rv_yingshou);
        rv_yingshou.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 收费标准
         */
        ll_shoufeiinfo = findViewByID_My(R.id.ll_shoufeiinfo);
        rv_shoufei = findViewByID_My(R.id.rv_shoufei);
        rv_shoufei.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.right_btn1:
                CommonUtil.gotoActivity(StoreDetailActivity.this, AssignActivity.class);
                break;
            case R.id.linearLayout1:
                //设备
                CommonUtil.gotoActivity(StoreDetailActivity.this, MyDeviceListActivity.class);
                break;
            case R.id.linearLayout2:
                //房号
//                bundle.putString("storeId",model.getId());
                bundle.putSerializable("StoreDetailModel",model);
                CommonUtil.gotoActivityWithData(StoreDetailActivity.this, RoomNoManagementActivity.class,bundle);
                break;
            case R.id.linearLayout3:
                //员工
                CommonUtil.gotoActivity(StoreDetailActivity.this, StaffManagementActivity.class);
                break;
            case R.id.linearLayout4:
                //移位
                CommonUtil.gotoActivity(StoreDetailActivity.this, MoveDeviceActivity.class);
                break;
            case R.id.tv_change1:
                //修改账号
                CommonUtil.gotoActivity(StoreDetailActivity.this, ChangeStoreAccountActivity.class);
                break;
            case R.id.tv_change2:
                //修改信息
                CommonUtil.gotoActivity(StoreDetailActivity.this, ChangeStoreActivity.class);
                break;
            case R.id.ll_tab1:
                //待拜访
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //待划转
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //待调价
                type = 3;
                changeUI();
                break;
            case R.id.ll_tab4:
                //待调价
                type = 4;
                changeUI();
                break;
        }
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");

    }


    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.StoreDetail, params, headerMap, new CallBackUtil<StoreDetailModel>() {
            @Override
            public StoreDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(StoreDetailModel response) {
                hideProgress();
                model = response;
                Glide.with(StoreDetailActivity.this)
                        .load(response.getImage())
//                                .fitCenter()
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(CommonUtil.dip2px(StoreDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView);//加载图片
                textView1.setText(response.getMoney());//总收益
                textView2.setText(response.getDeviceNum());//设备数
                textView3.setText(response.getDeviceOnlineNum());//运行中
                textView4.setText(response.getDeviceOffLineNum());//离线中
                /**
                 * 门店信息
                 */
                list_mendian.clear();
                list_mendian.add(new KeyValueModel("门店ID", response.getId()));
                list_mendian.add(new KeyValueModel("门店名称", response.getBase().getName()));
                list_mendian.add(new KeyValueModel("门店联系人", response.getBase().getContact()));
                list_mendian.add(new KeyValueModel("门店联系电话", response.getBase().getContactMobile()));
                list_mendian.add(new KeyValueModel("门店区域", response.getBase().getCity()));
                list_mendian.add(new KeyValueModel("详细地址", response.getBase().getAddress()));
                list_mendian.add(new KeyValueModel("门店等级", response.getBase().getLevel()));
                list_mendian.add(new KeyValueModel("门店标识", response.getBase().getSlag()));
                list_mendian.add(new KeyValueModel("商户ID", response.getBase().getMerchantId()));
                list_mendian.add(new KeyValueModel("商户名称", response.getBase().getMerchantName()));
                list_mendian.add(new KeyValueModel("所在行业", response.getBase().getInstudy()));
                list_mendian.add(new KeyValueModel("营业时间", response.getBase().getBusinessHours()));
                list_mendian.add(new KeyValueModel("所属大区", response.getBase().getRegion()));
                list_mendian.add(new KeyValueModel("签约人", response.getBase().getSignName()));
                list_mendian.add(new KeyValueModel("签约类型", response.getBase().getSignType()));
                list_mendian.add(new KeyValueModel("运维类型", response.getBase().getBdType()));
                list_mendian.add(new KeyValueModel("当前运维人", response.getBase().getBdName()));
                list_mendian.add(new KeyValueModel("运维BDM", response.getBase().getBdmName()));
                list_mendian.add(new KeyValueModel("运维城市经理", response.getBase().getCmName()));
                list_mendian.add(new KeyValueModel("大区经理", response.getBase().getRmName()));
                list_mendian.add(new KeyValueModel("创建时间", response.getBase().getCreatedAt()));
                list_mendian.add(new KeyValueModel("上线时间", response.getBase().getOnlineAt()));
                list_mendian.add(new KeyValueModel("门店状态", response.getBase().getStatusTitle()));
                list_mendian.add(new KeyValueModel("是否划转", response.getBase().getIsTransfter()));
                list_mendian.add(new KeyValueModel("安装状态", response.getBase().getInstallStatus()));

                mAdapter_mendian = new CommonAdapter<KeyValueModel>
                        (StoreDetailActivity.this, R.layout.item_keyvalue, list_mendian) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_mendian.setAdapter(mAdapter_mendian);

                /**
                 * 设备信息
                 */
                list_shebei.clear();
                list_shebei.add(new KeyValueModel("绑定设备", response.getDeviceNum()));
                list_shebei.add(new KeyValueModel("上线设备", response.getDevice().getOnlineTotal()));
                list_shebei.add(new KeyValueModel("在线设备", response.getDeviceOnlineNum()));
                list_shebei.add(new KeyValueModel("离线设备", response.getDeviceOffLineNum()));
                list_shebei.add(new KeyValueModel("丢失设备", response.getDevice().getLastBind()));
                list_shebei.add(new KeyValueModel("最新邦定设备时间", response.getBase().getCreatedAt()));
                list_shebei.add(new KeyValueModel("待新增设备", response.getDevice().getWaitAdd()));
                list_shebei.add(new KeyValueModel("待回收设备", response.getDevice().getWaitRecycled()));
                list_shebei.add(new KeyValueModel("待转出设备", response.getDevice().getWaidTransfer()));
                mAdapter_shebei = new CommonAdapter<KeyValueModel>
                        (StoreDetailActivity.this, R.layout.item_keyvalue, list_shebei) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_shebei.setAdapter(mAdapter_shebei);

                /**
                 * 营收信息
                 */
                list_yingshou.clear();
                list_yingshou.add(new KeyValueModel("总营收", response.getMoney()));
                list_yingshou.add(new KeyValueModel("总订单数", response.getRevenueInformation().getOrderNum()));
                list_yingshou.add(new KeyValueModel("当日营收", response.getRevenueInformation().getTodayMoney()));
                list_yingshou.add(new KeyValueModel("当日订单数", response.getRevenueInformation().getTodayOrderNum()));
                list_yingshou.add(new KeyValueModel("上月动销天数", response.getRevenueInformation().getLastMonthSalesDays()));
                list_yingshou.add(new KeyValueModel("上月在线天数", response.getRevenueInformation().getLastMonthOnline()));
                list_yingshou.add(new KeyValueModel("本月动销天数", response.getRevenueInformation().getThisMonthSalesDays()));
                list_yingshou.add(new KeyValueModel("本月在线天数", response.getRevenueInformation().getThisMonthOnline()));
                list_yingshou.add(new KeyValueModel("30天拜访天数", response.getRevenueInformation().getVisitDays()));
                list_yingshou.add(new KeyValueModel("本月是否拜访", response.getRevenueInformation().getThisMonthVisit()));
                list_yingshou.add(new KeyValueModel("本月拜访时间", response.getRevenueInformation().getThisMonthVisitAt()));
                list_yingshou.add(new KeyValueModel("上月未标识", response.getRevenueInformation().getLastMonthMark()));
                list_yingshou.add(new KeyValueModel("最新头腰标识", response.getRevenueInformation().getLatestHeadWaistLogo()));
                mAdapter_yingshou = new CommonAdapter<KeyValueModel>
                        (StoreDetailActivity.this, R.layout.item_keyvalue, list_yingshou) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_yingshou.setAdapter(mAdapter_yingshou);

                /**
                 * 收费标准
                 */
                list_shoufei.clear();
                list_shoufei.add(new KeyValueModel("分成比例", response.getFees().getScale()));
                list_shoufei.add(new KeyValueModel("计费单元", response.getFees().getUnitPrice()));
                list_shoufei.add(new KeyValueModel("单日封顶价格", response.getFees().getSystemCapping()));
                list_shoufei.add(new KeyValueModel("计费单价", response.getFees().getSystemUnitPrice()));
                list_shoufei.add(new KeyValueModel("自定义单价", response.getFees().getStoreUnitPrice()));
                list_shoufei.add(new KeyValueModel("自定义封顶", response.getFees().getStoreCapping()));
                list_shoufei.add(new KeyValueModel("免费时长", response.getFees().getFreeTime()));
                mAdapter_shoufei = new CommonAdapter<KeyValueModel>
                        (StoreDetailActivity.this, R.layout.item_keyvalue, list_shoufei) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_shoufei.setAdapter(mAdapter_shoufei);
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.put("id", id);
        request(params);
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    private void changeUI() {
        switch (type) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                ll_storeinfo.setVisibility(View.VISIBLE);
                ll_deviceinfo.setVisibility(View.GONE);
                ll_yingshouinfo.setVisibility(View.GONE);
                ll_shoufeiinfo.setVisibility(View.GONE);

                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));

                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);

                ll_storeinfo.setVisibility(View.GONE);
                ll_deviceinfo.setVisibility(View.VISIBLE);
                ll_yingshouinfo.setVisibility(View.GONE);
                ll_shoufeiinfo.setVisibility(View.GONE);

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));

                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.INVISIBLE);

                ll_storeinfo.setVisibility(View.GONE);
                ll_deviceinfo.setVisibility(View.GONE);
                ll_yingshouinfo.setVisibility(View.VISIBLE);
                ll_shoufeiinfo.setVisibility(View.GONE);
                break;
            case 4:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black1));

                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.VISIBLE);

                ll_storeinfo.setVisibility(View.GONE);
                ll_deviceinfo.setVisibility(View.GONE);
                ll_yingshouinfo.setVisibility(View.GONE);
                ll_shoufeiinfo.setVisibility(View.VISIBLE);
                break;

        }
    }

}
