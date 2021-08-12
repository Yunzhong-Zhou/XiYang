package com.xiyang.xiyang.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.LossDeviceListModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.popupwindow.PopupWindow_List2;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.SelectCityDialog;
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
 * 报失设备列表
 */
public class LossDeviceListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<LossDeviceListModel> list = new ArrayList<>();
    CommonAdapter<LossDeviceListModel> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;
    private LinearLayout pop_view;
    int page = 1;

    List<String> list_status = new ArrayList<>();
    String sort = "", status = "", provinceId = "", cityId = "", areaId = "", storeId = "", instudy = "", keyword = "";
    int i1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lossdevicelist);
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
                params.put("page", page + "");
                params.put("size", "10");
                params.put("keyword", keyword);
                params.put("status", status);
                params.put("storeId", storeId);
                params.put("industryId", instudy);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("page", page + "");
                params.put("size", "10");
                params.put("keyword", keyword);
                params.put("status", status);
                params.put("storeId", storeId);
                params.put("industryId", instudy);
                requestListMore(params);
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        pop_view = findViewByID_My(R.id.pop_view);
    }

    @Override
    protected void initData() {
        storeId = getIntent().getStringExtra("storeId");
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
        OkhttpUtil.okHttpPostJson(URLs.LossDeviceList, GsonUtils.toJson(params), headerMap, new CallBackUtil<List<LossDeviceListModel>>() {
            @Override
            public List<LossDeviceListModel> onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(List<LossDeviceListModel> response) {
                showContentPage();
                hideProgress();
                list = response;
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<LossDeviceListModel>
                            (LossDeviceListActivity.this, R.layout.item_affairedetail_anzhuang, list) {
                        @Override
                        protected void convert(ViewHolder holder, LossDeviceListModel model, int position) {
                            holder.setText(R.id.textView1, model.getFullName());
                            holder.setText(R.id.textView2, model.getHostName());
                            holder.setText(R.id.textView4, model.getLossTime() + "");

                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("deviceName", model.getDeviceId());
//                                    CommonUtil.gotoActivityWithData(AffairDetailActivity.this, DeviceDetailActivity.class, bundle, false);
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
        OkhttpUtil.okHttpPostJson(URLs.LossDeviceList, GsonUtils.toJson(params), headerMap, new CallBackUtil<List<LossDeviceListModel>>() {
            @Override
            public List<LossDeviceListModel> onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                showErrorPage();
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(List<LossDeviceListModel> response) {
//                showContentPage();
                hideProgress();

                List<LossDeviceListModel> list1 = new ArrayList<>();
                list1 = response;
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
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
                new SelectCityDialog(LossDeviceListActivity.this, dialog) {
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
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new PopupWindow_List2(LossDeviceListActivity.this, 1, list_status, i1, pop_view) {
                    @Override
                    public void onFailure(String keys, int item) {
                        i1 = item;
                        status = item + "";
                        requestServer();
                    }
                };
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("报失列表");
        titleView.showRightTextview("新建报失", true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(LossDeviceListActivity.this, AddLossDeviceActivity.class);
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        params.put("page", page + "");
        params.put("size", "10");
        params.put("keyword", keyword);
        params.put("status", status);
        params.put("storeId", storeId);
        params.put("industryId", instudy);
        requestList(params);
    }
}
