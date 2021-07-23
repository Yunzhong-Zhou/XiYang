package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.SubordinateModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.Constant;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 选择员工
 */
public class SelectStaffActivity extends BaseActivity {
    String role = "", userId = "";
    int requestCode = 0, item = -1;
    private RecyclerView recyclerView;
    List<SubordinateModel> list = new ArrayList<>();
    CommonAdapter<SubordinateModel> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmycity);
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        findViewByID_My(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String postionIds = "";
                String postionCitys = "";
                for (SelectMyCityModel.ListBean bean : list) {
                    if (bean.isIsxuanzhong()) {
                        postionIds += bean.getId() + ",";
                        postionCitys += bean.getName() + ",";
                    }
                }
                if (!postionIds.equals("")) {
                    postionIds = postionIds.substring(0, postionIds.length() - 1);
                    postionCitys = postionCitys.substring(0, postionCitys.length() - 1);
                }*/
                if (requestCode == Constant.SELECT_STAFF && item >= 0) {
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("staffId", list.get(item).getUserId());
                    bundle.putString("staffName", list.get(item).getName());
//                    bundle.putStringArrayList("imgList", (ArrayList<String>) response.getList());
                    bundle.putString("ShangJiId", list.get(item).getParentUserId());
                    bundle.putString("ShangJiName", list.get(item).getParentUserName());
                    resultIntent.putExtras(bundle);
                    SelectStaffActivity.this.setResult(RESULT_OK, resultIntent);
                    finish();
                } else myToast("请选择员工");

            }
        });
    }

    @Override
    protected void initData() {
        role = getIntent().getStringExtra("role");
        requestCode = getIntent().getIntExtra("requestCode", 0);
        userId = getIntent().getStringExtra("userId");
        if (userId == null) userId = "";
        requestServer();//获取数据
    }

    private void requestStaff(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.Subordinate, GsonUtils.toJson(params), headerMap, new CallBackUtil<List<SubordinateModel>>() {
            @Override
            public List<SubordinateModel> onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(List<SubordinateModel> response) {
                showContentPage();
                hideProgress();
                list = response;
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<SubordinateModel>
                            (SelectStaffActivity.this, R.layout.item_fragment1_m, list) {
                        @Override
                        protected void convert(ViewHolder holder, SubordinateModel model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(SelectStaffActivity.this)
                                    .load(model.getAvatar())
                                    .fitCenter()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name, model.getName());

                            holder.setText(R.id.tv1, model.getStatisticInfo().getMerchantNumber());
                            holder.setText(R.id.tv2, model.getStatisticInfo().getStoreNumber());
                            holder.setText(R.id.tv3, model.getStatisticInfo().getDeviceNumber());
                            holder.setText(R.id.tv4, "￥" + model.getStatisticInfo().getMoney());

                            TextView tv_bdm = holder.getView(R.id.tv_bdm);
                            TextView tv_bd = holder.getView(R.id.tv_bd);
                            tv_bdm.setVisibility(View.VISIBLE);
                            tv_bd.setVisibility(View.VISIBLE);
                            switch (localUserInfo.getUserJob()) {
                                case "CM":
                                    tv_bdm.setText("BDM:" + model.getStatisticInfo().getBdmNumber());
                                    tv_bd.setText("BD:" + model.getStatisticInfo().getBdNumber());
                                    break;
                                case "BDM":
                                    tv_bdm.setVisibility(View.GONE);
                                    tv_bd.setText("BD:" + model.getStatisticInfo().getBdNumber());
                                    break;
                                case "BD":
                                    tv_bdm.setVisibility(View.GONE);
                                    tv_bd.setVisibility(View.GONE);
                                    break;
                            }
                            //负责城市
                            FlowLayout flowLayout = holder.getView(R.id.flowLayout);
                            FlowLayoutAdapter<SubordinateModel.RegionsBean> flowLayoutAdapter = new FlowLayoutAdapter<SubordinateModel.RegionsBean>(model.getRegions()) {
                                @Override
                                public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position, SubordinateModel.RegionsBean bean) {
                                    holder.setText(R.id.tv, bean.getNameX());
                                }

                                @Override
                                public void onItemClick(int position, SubordinateModel.RegionsBean bean) {

                                }

                                @Override
                                public int getItemLayoutID(int position, SubordinateModel.RegionsBean bean) {
                                    return R.layout.item_flowlayout_yuanjiaobiankuang;
                                }
                            };
                            flowLayout.setAdapter(flowLayoutAdapter);

                            LinearLayout linearLayout = holder.getView(R.id.linearLayout);
                            ImageView imageView = holder.getView(R.id.imageView);
                            if (position == item) {
                                linearLayout.setBackgroundResource(R.drawable.yuanjiao_8_huisetouming);
                                imageView.setVisibility(View.VISIBLE);
                            } else {
                                linearLayout.setBackgroundResource(R.drawable.yuanjiao_8_baise);
                                imageView.setVisibility(View.GONE);
                            }


                            linearLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    /*if (list.get(position).isXuanZhong())
                                        list.get(position).setXuanZhong(false);
                                    else list.get(position).setXuanZhong(true);*/
                                    item = position;

                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择" + role.toUpperCase());
        titleView.showRightTxtBtn("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestCode == Constant.SELECT_STAFF && item >= 0) {
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    //选择的用户
                    bundle.putString("staffId", list.get(item).getUserId());
                    bundle.putString("staffName", list.get(item).getName());
//                    bundle.putStringArrayList("imgList", (ArrayList<String>) response.getList());
                    //所属上级
                    bundle.putString("ShangJiId", list.get(item).getParentUserId());
                    bundle.putString("ShangJiName", list.get(item).getParentUserName());

                    //管理城市
                   /* String cityIds = "";
                    String Citys = "";
                    for (SubordinateModel.RegionsBean bean : list.get(item).getRegions()) {
                        cityIds += bean.getId() + ",";
                        Citys += bean.getNameX() + ",";
                    }
                    if (!cityIds.equals("")) {
                        cityIds = cityIds.substring(0, cityIds.length() - 1);
                        Citys = Citys.substring(0, Citys.length() - 1);
                    }
                    bundle.putString("cityIds", cityIds);
                    bundle.putString("citys", Citys);*/

                    String[] cityIds = new String[list.get(item).getRegions().size()];
                    String[] Citys = new String[list.get(item).getRegions().size()];
                    for (int i = 0; i < list.get(item).getRegions().size(); i++) {
                        cityIds[i]= list.get(item).getRegions().get(i).getId();
                        Citys[i]= list.get(item).getRegions().get(i).getNameX();
                    }
                    bundle.putStringArray("cityIds", cityIds);
                    bundle.putStringArray("citys", Citys);

//                    bundle.putParcelableArrayList("Citys", (ArrayList<? extends Parcelable>) list.get(item).getRegions());

                    resultIntent.putExtras(bundle);
                    SelectStaffActivity.this.setResult(RESULT_OK, resultIntent);
                    finish();
                } else myToast("请选择" + role.toUpperCase());
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        params.clear();
        params.put("userId", userId);
        params.put("code", role);
        requestStaff(params);
    }

}
