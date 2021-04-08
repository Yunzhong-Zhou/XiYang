package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.MyProfileModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.FileUtil;
import com.xiyang.xiyang.utils.MyChooseImages;
import com.xiyang.xiyang.utils.MyLogger;
import com.xiyang.xiyang.utils.UpFileToQiNiuUtil;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static com.xiyang.xiyang.utils.Constant.SELECT_PDF_FILE;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


/**
 * Created by fafukeji01 on 2017/5/8.
 * 我的资料
 */

public class MyProfileActivity extends BaseActivity {
    MyProfileModel model;

    ImageView imageView1;
    EditText editText1, editText2,editText3,editText4,editText5,editText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                requestInfo("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {

            }
        });

        imageView1 = findViewByID_My(R.id.imageView1);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        editText5 = findViewByID_My(R.id.editText5);
        editText6 = findViewByID_My(R.id.editText6);

    }

    @Override
    protected void initData() {
//        textView1.setText("+" + localUserInfo.getMobile_State_Code() + "  " + localUserInfo.getPhonenumber());
        editText1.setText(localUserInfo.getNickname());
        editText2.setText(localUserInfo.getPhonenumber());

        Glide.with(MyProfileActivity.this)
                .load(localUserInfo.getUserImage())
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new
                        RoundedCorners(CommonUtil.dip2px(this, 10))))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.headimg)//加载失败
                .into(imageView1);//加载图片

        //获取个人信息
//        showProgress(true, getString(R.string.app_loading2));
//        requestInfo("?token=" + localUserInfo.getToken());
    }

    private void requestInfo(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ChangeProfile, params, headerMap, new CallBackUtil<MyProfileModel>() {
            @Override
            public MyProfileModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(MyProfileModel response) {
                hideProgress();
                model = response;
                //头像
                Glide.with(MyProfileActivity.this)
                        .load(URLs.IMGHOST + response.getHead())
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(MyProfileActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片

                //手机号
//                textView1.setText("+" + localUserInfo.getMobile_State_Code() + "  " + response.getMobile());
                //昵称
                editText1.setText(response.getNickname());
                //邮箱
                editText2.setText(response.getEmail());


                localUserInfo.setPhoneNumber(response.getMobile());
                localUserInfo.setNickname(response.getNickname());
//                localUserInfo.setInvuteCode(response.getInvite_code());
                localUserInfo.setEmail(response.getEmail());
                localUserInfo.setUserImage(response.getHead());

                if (response.getNickname_update() == 2) {
                    editText1.setFocusable(false);
                } else {
                    editText1.setFocusable(true);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.textView3:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", model.getInvite_code());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                myToast(getString(R.string.recharge_h34));
                break;*/
            case R.id.linearLayout1:
                //头像
                MyChooseImages.showPhotoDialog(MyProfileActivity.this);
                break;
            case R.id.tv_confirm:
                //退出登录
                showToast("确认退出登录吗？",
                        getString(R.string.app_confirm),
                        getString(R.string.app_cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                localUserInfo.setUserId("");
                                localUserInfo.setToken("");
                                localUserInfo.setPhoneNumber("");
                                localUserInfo.setNickname("");
                                localUserInfo.setInvuteCode("");
                                localUserInfo.setWalletaddr("");
                                localUserInfo.setEmail("");
                                localUserInfo.setUserImage("");

                                ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                                CommonUtil.gotoActivity(MyProfileActivity.this, LoginActivity.class, true);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("资料管理");
    }

    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            File pdffile = null;
            File imgfile = null;
            String imgpath = null;
            Uri uri = null;
            switch (requestCode) {
                case SELECT_PDF_FILE:
                    //选取PDF文件
                    uri = data.getData();
                    String pdfpath = FileUtil.getPath(this, uri);
                    MyLogger.i(">>>>>>>>>选取的文件路径：" + pdfpath+">>>>>后缀名："+FileUtils.getFileExtension(pdfpath));
                    if (pdfpath != null) {
                        if (FileUtils.getFileExtension(pdfpath).equals("pdf")){
                            pdffile = new File(pdfpath);
                        }else {
                            myToast("请选择PDF文件上传");
                            return;
                        }
                    }
                    break;
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imgpath = uri.getPath();
                    MyLogger.i(">>>>>>>>>选取的文件路径：" + imgpath+">>>>>后缀名："+FileUtils.getFileExtension(imgpath));
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    imgpath = FileUtil.getPath(this, uri);
                    MyLogger.i(">>>>>>>>>选取的文件路径：" + imgpath+">>>>>后缀名："+FileUtils.getFileExtension(imgpath));
                    break;

            }
            if (imgpath != null) {
//                imgfile = new File(uri.getPath());
                //压缩
                Bitmap bitmap= BitmapFactory.decodeFile(imgpath);
                imgfile = FileUtil.bytesToImageFile(MyProfileActivity.this,
                        ImageUtils.compressByQuality(bitmap,50));

                new UpFileToQiNiuUtil(MyProfileActivity.this, imgfile, FileUtils.getFileExtension(imgfile)) {
                    @Override
                    public void complete(boolean isok, String result, String url) {
                        hideProgress();
                        if (isok) {
                            MyLogger.i(">>>>上传文件路径：" + url);
                            localUserInfo.setUserImage(url);
                            Glide.with(MyProfileActivity.this)
                                    .load(url)
                                    .centerCrop()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(MyProfileActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                        } else {
                            myToast(result);
                        }
                    }
                };
            }
        }

    }
}
