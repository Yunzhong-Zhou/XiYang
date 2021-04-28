package com.xiyang.xiyang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.AddContractActivity;
import com.xiyang.xiyang.activity.AddShopActivity;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.activity.MyContractActivity;
import com.xiyang.xiyang.activity.MyShopListActivity;
import com.xiyang.xiyang.activity.ShopDetailActivity;
import com.xiyang.xiyang.activity.TransferShopActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.model.Fragment1Model;
import com.xiyang.xiyang.model.MyFragment1Model;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;
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
 * Created by fafukeji01 on 2016/1/6.
 * 商户
 */

public class Fragment1 extends BaseFragment {
    Fragment1Model model;
    int type = 1;
    private RecyclerView recyclerView1, recyclerView2;
    List<MyFragment1Model> list1 = new ArrayList<>();
    CommonAdapter<MyFragment1Model> mAdapter1;
    List<Fragment1Model.MerchantsBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model.MerchantsBean> mAdapter2;
    TextView tv_mymore,tv_more;

    TextView textView1, textView2, textView3, textView4;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12;

    TextView tv_tab1, tv_tab2, tv_tab3, tv_tab4;
    LinearLayout ll_tab1, ll_tab2, ll_tab3, ll_tab4;
    View view1, view2, view3, view4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 0) {
            requestServer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 0) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>setUserVisibleHint>>>" + isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 0) {
                    requestServer();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView(View view) {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                Request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });

        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2 = findViewByID_My(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        tv_mymore = findViewByID_My(R.id.tv_mymore);
        tv_mymore.setOnClickListener(this);
        tv_more = findViewByID_My(R.id.tv_more);
        tv_more.setOnClickListener(this);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout4 = findViewByID_My(R.id.linearLayout4);
        linearLayout5 = findViewByID_My(R.id.linearLayout5);
        linearLayout6 = findViewByID_My(R.id.linearLayout6);
        linearLayout7 = findViewByID_My(R.id.linearLayout7);
        linearLayout8 = findViewByID_My(R.id.linearLayout8);
        linearLayout9 = findViewByID_My(R.id.linearLayout9);
        linearLayout10 = findViewByID_My(R.id.linearLayout10);
        linearLayout11 = findViewByID_My(R.id.linearLayout11);
        linearLayout12 = findViewByID_My(R.id.linearLayout12);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
        linearLayout6.setOnClickListener(this);
        linearLayout7.setOnClickListener(this);
        linearLayout8.setOnClickListener(this);
        linearLayout9.setOnClickListener(this);
        linearLayout10.setOnClickListener(this);
        linearLayout11.setOnClickListener(this);
        linearLayout12.setOnClickListener(this);

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

    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Fragment1, params, headerMap, new CallBackUtil<Fragment1Model>() {
            @Override
            public Fragment1Model onParseResponse(Call call, Response response) {
                MainActivity.isOver = true;
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                MainActivity.isOver = true;
                hideProgress();
                showErrorPage();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment1Model response) {
                hideProgress();
                showContentPage();
                model = response;
                textView1.setText(response.getManageMerchantNum());
                textView2.setText(response.getSignMerchantNum());
                textView3.setText(response.getRecommendMerchantNum());
                textView4.setText(response.getMoney());

                mAdapter1 = new CommonAdapter<MyFragment1Model>
                        (getActivity(), R.layout.item_fragment1_1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, MyFragment1Model model, int position) {
                        holder.setText(R.id.tv1, model.getName());
                        holder.setText(R.id.tv2, model.getCreatedAt());
                    }
                };
                recyclerView1.setAdapter(mAdapter1);

                changeUI();



                /**
                 * 商户列表
                 */
                list2 = response.getMerchants();
                if (list2.size() > 0) {
                    mAdapter2 = new CommonAdapter<Fragment1Model.MerchantsBean>
                            (getActivity(), R.layout.item_fragment1_2, list2) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment1Model.MerchantsBean model, int position) {
                            holder.setText(R.id.tv_name, model.getName());//标题
                            holder.setText(R.id.tv_shop, model.getDeviceNum());
                            holder.setText(R.id.tv_num, model.getMoney());//money
                            holder.setText(R.id.tv_addr, model.getAddress());

                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(model.getImage())
//                                .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            ImageView imageView2 = holder.getView(R.id.imageView2);
                            if (model.getStatus() != null && model.getStatus().equals("1")) {
                                //待签约
                                imageView2.setImageResource(R.mipmap.bg_daiqianyue);
                            } else {
                                imageView2.setImageResource(R.mipmap.bg_yiqianyue);
                            }
                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    CommonUtil.gotoActivityWithData(getActivity(), ShopDetailActivity.class, bundle, false);

                                }
                            });

                        }
                    };
                    recyclerView2.setAdapter(mAdapter2);
                } else {
                    showEmptyPage();
                }

                MainActivity.isOver = true;

            }
        });
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.linearLayout1:
                //运维商户
