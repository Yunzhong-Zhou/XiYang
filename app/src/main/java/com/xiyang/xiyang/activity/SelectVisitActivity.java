package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;

/**
 * Created by Mr.Z on 2021/3/29.
 * 选择拜访
 */
public class SelectVisitActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectvisit);
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
        switch (v.getId()){
            case R.id.textView1:
                //远程拜访

                break;
            case R.id.textView2:
                //上门拜访

                break;
            case R.id.textView3:
                //陌生拜访
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择拜访");
    }
}
