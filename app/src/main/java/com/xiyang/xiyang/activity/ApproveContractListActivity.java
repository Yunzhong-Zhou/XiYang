package com.xiyang.xiyang.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.ApproveContractListModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.popupwindow.PopupWindow_List3;
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
 * Created by Mr.Z on 2021/3/28.
 * 审批合同列表
 */
public class ApproveContractListActivity extends BaseActivity {
    int item = 1;
    private RecyclerView recyclerView;
    List<ApproveContractListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<ApproveContractListModel.RecordsBean> mAdapter;

    TextView tv_tab1, tv_tab2, tv_tab3;
    LinearLayout ll_tab1, ll_tab2, ll_tab3;
    View view1, view2, view3;
    private LinearLayout pop_view;
    List<String> list_type = new ArrayList<>();
    List<String> list_status = new ArrayList<>();
    List<String> list_time = new ArrayList<>();

    int page = 1;
    String status = "", title = "", startTime = "", endTIme = "", type = "", keyword = "";
    int i1 = 0, i2 = 0, i3 = 0;
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvecontractlist);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
    }

    @Override
    protected void initView() {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(ApproveContractListActivity.this), 0, 0);
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("pageSize", "10");
                params.put("status", status);//处理结果 1-已提交，2-进行中，3-完成
                params.put("title", title);//搜索说明
                params.put("startTime", startTime);
                params.put("endTIme", endTIme);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("pageSize", "10");
                params.put("status", status);//处理结果 1-已提交，2-进行中，3-完成
                params.put("title", title);//搜索说明
                params.put("startTime", startTime);
                params.put("endTIme", endTIme);
                requestListMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ApproveContractListActivity.this));

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
        pop_view = findViewByID_My(R.id.pop_view);

        editText1 = findViewByID_My(R.id.editText1);
    }

    @Override
    protected void initData() {
        list_type.add("全部");
        list_type.add("签约合同");
        list_type.add("新增合同");
        list_type.add("回收合同");
        list_type.add("换绑合同");
        list_type.add("修改合同");
        list_type.add("续签合同");
        list_type.add("取消合同");
        list_type.add("调价合同");

        //签约状态 1正常2待签约3待审核4签约成功5签约失败
        list_status.clear();
        list_status.add("全部");
        list_status.add("正常");
        list_status.add("待签约");
        list_status.add("待审核");
        list_status.add("签约成功");
        list_status.add("签约失败");

        list_time.add(getString(R.string.app_type_jiangxu));
        list_time.add(getString(R.string.app_type_shengxu));
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("pageSize", "10");
        params.put("status", status);//处理结果 1-已提交，2-进行中，3-完成
        params.put("title", title);//搜索说明
        params.put("startTime", startTime);
        params.put("endTIme", endTIme);
        requestList(params);
    }

    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.ApproveContractList, params, headerMap, new CallBackUtil<ApproveContractListModel>() {
            @Override
            public ApproveContractListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(ApproveContractListModel response) {
                showContentPage();
                hideProgress();
                list = response.getRecords();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<ApproveContractListModel.RecordsBean>
                            (ApproveContractListActivity.this, R.layout.item_approvecontractlist, list) {
                        @Override
                        protected void convert(ViewHolder holder, ApproveContractListModel.RecordsBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(ApproveContractListActivity.this)
                                    .load(model.getImage())
//                                .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(ApproveContractListActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name, model.getName());//标题
                            holder.setText(R.id.tv_shop, "《" + model.getTypeTitle() + "》");
                            holder.setText(R.id.tv_time, model.getCreateTime());
                            TextView tv_type = holder.getView(R.id.tv_type);
                            tv_type.setText(model.getStatusTitle());
                            switch (model.getStatus()) {//0:需要审核  1:审核通过; 2:驳回; 3:通过;
                                case "2":
                                    //驳回
                                    tv_type.setTextColor(getResources().getColor(R.color.red));
                                    break;
                                case "1":
                                case "3":
                                    //已通过
                                    tv_type.setTextColor(getResources().getColor(R.color.green));
                                    break;

                                default:
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    break;

                            }
                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    bundle.putString("typeStr", model.getType());
                                    CommonUtil.gotoActivityWithData(ApproveContractListActivity.this, ApproveDetailActivity.class, bundle, false);
                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                }
                MainActivity.isOver = true;
            }
        });

    }

    private void requestListMore(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.ApproveContractList, params, headerMap, new CallBackUtil<ApproveContractListModel>() {
            @Override
            public ApproveContractListModel onParseResponse(Call call, Response response) {
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
            public void onResponse(ApproveContractListModel response) {
//                showContentPage();
                hideProgress();
                List<ApproveContractListModel.RecordsBean> list1 = new ArrayList<>();
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
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.ll_tab1:
                //待安装
                item = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //待回收
                item = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //待换绑
                item = 3;
                changeUI();
                break;
            case R.id.tv_search:
                //搜索
                //关闭软键盘
                KeyboardUtils.hideSoftInput(editText1);
                if (!editText1.getText().toString().trim().equals("")) {
                    keyword = editText1.getText().toString().trim();
                    requestServer();
                } else {
                    myToast("请输入需要搜索的内容");
                }
                break;
        }
    }

    private void changeUI() {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.ic_down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (item) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.white));
                tv_tab2.setTextColor(getResources().getColor(R.color.white));
                tv_tab3.setTextColor(getResources().getColor(R.color.white));
//                tv_tab1.setCompoundDrawables(null, null, drawable1, null);
//                tv_tab2.setCompoundDrawables(null, null, drawable2, null);
//                tv_tab3.setCompoundDrawables(null, null, drawable2, null);

//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new PopupWindow_List3(ApproveContractListActivity.this, 0, list_type, i1, pop_view) {
                    @Override
                    public void onFailure(String keys, int item) {
                        i1 = item;
                        type = item + "";
                        requestServer();
                    }
                };
                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.white));
                tv_tab2.setTextColor(getResources().getColor(R.color.white));
                tv_tab3.setTextColor(getResources().getColor(R.color.white));
                tv_tab1.setCompoundDrawables(null, null, drawable2, null);
                tv_tab2.setCompoundDrawables(null, null, drawable1, null);
                tv_tab3.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                view3.setVisibility(View.INVISIBLE);
                new PopupWindow_List3(ApproveContractListActivity.this, 1, list_status, i2, pop_view) {
                    @Override
                    public void onFailure(String keys, int item) {
                        i2 = item;
                        status = item + "";
                        requestServer();
                    }
                };
                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.white));
                tv_tab2.setTextColor(getResources().getColor(R.color.white));
                tv_tab3.setTextColor(getResources().getColor(R.color.white));
                tv_tab1.setCompoundDrawables(null, null, drawable2, null);
                tv_tab2.setCompoundDrawables(null, null, drawable2, null);
                tv_tab3.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.INVISIBLE);
//                view3.setVisibility(View.VISIBLE);
                new PopupWindow_List3(ApproveContractListActivity.this, 2, list_time, i2, pop_view) {
                    @Override
                    public void onFailure(String keys, int item) {
                        i3 = item;
                        status = item + "";
                        requestServer();
                    }
                };
                break;

        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
