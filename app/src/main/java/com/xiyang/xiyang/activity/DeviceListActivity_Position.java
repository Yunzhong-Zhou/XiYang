package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.DeviceListModel_Position;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 我的设备-定位异常
 */
public class DeviceListActivity_Position extends BaseActivity {
    private RecyclerView recyclerView;
    List<DeviceListModel_Position.LocationDeviceVoListBean> list = new ArrayList<>();
    CommonAdapter<DeviceListModel_Position.LocationDeviceVoListBean> mAdapter;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshoplist_position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                params.put("page", page + "");
                params.put("size", "10");
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("page", page + "");
                params.put("size", "10");
                requestListMore(params);
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeviceList_Position, params, headerMap, new CallBackUtil<DeviceListModel_Position>() {
            @Override
            public DeviceListModel_Position onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(DeviceListModel_Position response) {
                showEmptyPage();
                hideProgress();
                if (response.getLocationDeviceVoList() != null) {
                    list = response.getLocationDeviceVoList();
                    if (list.size() > 0) {
                        showContentPage();
                        mAdapter = new CommonAdapter<DeviceListModel_Position.LocationDeviceVoListBean>
                                (DeviceListActivity_Position.this, R.layout.item_fragment2_2, list) {
                            @Override
                            protected void convert(ViewHolder holder, DeviceListModel_Position.LocationDeviceVoListBean model, int position) {
                                holder.setText(R.id.tv_name, model.getStoreName());//标题
                                holder.setText(R.id.tv_shop, "SN:"+model.getDeviceHostName());
                                holder.setText(R.id.tv_num, model.getTotalMoney());//money
                                holder.setText(R.id.tv_addr, model.getStoreAddress());

                                ImageView imageView1 = holder.getView(R.id.imageView1);
                                Glide.with(DeviceListActivity_Position.this)
                                        .load(model.getStoreImg())
//                                .fitCenter()
                                        .apply(RequestOptions.bitmapTransform(new
                                                RoundedCorners(CommonUtil.dip2px(DeviceListActivity_Position.this, 10))))
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.zanwutupian)//加载失败
                                        .into(imageView1);//加载图片
                                ImageView imageView2 = holder.getView(R.id.imageView2);
                                if (model.getDeviceStatus() != null && model.getDeviceStatus().equals("1")) {
                                    imageView2.setImageResource(R.mipmap.bg_zaixian);
                                } else {
                                    //离线
                                    imageView2.setImageResource(R.mipmap.bg_lixian);
                                }
                                holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("model",model);
                                        CommonUtil.gotoActivityWithData(DeviceListActivity_Position.this, DeviceAddressActivity.class, bundle, false);

                                    }
                                });
                            }
                        };
                        recyclerView.setAdapter(mAdapter);
                    }
                }

            }
        });

    }

    private void requestListMore(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.DeviceList_Position, GsonUtils.toJson(params), headerMap, new CallBackUtil<DeviceListModel_Position>() {
            @Override
            public DeviceListModel_Position onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                showErrorPage();
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(DeviceListModel_Position response) {
//                showContentPage();
                hideProgress();

                List<DeviceListModel_Position.LocationDeviceVoListBean> list1 = new ArrayList<>();
                list1 = response.getLocationDeviceVoList();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    @Override
    protected void updateView() {
        titleView.setTitle("定位异常设备");
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        params.put("page", page + "");
        params.put("size", "10");
        requestList(params);
    }
}
