package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
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
    private RecyclerView recyclerView;
    List<MyIncomeModel.InBean> list1 = new ArrayList<>();
    CommonAdapter<MyIncomeModel.InBean> mAdapter1;

    List<MyIncomeModel.OutBean> list2 = new ArrayList<>();
    CommonAdapter<MyIncomeModel.OutBean> mAdapter2;
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
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Request(params);
            }

            @Override
            public void onLoadmore() {

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

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.MyIncome, params, headerMap, new CallBackUtil<MyIncomeModel>() {
            @Override
            public MyIncomeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyIncomeModel response) {
                showContentPage();
                model1 = response;

                head1_textView1.setText(response.getAmount());//可用余额
                head1_textView2.setText("￥" + response.getOperateMoney());//运维分成
                head1_textView3.setText("￥" + response.getRecommendMoney());//推荐分成
                head1_textView4.setText("￥" + response.getTotalMoney());//总营收

                list1 = response.getIn();
                mAdapter1 = new CommonAdapter<MyIncomeModel.InBean>
                        (MyIncomeActivity.this, R.layout.item_myincome, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final MyIncomeModel.InBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.get);//流水号
                        holder.setText(R.id.textView3, model.getCreatedAt());//时间
                        holder.setText(R.id.textView4, "+"+model.getMoney());//状态
                    }
                };

                list2 = response.getOut();
                mAdapter2 = new CommonAdapter<MyIncomeModel.OutBean>
                        (MyIncomeActivity.this, R.layout.item_myincome, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final MyIncomeModel.OutBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.get);//流水号
                        holder.setText(R.id.textView3, model.getCreatedAt());//时间
                        holder.setText(R.id.textView4, "-"+model.getMoney());//状态
                    }
                };

                changeUI();
                hideProgress();
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

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.black1));
//            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
//            head2_view1.setVisibility(View.INVISIBLE);
//            head2_view2.setVisibility(View.VISIBLE);
            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
//                mAdapter2.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        /*params.clear();
        page1 = 1;
        page2 = 2;
        params.put("count", "10");
        params.put("page", page1+"");*/
        Request(params);

    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.qianbao_h1));
        titleView.setVisibility(View.GONE);
    }
}
