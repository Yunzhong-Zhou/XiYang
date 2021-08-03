package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.DeviceListModel_Position;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/4/12.
 * 设备定位
 */
public class DeviceAddressActivity extends BaseActivity {
    DeviceListModel_Position.LocationDeviceVoListBean model;
    TextView tv_name, tv_shop, tv_addr, textView, tv_confirm;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviceaddress);
    }

    @Override
    protected void initView() {
        tv_name = findViewByID_My(R.id.tv_name);
        tv_shop = findViewByID_My(R.id.tv_shop);
        tv_addr = findViewByID_My(R.id.tv_addr);
        textView = findViewByID_My(R.id.textView);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        imageView = findViewByID_My(R.id.imageView);

    }

    @Override
    protected void initData() {
        model = (DeviceListModel_Position.LocationDeviceVoListBean) getIntent().getSerializableExtra("model");
        if (model != null) {
            tv_name.setText(model.getStoreName());
            tv_shop.setText(model.getDeviceHostName());
            tv_addr.setText(model.getStoreAddress());

            if (Double.valueOf(model.getDifferNum()) > 100) {
                //设备异常
                imageView.setImageResource(R.mipmap.bg_yichang);
                textView.setText("设备异常！\n\n"
                        + "距离位置偏移" + model.getDifferNum() + "米\n"
                        + model.getStoreAddress());
                //禁用设备
                tv_confirm.setVisibility(View.VISIBLE);
                tv_confirm.setOnClickListener(v -> {
                    showToast("确认禁用该设备吗？","确认","取消",v1 -> {
                        dialog.dismiss();
                        params.clear();
                        params.put("deviceId", model.getDeviceHostName());
                        params.put("type", "0");
                        params.put("relationId", "");
                        requestUpData(params);
                    },v2 -> {
                        dialog.dismiss();
                    });

                });
            } else {
                //设备正常
                imageView.setImageResource(R.mipmap.bg_zhengchang);
                textView.setText("设备位置正常！");
                //禁用设备
                tv_confirm.setVisibility(View.GONE);
            }


        }
    }

    /**
     * 禁用设备
     *
     * @param params
     */
    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.StopDevice, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                myToast("提交成功");
                hideProgress();
                finish();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("设备定位");
    }
}
