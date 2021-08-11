package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.Constant;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 调整岗位
 */
public class AdjustJobActivity extends BaseActivity {
    String job = "";
    boolean isKuaQu = false;
    ImageView iv_kuaqu;
    TextView textView1, tv_code;
    EditText editText1, editText2, et_code;

    LinearLayout linearLayout;
    View view1;

    private TimeCount time;

    List<String> list_juese = new ArrayList<>();

    int itme_juese = 0;

    RecyclerView recyclerView;
    List<String> idlist = new ArrayList<>();
    List<String> citylist = new ArrayList<>();
    CommonAdapter<String> mAdapter;

    String crossLevel="0",adminId = "", role = "", code = "",newAreaIds="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustjob);
    }

    @Override
    protected void initView() {
        iv_kuaqu = findViewByID_My(R.id.iv_kuaqu);
        textView1 = findViewByID_My(R.id.textView1);
        tv_code = findViewByID_My(R.id.tv_code);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        et_code = findViewByID_My(R.id.et_code);
        linearLayout = findViewByID_My(R.id.linearLayout);
        view1 = findViewByID_My(R.id.view1);

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(AdjustJobActivity.this, 3));

    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        job = getIntent().getStringExtra("job");

        titleView.setTitle("调整" + job.toUpperCase() + "市场");
        textView1.setText("选择" + job.toUpperCase());
        editText1.setHint("请选择" + job.toUpperCase());


        if (localUserInfo.getUserJob().equals("RM")) {
            if (!job.equals("CM")) {
                list_juese.add("CM");
            }
        }

        if (localUserInfo.getUserJob().equals("CM")){
            if (!job.equals("BDM")) {
                list_juese.add("BDM");
            }
        }
        list_juese.add("BD");
        editText2.setText(list_juese.get(itme_juese));

        showSelectCity(idlist, citylist);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_kuaqu:
                isKuaQu = !isKuaQu;
                if (isKuaQu) {
                    crossLevel = "1";
                    iv_kuaqu.setImageResource(R.mipmap.ic_xuanzhong);
                    linearLayout.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                } else {
                    crossLevel = "0";
                    iv_kuaqu.setImageResource(R.mipmap.ic_weixuanzhong);
                    linearLayout.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.editText1:
                //选择用户
                Intent intent2 = new Intent(AdjustJobActivity.this, SelectStaffActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_STAFF);
                bundle2.putString("role", job);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_STAFF, bundle2);
                break;
            case R.id.editText2:
                //选择角色
                dialogList_juese(editText2);
                break;
            case R.id.tv_code:
                //获取验证码
                showProgress(true, getString(R.string.app_sendcode_hint1));
                tv_code.setClickable(false);
                HashMap<String, String> params1 = new HashMap<>();
                params1.put("mobile", localUserInfo.getPhonenumber());
//                params1.put("type", "37");
                RequestCode(params1,"");//获取验证码
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    params.clear();
                    params.put("crossLevel", crossLevel);//跨区
                    params.put("userId", adminId);
                    params.put("newCode", role.toUpperCase());
                    params.put("newAreaIds", newAreaIds);
                    params.put("extra", "");
                    params.put("code", code);//手机验证码
                    requestUpData(params);
                }
                break;
        }
    }

    private boolean match() {
        if (TextUtils.isEmpty(adminId)) {
            myToast("请选择用户");
            return false;
        }
        role = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(role)) {
            myToast("请选择新角色");
            return false;
        }

        //现在负责城市
        newAreaIds = "";
        idlist.remove("");
        MyLogger.i(">>>>"+idlist.size());
        if (crossLevel.equals("0")) {
            for (String s : idlist) {
                newAreaIds += s + ",";
            }
            if (!newAreaIds.equals("")) {
                newAreaIds = newAreaIds.substring(0, newAreaIds.length() - 1);
            } else {
                myToast("请选择新的负责城市");
                return false;
            }
        }

        code = et_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast("请输入验证码");
            return false;
        }
        return true;
    }

    /**
     * 展示选择的城市
     */
    private void showSelectCity(List<String> idlist, List<String> citylist) {
        idlist.add("");
        citylist.add("");
        mAdapter = new CommonAdapter<String>
                (AdjustJobActivity.this, R.layout.item_selectcity, citylist) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.tv);
                ImageView iv_delete = holder.getView(R.id.iv_delete);
                ImageView iv_add = holder.getView(R.id.iv_add);

                if (position == citylist.size() - 1) {//最后一个
                    tv.setVisibility(View.INVISIBLE);
                    iv_delete.setVisibility(View.INVISIBLE);
                    iv_add.setVisibility(View.VISIBLE);
                } else {
                    tv.setVisibility(View.VISIBLE);
                    iv_delete.setVisibility(View.VISIBLE);
                    iv_add.setVisibility(View.INVISIBLE);

                    tv.setText(model);
                }
                iv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idlist.remove(position);
                        citylist.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                iv_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //选择城市
                        Intent intent2 = new Intent(AdjustJobActivity.this, SelectMyCityActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("requestCode", Constant.SELECT_MYCITY);
                        bundle2.putString("job",editText2.getText().toString());
                        intent2.putExtras(bundle2);
                        startActivityForResult(intent2, Constant.SELECT_MYCITY, bundle2);
                    }
                });

            }
        };
        recyclerView.setAdapter(mAdapter);
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
                MyLogger.i(">>>>>>>>>发送验证码" + response);
                myToast(getString(R.string.app_sendcode_hint));
            }
        });

    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.AdjustJob, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
                myToast("调整成功");
                hideProgress();
//                finish();
                Bundle bundle = new Bundle();
                bundle.putInt("type", 3);////1、调整上级 2、调整市场 3、升职降职 4、采购申请
                CommonUtil.gotoActivityWithData(AdjustJobActivity.this, PersonnelListActivity.class, bundle, false);
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("升职降职");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.SELECT_STAFF:
                    //选择员工
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        adminId = bundle.getString("staffId");
                        editText1.setText(bundle.getString("staffName"));
                    }
                    break;
                case Constant.SELECT_MYCITY:
                    //选择城市
                    if (data != null) {
                        Bundle bundle = data.getExtras();

                        String postionIds = bundle.getString("postionIds");
                        String postionCitys = bundle.getString("postionCitys");
                        String[] idArr = postionIds.split(",");
                        String[] cityArr = postionCitys.split(",");

                        idlist.clear();
                        citylist.clear();
                        for (String s : idArr) {
                            idlist.add(s);
                        }
                        for (String s : cityArr) {
                            citylist.add(s);
                        }
                        showSelectCity(idlist, citylist);
                    }
                    break;
            }
        }

    }

    /**
     * 选择角色
     */
    private void dialogList_juese(EditText editText) {
        dialog.contentView(R.layout.dialog_list_center)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.CENTER)
                .dimAmount(0.5f)
                .show();
        RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<String> adapter = new CommonAdapter<String>
                (AdjustJobActivity.this, R.layout.item_help, list_juese) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (itme_juese == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                itme_juese = position;
                editText.setText(list_juese.get(position));
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
}
