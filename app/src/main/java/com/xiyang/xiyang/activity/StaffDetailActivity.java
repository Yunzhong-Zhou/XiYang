package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.KeyValueModel;
import com.xiyang.xiyang.model.StaffDetailModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/4/4.
 * 员工详情
 */
public class StaffDetailActivity extends BaseActivity {
    String id = "";
    private RecyclerView rv_tongji;
    List<KeyValueModel> list_tongji = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_tongji;
    private RecyclerView rv_info;
    List<KeyValueModel> list_info = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_info;

    ImageView imageView1;
    TextView tv_shangji, tv_shichang, tv_gangwei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffdetail);
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
                params.put("id", id);
                request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        rv_tongji = findViewByID_My(R.id.rv_tongji);
        rv_tongji.setLayoutManager(new GridLayoutManager(StaffDetailActivity.this, 3));
        rv_info = findViewByID_My(R.id.rv_info);
        rv_info.setLayoutManager(new LinearLayoutManager(StaffDetailActivity.this));
        imageView1 = findViewByID_My(R.id.imageView1);
        tv_shangji = findViewByID_My(R.id.tv_shangji);
        tv_shichang = findViewByID_My(R.id.tv_shichang);
        tv_gangwei = findViewByID_My(R.id.tv_gangwei);

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.put("id", id);
        request(params);
    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.StaffDetail, params, headerMap, new CallBackUtil<StaffDetailModel>() {
            @Override
            public StaffDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showErrorPage();
                myToast(err);
            }

            @Override
            public void onResponse(StaffDetailModel response) {
                hideProgress();
//                model = response;
                /**
                 * 统计信息
                 */
                list_tongji.clear();
                switch (response.getRole()) {
                    case "cm":
                        list_tongji.add(new KeyValueModel("BDM", response.getBdmNum()));
                        list_tongji.add(new KeyValueModel("BD", response.getBdNum()));
                        break;
                    case "bdm":
                        list_tongji.add(new KeyValueModel("BD", response.getBdNum()));
                        list_tongji.add(new KeyValueModel("总区域", response.getStoreNum()));
                        break;
                    case "bd":

                        tv_shichang.setVisibility(View.GONE);
                        break;
                }
                list_tongji.add(new KeyValueModel("总营收", "￥" + response.getMoney()));
                list_tongji.add(new KeyValueModel("商户数", response.getMerchantNum()));
                list_tongji.add(new KeyValueModel("门店数", response.getStoreNum()));
                list_tongji.add(new KeyValueModel("设备数", response.getDeviceNum()));

                mAdapter_tongji = new CommonAdapter<KeyValueModel>
                        (StaffDetailActivity.this, R.layout.item_staffdetail, list_tongji) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv1, model.getValue());
                        holder.setText(R.id.tv2, model.getKey());
                    }
                };
                rv_tongji.setAdapter(mAdapter_tongji);
                /**
                 * 基本信息
                 */
                Glide.with(StaffDetailActivity.this)
                        .load(response.getHead())
                        .fitCenter()
//                        .apply(RequestOptions.bitmapTransform(new
//                                RoundedCorners(CommonUtil.dip2px(StaffDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片

                list_info.clear();
                list_info.add(new KeyValueModel("姓名", response.getName()));
                list_info.add(new KeyValueModel("帐号", response.getAccount()));
                list_info.add(new KeyValueModel("联系手机号", response.getMobile()));
                list_info.add(new KeyValueModel("性别", response.getSex()));
                switch (response.getRole()) {
                    case "cm":
                        list_info.add(new KeyValueModel("负责城市", response.getRegionName()));
                        list_info.add(new KeyValueModel("加入时间", response.getJoinAt()));
                        list_info.add(new KeyValueModel("所属分公司", response.getParentName()));
                        list_info.add(new KeyValueModel("已用指标", response.getJoinAt()));
                        list_info.add(new KeyValueModel("可用指标", response.getJoinAt()));

                        break;
                    case "bdm":
                        list_info.add(new KeyValueModel("负责区县", response.getRegionName()));
                        list_info.add(new KeyValueModel("加入时间", response.getJoinAt()));
                        list_info.add(new KeyValueModel("所属分公司", response.getParentName()));
                        break;
                    case "bd":
                        list_info.add(new KeyValueModel("所属区域", response.getRegionName()));
                        list_info.add(new KeyValueModel("加入时间", response.getJoinAt()));
                        list_info.add(new KeyValueModel("所属分公司", response.getParentName()));
                        break;
                }

                list_info.add(new KeyValueModel("领货仓库", response.getJoinAt()));
                list_info.add(new KeyValueModel("职业状态", response.getJoinAt()));
                list_info.add(new KeyValueModel("类型", response.getJoinAt()));
                list_info.add(new KeyValueModel("等级", response.getJoinAt()));
                list_info.add(new KeyValueModel("头像", ""));
                mAdapter_info = new CommonAdapter<KeyValueModel>
                        (StaffDetailActivity.this, R.layout.item_keyvalue, list_info) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_info.setAdapter(mAdapter_info);


            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("员工详情");
    }
}
