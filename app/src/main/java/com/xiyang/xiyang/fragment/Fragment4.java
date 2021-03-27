package com.xiyang.xiyang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.base.BaseFragment;
import com.xiyang.xiyang.model.Fragment4Model_P;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 数据
 */
public class Fragment4 extends BaseFragment {
    /**
     * 板块1
     */
    int type1 = 1;
    TextView tv_tab1_1, tv_tab1_2, tv_tab1_3;
    LinearLayout ll_tab1_1, ll_tab1_2, ll_tab1_3;
    View view1_1, view1_2, view1_3;
    RecyclerView rv1_t, rv1_1, rv1_2;
    int item1_t = 0, item1_1 = 0;
    CommonAdapter<String> mAdapter1_t;
    List<Fragment4Model_P> list1_1 = new ArrayList<>();
    CommonAdapter<Fragment4Model_P> mAdapter1_1;
    List<Fragment4Model_P> list1_2 = new ArrayList<>();
    CommonAdapter<Fragment4Model_P> mAdapter1_2;
    /**
     * 板块2
     */
    int item2_t = 0;
    RecyclerView rv2_t, rv2_1;
    CommonAdapter<String> mAdapter2_t;
    List<Fragment4Model_P> list2_1 = new ArrayList<>();
    CommonAdapter<Fragment4Model_P> mAdapter2_1;

    /**
     *板块3
     */
    int item3_t = 0;
    RecyclerView rv3_t;
    CommonAdapter<String> mAdapter3_t;

