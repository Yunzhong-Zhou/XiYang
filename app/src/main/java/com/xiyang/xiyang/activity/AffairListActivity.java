package com.xiyang.xiyang.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.AffairListModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.popupwindow.PopupWindow_List4;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.SearchDialog;
import com.xiyang.xiyang.utils.SelectCityDialog;
import com.xiyang.xiyang.utils.SelectIndustryDialog;
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
 * 事务列表
 */
public class AffairListActivity extends BaseActivity {
    int type = 1;//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
    private RecyclerView recyclerView;
    List<AffairListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<AffairListModel.RecordsBean> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    private TextView textView1, textView2, textView3, textView4;
    private View view1, view2, view3, view4;
    private LinearLayout pop_view;
    int page = 1;

    List<String> list_status = new ArrayList<>();
    String status = "", keyword = "", instudyId = "", provinceId = "", cityId = "", areaId = "";
    int i1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshoplist);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                params.put("current", page + "");
                params.put("pageSize", "10");
                params.put("status", status);
                params.put("keyword", keyword);
                params.put("instudyId", instudyId);
                params.put("provinceId", provinceId);
                params.put("cityId", cityId);
                params.put("areaId", areaId);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("current", page + "");
                params.put("pageSize", "10");
                params.put("status", status);
                params.put("keyword", keyword);
                params.put("instudyId", instudyId);
                params.put("provinceId", provinceId);
                params.put("cityId", cityId);
                params.put("areaId", areaId);
                requestListMore(params);
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout4 = findViewByID_My(R.id.linearLayout4);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);

        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        view4 = findViewByID_My(R.id.view4);

        pop_view = findViewByID_My(R.id.pop_view);
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 1);

        //签约状态 1正常2待签约3待审核4签约成功5签约失败
        list_status.clear();
        list_status.add("全部");
        list_status.add("正常");
        list_status.add("待签约");
        list_status.add("待审核");
        list_status.add("签约成功");
        list_status.add("签约失败");
    }

    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.AffairList, GsonUtils.toJson(params), headerMap, new CallBackUtil<AffairListModel>() {
            @Override
            public AffairListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(AffairListModel response) {
                showContentPage();
                hideProgress();
                list = response.getRecords();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<AffairListModel.RecordsBean>
                            (AffairListActivity.this, R.layout.item_affairlist, list) {
                        @Override
                        protected void convert(ViewHolder holder, AffairListModel.RecordsBean model, int position) {
                            holder.setText(R.id.tv_name, model.getName());//标题
                            holder.setText(R.id.tv_shop, model.getTypeTitle());
                            holder.setText(R.id.tv_addr, model.getDeviceNum() + "台");

                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(AffairListActivity.this)
                                    .load(model.getImage())
//                                .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(AffairListActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片

                            TextView tv_type = holder.getView(R.id.tv_num);
                            tv_type.setText(model.getStatusTitle());
                            switch (model.getStatus()) {//1:待处理; 2:处理中; 3:通过; 4:驳回;
                                /*case "0":
                                    //待处理
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    break;
                                case "2":
                                    //处理中
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    break;*/
                                case "3":
                                    //已通过
                                    tv_type.setTextColor(getResources().getColor(R.color.green));
                                    break;
                                case "4":
                                    //驳回
                                    tv_type.setTextColor(getResources().getColor(R.color.red));
                                    break;
                                default:
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    break;

                            }

                            /*ImageView imageView2 = holder.getView(R.id.imageView2);
                            if (model.getStatus().equals("1")) {
                                //待签约
                                imageView2.setImageResource(R.mipmap.bg_daiqianyue);
                            } else {
                                imageView2.setImageResource(R.mipmap.bg_yiqianyue);
                            }*/
                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    if (requestCode == Constant.SELECT_SHOP) {
//                                        Intent resultIntent = new Intent();
//                                        Bundle bundle = new Bundle();
//                                        bundle.putString("shopId", model.getId());
//                                        bundle.putString("shopName", model.getName());
//                                        resultIntent.putExtras(bundle);
//                                        MyShopListActivity.this.setResult(RESULT_OK, resultIntent);
//                                        finish();
//                                    } else {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    bundle.putString("apply_Type", model.getType()+"");
                                    CommonUtil.gotoActivityWithData(AffairListActivity.this, AffairDetailActivity.class, bundle, false);
//                                    }

                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });

    }

    private void requestListMore(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.AffairList, params, headerMap, new CallBackUtil<AffairListModel>() {
            @Override
            public AffairListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(AffairListModel response) {
                showContentPage();
                onHttpResult();
                List<AffairListModel.RecordsBean> list1 = new ArrayList<>();
                list1 = response.getRecords();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (v.getId()) {
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView3.setTextColor(getResources().getColor(R.color.black3));
                textView4.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                textView3.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new PopupWindow_List4(AffairListActivity.this, 0, list_status, i1, pop_view) {
                    @Override
                    public void onReturn(String keys, int item) {
                        textView1.setText(keys);

                        i1 = item;
                        if (item == 0) status = "";
                        else status = item + "";

                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView3.setTextColor(getResources().getColor(R.color.black3));
                textView4.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable1, null);
                textView3.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);

                new SelectCityDialog(AffairListActivity.this, dialog) {
                    @Override
                    public void onCallBack(String province, String city, String district, String pId, String cId, String aId) {
                        textView2.setText(district);

                        provinceId = pId;
                        cityId = cId;
                        areaId = aId;
                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout3:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView3.setTextColor(getResources().getColor(R.color.green));
                textView4.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                textView3.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new SelectIndustryDialog(AffairListActivity.this, dialog) {
                    @Override
                    public void onCallBack(String string_hangye, String id) {
                        textView3.setText(string_hangye);
                        instudyId = id;

                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout4:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView3.setTextColor(getResources().getColor(R.color.black3));
                textView4.setTextColor(getResources().getColor(R.color.green));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                textView3.setCompoundDrawables(null, null, drawable2, null);
                new SearchDialog(AffairListActivity.this, dialog) {
                    @Override
                    public void onFailure(String keys) {
                        keyword = keys;
                        requestServer();
                    }
                };
                break;
        }
    }

    @Override
    protected void updateView() {

        Bundle bundle = new Bundle();
        switch (type) {//1、主机、2、4g模块 3、过滤网  4、回收  5、换绑
            case 1:
                titleView.setTitle("申领主机列表");
                break;
            case 2:
                //4G模块
                titleView.setTitle("申领4G模块列表");
                titleView.showRightTextview("申领4G模块", v -> {
                    bundle.putInt("type", type);
                    CommonUtil.gotoActivityWithData(AffairListActivity.this, AddDeviceActivity.class, bundle);
                });
                break;
            case 3:
                //过滤网
                titleView.setTitle("申领过滤网列表");
                titleView.showRightTextview("申领过滤网", v -> {
                    bundle.putInt("type", type);
                    CommonUtil.gotoActivityWithData(AffairListActivity.this, AddDeviceActivity.class, bundle);
                });
                break;
            case 4:
                titleView.setTitle("设备回收列表");
                titleView.showRightTextview("回收设备", v -> {
                    bundle.putInt("item_hetong", 2);
                    CommonUtil.gotoActivityWithData(AffairListActivity.this, AddContractActivity.class, bundle);
                });
                break;
            case 5:
                titleView.setTitle("设备换绑列表");
                titleView.showRightTextview("设备换绑", v -> {
                    bundle.putInt("item_hetong", 3);
                    CommonUtil.gotoActivityWithData(AffairListActivity.this, AddContractActivity.class, bundle);
//                    CommonUtil.gotoActivity(AffairListActivity.this, ChangeTieDeviceActivity.class);
                });
                break;
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        params.put("current", page + "");
        params.put("pageSize", "10");
        params.put("status", status);
        params.put("keyword", keyword);
        params.put("instudyId", instudyId);
        params.put("provinceId", provinceId);
        params.put("cityId", cityId);
        params.put("areaId", areaId);
        requestList(params);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }
}
