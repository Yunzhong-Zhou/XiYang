package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.utils.CommonUtil;

/**
 * Created by Mr.Z on 2021/3/29.
 * 选择设备
 */
public class SelectDeviceActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdevice);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.textView1:
                //空气净化主机>>
                bundle.putInt("type", 1);

                break;
            case R.id.textView2:
                //4G网络模块>>
                bundle.putInt("type", 2);

                break;
            case R.id.textView3:
                //过滤网>>
                bundle.putInt("type", 3);

                break;
        }
        CommonUtil.gotoActivityWithData(SelectDeviceActivity.this, AddDeviceActivity.class, bundle);
    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择设备");
    }
}
