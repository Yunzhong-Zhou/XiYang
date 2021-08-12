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
import com.lihang.ShadowLayout;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.model.WorkListDetailModel;
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
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/30.
 * 工单详情
 */
public class WorkListDetailActivity extends BaseActivity {
    String id = "", url = "";
    WorkListDetailModel model;

    ShadowLayout sl_tab;
    int type = 1, type_m = 3;//1、商户分派 2、门店分派 3、工单分派
    TextView tv_tab1, tv_tab2;
    LinearLayout ll_tab1, ll_tab2;
    View view1, view2;

    ImageView imageView1, imageView2;
    TextView textView1, textView2, textView3, textView4;

    /**
     * 商户信息
     */
    LinearLayout ll_shopinfo;
    private RecyclerView rv_info;
    List<KeyValueModel> list_info = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_info;
    ImageView iv_info;
    TextView tv_jieshou;
    /**
     * 审核合同
     */
    LinearLayout ll_shenhe;
    RecyclerView rv_shenhe;
    List<WorkListDetailModel.DealListBean> list_shenhe = new ArrayList<>();
    CommonAdapter<WorkListDetailModel.DealListBean> mAdapter_shenhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklistdetail);
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
        sl_tab = findViewByID_My(R.id.sl_tab);
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        tv_tab1 = findViewByID_My(R.id.tv_tab1);
        tv_tab2 = findViewByID_My(R.id.tv_tab2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);

        /**
         *商户信息
         */
        ll_shopinfo = findViewByID_My(R.id.ll_shopinfo);
        rv_info = findViewByID_My(R.id.rv_info);
        rv_info.setLayoutManager(new LinearLayoutManager(this));
        iv_info = findViewByID_My(R.id.iv_info);
        tv_jieshou = findViewByID_My(R.id.tv_jieshou);
        /**
         * 门店信息
         */
        ll_shenhe = findViewByID_My(R.id.ll_shenhe);
        rv_shenhe = findViewByID_My(R.id.rv_shenhe);
        rv_shenhe.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_info:
                //查看图片
                PhotoShowDialog_1 photoShowDialog = new PhotoShowDialog_1(WorkListDetailActivity.this,
                        model.getImages());
                photoShowDialog.show();
                break;
            case R.id.tv_jieshou:
                //接手
                if (tv_jieshou.getText().toString().trim().equals("接手")) {
                    showToast("确认接手该工单吗？", "确定", "取消",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.app_loading1));
                                    params.clear();
