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
import com.xiyang.xiyang.model.PersonnelDetailModel;
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
 * 人事详情
 */
public class PersonnelDetailActivity extends BaseActivity {
    String id = "";
    int type = 1, PersonnelType = 1;
    TextView tv_tab1, tv_tab2;
    LinearLayout ll_tab1, ll_tab2;
    View view1, view2;

    PersonnelDetailModel model;
    TextView tv_name, tv_shop, tv_num, tv_addr;
    ImageView imageView1;
    /**
     * 基本信息
     */
    LinearLayout ll_info;
    RecyclerView rv_info;
    List<KeyValueModel> list_info = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_info;

    /**
     * 审批记录
     */
    LinearLayout ll_shenhe;
    TextView tv_shenpi;
    RecyclerView rv_shenhe;
    List<PersonnelDetailModel.ListBean> list_shenhe = new ArrayList<>();
    CommonAdapter<PersonnelDetailModel.ListBean> mAdapter_shenhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personneldetail);
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
//        params.put("id", id);
                switch (PersonnelType) {
                    case 1:
                        //调整上级
                    case 2:
                        //调整市场
                    case 3:
                        //升职降职
                        request(params, URLs.PersonnelDetail + id);
                        break;
                    case 4:
                        //采购审批
                        request(params, URLs.CaiGouDetail + id);
                        break;
                }

            }

            @Override
            public void onLoadmore() {

            }
        });
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        tv_tab1 = findViewByID_My(R.id.tv_tab1);
        tv_tab2 = findViewByID_My(R.id.tv_tab2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

        tv_name = findViewByID_My(R.id.tv_name);
        tv_shop = findViewByID_My(R.id.tv_shop);
        tv_num = findViewByID_My(R.id.tv_num);
        tv_addr = findViewByID_My(R.id.tv_addr);
        imageView1 = findViewByID_My(R.id.imageView1);

        /**
         * 基本信息
         */
        ll_info = findViewByID_My(R.id.ll_info);
        rv_info = findViewByID_My(R.id.rv_info);
        rv_info.setLayoutManager(new LinearLayoutManager(this));
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
            case R.id.tv_shenpi:
                //立即审批
                switch (PersonnelType) {
                    case 1:
                        //调整上级
                        bundle.putString("id", model.getId());
                        bundle.putString("type", "");
                        bundle.putString("type_shenhe", "1");
                        bundle.putString("num", model.getSn());
                        break;
                    case 2:
                        //调整市场
                        bundle.putString("id", model.getId());
                        bundle.putString("type", "");
                        bundle.putString("type_shenhe", "2");
                        bundle.putString("num", model.getSn());
                        break;
                    case 3:
                        //升职降职
                        bundle.putString("id", model.getId());
                        bundle.putString("type", "");
                        bundle.putString("type_shenhe", "3");
                        bundle.putString("num", model.getSn());
                        break;
                    case 4:
                        //采购审批
                        bundle.putString("id", model.getPurchaseApplyLogId());
                        bundle.putString("type", "");
                        bundle.putString("type_shenhe", "4");//采购审核
                        bundle.putString("num", model.getPurchaseQuantity());
                        break;
                }
                CommonUtil.gotoActivityWithData(PersonnelDetailActivity.this, ApproveContractActivity.class, bundle);
                break;
            case R.id.ll_tab1:
                //基本信息
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //审批记录
                type = 2;
                changeUI();
                break;
        }
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        PersonnelType = getIntent().getIntExtra("PersonnelType", 1);
        switch (PersonnelType) {
            case 1:
                //调整上级
                titleView.setTitle("调整上级详情");
                break;
            case 2:
                //调整市场
                titleView.setTitle("调整市场详情");
                break;
            case 3:
                //升职降职
                titleView.setTitle("升职降职详情");
                break;
            case 4:
                //采购审批
                titleView.setTitle("采购审批详情");
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
        switch (PersonnelType) {
            case 1:
                //调整上级
            case 2:
                //调整市场
            case 3:
                //升职降职
                request(params, URLs.PersonnelDetail + id);
                break;
            case 4:
                //采购审批
                request(params, URLs.CaiGouDetail + id);
                break;
        }

    }

    private void request(HashMap<String, String> params, String url) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<PersonnelDetailModel>() {
            @Override
            public PersonnelDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(PersonnelDetailModel response) {
                hideProgress();
                model = response;
                /**
                 * 基本信息
                 */
                list_info.clear();
                if (PersonnelType == 4) {
                    /**
                     * 采购审批
                     */
                    tv_name.setText(response.getPurchaseApplicantName());
                    tv_shop.setText(response.getPurchaseApplicantPhoneNumber());
                    tv_addr.setText("职位：" + response.getPurchaseApplicantOrganCode());
                    Glide.with(PersonnelDetailActivity.this)
                            .load(model.getPurchaseApplicantAvatar())
//                                .fitCenter()
                            .apply(RequestOptions.bitmapTransform(new
                                    RoundedCorners(CommonUtil.dip2px(PersonnelDetailActivity.this, 10))))
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.zanwutupian)//加载失败
                            .into(imageView1);//加载图片

                    list_info.add(new KeyValueModel("ID", response.getSn()));
                    list_info.add(new KeyValueModel("申请人", response.getPurchaseApplicantName()));
                    list_info.add(new KeyValueModel("提货仓库", response.getWarehouseName()));
                    list_info.add(new KeyValueModel("申请数量", response.getPurchaseQuantity() + "台"));
                    list_info.add(new KeyValueModel("提货时间", response.getDeliveryTime()));
                    list_info.add(new KeyValueModel("申请时间", response.getCreateTime()));

                    /**
                     * 审核合同
                     */
                    list_shenhe = response.getList();
                    if (list_shenhe != null && list_shenhe.size() > 0) {
                        showContentPage();
                        mAdapter_shenhe = new CommonAdapter<PersonnelDetailModel.ListBean>
                                (PersonnelDetailActivity.this, R.layout.item_contractdetail_shenhe, list_shenhe) {
                            @Override
                            protected void convert(ViewHolder holder, PersonnelDetailModel.ListBean model, int position) {
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
                                if (model.getApprovalImages() != null) {
                                    String[] strArr = model.getApprovalImages().split(",");//拆分
                                    for (String s : strArr) {
                                        list_img.add(s);
                                    }
                                }

                                RecyclerView rv = holder.getView(R.id.rv);
                                LinearLayoutManager llm1 = new LinearLayoutManager(PersonnelDetailActivity.this);
                                llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                rv.setLayoutManager(llm1);
                                CommonAdapter<String> ca = new CommonAdapter<String>
                                        (PersonnelDetailActivity.this, R.layout.item_img_28_28, list_img) {
                                    @Override
                                    protected void convert(ViewHolder holder, String model, int position) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(PersonnelDetailActivity.this).load(model)
                                                .centerCrop()
                                                .apply(RequestOptions.bitmapTransform(new
                                                        RoundedCorners(CommonUtil.dip2px(PersonnelDetailActivity.this, 7))))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                    }
                                };
                                ca.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(PersonnelDetailActivity.this, list_img, i);
                                        photoShowDialog.show();
                                    }

                                    @Override
                                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        return false;
                                    }
                                });
                                rv.setAdapter(ca);

                                ImageView iv_head = holder.getView(R.id.iv_head);
                                Glide.with(PersonnelDetailActivity.this)
                                        .load(model.getApprovalAvatar())
                                        .fitCenter()
                                        .apply(RequestOptions.bitmapTransform(new
                                                RoundedCorners(CommonUtil.dip2px(PersonnelDetailActivity.this, 3))))
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.headimg)//加载失败
                                        .into(iv_head);//加载图片
                                holder.setText(R.id.tv_name, model.getApprovalName());
                                holder.setText(R.id.tv_time, model.getApprovalTime());
                                holder.setText(R.id.tv_content, model.getApprovalRemark());
                                //状态图片
                                ImageView iv_zhuangtai = holder.getView(R.id.iv_zhuangtai);
                                TextView tv_type = holder.getView(R.id.tv_type);
                                switch (model.getApprovalStatus()) {//1待处理2已处理3驳回
                                    case "1":
                                        tv_type.setText("待审核");
                                        tv_type.setTextColor(getResources().getColor(R.color.black3));
                                        iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_1);
                                        break;
                                    case "2":
                                        tv_type.setText("已完成");
                                        tv_type.setTextColor(getResources().getColor(R.color.green));
                                        iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_2);
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
                } else {
                    tv_name.setText(response.getAdminName());
                    tv_shop.setText(response.getAdminPhone());
                    tv_addr.setText("职位：" + response.getAdminOrganCode());
                    Glide.with(PersonnelDetailActivity.this)
                            .load(model.getAdminAvatar())
//                                .fitCenter()
                            .apply(RequestOptions.bitmapTransform(new
                                    RoundedCorners(CommonUtil.dip2px(PersonnelDetailActivity.this, 10))))
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.zanwutupian)//加载失败
                            .into(imageView1);//加载图片
                    list_info.add(new KeyValueModel("ID", response.getSn()));
                    list_info.add(new KeyValueModel("是否跨区", response.getCrossRegional()));
                    list_info.add(new KeyValueModel("调整人", response.getAdminName()));
                    list_info.add(new KeyValueModel("职位", response.getAdminOrganCode()));

                    switch (PersonnelType) {
                        case 1:
                            //调整上级
                            list_info.add(new KeyValueModel("调整前上级", response.getOldParentName()));
                            list_info.add(new KeyValueModel("调整后上级", response.getNewParentName()));
                            break;
                        case 2:
                            //调整市场
                            String oldcity = "";
                            for (PersonnelDetailModel.OldRegionsBean bean : model.getOldRegions()) {
                                oldcity = oldcity + bean.getName() + "、";
                            }
                            String newcity = "";
                            for (PersonnelDetailModel.OldRegionsBean bean : model.getNewRegions()) {
                                newcity = newcity + bean.getName() + "、";
                            }
                            if (!oldcity.equals("")) {
                                oldcity = oldcity.substring(0, oldcity.length() - 1);
                            }
                            if (!newcity.equals("")) {
                                newcity = newcity.substring(0, newcity.length() - 1);
                            }

                            list_info.add(new KeyValueModel("调整前省市", oldcity));
                            list_info.add(new KeyValueModel("调整后省市", newcity));
                            break;
                        case 3:
                            //升职降职
                            list_info.add(new KeyValueModel("调整前职位", response.getOldOrganCode()));
                            list_info.add(new KeyValueModel("调整后职位", response.getNewOrganCode()));
                            break;
                        case 4:

                            break;
                    }
                }

                tv_shenpi.setVisibility(View.GONE);
                switch (model.getStatus()) {//1:待审核; 2:未通过; 3:已通过;
                    case "1":
                        tv_num.setText("处理中");
                        tv_num.setTextColor(getResources().getColor(R.color.black3));

                        tv_shenpi.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        tv_num.setText("驳回");
                        tv_num.setTextColor(getResources().getColor(R.color.red));
                        break;
                    case "3":
                        tv_num.setText("已完成");
                        tv_num.setTextColor(getResources().getColor(R.color.green));
                        break;
                }

                mAdapter_info = new CommonAdapter<KeyValueModel>
                        (PersonnelDetailActivity.this, R.layout.item_keyvalue, list_info) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_info.setAdapter(mAdapter_info);

                /**
                 * 审核合同
                 */
                list_shenhe = response.getList();
                if (list_shenhe != null && list_shenhe.size() > 0) {
                    showContentPage();
                    mAdapter_shenhe = new CommonAdapter<PersonnelDetailModel.ListBean>
                            (PersonnelDetailActivity.this, R.layout.item_contractdetail_shenhe, list_shenhe) {
                        @Override
                        protected void convert(ViewHolder holder, PersonnelDetailModel.ListBean model, int position) {
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

                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(PersonnelDetailActivity.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (PersonnelDetailActivity.this, R.layout.item_img_28_28, list_img) {
                                @Override
                                protected void convert(ViewHolder holder, String model, int position) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(PersonnelDetailActivity.this).load(model)
                                            .centerCrop()
                                            .apply(RequestOptions.bitmapTransform(new
                                                    RoundedCorners(CommonUtil.dip2px(PersonnelDetailActivity.this, 7))))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                }
                            };
                            ca.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(PersonnelDetailActivity.this, list_img, i);
                                    photoShowDialog.show();
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv.setAdapter(ca);

                            ImageView iv_head = holder.getView(R.id.iv_head);
                            Glide.with(PersonnelDetailActivity.this)
                                    .load(model.getAdminAvatar())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(PersonnelDetailActivity.this, 3))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(iv_head);//加载图片
                            holder.setText(R.id.tv_name, model.getAdminName());
                            holder.setText(R.id.tv_time, model.getUpdateTime());
                            holder.setText(R.id.tv_content, model.getRemark());
                            //状态图片
                            ImageView iv_zhuangtai = holder.getView(R.id.iv_zhuangtai);
                            TextView tv_type = holder.getView(R.id.tv_type);
                            if (model.getStatus()!=null){
                                switch (model.getStatus()) {//1待处理2已处理3驳回
                                    case "1":
                                        tv_type.setText("待审核");
                                        tv_type.setTextColor(getResources().getColor(R.color.black3));
                                        iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_1);
                                        break;
                                    case "2":
                                        tv_type.setText("已完成");
                                        tv_type.setTextColor(getResources().getColor(R.color.green));
                                        iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_2);
                                        break;
                                    case "3":
                                        tv_type.setText("驳回");
                                        tv_type.setTextColor(getResources().getColor(R.color.red));
                                        iv_zhuangtai.setImageResource(R.mipmap.ic_shenhe_3);
                                        break;
                                }
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
    }

    private void changeUI() {
        switch (type) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);

                ll_info.setVisibility(View.VISIBLE);
                ll_shenhe.setVisibility(View.GONE);

                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);

                ll_info.setVisibility(View.GONE);
                ll_shenhe.setVisibility(View.VISIBLE);

                break;

        }
    }

}
