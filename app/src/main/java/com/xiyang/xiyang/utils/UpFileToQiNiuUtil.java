package com.xiyang.xiyang.utils;

import android.content.Context;
import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.xiyang.xiyang.model.UpLoadTokenModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/4/7.
 * 上传文件到七牛云
 */
public abstract class UpFileToQiNiuUtil {
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> headerMap = new HashMap<>();
    private Configuration config;
    private UploadManager uploadManager;
    private UploadOptions opt;

    protected UpFileToQiNiuUtil(Context mContext, File uploadFile, String suffix) {
        RequestUpLoadToken(mContext, uploadFile, suffix);
    }

    public abstract void complete(boolean isok, String result, String url);

    /**
     * 获取上传文件token
     */
    private void RequestUpLoadToken(Context mContext, File uploadFile, String suffix) {
        OkhttpUtil.okHttpGet(URLs.UpLoadToken, params, headerMap, new CallBackUtil<UpLoadTokenModel>() {
            @Override
            public UpLoadTokenModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                UpFileToQiNiuUtil.this.complete(false, err, "");
            }

            @Override
            public void onResponse(UpLoadTokenModel response) {
                upload(mContext, uploadFile, response.getKey() + "." + suffix, response.getToken());
            }
        });
    }

    private void upload(Context mContext, File uploadFile, String keyname, String uploadToken) {
        //配置断点续传
        /*FileRecorder fileRecorder = null;
        try {
            fileRecorder = new FileRecorder("directory");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //config配置上传参数
        if (config == null) {
            config = new Configuration.Builder()
                    .connectTimeout(90)              // 链接超时。默认90秒
                    .useHttps(true)                  // 是否使用https上传域名
                    .useConcurrentResumeUpload(true) // 使用并发上传，使用并发上传时，除最后一块大小不定外，其余每个块大小固定为4M，
                    .concurrentTaskCount(3)          // 并发上传线程数量为3
                    .responseTimeout(90)             // 服务器响应超时。默认90秒
//                .recorder(fileRecorder)              // recorder分片上传时，已上传片记录器。默认null
//                .recorder(fileRecorder, keyGen)      // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
//                .zone(FixedZone.zone0)           // 设置区域，不指定会自动选择。指定不同区域的上传域名、备用域名、备用IP。
                    .build();
        }
        if (uploadManager == null) {
            //this.uploadManager = new UploadManager(fileRecorder);
            uploadManager = new UploadManager(config);
        }

        if (opt == null) {
            opt = new UploadOptions(null, null, true, new UpProgressHandler() {
                @Override
                public void progress(String key, double percent) {
                    Log.i("qiniutest", "percent:" + percent);
                }
            }, null);
        }
        uploadManager.put(uploadFile, keyname, uploadToken,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo respInfo,
                                         JSONObject jsonData) {
                        MyLogger.i(">>>>>>key：" + key);
                        MyLogger.i(">>>>>>respInfo：" + respInfo);
                        MyLogger.i(">>>>>>jsonData：" + jsonData);
                        if (respInfo.isOK()) {
                            //上传成功
//                            ToastUtils.show("上传成功");
                            UpFileToQiNiuUtil.this.complete(true, "上传成功", key);
                            /*try {
                                String url = jsonData.getString("url");
                                UpFileToQiNiuUtil.this.complete(true, "上传成功", url);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }*/

                        } else {
                            //上传失败
//                            ToastUtils.show("上传失败");
                            UpFileToQiNiuUtil.this.complete(false, respInfo.error, "");
                        }
                    }

                }, opt);
    }
}
