package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.MyProfileModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.FileUtil;
import com.xiyang.xiyang.utils.MyChooseImages;
import com.xiyang.xiyang.utils.MyLogger;

import java.io.File;
import java.util.HashMap;
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
    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    TextView textView;
    RelativeLayout relativeLayout;
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
                requestInfo(params);
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

        textView = findViewByID_My(R.id.textView);
        relativeLayout = findViewByID_My(R.id.relativeLayout);
    }

    @Override
    protected void initData() {
//        textView1.setText("+" + localUserInfo.getMobile_State_Code() + "  " + localUserInfo.getPhonenumber());
        editText1.setText(localUserInfo.getNickname());
        editText2.setText(localUserInfo.getPhonenumber());

        Glide.with(MyProfileActivity.this)
                .load(localUserInfo.getUserImage())
                .centerCrop()
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(CommonUtil.dip2px(this, 10))))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.headimg)//加载失败
                .into(imageView1);//加载图片

        //获取个人信息
        showProgress(true, getString(R.string.app_loading2));
        requestInfo(params);
    }

    private void requestInfo(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyProfile, params, headerMap, new CallBackUtil<MyProfileModel>() {
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
                        .load(response.getAvatar())
                        .centerCrop()
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(CommonUtil.dip2px(MyProfileActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片

                //昵称
                editText1.setText(response.getName());
                //手机号
                editText2.setText("" + localUserInfo.getMobile_State_Code() + response.getPhone());
                //性别
                switch (response.getGender()) {
                    case "1":
                        editText3.setText("男");
                        break;
                    case "2":
                        editText3.setText("女");
                        break;
                    default:
                        editText3.setText("未知");
                        break;
                }
                //所属BDM
                relativeLayout.setVisibility(View.VISIBLE);
                switch (localUserInfo.getUserJob()){
                    case "BD":
                        textView.setText("所属BDM");
                        editText4.setText(response.getBelongingBDM());
                        break;
                    case "BDM":
                        textView.setText("所属CM");
                        editText4.setText(response.getBelongingCM());
                        break;
                    case "CM":
                        textView.setText("所属RM");
                        editText4.setText(response.getBelongingRM());
                        break;
                    default:
                        relativeLayout.setVisibility(View.GONE);
                        break;
                }
                //所属城市
                editText5.setText(response.getBelongingRegion());
                //加入时间
                editText6.setText(response.getJoinTime());


                localUserInfo.setPhoneNumber(response.getPhone());
                localUserInfo.setNickname(response.getName());
//                localUserInfo.setInvuteCode(response.getInvite_code());
//                localUserInfo.setEmail(response.getEmail());
                localUserInfo.setUserImage(response.getAvatar());

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
                    MyLogger.i(">>>>>>>>>选取的文件路径：" + pdfpath + ">>>>>后缀名：" + FileUtils.getFileExtension(pdfpath));
                    if (pdfpath != null) {
                        if (FileUtils.getFileExtension(pdfpath).equals("pdf")) {
                            pdffile = new File(pdfpath);
                        } else {
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
                    MyLogger.i(">>>>>>>>>选取的文件路径：" + imgpath + ">>>>>后缀名：" + FileUtils.getFileExtension(imgpath));
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    imgpath = FileUtil.getPath(this, uri);
                    MyLogger.i(">>>>>>>>>选取的文件路径：" + imgpath + ">>>>>后缀名：" + FileUtils.getFileExtension(imgpath));
                    break;

            }
            if (imgpath != null) {
                showProgress(true, getString(R.string.app_loading1));
//                imgfile = new File(uri.getPath());
                //压缩
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //如果是拍照，则旋转
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }
                imgfile = FileUtil.bytesToImageFile(MyProfileActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));

                //上传头像
                Map<String, File> fileMap = new HashMap<>();
                fileMap.put("file", imgfile);
                params.clear();
                OkhttpUtil.okHttpUploadMapFile(URLs.UpFile, fileMap, "file", params, headerMap, new CallBackUtil<String>() {
                    @Override
                    public String onParseResponse(Call call, Response response) {
                        return null;
                    }

                    @Override
                    public void onFailure(Call call, Exception e, String err) {
                        hideProgress();
                        myToast("图片上传失败" + err);
                    }

                    @Override
                    public void onResponse(String response) {
//                        hideProgress();
                        Glide.with(MyProfileActivity.this)
                                .load(response)
                                .centerCrop()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(MyProfileActivity.this, 10))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.headimg)//加载失败
                                .into(imageView1);//加载图片

                        params.put("avatar", response);
                        params.put("name", localUserInfo.getNickname());
                        requestUpFile(params);
                    }
                });


                /*new UpFileToQiNiuUtil(MyProfileActivity.this, imgfile, FileUtils.getFileExtension(imgfile)) {
                    @Override
                    public void complete(boolean isok, String result, String url) {
//                        hideProgress();
                        if (isok) {
                            MyLogger.i(">>>>上传文件路径：" + url);
                            localUserInfo.setUserImage(url);
                            Glide.with(MyProfileActivity.this)
                                    .load(url)
                                    .centerCrop()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(MyProfileActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                            Map<String, String> params = new HashMap<>();
                            params.put("head",url);
                            RequestUpFile(params);

                        } else {
                            myToast(result);
                        }
                    }
                };*/
            }
        }

    }

    private void requestUpFile(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.ChangeProfile, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                myToast("头像修改成功");
                hideProgress();
            }
        });
    }
}
