package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by zyz on 2017/9/4.
 * 修改门店账号
 */

public class ChangeStoreAccountActivity extends BaseActivity {
    String storeId = "",storeName="",storeAccount="";
    EditText editText1, editText2, editText3, editText4;
    TextView textView1,tv_confirm;
    private TimeCount time;
    String mobile = "", code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changestoreaccount);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
//        editText1.setText("+"+localUserInfo.getMobile_State_Code()+"  "+localUserInfo.getPhonenumber());
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (!s.toString().trim().equals("") && s.length() == 11) {
                    params.clear();
                    params.put("phone", s.toString().trim());
                    requestDetect(params,editText4);
                }
            }
        });

        textView1 = findViewByID_My(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(storeAccount)) {
                    Toast.makeText(ChangeStoreAccountActivity.this, getString(R.string.settransactionpassword_h3), Toast.LENGTH_SHORT).show();
                } else {
                    showProgress(true, getString(R.string.app_sendcode_hint1));
                    textView1.setClickable(false);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("mobile", storeAccount);
//                    params.put("type", "8");
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
                    params.clear();
                    params.put("compulsoryNormalUserToStoreUserType","true");
                    params.put("id", storeId);
//                    params.put("account",editText2.getText().toString().trim());
                    params.put("newAccount", mobile);
                    params.put("code", code);
                    RequestUpData(params);
                }
            }
        });
    }

    private void RequestCode(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Code_yonghu, params, headerMap, new CallBackUtil<String>() {
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

    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPutJson(URLs.ChangeStoreAccount, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
                MyLogger.i(">>>>>>>>>设置交易密码" + response);
//                myToast(getString(R.string.settransactionpassword_h11));
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    myToast(jObj.getString("msg"));
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        storeId = getIntent().getStringExtra("storeId");
        storeName = getIntent().getStringExtra("storeName");
        storeAccount = getIntent().getStringExtra("storeAccount");
        editText1.setText(storeName);
        editText2.setText(storeAccount);
    }

    private boolean match() {
        code = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.settransactionpassword_h5));
            return false;
        }
        mobile = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            myToast("请输入新账号");
            return false;
        }

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("修改账户信息");
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

    /**
     * 检测账号是否存在
     *
     * @param params
     */
    private void requestDetect(HashMap<String, String> params,TextView textView) {
        OkhttpUtil.okHttpGet(URLs.AddShop_Detect,params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                if (err.contains("存在")) {
                    showToast("该账号已存在是否同意\n切换为商户账号？", "同意", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            textView.setText("");
                        }
                    });
                } else
                    myToast(err);
            }

            @Override
            public void onResponse(String response) {
            }
        });
    }
}
