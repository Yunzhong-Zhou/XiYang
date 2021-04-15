package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.AvailableAmountModel;
import com.xiyang.xiyang.model.TakeCashModel;
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
 * Created by zyz on 2017/9/4.
 * 提币
 */

public class TakeCashActivity extends BaseActivity {
    int money_type = 1;
    ImageView imageView1, imageView2,imageView3;
    TextView tv_title, textView1, textView2, textView3, textView4, textView5, textView6, tv_confirm;
    EditText editText1, editText2, editText3;

    String input_money = "", password = "", code = "";
    AvailableAmountModel model;

    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takecash);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                /*String string = "?token=" + localUserInfo.getToken()
                        + "&money_type=" + money_type;
                RequestAvailableAmount(string);//获取可用币数*/
            }

            @Override
            public void onLoadmore() {

            }
        });
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView3 = findViewByID_My(R.id.imageView3);
        tv_title = findViewByID_My(R.id.tv_title);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);

        textView6 = findViewByID_My(R.id.textView6);
        textView6.setText("验证码已发送至"
                + "+" + localUserInfo.getMobile_State_Code() + " "
                + localUserInfo.getPhonenumber());
        textView6.setVisibility(View.GONE);

        tv_confirm = findViewByID_My(R.id.tv_confirm);

        time = new TimeCount(60000, 1000, textView5);//构造CountDownTimer对象

        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        //输入监听
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                /*if (!editText1.getText().toString().trim().equals("")) {
                    input_money = editText1.getText().toString().trim();
                    MyLogger.i(">>>>>输入币数>>>>>" + input_money);
                    //手续费 = 输入币数 * 手续费率 /100
                    double service_money = Double.valueOf(model.getWithdrawal_service_charge());
                    MyLogger.i(">>>>>手续费>>>>>" + service_money);
                    //实际到账 =  (输入币数 - 手续费)
                    double real_money = (Double.valueOf(input_money) - service_money);
                    MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                    textView6.setText("¥ " + String.format("%.2f", real_money));//实际到账

                } else {
                    textView6.setText("0");//实际到账
                }*/
            }
        });

