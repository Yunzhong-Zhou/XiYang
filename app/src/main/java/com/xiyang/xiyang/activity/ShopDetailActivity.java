package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.BankCardSettingModel;
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
 * 商户详情
 */
public class ShopDetailActivity extends BaseActivity {
    int type = 1;
    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;

    /**
     * 商户信息
     */
    LinearLayout ll_shopinfo, ll_shopedit;

    /**
     * 合同信息
     */
    LinearLayout ll_contract;
    RecyclerView rv_contract;
    List<Fragment2Model> list_contract = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter_contract;

    /**
     * 门店信息
     */
    LinearLayout ll_store;
    RecyclerView rv_store;
    List<Fragment2Model> list_store = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
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
         *商户信息
         */
        ll_shopinfo = findViewByID_My(R.id.ll_shopinfo);
        ll_shopedit = findViewByID_My(R.id.ll_shopedit);


        /**
         * 合同信息
         */
        ll_contract = findViewByID_My(R.id.ll_contract);
        rv_contract = findViewByID_My(R.id.rv_contract);
        rv_contract.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 门店信息
         */
        ll_store = findViewByID_My(R.id.ll_store);
        rv_store = findViewByID_My(R.id.rv_store);
        rv_store.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addcontract:
                //添加合同
                Bundle bundle = new Bundle();
                bundle.putInt("item_hetong",1);
                CommonUtil.gotoActivityWithData(ShopDetailActivity.this, AddContractActivity.class,bundle);
                break;
            case R.id.tv_morecontract:
                //合同-查看更多
                CommonUtil.gotoActivity(ShopDetailActivity.this, MyContractActivity.class);
                break;
            case R.id.tv_addstore:
                //添加门店
                CommonUtil.gotoActivity(ShopDetailActivity.this, AddStoreActivity.class);
                break;
            case R.id.tv_morestore:
                //门店-查看更多
                CommonUtil.gotoActivity(ShopDetailActivity.this, MyShopListActivity.class);
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
        for (int i = 0; i < 5; i++) {
            list_contract.add(new Fragment2Model());
            list_store.add(new Fragment2Model());
        }
        mAdapter_contract = new CommonAdapter<Fragment2Model>
                (ShopDetailActivity.this, R.layout.item_shopdetail_contract, list_contract) {
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
                        CommonUtil.gotoActivityWithData(ShopDetailActivity.this, ContractDetailActivity.class, bundle, false);
                    }
                });

            }
        };
        rv_contract.setAdapter(mAdapter_contract);
        mAdapter_store = new CommonAdapter<Fragment2Model>
                (ShopDetailActivity.this, R.layout.item_fragment2_2, list_store) {
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
                        CommonUtil.gotoActivityWithData(ShopDetailActivity.this, StoreDetailActivity.class, bundle, false);
                    }
                });

            }
        };
        rv_store.setAdapter(mAdapter_store);


    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.ShopDetail, params, headerMap, new CallBackUtil<BankCardSettingModel>() {
            @Override
            public BankCardSettingModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(BankCardSettingModel response) {
//                hideProgress();
//                model = response;

            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        params.put("id",id+"");
        request(params);
    }
    @Override
    protected void updateView() {
        titleView.setTitle("商户详情");
        titleView.showRightTxtBtn("立即指派", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(ShopDetailActivity.this,AssignActivity.class);
            }
        });
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

                ll_shopinfo.setVisibility(View.VISIBLE);
//                ll_shopedit.setVisibility(View.GONE);
                ll_contract.setVisibility(View.GONE);
                ll_store.setVisibility(View.GONE);

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

                ll_shopinfo.setVisibility(View.GONE);
                ll_shopedit.setVisibility(View.GONE);
                ll_contract.setVisibility(View.VISIBLE);
                ll_store.setVisibility(View.GONE);

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);

                ll_shopinfo.setVisibility(View.GONE);
                ll_shopedit.setVisibility(View.GONE);
                ll_contract.setVisibility(View.GONE);
                ll_store.setVisibility(View.VISIBLE);

                break;

        }
    }

}
