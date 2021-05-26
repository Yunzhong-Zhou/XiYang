package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.Fragment2Model;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
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
 * Created by Mr.Z on 2021/5/7.
 * 分派列表
 */
public class DispatchListActivity extends BaseActivity {
    int type = 1,type_m = 1;//1、商户分派 2、门店分派 3、工单分派
    private RecyclerView recyclerView;
    List<Fragment2Model> list = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter;
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
                Map<String, String> params = new HashMap<>();
                request(params);
            }

            @Override
            public void onLoadmore() {

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
        type_m = getIntent().getIntExtra("type_m",1);
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        Map<String, String> params = new HashMap<>();
        request(params);
    }

    private void request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Fragment2_m, params, headerMap, new CallBackUtil<Fragment2Model>() {
            @Override
            public Fragment2Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showErrorPage();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment2Model response) {
                hideProgress();
//                list2 = response.getCooperation_shop_list();
                if (list.size() > 0) {
                    mAdapter = new CommonAdapter<Fragment2Model>
                            (DispatchListActivity.this, R.layout.item_dispatchlist, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment2Model model, int position) {
                            /*ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(DispatchShopActivity.this)
                                    .load(URLs.IMGHOST + model.getCover())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(DispatchShopActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            ImageView imageView2 = holder.getView(R.id.imageView2);
                            if (model.getStatus() == 1) {
                                //待安装
                                imageView2.setImageResource(R.mipmap.bg_anzhuangzhong);
                            } else {
                                imageView2.setImageResource(R.mipmap.bg_yianzhuang);
                            }

                            holder.setText(R.id.tv_name, model.getTitle());
                            holder.setText(R.id.tv_content, model.getProvince() + model.getCity() + model.getDistrict());
                            holder.setText(R.id.tv_addr, model.getAddress());
                            holder.setText(R.id.tv_num, model.getNum() + "");*/
                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
//                    bundle.putString("id",model.getId());
                                    CommonUtil.gotoActivityWithData(DispatchListActivity.this, ShopDetailActivity.class, bundle, false);

                                    switch (type) {
                                        case 1:
                                            break;
                                        case 2:
                                            CommonUtil.gotoActivityWithData(DispatchListActivity.this, StoreDetailActivity.class, bundle, false);
                                            break;
                                        case 3:
                                            CommonUtil.gotoActivityWithData(DispatchListActivity.this, WorkListDetailActivity.class, bundle, false);
                                            break;
                                    }

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
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //已完成
                type = 3;
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
                /*if (list1.size() > 0) {
                    showContentPage();
                    recyclerView1.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
                } else {
                    showEmptyPage();
                }*/
                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);

                break;

        }
    }
}
