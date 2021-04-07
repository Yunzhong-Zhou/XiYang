package com.xiyang.xiyang.utils;

import android.content.Context;
import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by Mr.Z on 2021/4/7.
 * 上传文件到七牛云
 */
public abstract class UpFileToQiNiuUtil {

    public abstract void complete(String key);

    private UploadManager uploadManager;

    private void upload(Context mContext, File uploadFile, String keyname, String uploadToken) {
        //配置断点续传
        /*FileRecorder fileRecorder = null;
        try {
            fileRecorder = new FileRecorder("directory");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //config配置上传参数
        Configuration config = new Configuration.Builder()
                .connectTimeout(90)              // 链接超时。默认90秒
                .useHttps(true)                  // 是否使用https上传域名
                .useConcurrentResumeUpload(true) // 使用并发上传，使用并发上传时，除最后一块大小不定外，其余每个块大小固定为4M，
                .concurrentTaskCount(3)          // 并发上传线程数量为3
                .responseTimeout(90)             // 服务器响应超时。默认90秒
//                .recorder(fileRecorder)              // recorder分片上传时，已上传片记录器。默认null
//                .recorder(fileRecorder, keyGen)      // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
//                .zone(FixedZone.zone0)           // 设置区域，不指定会自动选择。指定不同区域的上传域名、备用域名、备用IP。
                .build();

        if (this.uploadManager == null) {
            //this.uploadManager = new UploadManager(fileRecorder);
            this.uploadManager = new UploadManager(config);
        }

        UploadOptions opt = new UploadOptions(null, null, true, new UpProgressHandler() {
            @Override
            public void progress(String key, double percent) {
                Log.i("qiniutest", "percent:" + percent);
            }
        }, null);

        this.uploadManager.put(uploadFile, keyname, uploadToken,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo respInfo,
                                         JSONObject jsonData) {

                        if (respInfo.isOK()) {
                            //上传成功
                            UpFileToQiNiuUtil.this.complete("上传成功");
//                                Log.e("zw", jsonData.toString() + respInfo.toString());
//                                String fileKey = jsonData.getString("key");
//                                String fileHash = jsonData.getString("hash");

                        } else {
                            //上传失败
                            UpFileToQiNiuUtil.this.complete("上传失败");

                        }
                    }

                }, opt);


    }
}
