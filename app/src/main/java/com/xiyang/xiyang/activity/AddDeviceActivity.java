package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.AddDeviceModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

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
 * 申领设备
 */
public class AddDeviceActivity extends BaseActivity {
    int type = 1;//1、主机、2、4g模块 3、过滤网
    int page = 1;
    private RecyclerView recyclerView;
    List<AddDeviceModel> list = new ArrayList<>();
    CommonAdapter<AddDeviceModel> mAdapter;

    TextView tv_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevice);
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
                /*String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);*/
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                /*String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentListMore(string);*/
            }
        });
        tv_num = findViewByID_My(R.id.tv_num);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.textView1:
                //选择门店

                break;
            case R.id.tv_add:
                //申领
                Bundle bundle = new Bundle();
                bundle.putInt("type", type);
                CommonUtil.gotoActivityWithData(AddDeviceActivity.this, AddDeviceDetailActivity.class, bundle);
                break;
        }
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 1);
        requestServer();//获取数据

        for (int i = 0; i < 5; i++) {
            list.add(new AddDeviceModel());
        }
        mAdapter = new CommonAdapter<AddDeviceModel>
                (AddDeviceActivity.this, R.layout.item_adddevice, list) {
            @Override
            protected void convert(ViewHolder holder, AddDeviceModel model, int position) {

//                        holder.setText(R.id.tv1, model.getTitle());
//                        holder.setText(R.id.tv2, model.getProvince() + model.getCity() + model.getDistrict());
                //删除
                holder.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
               /* holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
//                    bundle.putString("id",model.getId());
                        CommonUtil.gotoActivityWithData(AddDeviceActivity.this, ContractDetailActivity.class, bundle, false);
                    }
                });*/
            }
        };
        recyclerView.setAdapter(mAdapter);
    }
    private void RequestList(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.MyIncome, params, headerMap, new CallBackUtil<String>() {
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
                MyLogger.i(">>>>>>>>>提现记录列表" + response);
                JSONObject jObj;
                /*try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list = JSON.parseArray(jsonArray.toString(), MyTakeCashModel.class);
                    if (list.size() == 0) {
                        showEmptyPage();//空数据
                    } else {
                        mAdapter = new CommonAdapter<MyTakeCashModel>
                                (MyTakeCashActivity.this, R.layout.item_addservice, list) {
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

    private void RequestListMore(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.MyIncome, params, headerMap, new CallBackUtil<String>() {
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
                MyLogger.i(">>>>>>>>>提现记录列表更多" + response);
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
    protected void updateView() {
        switch (type) {
            case 1:
                titleView.setTitle("申领净化器主机");
                break;
            case 2:
                titleView.setTitle("申领4G模块");
                break;
            case 3:
                titleView.setTitle("申领过滤网");
                break;
        }

    }
}
