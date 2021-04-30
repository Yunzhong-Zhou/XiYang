package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.Constant;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 划转商户
 */
public class TransferShopActivity extends BaseActivity {
    EditText textView1,textView2;
    String shopId="",reason="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfershop);
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
    }

    @Override
    protected void initData() {

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.textView1:
                //选择门店
                Intent intent2 = new Intent(TransferShopActivity.this, MyShopListActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_SHOP);
                bundle2.putString("status", "");//状态 0 => '待指派',1 => '待签约',2 => '待审核',3 => '正常',4 => '待续约'
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_SHOP, bundle2);
                break;
            case R.id.tv_confirm:
                //提交
                if (match()){
                    showToast("确认划转该商户吗？", "确定", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            showProgress(true, getString(R.string.app_loading1));
                            params.put("id", shopId);
                            params.put("reason", reason);
                            requestUpData(params);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
                break;
        }
    }

    private boolean match() {
        if (TextUtils.isEmpty(shopId)) {
            myToast("请选择商户");
            return false;
        }
        reason = textView2.getText().toString().trim();
        if (TextUtils.isEmpty(reason)) {
            myToast("请输入划转理由");
            return false;
        }
        return true;
    }
    @Override
    protected void updateView() {
        titleView.setTitle("申请商户划转");
    }
    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.TransferShop, params, headerMap, new CallBackUtil<String>() {
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
                myToast("提交成功");
                hideProgress();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.SELECT_SHOP:
                    //选择商户
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        shopId = bundle.getString("shopId");
                        textView1.setText(bundle.getString("shopName"));
                    }
                    break;
            }

        }
    }
}
