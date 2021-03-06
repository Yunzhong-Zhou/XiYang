package com.xiyang.xiyang.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.cy.dialog.BaseDialog;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.adapter.Pop_ListAdapter;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.BankCardModel;
import com.xiyang.xiyang.model.BankListModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;
import com.xiyang.xiyang.view.FixedPopupWindow;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2017/9/16.
 * 银行卡设置
 */
public class BankCardSettingActivity extends BaseActivity {
    BankCardModel model;
    //    String qk = "";
    EditText textView1, textView3, editText1, editText2, editText3, editText4;
    TextView textView4, textView5;
    private TimeCount time;
    String bank_address = "", bank_card_account = "", bank_card_proceeds_name = "", bank_title = "", code = "", password = "", bankId = "";

    List<BankListModel.ListBean> banklist = new ArrayList<>();
    int i1 = -1;

    CityConfig cityConfig = null;
    //开户行控件 申明对象
    CityPickerView mPicker = new CityPickerView();
    String bank_address_temp = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcardsetting);

        //预先加载仿iOS滚轮实现的全部数据
        mPicker.init(this);
        cityConfig = new CityConfig.Builder()
                .title("选择省市")//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#eaeaea")//标题栏背景色
                .confirTextColor("#AF8848")//确认按钮文字颜色
                .confirmText(getString(R.string.app_confirm))//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#AF8848")//取消按钮文字颜色
                .cancelText(getString(R.string.app_cancel))//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(7)//显示item的数量
                .province("北京市")//默认显示的省份
                .city("北京市")//默认显示省份下面的城市
                .district("朝阳区")//默认显示省市下面的区县数据
                .provinceCyclic(true)//省份滚轮是否可以循环滚动
                .cityCyclic(true)//城市滚轮是否可以循环滚动
                .districtCyclic(true)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.textView1)//自定义item布局里面的textViewid
                .drawShadows(true)//滚轮不显示模糊效果
                .setLineColor("#80CDCDCE")//中间横线的颜色
                .setLineHeigh(1)//中间横线的高度
                .setShowGAT(true)//是否显示港澳台数据，默认不显示
                .build();

        //设置自定义的属性配置
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份province//城市city//地区district
               /* textView2.setText(province.getName().toString() +
                        city.getName().toString() +
                        district.getName().toString());*/
                bank_address_temp = province.getName().toString() + "#" +
                        city.getName().toString() + "#" +
                        district.getName().toString();
            }

            @Override
            public void onCancel() {
//                ToastUtils.showLongToast(this, "已取消");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress(true, getString(R.string.app_loading2));
        //获取收款设置
        RequestBankList(params);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);

        textView1 = findViewByID_My(R.id.textView1);
//        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView3.setText("" + localUserInfo.getMobile_State_Code() + "  " + localUserInfo.getPhonenumber());
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

