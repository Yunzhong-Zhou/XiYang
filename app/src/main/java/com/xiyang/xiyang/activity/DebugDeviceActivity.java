package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.DeviceModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyLogger;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Mr.Z on 2021/4/3.
 * 调试设备
 */
public class DebugDeviceActivity extends BaseActivity {
    DeviceModel model;
    String deviceName = "";
    int i_dangwei = 1;
    ImageView iv_zhizhen;
    float fromDegrees = -90f;//开始角度
    float toDegrees = -90f;//结束角度
    TextView tv_dangwei, tv_pm, tv_wendu, tv_shidu;

    ImageView iv_zidong, iv_fulizi, iv_guangchuihua, iv_shuimian, iv_ertongsuo, iv_start;
    boolean is_zidong = false, is_fulizi = false, is_guangchuihua = false, is_shuimian = false, is_ertongsuo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debugdevice);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewById(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                params.clear();
                params.put("deviceName", deviceName);
                request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        tv_pm = findViewByID_My(R.id.tv_pm);
        tv_wendu = findViewByID_My(R.id.tv_wendu);
        tv_shidu = findViewByID_My(R.id.tv_shidu);

        iv_zhizhen = findViewByID_My(R.id.iv_zhizhen);
        rotateAnim(iv_zhizhen, fromDegrees, toDegrees);//初始化在左边
        tv_dangwei = findViewByID_My(R.id.tv_dangwei);

        iv_zidong = findViewByID_My(R.id.iv_zidong);
        iv_fulizi = findViewByID_My(R.id.iv_fulizi);
        iv_guangchuihua = findViewByID_My(R.id.iv_guangchuihua);
        iv_shuimian = findViewByID_My(R.id.iv_shuimian);
        iv_ertongsuo = findViewByID_My(R.id.iv_ertongsuo);
        iv_start = findViewByID_My(R.id.iv_start);

    }

    @Override
    protected void initData() {
        deviceName = getIntent().getStringExtra("deviceName");
        MyLogger.i(">>>>>>>" + deviceName);
        requestServer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
        params.put("deviceName", deviceName);
        request(params);
    }

    private void request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DebugDevice, params, headerMap, new CallBackUtil<DeviceModel>() {
            @Override
            public DeviceModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                finish();
            }

            @Override
            public void onResponse(DeviceModel response) {
                hideProgress();
                model = response;
                //档位
                if (response.getIotDeviceCurrentData().getWindSpeed().equals("0")) {
                    response.getIotDeviceCurrentData().setWindSpeed("1");
                }
                tv_dangwei.setText(response.getIotDeviceCurrentData().getWindSpeed());
                if (Integer.valueOf(response.getIotDeviceCurrentData().getWindSpeed()) > i_dangwei) {//实际档位比临时档位大，角度增加
                    fromDegrees = toDegrees;
                    toDegrees = fromDegrees + 45 * (Integer.valueOf(response.getIotDeviceCurrentData().getWindSpeed()) - i_dangwei);
                    rotateAnim(iv_zhizhen, fromDegrees, toDegrees);
                } else if (Integer.valueOf(response.getIotDeviceCurrentData().getWindSpeed()) < i_dangwei) {//实际档位比临时档位小，角度减少
                    fromDegrees = toDegrees;
                    toDegrees = fromDegrees - 45 * (i_dangwei - Integer.valueOf(response.getIotDeviceCurrentData().getWindSpeed()));
                    rotateAnim(iv_zhizhen, fromDegrees, toDegrees);
                }
                i_dangwei = Integer.valueOf(response.getIotDeviceCurrentData().getWindSpeed());


                //PM2.5
                tv_pm.setText(response.getIotDeviceCurrentData().getPmValue());
                //温度
                tv_wendu.setText(response.getIotDeviceCurrentData().getTempature() + "°");
                //湿度
                tv_shidu.setText(response.getIotDeviceCurrentData().getHumidity());
                //开关
                if (response.getIotDeviceCurrentData().getPowerSwitch().equals("0")) {//关闭
                    iv_start.setImageResource(R.mipmap.bg_device_start);
                } else {
                    iv_start.setImageResource(R.mipmap.bg_device_close);
                }
                //自动
                if (response.getIotDeviceCurrentData().getAutoModeSwitch().equals("0")) {//关闭
                    iv_zidong.setImageResource(R.mipmap.ic_debugdevice1_1);
                } else {
                    iv_zidong.setImageResource(R.mipmap.ic_debugdevice1_0);
                }
                //负离子
                if (response.getIotDeviceCurrentData().getIonsSwitch().equals("0")) {//关闭
                    iv_fulizi.setImageResource(R.mipmap.ic_debugdevice2_1);
                } else {
                    iv_fulizi.setImageResource(R.mipmap.ic_debugdevice2_0);
                }
                //光催化
                if (response.getIotDeviceCurrentData().getPhotocatalysisSwitch().equals("0")) {//关闭
                    iv_guangchuihua.setImageResource(R.mipmap.ic_debugdevice3_1);
                } else {
                    iv_guangchuihua.setImageResource(R.mipmap.ic_debugdevice3_0);
                }
                //睡眠
                if (response.getIotDeviceCurrentData().getSleep().equals("0")) {//关闭
                    iv_shuimian.setImageResource(R.mipmap.ic_debugdevice4_1);
                } else {
                    iv_shuimian.setImageResource(R.mipmap.ic_debugdevice4_0);
                }
                //儿童锁
                if (response.getIotDeviceCurrentData().getChildLockSwitch().equals("0")) {//关闭
                    iv_ertongsuo.setImageResource(R.mipmap.ic_debugdevice5_1);
                } else {
                    iv_ertongsuo.setImageResource(R.mipmap.ic_debugdevice5_0);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        params.clear();
        switch (v.getId()) {
            /*case R.id.left_btn:
                finish();
                break;*/
            case R.id.iv_start:
                //开启设备
                if (model.getIotDeviceCurrentData().getPowerSwitch().equals("0")) {//关闭
                    params.put("powerSwitch", "1");
                } else {
                    params.put("powerSwitch", "0");
                }
                break;

            case R.id.iv_jian:
                //减档
                if (Integer.valueOf(model.getIotDeviceCurrentData().getWindSpeed()) > 1) {
                    /*i_dangwei--;
                    fromDegrees = toDegrees;
                    toDegrees = fromDegrees - 45;
                    rotateAnim(iv_zhizhen, fromDegrees, toDegrees);*/
                    params.put("windSpeed", Integer.valueOf(model.getIotDeviceCurrentData().getWindSpeed()) - 1 + "");

                } else {
                    return;
                }
//                tv_dangwei.setText(i_dangwei + "");

                break;
            case R.id.iv_jia:
                //加档
                if (Integer.valueOf(model.getIotDeviceCurrentData().getWindSpeed()) < 5) {
                    /*i_dangwei++;
                    fromDegrees = toDegrees;
                    toDegrees = fromDegrees + 45;
                    rotateAnim(iv_zhizhen, fromDegrees, toDegrees);*/

                    params.put("windSpeed", Integer.valueOf(model.getIotDeviceCurrentData().getWindSpeed()) + 1 + "");

                } else {
                    return;
                }
//                tv_dangwei.setText(i_dangwei + "");

                break;

            case R.id.iv_zidong:
                //自动
                /*is_zidong = !is_zidong;
                if (is_zidong) iv_zidong.setImageResource(R.mipmap.ic_debugdevice1_1);
                else iv_zidong.setImageResource(R.mipmap.ic_debugdevice1_0);*/

                if (model.getIotDeviceCurrentData().getAutoModeSwitch().equals("0")) {//关闭
                    params.put("autoModeSwitch", 1 + "");
                } else {
                    params.put("autoModeSwitch", 0 + "");
                }
                break;
            case R.id.iv_fulizi:
                //负离子
                /*is_fulizi = !is_fulizi;
                if (is_fulizi) iv_fulizi.setImageResource(R.mipmap.ic_debugdevice2_1);
                else iv_fulizi.setImageResource(R.mipmap.ic_debugdevice2_0);*/

                if (model.getIotDeviceCurrentData().getIonsSwitch().equals("0")) {//关闭
                    params.put("ionsSwitch", 1 + "");
                } else {
                    params.put("ionsSwitch", 0 + "");
                }
                break;
            case R.id.iv_guangchuihua:
                //光催化
                /*is_guangchuihua = !is_guangchuihua;
                if (is_guangchuihua) iv_guangchuihua.setImageResource(R.mipmap.ic_debugdevice3_1);
                else iv_guangchuihua.setImageResource(R.mipmap.ic_debugdevice3_0);*/

                if (model.getIotDeviceCurrentData().getPhotocatalysisSwitch().equals("0")) {//关闭
                    params.put("photocatalysisSwitch", 1 + "");
                } else {
                    params.put("photocatalysisSwitch", 0 + "");
                }
                break;
            case R.id.iv_shuimian:
                //睡眠
                /*is_shuimian = !is_shuimian;
                if (is_shuimian) iv_shuimian.setImageResource(R.mipmap.ic_debugdevice4_1);
                else iv_shuimian.setImageResource(R.mipmap.ic_debugdevice4_0);*/

                if (model.getIotDeviceCurrentData().getSleep().equals("0")) {//关闭
                    params.put("sleep", 1 + "");
                } else {
                    params.put("sleep", 0 + "");
                }
                break;
            case R.id.iv_ertongsuo:
                //儿童锁
                /*is_ertongsuo = !is_ertongsuo;
                if (is_ertongsuo) iv_ertongsuo.setImageResource(R.mipmap.ic_debugdevice5_1);
                else iv_ertongsuo.setImageResource(R.mipmap.ic_debugdevice5_0);*/
                if (model.getIotDeviceCurrentData().getChildLockSwitch().equals("0")) {//关闭
                    params.put("childLockSwitch", 1 + "");
                } else {
                    params.put("childLockSwitch", 0 + "");
                }

                break;
        }

        showProgress(true, getString(R.string.app_loading1));
        params.put("deviceName", deviceName);
//            params1.put("properties", object.toString());
        RequestSetUp(params);
//        request(params);

    }


    @Override
    protected void updateView() {
//        titleView.setTitle("调试设备");
        titleView.setVisibility(View.GONE);
    }

    /**
     * 选转指针图片
     * 1）LinearInterpolator：动画从开始到结束，变化率是线性变化。
     * （2）AccelerateInterpolator：动画从开始到结束，变化率是一个加速的过程。
     * （3）DecelerateInterpolator：动画从开始到结束，变化率是一个减速的过程。
     * （4）CycleInterpolator：动画从开始到结束，变化率是循环给定次数的正弦曲线。
     * （5）AccelerateDecelerateInterpolator：动画从开始到结束，变化率是先加速后减速的过程。
     */
    public void rotateAnim(ImageView iv, float fromDegrees, float toDegrees) {

        Animation anim = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1f);
        anim.setFillAfter(true); // 设置保持动画最后的状态
        anim.setDuration(1000); // 设置动画时间
        anim.setInterpolator(new LinearInterpolator()); // 设置插入器
        iv.startAnimation(anim);
    }

    /**
     * 设置设备属性值
     *
     * @param params
     */
    private void RequestSetUp(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DebugDevice, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //这里写延时后执行的操作
                        params.clear();
                        params.put("deviceName", deviceName);
                        request(params);
//                        hideProgress();
                    }

                }, 1 * 1000);
            }
        });
    }

}
