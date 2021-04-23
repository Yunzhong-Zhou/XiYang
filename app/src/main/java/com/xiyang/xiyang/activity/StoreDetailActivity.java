package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.BankCardSettingModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/30.
 * 门店详情
 */
public class StoreDetailActivity extends BaseActivity {
    String id = "";
    int type = 1;
    TextView tv_tab1, tv_tab2, tv_tab3, tv_tab4;
    LinearLayout ll_tab1, ll_tab2, ll_tab3, ll_tab4;
    View view1, view2, view3, view4;

    /**
     * 门店信息
     */
    LinearLayout ll_storeinfo;

    /**
     * 设备信息
     */
    LinearLayout ll_deviceinfo;
    /**
     * 营收信息
     */
    LinearLayout ll_yingshouinfo;
    /**
     * 收费标准
     */
    LinearLayout ll_shoufeiinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);

        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                params.put("id", id);
                request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        ll_tab3 = findViewByID_My(R.id.ll_tab3);
        ll_tab4 = findViewByID_My(R.id.ll_tab4);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_tab3.setOnClickListener(this);
        ll_tab4.setOnClickListener(this);
        tv_tab1 = findViewByID_My(R.id.tv_tab1);
        tv_tab2 = findViewByID_My(R.id.tv_tab2);
        tv_tab3 = findViewByID_My(R.id.tv_tab3);
        tv_tab4 = findViewByID_My(R.id.tv_tab4);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        view4 = findViewByID_My(R.id.view4);

        /**
         * 门店信息
         */
        ll_storeinfo = findViewByID_My(R.id.ll_storeinfo);

        /**
         * 设备信息
         */
        ll_deviceinfo = findViewByID_My(R.id.ll_deviceinfo);
        /**
         * 营收信息
         */
        ll_yingshouinfo = findViewByID_My(R.id.ll_yingshouinfo);
        /**
         * 收费标准
         */
        ll_shoufeiinfo = findViewByID_My(R.id.ll_shoufeiinfo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.right_btn1:
                CommonUtil.gotoActivity(StoreDetailActivity.this,AssignActivity.class);
                break;
            case R.id.linearLayout1:
                //设备
                CommonUtil.gotoActivity(StoreDetailActivity.this, MyDeviceListActivity.class);
                break;
            case R.id.linearLayout2:
                //房号
                CommonUtil.gotoActivity(StoreDetailActivity.this, RoomNoManagementActivity.class);
                break;
            case R.id.linearLayout3:
                //员工
                CommonUtil.gotoActivity(StoreDetailActivity.this, StaffManagementActivity.class);
                break;
            case R.id.linearLayout4:
                //移位
                CommonUtil.gotoActivity(StoreDetailActivity.this, MoveDeviceActivity.class);
                break;
            case R.id.tv_change1:
                //修改账号
                CommonUtil.gotoActivity(StoreDetailActivity.this, ChangeStoreAccountActivity.class);
                break;
            case R.id.tv_change2:
                //修改信息
                CommonUtil.gotoActivity(StoreDetailActivity.this, ChangeStoreActivity.class);
                break;
            case R.id.ll_tab1:
                //待拜访
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //待划转
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //待调价
                type = 3;
                changeUI();
                break;
            case R.id.ll_tab4:
                //待调价
                type = 4;
                changeUI();
                break;
        }
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
    }


    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.BankCard, params, headerMap, new CallBackUtil<BankCardSettingModel>() {
            @Override
            public BankCardSettingModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(BankCardSettingModel response) {
//                hideProgress();
//                model = response;

            }
        });
    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.put("id", id);
        request(params);
    }
    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    private void changeUI() {
        switch (type) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                ll_storeinfo.setVisibility(View.VISIBLE);
                ll_deviceinfo.setVisibility(View.GONE);
                ll_yingshouinfo.setVisibility(View.GONE);
                ll_shoufeiinfo.setVisibility(View.GONE);

                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));

                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);

                ll_storeinfo.setVisibility(View.GONE);
                ll_deviceinfo.setVisibility(View.VISIBLE);
                ll_yingshouinfo.setVisibility(View.GONE);
                ll_shoufeiinfo.setVisibility(View.GONE);

                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));

                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.INVISIBLE);

                ll_storeinfo.setVisibility(View.GONE);
                ll_deviceinfo.setVisibility(View.GONE);
                ll_yingshouinfo.setVisibility(View.VISIBLE);
                ll_shoufeiinfo.setVisibility(View.GONE);
                break;
            case 4:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black1));

                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.VISIBLE);

                ll_storeinfo.setVisibility(View.GONE);
                ll_deviceinfo.setVisibility(View.GONE);
                ll_yingshouinfo.setVisibility(View.GONE);
                ll_shoufeiinfo.setVisibility(View.VISIBLE);
                break;

        }
    }

}
