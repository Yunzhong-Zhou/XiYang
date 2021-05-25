package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.utils.CommonUtil;

/**
 * Created by Mr.Z on 2021/3/28.
 * 人事记录
 */
public class PersonnelActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel);
    }

    @Override
    protected void initView() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                //调整上级
                CommonUtil.gotoActivity(PersonnelActivity.this, AdjustSuperiorListActivity.class);
                break;
            case R.id.linearLayout2:
                //调整市场
                CommonUtil.gotoActivity(PersonnelActivity.this, AdjustMarketListActivity.class);
                break;
            case R.id.linearLayout3:
                //升职降职
                CommonUtil.gotoActivity(PersonnelActivity.this, AdjustJobListActivity.class);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle("人事记录");
    }

}
