package com.xiyang.xiyang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.liaoinstan.springview.widget.SpringView;
import com.lihang.ShadowLayout;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.AddBuyActivity;
import com.xiyang.xiyang.activity.AdjustJobActivity;
import com.xiyang.xiyang.activity.AdjustMarketActivity;
import com.xiyang.xiyang.activity.AdjustSuperiorActivity;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.activity.MyCityActivity;
import com.xiyang.xiyang.activity.MyDeviceListActivity;
import com.xiyang.xiyang.activity.MyShopListActivity;
import com.xiyang.xiyang.activity.MyStoreListActivity;
import com.xiyang.xiyang.activity.PersonnelActivity;
import com.xiyang.xiyang.activity.SelectLevelActivity;
import com.xiyang.xiyang.activity.StaffDetailActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.model.Fragment1Model_m;
import com.xiyang.xiyang.model.KeyValueModel_m;
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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 管理
 */

public class Fragment1_m extends BaseFragment {
    String type = "CM";
    private RecyclerView rv_tongji;
    List<KeyValueModel_m> list_tongji = new ArrayList<>();
    CommonAdapter<KeyValueModel_m> mAdapter_tongji;

    private RecyclerView recyclerView;
    List<SubordinateModel> list = new ArrayList<>();
    CommonAdapter<SubordinateModel> mAdapter;

    TextView textView1, textView8;
    ImageView imageView8;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8;

