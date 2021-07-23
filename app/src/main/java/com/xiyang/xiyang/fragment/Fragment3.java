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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cretin.tools.scancode.CaptureActivity;
import com.cretin.tools.scancode.config.ScanConfig;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.AddWorkListActivity;
import com.xiyang.xiyang.activity.AffairListActivity;
import com.xiyang.xiyang.activity.DebugDeviceActivity;
import com.xiyang.xiyang.activity.DeviceDetailActivity;
import com.xiyang.xiyang.activity.DeviceListActivity_Position;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.activity.MyDeviceListActivity;
import com.xiyang.xiyang.activity.SelectDeviceActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.model.Fragment3Model;
import com.xiyang.xiyang.model.MyFragment1Model;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 设备
 */
public class Fragment3 extends BaseFragment {
    Fragment3Model model;
    int type = 1;
    private RecyclerView recyclerView1, recyclerView2;
    List<MyFragment1Model> list1 = new ArrayList<>();
    CommonAdapter<MyFragment1Model> mAdapter1;
        List<Fragment3Model.DeviceListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.DeviceListBean> mAdapter2;
    TextView tv_mymore;

    TextView textView1, textView2, textView3, textView4;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12;

    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 2) {
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
        /*if (MainActivity.item == 2) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>setUserVisibleHint>>>" + isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 2) {
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

        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2 = findViewByID_My(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        tv_mymore = findViewByID_My(R.id.tv_mymore);
        tv_mymore.setOnClickListener(this);

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
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        request(params);
    }

    private void request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
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
            public void onResponse(Fragment3Model response) {
                hideProgress();
                showContentPage();
                model = response;
                textView1.setText(response.getTotalNum());
                textView2.setText(response.getUpLineNum());
                textView3.setText(response.getOnLineNum());
                textView4.setText(response.getOffLineNum());

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

                list2 = response.getDeviceList();
                if (list2 != null && list2.size() > 0) {
                    mAdapter2 = new CommonAdapter<Fragment3Model.DeviceListBean>
                            (getActivity(), R.layout.item_fragment2_2, list2) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.DeviceListBean model, int position) {
                            holder.setText(R.id.tv_name, model.getStoreName());//标题
                            holder.setText(R.id.tv_shop, "SN:"+model.getHostName());
                            holder.setText(R.id.tv_num, model.getTotalRevenue());//money
                            holder.setText(R.id.tv_addr, model.getStoreAddress());

                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(model.getStoreImage())
//                                .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
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
                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("deviceName", model.getId());
                                    CommonUtil.gotoActivityWithData(getActivity(), DeviceDetailActivity.class, bundle, false);
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
                //总设备
//                CommonUtil.gotoActivity(getActivity(), PersonalSettingActivity.class);
                break;
            case R.id.linearLayout2:
                //上线设备
//                CommonUtil.gotoActivity(getActivity(), VerifiedActivity.class);
                break;
            case R.id.linearLayout3:
                //在线设备
//                CommonUtil.gotoActivity(getActivity(), MyPublishActivity.class);
                break;
            case R.id.linearLayout4:
                //离线设备
//                CommonUtil.gotoActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.tv_mymore:
                //我的设备
                CommonUtil.gotoActivity(getActivity(), MyDeviceListActivity.class);
                break;
            case R.id.linearLayout5:
                //申领设备
                CommonUtil.gotoActivity(getActivity(), SelectDeviceActivity.class);
                break;
            case R.id.linearLayout6:
                //安装设备
                bundle.putInt("type", 1);//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
                CommonUtil.gotoActivityWithData(getActivity(),AffairListActivity.class,bundle);
                break;
            case R.id.linearLayout7:
                //调试设备
//                CommonUtil.gotoActivity(getActivity(), DebugDeviceActivity.class);
                ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(this, config);
                break;
            case R.id.linearLayout8:
                //回收设备
                bundle.putInt("type", 4);//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
                CommonUtil.gotoActivityWithData(getActivity(),AffairListActivity.class,bundle);
                break;
            case R.id.linearLayout9:
                //设备换绑
                bundle.putInt("type", 5);//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
                CommonUtil.gotoActivityWithData(getActivity(),AffairListActivity.class,bundle);
                break;
            case R.id.linearLayout10:
                //设备报失-工单
                bundle.putInt("type", 2);
                CommonUtil.gotoActivityWithData(getActivity(), AddWorkListActivity.class, bundle);
                break;
            case R.id.linearLayout11:
                //设备故障-工单
                bundle.putInt("type", 0);
                CommonUtil.gotoActivityWithData(getActivity(), AddWorkListActivity.class, bundle);
                break;
            case R.id.linearLayout12:
                //设备定位
                CommonUtil.gotoActivity(getActivity(), DeviceListActivity_Position.class);

                break;

            case R.id.ll_tab1:
                //待安装
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //待回收
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //待换绑
                type = 3;
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
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                for (Fragment3Model.WaitingInstallListBean bean : model.getWaitingInstallList()) {
                    list1.add(new MyFragment1Model(bean.getId(), bean.getStoreName(), bean.getCreateTime()));
                }
                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                for (Fragment3Model.WaitingRecycleListBean bean : model.getWaitingRecycleList()) {
                    list1.add(new MyFragment1Model(bean.getId(), bean.getStoreName(), bean.getCreateTime()));
                }
                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                for (Fragment3Model.WaitingSwapListBean bean : model.getWaitingSwapList()) {
                    list1.add(new MyFragment1Model(bean.getId(), bean.getStoreName(), bean.getCreateTime()));
                }
                break;

        }
        mAdapter1.notifyDataSetChanged();
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        MyLogger.i(">>>>>>>>>>>>>>");
        if (requestCode == CaptureActivity.REQUEST_CODE_SCAN) {
            // 扫描二维码回传
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    //获取扫描结果
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                    //{"deviceName": "641708882ef84e09995d70440e12ebf9"}
                    MyLogger.i("扫码返回", result);
                    try {
                        JSONObject mJsonObject = new JSONObject(result);
                        String deviceName = mJsonObject.getString("deviceName");
                        bundle.putString("deviceName", deviceName);
                        CommonUtil.gotoActivityWithData(getActivity(), DebugDeviceActivity.class, bundle, false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        myToast("解析出错");
                    }

                    /*if (!result.equals("")) {
                        iv_scan.setVisibility(View.GONE);
                        tv_scan.setText("SN号:" + result);
                    } else {
                        iv_scan.setVisibility(View.VISIBLE);
                        tv_scan.setVisibility(View.GONE);
                    }*/
                }
            }
        }
    }
}
