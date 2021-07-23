package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
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
import com.xiyang.xiyang.utils.MyLogger;
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
    List<String> stringList_ID = new ArrayList<>();
    FlowLayout flowLayout;

    RecyclerView recyclerView;
    List<String> idlist = new ArrayList<>();
    List<String> citylist = new ArrayList<>();
    CommonAdapter<String> mAdapter;
    FlowLayoutAdapter<String> flowLayoutAdapter;

    String crossLevel = "0", userId = "", oldAreaIds = "", newAreaIds = "", code = "";

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

        flowLayoutAdapter = new FlowLayoutAdapter<String>(stringList) {
            @Override
            public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position, String bean) {
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

        showSelectCity(idlist, citylist);

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        params.put("role", editText1.getText().toString().toLowerCase());
//        requestStaff(params);
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
                Intent intent2 = new Intent(AdjustMarketActivity.this, SelectStaffActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_STAFF);
                bundle2.putString("role", job);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_STAFF, bundle2);
                break;
            case R.id.tv_code:
                //获取验证码
                showProgress(true, getString(R.string.app_sendcode_hint1));
                tv_code.setClickable(false);
                HashMap<String, String> params1 = new HashMap<>();
                params1.put("mobile", localUserInfo.getPhonenumber());
                params1.put("type", "37");
                RequestCode(params1);//获取验证码
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    params.put("crossLevel", crossLevel);//跨区
                    params.put("userId", userId);
                    params.put("oldAreaIds", oldAreaIds);
                    params.put("newAreaIds", newAreaIds);
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
        //当前负责城市
        oldAreaIds = "";
        for (String s : stringList_ID) {
            oldAreaIds += s + ",";
        }
        if (!oldAreaIds.equals("")) {
            oldAreaIds = oldAreaIds.substring(0, oldAreaIds.length() - 1);
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
            }
        }
        code = et_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast("请输入验证码");
            return false;
        }
        return true;
    }

    @Override
    protected void updateView() {
    }

    /**
     * 展示选择的城市
     */
    private void showSelectCity(List<String> idlist, List<String> citylist) {
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
                        bundle2.putString("job",job);
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
        OkhttpUtil.okHttpPostJson(URLs.AdjustMarket, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
//                CommonUtil.gotoActivity(AdjustMarketActivity.this, AdjustMarketListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);////1、调整上级 2、调整市场 3、升职降职 4、采购申请
                CommonUtil.gotoActivityWithData(AdjustMarketActivity.this, PersonnelListActivity.class, bundle, false);
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
                    //选择员工
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        userId = bundle.getString("staffId");
                        editText1.setText(bundle.getString("staffName"));

                        //管理的城市
                        String[] cityIds = bundle.getStringArray("cityIds");
                        String[] citys = bundle.getStringArray("citys");
                        stringList_ID.clear();
                        stringList.clear();
                        for (String s : cityIds) {
                            stringList_ID.add(s);
                        }
                        for (String s : citys) {
                            stringList.add(s);
                        }
                        flowLayoutAdapter.notifyDataSetChanged();
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
