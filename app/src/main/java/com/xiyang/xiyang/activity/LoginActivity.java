package com.xiyang.xiyang.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.cy.dialog.BaseDialog;
import com.maning.updatelibrary.InstallUtils;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.CodeModel;
import com.xiyang.xiyang.model.LoginModel;
import com.xiyang.xiyang.model.UpgradeModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.LocalUserInfo;

import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import okhttp3.Call;
import okhttp3.Response;


/**
 * 登录
 */
public class LoginActivity extends BaseActivity {
    int type = 1;//1、验证码 2、密码
    String code = "", resultCode = "";
    private EditText editText1, editText2, editText3;
    private TextView textView1, textView2, tv_yzm, tv_mima;
    private RelativeLayout rl_mima, rl_yzm;

    private String phonenum = "", password = "";

    private TimeCount time;

    ImageView iv_gouxuan;
    boolean isGouXuan = false;

    //更新
    UpgradeModel model_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        mImmersionBar.reset().init();
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
//        CommonUtil.setMargins(findViewByID_My(R.id.headView),0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);


        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (time != null)
            time.cancel();
    }

    @Override
    protected void initView() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

        tv_yzm = findViewByID_My(R.id.tv_yzm);
        tv_mima = findViewByID_My(R.id.tv_mima);
        rl_mima = findViewByID_My(R.id.rl_mima);
        rl_yzm = findViewByID_My(R.id.rl_yzm);

        iv_gouxuan = findViewByID_My(R.id.iv_gouxuan);
    }

    @Override
    protected void initData() {
        /*Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        RequestUpgrade(params);//检查更新*/

        /*byte[] mBytes = null;
        String mString = "{阿达大as家阿sdf什顿附asd件好久}";
        AES mAes = new AES();
        try {
            mBytes = mString.getBytes("UTF-8");
        } catch (Exception e) {
            Log.i("qing", "MainActivity----catch");
        }
        String enString = mAes.encrypt(mBytes);
        MyLogger.i("加密后：" + enString);
        String deString = mAes.decrypt("P9ezA6lsRKVID383Rg5mwQ==");
        MyLogger.i("解密后：" + deString);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                //获取验证码
                phonenum = editText1.getText().toString().trim();
                if (TextUtils.isEmpty(phonenum)) {
                    myToast("请输入手机号");
                } else {
                    showProgress(true, "正在获取短信验证码...");
                    textView1.setClickable(false);
                    params.clear();
                    params.put("mobile", phonenum);
                    params.put("appType", "1");
                    params.put("type", "1");
                    RequestCode(params);//获取验证码
                }
                break;

            case R.id.textView2:
                //确认登录
                if (match()) {
                    this.showProgress(true, "正在登录，请稍候...");

                    textView2.setClickable(false);
                    params.clear();

                    /*headerMap.clear();
                    headerMap.put("Authorization","Basic YWRtaW46YWRtaW4=");
                    headerMap.put("isToken","false");*/
                    if (type == 1) {
                        params.put("mobile", phonenum);
                        params.put("code", code);
                        params.put("appType", "1");
                        params.put("type", "1");
                        RequestLogin(params);//登录
                    } else {
                        params.put("username", phonenum);
                        params.put("password", password);
                        params.put("appType", "2");
                        params.put("type", "1");
                        RequestLogin(params);//登录
                    }
                }