    /**
     * 板块4
     */
    int item4_t = 0,item4_1 = 0;
    RecyclerView rv4_t, rv4_1;
    CommonAdapter<String> mAdapter4_t;
    List<Fragment4Model_P> list4_1 = new ArrayList<>();
    CommonAdapter<Fragment4Model_P> mAdapter4_1;
    /**
     * 板块5
     */
    int item5_t = 0;
    RecyclerView rv5_t, rv5_1;
    CommonAdapter<String> mAdapter5_t;
    List<Fragment4Model_P> list5_1 = new ArrayList<>();
    CommonAdapter<Fragment4Model_P> mAdapter5_1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
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
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>setUserVisibleHint>>>" + isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 3) {
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
                request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });

        /**
         * 板块1
         */
        ll_tab1_1 = findViewByID_My(R.id.ll_tab1_1);
        ll_tab1_2 = findViewByID_My(R.id.ll_tab1_2);
        ll_tab1_3 = findViewByID_My(R.id.ll_tab1_3);
        ll_tab1_1.setOnClickListener(this);
        ll_tab1_2.setOnClickListener(this);
        ll_tab1_3.setOnClickListener(this);
        tv_tab1_1 = findViewByID_My(R.id.tv_tab1_1);
        tv_tab1_2 = findViewByID_My(R.id.tv_tab1_2);
        tv_tab1_3 = findViewByID_My(R.id.tv_tab1_3);
        view1_1 = findViewByID_My(R.id.view1_1);
        view1_2 = findViewByID_My(R.id.view1_2);
        view1_3 = findViewByID_My(R.id.view1_3);
        rv1_t = findViewByID_My(R.id.rv1_t);
        rv1_t.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv1_1 = findViewByID_My(R.id.rv1_1);
        rv1_1.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        rv1_2 = findViewByID_My(R.id.rv1_2);
        rv1_2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        /**
         * 板块2
         */
        rv2_t = findViewByID_My(R.id.rv2_t);
        rv2_t.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        rv2_1 = findViewByID_My(R.id.rv2_1);
        rv2_1.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        /**
         * 板块3
         */
        rv3_t = findViewByID_My(R.id.rv3_t);
        rv3_t.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        /**
         * 板块4
         */
        rv4_t = findViewByID_My(R.id.rv4_t);
        rv4_t.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv4_1 = findViewByID_My(R.id.rv4_1);
        rv4_1.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        /**
         * 板块5
         */
        rv5_t = findViewByID_My(R.id.rv5_t);
        rv5_t.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv5_1 = findViewByID_My(R.id.rv5_1);
        rv5_1.setLayoutManager(new GridLayoutManager(getActivity(), 4));
    }

    @Override
    protected void initData() {
        requestServer();
        /**
         * 板块1
         */
        List<String> h1 = new ArrayList<>();
        h1.add("昨日");
        h1.add("今日");
        h1.add("本月");
        mAdapter1_t = new CommonAdapter<String>
                (getActivity(), R.layout.item_fragment4_t, h1) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                holder.setText(R.id.tv1, model);
                MyLogger.i(">>>>>>"+item1_t);
                View v1 = holder.getView(R.id.v1);
                if (item1_t == position) {
                    v1.setVisibility(View.VISIBLE);
                } else {
                    v1.setVisibility(View.INVISIBLE);
                }
            }
        };
        mAdapter1_t.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                MyLogger.i(">>>>>>"+i);
                item1_t = i;
                mAdapter1_t.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv1_t.setAdapter(mAdapter1_t);

        list1_1.clear();
        List<String> t1 = new ArrayList<>();
        t1.add("总营收");
        t1.add("总商户");
        t1.add("总门店");
        t1.add("总设备");
        List<String> m1 = new ArrayList<>();
        m1.add("¥10000.00");
        m1.add("100");
        m1.add("100");
        m1.add("100");
        List<String> n1 = new ArrayList<>();
        n1.add("12.3");
        n1.add("1.23");
        n1.add("123");
        n1.add("12.3");
        List<String> z1 = new ArrayList<>();
        z1.add("1");
        z1.add("0");
        z1.add("1");
        z1.add("0");
        for (int i = 0; i < t1.size(); i++) {
            list1_1.add(new Fragment4Model_P(t1.get(i), m1.get(i), n1.get(i), z1.get(i)));
        }

        mAdapter1_1 = new CommonAdapter<Fragment4Model_P>
                (getActivity(), R.layout.item_fragment4_1, list1_1) {
            @Override
            protected void convert(ViewHolder holder, Fragment4Model_P model, int position) {
                LinearLayout ll = holder.getView(R.id.ll);
                if (item1_1 == position) {
                    ll.setBackgroundResource(R.color.qianblue);
                } else {
                    ll.setBackgroundResource(R.color.white);
                }
                holder.setText(R.id.tv1, model.getTitle());
                holder.setText(R.id.tv2, model.getMoney());
                TextView tv3 = holder.getView(R.id.tv3);
                if (model.getZd().equals("1")) {
                    tv3.setText("↑" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.red));
                } else {
                    tv3.setText("↓" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.green));
                }
            }
        };
        mAdapter1_1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                item1_1 = i;
                mAdapter1_1.notifyDataSetChanged();
                list1_2.clear();
                switch (item1_1) {
                    case 0:
                        List<String> t1_0 = new ArrayList<>();
                        t1_0.add("总订单量");
                        t1_0.add("总时长");
                        t1_0.add("有效设备");
                        List<String> m1_0 = new ArrayList<>();
                        m1_0.add("¥10000.00");
                        m1_0.add("100");
                        m1_0.add("100");
                        List<String> n1_0 = new ArrayList<>();
                        n1_0.add("12.3");
                        n1_0.add("1.23");
                        n1_0.add("123");
                        List<String> z1_0 = new ArrayList<>();
                        z1_0.add("1");
                        z1_0.add("0");
                        z1_0.add("1");
                        for (int j = 0; j < t1_0.size(); j++) {
                            list1_2.add(new Fragment4Model_P(t1_0.get(j), m1_0.get(j), n1_0.get(j), z1_0.get(j)));
                        }
                        break;
                    case 1:
                        List<String> t1_1 = new ArrayList<>();
                        t1_1.add("新签约");
                        t1_1.add("待签约");
                        t1_1.add("签约数");
                        t1_1.add("代加门店");
                        List<String> m1_1 = new ArrayList<>();
                        m1_1.add("¥10000.00");
                        m1_1.add("100");
                        m1_1.add("100");
                        m1_1.add("100");
                        List<String> n1_1 = new ArrayList<>();
                        n1_1.add("12.3");
                        n1_1.add("1.23");
                        n1_1.add("123");
                        n1_1.add("12.3");
                        List<String> z1_1 = new ArrayList<>();
                        z1_1.add("1");
                        z1_1.add("0");
                        z1_1.add("1");
                        z1_1.add("0");
                        for (int j = 0; j < t1_1.size(); j++) {
                            list1_2.add(new Fragment4Model_P(t1_1.get(j), m1_1.get(j), n1_1.get(j), z1_1.get(j)));
                        }
                        break;
                    case 2:
                        List<String> t1_2 = new ArrayList<>();
                        t1_2.add("待装设备");
                        t1_2.add("新签门店");
                        t1_2.add("待拜访");
                        t1_2.add("BAT门店");
                        t1_2.add("亏损门店");
                        List<String> m1_2 = new ArrayList<>();
                        m1_2.add("¥10000.00");
                        m1_2.add("100");
                        m1_2.add("100");
                        m1_2.add("100");
                        m1_2.add("100");
                        List<String> n1_2 = new ArrayList<>();
                        n1_2.add("12.3");
                        n1_2.add("1.23");
                        n1_2.add("123");
                        n1_2.add("12.3");
                        n1_2.add("1.23");
                        List<String> z1_2 = new ArrayList<>();
                        z1_2.add("1");
                        z1_2.add("0");
                        z1_2.add("1");
                        z1_2.add("0");
                        z1_2.add("1");
                        for (int j = 0; j < t1_2.size(); j++) {
                            list1_2.add(new Fragment4Model_P(t1_2.get(j), m1_2.get(j), n1_2.get(j), z1_2.get(j)));
                        }
                        break;
                    case 3:
                        List<String> t1_3 = new ArrayList<>();
                        t1_3.add("上线设备");
                        t1_3.add("在线设备");
                        t1_3.add("离线设备");
                        t1_3.add("连续不在线设备");
                        t1_3.add("移位设备");
                        t1_3.add("长期移位设备");
                        List<String> m1_3 = new ArrayList<>();
                        m1_3.add("¥10000.00");
                        m1_3.add("100");
                        m1_3.add("100");
                        m1_3.add("100");
                        m1_3.add("100");
                        m1_3.add("100");
                        List<String> n1_3 = new ArrayList<>();
                        n1_3.add("12.3");
                        n1_3.add("1.23");
                        n1_3.add("123");
                        n1_3.add("12.3");
                        n1_3.add("1.23");
                        n1_3.add("123");
                        List<String> z1_3 = new ArrayList<>();
                        z1_3.add("1");
                        z1_3.add("0");
                        z1_3.add("1");
                        z1_3.add("0");
                        z1_3.add("1");
                        z1_3.add("1");
                        for (int j = 0; j < t1_3.size(); j++) {
                            list1_2.add(new Fragment4Model_P(t1_3.get(j), m1_3.get(j), n1_3.get(j), z1_3.get(j)));
                        }
                        break;
                }
                mAdapter1_2.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv1_1.setAdapter(mAdapter1_1);
        list1_2.clear();
        switch (item1_1) {
            case 0:
                List<String> t1_0 = new ArrayList<>();
                t1_0.add("总订单量");
                t1_0.add("总时长");
                t1_0.add("有效设备");
                List<String> m1_0 = new ArrayList<>();
                m1_0.add("¥10000.00");
                m1_0.add("100");
                m1_0.add("100");
                List<String> n1_0 = new ArrayList<>();
                n1_0.add("12.3");
                n1_0.add("1.23");
                n1_0.add("123");
                List<String> z1_0 = new ArrayList<>();
                z1_0.add("1");
                z1_0.add("0");
                z1_0.add("1");
                for (int j = 0; j < t1_0.size(); j++) {
                    list1_2.add(new Fragment4Model_P(t1_0.get(j), m1_0.get(j), n1_0.get(j), z1_0.get(j)));
                }
                break;
            case 1:
                List<String> t1_1 = new ArrayList<>();
                t1_1.add("新签约");
                t1_1.add("待签约");
                t1_1.add("签约数");
                t1_1.add("代加门店");
                List<String> m1_1 = new ArrayList<>();
                m1_1.add("¥10000.00");
                m1_1.add("100");
                m1_1.add("100");
                m1_1.add("100");
                List<String> n1_1 = new ArrayList<>();
                n1_1.add("12.3");
                n1_1.add("1.23");
                n1_1.add("123");
                n1_1.add("12.3");
                List<String> z1_1 = new ArrayList<>();
                z1_1.add("1");
                z1_1.add("0");
                z1_1.add("1");
                z1_1.add("0");
                for (int j = 0; j < t1_1.size(); j++) {
                    list1_2.add(new Fragment4Model_P(t1_1.get(j), m1_1.get(j), n1_1.get(j), z1_1.get(j)));
                }
                break;
            case 2:
                List<String> t1_2 = new ArrayList<>();
                t1_2.add("待装设备");
                t1_2.add("新签门店");
                t1_2.add("待拜访");
                t1_2.add("BAT门店");
                t1_2.add("亏损门店");
                List<String> m1_2 = new ArrayList<>();
                m1_2.add("¥10000.00");
                m1_2.add("100");
                m1_2.add("100");
                m1_2.add("100");
                m1_2.add("100");
                List<String> n1_2 = new ArrayList<>();
                n1_2.add("12.3");
                n1_2.add("1.23");
                n1_2.add("123");
                n1_2.add("12.3");
                n1_2.add("1.23");
                List<String> z1_2 = new ArrayList<>();
                z1_2.add("1");
                z1_2.add("0");
                z1_2.add("1");
                z1_2.add("0");
                z1_2.add("1");
                for (int j = 0; j < t1_2.size(); j++) {
                    list1_2.add(new Fragment4Model_P(t1_2.get(j), m1_2.get(j), n1_2.get(j), z1_2.get(j)));
                }
                break;
            case 3:
                List<String> t1_3 = new ArrayList<>();
                t1_3.add("上线设备");
                t1_3.add("在线设备");
                t1_3.add("离线设备");
                t1_3.add("连续不在线设备");
                t1_3.add("移位设备");
                t1_3.add("长期移位设备");
                List<String> m1_3 = new ArrayList<>();
                m1_3.add("¥10000.00");
                m1_3.add("100");
                m1_3.add("100");
                m1_3.add("100");
                m1_3.add("100");
                m1_3.add("100");
                List<String> n1_3 = new ArrayList<>();
                n1_3.add("12.3");
                n1_3.add("1.23");
                n1_3.add("123");
                n1_3.add("12.3");
                n1_3.add("1.23");
                n1_3.add("123");
                List<String> z1_3 = new ArrayList<>();
                z1_3.add("1");
                z1_3.add("0");
                z1_3.add("1");
                z1_3.add("0");
                z1_3.add("1");
                z1_3.add("1");
                for (int j = 0; j < t1_3.size(); j++) {
                    list1_2.add(new Fragment4Model_P(t1_3.get(j), m1_3.get(j), n1_3.get(j), z1_3.get(j)));
                }
                break;
        }
        mAdapter1_2 = new CommonAdapter<Fragment4Model_P>
                (getActivity(), R.layout.item_fragment4_2, list1_2) {
            @Override
            protected void convert(ViewHolder holder, Fragment4Model_P model, int position) {
                holder.setText(R.id.tv1, model.getTitle());
                holder.setText(R.id.tv2, model.getMoney());
                TextView tv3 = holder.getView(R.id.tv3);
                if (model.getZd().equals("1")) {
                    tv3.setText("↑" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.red));
                } else {
                    tv3.setText("↓" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.green));
                }
            }
        };
        rv1_2.setAdapter(mAdapter1_2);

        /**
         * 板块2
         */
        List<String> h2 = new ArrayList<>();
        h2.add("昨日");
        h2.add("今日");
        h2.add("七天");
        h2.add("30天");
        h2.add("上月");
        mAdapter2_t = new CommonAdapter<String>
                (getActivity(), R.layout.item_fragment4_t, h2) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                holder.setText(R.id.tv1, model);
                View v1 = holder.getView(R.id.v1);
                if (item2_t == position) {
                    v1.setVisibility(View.VISIBLE);
                } else {
                    v1.setVisibility(View.INVISIBLE);
                }
            }
        };
        mAdapter2_t.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                item2_t = i;
                mAdapter2_t.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv2_t.setAdapter(mAdapter2_t);

        list2_1.clear();
        List<String> t2 = new ArrayList<>();
        t2.add("总营收");
        t2.add("日均营收");
        t2.add("单机日均");
        t2.add("上线设备");
        t2.add("总订单量");
        t2.add("日均订单");
        t2.add("单机订单");
        t2.add("在线设备");
        t2.add("总时长");
        t2.add("日均时长");
        t2.add("单机时长");
        List<String> m2 = new ArrayList<>();
        m2.add("¥10000.00");
        m2.add("100");
        m2.add("100");
        m2.add("100");
        m2.add("100");
        m2.add("100");
        m2.add("100");
        m2.add("100");
        m2.add("100");
        m2.add("100");
        m2.add("100");
        List<String> n2 = new ArrayList<>();
        n2.add("12.3");
        n2.add("1.23");
        n2.add("123");
        n2.add("12.3");
        n2.add("12.3");
        n2.add("12.3");
        n2.add("12.3");
        n2.add("12.3");
        n2.add("12.3");
        n2.add("12.3");
        n2.add("12.3");
        List<String> z2 = new ArrayList<>();
        z2.add("1");
        z2.add("0");
        z2.add("1");
        z2.add("0");
        z2.add("1");
        z2.add("1");
        z2.add("1");
        z2.add("1");
        z2.add("1");
        z2.add("1");
        z2.add("1");
        for (int i = 0; i < t2.size(); i++) {
            list2_1.add(new Fragment4Model_P(t2.get(i), m2.get(i), n2.get(i), z2.get(i)));
        }

        mAdapter2_1 = new CommonAdapter<Fragment4Model_P>
                (getActivity(), R.layout.item_fragment4_1, list2_1) {
            @Override
            protected void convert(ViewHolder holder, Fragment4Model_P model, int position) {
                /*LinearLayout ll = holder.getView(R.id.ll);
                if (item1_1 == position) {
                    ll.setBackgroundResource(R.color.qianblue);
                } else {
                    ll.setBackgroundResource(R.color.white);
                }*/
                holder.setText(R.id.tv1, model.getTitle());
                holder.setText(R.id.tv2, model.getMoney());
                TextView tv3 = holder.getView(R.id.tv3);
                if (model.getZd().equals("1")) {
                    tv3.setText("↑" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.red));
                } else {
                    tv3.setText("↓" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.green));
                }
            }
        };
        rv2_1.setAdapter(mAdapter2_1);

        /**
         * 板块3
         */
        List<String> h3 = new ArrayList<>();
        h3.add("营收趋势");
        h3.add("单机日均");
        h3.add("单机时长");
        mAdapter3_t = new CommonAdapter<String>
                (getActivity(), R.layout.item_fragment4_t, h3) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                holder.setText(R.id.tv1, model);
                View v1 = holder.getView(R.id.v1);
                if (item3_t == position) {
                    v1.setVisibility(View.VISIBLE);
                } else {
                    v1.setVisibility(View.INVISIBLE);
                }
            }
        };
        mAdapter3_t.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                MyLogger.i(">>>>>>"+i);
                item3_t = i;
                mAdapter3_t.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv3_t.setAdapter(mAdapter3_t);

        /**
         * 板块4
         */
        List<String> h4 = new ArrayList<>();
        h4.add("本月");
        h4.add("上月");
        mAdapter4_t = new CommonAdapter<String>
                (getActivity(), R.layout.item_fragment4_t, h4) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                holder.setText(R.id.tv1, model);
                View v1 = holder.getView(R.id.v1);
                if (item4_t == position) {
                    v1.setVisibility(View.VISIBLE);
                } else {
                    v1.setVisibility(View.INVISIBLE);
                }
            }
        };
        mAdapter4_t.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                item4_t = i;
                mAdapter4_t.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv4_t.setAdapter(mAdapter4_t);

        list4_1.clear();
        List<String> t4 = new ArrayList<>();
        t4.add("新签门店利润");
        t4.add("存量门店毛利润");
        t4.add("总毛利润");
        t4.add("新签BAT门店");
        List<String> m4 = new ArrayList<>();
        m4.add("¥10000.00");
        m4.add("100");
        m4.add("100");
        m4.add("100");
        List<String> n4 = new ArrayList<>();
        n4.add("12.3");
        n4.add("1.23");
        n4.add("123");
        n4.add("12.3");
        List<String> z4 = new ArrayList<>();
        z4.add("1");
        z4.add("0");
        z4.add("1");
        z4.add("0");
        for (int i = 0; i < t4.size(); i++) {
            list4_1.add(new Fragment4Model_P(t4.get(i), m4.get(i), n4.get(i), z4.get(i)));
        }

        mAdapter4_1 = new CommonAdapter<Fragment4Model_P>
                (getActivity(), R.layout.item_fragment4_3, list4_1) {
            @Override
            protected void convert(ViewHolder holder, Fragment4Model_P model, int position) {
                View v1 = holder.getView(R.id.v1);
                if (item4_1 == position) {
                    v1.setVisibility(View.VISIBLE);
                } else {
                    v1.setVisibility(View.INVISIBLE);
                }
                holder.setText(R.id.tv1, model.getTitle());
                holder.setText(R.id.tv2, model.getMoney());
                TextView tv3 = holder.getView(R.id.tv3);
                if (model.getZd().equals("1")) {
                    tv3.setText("↑" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.red));
                } else {
                    tv3.setText("↓" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.green));
                }
            }
        };
        mAdapter4_1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                item4_1 = i;
                mAdapter4_1.notifyDataSetChanged();

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv4_1.setAdapter(mAdapter4_1);


        /**
         * 板块5
         */
        List<String> h5 = new ArrayList<>();
        h5.add("本月");
        h5.add("上月");
        mAdapter5_t = new CommonAdapter<String>
                (getActivity(), R.layout.item_fragment4_t, h5) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                holder.setText(R.id.tv1, model);
                View v1 = holder.getView(R.id.v1);
                if (item5_t == position) {
                    v1.setVisibility(View.VISIBLE);
                } else {
                    v1.setVisibility(View.INVISIBLE);
                }
            }
        };
        mAdapter5_t.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                item5_t = i;
                mAdapter5_t.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv5_t.setAdapter(mAdapter5_t);

        list5_1.clear();
        List<String> t5 = new ArrayList<>();
        t5.add("陌拜门店");
        t5.add("新签门店");
        t5.add("新签绑柜门店");
        t5.add("新签BAT门店");
        t5.add("当日新签门店");
        List<String> m5 = new ArrayList<>();
        m5.add("¥10000.00");
        m5.add("100");
        m5.add("100");
        m5.add("100");
        m5.add("100");
        List<String> n5 = new ArrayList<>();
        n5.add("12.3");
        n5.add("1.23");
        n5.add("123");
        n5.add("12.3");
        n5.add("12.3");
        List<String> z5 = new ArrayList<>();
        z5.add("1");
        z5.add("0");
        z5.add("1");
        z5.add("0");
        z5.add("1");
        for (int i = 0; i < t5.size(); i++) {
            list5_1.add(new Fragment4Model_P(t5.get(i), m5.get(i), n5.get(i), z5.get(i)));
        }

        mAdapter5_1 = new CommonAdapter<Fragment4Model_P>
                (getActivity(), R.layout.item_fragment4_1, list5_1) {
            @Override
            protected void convert(ViewHolder holder, Fragment4Model_P model, int position) {
                /*LinearLayout ll = holder.getView(R.id.ll);
                if (item1_1 == position) {
                    ll.setBackgroundResource(R.color.qianblue);
                } else {
                    ll.setBackgroundResource(R.color.white);
                }*/
                holder.setText(R.id.tv1, model.getTitle());
                holder.setText(R.id.tv2, model.getMoney());
                TextView tv3 = holder.getView(R.id.tv3);
                if (model.getZd().equals("1")) {
                    tv3.setText("↑" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.red));
                } else {
                    tv3.setText("↓" + model.getNum() + "%");
                    tv3.setTextColor(getResources().getColor(R.color.green));
                }
            }
        };
        rv5_1.setAdapter(mAdapter5_1);

    }

    private void request(Map<String, String> params) {
        /*OkhttpUtil.okHttpPost(URLs.Fragment4, params, headerMap, new CallBackUtil<Fragment4Model>() {
            @Override
            public Fragment4Model onParseResponse(Call call, Response response) {
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
            public void onResponse(Fragment4Model response) {
                hideProgress();
                MainActivity.isOver = true;
            }
        });*/
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        /*showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        request(params);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 板块1
             */
            case R.id.ll_tab1_1:
                //昨日
                type1 = 1;
                changeUI1();
                break;
            case R.id.ll_tab1_2:
                //待审核
                type1 = 2;
                changeUI1();
                break;
            case R.id.ll_tab1_3:
                //已驳回
                type1 = 3;
                changeUI1();
                break;
        }
    }

    /**
     * 板块1
     */
    private void changeUI1() {
        switch (type1) {
            case 1:
//                tv_tab1_1.setTextColor(getResources().getColor(R.color.black1));
//                tv_tab1_2.setTextColor(getResources().getColor(R.color.black3));
//                tv_tab1_3.setTextColor(getResources().getColor(R.color.black3));
                view1_1.setVisibility(View.VISIBLE);
                view1_2.setVisibility(View.INVISIBLE);
                view1_3.setVisibility(View.INVISIBLE);
                /*if (list1.size() > 0) {
                    showContentPage();
                    recyclerView1.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
                } else {
                    showEmptyPage();
                }*/
                break;
            case 2:
//                tv_tab1_1.setTextColor(getResources().getColor(R.color.black3));
//                tv_tab1_2.setTextColor(getResources().getColor(R.color.black1));
//                tv_tab1_3.setTextColor(getResources().getColor(R.color.black3));
                view1_1.setVisibility(View.INVISIBLE);
                view1_2.setVisibility(View.VISIBLE);
                view1_3.setVisibility(View.INVISIBLE);

                break;
            case 3:
//                tv_tab1_1.setTextColor(getResources().getColor(R.color.black3));
//                tv_tab1_2.setTextColor(getResources().getColor(R.color.black3));
//                tv_tab1_3.setTextColor(getResources().getColor(R.color.black1));
                view1_1.setVisibility(View.INVISIBLE);
                view1_2.setVisibility(View.INVISIBLE);
                view1_3.setVisibility(View.VISIBLE);

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
