package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.model.ShopDetailModel;
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
    String id = "";
    int type = 1;
    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;

    ShopDetailModel model;
    TextView tv_name, tv_shop, tv_num, tv_addr;
    ImageView imageView1, imageView2;
    /**
     * 商户信息
     */
    LinearLayout ll_shopinfo, ll_shopedit;
    //基本信息-已审核
    private RecyclerView rv_info;
    List<KeyValueModel> list_info = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_info;
    ImageView iv_info;
    //统计数据
    private RecyclerView rv_tongji;
    List<KeyValueModel> list_tongji = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_tongji;
    //申请信息
    private RecyclerView rv_shenqing;
    List<KeyValueModel> list_shenqing = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_shenqing;
    //签约信息
    private RecyclerView rv_qianyue;
    List<KeyValueModel> list_qianyue = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_qianyue;
    //运维信息
    private RecyclerView rv_yunwei;
    List<KeyValueModel> list_yunwei = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_yunwei;
    /**
     * 合同信息
     */
    LinearLayout ll_contract;
    RecyclerView rv_contract;
    List<ShopDetailModel.ContractBean> list_contract = new ArrayList<>();
    CommonAdapter<ShopDetailModel.ContractBean> mAdapter_contract;

    /**
     * 门店信息
     */
    LinearLayout ll_store;
    RecyclerView rv_store;
    List<ShopDetailModel.StoreBean> list_store = new ArrayList<>();
    CommonAdapter<ShopDetailModel.StoreBean> mAdapter_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
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
                //刷新
