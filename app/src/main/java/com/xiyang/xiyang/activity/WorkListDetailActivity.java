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
    String id = "";
    WorkListDetailModel model;

    ShadowLayout sl_tab;
    int type = 1;
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
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                params.put("id", id);
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
            case R.id.tv_liulan:
                //查看图片
                PhotoShowDialog_1 photoShowDialog = new PhotoShowDialog_1(WorkListDetailActivity.this,
                        URLs.IMGHOST + "");
                photoShowDialog.show();
                break;
            case R.id.tv_jieshou:
                //接手
                showToast("确认接手该工单吗？", "确定", "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                showProgress(true, getString(R.string.app_loading1));
                                params.clear();
                                params.put("id", id);
                                requestJieShou(params);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
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
        }
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        showProgress(true, getString(R.string.app_loading2));
        params.put("id", id);
        request(params);

    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.WorkListDetail, params, headerMap, new CallBackUtil<WorkListDetailModel>() {
            @Override
            public WorkListDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(WorkListDetailModel response) {
                hideProgress();
                model = response;
                /**
                 * 基本信息
                 */
                if (response.getBaseInfo().getStatus().equals("0")){//未接工单
                    sl_tab.setVisibility(View.GONE);
                    tv_jieshou.setVisibility(View.VISIBLE);
                } else{
                    sl_tab.setVisibility(View.VISIBLE);
                    tv_jieshou.setVisibility(View.GONE);
                }
                Glide.with(WorkListDetailActivity.this)
                        .load(response.getBaseInfo().getStoreCover())
                        .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(WorkListDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片
                textView1.setText(response.getBaseInfo().getStoreName());
                textView2.setText(response.getBaseInfo().getStoreAddres());
                textView3.setText(response.getBaseInfo().getCreatedAt());
                textView4.setText(response.getBaseInfo().getStatusTitle());

                list_info.clear();
                list_info.add(new KeyValueModel("工单ID", response.getBaseInfo().getId()));
                list_info.add(new KeyValueModel("门店名称", response.getBaseInfo().getStoreName()));
                list_info.add(new KeyValueModel("工单类型", response.getBaseInfo().getType()));
                list_info.add(new KeyValueModel("反馈渠道", response.getBaseInfo().getChannel()));
                list_info.add(new KeyValueModel("工单创建人", response.getBaseInfo().getCreatedUser()));
                list_info.add(new KeyValueModel("创建人类型", response.getBaseInfo().getCreatedUserRole()));
                list_info.add(new KeyValueModel("所属城市", response.getBaseInfo().getCreatedUserCity()));
                list_info.add(new KeyValueModel("创建时间", response.getBaseInfo().getCreatedAt()));
                list_info.add(new KeyValueModel("工单内容", response.getBaseInfo().getReamrk()));
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
                        .load(response.getBaseInfo().getStoreCover())
                        .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(WorkListDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_info);//加载图片

                /**
                 * 处理记录
                 */
                list_shenhe = response.getDealList();
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
                        //状态图片
                        ImageView iv_zhuangtai = holder.getView(R.id.iv_zhuangtai);
                        switch (model.getStatus()) {
                            case "1":
                                iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_2);
                                break;
                            case "2":
                                iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_1);
                                break;
                            case "3":
                                iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_3);
                                break;
                        }

                        //横向图片
                        List<String> list_img = new ArrayList<>();
                        for (String s : model.getImages()) {
                            list_img.add(s);
                        }
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

                        /*ImageView iv_head = holder.getView(R.id.iv_head);
                        Glide.with(WorkListDetailActivity.this)
                                .load(model.get)
                                .fitCenter()
                                .apply(RequestOptions.bitmapTransform(new
                                        RoundedCorners(CommonUtil.dip2px(WorkListDetailActivity.this, 3))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv_head);//加载图片
                        holder.setText(R.id.tv_name, model.get);*/
                        holder.setText(R.id.tv_time, model.getCreatedAt());
                        holder.setText(R.id.tv_type, model.getStatusTitle());
                        holder.setText(R.id.tv_content, model.getReamrk());


                    }
                };
                rv_shenhe.setAdapter(mAdapter_shenhe);
            }
        });
    }

    private void requestJieShou(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.WorkList_JieShou, params, headerMap, new CallBackUtil<String>() {
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
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);

                ll_shopinfo.setVisibility(View.GONE);
                ll_shenhe.setVisibility(View.VISIBLE);

                break;

        }
    }

}
