package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.BankCardSettingModel;
import com.xiyang.xiyang.model.Fragment2Model;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.popupwindow.PhotoShowDialog;
import com.xiyang.xiyang.popupwindow.PhotoShowDialog_1;
import com.xiyang.xiyang.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/30.
 * 工单详情
 */
public class WorkListDetailActivity extends BaseActivity {
    int type = 1;
    TextView tv_tab1, tv_tab2;
    LinearLayout ll_tab1, ll_tab2;
    View view1, view2;

    /**
     * 商户信息
     */
    LinearLayout ll_shopinfo;

    /**
     * 审核合同
     */
    LinearLayout ll_shenhe;
    RecyclerView rv_shenhe;
    List<Fragment2Model> list_shenhe = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter_shenhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklistdetail);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                /*String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);*/
            }

            @Override
            public void onLoadmore() {

            }
        });
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        tv_tab1 = findViewByID_My(R.id.tv_tab1);
        tv_tab2 = findViewByID_My(R.id.tv_tab2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        /**
         *商户信息
         */
        ll_shopinfo = findViewByID_My(R.id.ll_shopinfo);

        /**
         * 门店信息
         */
        ll_shenhe = findViewByID_My(R.id.ll_shenhe);
        rv_shenhe = findViewByID_My(R.id.rv_shenhe);
        rv_shenhe.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_liulan:
                //查看图片
                PhotoShowDialog_1 photoShowDialog = new PhotoShowDialog_1(WorkListDetailActivity.this,
                        URLs.IMGHOST + "");
                photoShowDialog.show();
                break;
            case R.id.ll_tab1:
                //商户信息
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //合同信息
                type = 2;
                changeUI();
                break;
        }
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 5; i++) {
            list_shenhe.add(new Fragment2Model());
        }
        mAdapter_shenhe = new CommonAdapter<Fragment2Model>
                (WorkListDetailActivity.this, R.layout.item_contractdetail_shenhe, list_shenhe) {
            @Override
            protected void convert(ViewHolder holder, Fragment2Model model, int position) {
                //隐藏最前和最后的竖线
                View view_top = holder.getView(R.id.view_top);
                View view_bottom = holder.getView(R.id.view_bottom);
                if (position == 0){
                    view_top.setVisibility(View.INVISIBLE);
                }else {
                    view_top.setVisibility(View.VISIBLE);
                }
                if (position == (list_shenhe.size() -1)){
                    view_bottom.setVisibility(View.GONE);
                }else {
                    view_bottom.setVisibility(View.VISIBLE);
                }
                //横向图片
                List<String> list_img = new ArrayList<>();
                /*for (String s : model1.getGoods_info().getImgArr()) {
                    list_img.add(URLs.IMGHOST + s);
                }*/
                list_img.add(URLs.IMGHOST +"");
                list_img.add(URLs.IMGHOST +"");
                list_img.add(URLs.IMGHOST +"");
                RecyclerView rv = holder.getView(R.id.rv);
                LinearLayoutManager llm1 = new LinearLayoutManager(WorkListDetailActivity.this);
                llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                rv.setLayoutManager(llm1);
                CommonAdapter<String> ca = new CommonAdapter<String>
                        (WorkListDetailActivity.this, R.layout.item_img_28_28, list_img) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(WorkListDetailActivity.this).load(model)
                                .centerCrop()
                                .apply(RequestOptions.bitmapTransform(new
                                        RoundedCorners(CommonUtil.dip2px(WorkListDetailActivity.this, 3))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv);//加载图片
                    }
                };
                ca.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(WorkListDetailActivity.this, list_img, i);
                        photoShowDialog.show();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv.setAdapter(ca);

                            /*ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(OkHttpClientManager.IMGHOST + model.getCover())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            ImageView imageView2 = holder.getView(R.id.imageView2);
                            if (model.getStatus() == 1) {
                                //待安装
                                imageView2.setImageResource(R.mipmap.bg_anzhuangzhong);
                            } else {
                                imageView2.setImageResource(R.mipmap.bg_yianzhuang);
                            }

                            holder.setText(R.id.tv_name, model.getTitle());
                            holder.setText(R.id.tv_content, model.getProvince() + model.getCity() + model.getDistrict());
                            holder.setText(R.id.tv_addr, model.getAddress());
                            holder.setText(R.id.tv_num, model.getNum() + "");*/

               /* holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
//                    bundle.putString("id",model.getId());
                        CommonUtil.gotoActivityWithData(ContractDetailActivity.this, ContractDetailActivity.class, bundle, false);
                    }
                });*/

            }
        };
        rv_shenhe.setAdapter(mAdapter_shenhe);
    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Collection, params, headerMap, new CallBackUtil<BankCardSettingModel>() {
            @Override
            public BankCardSettingModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(BankCardSettingModel response) {
//                hideProgress();
//                model = response;

            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("工单详情");
    }

    private void changeUI() {
        switch (type) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);

                ll_shopinfo.setVisibility(View.VISIBLE);
                ll_shenhe.setVisibility(View.GONE);

                /*if (list1.size() > 0) {
                    showContentPage();
                    recyclerView1.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
                } else {
                    showEmptyPage();
                }*/
                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);

                ll_shopinfo.setVisibility(View.GONE);
                ll_shenhe.setVisibility(View.VISIBLE);

                break;

        }
    }

}
