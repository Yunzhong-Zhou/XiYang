package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
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
                CommonUtil.gotoActivity(this, ForgetPasswordActivity.class, false);
                break;
            case R.id.textView4:
                //个人资料
                CommonUtil.gotoActivity(this, MyProfileActivity.class, false);
                break;
            case R.id.textView5:
                //关于我们
                CommonUtil.gotoActivity(this, AboutActivity.class, false);
                break;
            case R.id.textView6:
                //帮助中心
                CommonUtil.gotoActivity(this, HelpCenterActivity.class, false);
                break;
            case R.id.tv_confirm:
                //退出登录
                showToast("确认退出登录吗？",
                        getString(R.string.app_confirm),
                        getString(R.string.app_cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                localUserInfo.setUserId("");
                                localUserInfo.setToken("");
                                localUserInfo.setPhoneNumber("");
                                localUserInfo.setNickname("");
                                localUserInfo.setInvuteCode("");
                                localUserInfo.setWalletaddr("");
                                localUserInfo.setEmail("");
                                localUserInfo.setUserImage("");

                                ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                                CommonUtil.gotoActivity(SetUpActivity.this, LoginActivity.class, true);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("设置中心");
    }
}
