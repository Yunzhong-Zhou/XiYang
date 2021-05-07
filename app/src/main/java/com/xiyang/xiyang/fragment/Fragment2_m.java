package com.xiyang.xiyang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.DispatchShopActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 分派
 */
public class Fragment2_m extends BaseFragment {
    TextView textView1,textView2,textView3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_m, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>setUserVisibleHint>>>" + isVisibleToUser);
        /*if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 1) {
                    requestServer();
                }
            }
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView(View view) {
        CommonUtil.setMargins(findViewByID_My(R.id.headView),0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);

    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        /*this.showLoadingPage();
        Map<String, String> params = new HashMap<>();
        Request(params);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                //商户分派
                CommonUtil.gotoActivity(getActivity(), DispatchShopActivity.class);
                break;
            case R.id.textView2:
                //门店分派
                CommonUtil.gotoActivity(getActivity(), DispatchShopActivity.class);
                break;
            case R.id.textView3:
                //工单分派
                CommonUtil.gotoActivity(getActivity(), DispatchShopActivity.class);
                break;

        }
    }

    @Override
    protected void updateView() {

    }

}
