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
import com.xiyang.xiyang.utils.CommonUtil;
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
    StaffDetailModel model;
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
                params.clear();
//                params.put("id", id);
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
    public void onClick(View v) {
        super.onClick(v);
        Bundle bundle = new Bundle();
        bundle.putString("job", model.getOrganName());
        switch (v.getId()) {
            case R.id.tv_shangji:
                //调整上级
                CommonUtil.gotoActivityWithData(StaffDetailActivity.this, AdjustSuperiorActivity.class, bundle);
                break;
            case R.id.tv_shichang:
                //调整市场
                CommonUtil.gotoActivityWithData(StaffDetailActivity.this, AdjustMarketActivity.class, bundle);
                break;
            case R.id.tv_gangwei:
                //升职降职
               /* if (localUserInfo.getUserJob().equals("BDM"))
                    myToast("调整岗位需CM及以上");
                else*/
                    CommonUtil.gotoActivityWithData(StaffDetailActivity.this, AdjustJobActivity.class, bundle);
                break;
        }
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
        params.clear();
//        params.put("id", id);
        request(params);
    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.StaffDetail + id, params, headerMap, new CallBackUtil<StaffDetailModel>() {
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
                model = response;
                /**
                 * 统计信息
                 */
                list_tongji.clear();
                tv_shichang.setVisibility(View.VISIBLE);
                switch (response.getOrganName()) {
                    case "CM":
                        list_tongji.add(new KeyValueModel("BDM", response.getStatisticInfo().getBdmNumber()));
                        list_tongji.add(new KeyValueModel("BD", response.getStatisticInfo().getBdNumber()));
                        break;
                    case "BDM":
                        list_tongji.add(new KeyValueModel("BD", response.getStatisticInfo().getBdNumber()));
                        list_tongji.add(new KeyValueModel("总区域", response.getStatisticInfo().getAreaNumber()));
                        break;
                    case "BD":
                        tv_shichang.setVisibility(View.GONE);
                        break;
                }
                list_tongji.add(new KeyValueModel("总营收", "￥" + response.getAvailableMoney()));
                list_tongji.add(new KeyValueModel("商户数", response.getStatisticInfo().getMerchantNumber()));
                list_tongji.add(new KeyValueModel("门店数", response.getStatisticInfo().getStoreNumber()));
                list_tongji.add(new KeyValueModel("设备数", response.getStatisticInfo().getDeviceNumber()));

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
                        .load(response.getAvatar())
                        .fitCenter()
//                        .apply(RequestOptions.bitmapTransform(new
//                                RoundedCorners(CommonUtil.dip2px(StaffDetailActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片

                list_info.clear();
                list_info.add(new KeyValueModel("姓名", response.getName()));
                list_info.add(new KeyValueModel("帐号", response.getUsername()));
                list_info.add(new KeyValueModel("联系手机号", response.getPhone()));
                list_info.add(new KeyValueModel("性别", response.getGender()));
                String city = "";
                for (StaffDetailModel.RegionsBean bean : model.getRegions()) {
                    city = city + bean.getNameX()+ "、";
                }
                if (!city.equals("")) {
                    city = city.substring(0, city.length() - 1);
                }
                switch (response.getOrganName()) {
                    case "CM":
                        list_info.add(new KeyValueModel("负责城市", city));
                        list_info.add(new KeyValueModel("加入时间", response.getJoinTime()));
                        list_info.add(new KeyValueModel("所属分公司", response.getChildCompany()));
                        list_info.add(new KeyValueModel("已用指标", response.getUsedIndicators()));
                        list_info.add(new KeyValueModel("可用指标", response.getAvailableIndicators()));

                        break;
                    case "BDM":
                        list_info.add(new KeyValueModel("负责区县", city));
                        list_info.add(new KeyValueModel("加入时间", response.getJoinTime()));
                        list_info.add(new KeyValueModel("所属分公司", response.getChildCompany()));
                        break;
                    case "BD":
                        list_info.add(new KeyValueModel("所属区域", city));
                        list_info.add(new KeyValueModel("加入时间", response.getJoinTime()));
                        list_info.add(new KeyValueModel("所属分公司", response.getChildCompany()));
                        break;
                }

                list_info.add(new KeyValueModel("领货仓库", response.getWarehouseName()));
//                list_info.add(new KeyValueModel("职业状态", response.get));
//                list_info.add(new KeyValueModel("类型", response.get));
//                list_info.add(new KeyValueModel("等级", response.get));
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
