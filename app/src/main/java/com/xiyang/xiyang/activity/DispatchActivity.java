package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;

/**
 * Created by Mr.Z on 2021/3/28.
 * 分派
 */
public class DispatchActivity extends BaseActivity {
    RelativeLayout rl_xuanzezhuangtai,rl_xuanzemendian,rl_dangqianBD,rl_xuanzexinBD,rl_xuanzesheng,
            rl_xuanzeleixing,rl_xuanzechengshi,rl_xuanzequxian,rl_dangqianCM,rl_xuanzexinCM,
            rl_dangqianBDM,rl_xuanzexinBDM,rl_shebeifencheng;
    EditText tv_xuanzezhuangtai,tv_xuanzemendian,tv_dangqianBD,tv_xuanzexinBD,tv_xuanzesheng,
            tv_xuanzeleixing,tv_xuanzechengshi,tv_xuanzequxian,tv_dangqianCM,tv_xuanzexinCM,
            tv_dangqianBDM,tv_xuanzexinBDM,tv_shebeifencheng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
    }

    @Override
    protected void initView() {
        rl_xuanzezhuangtai = findViewByID_My(R.id.rl_xuanzezhuangtai);
        rl_xuanzemendian = findViewByID_My(R.id.rl_xuanzemendian);
        rl_dangqianBD = findViewByID_My(R.id.rl_dangqianBD);
        rl_xuanzexinBD = findViewByID_My(R.id.rl_xuanzexinBD);
        rl_xuanzesheng = findViewByID_My(R.id.rl_xuanzesheng);
        rl_xuanzeleixing = findViewByID_My(R.id.rl_xuanzeleixing);
        rl_xuanzechengshi = findViewByID_My(R.id.rl_xuanzechengshi);
        rl_xuanzequxian = findViewByID_My(R.id.rl_xuanzequxian);
        rl_dangqianCM = findViewByID_My(R.id.rl_dangqianCM);
        rl_xuanzexinCM = findViewByID_My(R.id.rl_xuanzexinCM);
        rl_dangqianBDM = findViewByID_My(R.id.rl_dangqianBDM);
        rl_xuanzexinBDM = findViewByID_My(R.id.rl_xuanzexinBDM);
        rl_shebeifencheng = findViewByID_My(R.id.rl_shebeifencheng);

        tv_xuanzezhuangtai = findViewByID_My(R.id.tv_xuanzezhuangtai);
        tv_xuanzemendian = findViewByID_My(R.id.tv_xuanzemendian);
        tv_dangqianBD = findViewByID_My(R.id.tv_dangqianBD);
        tv_xuanzexinBD = findViewByID_My(R.id.tv_xuanzexinBD);
        tv_xuanzesheng = findViewByID_My(R.id.tv_xuanzesheng);
        tv_xuanzeleixing = findViewByID_My(R.id.tv_xuanzeleixing);
        tv_xuanzechengshi = findViewByID_My(R.id.tv_xuanzechengshi);
        tv_xuanzequxian = findViewByID_My(R.id.tv_xuanzequxian);
        tv_dangqianCM = findViewByID_My(R.id.tv_dangqianCM);
        tv_xuanzexinCM = findViewByID_My(R.id.tv_xuanzexinCM);
        tv_dangqianBDM = findViewByID_My(R.id.tv_dangqianBDM);
        tv_xuanzexinBDM = findViewByID_My(R.id.tv_xuanzexinBDM);
        tv_shebeifencheng = findViewByID_My(R.id.tv_shebeifencheng);

        changeUI();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {

    }

    /**
     * 改变UI布局
     */
    private void changeUI() {
        rl_xuanzezhuangtai.setVisibility(View.GONE);
        rl_xuanzemendian.setVisibility(View.GONE);
        rl_dangqianBD.setVisibility(View.GONE);
        rl_xuanzexinBD.setVisibility(View.GONE);
        rl_xuanzesheng.setVisibility(View.GONE);
        rl_xuanzeleixing.setVisibility(View.GONE);
        rl_xuanzechengshi.setVisibility(View.GONE);
        rl_xuanzequxian.setVisibility(View.GONE);
        rl_dangqianCM.setVisibility(View.GONE);
        rl_xuanzexinCM.setVisibility(View.GONE);
        rl_dangqianBDM.setVisibility(View.GONE);
        rl_xuanzexinBDM.setVisibility(View.GONE);
        rl_shebeifencheng.setVisibility(View.GONE);
        switch (localUserInfo.getUserJob()){
            case "rm":
                titleView.setTitle("分派CM");
                rl_xuanzesheng.setVisibility(View.VISIBLE);
                rl_xuanzeleixing.setVisibility(View.VISIBLE);
                rl_xuanzechengshi.setVisibility(View.VISIBLE);
                rl_dangqianCM.setVisibility(View.VISIBLE);
                rl_xuanzexinCM.setVisibility(View.VISIBLE);
                break;
            case "cm":
                titleView.setTitle("分派BDM");
                rl_xuanzechengshi.setVisibility(View.VISIBLE);
                rl_xuanzezhuangtai.setVisibility(View.VISIBLE);
                rl_xuanzequxian.setVisibility(View.VISIBLE);
                rl_dangqianBDM.setVisibility(View.VISIBLE);
                rl_xuanzexinBDM.setVisibility(View.VISIBLE);

                break;
            case "bdm":
                titleView.setTitle("分派BD");
                rl_xuanzezhuangtai.setVisibility(View.VISIBLE);
                rl_xuanzemendian.setVisibility(View.VISIBLE);
                rl_dangqianBD.setVisibility(View.VISIBLE);
                rl_xuanzexinBD.setVisibility(View.VISIBLE);
                break;
        }

    }

}
