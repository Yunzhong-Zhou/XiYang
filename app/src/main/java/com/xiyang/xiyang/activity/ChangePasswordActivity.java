package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by fafukeji01 on 2017/4/25.
 * 修改密码
 */

public class ChangePasswordActivity extends BaseActivity {
    EditText editText1, editText2, editText3;
    String oldpassword = "", password1 = "", password2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                //确认
                if (match()) {
                    this.showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("new_password", password1);//密码（不能小于6位数）
                    params.put("token", localUserInfo.getToken());
                    params.put("old_password", oldpassword);
                    Request(params);
                }
                break;
        }
    }

    private boolean match() {
        oldpassword = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(oldpassword)) {
            myToast(getString(R.string.changepassword_h5));
            return false;
        }
        password1 = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password1)) {
            myToast(getString(R.string.changepassword_h6));
            return false;
        }
        password2 = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            myToast(getString(R.string.changepassword_h7));
            return false;
        }
        if (!password1.equals(password2)) {
            myToast(getString(R.string.changepassword_h8));
            return false;
        }
        return true;
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ChangePassword, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>修改登录密码" + response);
                myToast(getString(R.string.changepassword_h9));
//                finish();
                localUserInfo.setUserId("");
                localUserInfo.setToken("");
                localUserInfo.setPhoneNumber("");
                localUserInfo.setNickname("");
                localUserInfo.setWalletaddr("");
                localUserInfo.setEmail("");
                localUserInfo.setUserImage("");

                ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                CommonUtil.gotoActivity(ChangePasswordActivity.this, LoginActivity.class, true);
               /* CommonUtil.gotoActivityWithFinishOtherAll(ChangePasswordActivity.this, LoginActivity.class, true);
                finish();*/
            }
        });

    }


    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.changepassword_h1));
    }
}
