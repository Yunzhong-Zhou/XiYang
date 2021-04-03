package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.utils.CommonUtil;

/**
 * Created by Mr.Z on 2021/4/3.
 * 调试设备
 */
public class DebugDeviceActivity extends BaseActivity {
    int i_dangwei = 1;
    ImageView iv_zhizhen;
    float fromDegrees = -90f;//开始角度
    float toDegrees = -90f;//结束角度
    TextView tv_dangwei;

    ImageView iv_zidong, iv_fulizi, iv_guangchuihua, iv_shuimian, iv_ertongsuo;
    boolean is_zidong = false, is_fulizi = false, is_guangchuihua = false, is_shuimian = false, is_ertongsuo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debugdevice);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        iv_zhizhen = findViewByID_My(R.id.iv_zhizhen);
        rotateAnim(iv_zhizhen, fromDegrees, toDegrees);//初始化在左边
        tv_dangwei = findViewByID_My(R.id.tv_dangwei);

        iv_zidong = findViewByID_My(R.id.iv_zidong);
        iv_fulizi = findViewByID_My(R.id.iv_fulizi);
        iv_guangchuihua = findViewByID_My(R.id.iv_guangchuihua);
        iv_shuimian = findViewByID_My(R.id.iv_shuimian);
        iv_ertongsuo = findViewByID_My(R.id.iv_ertongsuo);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;

            case R.id.iv_jian:
                //减档
                if (i_dangwei > 1) {
                    i_dangwei--;
                    fromDegrees = toDegrees;
                    toDegrees = fromDegrees - 45;
                    rotateAnim(iv_zhizhen, fromDegrees, toDegrees);
                }
                tv_dangwei.setText(i_dangwei + "");

                break;
            case R.id.iv_jia:
                //加档
                if (i_dangwei < 5) {
                    i_dangwei++;
                    fromDegrees = toDegrees;
                    toDegrees = fromDegrees + 45;
                    rotateAnim(iv_zhizhen, fromDegrees, toDegrees);
                }
                tv_dangwei.setText(i_dangwei + "");

                break;

            case R.id.iv_zidong:
                //自动
                is_zidong = !is_zidong;
                if (is_zidong) iv_zidong.setImageResource(R.mipmap.ic_debugdevice1_1);
                else iv_zidong.setImageResource(R.mipmap.ic_debugdevice1_0);
                break;
            case R.id.iv_fulizi:
                //负离子
                is_fulizi = !is_fulizi;
                if (is_fulizi) iv_fulizi.setImageResource(R.mipmap.ic_debugdevice2_1);
                else iv_fulizi.setImageResource(R.mipmap.ic_debugdevice2_0);
                break;
            case R.id.iv_guangchuihua:
                //光催化
                is_guangchuihua = !is_guangchuihua;
                if (is_guangchuihua) iv_guangchuihua.setImageResource(R.mipmap.ic_debugdevice3_1);
                else iv_guangchuihua.setImageResource(R.mipmap.ic_debugdevice3_0);
                break;
            case R.id.iv_shuimian:
                //睡眠
                is_shuimian = !is_shuimian;
                if (is_shuimian) iv_shuimian.setImageResource(R.mipmap.ic_debugdevice4_1);
                else iv_shuimian.setImageResource(R.mipmap.ic_debugdevice4_0);
                break;
            case R.id.iv_ertongsuo:
                //儿童锁
                is_ertongsuo = !is_ertongsuo;
                if (is_ertongsuo) iv_ertongsuo.setImageResource(R.mipmap.ic_debugdevice5_1);
                else iv_ertongsuo.setImageResource(R.mipmap.ic_debugdevice5_0);
                break;
        }
    }

    @Override
    protected void initData() {

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
}
