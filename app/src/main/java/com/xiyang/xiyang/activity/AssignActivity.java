package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
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
 * 指派
 */
public class AssignActivity extends BaseActivity {
    int type_m = 1;//1、商户分派 2、门店分派 3、工单分派
    String id = "", name = "", userName = "", job_xiaji = "", staffId = "";
    ;
    TextView tv_1, tv_2, tv_3,
            textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
    }

    @Override
    protected void initView() {
        tv_1 = findViewByID_My(R.id.tv_1);
        tv_2 = findViewByID_My(R.id.tv_2);
        tv_3 = findViewByID_My(R.id.tv_3);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);

    }

    @Override
    protected void initData() {
        type_m = getIntent().getIntExtra("type_m", 1);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        userName = getIntent().getStringExtra("userName");

        textView1.setText(name);
        textView2.setText(userName);
        switch (type_m) {
            case 1:
                //商户
                titleView.setTitle("指派商户");
                tv_1.setText("选择商户");
                textView1.setHint("请选择商户");
                break;
            case 2:
                //门店
                titleView.setTitle("指派门店");
                tv_1.setText("选择门店");
                textView1.setHint("请选择门店");
                break;
            case 3:
                //工单
                titleView.setTitle("指派工单");
                tv_1.setText("选择工单");
                textView1.setHint("请选择工单");
                break;
        }
        switch (localUserInfo.getUserJob()) {
            case "RM":
                job_xiaji = "CM";
                tv_2.setText("当前CM");
                textView2.setHint("获取当前CM");
                tv_3.setText("选择CM");
                textView3.setHint("请选择CM");
                break;
            case "CM":
                job_xiaji = "BDM";
                tv_2.setText("当前BDM");
                textView2.setHint("获取当前BDM");
                tv_3.setText("选择BDM");
                textView3.setHint("请选择BDM");
                break;
            case "BDM":
                job_xiaji = "BD";
                tv_2.setText("当前BD");
                textView2.setHint("获取当前BD");
                tv_3.setText("选择BD");
                textView3.setHint("请选择BD");
                break;
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //选择任务
                break;
            case R.id.textView3:
                //选择新指派人
                Intent intent2 = new Intent(AssignActivity.this, SelectStaffActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_STAFF);
                bundle2.putString("role", job_xiaji);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_STAFF, bundle2);
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    params.clear();
                    switch (type_m) {
                        case 1:
                            //商户
                            params.put("merchantTransferLogId", id);
                            params.put("takeOverUserId", staffId);
                            requestUpData(params,URLs.DispatchShop);
                            break;
                        case 2:
                            //门店
                            params.put("storeTransferLogId", id);
                            params.put("takeOverUserId", staffId);
                            requestUpData(params,URLs.DispatchStore);
                            break;
                        case 3:
                            //工单
                            params.put("workOrderId", id);
                            params.put("takeOverUserId", staffId);
                            requestUpData(params,URLs.DispatchWork);
                            break;
                    }

                }
                break;
        }
    }

    private boolean match() {
        if (TextUtils.isEmpty(id)) {
            myToast(textView1.getHint().toString());
            return false;
        }
        if (TextUtils.isEmpty(staffId)) {
            myToast(textView3.getHint().toString());
            return false;
        }
        return true;
    }

    /**
     * 提交数据
     */
    private void requestUpData(Map<String, String> params, String url) {
        OkhttpUtil.okHttpPostJson(url, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
    protected void updateView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.SELECT_STAFF:
                    //选择员工
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        staffId = bundle.getString("staffId");
                        textView3.setText(bundle.getString("staffName"));
                    }
                    break;
            }
        }

    }
}
