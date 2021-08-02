package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.CommonModel;
import com.xiyang.xiyang.model.IndustryModel;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.model.ShopDetailModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
     * 商户信息-已审核
     */
    LinearLayout ll_shopinfo;
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
     * 商户信息-未审核
     */
    LinearLayout ll_shopedit;
    ImageView iv_edit;
    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9;
    TextView tv_xiugai;
    String name = "", companyName = "", contactName = "", contactPhone = "", provinceId = "", cityId = "", areaId = "",
            account = "", licenseNo = "", address = "", industryId = "";
    /**
     * 合同信息
     */
    LinearLayout ll_contract, ll_contract_more;
    RecyclerView rv_contract;
    List<ShopDetailModel.ContractsListBean> list_contract = new ArrayList<>();
    CommonAdapter<ShopDetailModel.ContractsListBean> mAdapter_contract;
    TextView tv_addcontract;
    View view_addcontract;
    /**
     * 门店信息
     */
    LinearLayout ll_store, ll_store_more;
    RecyclerView rv_store;
    List<ShopDetailModel.StoresListBean> list_store = new ArrayList<>();
    CommonAdapter<ShopDetailModel.StoresListBean> mAdapter_store;
    TextView tv_addstore;
    View view_addstore;

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
         *商户信息-已审核
         */
        ll_shopinfo = findViewByID_My(R.id.ll_shopinfo);
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
         * 商户信息-未审核
         */
        ll_shopedit = findViewByID_My(R.id.ll_shopedit);
        iv_edit = findViewByID_My(R.id.iv_edit);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        editText5 = findViewByID_My(R.id.editText5);
        editText6 = findViewByID_My(R.id.editText6);
        editText7 = findViewByID_My(R.id.editText7);
        editText8 = findViewByID_My(R.id.editText8);
        editText9 = findViewByID_My(R.id.editText9);
        tv_xiugai = findViewByID_My(R.id.tv_xiugai);
        /**
         * 合同信息
         */
        ll_contract = findViewByID_My(R.id.ll_contract);
        rv_contract = findViewByID_My(R.id.rv_contract);
        rv_contract.setLayoutManager(new LinearLayoutManager(this));
        ll_contract_more = findViewByID_My(R.id.ll_contract_more);
        tv_addcontract = findViewByID_My(R.id.tv_addcontract);
        view_addcontract = findViewByID_My(R.id.view_addcontract);
        if (localUserInfo.getUserJob().equals("BD")) {
            ll_contract_more.setVisibility(View.VISIBLE);
            tv_addcontract.setVisibility(View.VISIBLE);
            view_addcontract.setVisibility(View.VISIBLE);
        } else {
            ll_contract_more.setVisibility(View.GONE);
            tv_addcontract.setVisibility(View.GONE);
            view_addcontract.setVisibility(View.GONE);
        }
        /**
         * 门店信息
         */
        ll_store = findViewByID_My(R.id.ll_store);
        rv_store = findViewByID_My(R.id.rv_store);
        rv_store.setLayoutManager(new LinearLayoutManager(this));
        ll_store_more = findViewByID_My(R.id.ll_store_more);
        tv_addstore = findViewByID_My(R.id.tv_addstore);
        view_addstore = findViewByID_My(R.id.view_addstore);
        if (localUserInfo.getUserJob().equals("BD")) {
            ll_store_more.setVisibility(View.VISIBLE);
            tv_addstore.setVisibility(View.VISIBLE);
            view_addstore.setVisibility(View.VISIBLE);
        } else {
            ll_store_more.setVisibility(View.GONE);
            tv_addstore.setVisibility(View.GONE);
            view_addstore.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_xiugai:
                //修改商户信息
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    params.clear();
                    params.put("id", model.getBase().getId());
                    params.put("name", name);
                    params.put("companyName", companyName);
                    params.put("contactName", contactName);
                    params.put("contactPhone", contactPhone);
                    params.put("provinceId", provinceId);
                    params.put("cityId", cityId);
                    params.put("areaId", areaId);
//                    params.put("logoUrl", logoUrl);
                    params.put("account", account);
                    params.put("licenseNo", licenseNo);
//                    params.put("licenseNoImage", licenseNoImage);
                    params.put("address", address);
                    params.put("industryId", industryId);
                    requestUpData(params);
                }
                break;
            case R.id.editText7:
                //商户行业
                dialogList_hangye("0");
                break;
            case R.id.editText8:
                //所在城市
                dialogList_chengshi("0");
                break;
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
                bundle2.putString("shopId", model.getBase().getId());
                bundle2.putString("shopName", model.getBase().getName());
                CommonUtil.gotoActivityWithData(ShopDetailActivity.this, SelectAddressActivity.class, bundle2);
                break;
            case R.id.tv_morestore:
                //门店-查看更多
                CommonUtil.gotoActivity(ShopDetailActivity.this, MyStoreListActivity.class);
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
                tv_name.setText(response.getBase().getName());
                tv_shop.setText(response.getCountData().getStoreNum());//门店数量
                tv_num.setText(response.getCountData().getMoney());//商户营收
                tv_addr.setText(response.getBase().getAddress());
                Glide.with(ShopDetailActivity.this)
                        .load(model.getBase().getLogoUrl())
