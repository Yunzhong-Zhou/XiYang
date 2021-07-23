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
import com.xiyang.xiyang.model.ContractDetailModel1;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.popupwindow.PhotoShowDialog;
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
 * 合同详情
 */
public class ContractDetailActivity1 extends BaseActivity {
    String id = "", typeStr = "";
    int type = 1;
    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;

    ContractDetailModel1 model;
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
    TextView tv_liulan;
    ImageView iv_contract;
    RecyclerView rv_contract;
    List<KeyValueModel> list_contract = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_contract;
    /**
     * 审核合同
     */
    LinearLayout ll_shenhe;
    RecyclerView rv_shenhe;
    List<ContractDetailModel1.WorkFlowApplylogOperateVoBean> list_shenhe = new ArrayList<>();
    CommonAdapter<ContractDetailModel1.WorkFlowApplylogOperateVoBean> mAdapter_shenhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractdetail);
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
                request(params,URLs.ContractDetail+typeStr+"/"+id);
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
        iv_contract = findViewByID_My(R.id.iv_contract);
        rv_contract = findViewByID_My(R.id.rv_contract);
        rv_contract.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 门店信息
         */
        ll_shenhe = findViewByID_My(R.id.ll_shenhe);
        rv_shenhe = findViewByID_My(R.id.rv_shenhe);
        rv_shenhe.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_liulan:
                //查看图片
                /*PhotoShowDialog_1 photoShowDialog = new PhotoShowDialog_1(ContractDetailActivity.this,
                        URLs.IMGHOST + "");
                photoShowDialog.show();*/
               /* if (model.getBase().getFile() != null && !model.getBase().getFile().equals("")) {
                    bundle.putString("url", model.getBase().getFile());
                    CommonUtil.gotoActivityWithData(ApproveDetailActivity.this, ShowPDFActivity.class, bundle, false);
                }else myToast("暂无文件");*/
                break;
            /*case R.id.tv_shenpi:
                //立即审批
                bundle.putString("id", "");
                if (model.getWorkFlowApplyLogVo().getType().equals("device_add")){
                    bundle.putString("type", "device_add");
                    bundle.putString("type_shenhe", "1");//合同审核
                    bundle.putString("num", "");
                }
                CommonUtil.gotoActivityWithData(ContractDetailActivity.this, ApproveContractActivity.class, bundle);
                break;*/
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
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
//        params.put("id", id);
        request(params,URLs.ContractDetail+typeStr+"/"+id);
    }

    private void request(HashMap<String, String> params,String url) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<ContractDetailModel1>() {
            @Override
            public ContractDetailModel1 onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(ContractDetailModel1 response) {
                hideProgress();
                model = response;
                tv_name.setText(response.getContractsVo().getName());
                tv_shop.setText("《" + response.getContractsVo().getTypeTitle() + "》");
                tv_num.setText(response.getContractsVo().getStatusTitle());
                tv_addr.setText(response.getContractsVo().getContractNumber());
                Glide.with(ContractDetailActivity1.this)
                        .load(model.getContractsVo().getImage())
//                                .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(ContractDetailActivity1.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片

                /**
                 * 商户信息
                 */


                /**
                 * 合同信息
                 */
                list_contract.clear();
                list_contract.add(new KeyValueModel("合同类型", response.getContractsVo().getTypeTitle()));
                list_contract.add(new KeyValueModel("商户名称", response.getContractsVo().getName()));

                //签约合同
                /*if (response.getContractsVo().getSole() != null) {
                    list_contract.add(new KeyValueModel("签约期限", response.getContractsVo().getRenewalPeriod() + "年"));
                    if (response.getContractsVo().getSole().equals("1"))
                        list_contract.add(new KeyValueModel("是否独家", "是"));
                    else list_contract.add(new KeyValueModel("是否独家", "否"));
                }*/


                list_contract.add(new KeyValueModel("审核时间", response.getContractsVo().getAuditTime()));

                mAdapter_contract = new CommonAdapter<KeyValueModel>
                        (ContractDetailActivity1.this, R.layout.item_keyvalue, list_contract) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_contract.setAdapter(mAdapter_contract);
                Glide.with(ContractDetailActivity1.this)
                        .load(response.getContractsVo().getImage())
                        .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(ContractDetailActivity1.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_contract);//加载图片

                /**
                 * 审核合同
                 */
                list_shenhe = response.getWorkFlowApplylogOperateVo();
                if (list_shenhe.size() > 0) {
                    showContentPage();
                    mAdapter_shenhe = new CommonAdapter<ContractDetailModel1.WorkFlowApplylogOperateVoBean>
                            (ContractDetailActivity1.this, R.layout.item_contractdetail_shenhe, list_shenhe) {
                        @Override
                        protected void convert(ViewHolder holder, ContractDetailModel1.WorkFlowApplylogOperateVoBean model, int position) {
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
                            /*if (model.getImages()!=null){
                                String[] strArr = model.getImages().split(",");//拆分
                                for (String s : strArr) {
                                    list_img.add(s);
                                }
                            }*/
                            /*for (String s : model.getImage()) {
                                list_img.add(s);
                            }*/
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(ContractDetailActivity1.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (ContractDetailActivity1.this, R.layout.item_img_28_28, list_img) {
                                @Override
                                protected void convert(ViewHolder holder, String model, int position) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(ContractDetailActivity1.this).load(model)
                                            .centerCrop()
                                            .apply(RequestOptions.bitmapTransform(new
                                                    RoundedCorners(CommonUtil.dip2px(ContractDetailActivity1.this, 7))))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                }
                            };
                            ca.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(ContractDetailActivity1.this, list_img, i);
                                    photoShowDialog.show();
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv.setAdapter(ca);

                            ImageView iv_head = holder.getView(R.id.iv_head);
                            /*Glide.with(ApproveDetailActivity.this)
                                    .load(model.getHead())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(ApproveDetailActivity.this, 3))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(iv_head);//加载图片*/
                            holder.setText(R.id.tv_name, model.getUserName());
                            holder.setText(R.id.tv_time, model.getAuditTime());
                            holder.setText(R.id.tv_content, model.getReason());
                            //状态图片
                            ImageView iv_zhuangtai = holder.getView(R.id.iv_zhuangtai);
                            TextView tv_type = holder.getView(R.id.tv_type);
                            switch (model.getAuditStat()) {
                                case "1":
                                    tv_type.setText("已完成");
                                    tv_type.setTextColor(getResources().getColor(R.color.green));
                                    iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_2);
                                    break;
                                case "2":
                                    tv_type.setText("处理中");
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_1);
                                    break;
                                case "3":
                                    tv_type.setText("驳回");
                                    tv_type.setTextColor(getResources().getColor(R.color.red));
                                    iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_3);
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