//                CommonUtil.gotoActivity(LoginActivity.this, MainActivity.class, true);
                break;
            /*case R.id.image_wechat:
                //微信登录
                if (!api.isWXAppInstalled()) {
                    myToast("您的设备未安装微信客户端");
                } else {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    api.sendReq(req);
                    finish();
                }
                break;*/
            case R.id.iv_gouxuan:
                //勾选图片
                isGouXuan = !isGouXuan;
                if (isGouXuan) {
                    iv_gouxuan.setImageResource(R.mipmap.xuanzhong);
                } else {
                    iv_gouxuan.setImageResource(R.mipmap.weixuan);
                }
                break;
            case R.id.tv_tiaoli:
                Bundle bundle = new Bundle();
                bundle.putString("url", URLs.HOST + "/single/h5/register?user_hash=" + localUserInfo.getUserHash());
                CommonUtil.gotoActivityWithData(LoginActivity.this, WebContentActivity.class, bundle, false);
                break;
            case R.id.tv_yzm:
                //验证码
                type = 1;//1、验证码 2、密码
                editText1.setInputType(InputType.TYPE_CLASS_PHONE);
                editText1.setHint("请输入手机号");
                tv_yzm.setBackgroundResource(R.drawable.yuanjiao_15_lvsejianbian);
                tv_mima.setBackgroundResource(R.color.transparent);
                rl_yzm.setVisibility(View.VISIBLE);
                rl_mima.setVisibility(View.GONE);

                break;
            case R.id.tv_mima:
                //密码
                type = 2;//1、验证码 2、密码
                editText1.setInputType(InputType.TYPE_CLASS_TEXT);
                editText1.setHint("请输入账号");
                tv_yzm.setBackgroundResource(R.color.transparent);
                tv_mima.setBackgroundResource(R.drawable.yuanjiao_15_lvsejianbian);
                rl_yzm.setVisibility(View.GONE);
                rl_mima.setVisibility(View.VISIBLE);
                break;
        }
    }

    //登录
    private void RequestLogin(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.Login2, GsonUtils.toJson(params), headerMap, new CallBackUtil<LoginModel>() {
            @Override
            public LoginModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                textView2.setClickable(true);
                myToast(err);
            }

            @Override
            public void onResponse(LoginModel response) {
                textView2.setClickable(true);
                hideProgress();

                //保存Token
                localUserInfo.setToken(response.getToken());
                //保存Token类型
                localUserInfo.setTokenType(response.getTokentype());
                //保存电话号码
                localUserInfo.setPhoneNumber(response.getMobile());
                //保存是否认证
//                localUserInfo.setIsVerified(response.getIs_certification() + "");//1 认证 2 未认证
                //保存昵称
                localUserInfo.setNickname(response.getNickname());
                //保存头像
                localUserInfo.setUserImage(response.getAvatar());
                //保存职位
                localUserInfo.setUserJob(response.getJobTitle());//1、BD

                MainActivity.isOver = false;
                ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                CommonUtil.gotoActivity(LoginActivity.this, MainActivity.class, true);
            }
        });
    }

    private void RequestCode(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.Code_denglu, GsonUtils.toJson(params), headerMap, new CallBackUtil<CodeModel>() {
            @Override
            public CodeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                textView1.setClickable(true);
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(CodeModel response) {
                hideProgress();
                textView1.setClickable(true);
                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));
                editText2.setText(response.getCode());
            }
        });
    }

    private boolean match() {
        phonenum = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(phonenum)) {
            myToast("请输入手机号");
            return false;
        }
        if (type == 1) {
            code = editText2.getText().toString().trim();
            if (TextUtils.isEmpty(code)) {
                myToast("请输入验证码");
                return false;
            }
        } else {
            password = editText3.getText().toString().trim();
            if (TextUtils.isEmpty(password)) {
                myToast("请输入验证码");
                return false;
            }
        }

       /* if (!isGouXuan) {
            myToast("请阅读并同意《用户协议》");
            return false;
        }*/

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView1.setText(getString(R.string.app_reacquirecode));
            textView1.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView1.setClickable(false);
            textView1.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }
    /*//屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }*/

    private void RequestUpgrade(Map<String, String> params) {
        //设置初始时间戳
        LocalUserInfo.getInstance(this).setTime(System.currentTimeMillis() + "");

        OkhttpUtil.okHttpGet(URLs.Upgrade, params, headerMap, new CallBackUtil<UpgradeModel>() {
            @Override
            public UpgradeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
//                myToast(err);
            }

            @Override
            public void onResponse(UpgradeModel response) {
//                hideProgress();
                model_up = response;
                if (Integer.valueOf(CommonUtil.getVersionCode(LoginActivity.this)) < Integer.valueOf(response.getVersion_code())) {
//                    handler1.sendEmptyMessage(0);
                    showUpdateDialog();
                } else {
//                    showToast("已经是最新版，无需更新");
                }
            }
        });
    }

    //显示是否要更新的对话框
    private void showUpdateDialog() {
        dialog.contentView(R.layout.dialog_upgrade)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.CENTER)
                .canceledOnTouchOutside(true)
                .dimAmount(0.8f)
                .show();
        TextView textView1 = dialog.findViewById(R.id.textView1);
        TextView textView2 = dialog.findViewById(R.id.textView2);
        TextView textView3 = dialog.findViewById(R.id.textView3);
        TextView textView4 = dialog.findViewById(R.id.textView4);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                        /*Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(model_up.getUrl());
                        intent.setData(content_url);
                        startActivity(intent);*/
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setCancelable(false);//点击外部不消失，返回键没用
//        progressDialog.setCanceledOnTouchOutside(false);//点击外部不消失，返回键有用
                    progressDialog.setTitle(getString(R.string.update_hint3));
                    progressDialog.setMessage(getString(R.string.update_hint4));
                    progressDialog.setProgress(0);
                    progressDialog.show();

                    //下载APK
                    InstallUtils.with(LoginActivity.this)
                            //必须-下载地址
                            .setApkUrl(model_up.getUrl())
                            //非必须-下载保存的文件的完整路径+/name.apk，使用自定义路径需要获取读写权限
