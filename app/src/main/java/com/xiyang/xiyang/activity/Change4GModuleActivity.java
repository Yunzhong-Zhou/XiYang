package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cretin.tools.scancode.CaptureActivity;
import com.cretin.tools.scancode.config.ScanConfig;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.DeviceRoomModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.MyLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/7/27.
 * 更换4G模组
 */
public class Change4GModuleActivity extends BaseActivity {
    int type = 1;//1、扫设备  2、扫4G码
    LinearLayout ll_scan;
    TextView iv_scan;
    TextView tv_scan;
    EditText tv_old, tv_new;
    ImageView imageView1;

    String deviceName = "", old4GId = "", new4GId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change4gmodul);
    }

    @Override
    protected void initView() {
        ll_scan = findViewByID_My(R.id.ll_scan);
        iv_scan = findViewByID_My(R.id.iv_scan);
        tv_scan = findViewByID_My(R.id.tv_scan);
        tv_old = findViewByID_My(R.id.tv_old);
        tv_new = findViewByID_My(R.id.tv_new);
        imageView1 = findViewByID_My(R.id.imageView1);

    }

    @Override
    protected void initData() {
        deviceName = getIntent().getStringExtra("deviceName");
        if (deviceName!=null&& !deviceName.equals("")){
            iv_scan.setVisibility(View.GONE);
            tv_scan.setText("SN号:" + deviceName);

            showProgress(true, getString(R.string.app_loading2));
            params.clear();
//                                params.put("deviceName", deviceName);
            requestDevice4G(params, deviceName);
        }

       /* old4GId = getIntent().getStringExtra("4GModule");
        if (old4GId !=null&&!old4GId.equals("")){
            tv_old.setText(old4GId);
        }*/

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_scan:
                //扫描
                type = 1;//1、扫设备  2、扫4G码
                ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(this, config);

                break;
            case R.id.imageView1:
                //选择房号
                type = 2;//1、扫设备  2、扫4G码
                ScanConfig config1 = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(this, config1);
                break;

            case R.id.tv_confirm:
                //确认安装
                if (match()) {
                    this.showProgress(true, getString(R.string.app_loading1));
                    params.clear();
                    /*params.put("hostName", deviceName);
                    params.put("storeId", model_sdm.getStoreInfo().getId());
                    params.put("roomId", oldRoomId);
                    params.put("newRoomId", newRoomId);*/
                    requestUpData(params, URLs.Change4G + deviceName + "/" + new4GId);
                }
                break;
        }
    }

    private boolean match() {
        if (TextUtils.isEmpty(deviceName)) {
            myToast("请先扫码");
            return false;
        }
        new4GId = tv_new.getText().toString().trim();
        if (TextUtils.isEmpty(new4GId)) {
            myToast("请输入新4G序列号");
            return false;
        }

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("更换4G模组");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CaptureActivity.REQUEST_CODE_SCAN:
                    //二维码扫码
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                        //{"deviceName": "641708882ef84e09995d70440e12ebf9"}
                        MyLogger.i("扫码返回", result);
                        if (type == 1) {//扫设备
                            if (!result.equals("")) {
                                try {
                                    JSONObject mJsonObject = new JSONObject(result);
                                    deviceName = mJsonObject.getString("deviceName");
                                    iv_scan.setVisibility(View.GONE);
                                    tv_scan.setText("SN号:" + deviceName);

                                    showProgress(true, getString(R.string.app_loading2));
                                    params.clear();
//                                params.put("deviceName", deviceName);
                                    requestDevice4G(params, deviceName);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    myToast("解析出错");
                                }
                            } else {
                                iv_scan.setVisibility(View.VISIBLE);
                                tv_scan.setVisibility(View.GONE);
                            }
                        } else {
                            //扫4G模块
                            tv_new.setText(result);
                        }

                    }
                    break;
            }
        }
    }

    /**
     * 获取设备房号
     *
     * @param params
     */
    private void requestDevice4G(HashMap<String, String> params, String deviceName) {
        OkhttpUtil.okHttpGet(URLs.Device4G + deviceName, params, headerMap, new CallBackUtil<DeviceRoomModel>() {
            @Override
            public DeviceRoomModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(DeviceRoomModel response) {
                hideProgress();
                old4GId = response.getModuleName();
                tv_old.setText(response.getModuleName());
            }
        });
    }

    private void requestUpData(Map<String, String> params, String url) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<String>() {
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
