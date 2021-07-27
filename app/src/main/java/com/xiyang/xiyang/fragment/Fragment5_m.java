package com.xiyang.xiyang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.BankCardActivity;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.activity.MyDeviceListActivity;
import com.xiyang.xiyang.activity.MyIncomeActivity;
import com.xiyang.xiyang.activity.MyProfileActivity;
import com.xiyang.xiyang.activity.MyShopListActivity;
import com.xiyang.xiyang.activity.MyStoreListActivity;
import com.xiyang.xiyang.activity.PersonnelActivity;
import com.xiyang.xiyang.activity.PersonnelListActivity;
import com.xiyang.xiyang.activity.SetUpActivity;
import com.xiyang.xiyang.activity.ShareProfitListActivity;
import com.xiyang.xiyang.activity.TakeCashActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.model.Fragment5Model;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by fafukeji01 on 2016/1/6.
 * 我的
 */
public class Fragment5_m extends BaseFragment {
    Fragment5Model model;
    ImageView imageView1;

    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12,
            linearLayout13, linearLayout14, linearLayout15, linearLayout16, ll_cm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment5_m, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 4) {
            requestServer();
        }
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 4) {
            requestServer();
        }
    }*/

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>setUserVisibleHint>>>" + isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 4) {
                    requestServer();
                }
            }
        }
    }


    @Override
    protected void initView(View view) {
//        CommonUtil.setMargins(findViewByID_My(R.id.headView),0, (int) CommonUtil.getStatusBarHeight(getActivity()),0,0);
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                requestCenter(params);
            }

            @Override
            public void onLoadmore() {

            }
        });

        imageView1 = findViewByID_My(R.id.imageView1);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);

        textView1.setText(localUserInfo.getNickname());
        textView2.setText(localUserInfo.getUserJob());
        Glide.with(getActivity())
                .load(localUserInfo.getUserImage())
                .centerCrop()
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.headimg)//加载失败
                .into(imageView1);//加载图片

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
        linearLayout13 = findViewByID_My(R.id.linearLayout13);
        linearLayout14 = findViewByID_My(R.id.linearLayout14);
        linearLayout15 = findViewByID_My(R.id.linearLayout15);
        linearLayout16 = findViewByID_My(R.id.linearLayout16);

        ll_cm = findViewByID_My(R.id.ll_cm);
        if (localUserInfo.getUserJob().equals("CM")) ll_cm.setVisibility(View.VISIBLE);
        else ll_cm.setVisibility(View.GONE);

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
        linearLayout13.setOnClickListener(this);
        linearLayout14.setOnClickListener(this);
        linearLayout15.setOnClickListener(this);
        linearLayout16.setOnClickListener(this);


         /*LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        sp_params.height = CommonUtil.getScreenHeight(getActivity()) / 4;
        linearLayout.setLayoutParams(sp_params);*/

       /* LinearLayout linearLayout = findViewByID_My(R.id.linearLayout);
        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = linearLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(getActivity()) / 3;*/
    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void requestCenter(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Fragment5, params, headerMap, new CallBackUtil<Fragment5Model>() {
            @Override
            public Fragment5Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                MainActivity.isOver = true;
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment5Model response) {
                hideProgress();
                model = response;

                textView3.setText(response.getUserinfo().getAvailableMoney());
                textView4.setText(response.getUserinfo().getTotalMoney());
                textView5.setText(response.getUserinfo().getMonthRevenueMoney());
                textView6.setText(response.getUserinfo().getTotalRevenueMoney());

                //保存头像
                localUserInfo.setUserImage(response.getUserinfo().getAvatar());
                //保存昵称
                localUserInfo.setNickname(response.getUserinfo().getName());
                //保存电话号码
//                localUserInfo.setPhoneNumber(response.getUserinfo().);
                //保存职位
                localUserInfo.setUserJob(response.getUserinfo().getJobTitle());//为1、有分配权限
                //保存userid
                localUserInfo.setUserId(response.getUserinfo().getId());

                textView1.setText(localUserInfo.getNickname());
                textView2.setText(localUserInfo.getUserJob());
                Glide.with(getActivity())
                        .load(localUserInfo.getUserImage())
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片


                MainActivity.isOver = true;
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        requestCenter(params);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.linearLayout1:
                //个人资料
                CommonUtil.gotoActivity(getActivity(), MyProfileActivity.class);
                break;
            case R.id.linearLayout2:
                //可用收益
//                CommonUtil.gotoActivity(getActivity(), MyShopListActivity.class);
                break;
            case R.id.linearLayout3:
                //总收益
//                CommonUtil.gotoActivity(getActivity(), MyStoreListActivity.class);
                break;
            case R.id.linearLayout4:
                //当日营收
//                CommonUtil.gotoActivity(getActivity(), MyDeviceListActivity.class);
                break;
            case R.id.linearLayout5:
                //我的营收
//                CommonUtil.gotoActivity(getActivity(), MyCollectionActivity.class);
                break;
            case R.id.linearLayout6:
                //我的商户
                CommonUtil.gotoActivity(getActivity(), MyShopListActivity.class);
                break;
            case R.id.linearLayout7:
                //我的门店
                CommonUtil.gotoActivity(getActivity(), MyStoreListActivity.class);
                break;
            case R.id.linearLayout8:
                //我的设备
                CommonUtil.gotoActivity(getActivity(), MyDeviceListActivity.class);
                break;
            case R.id.linearLayout9:
                //采购记录
                bundle.putInt("type",4);
                CommonUtil.gotoActivityWithData(getActivity(), PersonnelListActivity.class,bundle);
                break;
            case R.id.linearLayout16:
                //人事记录
                CommonUtil.gotoActivity(getActivity(), PersonnelActivity.class);
                break;
            case R.id.linearLayout10:
                //我的收益
                CommonUtil.gotoActivity(getActivity(), MyIncomeActivity.class, false);
                break;
            case R.id.linearLayout11:
                //提现收益
                CommonUtil.gotoActivity(getActivity(), TakeCashActivity.class, false);
                break;
            case R.id.linearLayout12:
                //分润记录
                CommonUtil.gotoActivity(getActivity(), ShareProfitListActivity.class, false);
                break;
            case R.id.linearLayout13:
                //资料管理
                CommonUtil.gotoActivity(getActivity(), MyProfileActivity.class, false);
                break;
            case R.id.linearLayout14:
                //绑定银行卡
                CommonUtil.gotoActivity(getActivity(), BankCardActivity.class, false);
                break;
            case R.id.linearLayout15:
                //设置中心
                CommonUtil.gotoActivity(getActivity(), SetUpActivity.class);
                break;
        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
