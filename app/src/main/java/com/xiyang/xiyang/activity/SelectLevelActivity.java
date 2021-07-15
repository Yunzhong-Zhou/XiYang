package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.lihang.ShadowLayout;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.utils.CommonUtil;

/**
 * Created by Mr.Z on 2021/3/28.
 * 选择等级
 */
public class SelectLevelActivity extends BaseActivity {
    int type = 1;
    ShadowLayout shadowLayout1,shadowLayout2,shadowLayout3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectlevel);
    }

    @Override
    protected void initView() {
        shadowLayout1 = findViewByID_My(R.id.shadowLayout1);
        shadowLayout2 = findViewByID_My(R.id.shadowLayout2);
        shadowLayout3 = findViewByID_My(R.id.shadowLayout3);

        if (localUserInfo.getUserJob().equals("CM"))
            shadowLayout1.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.linearLayout1:
                //CM
                bundle.putString("job","CM");
                break;
            case R.id.linearLayout2:
                //BDM
                bundle.putString("job","BDM");
                break;
            case R.id.linearLayout3:
                //BD
                bundle.putString("job","BD");
                break;
        }
        switch (type){
            case 1:
                //调整上级
                CommonUtil.gotoActivityWithData(SelectLevelActivity.this, AdjustSuperiorActivity.class,bundle);
                break;
            case 2:
                //调整市场
                CommonUtil.gotoActivityWithData(SelectLevelActivity.this, AdjustMarketActivity.class,bundle);
                break;
            case 3:
                //升职降职
                CommonUtil.gotoActivityWithData(SelectLevelActivity.this, AdjustJobActivity.class,bundle);
                break;
        }
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type",1);
        switch (type){
            case 1:
                titleView.setTitle("调整上级");
                break;
            case 2:
                titleView.setTitle("调整市场");
                break;
            case 3:
                titleView.setTitle("升职降职");
                break;
        }
    }

    @Override
    protected void updateView() {

    }

}