//                                    params.put("id", id);
                                    requestJieShou(params, id);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                } else {
                    //处理工单（上报、已处理）
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    CommonUtil.gotoActivityWithData(WorkListDetailActivity.this, ChangeWorkListActivity.class, bundle, false);
                }

                break;
            case R.id.ll_tab1:
                //工单信息
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //处理记录
                type = 2;
                changeUI();
                break;
        }
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        type_m = getIntent().getIntExtra("type_m", 3);
        switch (type_m) {
            case 1:
                //商户

                break;
            case 2:
                //门店

                break;
            case 3:
                //工单
                url = URLs.WorkListDetail;
                break;
        }
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
        OkhttpUtil.okHttpGet(url + id, params, headerMap, new CallBackUtil<WorkListDetailModel>() {
            @Override
            public WorkListDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showErrorPage();
                myToast(err);

            }

            @Override
            public void onResponse(WorkListDetailModel response) {
                hideProgress();
                model = response;
                /**
                 * 基本信息
                 */

                tv_jieshou.setVisibility(View.GONE);
                if (response.getTakeOverFlag().equals("0")) {//未接工单
                    sl_tab.setVisibility(View.GONE);
                    tv_jieshou.setText("接手");
                    tv_jieshou.setVisibility(View.VISIBLE);
                } else {
                    sl_tab.setVisibility(View.VISIBLE);
                    if (response.getStatus().equals("1") && localUserInfo.getUserJob().equals("BD") && response.isCurrentUser()) {
                        tv_jieshou.setText("处理工单");
                        tv_jieshou.setVisibility(View.VISIBLE);
                    }

                    if (response.getStatus().equals("1") && !localUserInfo.getUserJob().equals("BD") && response.isCurrentUser()) {
                        titleView.showRightTxtBtn("立即指派", v -> {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", model.getId());
                            bundle.putInt("type_m", type_m);
                            bundle.putString("name", textView1.getText().toString() + "-" + model.getStoreName());
                            bundle.putString("userName", model.getReportUserName());
                            CommonUtil.gotoActivityWithData(WorkListDetailActivity.this, AssignActivity.class, bundle, false);

                        });
                    }else {
                        titleView.hideRightBtn_invisible();
                    }
                }

                Glide.with(WorkListDetailActivity.this)
                        .load(response.getStoreImage())
                        .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(WorkListDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片
                switch (model.getType()) {//类型 1-设备工单,2-订单工单,3-任务工单,4-其它,、
                    case "1":
                        //设备故障
                        textView1.setText("设备故障");

                        break;
                    case "2":
                        //订单故障
                        textView1.setText("订单故障");
                        break;
                    case "3":
                        //任务工单
                        textView1.setText("任务工单");
                        break;
                    case "4":
                        //其他故障
                        textView1.setText("其他故障");
                        break;
                }

                textView2.setText(response.getStoreName());
                textView3.setText(response.getCreateTime());
                switch (model.getStatus()) {
                    case "1":
                        //待处理
                        textView4.setText("待处理");
                        textView4.setTextColor(getResources().getColor(R.color.black3));
                        break;
                    case "2":
                        //处理中
                        textView4.setText("处理中");
                        textView4.setTextColor(getResources().getColor(R.color.black3));
                        break;
                    case "3":
                        //完成
                        textView4.setText("完成");
                        textView4.setTextColor(getResources().getColor(R.color.green));
                        break;
                }

                list_info.clear();
                list_info.add(new KeyValueModel("工单ID", response.getId()));
                list_info.add(new KeyValueModel("门店名称", response.getStoreName()));
                switch (model.getType()) {//类型 1-设备工单,2-订单工单,3-任务工单,4-其它,、
                    case "1":
                        //设备故障
                        list_info.add(new KeyValueModel("工单类型", "设备故障"));
                        break;
                    case "2":
                        //订单故障
                        list_info.add(new KeyValueModel("工单类型", "订单故障"));
                        break;
                    case "3":
                        //任务工单
                        list_info.add(new KeyValueModel("工单类型", "任务工单"));
                        break;
                    case "4":
                        //其他故障
                        list_info.add(new KeyValueModel("工单类型", "其他故障"));
                        break;
                }
                list_info.add(new KeyValueModel("工单原因", response.getFailureReason()));
//                list_info.add(new KeyValueModel("反馈渠道", response.get));
                list_info.add(new KeyValueModel("工单创建人", response.getCreateName()));
                list_info.add(new KeyValueModel("创建人类型", response.getUserTypeName()));
//                list_info.add(new KeyValueModel("所属城市", response.get));
                list_info.add(new KeyValueModel("创建时间", response.getCreateTime()));
                list_info.add(new KeyValueModel("工单内容", response.getRemark()));
                mAdapter_info = new CommonAdapter<KeyValueModel>
                        (WorkListDetailActivity.this, R.layout.item_keyvalue, list_info) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_info.setAdapter(mAdapter_info);
                Glide.with(WorkListDetailActivity.this)
                        .load(response.getImages())
                        .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(WorkListDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_info);//加载图片

                /**
                 * 处理记录
                 */
                showEmptyPage();
                if (response.getDealList() != null) {
                    list_shenhe = response.getDealList();
                    if (list_shenhe.size() > 0) {
                        showContentPage();
                        mAdapter_shenhe = new CommonAdapter<WorkListDetailModel.DealListBean>
                                (WorkListDetailActivity.this, R.layout.item_contractdetail_shenhe, list_shenhe) {
                            @Override
                            protected void convert(ViewHolder holder, WorkListDetailModel.DealListBean model, int position) {
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
                                if (model.getImages() != null) {
                                    String[] strArr = model.getImages().split(",");//拆分
                                    for (String s : strArr) {
                                        list_img.add(s);
                                    }
                                }
                            /*for (String s : model.getImage()) {
                                list_img.add(s);
                            }*/
                                RecyclerView rv = holder.getView(R.id.rv);
                                LinearLayoutManager llm1 = new LinearLayoutManager(WorkListDetailActivity.this);
                                llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                rv.setLayoutManager(llm1);
                                CommonAdapter<String> ca = new CommonAdapter<String>
                                        (WorkListDetailActivity.this, R.layout.item_img_28_28, list_img) {
                                    @Override
                                    protected void convert(ViewHolder holder, String model, int position) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(WorkListDetailActivity.this).load(model)
                                                .centerCrop()
                                                .apply(RequestOptions.bitmapTransform(new
                                                        RoundedCorners(CommonUtil.dip2px(WorkListDetailActivity.this, 7))))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                    }
                                };
                                ca.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(WorkListDetailActivity.this, list_img, i);
                                        photoShowDialog.show();
                                    }

                                    @Override
                                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        return false;
                                    }
                                });
                                rv.setAdapter(ca);

                                ImageView iv_head = holder.getView(R.id.iv_head);
                                Glide.with(WorkListDetailActivity.this)
                                        .load(model.getAvatar())
                                        .fitCenter()
                                        .apply(RequestOptions.bitmapTransform(new
                                                RoundedCorners(CommonUtil.dip2px(WorkListDetailActivity.this, 3))))
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.headimg)//加载失败
                                        .into(iv_head);//加载图片
                                holder.setText(R.id.tv_name, model.getName());
                                holder.setText(R.id.tv_time, model.getCreateTime());

                                holder.setText(R.id.tv_content, model.getRemark());
                                //状态图片
                                ImageView iv_zhuangtai = holder.getView(R.id.iv_zhuangtai);
                                TextView tv_type = holder.getView(R.id.tv_type);
                                if (model.getStatus() != null) {
                                    switch (model.getStatus()) {
                                        case "1":
                                            tv_type.setText("上报");
                                            tv_type.setTextColor(getResources().getColor(R.color.red));
                                            iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_3);
                                            break;
                                        case "2":
                                            tv_type.setText("已完成");
                                            tv_type.setTextColor(getResources().getColor(R.color.green));
                                            iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_2);
                                            break;
                                        case "3":
                                            tv_type.setText("指派");
                                            tv_type.setTextColor(getResources().getColor(R.color.black3));
                                            iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_1);
                                            break;
                                        default:
                                            tv_type.setText("处理中");
                                            tv_type.setTextColor(getResources().getColor(R.color.black3));
                                            iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_1);
                                            break;
                                    }
                                }
                            }
                        };
                        rv_shenhe.setAdapter(mAdapter_shenhe);
                    }
                }


            }
        });
    }

    private void requestJieShou(Map<String, String> params, String id) {
        OkhttpUtil.okHttpPost(URLs.WorkList_JieShou + id, params, headerMap, new CallBackUtil<String>() {
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
                hideProgress();
//                model = response;
                myToast("接手工单成功");
                requestServer();

            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("工单详情");
    }

    private void changeUI() {
        switch (type) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);

                ll_shopinfo.setVisibility(View.VISIBLE);
                ll_shenhe.setVisibility(View.GONE);

                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);

                ll_shopinfo.setVisibility(View.GONE);
                ll_shenhe.setVisibility(View.VISIBLE);

                break;

        }
    }

}
