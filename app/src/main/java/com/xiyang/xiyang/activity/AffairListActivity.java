package com.xiyang.xiyang.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.adapter.Pop_ListAdapter;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.AffairListModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.view.FixedPopupWindow;
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
    int type = 1;//1、主机、2、4g模块 3、过滤网 4、换绑 5、回收
    private RecyclerView recyclerView;
    List<AffairListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<AffairListModel.RecordsBean> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2, linearLayout3;
    private TextView textView1, textView2, textView3;
    private View view1, view2, view3;
    private LinearLayout pop_view;
    int page = 1;
    String area = "", status = "", keyword = "", industry = "";
    int i1 = 0;
    int i2 = 0;

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
                params.put("type", type + "");
                params.put("area", area);
                params.put("keyword", keyword);
                params.put("industry", industry);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("current", page + "");
                params.put("pageSize", "10");
                params.put("status", status);
                params.put("type", type + "");
                params.put("area", area);
                params.put("keyword", keyword);
                params.put("industry", industry);
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
        type = getIntent().getIntExtra("type", 1);
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
                            holder.setText(R.id.tv_num, model.getStatusTitle());//money
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
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
                showPopupWindow1(pop_view);
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
                showPopupWindow2(pop_view);
                break;
        }
    }

    @Override
    protected void updateView() {

        Bundle bundle = new Bundle();
        switch (type) {
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
                titleView.setTitle("设备换绑列表");
                titleView.showRightTextview("设备换绑", v -> {
                    bundle.putInt("item_hetong", 3);
                    CommonUtil.gotoActivityWithData(AffairListActivity.this, AddContractActivity.class, bundle);
//                    CommonUtil.gotoActivity(AffairListActivity.this, ChangeTieDeviceActivity.class);
                });
                break;
            case 5:
                titleView.setTitle("设备回收列表");
                titleView.showRightTextview("回收设备", v -> {
                    bundle.putInt("item_hetong", 2);
                    CommonUtil.gotoActivityWithData(AffairListActivity.this, AddContractActivity.class, bundle);
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
        params.put("type", type + "");
        params.put("area", area);
        params.put("keyword", keyword);
        params.put("industry", industry);
        requestList(params);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }

    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(AffairListActivity.this).inflate(
                R.layout.pop_list2, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView1);
        contentView.findViewById(R.id.pop_listView2).setVisibility(View.INVISIBLE);
        final List<String> list = new ArrayList<String>();
        list.add(getString(R.string.app_type_jiangxu));
        list.add(getString(R.string.app_type_shengxu));
        final Pop_ListAdapter adapter = new Pop_ListAdapter(AffairListActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;
                /*if (i == 0) {
                    sort = "desc";
                } else {
                    sort = "asc";
                }*/
//                textView1.setText(list.get(i));
                requestServer();
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparentblack2));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }

    private void showPopupWindow2(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(AffairListActivity.this).inflate(
                R.layout.pop_list2, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        contentView.findViewById(R.id.pop_listView1).setVisibility(View.INVISIBLE);
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView2);
        final List<String> list = new ArrayList<String>();
        list.add(getString(R.string.app_type_quanbu));
        list.add(getString(R.string.app_type_daishenhe));
        list.add(getString(R.string.app_type_yitongguo));
        list.add(getString(R.string.app_type_weitongguo));

        final Pop_ListAdapter adapter = new Pop_ListAdapter(AffairListActivity.this, list);
        adapter.setSelectItem(i2);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                i2 = i;
                adapter.notifyDataSetChanged();

                if (i == 0) {
                    status = "";
                } else {
                    status = i + "";
                }
//                textView2.setText(list.get(i));
                requestServer();
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparentblack1));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }
}
