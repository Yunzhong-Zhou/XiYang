package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
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
 * 换绑设备
 */
public class ChangeTieDeviceActivity extends BaseActivity {
    StoreDetailModel model;
    String deviceName = "", storeId = "", shopId = "", roomId = "";
    LinearLayout ll_scan;
    TextView iv_scan;
    TextView tv_scan;
    EditText tv_anzhuangshanghu, tv_anzhuangmendian, tv_xuanzefanghao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changetiedevice);
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
            case R.id.tv_xuanzefanghao:
                //选择房号
                if (model !=null){
                    Intent intent3 = new Intent(ChangeTieDeviceActivity.this, RoomNoManagementActivity.class);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("requestCode", Constant.SELECT_ROOMNO);
                    bundle3.putSerializable("StoreDetailModel", model);
                    intent3.putExtras(bundle3);
                    startActivityForResult(intent3, Constant.SELECT_ROOMNO);
                }else {
                    myToast("请先扫码，获取设备所属门店");
                }
                break;
            case R.id.tv_confirm:
                //确认换绑
                if (match()){
                    this.showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("hostname", deviceName);
                    params.put("storeId", storeId);
//                    params.put("shopId", shopId);
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
            myToast("请先扫码，获取安装商户");
            return false;
        }
        if (TextUtils.isEmpty(storeId)) {
            myToast("请先扫码，获取安装门店");
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
        titleView.setTitle("换绑设备");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == CaptureActivity.REQUEST_CODE_SCAN) {
            // 扫描二维码回传
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    //获取扫描结果
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                    MyLogger.i("扫码返回", result);
                    if (!result.equals("")) {
                        try {
                            JSONObject mJsonObject = new JSONObject(result);
                            deviceName = mJsonObject.getString("deviceName");
                            iv_scan.setVisibility(View.GONE);
                            tv_scan.setText("SN号:" + deviceName);

                            showProgress(true, getString(R.string.app_loading2));
                            params.clear();
                            params.put("deviceName", deviceName);
                            requestDeviceDetail(params);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            myToast("解析出错");
                        }
                    } else {
                        iv_scan.setVisibility(View.VISIBLE);
                        tv_scan.setVisibility(View.GONE);
                    }
                }
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
//                hideProgress();
//                tv_anzhuangshanghu.setText(response.get);
                tv_anzhuangmendian.setText(response.getStoreName());

                params.clear();
                params.put("id", response.getStoreName());
                requestStoreDetail(params);
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
        OkhttpUtil.okHttpPostJson(URLs.ChangeTieDevice, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
