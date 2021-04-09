package com.xiyang.xiyang.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.TimeUtils;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.AddContractActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 页面跳转辅助类
 */

public class CommonUtil {

    /**
     * 跳转页面，动画效果
     *
     * @param curActivity    当前活动
     * @param targetActivity 目标活动
     * @param finish         是否结束当前活动
     */
    public static void gotoActivity(Activity curActivity,
                                    Class<?> targetActivity, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        curActivity.startActivity(intent);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);
        if (finish) {
            curActivity.finish();
        }
    }

    public static void gotoActivity(Context curActivity,
                                    Class<?> targetActivity) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        curActivity.startActivity(intent);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);
        /*if (finish) {
            curActivity.finish();
		}*/
    }

    /**
     * 跳转页面--带数据传递
     *
     * @param curActivity    当前活动
     * @param targetActivity 目标活动
     * @param bundle         需要传递的数据
     * @param finish         是否结束当前活动
     */
    public static void gotoActivityWithData(Activity curActivity,
                                            Class<?> targetActivity, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        intent.putExtras(bundle);
        curActivity.startActivity(intent);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);

        if (finish) {
            curActivity.finish();
        }
    }

    public static void gotoActivityWithData(Context curActivity,
                                            Class<?> targetActivity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        intent.putExtras(bundle);
        curActivity.startActivity(intent);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);

		/*if (finish) {
            curActivity.finish();
		}*/
    }

    /**
     * 跳转页面--带数据传递
     *
     * @param curActivity    当前活动
     * @param targetActivity 目标活动
     * @param bundle         需要传递的数据
     * @param finish         是否结束当前活动
     */
    public static void gotoActivityWithDataForResult(Activity curActivity,
                                                     Class<?> targetActivity, Bundle bundle, int RequestCode,
                                                     boolean finish) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        intent.putExtras(bundle);
        curActivity.startActivityForResult(intent, RequestCode);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);

        if (finish) {
            curActivity.finish();
        }
    }

    /**
     * 跳转页面，动画效果
     *
     * @param fragment       当前fragment
     * @param targetActivity 目标活动
     * @param Code           int 类型数据
     * @param finish         是否结束当前活动
     */
    public static void gotoActivityForResult_fragment(Fragment fragment,
                                                      Class<?> targetActivity, int Code, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(fragment.getActivity(), targetActivity);
        fragment.startActivityForResult(intent, Code);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);

        if (finish) {
            fragment.getActivity().finish();
        }
    }

    /**
     * 跳转页面，动画效果
     *
     * @param curActivity    当前活动
     * @param targetActivity 目标活动
     * @param Code           int 类型数据
     * @param finish         是否结束当前活动
     */
    public static void gotoActivityForResult(Activity curActivity,
                                             Class<?> targetActivity, int Code, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        curActivity.startActivityForResult(intent, Code);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);

        if (finish) {
            curActivity.finish();
        }
    }

    /**
     * 跳转页面--结束中间活动
     *
     * @param curActivity    当前活动
     * @param targetActivity 目标活动
     * @param refresh        是否刷新要跳转界面
     */
    public static void gotoActivityWithFinishOtherAll(Activity curActivity,
                                                      Class<?> targetActivity, boolean refresh) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        if (!refresh) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);// 设置不要刷新将要跳到的界面
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// 它可以关掉所要到的界面中间的activity
        curActivity.startActivity(intent);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);
        if (refresh) {
            curActivity.finish();
        }
    }

    public static void gotoActivityWithFinishOtherAll(Context curActivity,
                                                      Class<?> targetActivity, boolean refresh) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        if (!refresh) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);// 设置不要刷新将要跳到的界面
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// 它可以关掉所要到的界面中间的activity
        curActivity.startActivity(intent);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);

        /*if (refresh) {
            curActivity.finish();
        }*/
    }

    /**
     * 跳转页面--结束中间活动 -传值
     *
     * @param curActivity    当前活动
     * @param targetActivity 目标活动
     * @param refresh        是否刷新要跳转界面
     */
    public static void gotoActivityWithFinishOtherAllAndData(Activity curActivity,
                                                             Class<?> targetActivity, Bundle bundle, boolean refresh) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(curActivity, targetActivity);
        if (!refresh) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);// 设置不要刷新将要跳到的界面
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// 它可以关掉所要到的界面中间的activity
        curActivity.startActivity(intent);
