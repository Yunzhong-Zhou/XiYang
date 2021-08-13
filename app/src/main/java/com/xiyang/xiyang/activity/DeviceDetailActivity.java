package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.DeviceDetailModel;
import com.xiyang.xiyang.model.KeyValueModel;
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
 * 设备详情
 */
public class DeviceDetailActivity extends BaseActivity {
    String deviceName = "";
    DeviceDetailModel model;
    /**
     * 基本信息
     */
    private RecyclerView rv_jiben;
    List<KeyValueModel> list_jiben = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_jiben;
    /**
     * 运维数据
     */
    private RecyclerView rv_yunwei;
    List<KeyValueModel> list_yunwei = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_yunwei;
    /**
     * 营收信息
     */
    private RecyclerView rv_yingshou;
    List<KeyValueModel> list_yingshou = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_yingshou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicedetail);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                params.clear();
//                params.put("deviceName", deviceName);
                requestDeviceDetail(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        /**
         * 基本信息
         */
        rv_jiben = findViewByID_My(R.id.rv_jiben);
        rv_jiben.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 运维数据
         */
        rv_yunwei = findViewByID_My(R.id.rv_yunwei);
        rv_yunwei.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 营收信息
         */
        rv_yingshou = findViewByID_My(R.id.rv_yingshou);
        rv_yingshou.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_tiaoshi:
                //调试设备
                bundle.putString("deviceName", model.getHostName());
                CommonUtil.gotoActivityWithData(DeviceDetailActivity.this, DebugDeviceActivity.class, bundle, false);
                break;
            case R.id.tv_shangbao:
                //上报故障
                bundle.putInt("type", 0);
                bundle.putInt("guzhang",-1);
                bundle.putString("deviceName",model.getHostName());
                bundle.putString("storeId",model.getStoreId());
                bundle.putString("storeName",model.getStoreName());

                CommonUtil.gotoActivityWithData(DeviceDetailActivity.this, AddWorkListActivity.class, bundle);
                break;
            case R.id.tv_weixiu:
                //维修记录
                bundle.putString("fetch", "1");//1待接工单2我的工单
                CommonUtil.gotoActivityWithData(DeviceDetailActivity.this, MyWorkListActivity.class, bundle);
                break;
        }
    }

    @Override
    protected void initData() {
        deviceName = getIntent().getStringExtra("deviceName");
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
//        params.put("deviceName", deviceName);
        requestDeviceDetail(params);

    }

    private void requestDeviceDetail(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.DeviceDetail + deviceName, params, headerMap, new CallBackUtil<DeviceDetailModel>() {
            @Override
            public DeviceDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                finish();
            }

            @Override
            public void onResponse(DeviceDetailModel response) {
                hideProgress();
                model = response;
                /**
                 * 基本信息
                 */
                list_jiben.clear();
                list_jiben.add(new KeyValueModel("主机名称", response.getHostName()));
                list_jiben.add(new KeyValueModel("主机序列号", response.getId()));
                list_jiben.add(new KeyValueModel("4G模组ID", response.getId()));
//                list_jiben.add(new KeyValueModel("位置", response.getStoreName()+"·"+response.));
                list_jiben.add(new KeyValueModel("安装时间", response.getInstallTime()));
                list_jiben.add(new KeyValueModel("网络类型", response.getNetwork()));
                list_jiben.add(new KeyValueModel("区域", response.getStoreRegion()));
                list_jiben.add(new KeyValueModel("详细地址", response.getStoreAddress()));
                list_jiben.add(new KeyValueModel("行业", response.getStoreIndustry()));

                mAdapter_jiben = new CommonAdapter<KeyValueModel>
                        (DeviceDetailActivity.this, R.layout.item_keyvalue, list_jiben) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_jiben.setAdapter(mAdapter_jiben);
                /**
                 * 运维数据
                 */
                list_yunwei.clear();
                list_yunwei.add(new KeyValueModel("运维类型", response.getClassic()));
                list_yunwei.add(new KeyValueModel("运维人", response.getMaintainer()));
                list_yunwei.add(new KeyValueModel("最后心跳时间", response.getLastOfflineTime()));
                list_yunwei.add(new KeyValueModel("连续在线天数", response.getContinueOnlineDays()));
                list_yunwei.add(new KeyValueModel("连续不在线天数", response.getContinueOfflineDays()));
                list_yunwei.add(new KeyValueModel("连续无动销天数", response.getContinueNotMovingSalesDays()));

                mAdapter_yunwei = new CommonAdapter<KeyValueModel>
                        (DeviceDetailActivity.this, R.layout.item_keyvalue, list_yunwei) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_yunwei.setAdapter(mAdapter_yunwei);
                /**
                 * 营收信息
                 */
                list_yingshou.clear();
                list_yingshou.add(new KeyValueModel("订单总数", response.getTotalOrders()));
                list_yingshou.add(new KeyValueModel("当日订单数", response.getCurrentDyOrders()));
                list_yingshou.add(new KeyValueModel("总营收", "￥ "+response.getTotalRevenue()));
                list_yingshou.add(new KeyValueModel("当日营收", "￥ "+response.getCurrentDyRevenue()));
                list_yingshou.add(new KeyValueModel("门店标识", response.getStoreLevel()));

                mAdapter_yingshou = new CommonAdapter<KeyValueModel>
                        (DeviceDetailActivity.this, R.layout.item_keyvalue, list_yingshou) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_yingshou.setAdapter(mAdapter_yingshou);
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("设备详情");
    }
}
