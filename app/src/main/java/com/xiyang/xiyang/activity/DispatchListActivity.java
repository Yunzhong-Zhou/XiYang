package com.xiyang.xiyang.activity;

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
import com.xiyang.xiyang.model.DispatchListModel;
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
 * Created by Mr.Z on 2021/5/7.
 * 分派列表
 */
public class DispatchListActivity extends BaseActivity {
    int page = 1, type = 1, type_m = 1;//1、商户分派 2、门店分派 3、工单分派
    private RecyclerView recyclerView;
    List<DispatchListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<DispatchListModel.RecordsBean> mAdapter;
    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchlist);
    }

    @Override
    protected void initView() {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                params.clear();
                params.put("page", page + "");
                params.put("size", "10");
                params.put("status", type + "");//1:待处理 2:已完成; 3:上报中

                switch (type_m) {
                    case 1:
                        //商户
                        requestList(params, URLs.DispatchShopList);
                        break;
                    case 2:
                        //门店
                        requestList(params, URLs.DispatchStoreList);
                        break;
                    case 3:
                        //工单
                        requestList(params, URLs.DispatchWorkList);
                        break;
                }
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                params.clear();
                params.put("page", page + "");
                params.put("size", "10");
                params.put("status", type + "");//1:待处理 2:已完成; 3:上报中
                switch (type_m) {
                    case 1:
                        //商户
                        requestListMore(params, URLs.DispatchShopList);
                        break;
                    case 2:
                        //门店
                        requestListMore(params, URLs.DispatchStoreList);
                        break;
                    case 3:
                        //工单
                        requestListMore(params, URLs.DispatchWorkList);
                        break;
                }
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
    }

    @Override
    protected void initData() {
        type_m = getIntent().getIntExtra("type_m", 1);
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        params.clear();
        params.put("page", page + "");
        params.put("size", "10");
        params.put("status", type + "");//1:待处理 2:已完成; 3:上报中

        switch (type_m) {
            case 1:
                //商户
                requestList(params, URLs.DispatchShopList);
                break;
            case 2:
                //门店
                requestList(params, URLs.DispatchStoreList);
                break;
            case 3:
                //工单
                requestList(params, URLs.DispatchWorkList);
                break;
        }

    }

    private void requestList(Map<String, String> params, String url) {
        OkhttpUtil.okHttpPostJson(url, GsonUtils.toJson(params), headerMap, new CallBackUtil<DispatchListModel>() {
            @Override
            public DispatchListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showErrorPage();
                myToast(err);
            }

            @Override
            public void onResponse(DispatchListModel response) {
                hideProgress();
                showContentPage();
                list = response.getRecords();
                if (list.size() > 0) {
                    mAdapter = new CommonAdapter<DispatchListModel.RecordsBean>
                            (DispatchListActivity.this, R.layout.item_dispatchlist, list) {
                        @Override
                        protected void convert(ViewHolder holder, DispatchListModel.RecordsBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            ImageView iv_img1 = holder.getView(R.id.iv_img1);
                            ImageView iv_img2 = holder.getView(R.id.iv_img2);
                            TextView tv_num = holder.getView(R.id.tv_num);
                            imageView1.setVisibility(View.VISIBLE);
                            iv_img1.setVisibility(View.VISIBLE);
                            iv_img2.setVisibility(View.VISIBLE);
                            tv_num.setVisibility(View.VISIBLE);

                            switch (type_m) {
                                case 1:
                                    //商户
                                    iv_img1.setImageResource(R.mipmap.ic_shop_gray);
                                    Glide.with(DispatchListActivity.this)
                                            .load(model.getMerchantLogoUrl())
                                            .fitCenter()
                                            .apply(RequestOptions.bitmapTransform(new
                                                    RoundedCorners(CommonUtil.dip2px(DispatchListActivity.this, 10))))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(imageView1);//加载图片
                                    holder.setText(R.id.tv_name, model.getMerchantName());
                                    holder.setText(R.id.tv_shop, model.getMerchantTotalStoresNumber() + "");
                                    holder.setText(R.id.tv_addr, model.getMerchantAddress());
                                    tv_num.setText(model.getMerchantTotalRevenue());
                                    break;
                                case 2:
                                    //门店
                                    iv_img1.setImageResource(R.mipmap.ic_store_gray);
                                    Glide.with(DispatchListActivity.this)
                                            .load(model.getMerchantLogoUrl())
                                            .fitCenter()
                                            .apply(RequestOptions.bitmapTransform(new
                                                    RoundedCorners(CommonUtil.dip2px(DispatchListActivity.this, 10))))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(imageView1);//加载图片
                                    holder.setText(R.id.tv_name, model.getStoreName());
                                    holder.setText(R.id.tv_shop, model.getMerchantTotalStoresNumber() + "");
                                    holder.setText(R.id.tv_addr, model.getMerchantAddress());
                                    tv_num.setText(model.getMerchantTotalRevenue());
                                    break;
                                case 3:
                                    //工单
                                    imageView1.setVisibility(View.GONE);
                                    iv_img1.setVisibility(View.GONE);
                                    iv_img2.setVisibility(View.GONE);
                                    tv_num.setVisibility(View.GONE);


                                    break;
                            }

                            TextView tv_shangbao = holder.getView(R.id.tv_shangbao);
                            TextView tv_zhipai = holder.getView(R.id.tv_zhipai);
                            TextView tv_bdname = holder.getView(R.id.tv_bdname);
                            TextView tv_type = holder.getView(R.id.tv_type);
                            switch (type) {//1:待处理 2:已完成; 3:上报中
                                case 1:
                                    //待处理
                                    tv_shangbao.setVisibility(View.VISIBLE);
                                    tv_zhipai.setVisibility(View.VISIBLE);
                                    tv_bdname.setVisibility(View.GONE);
                                    tv_type.setVisibility(View.GONE);
                                    break;
                                case 2:
                                    //已完成
                                    tv_shangbao.setVisibility(View.GONE);
                                    tv_zhipai.setVisibility(View.GONE);
                                    tv_bdname.setVisibility(View.VISIBLE);
//                                    tv_bdname.setText(model.getMerchantName());
                                    tv_type.setVisibility(View.VISIBLE);
                                    tv_type.setTextColor(getResources().getColor(R.color.gray));
                                    tv_type.setText("已完成");
                                    break;
                                case 3:
                                    //上报中
                                    tv_shangbao.setVisibility(View.GONE);
                                    tv_zhipai.setVisibility(View.GONE);
                                    tv_bdname.setVisibility(View.GONE);
                                    tv_type.setVisibility(View.VISIBLE);
                                    tv_type.setTextColor(getResources().getColor(R.color.green));
                                    tv_type.setText("上报中");
                                    break;
                            }

                            //上报
                            tv_shangbao.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认上报吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, getString(R.string.app_loading1));
                                            params.clear();
                                            switch (type_m) {
                                                case 1:
                                                    //商户
                                                    requestShangBao(model.getId(), URLs.DispatchShop_ShangBao);
                                                    break;
                                                case 2:
                                                    //门店
                                                    requestShangBao(model.getId(), URLs.DispatchStore_ShangBao);
                                                    break;
                                                case 3:
                                                    //工单
                                                    requestShangBao(model.getId(), URLs.DispatchWork_ShangBao);
                                                    break;
                                            }
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void requestListMore(Map<String, String> params, String url) {
        OkhttpUtil.okHttpPostJson(url, GsonUtils.toJson(params), headerMap, new CallBackUtil<DispatchListModel>() {
            @Override
            public DispatchListModel onParseResponse(Call call, Response response) {
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
            public void onResponse(DispatchListModel response) {
//                showContentPage();
                hideProgress();
                List<DispatchListModel.RecordsBean> list1 = new ArrayList<>();
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

    /**
     * 上报
     * @param id
     * @param url
     */
    private void requestShangBao(String id, String url) {
        OkhttpUtil.okHttpPost(url + id, params, headerMap, new CallBackUtil<String>() {
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
                hideProgress();
                myToast("上报提交成功");
                type = 3;//1:待处理 2:已完成; 3:上报中
                changeUI();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;

            case R.id.ll_tab1:
                //待处理
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //上报中
                type = 3;
                changeUI();
                break;
            case R.id.ll_tab3:
                //已完成
                type = 2;
                changeUI();
                break;

        }
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
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                break;

        }
        requestServer();

    }
}
