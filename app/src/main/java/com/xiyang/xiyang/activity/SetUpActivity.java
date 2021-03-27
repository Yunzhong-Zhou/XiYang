package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.utils.CommonUtil;

/**
 * Created by Mr.Z on 2020/12/10.
 * 设置中心
 */
public class SetUpActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_setup);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                //绑定银行卡
                CommonUtil.gotoActivity(this, BankCardSettingActivity.class, false);
                break;
            case R.id.textView2:
                //交易密码
                CommonUtil.gotoActivity(this, SetTransactionPasswordActivity.class, false);
                break;
            case R.id.textView3:
                //登录密码
                CommonUtil.gotoActivity(this, ChangePasswordActivity.class, false);
                break;
            case R.id.textView4:
                //个人资料
//                CommonUtil.gotoActivity(this, MyProfileActivity.class, false);
                break;
            /*case R.id.textView5:
                //关于我们
                CommonUtil.gotoActivity(this, AboutActivity.class, false);
                break;*/
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("设置中心");
    }
}
