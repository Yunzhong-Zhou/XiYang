package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cretin.tools.scancode.CaptureActivity;
import com.cretin.tools.scancode.config.ScanConfig;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.DeviceDetailModel;
import com.xiyang.xiyang.model.StoreDetailModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.Constant;
import com.xiyang.xiyang.utils.MyLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/4/2.
 * 安装设备
 */
public class InstallDeviceActivity extends BaseActivity {
    StoreDetailModel model;
    String deviceName = "", storeId = "", shopId = "", roomId = "",transactionId="";
    LinearLayout ll_scan;
    TextView iv_scan;
    TextView tv_scan;
    EditText tv_anzhuangshanghu, tv_anzhuangmendian, tv_xuanzefanghao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installdevice);
    }

    @Override
    protected void initView() {
        ll_scan = findViewByID_My(R.id.ll_scan);
        iv_scan = findViewByID_My(R.id.iv_scan);
        tv_scan = findViewByID_My(R.id.tv_scan);
        tv_anzhuangshanghu = findViewByID_My(R.id.tv_anzhuangshanghu);
        tv_anzhuangmendian = findViewByID_My(R.id.tv_anzhuangmendian);
        tv_xuanzefanghao = findViewByID_My(R.id.tv_xuanzefanghao);

    }

    @Override
    protected void initData() {
        transactionId = getIntent().getStringExtra("transactionId");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_scan:
                //扫描
                ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(this, config);
                break;
            case R.id.tv_anzhuangshanghu:
                //选择商户
                Intent intent2 = new Intent(InstallDeviceActivity.this, MyShopListActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_SHOP);
                bundle2.putString("status", "");//状态 0 => '待指派',1 => '待签约',2 => '待审核',3 => '正常',4 => '待续约'
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_SHOP, bundle2);
                break;
            case R.id.tv_anzhuangmendian:
                //选择门店
                Intent intent1 = new Intent(InstallDeviceActivity.this, MyStoreListActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("requestCode", Constant.SELECT_STORE);
                bundle1.putString("status", "");//状态 0 => '待指派',1 => '待签约',2 => '待审核',3 => '正常',4 => '待续约'
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, Constant.SELECT_STORE, bundle1);
                break;
            case R.id.tv_xuanzefanghao:
                //选择房号
//                Bundle bundle= new Bundle();
//                bundle.putSerializable("StoreDetailModel",model);
//                CommonUtil.gotoActivityWithData(InstallDeviceActivity.this, RoomNoManagementActivity.class,bundle);
                if (model !=null){
                    Intent intent3 = new Intent(InstallDeviceActivity.this, RoomNoManagementActivity.class);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("requestCode", Constant.SELECT_ROOMNO);
                    bundle3.putSerializable("StoreDetailModel", model);
                    intent3.putExtras(bundle3);
                    startActivityForResult(intent3, Constant.SELECT_ROOMNO);
                }else {
                    myToast("请先选择门店");
                }

                break;

            case R.id.tv_confirm:
                //确认安装
                if (match()){
                    this.showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("transactionId", transactionId);
                    params.put("deviceName", deviceName);
                    params.put("storeId", storeId);
                    params.put("shopId", shopId);
                    params.put("roomId", roomId);
                    requestUpData(params);
                }
                break;
        }
    }
    private boolean match() {
        if (TextUtils.isEmpty(deviceName)) {
            myToast("请先扫码");
            return false;
        }
        if (TextUtils.isEmpty(shopId)) {
            myToast("请选择安装商户");
            return false;
        }
        if (TextUtils.isEmpty(storeId)) {
            myToast("请选择安装门店");
            return false;
        }
        if (TextUtils.isEmpty(roomId)) {
            myToast("请选择房号");
            return false;
        }

        return true;
    }
    @Override
    protected void updateView() {
        titleView.setTitle("安装设备");
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String imgpath = null;
            Uri uri = null;
            switch (requestCode) {
                case CaptureActivity.REQUEST_CODE_SCAN:
                    //二维码扫码
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                        //{"deviceName": "641708882ef84e09995d70440e12ebf9"}
                        MyLogger.i("扫码返回", result);
                        if (!result.equals("")) {
                            try {
                                JSONObject mJsonObject = new JSONObject(result);
                                deviceName = mJsonObject.getString("deviceName");
                                iv_scan.setVisibility(View.GONE);
                                tv_scan.setText("SN号:" + deviceName);

                            /*showProgress(true, getString(R.string.app_loading2));
                            params.clear();
                            params.put("deviceName", deviceName);
                            requestDeviceDetail(params);*/

                            } catch (JSONException e) {
                                e.printStackTrace();
                                myToast("解析出错");
                            }
                        } else {
                            iv_scan.setVisibility(View.VISIBLE);
                            tv_scan.setVisibility(View.GONE);
                        }
                    }
                    break;
                case Constant.SELECT_STORE:
                    //选择门店
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        storeId = bundle.getString("storeId");
                        tv_anzhuangmendian.setText(bundle.getString("storeName"));

                        //获取门店详情
                        showProgress(true, getString(R.string.app_loading2));
                        params.clear();
                        params.put("id", storeId);
                        requestStoreDetail(params);
                    }
                    break;
                case Constant.SELECT_SHOP:
                    //选择商户
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        shopId = bundle.getString("shopId");
                        tv_anzhuangshanghu.setText(bundle.getString("shopName"));
                    }
                    break;
                case Constant.SELECT_ROOMNO:
                    //选择房号
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        roomId = bundle.getString("roomId");
                        tv_xuanzefanghao.setText(bundle.getString("roomName"));
                    }
                    break;

            }
        }
    }

    /**
     * 获取设备详情
     *
     * @param params
     */
    private void requestDeviceDetail(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.DeviceDetail, params, headerMap, new CallBackUtil<DeviceDetailModel>() {
            @Override
            public DeviceDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(DeviceDetailModel response) {
                hideProgress();

            }
        });
    }

    /**
     * 获取门店详情
     *
     * @param params
     */
    private void requestStoreDetail(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.StoreDetail, params, headerMap, new CallBackUtil<StoreDetailModel>() {
            @Override
            public StoreDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(StoreDetailModel response) {
                hideProgress();
                model = response;
            }
        });
    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.InstallDevice, params, headerMap, new CallBackUtil<String>() {
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
}