//        qk = getIntent().getStringExtra("qk");
        model = (BankCardModel) getIntent().getSerializableExtra("BankCardModel");
        if (model != null) {
            if (model.getBankCardNumber() != null && !model.getBankCardNumber().equals("")) {
                bankId = model.getBankId();
                textView1.setText(model.getBankName());//开户行
                editText1.setText(model.getBankUserName());//开户名
                editText2.setText(model.getBankCardNumber());//银行卡号
            }
            if (!model.isTradePasswordFlag()) {
                showToast(getString(R.string.password_h2),
                        getString(R.string.password_h5), getString(R.string.password_h6),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                CommonUtil.gotoActivity(BankCardSettingActivity.this, SetTransactionPasswordActivity.class, false);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                finish();
                            }
                        });
            }
        }

    }

    //获取收款设置
    private void RequestBankList(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.BankList, params, headerMap, new CallBackUtil<BankListModel>() {
            @Override
            public BankListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);

            }

            @Override
            public void onResponse(BankListModel response) {
                hideProgress();
                /*if (response.getMember().getTrade_password().equals("")) {
                    showToast("需设置交易密码后才可操作",
                            getString(R.string.app_confirm), getString(R.string.app_cancel),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(BankCardSettingActivity.this, SetTransactionPasswordActivity.class, false);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                }*/
                banklist = response.getList();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                //选择银行
//                showPopupWindow1(textView1);
                dialogList_bank();
                break;
            case R.id.textView4:
                //获取验证码
                showProgress(true, getString(R.string.app_sendcode_hint1));
                textView4.setClickable(false);
                HashMap<String, String> params1 = new HashMap<>();
//                params1.put("mobile", localUserInfo.getPhonenumber());
//                params1.put("type", "BIND_BANK_CARD");//忘记密码:FORGET_PASSWORD; 添加员工:ADD_EMPLOYEE;提现:WITHDRAWAL; 设置交易密码:SET_TRADE_PASSWORD; LOGIN:登录; BIND_BANK_CARD:绑定银行卡
//                params1.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                RequestCode(params1,"BIND_BANK_CARD");//获取验证码
                break;
            case R.id.textView5:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
//                    params.put("qk", qk);
                    params.put("bankCardNumber", bank_card_account);//银行卡账号
                    params.put("bankUserName", bank_card_proceeds_name);//银行卡收款人姓名
                    params.put("bankId", bankId);//银行名称
                    params.put("verificationCode", code);//手机验证码
                    params.put("phone", localUserInfo.getPhonenumber());
                    params.put("tradePassword", password);//交易密码（不能小于6位数）
//                    params.put("bank_address", bank_address + "");//开户行
                    RequestSetting(params);//
                }
                break;
           /* case R.id.textView2:
                //开户行地址
                mPicker.showCityPicker();
                break;*/

        }
    }

    //发送验证码
    private void RequestCode(HashMap<String, String> params, String type) {
        OkhttpUtil.okHttpPost(URLs.Code_yinhangka+type, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                textView4.setClickable(true);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                time.start();
                textView4.setClickable(true);
                MyLogger.i(">>>>>>>>>发送验证码" + response);
                myToast(getString(R.string.app_sendcode_hint));
            }
        });

    }

    //收款设置
    private void RequestSetting(HashMap<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.BankCardSet, GsonUtils.toJson(params), headerMap, new CallBackUtil<BankCardModel>() {
            @Override
            public BankCardModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (err.contains(getString(R.string.password_h1))) {
                    showToast(getString(R.string.password_h2),
                            getString(R.string.password_h5), getString(R.string.password_h6),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(BankCardSettingActivity.this, SetTransactionPasswordActivity.class, false);
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

            @Override
            public void onResponse(BankCardModel response) {
//                hideProgress();
                if (response != null && response.getResultCode().equals("2")) {
                    showToast(getString(R.string.password_h2),
                            getString(R.string.password_h5), getString(R.string.password_h6),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(BankCardSettingActivity.this, SetTransactionPasswordActivity.class, false);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                } else {
                    myToast("绑定银行卡成功");
                    finish();
                    /*Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    CommonUtil.gotoActivityWithData(BankCardSettingActivity.this, TakeCashActivity.class, bundle, true);*/
                }
            }
        });

    }

    private boolean match() {
//        bank_title = editText1.getText().toString().trim();
        bank_title = textView1.getText().toString().trim();
        if (TextUtils.isEmpty(bank_title)) {
            myToast("请选择开户行");
            return false;
        }
        bank_card_proceeds_name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(bank_card_proceeds_name)) {
            myToast("请输入姓名");
            return false;
        }
        bank_card_account = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(bank_card_account)) {
            myToast("请输入卡号");
            return false;
        }

        code = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast("请输入验证码");
            return false;
        }
        password = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast("请输入交易密码");
            return false;
        }

       /* if (!bank_address_temp.equals("")) {
            bank_address = bank_address_temp;
            *//*if (editText7.getText().toString().trim().equals("")) {
                bank_address = bank_address_temp + "#" + "支行";
            } else {
                bank_address = bank_address_temp + "#" + editText7.getText().toString().trim();
            }*//*
        } else {
//            bank_address = "";
            myToast(getString(R.string.takecash_h27));
            return false;
        }*/

        return true;
    }


    @Override
    protected void updateView() {
        titleView.setTitle("绑定银行卡");
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView4.setText(getString(R.string.app_reacquirecode));
            textView4.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView4.setClickable(false);
            textView4.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }

    /**
     * 选择银行
     */
    private void dialogList_bank() {
        dialog.contentView(R.layout.dialog_list_top)
                /*.layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))*/
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        CommonUtil.dip2px(BankCardSettingActivity.this, 400)))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.TOP)
                .dimAmount(0.5f)
                .show();
        /*dialog.contentView(R.layout.dialog_list_center)
//                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        CommonUtil.dip2px(BankCardSettingActivity.this, 400)))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.TOP)
                .dimAmount(0.5f)
                .show();*/
        RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<BankListModel.ListBean> adapter = new CommonAdapter<BankListModel.ListBean>
                (BankCardSettingActivity.this, R.layout.item_help, banklist) {
            @Override
            protected void convert(ViewHolder holder, BankListModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getName());
                if (i1 == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                i1 = position;
                bankId = banklist.get(position).getId();
                textView1.setText(banklist.get(position).getName());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_list.setAdapter(adapter);
    }

    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(BankCardSettingActivity.this).inflate(
                R.layout.pop_list_1, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < banklist.size(); i++) {
            list.add(banklist.get(i).getName());
        }
        final Pop_ListAdapter adapter = new Pop_ListAdapter(BankCardSettingActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                i1 = i;
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();

                bank_title = banklist.get(i).getName();
                textView1.setText(banklist.get(i).getName());
                popupWindow.dismiss();

            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparent));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }
}
