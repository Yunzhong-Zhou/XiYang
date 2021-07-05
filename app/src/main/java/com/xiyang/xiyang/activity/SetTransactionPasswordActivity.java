package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.GsonUtils;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.MyLogger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by zyz on 2017/9/4.
 * 设置交易密码
 */

public class SetTransactionPasswordActivity extends BaseActivity {
    EditText editText1, editText2, editText3, editText4;
    TextView textView1,tv_confirm;
    private TimeCount time;
    String phonenum = "", password1 = "", password2 = "", code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settransactionpassword);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText1.setText(""+localUserInfo.getMobile_State_Code()+"  "+localUserInfo.getPhonenumber());
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        textView1 = findViewByID_My(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonenum = localUserInfo.getPhonenumber();
                if (TextUtils.isEmpty(phonenum)) {
                    Toast.makeText(SetTransactionPasswordActivity.this, getString(R.string.settransactionpassword_h3), Toast.LENGTH_SHORT).show();
                } else {
                    /*String string = "?mobile=" + localUserInfo.getPhonenumber() +
                            "&type=" + "5" +
                            "&mobile_state_code=" + localUserInfo.getMobile_State_Code();*/
                    SetTransactionPasswordActivity.this.showProgress(true, getString(R.string.app_sendcode_hint1));
                    textView1.setClickable(false);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("mobile", localUserInfo.getPhonenumber());
                    params.put("type", "32");
//                    params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                    RequestCode(params);//获取验证码
                }
            }
        });
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
//                    params.put("qk", qk);
                    params.put("tradePassword", password1);//交易密码（不能小于6位数）
                    params.put("verificationCode", code);//手机验证码
//                    params.put("token", localUserInfo.getToken());
                    RequestSetTransactionPassword(params);//设置交易密码
                }
            }
        });
    }

    private void RequestCode(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Code_jiaoyi, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                textView1.setClickable(true);
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();

                time.start();
                MyLogger.i(">>>>>>>>>发送验证码" + response);
                myToast(getString(R.string.app_sendcode_hint));
            }
        });

    }

    private void RequestSetTransactionPassword(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.TransactionPassword, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
                myToast("交易密码设置成功");
                /*JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    myToast(jObj.getString("msg"));
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
                finish();
            }
        });

    }

    @Override
    protected void initData() {
//        qk = getIntent().getStringExtra("qk");
    }

    private boolean match() {
        phonenum = localUserInfo.getPhonenumber();
        if (TextUtils.isEmpty(phonenum)) {
            myToast(getString(R.string.settransactionpassword_h3));
            return false;
        }
        code = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.settransactionpassword_h5));
            return false;
        }
        password1 = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(password1)) {
            myToast(getString(R.string.settransactionpassword_h7));
            return false;
        }
        password2 = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            myToast(getString(R.string.settransactionpassword_h9));
            return false;
        }

        if (!password1.equals(password2)) {
            myToast(getString(R.string.settransactionpassword_h10));
            return false;
        }
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.settransactionpassword_h1));
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView1.setText(getString(R.string.app_reacquirecode));
            textView1.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView1.setClickable(false);
            textView1.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }
}
