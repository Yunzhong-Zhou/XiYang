package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.Constant;
import com.xiyang.xiyang.utils.MyLogger;

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

    int old_type = 1;
    String crossLevel = "0", userId = "", oldParentId = "", newParentId = "", code = "";

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
        editText3.setClickable(true);
        iv_kuaqu.setClickable(true);
        switch (job) {
            case "CM":
                job_shangji = "RM";
                if (localUserInfo.getUserJob().equals("RM")) {
                    //操作者是RM，不可点击新的RM-并强制跨区
                    editText3.setClickable(false);
                    iv_kuaqu.setClickable(false);
                    crossLevel = "1";
                    iv_kuaqu.setImageResource(R.mipmap.ic_xuanzhong);
                    relativeLayout.setVisibility(View.GONE);
                }
                break;
            case "BDM":
                job_shangji = "CM";
                if (localUserInfo.getUserJob().equals("CM")) {
                    //操作者是CM，不可点击新的CM-并强制跨区
                    editText3.setClickable(false);
                    iv_kuaqu.setClickable(false);
                    crossLevel = "1";
                    iv_kuaqu.setImageResource(R.mipmap.ic_xuanzhong);
                    relativeLayout.setVisibility(View.GONE);
                }
                break;
            case "BD":
                job_shangji = "BDM";
                if (localUserInfo.getUserJob().equals("BDM")) {
                    //操作者是BDM，不可点击新的BDM-并强制跨区
                    editText3.setClickable(false);
                    iv_kuaqu.setClickable(false);
                    crossLevel = "1";
                    iv_kuaqu.setImageResource(R.mipmap.ic_xuanzhong);
                    relativeLayout.setVisibility(View.GONE);
                }
                break;
        }
        titleView.setTitle("调整" + job.toUpperCase() + "上级");
        textView1.setText("选择" + job.toUpperCase());
        editText1.setHint("请选择" + job.toUpperCase());
        textView2.setText("当前所属" + job_shangji);
        editText2.setHint("获取当前所属" + job_shangji);
        textView3.setText("选择新" + job_shangji);
        editText3.setHint("请选择新" + job_shangji);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_kuaqu:
                isKuaQu = !isKuaQu;
                if (isKuaQu) {
                    crossLevel = "1";
                    iv_kuaqu.setImageResource(R.mipmap.ic_xuanzhong);
                    relativeLayout.setVisibility(View.GONE);
                } else {
                    crossLevel = "0";
                    iv_kuaqu.setImageResource(R.mipmap.ic_weixuanzhong);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.editText1:
                //选择用户
                old_type = 1;
                Intent intent2 = new Intent(AdjustSuperiorActivity.this, SelectStaffActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_STAFF);
                bundle2.putString("role", job);
                bundle2.putString("userId", "");
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_STAFF, bundle2);
                break;
            case R.id.editText3:
                //选择用户
                old_type = 2;
                Intent intent3 = new Intent(AdjustSuperiorActivity.this, SelectStaffActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("requestCode", Constant.SELECT_STAFF);
                bundle3.putString("role", job_shangji.toUpperCase());
                bundle3.putString("userId", "");
                intent3.putExtras(bundle3);
                startActivityForResult(intent3, Constant.SELECT_STAFF, bundle3);
                break;
            case R.id.tv_code:
                //获取验证码
                showProgress(true, getString(R.string.app_sendcode_hint1));
                tv_code.setClickable(false);
                params.clear();
                params.put("mobile", localUserInfo.getPhonenumber());
//                params.put("type", "37");
                RequestCode(params,"");//获取验证码
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    params.clear();
                    params.put("crossLevel", crossLevel);//是否跨区
                    params.put("userId", userId);
                    params.put("oldParentId", oldParentId);
                    if (crossLevel.equals("0")) {
                        params.put("newParentId", newParentId);
                    } else {
                        params.put("newParentId", "");
                    }
                    params.put("extra", "");
                    params.put("code", code);//手机验证码
                    requestUpData(params);
                }
                break;
        }
    }

    private boolean match() {
        if (TextUtils.isEmpty(userId)) {
            myToast(editText1.getHint().toString());
            return false;
        }
        if (TextUtils.isEmpty(oldParentId)) {
            myToast(editText2.getHint().toString());
            return false;
        }
        if (crossLevel.equals("0")) {
            if (TextUtils.isEmpty(newParentId)) {
                myToast(editText3.getHint().toString());
                return false;
            }
        }
        code = et_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(et_code.getHint().toString());
            return false;
        }
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
        OkhttpUtil.okHttpPostJson(URLs.AdjustSuperior, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
//                CommonUtil.gotoActivity(AdjustSuperiorActivity.this, AdjustSuperiorListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);////1、调整上级 2、调整市场 3、升职降职 4、采购申请
                CommonUtil.gotoActivityWithData(AdjustSuperiorActivity.this, PersonnelListActivity.class, bundle, false);
            }
        });
    }

    /**
     * 发送验证码
     */
    private void RequestCode(HashMap<String, String> params,String type) {
        OkhttpUtil.okHttpPost(URLs.Code_yonghu+type, params, headerMap, new CallBackUtil<String>() {
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
                    //选择员工
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        if (old_type == 1) {
                            userId = bundle.getString("staffId");
                            editText1.setText(bundle.getString("staffName"));
                            oldParentId = bundle.getString("ShangJiId");
                            editText2.setText(bundle.getString("ShangJiName"));
                            MyLogger.i(">>>>>" + bundle.getString("ShangJiName"));
                        } else {
                            newParentId = bundle.getString("staffId");
                            editText3.setText(bundle.getString("staffName"));
                        }
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