    ShadowLayout sl_tab;
    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;


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
                params.put("userId","");
                params.put("code",type);
                requestList(params);//获取下级员工
            }

            @Override
            public void onLoadmore() {

            }
        });
        rv_tongji = findViewByID_My(R.id.rv_tongji);
        rv_tongji.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout4 = findViewByID_My(R.id.linearLayout4);
        linearLayout5 = findViewByID_My(R.id.linearLayout5);
        linearLayout6 = findViewByID_My(R.id.linearLayout6);
        linearLayout7 = findViewByID_My(R.id.linearLayout7);
        linearLayout8 = findViewByID_My(R.id.linearLayout8);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
        linearLayout6.setOnClickListener(this);
        linearLayout7.setOnClickListener(this);
        linearLayout8.setOnClickListener(this);

        sl_tab = findViewByID_My(R.id.sl_tab);
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

        textView1 = findViewByID_My(R.id.textView1);
        textView8 = findViewByID_My(R.id.textView8);
        imageView8 = findViewByID_My(R.id.imageView8);

        switch (localUserInfo.getUserJob()) {
            case "RM":
                textView1.setText("我的城市");

                textView8.setText("人事记录");
                imageView8.setImageResource(R.mipmap.ic_fragment1_tab8_m);

                sl_tab.setVisibility(View.VISIBLE);
                ll_tab1.setVisibility(View.VISIBLE);
                ll_tab2.setVisibility(View.VISIBLE);
                ll_tab3.setVisibility(View.VISIBLE);
                type = "CM";

                break;
            case "CM":
                textView1.setText("我的市区");

                textView8.setText("申请采购");
                imageView8.setImageResource(R.mipmap.ic_fragment1_tab9_m);

                sl_tab.setVisibility(View.VISIBLE);
                ll_tab1.setVisibility(View.GONE);
                ll_tab2.setVisibility(View.VISIBLE);
                ll_tab3.setVisibility(View.VISIBLE);

                type = "BDM";
                break;
            case "BDM":
                textView1.setText("我的区域");

                textView8.setText("人事记录");
                imageView8.setImageResource(R.mipmap.ic_fragment1_tab8_m);

                sl_tab.setVisibility(View.GONE);
                type = "BD";

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
        changeUI();
    }

    private void request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Fragment1_m, params, headerMap, new CallBackUtil<Fragment1Model_m>() {
            @Override
            public Fragment1Model_m onParseResponse(Call call, Response response) {
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
            public void onResponse(Fragment1Model_m response) {
                hideProgress();

                /**
                 * 统计
                 */
                list_tongji.clear();
                switch (localUserInfo.getUserJob()) {
                    case "RM":
                        list_tongji.add(new KeyValueModel_m("总CM", response.getCmNumber(), "人"));
                        list_tongji.add(new KeyValueModel_m("总BDM", response.getBdmNumber(), "人"));
                        list_tongji.add(new KeyValueModel_m("总BD", response.getBdmNumber(), "人"));
                        list_tongji.add(new KeyValueModel_m("总省份", response.getAreaNumber(), ""));

                        list_tongji.add(new KeyValueModel_m("总商户", response.getMerchantNumber(), "个"));
                        list_tongji.add(new KeyValueModel_m("总门店", response.getStoreNumber(), "个"));
                        list_tongji.add(new KeyValueModel_m("总设备", response.getDeviceNumber(), "台"));
                        list_tongji.add(new KeyValueModel_m("总营收", "￥" + response.getMoney(), ""));

                        break;
                    case "CM":
                        list_tongji.add(new KeyValueModel_m("总BDM", response.getBdmNumber(), "人"));
                        list_tongji.add(new KeyValueModel_m("总BD", response.getBdNumber(), "人"));
                        list_tongji.add(new KeyValueModel_m("可用指标", response.getOrganName(), ""));
                        list_tongji.add(new KeyValueModel_m("总城市", response.getAreaNumber(), ""));

                        list_tongji.add(new KeyValueModel_m("总商户", response.getMerchantNumber(), "个"));
                        list_tongji.add(new KeyValueModel_m("总门店", response.getStoreNumber(), "个"));
                        list_tongji.add(new KeyValueModel_m("总设备", response.getDeviceNumber(), "台"));
                        list_tongji.add(new KeyValueModel_m("总营收", "￥" + response.getMoney(), ""));

                        break;
                    case "BDM":
                        list_tongji.add(new KeyValueModel_m("总商户", response.getMerchantNumber(), "个"));
                        list_tongji.add(new KeyValueModel_m("总门店", response.getStoreNumber(), "个"));
                        list_tongji.add(new KeyValueModel_m("总设备", response.getDeviceNumber(), "台"));
                        list_tongji.add(new KeyValueModel_m("总营收", "￥" + response.getMoney(), ""));

                        list_tongji.add(new KeyValueModel_m("总BD", response.getBdNumber(), "人"));
                        list_tongji.add(new KeyValueModel_m("总区域", response.getAreaNumber(), ""));
                        break;
                }

                mAdapter_tongji = new CommonAdapter<KeyValueModel_m>
                        (getActivity(), R.layout.item_fragment1_m_tongji, list_tongji) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel_m model, int position) {
                        holder.setText(R.id.tv1, model.getValue());
                        holder.setText(R.id.tv2, model.getKey());
                        holder.setText(R.id.tv3, model.getDanwei());
                    }
                };
                rv_tongji.setAdapter(mAdapter_tongji);

                MainActivity.isOver = true;
            }
        });
    }

    /**
     * 获取员工
     *
     * @param params
     */
    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.Subordinate, GsonUtils.toJson(params), headerMap, new CallBackUtil<List<SubordinateModel>>() {
            @Override
            public List<SubordinateModel> onParseResponse(Call call, Response response) {
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
            public void onResponse(List<SubordinateModel> response) {
                hideProgress();
                showContentPage();
                list = response;
                if (list.size() > 0) {
                    mAdapter = new CommonAdapter<SubordinateModel>
                            (getActivity(), R.layout.item_fragment1_m, list) {
                        @Override
                        protected void convert(ViewHolder holder, SubordinateModel model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(model.getAvatar())
                                    .fitCenter()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name, model.getName());

                            holder.setText(R.id.tv1, model.getStatisticInfo().getMerchantNumber());
                            holder.setText(R.id.tv2, model.getStatisticInfo().getStoreNumber());
                            holder.setText(R.id.tv3, model.getStatisticInfo().getDeviceNumber());
                            holder.setText(R.id.tv4, "￥" + model.getStatisticInfo().getMoney());

                            TextView tv_bdm = holder.getView(R.id.tv_bdm);
                            TextView tv_bd = holder.getView(R.id.tv_bd);
                            tv_bdm.setVisibility(View.VISIBLE);
                            tv_bd.setVisibility(View.VISIBLE);
                            switch (localUserInfo.getUserJob()) {
                                case "CM":
                                    tv_bdm.setText("BDM:" + model.getStatisticInfo().getBdmNumber());
                                    tv_bd.setText("BD:" + model.getStatisticInfo().getBdNumber());
                                    break;
                                case "BDM":
                                    tv_bdm.setVisibility(View.GONE);
                                    tv_bd.setText("BD:" + model.getStatisticInfo().getBdmNumber());
                                    break;
                                case "BD":
                                    tv_bdm.setVisibility(View.GONE);
                                    tv_bd.setVisibility(View.GONE);
                                    break;
                            }
                            //负责城市
                            FlowLayout flowLayout = holder.getView(R.id.flowLayout);
                            FlowLayoutAdapter<SubordinateModel.RegionsBean> flowLayoutAdapter = new FlowLayoutAdapter<SubordinateModel.RegionsBean>(model.getRegions()) {
                                @Override
                                public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position, SubordinateModel.RegionsBean bean) {
                                    holder.setText(R.id.tv, bean.getNameX());
                                }

                                @Override
                                public void onItemClick(int position, SubordinateModel.RegionsBean bean) {

                                }

                                @Override
                                public int getItemLayoutID(int position, SubordinateModel.RegionsBean bean) {
                                    return R.layout.item_flowlayout_yuanjiaobiankuang;
                                }
                            };

                            flowLayout.setAdapter(flowLayoutAdapter);

                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getOrganId());
                                    CommonUtil.gotoActivityWithData(getActivity(), StaffDetailActivity.class, bundle, false);
                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
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
                //我的城市
                CommonUtil.gotoActivity(getActivity(), MyCityActivity.class);
                break;
            case R.id.linearLayout2:
                //我的商户
                CommonUtil.gotoActivity(getActivity(), MyShopListActivity.class);
                break;
            case R.id.linearLayout3:
                //我的门店
                CommonUtil.gotoActivity(getActivity(), MyStoreListActivity.class);
                break;
            case R.id.linearLayout4:
                //我的设备
                CommonUtil.gotoActivity(getActivity(), MyDeviceListActivity.class);
                break;
            case R.id.linearLayout5:
                //调整上级
                if (localUserInfo.getUserJob().equals("BDM")) {
                    bundle.putString("job", "BD");
                    CommonUtil.gotoActivityWithData(getActivity(), AdjustSuperiorActivity.class, bundle);
                } else {
                    bundle.putInt("type", 1);
                    CommonUtil.gotoActivityWithData(getActivity(), SelectLevelActivity.class, bundle);
                }

                break;
            case R.id.linearLayout6:
                //调整市场
                if (localUserInfo.getUserJob().equals("BDM")) {
                    bundle.putString("job", "BD");
                    CommonUtil.gotoActivityWithData(getActivity(), AdjustMarketActivity.class, bundle);
                } else {
                    bundle.putInt("type", 2);
                    CommonUtil.gotoActivityWithData(getActivity(), SelectLevelActivity.class, bundle);
                }
                break;
            case R.id.linearLayout7:
                //调整岗位
                if (localUserInfo.getUserJob().equals("BDM")) {
//                    myToast("调整岗位需CM及以上");
                    bundle.putString("job", "BD");
                    CommonUtil.gotoActivityWithData(getActivity(), AdjustJobActivity.class, bundle);
                } else {
                    bundle.putInt("type", 3);
                    CommonUtil.gotoActivityWithData(getActivity(), SelectLevelActivity.class, bundle);
                }
                break;
            case R.id.linearLayout8:
                //人事记录
                if (localUserInfo.getUserJob().equals("CM"))
                    CommonUtil.gotoActivity(getActivity(), AddBuyActivity.class);//申请采购
                else
                    CommonUtil.gotoActivity(getActivity(), PersonnelActivity.class);
                break;

            case R.id.ll_tab1:
                //CM
                type = "CM";
                changeUI();
                break;
            case R.id.ll_tab2:
                //BDM
                type = "BDM";
                changeUI();
                break;
            case R.id.ll_tab3:
                //BD
                type = "BD";
                changeUI();
                break;

        }
    }

    private void changeUI() {
        switch (type) {
            case "CM":
                tv_tab1.setTextColor(getResources().getColor(R.color.green));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);

                break;
            case "BDM":
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.green));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);

                break;
            case "BD":
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.green));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);

                break;
        }
        Map<String, String> params = new HashMap<>();
        params.put("userId","");
        params.put("code",type);
        requestList(params);//获取下级员工
    }

    @Override
    protected void updateView() {

    }

}
