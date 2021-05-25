package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.SubordinateModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.Constant;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 调整市场
 */
public class AdjustMarketActivity extends BaseActivity {
    String job = "";
    boolean isKuaQu = false;
    ImageView iv_kuaqu;
    TextView textView1, tv_code;
    EditText editText1, et_code;
    private TimeCount time;

    LinearLayout linearLayout;
    View view1;

    List<String> stringList = new ArrayList<>();
    FlowLayout flowLayout;

    RecyclerView recyclerView;
    List<String> idlist = new ArrayList<>();
    List<String> citylist = new ArrayList<>();
    CommonAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustmarket);
    }

    @Override
    protected void initView() {
        iv_kuaqu = findViewByID_My(R.id.iv_kuaqu);
        textView1 = findViewByID_My(R.id.textView1);
        tv_code = findViewByID_My(R.id.tv_code);
        editText1 = findViewByID_My(R.id.editText1);
        et_code = findViewByID_My(R.id.et_code);
        linearLayout = findViewByID_My(R.id.linearLayout);
        view1 = findViewByID_My(R.id.view1);

        flowLayout = findViewByID_My(R.id.flowLayout);
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(AdjustMarketActivity.this, 3));


    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        job = getIntent().getStringExtra("job");

        titleView.setTitle("调整" + job.toUpperCase() + "市场");
        textView1.setText("选择" + job.toUpperCase());
        editText1.setHint("请选择" + job.toUpperCase());

        stringList.add("重庆");
        stringList.add("齐齐哈尔");
        stringList.add("齐齐哈尔");
        stringList.add("贵阳");
        stringList.add("齐齐哈尔");
        stringList.add("乌鲁木齐");
        stringList.add("杭州");
        stringList.add("齐齐哈尔");

        FlowLayoutAdapter<String> flowLayoutAdapter =
                new FlowLayoutAdapter<String>
                        (stringList) {
                    @Override
                    public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position,
                                               String bean) {
                        holder.setText(R.id.tv, bean);
                    }

                    @Override
                    public void onItemClick(int position, String bean) {

                    }

                    @Override
                    public int getItemLayoutID(int position, String bean) {
                        return R.layout.item_flowlayout_yuanjiaobiankuang;
                    }
                };

        flowLayout.setAdapter(flowLayoutAdapter);

        showSelectCity(idlist,citylist);

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        params.put("role", editText1.getText().toString().toLowerCase());
        requestStaff(params);
    }

    private void requestStaff(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.AdjustJob, params, headerMap, new CallBackUtil<SubordinateModel>() {
            @Override
            public SubordinateModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(SubordinateModel response) {
                showContentPage();
                hideProgress();
//                list = response.getList();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_kuaqu:
                isKuaQu = !isKuaQu;
                if (isKuaQu) {
                    iv_kuaqu.setImageResource(R.mipmap.ic_xuanzhong);
                    linearLayout.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                } else {
                    iv_kuaqu.setImageResource(R.mipmap.ic_weixuanzhong);
                    linearLayout.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.editText1:
                //选择用户
                Intent intent2 = new Intent(AdjustMarketActivity.this, SelectStaffActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_STAFF);
                bundle2.putString("role", job);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_STAFF, bundle2);
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
     * 展示选择的城市
     */
    private void showSelectCity(List<String> idlist,List<String> citylist){
        idlist.add("");
        citylist.add("");
        mAdapter = new CommonAdapter<String>
                (AdjustMarketActivity.this, R.layout.item_selectcity, citylist) {
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
                        Intent intent2 = new Intent(AdjustMarketActivity.this, SelectMyCityActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("requestCode", Constant.SELECT_MYCITY);
                        intent2.putExtras(bundle2);
                        startActivityForResult(intent2, Constant.SELECT_MYCITY, bundle2);
                    }
                });

            }
        };
        recyclerView.setAdapter(mAdapter);
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
                CommonUtil.gotoActivity(AdjustMarketActivity.this, AdjustMarketListActivity.class);
            }
        });
    }

    /**
     * 发送验证码
     */
    private void RequestCode(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Code, params, headerMap, new CallBackUtil<String>() {
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
//                        adminId = bundle.getString("staffId");
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
                        for (String s:idArr){
                            idlist.add(s);
                        }
                        for (String s:cityArr){
                            citylist.add(s);
                        }
                        showSelectCity(idlist,citylist);
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
