package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.MyCityModel;
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
    TextView tv_quanbu;
    private RecyclerView rv1, rv3;
    List<MyCityModel.ListBean> list1 = new ArrayList<>();
    CommonAdapter<MyCityModel.ListBean> mAdapter1;
    int page = 1, item1 = -1, item2 = -1;
    List<MyCityModel.ListBean> list2 = new ArrayList<>();
    CommonAdapter<MyCityModel.ListBean> mAdapter2;

    List<MyCityModel.ListBean> list3 = new ArrayList<>();
    CommonAdapter<MyCityModel.ListBean> mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycity);
    }

    @Override
    protected void initView() {
        tv_quanbu = findViewByID_My(R.id.tv_quanbu);

        rv1 = findViewByID_My(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv3 = findViewByID_My(R.id.rv3);
        rv3.setLayoutManager(new LinearLayoutManager(this));

        rv1.setVisibility(View.VISIBLE);
        switch (localUserInfo.getUserJob()) {
            case "RM":
                titleView.setTitle("我的城市");
                break;
            case "CM":
                titleView.setTitle("我的市区");
                break;
            case "BDM":
                titleView.setTitle("我的区域");
                rv1.setVisibility(View.GONE);
                break;
        }

        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                switch (localUserInfo.getUserJob()) {
                    case "RM":
                        requestCityMore(params, URLs.MyCity_RM);
                        break;
                    case "CM":
                        requestCityMore(params, URLs.MyCity_RM);
                        break;
                    case "BDM":
                        requestCityMore(params, URLs.MyCity_RM);
                        break;
                }
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
//                requestCityMore(params);
            }
        });

    }

    @Override
    protected void initData() {
        showMyCity3();

        requestServer();//获取数据
    }

    /**
     * 第一级数据
     *
     * @param params
     * @param url
     */
    private void requestCity1(Map<String, String> params, String url) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<MyCityModel>() {
            @Override
            public MyCityModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyCityModel response) {
                hideProgress();
                list1 = response.getList();
                showMyCity1();
                //显示第三级数据
                if (item1 == -1) {
                    list3 = list1;
                    showMyCity3();
                }
            }
        });

    }

    //显示第1级数据
    private void showMyCity1() {
        mAdapter1 = new CommonAdapter<MyCityModel.ListBean>
                (MyCityActivity.this, R.layout.item_mycity1, list1) {
            @Override
            protected void convert(ViewHolder holder, MyCityModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.tv);
                tv.setText(model.getName());
                LinearLayout ll = holder.getView(R.id.ll);
                ImageView iv = holder.getView(R.id.iv);
                RecyclerView rv = holder.getView(R.id.rv);
                rv.setLayoutManager(new LinearLayoutManager(MyCityActivity.this));
                if (item1 == position) {
                    tv_quanbu.setTextColor(getResources().getColor(R.color.black2));
                    tv_quanbu.setBackgroundResource(R.color.transparent);

                    ll.setBackgroundResource(R.color.green_3);
                    tv.setTextColor(getResources().getColor(R.color.green));

                    //获取下一级
                    showLoadingPage();
                    if (localUserInfo.getUserJob().equals("RM")) {
                        rv.setVisibility(View.VISIBLE);
                        iv.setImageResource(R.mipmap.ic_jiantou_down);
                        requestCity2(params, URLs.MyCity_RM, rv);
                    } else {
                        rv.setVisibility(View.GONE);
                        iv.setImageResource(R.mipmap.ic_xuanzhong);
                        requestCity3(params, URLs.MyCity_RM);
                    }


                } else {
                    ll.setBackgroundResource(R.color.transparent);
                    tv.setTextColor(getResources().getColor(R.color.black2));

                    rv.setVisibility(View.GONE);
                    if (localUserInfo.getUserJob().equals("RM")) {
                        iv.setImageResource(R.mipmap.ic_jiantou_right);
                    } else {
                        iv.setImageResource(R.color.transparent);
                    }

                }

                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item1 = position;
                        item2 = -1;
                        mAdapter1.notifyDataSetChanged();
                    }
                });

            }
        };
        rv1.setAdapter(mAdapter1);

    }

    /**
     * 第二级数据
     *
     * @param params
     * @param url
     */
    private void requestCity2(Map<String, String> params, String url, RecyclerView rv) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<MyCityModel>() {
            @Override
            public MyCityModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyCityModel response) {
                hideProgress();
                list2 = response.getList();
                showMyCity2(rv);
                //显示第三级数据
                list3 = list2;
                showMyCity3();
            }
        });

    }

    //显示第2级数据
    private void showMyCity2(RecyclerView rv) {
        mAdapter2 = new CommonAdapter<MyCityModel.ListBean>
                (MyCityActivity.this, R.layout.item_mycity2, list2) {
            @Override
            protected void convert(ViewHolder holder, MyCityModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.tv);
                tv.setText(model.getName());
                LinearLayout ll = holder.getView(R.id.ll);
                ImageView iv = holder.getView(R.id.iv);
                if (item2 == position) {
                    tv.setTextColor(getResources().getColor(R.color.green));
                    iv.setVisibility(View.VISIBLE);

                    //获取下一级
                    showLoadingPage();
                    requestCity3(params, URLs.MyCity_RM);

                } else {
                    tv.setTextColor(getResources().getColor(R.color.black2));
                    iv.setVisibility(View.GONE);
                }

                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item2 = position;
                        mAdapter2.notifyDataSetChanged();
                    }
                });
            }
        };
        rv.setAdapter(mAdapter2);

    }

    /**
     * 第三级数据
     *
     * @param params
     * @param url
     */
    private void requestCity3(Map<String, String> params, String url) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<MyCityModel>() {
            @Override
            public MyCityModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyCityModel response) {
                hideProgress();
                list3 = response.getList();
                //显示第三级数据
                showMyCity3();
            }
        });

    }

    //显示第3级数据
    private void showMyCity3() {
        if (list3.size() == 0) {
            showEmptyPage();//空数据
        } else {
            showContentPage();
            mAdapter3 = new CommonAdapter<MyCityModel.ListBean>
                    (MyCityActivity.this, R.layout.item_mycity3, list3) {
                @Override
                protected void convert(ViewHolder holder, MyCityModel.ListBean model, int position) {
                    if (model.getId() != null && !model.getId().equals("")) {
                        holder.setText(R.id.tv_city, model.getName());
                        TextView tv_name = holder.getView(R.id.tv_name);
                        switch (localUserInfo.getUserJob()) {
                            case "RM":
                                tv_name.setText("CM:" + model.getCmName());
                                break;
                            case "CM":
                                tv_name.setText("BDM:" + model.getCmName());
                                break;
                        }
                        TextView tv_daizhipai = holder.getView(R.id.tv_daizhipai);//是否指派
                        tv_daizhipai.setVisibility(View.GONE);

                        holder.setText(R.id.tv_shop, model.getMerchantNum());
                        holder.setText(R.id.tv_store, model.getStoreNum());
                        holder.setText(R.id.tv_device, model.getDeviceNum());
                    }
                }
            };
            rv3.setAdapter(mAdapter3);
        }

    }



    private void requestCityMore(Map<String, String> params, String url) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<String>() {
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
            case R.id.tv_quanbu:
                //全部
                item1 = -1;
                tv_quanbu.setTextColor(getResources().getColor(R.color.green));
                tv_quanbu.setBackgroundResource(R.color.green_3);

                list3 = list1;
                showMyCity3();

                mAdapter1.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        switch (localUserInfo.getUserJob()) {
            case "RM":
                requestCity1(params, URLs.MyCity_RM);
                break;
            case "CM":
                requestCity1(params, URLs.MyCity_RM);
                break;
            case "BDM":
                requestCity1(params, URLs.MyCity_RM);
                break;
        }

    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }
}
