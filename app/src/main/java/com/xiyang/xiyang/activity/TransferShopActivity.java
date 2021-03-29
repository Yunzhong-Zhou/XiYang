package com.xiyang.xiyang.activity;

import android.os.Bundle;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;

/**
 * Created by Mr.Z on 2021/3/28.
 * 划转商户
 */
public class TransferShopActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfershop);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle("申请商户划转");
    }
}
