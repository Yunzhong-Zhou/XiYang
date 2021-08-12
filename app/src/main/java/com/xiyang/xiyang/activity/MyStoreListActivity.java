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
import com.xiyang.xiyang.model.MyStoreListModel;
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
 * 我的门店
 */
public class MyStoreListActivity extends BaseActivity {
    int requestCode = 0, selectItem = -1;
    private RecyclerView recyclerView;
    List<MyStoreListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<MyStoreListModel.RecordsBean> mAdapter;

    //筛选
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    private TextView textView1, textView2, textView3, textView4;
    private View view1, view2, view3, view4;
    private LinearLayout pop_view;
    int page = 1;

    List<String> list_status = new ArrayList<>();
    String status = "", keyword = "", instudyId = "", provinceId = "", cityId = "", areaId = "",shopId="";
    int i1 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshoplist);
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
                params.put("status", status);
                params.put("keyword", keyword);
                params.put("instudyId", instudyId);
                params.put("provinceId", provinceId);
                params.put("cityId", cityId);
                params.put("areaId", areaId);
                params.put("shopId",shopId);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("page", page + "");
                params.put("pageSize", "10");
                params.put("status", status);
                params.put("keyword", keyword);
                params.put("instudyId", instudyId);
                params.put("provinceId", provinceId);
                params.put("cityId", cityId);
                params.put("areaId", areaId);
                params.put("shopId",shopId);
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
        shopId = getIntent().getStringExtra("shopId");
        requestCode = getIntent().getIntExtra("requestCode", 0);
        status = getIntent().getStringExtra("status");//状态 0 => '待指派',1 => '待签约',2 => '待审核',3 => '正常',4 => '待续约'
        if (status == null) status = "";
        requestServer();//获取数据

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
        OkhttpUtil.okHttpPostJson(URLs.MyStoreList, GsonUtils.toJson(params), headerMap, new CallBackUtil<MyStoreListModel>() {
            @Override
            public MyStoreListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyStoreListModel response) {
                showContentPage();
                hideProgress();
                list = response.getRecords();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<MyStoreListModel.RecordsBean>
                            (MyStoreListActivity.this, R.layout.item_fragment2_2, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyStoreListModel.RecordsBean model, int position) {
                            holder.setText(R.id.tv_name, model.getName());//标题
                            holder.setText(R.id.tv_shop, model.getDeviceNumber());
                            holder.setText(R.id.tv_num, model.getTotalRevenue());//money
                            holder.setText(R.id.tv_addr, model.getAddress());

                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(MyStoreListActivity.this)
                                    .load(model.getImage())
//                                .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(MyStoreListActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            ImageView imageView2 = holder.getView(R.id.imageView2);
                            if (model.getVisitStatus() != null && model.getVisitStatus().equals("3")) {
                                //已拜访
                                imageView2.setImageResource(R.mipmap.bg_yibaifang);
                            } else {
                                imageView2.setImageResource(R.mipmap.bg_daibaifang);
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
                                    if (requestCode == Constant.SELECT_STORE) {
                                        selectItem = position;
                                        mAdapter.notifyDataSetChanged();
                                    } else {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", model.getId());
                                        CommonUtil.gotoActivityWithData(MyStoreListActivity.this, StoreDetailActivity.class, bundle, false);
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
        OkhttpUtil.okHttpPostJson(URLs.MyStoreList, GsonUtils.toJson(params), headerMap, new CallBackUtil<MyStoreListModel>() {
            @Override
            public MyStoreListModel onParseResponse(Call call, Response response) {
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
            public void onResponse(MyStoreListModel response) {
//                showContentPage();
                hideProgress();
                List<MyStoreListModel.RecordsBean> list1 = new ArrayList<>();
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
                new PopupWindow_List4(MyStoreListActivity.this, 0, list_status, i1, pop_view) {
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

                new SelectCityDialog(MyStoreListActivity.this, dialog) {
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
                new SelectIndustryDialog(MyStoreListActivity.this, dialog) {
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
                new SearchDialog(MyStoreListActivity.this, dialog) {
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
        titleView.setTitle("我的门店");
        if (requestCode == Constant.SELECT_STORE) {
            titleView.setTitle("选择门店");
            titleView.showRightTxtBtn("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectItem >= 0) {
                        Intent resultIntent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("storeId", list.get(selectItem).getId());
                        bundle.putString("storeName", list.get(selectItem).getName());
                        resultIntent.putExtras(bundle);
                        MyStoreListActivity.this.setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        myToast("请选择门店");
                    }
                }
            });
        } else if (localUserInfo.getUserJob().equals("BD")) {
            titleView.showRightTextview("添加门店", true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonUtil.gotoActivity(MyStoreListActivity.this, SelectAddressActivity.class);
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
        params.put("status", status);
        params.put("keyword", keyword);
        params.put("instudyId", instudyId);
        params.put("provinceId", provinceId);
        params.put("cityId", cityId);
        params.put("areaId", areaId);
        params.put("shopId",shopId);
        requestList(params);
    }
}
