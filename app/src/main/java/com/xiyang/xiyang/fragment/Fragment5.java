package com.xiyang.xiyang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.BankCardSettingActivity;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.activity.MyIncomeActivity;
import com.xiyang.xiyang.activity.MyProfileActivity;
import com.xiyang.xiyang.activity.MyShopListActivity;
import com.xiyang.xiyang.activity.MyStoreListActivity;
import com.xiyang.xiyang.activity.MyWorkListActivity;
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
public class Fragment5 extends BaseFragment {
    Fragment5Model model;
    ImageView imageView1;

    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12,
            linearLayout13, linearLayout14, linearLayout15;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment5, container, false);
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
                params.put("u_token", localUserInfo.getToken());
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

        /*Glide.with(getActivity())
                .load(IMGHOST + localUserInfo.getUserImage())
                .centerCrop()
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.headimg)//加载失败
                .into(imageView1);//加载图片*/

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
        OkhttpUtil.okHttpPost(URLs.Fragment5, params, headerMap, new CallBackUtil<Fragment5Model>() {
            @Override
            public Fragment5Model onParseResponse(Call call, Response response) {
                MainActivity.isOver = true;
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

                /*//保存头像
                localUserInfo.setUserImage(response.getUser_info().getHeadPortrait());
                if (!response.getUser_info().getHeadPortrait().equals("") && getActivity() != null)
                    Glide.with(getActivity()).load(IMGHOST + localUserInfo.getUserImage())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                //保存昵称
                localUserInfo.setNickname(response.getUser_info().getUserName());
                if (!localUserInfo.getNickname().equals("")) {
                    textView1.setText(localUserInfo.getNickname());
                }
                //保存电话号码
                localUserInfo.setPhoneNumber(response.getUser_info().getUserPhone());
                //保存职位
                localUserInfo.setUserJob(response.getUser_info().getIsDistri()+"");//为1、有分配权限

                //保存y_store_id
                localUserInfo.setBelongid(response.getUser_info().getYStoreId());

                //保存userid
                localUserInfo.setUserId(response.getUser_info().getUserId());*/
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
        params.put("u_token", localUserInfo.getToken());
        requestCenter(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                //个人资料
                CommonUtil.gotoActivity(getActivity(), MyProfileActivity.class);
                break;
            case R.id.linearLayout2:
                //我的商户
//                CommonUtil.gotoActivity(getActivity(), MyShopListActivity.class);
                break;
            case R.id.linearLayout3:
                //我的门店
//                CommonUtil.gotoActivity(getActivity(), MyStoreListActivity.class);
                break;
            case R.id.linearLayout4:
                //我的设备
//                CommonUtil.gotoActivity(getActivity(), MyDeviceListActivity.class);
                break;
            case R.id.linearLayout5:
                //我的营收
//                CommonUtil.gotoActivity(getActivity(), MyCollectionActivity.class);
                break;
            case R.id.linearLayout6:
                //待签商户
                CommonUtil.gotoActivity(getActivity(), MyShopListActivity.class);
                break;
            case R.id.linearLayout7:
                //代装门店
                CommonUtil.gotoActivity(getActivity(), MyStoreListActivity.class);
                break;
            case R.id.linearLayout8:
                //代接工单
                CommonUtil.gotoActivity(getActivity(), MyWorkListActivity.class);
                break;
            case R.id.linearLayout9:
                //我的工单
                CommonUtil.gotoActivity(getActivity(), MyWorkListActivity.class,false);
                break;
            case R.id.linearLayout10:
                //我的收益
                CommonUtil.gotoActivity(getActivity(), MyIncomeActivity.class,false);
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
                CommonUtil.gotoActivity(getActivity(), BankCardSettingActivity.class, false);
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