//		curActivity.overridePendingTransition(R.anim.slide_left_in,
//				R.anim.slide_left_out);

        if (refresh) {
            curActivity.finish();
        }
    }

    /**
     * 测量 View
     *
     * @param measureSpec
     * @param defaultSize View 的默认大小
     * @return
     */
    public static int measure(int measureSpec, int defaultSize) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    /**
     * 反转数组
     *
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> T[] reverse(T[] arrays) {
        if (arrays == null) {
            return null;
        }
        int length = arrays.length;
        for (int i = 0; i < length / 2; i++) {
            T t = arrays[i];
            arrays[i] = arrays[length - i - 1];
            arrays[length - i - 1] = t;
        }
        return arrays;
    }

    /**
     * dp转换为px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        //MyLogger.i(scale+"");
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px装换成dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * px转换为sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 验证字符串是否为网址
     */
    public static boolean isUrl(String url) {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(url);
        return matcher.matches();
    }

    /**
     * 验证字符串是否为数字
     */
    public static boolean isNumeric(String url) {
        Pattern patt = Pattern.compile("[0-9]*");
        Matcher matcher = patt.matcher(url);
        return matcher.matches();
    }


    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(
                    mContext.getPackageName(), 0);
            return info.versionCode;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    /**
     * 获取版本名
     *
     * @return
     */
    public static String getVersionName(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(
                    mContext.getPackageName(), 0);
            return info.versionName;
        } catch (NameNotFoundException e) {
            return "1.0";
        }
    }

    public static boolean isTablet(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2)
                + Math.pow(dm.heightPixels, 2));
        double physicalSize = diagonalPixels / (160 * dm.density);
        return physicalSize > 7;
    }

    /**
     * 设置margins
     *
     * @return
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v
                    .getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static void toast(String str, Context context) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    // 获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    // 获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    /**
     * 隐藏软键盘 - Fragment
     */
    public static void hideSoftKeyboard_fragment(View v, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //保留两位小数
    public static String twoPointConversion(double price) {
        MyLogger.i(">>>>>>>>>>>>>>>>>>>>>>>" + price);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String str = decimalFormat.format(price);
        return str;
    }


    /**
     * date转年月日
     * @param date
     * @return
     */
    public static String date2ymd(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * date转年月日时分
     * @param date
     * @return
     */
    public static String date2ymdhm(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
    /**
     * date转年月日时分秒
     * @param date
     * @return
     */
    public static String date2ymdhms(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14 16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14 16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14 16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate(Long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
//        int i = Integer.parseInt(time);
//        String times = sdr.format(new Date(i * 1000L));
        String times = sdr.format(new Date(lcc));

        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14 16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate1(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM/dd HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14 16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate2(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14 16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate3(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14 16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate4(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
//        String times = sdr.format(new Date(lcc));
        return times;

    }


    /**
     * 毫秒转时分秒 HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String timedate3(Long time) {
        {
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = time / dd;
            Long hour = (time - day * dd) / hh;//24小时制
            Long hour1 = time / hh;
            Long minute = (time - day * dd - hour * hh) / mi;
            Long second = (time - day * dd - hour * hh - minute * mi) / ss;
            Long milliSecond = time - day * dd - hour * hh - minute * mi - second * ss;

            StringBuffer sb = new StringBuffer();
//            if(day > 0) {
//                sb.append(day+"天");//天
//            }
//            if(hour > 0) {
//            sb.append(String.format("%02d", hour) + ":");//小时-取两位小数
//            sb.append(Integer.valueOf(hour1+"") + ":");//小时-取整
//            }
//            if(minute > 0) {
//            sb.append(String.format("%02d", minute) + ":");//分
//            }
//            if(second > 0) {
//            sb.append(String.format("%02d", second) + "");//秒
            sb.append(String.format("%02d", time / ss) + "");//秒

//            }
            /*if(milliSecond > 0) {
                sb.append(milliSecond+"毫秒");//毫秒
            }*/
            return sb.toString();
        }
    }

    /**
     * 毫秒转时分秒 HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String timedate4(Long time, Context mContext) {
        {
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = time / dd;
            Long hour = (time - day * dd) / hh;//24小时制
            Long hour1 = time / hh;
            Long minute = (time - day * dd - hour * hh) / mi;
            Long second = (time - day * dd - hour * hh - minute * mi) / ss;
            Long milliSecond = time - day * dd - hour * hh - minute * mi - second * ss;

            StringBuffer sb = new StringBuffer();
//            if(day > 0) {
//                sb.append(day+"天");//天
//            }

            if (hour > 0) {
                sb.append(String.format("%02d", hour) + ":");//小时-取两位小数
//            sb.append(Integer.valueOf(hour1+"") + ":");//小时-取整
            }
            if (minute > 0) {
                sb.append(String.format("%02d", minute) + ":");//分
            } else {
                sb.append("00:");//分
            }
//            if (second > 0) {
            sb.append(String.format("%02d", second) + "s");//秒
//            }
            /*if(milliSecond > 0) {
                sb.append(milliSecond+"毫秒");//毫秒
            }*/
            return sb.toString();
        }
    }

    public static Bitmap stringtoBitmap(String string) {
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static String bitmaptoString(Bitmap bitmap) {
        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机状态栏高度
     */
    public static double getStatusBarHeight(Context context) {
        double statusBarHeight = Math.ceil(25 * context.getResources().getDisplayMetrics().density);
        return statusBarHeight;
    }

    /**
     * 设置年月日选择器
     * 年月日
     */
    public static void selectDate2YMD(Context context,String string, TextView textView, String date) {
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH);
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        if (!date.equals("")){
            String[] strArr = date.split("-");//拆分日期 得到年月日
            selectedDate.set(Integer.valueOf(strArr[0]),Integer.valueOf(strArr[1])-1,Integer.valueOf(strArr[2]));
        }

        //正确设置方式 原因：注意事项有说明
//        startDate.set(year, month, day);
        startDate.set(1900, 0, 1);

        //当前时间加3天
//        calendar.add(Calendar.YEAR, 100);
        endDate.set(2100, 11, 1);
        /*endDate.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));*/


        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
//                textView.setText(CommonUtil.date2ymd(date));
                textView.setText( TimeUtils.date2String(date,"yyyy-MM-dd"));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(15)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText(string)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(context.getResources().getColor(R.color.black2))//标题文字颜色
                .setSubmitColor(context.getResources().getColor(R.color.green))//确定按钮文字颜色
                .setCancelColor(context.getResources().getColor(R.color.green))//取消按钮文字颜色
                .setTitleBgColor(context.getResources().getColor(R.color.black5))//标题背景颜色 Night mode
                .setBgColor(context.getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pvTime.show();
    }

}
