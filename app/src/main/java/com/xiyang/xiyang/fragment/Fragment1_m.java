package com.xiyang.xiyang.fragment;

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
import com.xiyang.xiyang.activity.AddStaffActivity;
import com.xiyang.xiyang.activity.AdjustmentActivity;
import com.xiyang.xiyang.activity.AdjustmentListActivity;
import com.xiyang.xiyang.activity.DispatchActivity;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.activity.MyCityActivity;
import com.xiyang.xiyang.activity.MyDeviceListActivity;
import com.xiyang.xiyang.activity.MyShopListActivity;
import com.xiyang.xiyang.activity.MyStoreListActivity;
import com.xiyang.xiyang.activity.StaffDetailActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.model.Fragment1Model_m;
import com.xiyang.xiyang.model.SubordinateModel;
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
 * 管理
 */

public class Fragment1_m extends BaseFragment {
    int type = 1;
    private RecyclerView recyclerView;
    List<SubordinateModel.ListBean> list = new ArrayList<>();
    CommonAdapter<SubordinateModel.ListBean> mAdapter;

    TextView textView1, textView2, textView3, textView4,
            tv_lable1, tv_lable2, tv_lable3, tv_lable4,
            tv_tianjia, tv_fenpai, tv_tiaozheng, tv_mycity;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_m, container, false);

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
                request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        tv_lable1 = findViewByID_My(R.id.tv_lable1);
        tv_lable2 = findViewByID_My(R.id.tv_lable2);
        tv_lable3 = findViewByID_My(R.id.tv_lable3);
        tv_lable4 = findViewByID_My(R.id.tv_lable4);
        tv_tianjia = findViewByID_My(R.id.tv_tianjia);
        tv_fenpai = findViewByID_My(R.id.tv_fenpai);
        tv_tiaozheng = findViewByID_My(R.id.tv_tiaozheng);
        tv_mycity = findViewByID_My(R.id.tv_mycity);

        switch (localUserInfo.getUserJob()) {
            case "rm":
                tv_lable1.setText("总CM");
                tv_lable2.setText("总BDM");
                tv_lable3.setText("总BD");
                tv_lable4.setText("总营收");
                tv_tianjia.setText("添加CM");
                tv_fenpai.setText("分派CM");
                tv_tiaozheng.setText("调整BDM");
                tv_mycity.setText("我的城市");
                break;
            case "cm":
                tv_lable1.setText("总BDM");
                tv_lable2.setText("总BD");
                tv_lable3.setText("总门店");
                tv_lable4.setText("总营收");
                tv_tianjia.setText("添加BDM");
                tv_fenpai.setText("分派BDM");
                tv_tiaozheng.setText("调整BD");
                tv_mycity.setText("我的市区");
                break;
            case "bdm":
                tv_lable1.setText("总BD");
                tv_lable2.setText("总设备");
                tv_lable3.setText("总门店");
                tv_lable4.setText("总营收");
                tv_tianjia.setText("添加BD");
                tv_fenpai.setText("分派BD");
                tv_tiaozheng.setText("调整BD");
                tv_mycity.setText("我的市区");
                linearLayout10.setVisibility(View.INVISIBLE);
                linearLayout11.setVisibility(View.INVISIBLE);
                linearLayout12.setVisibility(View.INVISIBLE);

                break;
        }


    }

    @Override
    protected void initData() {
//        requestServer();

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        Map<String, String> params = new HashMap<>();
        request(params);
    }

    private void request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Fragment1_m, params, headerMap, new CallBackUtil<Fragment1Model_m>() {
            @Override
            public Fragment1Model_m onParseResponse(Call call, Response response) {
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
            public void onResponse(Fragment1Model_m response) {
                hideProgress();
                switch (localUserInfo.getUserJob()) {
                    case "rm":
                        textView1.setText(response.getBase().getCmNum());
                        textView2.setText(response.getBase().getBdmNum());
                        textView3.setText(response.getBase().getBdmNum());
                        textView4.setText(response.getBase().getMoney());
                        break;
                    case "cm":
                        textView1.setText(response.getBase().getBdmNum());
                        textView2.setText(response.getBase().getBdNum());
                        textView3.setText(response.getBase().getStoreNum());
                        textView4.setText(response.getBase().getMoney());
                        break;
                    case "bdm":
                        textView1.setText(response.getBase().getBdNum());
                        textView2.setText(response.getBase().getDeviceNum());
                        textView3.setText(response.getBase().getStoreNum());
                        textView4.setText(response.getBase().getMoney());
                        break;
                }

                Map<String, String> params = new HashMap<>();
                requestList(params);//获取下级员工
                MainActivity.isOver = true;
            }
        });
    }

    /**
     * 获取下级员工
     *
     * @param params
     */
    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Subordinate, params, headerMap, new CallBackUtil<SubordinateModel>() {
            @Override
            public SubordinateModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showErrorPage();
                myToast(err);
            }

            @Override
            public void onResponse(SubordinateModel response) {
                hideProgress();
                showContentPage();
                list = response.getList();
                if (list.size() > 0) {
                    mAdapter = new CommonAdapter<SubordinateModel.ListBean>
                            (getActivity(), R.layout.item_fragment1_m, list) {
                        @Override
                        protected void convert(ViewHolder holder, SubordinateModel.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(model.getHead())
                                    .fitCenter()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name, model.getName());
                            holder.setText(R.id.tv_phone, model.getMobile());
                            holder.setText(R.id.tv_money, model.getMoney());

                            TextView tv_addr = holder.getView(R.id.tv_addr);
                            TextView tv1 = holder.getView(R.id.tv1);
                            TextView tv2 = holder.getView(R.id.tv2);
                            tv1.setVisibility(View.VISIBLE);
                            tv2.setVisibility(View.VISIBLE);

                            switch (localUserInfo.getUserJob()) {
                                case "rm":
                                    tv_addr.setText(model.getAddress());
                                    tv1.setText("BDM:" + model.getBdmNum());
                                    tv2.setText("BD:" + model.getBdNum());
                                    break;
                                case "cm":
                                    tv_addr.setText(model.getAddress());
//                                    tv1.setText("BDM:"+model.getBdmNum());
                                    tv1.setVisibility(View.GONE);
                                    tv2.setText("BD:" + model.getBdNum());
                                    break;
                                case "bdm":
                                    tv1.setText("门店:" + model.getStoreNum());
                                    tv2.setText("设备:" + model.getDeviceNum());
                                    break;
                            }

                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    CommonUtil.gotoActivityWithData(getActivity(), StaffDetailActivity.class, bundle, false);
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
            case R.id.linearLayout1:
                //总CM
//                CommonUtil.gotoActivity(getActivity(), PersonalSettingActivity.class);
                break;
            case R.id.linearLayout2:
                //总BDM
//                CommonUtil.gotoActivity(getActivity(), VerifiedActivity.class);
                break;
            case R.id.linearLayout3:
                //总BD
//                CommonUtil.gotoActivity(getActivity(), MyPublishActivity.class);
                break;
            case R.id.linearLayout4:
                //总营收
//                CommonUtil.gotoActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.linearLayout5:
                //添加CM
                CommonUtil.gotoActivity(getActivity(), AddStaffActivity.class);
                break;
            case R.id.linearLayout6:
                //我的门店
                CommonUtil.gotoActivity(getActivity(), MyStoreListActivity.class);
                break;
            case R.id.linearLayout7:
                //我的商户
                CommonUtil.gotoActivity(getActivity(), MyShopListActivity.class);
                break;
            case R.id.linearLayout8:
                //我的设备
                CommonUtil.gotoActivity(getActivity(), MyDeviceListActivity.class);
                break;
            case R.id.linearLayout9:
                //分派CM
                CommonUtil.gotoActivity(getActivity(), DispatchActivity.class);
                break;
            case R.id.linearLayout10:
                //调整角色
                CommonUtil.gotoActivity(getActivity(), AdjustmentActivity.class);
                break;
            case R.id.linearLayout11:
                //调整BDM
                CommonUtil.gotoActivity(getActivity(), AdjustmentListActivity.class);
                break;
            case R.id.linearLayout12:
                //我的城市
                CommonUtil.gotoActivity(getActivity(), MyCityActivity.class);
                break;


        }
    }

    @Override
    protected void updateView() {

    }

}
