package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.SelectMyCityModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.Constant;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
public class SelectMyCityActivity extends BaseActivity {
    int requestCode = 0;
    private RecyclerView recyclerView;
    List<SelectMyCityModel.ListBean> list = new ArrayList<>();
    CommonAdapter<SelectMyCityModel.ListBean> mAdapter;

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
                String postionIds = "";
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
                }
                if (requestCode == Constant.SELECT_MYCITY && !postionIds.equals("")) {
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("postionIds", postionIds);
                    bundle.putString("postionCitys", postionCitys);
                    resultIntent.putExtras(bundle);
                    SelectMyCityActivity.this.setResult(RESULT_OK, resultIntent);
                    finish();
                }else myToast("请选择城市");

            }
        });
    }

    @Override
    protected void initData() {
        requestCode = getIntent().getIntExtra("requestCode", 0);
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
                            (SelectMyCityActivity.this, R.layout.item_selectmycity, list) {
                        @Override
                        protected void convert(ViewHolder holder, SelectMyCityModel.ListBean model, int position) {
                            holder.setText(R.id.textView, model.getName());//标题
                            ImageView iv = holder.getView(R.id.imageView);
                            if (model.isIsxuanzhong()) {
                                iv.setImageResource(R.mipmap.ic_xuanzhong);
                            } else {
                                iv.setImageResource(R.color.transparent);
                            }
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            if (list.get(position).isIsxuanzhong())
                                list.get(position).setIsxuanzhong(false);
                            else list.get(position).setIsxuanzhong(true);

                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
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
        titleView.setTitle("选择城市");
        titleView.showRightTxtBtn("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postionIds = "";
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
                }
                if (requestCode == Constant.SELECT_MYCITY && !postionIds.equals("")) {
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("postionIds", postionIds);
                    bundle.putString("postionCitys", postionCitys);
                    resultIntent.putExtras(bundle);
                    SelectMyCityActivity.this.setResult(RESULT_OK, resultIntent);
                    finish();
                }else myToast("请选择城市");
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        requestCity(params);
    }

}