//                params.put("id", id);
                params.clear();
                request(params);
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

        tv_name = findViewByID_My(R.id.tv_name);
        tv_shop = findViewByID_My(R.id.tv_shop);
        tv_num = findViewByID_My(R.id.tv_num);
        tv_addr = findViewByID_My(R.id.tv_addr);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);

        /**
         *商户信息
         */
        ll_shopinfo = findViewByID_My(R.id.ll_shopinfo);
        ll_shopedit = findViewByID_My(R.id.ll_shopedit);
        //基本信息-已审核
        rv_info = findViewByID_My(R.id.rv_info);
        rv_info.setLayoutManager(new LinearLayoutManager(this));
        iv_info = findViewByID_My(R.id.iv_info);
        //统计数据
        rv_tongji = findViewByID_My(R.id.rv_tongji);
        rv_tongji.setLayoutManager(new LinearLayoutManager(this));
        //申请信息
        rv_shenqing = findViewByID_My(R.id.rv_shenqing);
        rv_shenqing.setLayoutManager(new LinearLayoutManager(this));
        //签约信息
        rv_qianyue = findViewByID_My(R.id.rv_qianyue);
        rv_qianyue.setLayoutManager(new LinearLayoutManager(this));
        //运维信息
        rv_yunwei = findViewByID_My(R.id.rv_yunwei);
        rv_yunwei.setLayoutManager(new LinearLayoutManager(this));
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
                bundle.putInt("item_hetong", 1);
                CommonUtil.gotoActivityWithData(ShopDetailActivity.this, AddContractActivity.class, bundle);
                break;
            case R.id.tv_morecontract:
                //合同-查看更多
                CommonUtil.gotoActivity(ShopDetailActivity.this, MyContractActivity.class);
                break;
            case R.id.tv_addstore:
                //添加门店
                Bundle bundle2 = new Bundle();
                bundle2.putString("shopId", model.getId());
                bundle2.putString("shopName", model.getName());
                CommonUtil.gotoActivityWithData(ShopDetailActivity.this, AddStoreActivity.class, bundle2);
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
        id = getIntent().getStringExtra("id");
    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.ShopDetail + id, params, headerMap, new CallBackUtil<ShopDetailModel>() {
            @Override
            public ShopDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(ShopDetailModel response) {
                hideProgress();
                model = response;
                tv_name.setText(response.getName());
                tv_shop.setText(response.getDeviceNum());
                tv_num.setText(response.getMoney());
                tv_addr.setText(response.getAddress());
                Glide.with(ShopDetailActivity.this)
                        .load(model.getImage())
//                                .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(ShopDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片
                if (model.getStatus() != null && model.getStatus().equals("4")) {
                    imageView2.setImageResource(R.mipmap.bg_yiqianyue);
                } else {
                    //待签约
                    imageView2.setImageResource(R.mipmap.bg_daiqianyue);
                }
                /**
                 * 商户信息
                 */
                //基本信息 - 已审核
                list_info.clear();
                list_info.add(new KeyValueModel("商户ID", response.getId()));
                list_info.add(new KeyValueModel("商户名称", response.getName()));
                list_info.add(new KeyValueModel("商户账号", response.getBase().getAccount()));
                list_info.add(new KeyValueModel("商户联系人", response.getBase().getContactName()));
                list_info.add(new KeyValueModel("商户公司名", response.getBase().getCompanyName()));
                list_info.add(new KeyValueModel("营业执照号", response.getBase().getLicenseNo()));
                list_info.add(new KeyValueModel("所在城市", response.getBase().getCity()));
                list_info.add(new KeyValueModel("所在行业", response.getBase().getInsduty()));
                list_info.add(new KeyValueModel("商户等级", response.getBase().getLevel()));
                list_info.add(new KeyValueModel("商户标识", response.getBase().getSlag()));
                list_info.add(new KeyValueModel("商户来源", response.getBase().getSource()));
                list_info.add(new KeyValueModel("是否对公", response.getBase().getToPublic()));
                list_info.add(new KeyValueModel("是否绑卡", response.getBase().getIsCard()));
                list_info.add(new KeyValueModel("执照图片", ""));
                mAdapter_info = new CommonAdapter<KeyValueModel>
                        (ShopDetailActivity.this, R.layout.item_keyvalue, list_info) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_info.setAdapter(mAdapter_info);
                Glide.with(ShopDetailActivity.this)
                        .load(response.getBase().getLogoUrl())
                        .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(ShopDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_info);//加载图片
                //统计数据
                list_tongji.clear();
                list_tongji.add(new KeyValueModel("门店数", response.getBase().getCountData().getStoreNum()));
                list_tongji.add(new KeyValueModel("设备数", response.getBase().getCountData().getDeviceNum()));
                list_tongji.add(new KeyValueModel("总营收", response.getBase().getCountData().getMoney()));
                list_tongji.add(new KeyValueModel("总收益", response.getBase().getCountData().getProfit()));
                mAdapter_tongji = new CommonAdapter<KeyValueModel>
                        (ShopDetailActivity.this, R.layout.item_keyvalue, list_tongji) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_tongji.setAdapter(mAdapter_tongji);

                //申请信息
                list_shenqing.clear();
                list_shenqing.add(new KeyValueModel("推荐码", response.getBase().getApplyData().getInviteCode()));
                list_shenqing.add(new KeyValueModel("推荐类型", response.getBase().getApplyData().getInviteType()));
                list_shenqing.add(new KeyValueModel("申请时间", response.getBase().getApplyData().getApplyAt()));
                mAdapter_shenqing = new CommonAdapter<KeyValueModel>
                        (ShopDetailActivity.this, R.layout.item_keyvalue, list_shenqing) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_shenqing.setAdapter(mAdapter_shenqing);

                //签约信息
                list_qianyue.clear();
                list_qianyue.add(new KeyValueModel("签约合同", response.getBase().getSignData().getContract()));
                list_qianyue.add(new KeyValueModel("是否独家", response.getBase().getSignData().getSole()));
                list_qianyue.add(new KeyValueModel("签约时间", response.getBase().getSignData().getRenewalTime()));
                list_qianyue.add(new KeyValueModel("签约年限", response.getBase().getSignData().getRenewalPeriod()));
                list_qianyue.add(new KeyValueModel("签约人", response.getBase().getSignData().getUserName()));
                list_qianyue.add(new KeyValueModel("签约类型", response.getBase().getSignData().getSignType()));
                list_qianyue.add(new KeyValueModel("审核时间", response.getBase().getSignData().getVerifyedAt()));
                mAdapter_qianyue = new CommonAdapter<KeyValueModel>
                        (ShopDetailActivity.this, R.layout.item_keyvalue, list_qianyue) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_qianyue.setAdapter(mAdapter_qianyue);

                //申请信息
                list_yunwei.clear();
                list_yunwei.add(new KeyValueModel("运维BD", response.getBase().getBdData().getName()));
                list_yunwei.add(new KeyValueModel("运维类型", response.getBase().getBdData().getType()));
                mAdapter_yunwei = new CommonAdapter<KeyValueModel>
                        (ShopDetailActivity.this, R.layout.item_keyvalue, list_yunwei) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_yunwei.setAdapter(mAdapter_yunwei);

                /**
                 * 合同信息
                 */
                list_contract = response.getContract();
                mAdapter_contract = new CommonAdapter<ShopDetailModel.ContractBean>
                        (ShopDetailActivity.this, R.layout.item_shopdetail_contract, list_contract) {
                    @Override
                    protected void convert(ViewHolder holder, ShopDetailModel.ContractBean model, int position) {
                        holder.setText(R.id.tv1, "《" + model.getName() + "》");
                        TextView tv2 = holder.getView(R.id.tv2);
                        tv2.setText(model.getStatusTitle());
                        switch (model.getStatus()) {
                            case "1":
                                tv2.setTextColor(getResources().getColor(R.color.green));
                                break;
                            case "2":
                                tv2.setTextColor(getResources().getColor(R.color.black3));
                                break;
                            case "3":
                                tv2.setTextColor(getResources().getColor(R.color.red));
                                break;
                        }

                        holder.setText(R.id.tv3, model.getCreatedAt());
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

                /**
                 * 门店信息
                 */
                mAdapter_store = new CommonAdapter<ShopDetailModel.StoreBean>
                        (ShopDetailActivity.this, R.layout.item_fragment2_2, list_store) {
                    @Override
                    protected void convert(ViewHolder holder, ShopDetailModel.StoreBean model, int position) {
                        holder.setText(R.id.tv_name, model.getName());//标题
                        holder.setText(R.id.tv_shop, model.getDeviceNum());
                        holder.setText(R.id.tv_num, model.getMoney());//money
                        holder.setText(R.id.tv_addr, model.getAddress());

                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        Glide.with(ShopDetailActivity.this)
                                .load(model.getImage())
//                                .fitCenter()
                                .apply(RequestOptions.bitmapTransform(new
                                        RoundedCorners(CommonUtil.dip2px(ShopDetailActivity.this, 10))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(imageView1);//加载图片

                        /*ImageView imageView2 = holder.getView(R.id.imageView2);
                        if (model.getStatus().equals("1")) {
                            //待签约
                            imageView2.setImageResource(R.mipmap.bg_daiqianyue);
                        } else {
                            imageView2.setImageResource(R.mipmap.bg_yiqianyue);
                        }*/

                        holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("id", model.getId());
                                CommonUtil.gotoActivityWithData(ShopDetailActivity.this, StoreDetailActivity.class, bundle, false);
                            }
                        });

                    }
                };
                rv_store.setAdapter(mAdapter_store);
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
//        params.put("id", id);
        request(params);
    }

    @Override
    protected void updateView() {
        titleView.setTitle("商户详情");
        if (!localUserInfo.getUserJob().equals("BD")) {
            titleView.showRightTxtBtn("立即指派", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonUtil.gotoActivity(ShopDetailActivity.this, AssignActivity.class);
                }
            });
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

                ll_shopinfo.setVisibility(View.VISIBLE);
//                ll_shopedit.setVisibility(View.GONE);
                ll_contract.setVisibility(View.GONE);
                ll_store.setVisibility(View.GONE);

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
