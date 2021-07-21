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
import com.xiyang.xiyang.model.DeviceRoomModel;
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
 * 设备移位
 */
public class MoveDeviceActivity extends BaseActivity {
    StoreDetailModel model_sdm;
    LinearLayout ll_scan;
    TextView iv_scan;
    TextView tv_scan;
    EditText tv_dangqianfanghao, tv_xuanzefanghao;

    String deviceName = "",oldRoomId ="", newRoomId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movedevice);
    }

    @Override
    protected void initView() {
        ll_scan = findViewByID_My(R.id.ll_scan);
        iv_scan = findViewByID_My(R.id.iv_scan);
        tv_scan = findViewByID_My(R.id.tv_scan);
        tv_dangqianfanghao = findViewByID_My(R.id.tv_dangqianfanghao);
        tv_xuanzefanghao = findViewByID_My(R.id.tv_xuanzefanghao);

    }

    @Override
    protected void initData() {
        model_sdm = (StoreDetailModel) getIntent().getSerializableExtra("StoreDetailModel");
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
                if (model_sdm != null) {
                    Intent intent3 = new Intent(MoveDeviceActivity.this, RoomNoManagementActivity.class);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("requestCode", Constant.SELECT_ROOMNO);
                    bundle3.putSerializable("StoreDetailModel", model_sdm);
                    intent3.putExtras(bundle3);
                    startActivityForResult(intent3, Constant.SELECT_ROOMNO);
                }/* else {
                    myToast("请先选择门店");
                }*/
                break;

            case R.id.tv_confirm:
                //确认安装
                if (match()) {
                    this.showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("hostName", deviceName);
                    params.put("storeId", model_sdm.getStoreInfo().getId());
                    params.put("roomId", oldRoomId);
                    params.put("newRoomId", newRoomId);
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
        if (TextUtils.isEmpty(newRoomId)) {
            myToast("请选择房号");
            return false;
        }

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("设备移位");
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
                        if (!result.equals("")) {
                            try {
                                JSONObject mJsonObject = new JSONObject(result);
                                deviceName = mJsonObject.getString("deviceName");
                                iv_scan.setVisibility(View.GONE);
                                tv_scan.setText("SN号:" + deviceName);

                                showProgress(true, getString(R.string.app_loading2));
                                params.clear();
//                                params.put("deviceName", deviceName);
                                requestDeviceRoom(params, deviceName);

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
                case Constant.SELECT_ROOMNO:
                    //选择房号
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        newRoomId = bundle.getString("roomId");
                        tv_xuanzefanghao.setText(bundle.getString("roomName"));
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
    private void requestDeviceRoom(HashMap<String, String> params, String deviceName) {
        OkhttpUtil.okHttpGet(URLs.DeviceRoom+deviceName, params, headerMap, new CallBackUtil<DeviceRoomModel>() {
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
                oldRoomId = response.getRoomId();
                tv_dangqianfanghao.setText(response.getRoomName());
            }
        });
    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.ChangeRoomDevice, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
