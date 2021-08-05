package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.model.VisitDetailModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
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
 * 拜访详情
 */
public class VisitDetailActivity extends BaseActivity {
    String id = "";
    VisitDetailModel model;
    private RecyclerView rv_info;
    List<KeyValueModel> list_info = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_info;
    ImageView iv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitdetail);
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
        rv_info = findViewByID_My(R.id.rv_info);
        rv_info.setLayoutManager(new LinearLayoutManager(this));
        iv_info = findViewByID_My(R.id.iv_info);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_info:
                if (model.getImages() !=null && !model.getImages().equals("")){
                    PhotoShowDialog_1 photoShowDialog = new PhotoShowDialog_1(VisitDetailActivity.this,
                            model.getImages());
                    photoShowDialog.show();
                }

                break;
        }
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
//        params.put("id", id);
        params.clear();
        request(params);
    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.VisitDetail + id, params, headerMap, new CallBackUtil<VisitDetailModel>() {
            @Override
            public VisitDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(VisitDetailModel response) {
                hideProgress();
                model = response;
                list_info.clear();
                if (response.getType().equals("3")) {
                    //陌生拜访
                    list_info.add(new KeyValueModel("拜访门店", response.getStoreName()));
                    list_info.add(new KeyValueModel("拜访人员", response.getContractName()));
                    list_info.add(new KeyValueModel("联系电话", response.getContractMobile()));
                    list_info.add(new KeyValueModel("拜访时间", response.getVisitTime()));
                    list_info.add(new KeyValueModel("门店地址", response.getAddress()));
                    list_info.add(new KeyValueModel("拜访方式", response.getTypeStr()));
                    list_info.add(new KeyValueModel("是否意向", response.getIsIntention()));
                    list_info.add(new KeyValueModel("补充说明", response.getRemark()));
                } else {
                    list_info.add(new KeyValueModel("记录ID", response.getId()));
                    list_info.add(new KeyValueModel("拜访门店", response.getStoreName()));
                    list_info.add(new KeyValueModel("拜访方式", response.getTypeStr()));
                    list_info.add(new KeyValueModel("营业情况", response.getBusinessStr()));
                    list_info.add(new KeyValueModel("拜访联系人", response.getContractName()));
                    list_info.add(new KeyValueModel("是否竞对", response.getIsAdverStr()));
                    list_info.add(new KeyValueModel("拜访时间", response.getVisitTime()));
                    list_info.add(new KeyValueModel("拜访人", response.getBdAdminName()));
                    list_info.add(new KeyValueModel("合作风险上报", response.getReportStatusStr()));
                    list_info.add(new KeyValueModel("拜访原因", response.getReasonStr()));
                    list_info.add(new KeyValueModel("拜访反馈", response.getFeedbackStr()));
                    list_info.add(new KeyValueModel("补充说明", response.getRemark()));
                }

                list_info.add(new KeyValueModel("拜访照片", ""));
                mAdapter_info = new CommonAdapter<KeyValueModel>
                        (VisitDetailActivity.this, R.layout.item_keyvalue, list_info) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_info.setAdapter(mAdapter_info);
                Glide.with(VisitDetailActivity.this)
                        .load(response.getImages())
                        .fitCenter()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(VisitDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_info);//加载图片
            }
        });
    }


    @Override
    protected void updateView() {
        titleView.setTitle("拜访详情");
    }
}