//                                .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(ShopDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片
                if (model.getBase().getStatus() != null && model.getBase().getStatus().equals("4")) {
                    imageView2.setImageResource(R.mipmap.bg_yiqianyue);
                } else {
                    //待签约
                    imageView2.setImageResource(R.mipmap.bg_daiqianyue);
                }

                if (!localUserInfo.getUserJob().equals("BD") && response.getBase().getShowPointBtn() != null && !response.getBase().getShowPointBtn().equals("")) {
                    titleView.showRightTxtBtn("立即指派", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", response.getBase().getShowPointBtn());
                            bundle.putInt("type_m", 1);//1、商户分派 2、门店分派 3、工单分派
                            bundle.putString("name", model.getBase().getName());
                            bundle.putString("userName", model.getBase().getContactName());
                            CommonUtil.gotoActivityWithData(ShopDetailActivity.this, AssignActivity.class, bundle, false);
                        }
                    });
                }

                changeUI();//修改布局
                /**
                 * 商户信息 - 已审核
                 */
                //基本信息
                list_info.clear();
                list_info.add(new KeyValueModel("商户ID", response.getBase().getId()));
                list_info.add(new KeyValueModel("商户名称", response.getBase().getName()));
                list_info.add(new KeyValueModel("商户账号", response.getBase().getAccount()));
                list_info.add(new KeyValueModel("商户联系人", response.getBase().getContactName()));
                list_info.add(new KeyValueModel("商户公司名", response.getBase().getCompanyName()));
                list_info.add(new KeyValueModel("营业执照号", response.getBase().getLicenseNo()));
                list_info.add(new KeyValueModel("所在城市", response.getBase().getCity()));
                list_info.add(new KeyValueModel("所在行业", response.getBase().getInsduty()));
                list_info.add(new KeyValueModel("商户等级", response.getBase().getLevel()));
                list_info.add(new KeyValueModel("商户标识", response.getBase().getTag()));
                list_info.add(new KeyValueModel("商户来源", response.getBase().getSources()));
                list_info.add(new KeyValueModel("是否对公", response.getBase().getIsPublic()));
                list_info.add(new KeyValueModel("是否绑卡", response.getBase().getIsBindBank()));
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
                list_tongji.add(new KeyValueModel("门店数", response.getCountData().getStoreNum()));
                list_tongji.add(new KeyValueModel("设备数", response.getCountData().getDeviceNum()));
                list_tongji.add(new KeyValueModel("总营收", response.getCountData().getMoney()));
                list_tongji.add(new KeyValueModel("总收益", response.getCountData().getProfit()));
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
                list_shenqing.add(new KeyValueModel("推荐码", response.getApplyData().getInviteCode()));
                list_shenqing.add(new KeyValueModel("推荐类型", response.getApplyData().getInviteType()));
                list_shenqing.add(new KeyValueModel("申请时间", response.getApplyData().getApplyAt()));
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
                if (response.getSignData() != null) {
                    list_qianyue.add(new KeyValueModel("签约合同", response.getSignData().getContract()));
                    list_qianyue.add(new KeyValueModel("是否独家", response.getSignData().getSole()));
                    list_qianyue.add(new KeyValueModel("签约时间", response.getSignData().getRenewalTime()));
                    list_qianyue.add(new KeyValueModel("签约年限", response.getSignData().getRenewalPeriod()));
                    list_qianyue.add(new KeyValueModel("签约人", response.getSignData().getUserName()));
                    list_qianyue.add(new KeyValueModel("签约类型", response.getSignData().getSignType()));
                    list_qianyue.add(new KeyValueModel("审核时间", response.getSignData().getVerifyedAt()));
                }
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
                list_yunwei.add(new KeyValueModel("运维BD", response.getBdData().getName()));
                list_yunwei.add(new KeyValueModel("运维类型", response.getBdData().getType()));
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
                 * 商户信息-未审核
                 */
                editText1.setText(response.getBase().getName());//商户名称
                editText2.setText(response.getBase().getAccount());//商户账号
                editText3.setText(response.getBase().getContactName());//商户联系人
                editText4.setText(response.getBase().getContactPhone());//联系人电话
                editText5.setText(response.getBase().getCompanyName());//商户公司名
                editText6.setText(response.getBase().getLicenseNo());//营业执照号
                editText7.setText(response.getBase().getInsduty());//所在行业
                editText8.setText(response.getBase().getCity());//所在城市
                editText9.setText(response.getBase().getAddress());//详细地址

                /**
                 * 合同信息
                 */
                list_contract = response.getContractsList();
                mAdapter_contract = new CommonAdapter<ShopDetailModel.ContractsListBean>
                        (ShopDetailActivity.this, R.layout.item_shopdetail_contract, list_contract) {
                    @Override
                    protected void convert(ViewHolder holder, ShopDetailModel.ContractsListBean model, int position) {
                        holder.setText(R.id.tv1, "《" + model.getTypeStr() + "》");
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

                        holder.setText(R.id.tv3, model.getCreateTime());
                        holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("id", model.getId());
                                bundle.putString("typeStr", model.getType());
                                CommonUtil.gotoActivityWithData(ShopDetailActivity.this, ContractDetailActivity.class, bundle, false);
                            }
                        });

                    }
                };
                rv_contract.setAdapter(mAdapter_contract);

                /**
                 * 门店信息
                 */
                list_store = response.getStoresList();
                mAdapter_store = new CommonAdapter<ShopDetailModel.StoresListBean>
                        (ShopDetailActivity.this, R.layout.item_fragment2_2, list_store) {
                    @Override
                    protected void convert(ViewHolder holder, ShopDetailModel.StoresListBean model, int position) {
                        holder.setText(R.id.tv_name, model.getName());//标题
                        holder.setText(R.id.tv_shop, model.getDeviceNumber());
                        holder.setText(R.id.tv_num, model.getTotalRevenue());//money
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

                        ImageView imageView2 = holder.getView(R.id.imageView2);
                        if (model.getContractStatus().equals("1")) {
                            //待签约
                            imageView2.setImageResource(R.mipmap.bg_daiqianyue);
                        } else {
                            imageView2.setImageResource(R.mipmap.bg_yiqianyue);
                        }

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

                if (model.getBase().getSignStatus() != null && model.getBase().getSignStatus().equals("4")) {
                    ll_shopinfo.setVisibility(View.VISIBLE);
                    ll_shopedit.setVisibility(View.GONE);
                } else {
                    ll_shopinfo.setVisibility(View.GONE);
                    ll_shopedit.setVisibility(View.VISIBLE);
                }

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

    /**
     * ************************修改商户信息*******************************************
     */
    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.ChangeShop, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                myToast("修改成功");
                hideProgress();
//                finish();
                requestServer();
            }
        });
    }

    private boolean match() {
        name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            myToast("请输入商户名称");
            return false;
        }
        account = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            myToast("请输入商户账号");
            return false;
        }
        contactName = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(contactName)) {
            myToast("请输入商户联系人");
            return false;
        }
        contactPhone = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(contactPhone)) {
            myToast("请输入联系人电话");
            return false;
        }
        companyName = editText5.getText().toString().trim();
        if (TextUtils.isEmpty(companyName)) {
            myToast("请输入公司名称");
            return false;
        }
        licenseNo = editText6.getText().toString().trim();
        if (TextUtils.isEmpty(licenseNo)) {
            myToast("请输入营业执照号");
            return false;
        }
        if (TextUtils.isEmpty(industryId)) {
            myToast("请选择商户行业");
            return false;
        }
        if (TextUtils.isEmpty(provinceId)) {
            myToast("请选择省");
            return false;
        }
        if (TextUtils.isEmpty(cityId)) {
            myToast("请选择市");
            return false;
        }
        if (TextUtils.isEmpty(areaId)) {
            myToast("请选择区");
            return false;
        }
        address = editText9.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            myToast("请输入详细地址");
            return false;
        }

        return true;
    }

    /**
     * 选择行业
     */
    List<IndustryModel.ListBean> list_hangye = new ArrayList<>();
    int maxIdex_hangye = 2;
    String string_hangye = "";

    private void dialogList_hangye(String parentId) {
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
//        params.put("parentId", parentId);
        OkhttpUtil.okHttpGet(URLs.Industry + parentId, params, headerMap, new CallBackUtil<IndustryModel>() {
            @Override
            public IndustryModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(IndustryModel response) {
                hideProgress();
                list_hangye = response.getList();
                dialog.contentView(R.layout.dialog_list_center)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
                CommonAdapter<IndustryModel.ListBean> adapter = new CommonAdapter<IndustryModel.ListBean>
                        (ShopDetailActivity.this, R.layout.item_help, list_hangye) {
                    @Override
                    protected void convert(ViewHolder holder, IndustryModel.ListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model.getName());
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        maxIdex_hangye--;
                        string_hangye = string_hangye + list_hangye.get(position).getName() + "-";
                        if (maxIdex_hangye == 0) {
                            //最后一个，赋值
                            if (!string_hangye.equals("")) {
                                string_hangye = string_hangye.substring(0, string_hangye.length() - 1);
                            }
                            editText7.setText(string_hangye);
                            industryId = list_hangye.get(position).getId();
                            //初始化
                            string_hangye = "";
                            maxIdex_hangye = 2;

                            dialog.dismiss();
                        } else {
                            dialogList_hangye(list_hangye.get(position).getId());
                        }
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_list.setAdapter(adapter);

            }
        });
    }

    /**
     * 选择城市
     */
    List<CommonModel.ListBean> list_chengshi = new ArrayList<>();
    int maxIdex_chengshi = 1;
    String string_chengshi = "";

    private void dialogList_chengshi(String parentId) {
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
        params.put("id", parentId);
        params.put("level", maxIdex_chengshi + "");
        OkhttpUtil.okHttpPostJson(URLs.Region, GsonUtils.toJson(params), headerMap, new CallBackUtil<CommonModel>() {
            @Override
            public CommonModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(CommonModel response) {
                hideProgress();
                list_chengshi = response.getList();
                dialog.contentView(R.layout.dialog_list_center)
//                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                CommonUtil.dip2px(ShopDetailActivity.this, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(false)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (ShopDetailActivity.this, R.layout.item_help, list_chengshi) {
                    @Override
                    protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model.getName());
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        string_chengshi = string_chengshi + list_chengshi.get(position).getName() + "-";
                        switch (maxIdex_chengshi) {
                            case 3:
                                //区
                                //最后一个，赋值
                                if (!string_chengshi.equals("")) {
                                    string_chengshi = string_chengshi.substring(0, string_chengshi.length() - 1);
                                }
                                editText8.setText(string_chengshi);
                                areaId = list_chengshi.get(position).getId();
                                //初始化
                                string_chengshi = "";
                                maxIdex_chengshi = 1;

                                dialog.dismiss();
                                break;
                            case 2:
                                //市
                                cityId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 3;
                                dialogList_chengshi(list_chengshi.get(position).getId());
                                break;
                            case 1:
                                //省
                                provinceId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 2;
                                dialogList_chengshi(list_chengshi.get(position).getId());
                                break;
                        }

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_list.setAdapter(adapter);

            }
        });
    }
}
