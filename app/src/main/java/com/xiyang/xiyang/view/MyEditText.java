package com.xiyang.xiyang.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.xiyang.xiyang.R;

/**
 * Created by Mr.Z on 2021/3/30.
 */
public class MyEditText extends androidx.appcompat.widget.AppCompatEditText implements View.OnFocusChangeListener {
    private Context context;

    public void setOnCheckInputListener(OnCheckInputListener onCheckInputListener) {
        this.onCheckInputListener = onCheckInputListener;
    }

    private OnCheckInputListener onCheckInputListener;
    private GradientDrawable drawable;

    /**
     * 检测输入是否符合要求的回调
     */
    public interface OnCheckInputListener {
        /**
         * 检测输入的方法
         *
         * @param v   点击的view
         * @param str 输入的字符串
         * @return 检测成功返回true, 检测失败返回false
         */
        boolean checkInput(View v, String str);
    }

    public MyEditText(Context context) {
        this(context, null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayerDrawable layerDrawable = (LayerDrawable) getBackground();
        drawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.shape);
        setOnFocusChangeListener(this);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
//            LogUtil.i("获取焦点");
            drawable.setStroke(1, getResources().getColor(R.color.green));
        } else {
//            LogUtil.i("失去焦点");
            drawable.setStroke(1, getResources().getColor(R.color.xian));
            /*if (onCheckInputListener != null && onCheckInputListener.checkInput(this, getText().toString().trim())) {
                drawable.setStroke(1, getResources().getColor(R.color.xian));

            } else if (onCheckInputListener == null) {
                drawable.setStroke(1, getResources().getColor(R.color.xian));
            } else {
                drawable.setStroke(1, getResources().getColor(R.color.xian));
            }*/
        }
    }
}
