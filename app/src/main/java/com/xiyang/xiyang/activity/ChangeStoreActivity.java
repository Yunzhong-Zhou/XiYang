package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.StoreDetailModel;
import com.xiyang.xiyang.utils.CommonUtil;

/**
 * Created by Mr.Z on 2021/3/28.
 * 修改门店
 */
public class ChangeStoreActivity extends BaseActivity {
    StoreDetailModel model_sdm;
    EditText textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changestore);
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView10 = findViewByID_My(R.id.textView10);

        imageView1 = findViewByID_My(R.id.imageView1);
    }

    @Override
    protected void initData() {
        model_sdm = (StoreDetailModel) getIntent().getSerializableExtra("StoreDetailModel");
        textView1.setText(model_sdm.getBase().getName());
//        textView2.setText(model_sdm.getBase().getchildName());
//        textView3.setHint(model_sdm.getBase().getaccount);
        textView4.setText(model_sdm.getBase().getContact());
        textView5.setText(model_sdm.getBase().getContactMobile());
//        textView6.setHint(model_sdm.getBase().get);
//        textView7.setText(model_sdm.getBase().get);
        textView8.setText(model_sdm.getBase().getAddress());
        textView9.setText(model_sdm.getBase().getBusinessHours());
        textView10.setText(model_sdm.getBase().getIsTransfter());

        Glide.with(ChangeStoreActivity.this)
                .load(model_sdm.getImage())
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new
                        RoundedCorners(CommonUtil.dip2px(ChangeStoreActivity.this, 10))))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.headimg)//加载失败
                .into(imageView1);//加载图片

    }

    @Override
    protected void updateView() {
        titleView.setTitle("修改门店信息");
    }

}
