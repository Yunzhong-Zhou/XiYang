package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.InformationModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
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
 * Created by zyz on 2017/9/16.
 * 资讯
 */
public class InformationActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<InformationModel.ListBean> list = new ArrayList<>();
    CommonAdapter<InformationModel.ListBean> mAdapter;
    int page = 1, channel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                params.put("page", page + "");
                params.put("pageSize", "10");
                params.put("channel", channel + "");
                requestList(params);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.put("page", page + "");
                params.put("pageSize", "10");
                params.put("channel", channel + "");
                requestListMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void requestList(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.Information, GsonUtils.toJson(params), headerMap, new CallBackUtil<InformationModel>() {
            @Override
            public InformationModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(InformationModel response) {
                showContentPage();
                hideProgress();
                list = response.getList();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<InformationModel.ListBean>
                            (InformationActivity.this, R.layout.item_information, list) {
                        @Override
                        protected void convert(ViewHolder holder, InformationModel.ListBean model, int position) {
                            holder.setText(R.id.textView1, model.getTitle());
                            holder.setText(R.id.textView2, model.getCreateTime());

                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(InformationActivity.this)
                                    .load(model.getImage())
//                                .fitCenter()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(AffairListActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片

                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("url", model.getContent());
                                    CommonUtil.gotoActivityWithData(InformationActivity.this, WebHTMLActivity.class, bundle, false);
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
        OkhttpUtil.okHttpPostJson(URLs.Information, GsonUtils.toJson(params), headerMap, new CallBackUtil<InformationModel>() {
            @Override
            public InformationModel onParseResponse(Call call, Response response) {
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
            public void onResponse(InformationModel response) {
//                showContentPage();
                hideProgress();
                List<InformationModel.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
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
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        showProgress(true, getString(R.string.app_loading));
        page = 1;
        params.put("page", page + "");
        params.put("pageSize", "10");
        params.put("channel", channel + "");
        requestList(params);

    }

    @Override
    protected void updateView() {
        titleView.setTitle("系统公告");
    }
}
