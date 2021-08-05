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
import com.xiyang.xiyang.model.ApproveDetailModel;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.popupwindow.PhotoShowDialog;
import com.xiyang.xiyang.popupwindow.PhotoShowDialog_1;
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
 * 审批详情
 */
public class ApproveDetailActivity extends BaseActivity {
    String id = "", typeStr = "";
    int type = 2;
    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;

    ApproveDetailModel model;
    TextView tv_name, tv_shop, tv_num, tv_addr;
    ImageView imageView1, imageView2;
    /**
     * 商户信息
     */
    LinearLayout ll_shopinfo;

    /**
     * 合同信息
     */
    LinearLayout ll_contract;
    TextView tv_liulan, tv_contract;
    ImageView iv_contract;
    RecyclerView rv_contract;
    List<KeyValueModel> list_contract = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_contract;

    /**
     * 审核合同
     */
    LinearLayout ll_shenhe;
    TextView tv_shenpi;
    RecyclerView rv_shenhe;
    List<ApproveDetailModel.ContractsDetailBasicVOBean.RecordsBean> list_shenhe = new ArrayList<>();
    CommonAdapter<ApproveDetailModel.ContractsDetailBasicVOBean.RecordsBean> mAdapter_shenhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvedetail);
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
                params.clear();
//                params.put("id", id);
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

        /**
         * 合同信息
         */
        ll_contract = findViewByID_My(R.id.ll_contract);
        tv_liulan = findViewByID_My(R.id.tv_liulan);
        tv_contract = findViewByID_My(R.id.tv_contract);
        iv_contract = findViewByID_My(R.id.iv_contract);
        rv_contract = findViewByID_My(R.id.rv_contract);
        rv_contract.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 门店信息
         */
        ll_shenhe = findViewByID_My(R.id.ll_shenhe);
        tv_shenpi = findViewByID_My(R.id.tv_shenpi);
        rv_shenhe = findViewByID_My(R.id.rv_shenhe);
        rv_shenhe.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_liulan:
                //查看PDF
                /*PhotoShowDialog_1 photoShowDialog = new PhotoShowDialog_1(ApproveDetailActivity.this,
                        URLs.IMGHOST + "");
                photoShowDialog.show();*/
                if (model.getContractsDetailBasicVO().getContractUrl() != null && !model.getContractsDetailBasicVO().getContractUrl().equals("")) {
                    bundle.putString("url", model.getContractsDetailBasicVO().getContractUrl());
                    CommonUtil.gotoActivityWithData(ApproveDetailActivity.this, ShowPDFActivity.class, bundle, false);
                } else myToast("暂无文件");

                break;
            case R.id.iv_contract:
                if (model.getContractsDetailBasicVO().getQualificationsImageUrl() !=null
                        && !model.getContractsDetailBasicVO().getQualificationsImageUrl().equals("")){
                    PhotoShowDialog_1 photoShowDialog = new PhotoShowDialog_1(ApproveDetailActivity.this,
                            model.getContractsDetailBasicVO().getQualificationsImageUrl());
                    photoShowDialog.show();
                }

                break;
            case R.id.tv_shenpi:
                //立即审批
                bundle.putString("id", id);
                bundle.putString("type", typeStr);
                bundle.putString("type_shenhe", "0");//合同审核
