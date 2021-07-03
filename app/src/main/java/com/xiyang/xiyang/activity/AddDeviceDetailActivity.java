package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.BankListModel;
import com.xiyang.xiyang.model.Fragment2Model;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/30.
 * 申领设备详情
 */
public class AddDeviceDetailActivity extends BaseActivity {

    int type = 1, type_device = 1;
    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;


    /**
     * 基本信息
     */
    LinearLayout ll_jibeninfo;
    RecyclerView rv_yixuan;
    List<Fragment2Model> list_yixuan = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter_yixuan;

    /**
     * 申领信息
     */
    LinearLayout ll_shenling;


    /**
     * 安装记录
     */
    LinearLayout ll_anzhuang;
    RecyclerView rv_anzhuang;
    List<Fragment2Model> list_anzhuang = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter_anzhuang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevicedetail);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                /*String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);*/
            }

            @Override
            public void onLoadmore() {

            }
        });
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
        /**
         *基本信息
         */
        ll_jibeninfo = findViewByID_My(R.id.ll_jibeninfo);
        rv_yixuan = findViewByID_My(R.id.rv_yixuan);
        rv_yixuan.setLayoutManager(new LinearLayoutManager(this));

        /**
         * 申领信息
         */
        ll_shenling = findViewByID_My(R.id.ll_shenling);

        /**
         * 安装记录
         */
        ll_anzhuang = findViewByID_My(R.id.ll_anzhuang);
        rv_anzhuang = findViewByID_My(R.id.rv_anzhuang);
        rv_anzhuang.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_qianshou:
                //签收
//                CommonUtil.gotoActivity(AddDeviceDetailActivity.this, MyContractActivity.class);
                break;
            case R.id.tv_shuaxin:
                //刷新
//                CommonUtil.gotoActivity(AddDeviceDetailActivity.this, AddContractActivity.class);
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

    @Override
    protected void initData() {
        type_device = getIntent().getIntExtra("type", 1);

        for (int i = 0; i < 5; i++) {
            list_yixuan.add(new Fragment2Model());
            list_anzhuang.add(new Fragment2Model());
        }
        mAdapter_yixuan = new CommonAdapter<Fragment2Model>
                (AddDeviceDetailActivity.this, R.layout.item_adddevicedetail_yixuan, list_yixuan) {
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
                        CommonUtil.gotoActivityWithData(AddDeviceDetailActivity.this, ContractDetailActivity.class, bundle, false);
                    }
                });

            }
        };
        rv_yixuan.setAdapter(mAdapter_yixuan);
        mAdapter_anzhuang = new CommonAdapter<Fragment2Model>
                (AddDeviceDetailActivity.this, R.layout.item_affairedetail_anzhuang, list_anzhuang) {
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
                        CommonUtil.gotoActivityWithData(AddDeviceDetailActivity.this, StoreDetailActivity.class, bundle, false);
                    }
                });

            }
        };
        rv_anzhuang.setAdapter(mAdapter_anzhuang);


    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.BankCard, params, headerMap, new CallBackUtil<BankListModel>() {
            @Override
            public BankListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(BankListModel response) {
//                hideProgress();
//                model = response;

            }
        });
    }

    @Override
    protected void updateView() {
        switch (type_device) {
            case 1:
                titleView.setTitle("申领净化器主机");
                break;
            case 2:
                titleView.setTitle("申领4G模块");
                break;
            case 3:
                titleView.setTitle("申领过滤网");
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

                ll_jibeninfo.setVisibility(View.VISIBLE);
                ll_shenling.setVisibility(View.GONE);
                ll_anzhuang.setVisibility(View.GONE);

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

                ll_jibeninfo.setVisibility(View.GONE);
                ll_shenling.setVisibility(View.VISIBLE);
                ll_anzhuang.setVisibility(View.GONE);

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);

                ll_jibeninfo.setVisibility(View.GONE);
                ll_shenling.setVisibility(View.GONE);
                ll_anzhuang.setVisibility(View.VISIBLE);

                break;

        }
    }

}
