package com.xiyang.xiyang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.AddContractActivity;
import com.xiyang.xiyang.activity.AddStoreActivity;
import com.xiyang.xiyang.activity.ChangeTieDeviceActivity;
import com.xiyang.xiyang.activity.ContractDetailActivity;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.activity.MyStoreListActivity;
import com.xiyang.xiyang.activity.SelectVisitActivity;
import com.xiyang.xiyang.activity.StopStoreActivity;
import com.xiyang.xiyang.activity.StoreDetailActivity;
import com.xiyang.xiyang.activity.TransferStoreActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.model.Fragment2Model;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;
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
 * Created by fafukeji01 on 2016/1/6.
 * 门店
 */
public class Fragment2 extends BaseFragment {
    int type = 1;
    private RecyclerView recyclerView1, recyclerView2;
    List<Fragment2Model> list1 = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter1;
    List<Fragment2Model> list2 = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter2;
    TextView tv_mymore;

    TextView textView1, textView2, textView3, textView4;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12;

    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 1) {
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
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>setUserVisibleHint>>>" + isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 1) {
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
               /* page1 = 0;
                page2 = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page1 + "");
                params.put("u_token", localUserInfo.getToken());
                Request(params);*/
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
        for (int i = 0; i < 5; i++) {
            list1.add(new Fragment2Model());
            list2.add(new Fragment2Model());
        }
        mAdapter1 = new CommonAdapter<Fragment2Model>
                (getActivity(), R.layout.item_fragment1_1, list1) {
            @Override
            protected void convert(ViewHolder holder, Fragment2Model model, int position) {

//                        holder.setText(R.id.tv1, model.getTitle());
//                        holder.setText(R.id.tv2, model.getProvince() + model.getCity() + model.getDistrict());
                holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
//                    bundle.putString("id",model.getId());
                        CommonUtil.gotoActivityWithData(getActivity(), ContractDetailActivity.class, bundle, false);
                    }
                });

            }
        };
        recyclerView1.setAdapter(mAdapter1);

        if (list2.size() > 0) {
            mAdapter2 = new CommonAdapter<Fragment2Model>
                    (getActivity(), R.layout.item_fragment2_2, list2) {
                @Override
                protected void convert(ViewHolder holder, Fragment2Model model, int position) {
                            /*ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(OkHttpClientManager.IMGHOST + model.getCover())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
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
                            CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
                        }
                    });

                }
            };
            recyclerView2.setAdapter(mAdapter2);
        } else {
            showEmptyPage();
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
       /* this.showLoadingPage();
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        Request(params);*/
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2, params, headerMap, new CallBackUtil<Fragment2Model>() {
            @Override
            public Fragment2Model onParseResponse(Call call, Response response) {
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
            public void onResponse(Fragment2Model response) {
                hideProgress();

                mAdapter1 = new CommonAdapter<Fragment2Model>
                        (getActivity(), R.layout.item_fragment1_1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment2Model model, int position) {

//                        holder.setText(R.id.tv1, model.getTitle());
//                        holder.setText(R.id.tv2, model.getProvince() + model.getCity() + model.getDistrict());
                    }
                };
                recyclerView1.setAdapter(mAdapter1);
//                list2 = response.getCooperation_shop_list();
                if (list2.size() > 0) {
                    mAdapter2 = new CommonAdapter<Fragment2Model>
                            (getActivity(), R.layout.item_fragment2_2, list2) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment2Model model, int position) {
                            /*ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(URLs.IMGHOST + model.getCover())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
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
                //总门店
//                CommonUtil.gotoActivity(getActivity(), PersonalSettingActivity.class);
                break;
            case R.id.linearLayout2:
                //待拜访
//                CommonUtil.gotoActivity(getActivity(), VerifiedActivity.class);
                break;
            case R.id.linearLayout3:
                //待安装
//                CommonUtil.gotoActivity(getActivity(), MyPublishActivity.class);
                break;
            case R.id.linearLayout4:
                //总营收
//                CommonUtil.gotoActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.tv_mymore:
                //我的门店
                CommonUtil.gotoActivity(getActivity(), MyStoreListActivity.class);
                break;
            case R.id.linearLayout5:
                //添加门店
                CommonUtil.gotoActivity(getActivity(), AddStoreActivity.class);
                break;
            case R.id.linearLayout6:
                //拜访门店
                CommonUtil.gotoActivity(getActivity(), SelectVisitActivity.class);
                break;
            case R.id.linearLayout7:
                //调价申请
//                CommonUtil.gotoActivity(getActivity(), ChangeContractActivity.class);
                bundle.putInt("item_hetong", 7);
                CommonUtil.gotoActivityWithData(getActivity(), AddContractActivity.class, bundle);
                break;
            case R.id.linearLayout8:
                //划转门店
                CommonUtil.gotoActivity(getActivity(), TransferStoreActivity.class);
                break;
            case R.id.linearLayout9:
                //设备变更
                CommonUtil.gotoActivity(getActivity(), ChangeTieDeviceActivity.class);
                break;
            case R.id.linearLayout10:
                //门店纠错
//                CommonUtil.gotoActivity(getActivity(), MyContractActivity.class);
                break;
            case R.id.linearLayout11:
                //关闭门店
                CommonUtil.gotoActivity(getActivity(), StopStoreActivity.class);
                break;
            case R.id.linearLayout12:
                //
                break;

            case R.id.ll_tab1:
                //待拜访
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //待划转
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //待调价
                type = 3;
                changeUI();
                break;

        }
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

    @Override
    protected void updateView() {

    }

}
