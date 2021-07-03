package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 调整上级
 */
public class AdjustSuperiorActivity extends BaseActivity {
    String job = "", job_shangji = "";
    boolean isKuaQu = false;
    ImageView iv_kuaqu;
    TextView textView1, textView2, textView3, tv_code;
    EditText editText1, editText2, editText3, et_code;
    private TimeCount time;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustsuperior);
    }

    @Override
    protected void initView() {
        iv_kuaqu = findViewByID_My(R.id.iv_kuaqu);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        tv_code = findViewByID_My(R.id.tv_code);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        et_code = findViewByID_My(R.id.et_code);
        relativeLayout = findViewByID_My(R.id.relativeLayout);
    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        job = getIntent().getStringExtra("job");

        switch (job) {
            case "cm":
                job_shangji = "RM";
                break;
            case "bdm":
                job_shangji = "CM";
                break;
            case "bd":
                job_shangji = "BDM";
                break;
        }
        titleView.setTitle("调整"+ job.toUpperCase()+"上级");
        textView1.setText("选择" + job.toUpperCase());
        editText1.setHint("请选择" + job.toUpperCase());
        textView2.setText("当前所属"+job_shangji);
        editText2.setHint("当前所属"+job_shangji);
        textView3.setText("选择新"+job_shangji);
        editText3.setHint("请选择新"+job_shangji);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_kuaqu:
                isKuaQu = !isKuaQu;
                if (isKuaQu) {
                    iv_kuaqu.setImageResource(R.mipmap.ic_xuanzhong);
                    relativeLayout.setVisibility(View.GONE);
                } else {
                    iv_kuaqu.setImageResource(R.mipmap.ic_weixuanzhong);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.editText1:
                //选择用户
                Intent intent2 = new Intent(AdjustSuperiorActivity.this, SelectStaffActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_STAFF);
                bundle2.putString("role", job);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_STAFF, bundle2);
                break;
            case R.id.editText3:
                //选择用户
                Intent intent3 = new Intent(AdjustSuperiorActivity.this, SelectStaffActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("requestCode", Constant.SELECT_STAFF);
                bundle3.putString("role", job_shangji.toLowerCase());
                intent3.putExtras(bundle3);
                startActivityForResult(intent3, Constant.SELECT_STAFF, bundle3);
                break;
            case R.id.tv_code:
                //获取验证码
                showProgress(false, getString(R.string.app_sendcode_hint1));
                tv_code.setClickable(false);
                HashMap<String, String> params1 = new HashMap<>();
                params1.put("mobile", localUserInfo.getPhonenumber());
                params1.put("type", "37");
                RequestCode(params1);//获取验证码
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(false, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                   /* params.put("role", role.toLowerCase());//大写转小写
                    params.put("adminId", adminId);
                    params.put("code", code);//手机验证码*/
                    requestUpData(params);
                }
                break;
        }
    }

    private boolean match() {
       /* if (TextUtils.isEmpty(adminId)) {
            myToast(editText1.getHint().toString());
            return false;
        }
        role = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(role)) {
            myToast("请选择新角色");
            return false;
        }

        code = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast("请输入验证码");
            return false;
        }*/
        return true;
    }

    @Override
    protected void updateView() {

    }

    /**
     * 提交数据
     *
     * @param params
     */
    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AdjustSuperior, params, headerMap, new CallBackUtil<String>() {
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
                CommonUtil.gotoActivity(AdjustSuperiorActivity.this, AdjustSuperiorListActivity.class);
            }
        });
    }

    /**
     * 发送验证码
     */
    private void RequestCode(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Code_yonghu, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                tv_code.setClickable(true);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                time.start();
                tv_code.setClickable(true);
                myToast(getString(R.string.app_sendcode_hint));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.SELECT_STAFF:
                    //选择城市
                    if (data != null) {
                        Bundle bundle = data.getExtras();
//                        adminId = bundle.getString("staffId");
                        editText1.setText(bundle.getString("staffName"));
                    }
                    break;
            }
        }

    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv_code.setText(getString(R.string.app_reacquirecode));
            tv_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv_code.setClickable(false);
            tv_code.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }

}
