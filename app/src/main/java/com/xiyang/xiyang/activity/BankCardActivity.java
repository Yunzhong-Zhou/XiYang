package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.BankCardModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/6/30.
 */
public class BankCardActivity extends BaseActivity {
    BankCardModel model;
    LinearLayout linearLayout_wu, linearLayout_you;
    ImageView ic_banklogo;
    TextView tv_banknum, tv_kaihuname, tv_bankname;

    @Override
    protected void onResume() {
        super.onResume();
        showProgress(true, getString(R.string.app_loading2));
        //获取收款设置
        RequestGetCollection(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcard);
    }

    @Override
    protected void initView() {
        linearLayout_wu = findViewByID_My(R.id.linearLayout_wu);
        linearLayout_you = findViewByID_My(R.id.linearLayout_you);

        tv_banknum = findViewByID_My(R.id.tv_banknum);
        tv_kaihuname = findViewByID_My(R.id.tv_kaihuname);
        tv_bankname = findViewByID_My(R.id.tv_bankname);

        ic_banklogo = findViewByID_My(R.id.ic_banklogo);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout_wu:
                /*//编辑银行卡
                CommonUtil.gotoActivity(BankCardActivity.this, BankCardSettingActivity.class, false);
                break;*/
            case R.id.iv_edit:
                //编辑银行卡
                Bundle bundle = new Bundle();
                bundle.putSerializable("BankCardModel", model);
                CommonUtil.gotoActivityWithData(BankCardActivity.this, BankCardSettingActivity.class, bundle, false);
                break;
        }
    }

    //获取收款设置
    private void RequestGetCollection(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.BankCard, params, headerMap, new CallBackUtil<BankCardModel>() {
            @Override
            public BankCardModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(BankCardModel response) {
                hideProgress();
                model = response;
                if (response != null && response.getBankCardNumber()!=null) {
                    linearLayout_wu.setVisibility(View.GONE);
                    linearLayout_you.setVisibility(View.VISIBLE);
                    tv_banknum.setText(response.getBankCardNumber());//银行卡号
                    tv_kaihuname.setText(response.getBankName());//开户行
                    tv_bankname.setText(response.getBankUserName());//开户名
                    Glide.with(BankCardActivity.this)
                            .load(response.getBankLogo())
                            .centerCrop()
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(CommonUtil.dip2px(MyProfileActivity.this, 10))))
                            .placeholder(R.mipmap.ic_yinlian)//加载站位图
                            .error(R.mipmap.ic_yinlian)//加载失败
                            .into(ic_banklogo);//加载图片

                } else {
                    linearLayout_wu.setVisibility(View.VISIBLE);
                    linearLayout_you.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("绑定银行卡");
    }
}