//                if (localUserInfo.getUserJob().equals("CM") && model.getWorkFlowApplyLogVo().getType().equals("device_add"))
                if (typeStr.equals("device_add")){
                    bundle.putString("num", model.getContractsDetailBasicVO().getAddQuantity());
                }else {
                    bundle.putString("num", "");
                }
                CommonUtil.gotoActivityWithData(ApproveDetailActivity.this, ApproveContractActivity.class, bundle);
                break;
            case R.id.ll_tab1:
                //商户信息
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //合同信息
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //审核合同
                type = 3;
                changeUI();
                break;
        }
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        typeStr = getIntent().getStringExtra("typeStr");
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

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.ApproveDetail + id, params, headerMap, new CallBackUtil<ApproveDetailModel>() {
            @Override
            public ApproveDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(ApproveDetailModel response) {
                hideProgress();
                model = response;

                tv_name.setText(response.getContractsDetailBasicVO().getName());
                tv_shop.setText("《" + response.getContractsDetailBasicVO().getTypeName() + "》");
                switch (response.getContractsDetailBasicVO().getStatus()) {//1:待处理; 2:处理中; 3:通过; 4:驳回;
                    case "1":
                        tv_num.setTextColor(getResources().getColor(R.color.black3));
                        tv_num.setText("待审核");
                        break;
                    case "2":
                        tv_num.setTextColor(getResources().getColor(R.color.black3));
                        tv_num.setText("审核中");
                        break;
                    case "3":
                        tv_num.setTextColor(getResources().getColor(R.color.green));
                        tv_num.setText("已通过");
                        break;
                    case "4":
                        tv_num.setTextColor(getResources().getColor(R.color.red));
                        tv_num.setText("已驳回");
                        break;
                }
                tv_addr.setText(response.getContractsDetailBasicVO().getContractNumber());
                Glide.with(ApproveDetailActivity.this)
                        .load(model.getContractsDetailBasicVO().getImage())
//                                .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(ApproveDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片

                tv_shenpi.setVisibility(View.VISIBLE);
                if (response.getShowBtn().equals("1")) {//需要审核
                    tv_shenpi.setVisibility(View.VISIBLE);
                } else {
                    tv_shenpi.setVisibility(View.GONE);
                }
                /**
                 * 合同信息
                 */
                list_contract.clear();
                tv_contract.setVisibility(View.GONE);
                iv_contract.setVisibility(View.GONE);
                switch (typeStr) {
                    case "merchant_sign":
                        //签约合同
                        list_contract.add(new KeyValueModel("合同类型", response.getContractsDetailBasicVO().getTypeName()));
                        list_contract.add(new KeyValueModel("商户名称", response.getContractsDetailBasicVO().getMerchantName()));
                        list_contract.add(new KeyValueModel("签约期限", response.getContractsDetailBasicVO().getSignPeriod()));
                        list_contract.add(new KeyValueModel("是否独家", response.getContractsDetailBasicVO().getSole()));
                        list_contract.add(new KeyValueModel("签约时间", response.getContractsDetailBasicVO().getSignTime()));
                        tv_contract.setVisibility(View.VISIBLE);
                        iv_contract.setVisibility(View.VISIBLE);
                        Glide.with(ApproveDetailActivity.this)
                                .load(response.getContractsDetailBasicVO().getQualificationsImageUrl())
                                .fitCenter()
                                .apply(RequestOptions.bitmapTransform(new
                                        RoundedCorners(CommonUtil.dip2px(ApproveDetailActivity.this, 10))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv_contract);//加载图片
                        break;
                    case "device_add":
                        //新增合同
                        list_contract.add(new KeyValueModel("门店名称", response.getContractsDetailBasicVO().getStoreName()));
                        list_contract.add(new KeyValueModel("新增数量", response.getContractsDetailBasicVO().getAddQuantity() + "台"));
                        list_contract.add(new KeyValueModel("申领方式", response.getContractsDetailBasicVO().getApplyType()));
                        break;
                    case "device_recover":
                        //回收合同
                        list_contract.add(new KeyValueModel("门店名称", response.getContractsDetailBasicVO().getStoreName()));
                        list_contract.add(new KeyValueModel("选择数量", response.getContractsDetailBasicVO().getRecoverQuantity() + "台"));
                        list_contract.add(new KeyValueModel("减少原因", response.getContractsDetailBasicVO().getReason()));
                        list_contract.add(new KeyValueModel("退回仓库", response.getContractsDetailBasicVO().getWarehouseName()));
                        break;
                    case "device_exchange":
                        //换绑合同
                        list_contract.add(new KeyValueModel("转出门店名称", response.getContractsDetailBasicVO().getOutStoreName()));
                        list_contract.add(new KeyValueModel("转入门店名称", response.getContractsDetailBasicVO().getInStoreName()));
                        list_contract.add(new KeyValueModel("转出设备数量", response.getContractsDetailBasicVO().getOutQuantity() + "台"));
                        break;
                    case "merchant_update":
                        //修改合同
                        list_contract.add(new KeyValueModel("商户名称", response.getContractsDetailBasicVO().getMerchantName()));
                        list_contract.add(new KeyValueModel("商户账号", response.getContractsDetailBasicVO().getMerchantAccount()));
                        list_contract.add(new KeyValueModel("商户联系人", response.getContractsDetailBasicVO().getMerchantContactName()));
                        list_contract.add(new KeyValueModel("联系电话", response.getContractsDetailBasicVO().getMerchantContactPhone()));
                        list_contract.add(new KeyValueModel("公司名称", response.getContractsDetailBasicVO().getMerchantCompanyName()));
                        list_contract.add(new KeyValueModel("营业执照号", response.getContractsDetailBasicVO().getMerchantLicenseNo()));
                        list_contract.add(new KeyValueModel("商户行业", response.getContractsDetailBasicVO().getMerchantIndustry()));
                        list_contract.add(new KeyValueModel("所在城市", response.getContractsDetailBasicVO().getMerchantCityName()));
                        list_contract.add(new KeyValueModel("详细地址", response.getContractsDetailBasicVO().getMerchantAddress()));
                        tv_contract.setVisibility(View.VISIBLE);
                        iv_contract.setVisibility(View.VISIBLE);
                        Glide.with(ApproveDetailActivity.this)
                                .load(response.getContractsDetailBasicVO().getQualificationsImageUrl())
                                .fitCenter()
                                .apply(RequestOptions.bitmapTransform(new
                                        RoundedCorners(CommonUtil.dip2px(ApproveDetailActivity.this, 10))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv_contract);//加载图片
                        break;
                    case "merchant_extend":
                        //续签合同
                        list_contract.add(new KeyValueModel("商户名称", response.getContractsDetailBasicVO().getMerchantName()));
                        list_contract.add(new KeyValueModel("续签年限", response.getContractsDetailBasicVO().getRenewalPeriod()));
                        list_contract.add(new KeyValueModel("续签时间", response.getContractsDetailBasicVO().getRenewalTime()));
                        list_contract.add(new KeyValueModel("是否独家", response.getContractsDetailBasicVO().getSole()));
                        break;
                    case "merchant_cancel":
                        //取消合同
                        list_contract.add(new KeyValueModel("商户名称", response.getContractsDetailBasicVO().getMerchantName()));
                        list_contract.add(new KeyValueModel("取消原因", response.getContractsDetailBasicVO().getCancelReason()));
                        break;
                    case "change_price":
                        //调价合同
                        list_contract.add(new KeyValueModel("门店名称", response.getContractsDetailBasicVO().getStoreName()));
                        list_contract.add(new KeyValueModel("首个小时", response.getContractsDetailBasicVO().getFirstHour()));
                        list_contract.add(new KeyValueModel("基础计价", response.getContractsDetailBasicVO().getRenewalTime()));
                        list_contract.add(new KeyValueModel("每日封顶", response.getContractsDetailBasicVO().getDailyTopPrice()));
                        list_contract.add(new KeyValueModel("免费时长", response.getContractsDetailBasicVO().getFreeTime()));
                        list_contract.add(new KeyValueModel("计费单元", response.getContractsDetailBasicVO().getBillingUnit()));
                        list_contract.add(new KeyValueModel("门店加价", response.getContractsDetailBasicVO().getStorePriceIncrease()));
                        list_contract.add(new KeyValueModel("门店封顶", response.getContractsDetailBasicVO().getStoreTopPrice()));
                        list_contract.add(new KeyValueModel("调价理由", response.getContractsDetailBasicVO().getReasonName()));
                        break;
                }
                list_contract.add(new KeyValueModel("审核时间", response.getContractsDetailBasicVO().getCheckTime()));
                list_contract.add(new KeyValueModel("创建时间", response.getContractsDetailBasicVO().getCreateTime()));

                mAdapter_contract = new CommonAdapter<KeyValueModel>
                        (ApproveDetailActivity.this, R.layout.item_keyvalue, list_contract) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_contract.setAdapter(mAdapter_contract);

                /**
                 * 审核合同
                 */
                list_shenhe = response.getContractsDetailBasicVO().getRecords();
                if (list_shenhe.size() > 0) {
                    showContentPage();
                    mAdapter_shenhe = new CommonAdapter<ApproveDetailModel.ContractsDetailBasicVOBean.RecordsBean>
                            (ApproveDetailActivity.this, R.layout.item_contractdetail_shenhe, list_shenhe) {
                        @Override
                        protected void convert(ViewHolder holder, ApproveDetailModel.ContractsDetailBasicVOBean.RecordsBean model, int position) {
                            //隐藏最前和最后的竖线
                            View view_top = holder.getView(R.id.view_top);
                            View view_bottom = holder.getView(R.id.view_bottom);
                            if (position == 0) {
                                view_top.setVisibility(View.INVISIBLE);
                            } else {
                                view_top.setVisibility(View.VISIBLE);
                            }
                            if (position == (list_shenhe.size() - 1)) {
                                view_bottom.setVisibility(View.GONE);
                            } else {
                                view_bottom.setVisibility(View.VISIBLE);
                            }

                            //横向图片
                            List<String> list_img = new ArrayList<>();
                            if (model.getImager() != null) {
                                String[] strArr = model.getImager().split(",");//拆分
                                for (String s : strArr) {
                                    list_img.add(s);
                                }
                            }
                            /*for (String s : model.getImage()) {
                                list_img.add(s);
                            }*/
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(ApproveDetailActivity.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (ApproveDetailActivity.this, R.layout.item_img_28_28, list_img) {
                                @Override
                                protected void convert(ViewHolder holder, String model, int position) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(ApproveDetailActivity.this).load(model)
                                            .centerCrop()
                                            .apply(RequestOptions.bitmapTransform(new
                                                    RoundedCorners(CommonUtil.dip2px(ApproveDetailActivity.this, 7))))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                }
                            };
                            ca.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(ApproveDetailActivity.this, list_img, i);
                                    photoShowDialog.show();
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv.setAdapter(ca);

                            ImageView iv_head = holder.getView(R.id.iv_head);
                            Glide.with(ApproveDetailActivity.this)
                                    .load(model.getAuditImage())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(ApproveDetailActivity.this, 3))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(iv_head);//加载图片
                            holder.setText(R.id.tv_name, model.getAuditName());
                            holder.setText(R.id.tv_time, model.getAuditTime());
                            holder.setText(R.id.tv_content, model.getReason());
                            //状态图片
                            ImageView iv_zhuangtai = holder.getView(R.id.iv_zhuangtai);
                            TextView tv_type = holder.getView(R.id.tv_type);
                            switch (model.getAuditStat()) {
                                case "1":
                                    tv_type.setText("已通过");
                                    tv_type.setTextColor(getResources().getColor(R.color.green));
                                    iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_2);
                                    break;
                                case "2":
                                    tv_type.setText("驳回");
                                    tv_type.setTextColor(getResources().getColor(R.color.red));
                                    iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_3);
                                    break;
                                case "3":
                                    tv_type.setText("处理中");
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_1);
                                    break;
                                default:
                                    tv_type.setText("待审核");
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_1);
                                    break;
                            }
                        }
                    };
                    rv_shenhe.setAdapter(mAdapter_shenhe);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("合同详情");
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
                ll_contract.setVisibility(View.GONE);
                ll_shenhe.setVisibility(View.GONE);

                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);

                ll_shopinfo.setVisibility(View.GONE);
                ll_contract.setVisibility(View.VISIBLE);
                ll_shenhe.setVisibility(View.GONE);

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);

                ll_shopinfo.setVisibility(View.GONE);
                ll_contract.setVisibility(View.GONE);
                ll_shenhe.setVisibility(View.VISIBLE);

                break;

        }
    }

}
