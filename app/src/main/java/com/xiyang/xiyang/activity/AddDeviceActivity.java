package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.Device2StoreModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.Constant;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 申领设备
 */
public class AddDeviceActivity extends BaseActivity {
    int type = 1;//1、主机、2、4g模块 3、过滤网
    int page = 1;
    String storeId = "";
    TextView textView1;
    private RecyclerView recyclerView;
    List<Device2StoreModel.DeviceListBean> list = new ArrayList<>();
    CommonAdapter<Device2StoreModel.DeviceListBean> mAdapter;

    TextView tv_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevice);
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                params.clear();
                params.put("storeId", storeId);
                requestDevice2Store(params, storeId);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                /*String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentListMore(string);*/
            }
        });
        tv_num = findViewByID_My(R.id.tv_num);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //选择门店
                Intent intent1 = new Intent(AddDeviceActivity.this, MyStoreListActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("requestCode", Constant.SELECT_STORE);
                bundle1.putString("status", "");//状态 0 => '待指派',1 => '待签约',2 => '待审核',3 => '正常',4 => '待续约'
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, Constant.SELECT_STORE, bundle1);
                break;
            case R.id.tv_add:
                //申领
                if (list.size() > 0) {
                    this.showProgress(true, getString(R.string.app_loading1));
                    String deviceIds = "";
                    for (int i = 0; i < list.size(); i++) {
                        deviceIds = deviceIds + list.get(i).getDeviceId() + ",";
                    }
                    if (!deviceIds.equals("")) {
                        deviceIds = deviceIds.substring(0, deviceIds.length() - 1);
                    }
                    params.clear();
                    params.put("storesId", storeId);
                    params.put("deviceIds", deviceIds);
                    if (type == 2)
                        requestUpData(params, URLs.AddDevice_4G);
                    else requestUpData(params, URLs.AddDevice_GuoLv);

                } else myToast("没有选择申领的设备");
                break;
        }
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 1);
//        requestServer();//获取数据
        showEmptyPage();
    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        params.clear();
        params.put("storeId", storeId);
        requestDevice2Store(params, storeId);
    }
    @Override
    protected void updateView() {
        switch (type) {
            case 1:
                titleView.setTitle("申领净化器主机");
                break;
            case 2:
                titleView.setTitle("申领4G模块");
                break;
            case 3:
                titleView.setTitle("申领过滤网");
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.SELECT_STORE:
                    //选择门店
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        storeId = bundle.getString("storeId");
                        textView1.setText(bundle.getString("storeName"));

                        //获取门店下的设备
                        showProgress(true, getString(R.string.app_loading2));
                        params.clear();
                        params.put("storeId", storeId);
                        requestDevice2Store(params, storeId);
                    }
                    break;

            }
        }
    }

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
                finish();
            }
        });
    }

    /**
     * 获取门店下的设备
     *
     * @param params
     */
    private void requestDevice2Store(HashMap<String, String> params, String storeId) {
        OkhttpUtil.okHttpGet(URLs.Device2Store, params, headerMap, new CallBackUtil<Device2StoreModel>() {
            @Override
            public Device2StoreModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                showErrorPage();
            }

            @Override
            public void onResponse(Device2StoreModel response) {
                hideProgress();
                showEmptyPage();
                if (response.getDeviceList()!=null){
                    list = response.getDeviceList();
                    if (list.size() > 0) {
                        showContentPage();
                        mAdapter = new CommonAdapter<Device2StoreModel.DeviceListBean>
                                (AddDeviceActivity.this, R.layout.item_adddevice, list) {
                            @Override
                            protected void convert(ViewHolder holder, Device2StoreModel.DeviceListBean model, int position) {
                                holder.setText(R.id.textView1, model.getStoreFullName());
                                holder.setText(R.id.textView2, model.getDeviceHostName());
                                //删除
                                holder.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        list.remove(model);
                                        mAdapter.notifyDataSetChanged();
                                        tv_num.setText(list.size() + "");
                                    }
                                });

                            }
                        };
                        recyclerView.setAdapter(mAdapter);
                        tv_num.setText(list.size() + "");
                    }
                }


            }
        });
    }
}
