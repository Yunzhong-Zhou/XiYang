package com.xiyang.xiyang.popupwindow;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.xiyang.xiyang.R;

import androidx.annotation.NonNull;

/**
 * 查看大图的dialog
 */

public class PhotoShowDialog_1 extends Dialog {
    private Context mContext;
    private String url;
    PhotoView photoView;

    public PhotoShowDialog_1(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public PhotoShowDialog_1(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    public PhotoShowDialog_1(Context context, String url) {
        this(context, R.style.Pic_Dialog);
        this.url = url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_zoomimg);
        init();
    }

    private void init() {
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        PhotoView photoView = findViewById(R.id.img);
        photoView.enable();
        /*RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.zanwutupian);*/
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                /*.apply(options)
                .transition(new DrawableTransitionOptions().crossFade())*/
                .into(photoView);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoShowDialog_1.this.dismiss();
            }
        });
    }
}
