package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.BankCardSettingModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/30.
 * 设备详情
 */
public class DeviceDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicedetail);
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
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_tiaoshi:
                //调试设备

                break;
            case R.id.tv_shangbao:
                //上报故障
                break;
            case R.id.tv_weixiu:
                //维修记录
                break;
        }
    }

    @Override
    protected void initData() {

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
        titleView.setTitle("设备详情");
    }
}
