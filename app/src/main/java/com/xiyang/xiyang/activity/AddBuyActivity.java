package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.utils.CommonUtil;

/**
 * Created by Mr.Z on 2021/3/28.
 * 采购申请
 */
public class AddBuyActivity extends BaseActivity {
    EditText editText1,editText2,editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbuy);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.editText2:
                //选择提货时间
                CommonUtil.selectDate2YMD(AddBuyActivity.this,
                        "请选择提货时间", editText2, editText2.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("采购申请");
    }

}