//        LinearLayout linearLayout = findViewByID_My(R.id.linearLayout);
        /*LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        sp_params.height = CommonUtil.getScreenHeight(getActivity()) / 4;
        linearLayout.setLayoutParams(sp_params);*/


        //动态设置linearLayout的高度为屏幕高度的1/4
        /*linearLayout_addr = findViewByID_My(R.id.linearLayout_addr);
        ViewGroup.LayoutParams lp = linearLayout_addr.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(TakeCashActivity.this) / 4;*/


    }

    @Override
    protected void initData() {
        /*money_type = getIntent().getIntExtra("money_type", 1);
        if (money_type == 1) {
            //usdt
            tv_title.setText(getString(R.string.takecash_h15));
            textView1.setText(getString(R.string.takecash_h3));
            imageView1.setImageResource(R.mipmap.ic_usdt_white1);
            imageView2.setImageResource(R.mipmap.ic_usdt_black1);
            editText1.setInputType(InputType.TYPE_CLASS_NUMBER);
//            textView4.setText("3"+getString(R.string.app_type_usdt));
        } else {
            tv_title.setText(getString(R.string.takecash_h1));
            textView1.setText(getString(R.string.takecash_h37));
            imageView1.setImageResource(R.mipmap.ic_fil_white);
            imageView2.setImageResource(R.mipmap.ic_fil_black);
//            editText1.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
//            textView4.setText("3"+getString(R.string.app_type_fil));
        }*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.tv_confirm:
                if (match()) {
                    tv_confirm.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("code", code);
                    params.put("trade_password", password);//交易密码（不能小于6位数）
                    params.put("input_money", input_money);//提现金额
                    params.put("money_type", money_type + "");
                    params.put("token", localUserInfo.getToken());
                    params.put("hk", model.getHk());
                    requestTakeCash(params);//提现
                }
                break;
            case R.id.textView3:
                Bundle bundle = new Bundle();
                CommonUtil.gotoActivityWithData(this, BankCardSettingActivity.class, bundle, false);

               /* if (money_type == 1) {
                    bundle.putInt("type", 1);
                    CommonUtil.gotoActivityWithData(this, SetAddressActivity.class, bundle, false);
                } else {
                    bundle.putInt("type", 2);
                    CommonUtil.gotoActivityWithData(this, SetAddressActivity.class, bundle, false);
                }*/

                break;
            case R.id.textView5:
                showProgress(true, getString(R.string.app_sendcode_hint1));
                textView5.setClickable(false);
                HashMap<String, String> params = new HashMap<>();
                params.put("mobile", localUserInfo.getPhonenumber());
                params.put("type", "8");
                params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                RequestCode(params, textView5, textView6);//获取验证码
                break;
        }
    }

    //可用余额
    private void requestAvailableAmount(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.TakeCash, params, headerMap, new CallBackUtil<AvailableAmountModel>() {
            @Override
            public AvailableAmountModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(AvailableAmountModel response) {
//                showContentPage();
                MyLogger.i(">>>>>>>>>我的收益" + response);
                model = response;

                if (model.getBank_card_account() != null && !model.getBank_card_account().equals("")) {
                    Glide.with(TakeCashActivity.this)
                            .load(URLs.IMGHOST + response.getBank_icon())
                            .centerCrop()
                            .placeholder(R.mipmap.ic_bank_blue)//加载站位图
                            .error(R.mipmap.ic_bank_blue)//加载失败
                            .into(imageView3);//加载图片
                    textView3.setText(response.getBank_card_account());//地址
                } else {
                    textView3.setText("银行卡暂未设置，点此跳转设置");
                    showToast("需设置银行卡后才可操作",
                            getString(R.string.app_confirm), getString(R.string.app_cancel),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(TakeCashActivity.this, BankCardSettingActivity.class, false);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                }

                textView2.setText(response.getUsable_money());//可用余额
                editText1.setHint("请输入提现金额");//请输入提币个数
                textView4.setText("¥ "+response.getWithdrawal_service_charge());//手续费
                /*if (money_type == 1) {
                    textView4.setText(response.getWithdrawal_service_charge() + getString(R.string.app_type_usdt));//手续费
                } else {
                    textView4.setText(response.getWithdrawal_service_charge() + getString(R.string.app_type_fil));//手续费
                }*/
            }
        });
    }

    //提现
    private void requestTakeCash(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.TakeCash, params, headerMap, new CallBackUtil<TakeCashModel>() {
            @Override
            public TakeCashModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                tv_confirm.setClickable(true);
                hideProgress();
                if (!err.equals("")) {
                    if (err.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SetTransactionPasswordActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else {
                        showToast(err);
                    }
                }
                requestServer();
            }

            @Override
            public void onResponse(TakeCashModel response) {
                tv_confirm.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>提现" + response);
//                myToast(getString(R.string.takecash_h13));
                requestServer();
                if (response.getCode() == 1) {
                    showToast(getString(R.string.password_h2),
                            getString(R.string.password_h5), getString(R.string.password_h6),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(TakeCashActivity.this, SetTransactionPasswordActivity.class, false);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
//                                    requestServer();
                                    finish();
                                }
                            });
                } else if (response.getCode() == 2) {
                    showToast(getString(R.string.password_h9),
                            getString(R.string.password_h5), getString(R.string.password_h6),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(TakeCashActivity.this, BankCardSettingActivity.class, false);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
//                                    requestServer();
                                    finish();
                                }
                            });
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", response.getId());
                    CommonUtil.gotoActivityWithData(TakeCashActivity.this, TakeCashDetailActivity.class, bundle, true);
                }
            }
        });

    }

    private void RequestCode(HashMap<String, String> params, final TextView tv, final TextView tv3) {
        OkhttpUtil.okHttpPost(URLs.Code, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                tv.setClickable(true);
                tv3.setVisibility(View.GONE);
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>验证码" + response);
                tv.setClickable(true);
                tv3.setVisibility(View.VISIBLE);
                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));
            }
        });
    }

    private boolean match() {
        input_money = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(input_money)) {
            myToast("请输入提现金额");
            return false;
        }
        password = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast("请输入交易密码");
            return false;
        }
        code = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast("请输入验证码");
            return false;
        }
        if (Double.valueOf(input_money) < Double.valueOf(model.getMin_withdrawal_money())) {
            myToast("提现金额不能小于" + model.getMin_withdrawal_money());
            /*if (money_type == 1) {
                myToast(getString(R.string.takecash_h38) + model.getMin_withdrawal_money() + getString(R.string.app_type_usdt));
            } else {
                myToast(getString(R.string.takecash_h38) + model.getMin_withdrawal_money() + getString(R.string.app_type_fil));
            }*/
            return false;
        }
        return true;
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));

        requestAvailableAmount(params);//获取可用币数
    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.takecash_h1));
        titleView.setVisibility(View.GONE);
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        TextView tv;

        public TimeCount(long millisInFuture, long countDownInterval, TextView tv) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
            this.tv = tv;
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv.setText(getString(R.string.app_reacquirecode));
            tv.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv.setClickable(false);
            tv.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }
}
