package com.xiyang.xiyang.activity;

import android.os.Bundle;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;

/**
 * Created by Mr.Z on 2021/3/28.
 * 关闭门店
 */
public class StopStoreActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopstore);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle("关闭门店");
    }
}
