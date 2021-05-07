package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.SelectMyCityModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
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
 * 我的城市
 */
public class MyCityActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<SelectMyCityModel.ListBean> list = new ArrayList<>();
    CommonAdapter<SelectMyCityModel.ListBean> mAdapter;
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycity);
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
                requestCity(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                requestCityMore(params);
            }
        });

    }

    @Override
    protected void initData() {
        requestServer();//获取数据
    }

    private void requestCity(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.MyCity, params, headerMap, new CallBackUtil<SelectMyCityModel>() {
            @Override
            public SelectMyCityModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(SelectMyCityModel response) {
                showContentPage();
                hideProgress();
                list = response.getList();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<SelectMyCityModel.ListBean>
                            (MyCityActivity.this, R.layout.item_mycity, list) {
                        @Override
                        protected void convert(ViewHolder holder, SelectMyCityModel.ListBean model, int position) {
                            /*holder.setText(R.id.textView1,getString(R.string.qianbao_h6));//标题
                            holder.setText(R.id.textView2, model.getCreated_at());//时间
                            holder.setText(R.id.textView3, "-"+model.getMoney());//money
                            holder.setText(R.id.textView4, model.getStatus_title());//状态*/
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    /*mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
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
                    });*/
                }
            }
        });

    }

    private void requestCityMore(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.MyCity, params, headerMap, new CallBackUtil<String>() {
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
                onHttpResult();
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
        switch (v.getId()) {

        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("我的城市");
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        requestCity(params);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }
}