//                                    .setApkPath(Constants.APK_SAVE_PATH)
                            //非必须-下载回调
                            .setCallBack(new InstallUtils.DownloadCallBack() {
                                @Override
                                public void onStart() {
                                    //下载开始
                                }

                                @Override
                                public void onComplete(final String path) {
                                    progressDialog.cancel();
                                    //下载完成
                                    //先判断有没有安装权限---适配8.0
                                    //如果不想用封装好的，可以自己去实现8.0适配
                                    InstallUtils.checkInstallPermission(LoginActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                        @Override
                                        public void onGranted() {
                                            //去安装APK
                                            //一加手机8.0碰到了安装解析失败问题请添加下面判断
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                //先获取是否有安装未知来源应用的权限
                                                boolean haveInstallPermission = LoginActivity.this.getPackageManager().canRequestPackageInstalls();
                                                if (!haveInstallPermission) {
                                                    //跳转设置开启允许安装
                                                    Uri packageURI = Uri.parse("package:" + LoginActivity.this.getPackageName());
                                                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                    startActivityForResult(intent, 1000);
                                                    return;
                                                }
                                            }
                                            InstallUtils.installAPK(LoginActivity.this, path, new InstallUtils.InstallCallBack() {
                                                @Override
                                                public void onSuccess() {
                                                    myToast(getString(R.string.update_hint5));
                                                }

                                                @Override
                                                public void onFail(Exception e) {
                                                    myToast(getString(R.string.update_hint6) + e.toString());
                                                }
                                            });
                                        }

                                        @Override
                                        public void onDenied() {
                                            //弹出弹框提醒用户
                                            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                                                    .setTitle(getString(R.string.update_hint7))
                                                    .setMessage(getString(R.string.update_hint8))
                                                    .setNegativeButton(getString(R.string.app_cancel), null)
                                                    .setPositiveButton(getString(R.string.update_hint9), new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            //打开设置页面
                                                            InstallUtils.openInstallPermissionSetting(LoginActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                                                @Override
                                                                public void onGranted() {
                                                                    //去安装APK
                                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                                        //先获取是否有安装未知来源应用的权限
                                                                        boolean haveInstallPermission = LoginActivity.this.getPackageManager().canRequestPackageInstalls();
                                                                        if (!haveInstallPermission) {
                                                                            //跳转设置开启允许安装
                                                                            Uri packageURI = Uri.parse("package:" + LoginActivity.this.getPackageName());
                                                                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                                            startActivityForResult(intent, 1000);
                                                                            return;
                                                                        }
                                                                    }
                                                                    InstallUtils.installAPK(LoginActivity.this, path, new InstallUtils.InstallCallBack() {
                                                                        @Override
                                                                        public void onSuccess() {
                                                                            myToast(getString(R.string.update_hint5));
                                                                        }

                                                                        @Override
                                                                        public void onFail(Exception e) {
                                                                            myToast(getString(R.string.update_hint6) + e.toString());
                                                                        }
                                                                    });
                                                                }

                                                                @Override
                                                                public void onDenied() {
                                                                    //还是不允许咋搞？
                                                                    finish();
//                                                                            Toast.makeText(MainActivity.this, "不允许安装咋搞？强制更新就退出应用程序吧！", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }
                                                    })
                                                    .create();
                                            alertDialog.show();
                                        }
                                    });

                                }

                                @Override
                                public void onLoading(long total, long current) {
                                    //下载中
                                    progressDialog.setMax((int) total);
                                    progressDialog.setProgress((int) current);
                                }

                                @Override
                                public void onFail(Exception e) {
                                    //下载失败
                                }

                                @Override
                                public void cancle() {
                                    //下载取消
                                }
                            })
                            //开始下载
                            .startDownload();
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.update_hint1),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
