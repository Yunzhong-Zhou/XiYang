package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.MyIncomeListModel;
import com.xiyang.xiyang.model.MyIncomeModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
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
 * Created by zyz on 2019/5/28.
 * 我的收益
 */
public class MyIncomeActivity extends BaseActivity {
    int type = 1, page1 = 1, page2 = 1;
    MyIncomeModel model1;

    String orderField = "", orderType = "DESC";

    private RecyclerView recyclerView;
    List<MyIncomeListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<MyIncomeListModel.RecordsBean> mAdapter;
    //头部一
//    View headerView1;
    RelativeLayout head1_relativeLayout;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4;

    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myincome);

        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                params.clear();
                requestIncome(params);
            }

            @Override
            public void onLoadmore() {
                params.clear();
                //加载更多
                if (type == 1) {
                    page1 = page1 + 1;
                    params.put("page", page1 + "");
                }else {
                    page2 = page2 + 1;
                    params.put("page", page2 + "");
                }
                params.put("pageSize", "10");
                params.put("changeType", type + "");
                params.put("orderField", orderField);
                params.put("orderType", orderType);
                requestListMore(params);
            }
        });

        //悬浮部分
        invis = findViewByID_My(R.id.invis);
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);


        head1_relativeLayout = findViewByID_My(R.id.head1_relativeLayout);
//        head1_relativeLayout.setPadding(0, (int) CommonUtil.getStatusBarHeight(AccountDetailActivity.this), 0, 0);

        head1_textView1 = findViewByID_My(R.id.head1_textView1);
        head1_textView2 = findViewByID_My(R.id.head1_textView2);
        head1_textView3 = findViewByID_My(R.id.head1_textView3);
        head1_textView4 = findViewByID_My(R.id.head1_textView4);
        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*//listview 滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() >= 1) {
                    invis.setVisibility(View.VISIBLE);
                    headerView2.setVisibility(View.GONE);
                } else {
                    invis.setVisibility(View.GONE);
                    headerView2.setVisibility(View.VISIBLE);
                }
            }
        });*/
        //动态设置linearLayout的高度为屏幕高度的1/2
        ViewGroup.LayoutParams lp = head1_relativeLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(this) * 250 / 780;

    }

    @Override
    protected void initData() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
        requestIncome(params);
    }

    private void requestIncome(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.MyIncome, params, headerMap, new CallBackUtil<MyIncomeModel>() {
            @Override
            public MyIncomeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyIncomeModel response) {
//                showContentPage();
                model1 = response;

                head1_textView1.setText(response.getAvailableMoney());//可用余额
                head1_textView2.setText("￥" + response.getOperationMaintenanceShare());//运维分成
                head1_textView3.setText("￥" + response.getRecommendedShare());//推荐分成
                head1_textView4.setText("￥" + response.getTotalRevenue());//总营收

                params.clear();
                page1 = 1;
                page2 = 1;
                params.put("pageSize", "10");
                params.put("page", page1 + "");
                params.put("changeType", type + "");
                params.put("orderField", orderField);
                params.put("orderType", orderType);
                requestList(params);
            }
        });
    }

    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.MyIncomeList, GsonUtils.toJson(params), headerMap, new CallBackUtil<MyIncomeListModel>() {
            @Override
            public MyIncomeListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyIncomeListModel response) {
                showContentPage();
                hideProgress();
                list = response.getRecords();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<MyIncomeListModel.RecordsBean>
                            (MyIncomeActivity.this, R.layout.item_myincome, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyIncomeListModel.RecordsBean model, int position) {
                            holder.setText(R.id.textView1, model.getLogType());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.get);//流水号
                            holder.setText(R.id.textView3, model.getCreateTimeStr());//时间
                            if (type == 1)
                                holder.setText(R.id.textView4, "+" + model.getAvailableAmount());//金额
                            else
                                holder.setText(R.id.textView4, "-" + model.getAvailableAmount());//金额
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });
    }

    private void requestListMore(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.MyIncomeList, GsonUtils.toJson(params), headerMap, new CallBackUtil<MyIncomeListModel>() {
            @Override
            public MyIncomeListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                showErrorPage();
                hideProgress();
                myToast(err);
                if (type == 1)
                    page1--;
                else page2--;
            }

            @Override
            public void onResponse(MyIncomeListModel response) {
//                showContentPage();
                hideProgress();
                List<MyIncomeListModel.RecordsBean> list1 = new ArrayList<>();
                list1 = response.getRecords();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    if (type == 1)
                        page1--;
                    else page2--;
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;

            case R.id.tv_takecash:
                //提现
                CommonUtil.gotoActivity(this, TakeCashActivity.class, false);
                break;
            case R.id.tv_takecashlist:
                //提现列表
                CommonUtil.gotoActivity(this, MyTakeCashActivity.class, false);
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.black1));
            textView2.setTextColor(getResources().getColor(R.color.black3));
//            head2_textView1.setTextColor(getResources().getColor(R.color.blue));
//            head2_textView2.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
//            head2_view1.setVisibility(View.VISIBLE);
//            head2_view2.setVisibility(View.INVISIBLE);

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.black1));
//            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
//            head2_view1.setVisibility(View.INVISIBLE);
//            head2_view2.setVisibility(View.VISIBLE);

        }
        params.clear();
        page1 = 1;
        page2 = 1;
        params.put("pageSize", "10");
        params.put("page", page1 + "");
        params.put("changeType", type + "");
        params.put("orderField", orderField);
        params.put("orderType", orderType);
        requestList(params);
    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.qianbao_h1));
        titleView.setVisibility(View.GONE);
    }
}
