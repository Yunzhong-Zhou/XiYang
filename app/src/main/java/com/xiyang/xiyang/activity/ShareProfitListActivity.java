package com.xiyang.xiyang.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.adapter.Pop_ListAdapter;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.ShareProfitListModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.view.FixedPopupWindow;
import com.zhy.adapter.recyclerview.CommonAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 分润记录
 */
public class ShareProfitListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<ShareProfitListModel> list = new ArrayList<>();
    CommonAdapter<ShareProfitListModel> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2,linearLayout3;
    private TextView textView1, textView2,textView3;
    private View view1, view2,view3;
    private LinearLayout pop_view;
    int page = 1;
    String moneySort = "", status = "",startTime="",keyWord="";
    int i1 = 0;
    int i2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareprofitlist);
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
                params.put("page",page+"");
                params.put("pageSize","10");
                params.put("keyWord",keyWord);
                params.put("moneySort",moneySort);
                params.put("timeSort",startTime);
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("page",page+"");
                params.put("pageSize","10");
                params.put("keyWord",keyWord);
                params.put("moneySort",moneySort);
                params.put("timeSort",startTime);
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
        requestServer();//获取数据
    }

    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.ShareProfitList, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                showContentPage();
                hideProgress();
                JSONObject jObj;
                /*try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list = JSON.parseArray(jsonArray.toString(), MyTakeCashModel.class);
                    if (list.size() == 0) {
                        showEmptyPage();//空数据
                    } else {
                        mAdapter = new CommonAdapter<MyTakeCashModel>
                                (MyTakeCashActivity.this, R.layout.item_shareprofit, list) {
                            @Override
                            protected void convert(ViewHolder holder, MyTakeCashModel model, int position) {
                                holder.setText(R.id.textView1,getString(R.string.qianbao_h6));//标题
                                holder.setText(R.id.textView2, model.getCreated_at());//时间
                                holder.setText(R.id.textView3, "-"+model.getMoney());//money
                                holder.setText(R.id.textView4, model.getStatus_title());//状态
                            }
                        };
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("id", list.get(position).getId());
                                CommonUtil.gotoActivityWithData(MyTakeCashActivity.this, TakeCashDetailActivity.class, bundle1, false);
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
            }
        });

    }

    private void requestListMore(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.ShareProfitList, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
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
            public void onResponse(String response) {
//                showContentPage();
                hideProgress();
                /*JSONObject jObj;
                List<MyTakeCashModel> list1 = new ArrayList<MyTakeCashModel>();
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list1 = JSON.parseArray(jsonArray.toString(), MyTakeCashModel.class);
                    if (list1.size() == 0) {
                        myToast(getString(R.string.app_nomore));
                        page--;
                    } else {
                        list.addAll(list1);
                        mAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
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
        titleView.setTitle("分润记录");
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        params.put("page",page+"");
        params.put("pageSize","10");
        params.put("keyWord",keyWord);
        params.put("moneySort",moneySort);
        params.put("timeSort",startTime);
        requestList(params);
    }


    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(ShareProfitListActivity.this).inflate(
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
        final Pop_ListAdapter adapter = new Pop_ListAdapter(ShareProfitListActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;
                /*if (i == 0) {
                    moneySort = "desc";
                } else {
                    moneySort = "asc";
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
        final View contentView = LayoutInflater.from(ShareProfitListActivity.this).inflate(
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

        final Pop_ListAdapter adapter = new Pop_ListAdapter(ShareProfitListActivity.this, list);
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
