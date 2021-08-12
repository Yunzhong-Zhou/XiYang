package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.xiyang.xiyang.utils.Constant;
import com.xiyang.xiyang.utils.SearchDialog;
import com.xiyang.xiyang.utils.SelectCityDialog;
import com.xiyang.xiyang.utils.SelectIndustryDialog;
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
    int requestCode = 0, selectItem = -1;
    private RecyclerView recyclerView;
    List<MyDeviceListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<MyDeviceListModel.RecordsBean> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    private TextView textView1, textView2, textView3, textView4;
    private View view1, view2, view3, view4;
    private LinearLayout pop_view;
    int page = 1;

    List<String> list_status = new ArrayList<>();
    String status = "", keyword = "", instudyId = "", provinceId = "", cityId = "", areaId = "",storeId="";
    int i1 = 0;

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
                params.put("pageSize", "10");
                params.put("keyword", keyword);
                params.put("status", status);
                params.put("instudyId", instudyId);
                params.put("provinceId", provinceId);
                params.put("cityId", cityId);
                params.put("areaId", areaId);
                params.put("storeId",storeId);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("page", page + "");
                params.put("pageSize", "10");
                params.put("keyword", keyword);
                params.put("status", status);
                params.put("instudyId", instudyId);
                params.put("provinceId", provinceId);
                params.put("cityId", cityId);
                params.put("areaId", areaId);
                params.put("storeId",storeId);
                requestListMore(params);
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout4 = findViewByID_My(R.id.linearLayout4);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);

        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        view4 = findViewByID_My(R.id.view4);

        pop_view = findViewByID_My(R.id.pop_view);
    }

    @Override
    protected void initData() {
        requestCode = getIntent().getIntExtra("requestCode", 0);

        storeId = getIntent().getStringExtra("storeId");
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

                            RelativeLayout relativeLayout =  holder.getView(R.id.relativeLayout);
                            if (selectItem == position){
                                relativeLayout.setVisibility(View.VISIBLE);
                            }else {
                                relativeLayout.setVisibility(View.GONE);
                            }
                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (requestCode == Constant.SELECT_DEVICE) {
                                        selectItem = position;
                                        mAdapter.notifyDataSetChanged();
                                    } else {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("deviceName", model.getId());
                                        CommonUtil.gotoActivityWithData(MyDeviceListActivity.this, DeviceDetailActivity.class, bundle, false);
                                    }
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
                textView4.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                textView3.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new PopupWindow_List4(MyDeviceListActivity.this, 0, list_status, i1, pop_view) {
                    @Override
                    public void onReturn(String keys, int item) {
                        textView1.setText(keys);

                        i1 = item;
                        if (item == 0) status = "";
                        else status = item + "";

                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView3.setTextColor(getResources().getColor(R.color.black3));
                textView4.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable1, null);
                textView3.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);

                new SelectCityDialog(MyDeviceListActivity.this, dialog) {
                    @Override
                    public void onCallBack(String province, String city, String district, String pId, String cId, String aId) {
                        textView2.setText(district);

                        provinceId = pId;
                        cityId = cId;
                        areaId = aId;
                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout3:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView3.setTextColor(getResources().getColor(R.color.green));
                textView4.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                textView3.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new SelectIndustryDialog(MyDeviceListActivity.this, dialog) {
                    @Override
                    public void onCallBack(String string_hangye, String id) {
                        textView3.setText(string_hangye);
                        instudyId = id;

                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout4:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView3.setTextColor(getResources().getColor(R.color.black3));
                textView4.setTextColor(getResources().getColor(R.color.green));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                textView3.setCompoundDrawables(null, null, drawable2, null);
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
        if (requestCode == Constant.SELECT_DEVICE) {
            titleView.setTitle("选择设备");
            titleView.showRightTxtBtn("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectItem >= 0) {
                        Intent resultIntent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("deviceName", list.get(selectItem).getHostName());
                        bundle.putString("deviceId", list.get(selectItem).getId());
                        resultIntent.putExtras(bundle);
                        MyDeviceListActivity.this.setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        myToast("请选择门店");
                    }
                }
            });
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        params.put("page", page + "");
        params.put("pageSize", "10");
        params.put("keyword", keyword);
        params.put("status", status);
        params.put("instudyId", instudyId);
        params.put("provinceId", provinceId);
        params.put("cityId", cityId);
        params.put("areaId", areaId);
        params.put("storeId",storeId);
        requestList(params);
    }
}
