package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FileUtils;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.FileUtil;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

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
                CommonUtil.gotoActivity(this, BankCardActivity.class, false);
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
            case R.id.textView7:
                //通知公告
                CommonUtil.gotoActivity(this, InformationActivity.class, false);
                break;
            case R.id.tv_confirm:
                //退出登录
                showToast("确认退出登录吗？",
                        getString(R.string.app_confirm),
                        getString(R.string.app_cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                showProgress(true, "正在注销登录，请稍候...");
                                requestOut(params);

                                localUserInfo.setUserId("");
                                localUserInfo.setToken("");
                                localUserInfo.setPhoneNumber("");
                                localUserInfo.setNickname("");
                                localUserInfo.setUserJob("");
                                localUserInfo.setTokenType("");
                                localUserInfo.setEmail("");
                                localUserInfo.setUserImage("");

                                //清除文件-压缩过的文件、拍照的文件
                                FileUtils.deleteFilesInDir(FileUtil.getImageDownloadDir(SetUpActivity.this));

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

    private void requestOut(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.LoginOut, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();

                /*localUserInfo.setUserId("");
                localUserInfo.setToken("");
                localUserInfo.setPhoneNumber("");
                localUserInfo.setNickname("");
                localUserInfo.setUserJob("");
                localUserInfo.setTokenType("");
                localUserInfo.setEmail("");
                localUserInfo.setUserImage("");

                //清除文件-压缩过的文件、拍照的文件
                FileUtils.deleteFilesInDir(FileUtil.getImageDownloadDir(SetUpActivity.this));

                ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                CommonUtil.gotoActivity(SetUpActivity.this, LoginActivity.class, true);*/
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("设置中心");
    }
}
