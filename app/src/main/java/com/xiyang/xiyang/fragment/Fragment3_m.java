package com.xiyang.xiyang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lihang.ShadowLayout;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.ApproveContractListActivity;
import com.xiyang.xiyang.activity.PersonnelActivity;
import com.xiyang.xiyang.activity.PersonnelListActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 设备
 */
public class Fragment3_m extends BaseFragment {
    TextView textView1, textView2, textView3;
    ShadowLayout shadowLayout1,shadowLayout2,shadowLayout3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3_m, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (MainActivity.item == 2) {
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
        /*if (MainActivity.item == 2) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>setUserVisibleHint>>>" + isVisibleToUser);
        /*if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 2) {
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
        CommonUtil.setMargins(findViewByID_My(R.id.headView), 0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);

        shadowLayout1 = findViewByID_My(R.id.shadowLayout1);
        shadowLayout2 = findViewByID_My(R.id.shadowLayout2);
        shadowLayout3 = findViewByID_My(R.id.shadowLayout3);


        switch (localUserInfo.getUserJob()){
            case "CM":
            case "BDM":
                shadowLayout3.setVisibility(View.GONE);
                break;
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                //合同审批
                CommonUtil.gotoActivity(getActivity(), ApproveContractListActivity.class);
                break;
            case R.id.textView2:
                //人事审批
                CommonUtil.gotoActivity(getActivity(), PersonnelActivity.class);
                break;
            case R.id.textView3:
                //采购审批
                Bundle bundle = new Bundle();
                bundle.putInt("type",4);
                CommonUtil.gotoActivityWithData(getActivity(), PersonnelListActivity.class,bundle);
                break;
        }

    }
    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    protected void updateView() {

    }
}