//                CommonUtil.gotoActivity(getActivity(), PersonalSettingActivity.class);
                break;
            case R.id.linearLayout2:
                //签约商户
//                CommonUtil.gotoActivity(getActivity(), VerifiedActivity.class);
                break;
            case R.id.linearLayout3:
                //推荐商户
//                CommonUtil.gotoActivity(getActivity(), MyPublishActivity.class);
                break;
            case R.id.linearLayout4:
                //商户营收
//                CommonUtil.gotoActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.linearLayout5:
                //添加商户
                CommonUtil.gotoActivity(getActivity(), AddShopActivity.class);
                break;
            case R.id.linearLayout6:
            case R.id.tv_mymore:
                //我的商户
                CommonUtil.gotoActivity(getActivity(), MyShopListActivity.class);
                break;
            case R.id.linearLayout7:
                //取消商户
                bundle.putInt("item_hetong", 6);
                CommonUtil.gotoActivityWithData(getActivity(), AddContractActivity.class, bundle);
//                CommonUtil.gotoActivity(getActivity(), CancelShopActivity.class);
                break;
            case R.id.linearLayout8:
                //修改商户
                bundle.putInt("item_hetong", 4);
                CommonUtil.gotoActivityWithData(getActivity(), AddContractActivity.class, bundle);
                break;
            case R.id.linearLayout9:
                //添加合同
                bundle.putInt("item_hetong", 0);
                CommonUtil.gotoActivityWithData(getActivity(), AddContractActivity.class, bundle);
                break;
            case R.id.linearLayout10:
            case R.id.tv_more:
                //我的合同
                CommonUtil.gotoActivity(getActivity(), MyContractActivity.class);
                break;
            case R.id.linearLayout11:
                //划转商户
                CommonUtil.gotoActivity(getActivity(), TransferShopActivity.class);
                break;
            case R.id.linearLayout12:
                //续签商户
                bundle.putInt("item_hetong", 5);
                CommonUtil.gotoActivityWithData(getActivity(), AddContractActivity.class, bundle);
//                CommonUtil.gotoActivity(getActivity(), RenewContractActivity.class);
                break;

            case R.id.ll_tab1:
                //待签约
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //待审核
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //已驳回
                type = 3;
                changeUI();
                break;
            case R.id.ll_tab4:
                //已通过
                type = 4;
                changeUI();
                break;

        }
    }

    private void changeUI() {
        list1.clear();
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
                for (Fragment1Model.WaitSignBean bean:model.getWaitSign()){
                    list1.add(new MyFragment1Model(bean.getId(),bean.getName(),bean.getCreatedAt()));
                }
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
                for (Fragment1Model.WaitCheckBean bean:model.getWaitCheck()){
                    list1.add(new MyFragment1Model(bean.getId(),bean.getName(),bean.getCreatedAt()));
                }
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
                for (Fragment1Model.HasRefuseBean bean:model.getHasRefuse()){
                    list1.add(new MyFragment1Model(bean.getId(),bean.getName(),bean.getCreatedAt()));
                }
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
                for (Fragment1Model.HasCheckedBean bean:model.getHasChecked()){
                    list1.add(new MyFragment1Model(bean.getId(),bean.getName(),bean.getCreatedAt()));
                }
                break;

        }
        mAdapter1.notifyDataSetChanged();
    }

    @Override
    protected void updateView() {

    }

}
