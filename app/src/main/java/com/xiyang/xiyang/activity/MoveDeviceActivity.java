package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cretin.tools.scancode.CaptureActivity;
import com.cretin.tools.scancode.config.ScanConfig;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;

/**
 * Created by Mr.Z on 2021/4/2.
 * 设备移位
 */
public class MoveDeviceActivity extends BaseActivity {
    LinearLayout ll_scan;
    TextView iv_scan;
    TextView tv_scan;
    EditText tv_dangqianfanghao, tv_xuanzefanghao;

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
                CommonUtil.gotoActivity(MoveDeviceActivity.this, RoomNoManagementActivity.class);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("设备移位");
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
                        iv_scan.setVisibility(View.GONE);
                        tv_scan.setText("SN号:" + result);
                    } else {
                        iv_scan.setVisibility(View.VISIBLE);
                        tv_scan.setVisibility(View.GONE);
                    }
                }
            }
        }
    }
}
