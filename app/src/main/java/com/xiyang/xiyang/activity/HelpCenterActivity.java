package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.HelpModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
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
 * Created by Mr.Z on 2020/12/10.
 * 帮助中心
 */
public class HelpCenterActivity extends BaseActivity {
    HelpModel model;
    RecyclerView recyclerView;
    List<HelpModel.ProblemVoListBean> list = new ArrayList<>();
    CommonAdapter<HelpModel.ProblemVoListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpcenter);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
//                RequestHelpList("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {
                //加载更多
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_shangbao:
                bundle.putInt("type", 0);
                CommonUtil.gotoActivityWithData(HelpCenterActivity.this, AddWorkListActivity.class, bundle);
                //拨打电话
                /*showToast("确认拨打 " + model.getLandline_number() + " 吗？", "确认", "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                //创建打电话的意图
                                Intent intent = new Intent();
                                //设置拨打电话的动作
                                intent.setAction(Intent.ACTION_CALL);//直接拨出电话
//                               intent.setAction(Intent.ACTION_DIAL);//只调用拨号界面，不拨出电话
                                //设置拨打电话的号码
                                intent.setData(Uri.parse("tel:" + model.getLandline_number()));
                                //开启打电话的意图
                                startActivity(intent);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });*/


                break;
            case R.id.tv_kefu:
                //联系客服
                CommonUtil.gotoActivity(this, OnlineServiceActivity.class, false);
                break;
            case R.id.linearLayout1:
                //无法启动
                /*bundle.putInt("type", 1);
                CommonUtil.gotoActivityWithData(HelpCenterActivity.this, AddWorkListActivity.class, bundle);
                break;*/
            case R.id.linearLayout2:
                //无法关闭
               /* bundle.putInt("type", 1);
                CommonUtil.gotoActivityWithData(HelpCenterActivity.this, AddWorkListActivity.class, bundle);
                break;*/
            case R.id.linearLayout3:
                //设备断网
                bundle.putInt("type", 1);
                CommonUtil.gotoActivityWithData(HelpCenterActivity.this, AddWorkListActivity.class, bundle);
                break;
        }
    }

    @Override
    protected void initData() {
        requestServer();

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        //帮助列表
        RequestHelpList(params);
    }

    private void RequestHelpList(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Help, params, headerMap, new CallBackUtil<HelpModel>() {
            @Override
            public HelpModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(HelpModel response) {
                showContentPage();
                hideProgress();
                model = response;
//                textView1.setText(getString(R.string.onlineservice_h3) + response.getLandline_number());
                list = response.getProblemVoList();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<HelpModel.ProblemVoListBean>
                            (HelpCenterActivity.this, R.layout.item_help, list) {
                        @Override
                        protected void convert(ViewHolder holder, HelpModel.ProblemVoListBean model, int position) {
                            holder.setText(R.id.textView1, model.getName());
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            Bundle bundle = new Bundle();
                            bundle.putString("title", model.getProblemVoList().get(position).getName());
                            bundle.putString("url", model.getProblemVoList().get(position).getDescs());
                            CommonUtil.gotoActivityWithData(HelpCenterActivity.this, WebHTMLActivity.class, bundle, false);
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
    protected void updateView() {
        titleView.setTitle("帮助中心");
    }
}
