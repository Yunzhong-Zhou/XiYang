package com.xiyang.xiyang.fragment;

import android.graphics.drawable.Drawable;
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
import com.xiyang.xiyang.activity.ApproveDetailActivity;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.model.Fragment3Model_m;
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
 * 设备
 */
public class Fragment3_m extends BaseFragment {
    int item = 1;
    private RecyclerView recyclerView;
    List<Fragment3Model_m.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model_m.ListBean> mAdapter;

    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;

    int page = 1;
    String type = "", status = "", title = "", startTime = "", endTIme = "";

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
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("count", "10");
                params.put("status", status);//处理结果 1-已提交，2-进行中，3-完成
                params.put("title", title);//搜索说明
                params.put("type", type);//1商户2门店3合同4设备
                params.put("startTime", startTime);
                params.put("endTIme", endTIme);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("count", "10");
                params.put("status", status);//处理结果 1-已提交，2-进行中，3-完成
                params.put("title", title);//搜索说明
                params.put("type", type);//1商户2门店3合同4设备
                params.put("startTime", startTime);
                params.put("endTIme", endTIme);
                requestListMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        page = 1;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("count", "10");
        params.put("status", status);//处理结果 1-已提交，2-进行中，3-完成
        params.put("title", title);//搜索说明
        params.put("type", type);//1商户2门店3合同4设备
        params.put("startTime", startTime);
        params.put("endTIme", endTIme);
        requestList(params);
    }

    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Fragment3_m, params, headerMap, new CallBackUtil<Fragment3Model_m>() {
            @Override
            public Fragment3Model_m onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment3Model_m response) {
                showContentPage();
                hideProgress();
                list = response.getList();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<Fragment3Model_m.ListBean>
                            (getActivity(), R.layout.item_fragment3_m, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model_m.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(model.getImage())
//                                .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name, model.getName());//标题
                            holder.setText(R.id.tv_shop, "《" + model.getTypeTite() + "》");
                            holder.setText(R.id.tv_time, model.getCreatedAt());
                            TextView tv_type = holder.getView(R.id.tv_type);
                            tv_type.setText(model.getStatusTitle());
                            switch (model.getStatus()) {
                                case "0":
                                    //已提交
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    break;
                                case "1":
                                    //已通过
                                    tv_type.setTextColor(getResources().getColor(R.color.green));
                                    break;
                                case "2":
                                    //失败
                                    tv_type.setTextColor(getResources().getColor(R.color.red));
                                    break;
                                default:
                                    break;

                            }
                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    CommonUtil.gotoActivityWithData(getActivity(), ApproveDetailActivity.class, bundle, false);
                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                }
                MainActivity.isOver = true;
            }
        });

    }

    private void requestListMore(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Fragment3_m, params, headerMap, new CallBackUtil<Fragment3Model_m>() {
            @Override
            public Fragment3Model_m onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                showErrorPage();
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment3Model_m response) {
//                showContentPage();
                hideProgress();
                List<Fragment3Model_m.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.ll_tab1:
                //待安装
                item = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //待回收
                item = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //待换绑
                item = 3;
                changeUI();
                break;
        }
    }

    private void changeUI() {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.ic_down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (item) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.white));
                tv_tab2.setTextColor(getResources().getColor(R.color.white));
                tv_tab3.setTextColor(getResources().getColor(R.color.white));
//                tv_tab1.setCompoundDrawables(null, null, drawable1, null);
//                tv_tab2.setCompoundDrawables(null, null, drawable2, null);
//                tv_tab3.setCompoundDrawables(null, null, drawable2, null);

//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                /*if (list1.size() > 0) {
                    showContentPage();
                    recyclerView1.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
                } else {
                    showEmptyPage();
                }*/
                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.white));
                tv_tab2.setTextColor(getResources().getColor(R.color.white));
                tv_tab3.setTextColor(getResources().getColor(R.color.white));
                tv_tab1.setCompoundDrawables(null, null, drawable2, null);
                tv_tab2.setCompoundDrawables(null, null, drawable1, null);
                tv_tab3.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.white));
                tv_tab2.setTextColor(getResources().getColor(R.color.white));
                tv_tab3.setTextColor(getResources().getColor(R.color.white));
                tv_tab1.setCompoundDrawables(null, null, drawable2, null);
                tv_tab2.setCompoundDrawables(null, null, drawable2, null);
                tv_tab3.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.INVISIBLE);
//                view3.setVisibility(View.VISIBLE);

                break;

        }
    }

    @Override
    protected void updateView() {

    }
}
