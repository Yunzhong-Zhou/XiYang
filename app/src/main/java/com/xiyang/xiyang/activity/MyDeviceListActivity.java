package com.xiyang.xiyang.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.MyDeviceListModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.popupwindow.PopupWindow_List4;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.SearchDialog;
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
 * 我的设备
 */
public class MyDeviceListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<MyDeviceListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<MyDeviceListModel.RecordsBean> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2, linearLayout3;
    private TextView textView1, textView2, textView3;
    private View view1, view2, view3;
    private LinearLayout pop_view;
    int page = 1;

    List<String> list_status = new ArrayList<>();
    String sort = "", status = "", postionId = "", storeId = "", instudy = "", keyword = "";
    int i1 = 0;
    int i2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshoplist);
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

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                params.put("page", page + "");
                params.put("size", "10");
                params.put("keyword", keyword);
                params.put("status", status);
                params.put("regionId", postionId);
                params.put("keyword", sort);
                params.put("storeId", storeId);
                params.put("industryId", instudy);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("page", page + "");
                params.put("size", "10");
                params.put("keyword", keyword);
                params.put("status", status);
                params.put("regionId", postionId);
                params.put("keyword", sort);
                params.put("storeId", storeId);
                params.put("industryId", instudy);
                requestListMore(params);
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        pop_view = findViewByID_My(R.id.pop_view);
    }

    @Override
    protected void initData() {
        //签约状态 1正常2待签约3待审核4签约成功5签约失败
        list_status.clear();
        list_status.add("全部");
        list_status.add("正常");
        list_status.add("待签约");
        list_status.add("待审核");
        list_status.add("签约成功");
        list_status.add("签约失败");
    }

    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.MyDevice, GsonUtils.toJson(params), headerMap, new CallBackUtil<MyDeviceListModel>() {
            @Override
            public MyDeviceListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyDeviceListModel response) {
                showContentPage();
                hideProgress();
                list = response.getRecords();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<MyDeviceListModel.RecordsBean>
                            (MyDeviceListActivity.this, R.layout.item_fragment2_2, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyDeviceListModel.RecordsBean model, int position) {
                            holder.setText(R.id.tv_name, model.getStoreName());//标题
                            holder.setText(R.id.tv_shop, model.getHostName());
                            holder.setText(R.id.tv_num, model.getTotalRevenue());//money
                            holder.setText(R.id.tv_addr, model.getStoreAddress());

                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(MyDeviceListActivity.this)
                                    .load(model.getStoreImage())
//                                .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(MyDeviceListActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            ImageView imageView2 = holder.getView(R.id.imageView2);
                            if (model.getAliyunStatus() != null && model.getAliyunStatus().equals("1")) {
                                imageView2.setImageResource(R.mipmap.bg_zaixian);
                            } else {
                                //离线
                                imageView2.setImageResource(R.mipmap.bg_lixian);
                            }
                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("deviceName", model.getId());
                                    CommonUtil.gotoActivityWithData(MyDeviceListActivity.this, DeviceDetailActivity.class, bundle, false);

                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });

    }

    private void requestListMore(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.MyDevice, GsonUtils.toJson(params), headerMap, new CallBackUtil<MyDeviceListModel>() {
            @Override
            public MyDeviceListModel onParseResponse(Call call, Response response) {
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
            public void onResponse(MyDeviceListModel response) {
//                showContentPage();
                hideProgress();

                List<MyDeviceListModel.RecordsBean> list1 = new ArrayList<>();
                list1 = response.getRecords();
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
    public void onClick(View v) {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (v.getId()) {
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView3.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                textView3.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new PopupWindow_List4(MyDeviceListActivity.this, 0, list_status, i1, pop_view) {
                    @Override
                    public void onFailure(String keys, int item) {
                        status = item + "";
                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView3.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable1, null);
                textView3.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new PopupWindow_List4(MyDeviceListActivity.this, 1, list_status, i2, pop_view) {
                    @Override
                    public void onFailure(String keys, int item) {
                        status = item + "";
                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout3:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView3.setTextColor(getResources().getColor(R.color.green));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                textView3.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new PopupWindow_List4(MyDeviceListActivity.this, 2, list_status, i1, pop_view) {
                    @Override
                    public void onFailure(String keys, int item) {
                        status = item + "";
                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout4:
                new SearchDialog(MyDeviceListActivity.this, dialog) {
                    @Override
                    public void onFailure(String keys) {
                        keyword = keys;
                        requestServer();
                    }
                };
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("我的设备");
        /*titleView.showRightTextview("添加门店",true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(MyDeviceListActivity.this, AddStoreActivity.class);
            }
        });*/
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        params.put("page", page + "");
        params.put("size", "10");
        params.put("keyword", keyword);
        params.put("status", status);
        params.put("regionId", postionId);
        params.put("keyword", sort);
        params.put("storeId", storeId);
        params.put("industryId", instudy);
        requestList(params);
    }
}
