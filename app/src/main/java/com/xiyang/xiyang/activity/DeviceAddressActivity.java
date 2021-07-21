package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;

/**
 * Created by Mr.Z on 2021/4/12.
 * 设备定位
 */
public class DeviceAddressActivity extends BaseActivity {
    TextView tv_name,tv_shop,tv_addr,textView;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviceaddress);
    }

    @Override
    protected void initView() {
        tv_name  =findViewByID_My(R.id.tv_name);
        tv_shop = findViewByID_My(R.id.tv_shop);
        tv_addr = findViewByID_My(R.id.tv_addr);
        textView = findViewByID_My(R.id.textView);
        imageView = findViewByID_My(R.id.imageView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle("设备定位");
    }
}
