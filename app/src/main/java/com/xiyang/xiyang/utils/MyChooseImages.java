package com.xiyang.xiyang.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.xiyang.xiyang.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by ling on 2015/8/24.
 * description:选择图片，通过拍照或者图片文件
 */
public class MyChooseImages {
    public static final int REQUEST_CODE_CAPTURE_CAMEIA = 1;// 拍照
    public static final int REQUEST_CODE_PICK_IMAGE = 2;// 从相册中选择

    public static String imagepath;// 图片存储根路径

    public static void showPhotoDialog(final Activity activity) {
        final AlertDialog dlg = new AlertDialog.Builder(activity).create();
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.alertdialog);
        // 为确认按钮添加事件,执行退出应用操作
        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);

        tv_paizhao.setText(activity.getString(R.string.paizhao));
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 设置图片要保存的 根路径+文件名
                    imagepath = FileUtil.getImageDownloadDir(activity)+ System.currentTimeMillis() + ".png";
                    File file = new File(imagepath);
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // 置入一个不设防的VmPolicy（不设置的话 7.0以上一调用拍照功能就崩溃了）
                    // 还有一种方式：manifest中加入provider然后修改intent代码
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    activity.startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);

                    /*PictureSelector.create(activity)
                            .openCamera(PictureMimeType.ofImage())
                            .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                            .isEnableCrop(true)// 是否裁剪
                            .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                            .circleDimmedLayer(false)// 是否圆形裁剪
                            .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                            .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                            .cutOutQuality(90)// 裁剪输出质量 默认100
                            .minimumCompressSize(100)// 小于多少kb的图片不压缩
                            .isCompress(true)// 是否压缩
                            .compressQuality(60)// 图片压缩后输出质量 0~ 100
                            .forResult(PictureConfig.REQUEST_CAMERA);*/

                }
                else {
                    Toast.makeText(activity, activity.getString(R.string.app_card), Toast.LENGTH_LONG).show();
                }
                dlg.cancel();

            }
        });
        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
        tv_xiangce.setText(activity.getString(R.string.xiangce));
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");//相片类型
                activity.startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                dlg.cancel();
            }
        });
    }


}
