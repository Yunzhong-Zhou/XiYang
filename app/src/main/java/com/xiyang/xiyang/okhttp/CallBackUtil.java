package com.xiyang.xiyang.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.xiyang.xiyang.MyApplication;
import com.xiyang.xiyang.activity.LoginActivity;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.LocalUserInfo;
import com.xiyang.xiyang.utils.MyLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by fighting on 2017/4/7.
 */

public abstract class CallBackUtil<T> {
    Type mType;
    private Gson mGson = new Gson();
    /**
     * 异步线程-传参给activity时需要
     */
    public static Handler mMainHandler = new Handler(Looper.getMainLooper());

    /**
     * 下载进度
     */
    public void onProgress(float progress, long total) {
    }

    /**
     * 请求错误
     */
    public void onError(final Call call, final Exception e) {
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailure(call, e, "数据请求错误");
            }
        });
    }

    /**
     * 请求成功
     */
    public void onSeccess(Call call, Response response) {
        mType = getSuperclassTypeParameter(getClass());
        if (mGson == null){
            mGson = new Gson();
        }
        try {
            String string = response.body().string();
            if (!string.equals("")) {
                MyLogger.i("数据返回onSeccess", string);
                JSONObject mJsonObject = new JSONObject(string);
                int result_code = mJsonObject.getInt("code");

                //保存后台返回的最新时间戳
                LocalUserInfo.getInstance(MyApplication.getContext()).setTime(mJsonObject.getString("serverTime"));

                switch (result_code) {
                    case 0:
                        //数据请求成功-解析数据
                        if (string.indexOf("data") != -1) {
                            // TODO 有data数据 -解析data
                            String result = mJsonObject.getString("data");
                            if (mType == String.class) {//模型为string直接返回data数据
                                mMainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
//                                    T obj = onParseResponse(call, response);
                                        onResponse((T) result);
                                    }
                                });
                            } else {
                                mMainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Object o = mGson.fromJson(result, mType);
                                        onResponse((T) o);
                                    }
                                });
                            }
                        } else {
                            //TODO 无data数据 -解析message
                            if (string.indexOf("message") != -1) {
                                //TODO 判断有无message数据 -解析message
                                String msg = mJsonObject.getString("message");
                                mMainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
//                                    T obj = onParseResponse(call, response);
                                        onResponse((T) msg);
                                    }
                                });
                            }else {
                                mMainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
//                                    T obj = onParseResponse(call, response);
                                        onResponse((T) "请求成功");
                                    }
                                });
                            }
                        }
                        break;
                    /*case 600:
                    case 800:
                        mMainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                onFailure(call, null, "Headers验证失败");
                            }
                        });
                        break;*/
                    case 50003:
                    case 40002:
                        //会员token无效 - 跳转登录
                        ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                        LocalUserInfo.getInstance(MyApplication.getContext()).setUserHash("");
                        CommonUtil.gotoActivity(MyApplication.getContext(), LoginActivity.class);
                        break;
                    case 50007:
                        //请求时误差时间超时
                        mMainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                onFailure(call, null, "签名验证时间戳误差大，请刷新再试");
                            }
                        });
                        break;

//                    case 40004:
                        //TODO 没有数据、提交失败 （有冲突，走失败逻辑提示message信息，请求列表数据时，不要提示）
                    default:
                        //数据请求失败
                        String msg = mJsonObject.getString("message");
                        mMainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (!msg.equals(""))
                                    onFailure(call, null, msg.trim());
                                /*else
                                    onFailure(call, null, "数据请求失败");*/
                            }
                        });
                        break;
                }

            } else {
                //如果数据为空
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onFailure(call, null, "暂未请求到数据");
                    }
                });
            }
        } catch (IOException e) {
            //数据请求出错
            e.printStackTrace();
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(call, null, "数据请求出错");
                }
            });

        } catch (JSONException e) {
            //json解析出错
            e.printStackTrace();
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(call, null, "数据解析出错");
                }
            });
        }
    }

    /**
     * 判断传入数据类型
     */
    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
//            throw new RuntimeException("缺少类型参数");
            return String.class;
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    /**
     * 解析response，执行在子线程
     */
    public abstract T onParseResponse(Call call, Response response);

    /**
     * 访问网络失败后被调用，执行在UI线程
     */
    public abstract void onFailure(Call call, Exception e, String err);

    /**
     * 访问网络成功后被调用，执行在UI线程
     */
    public abstract void onResponse(T response);


    /**
     * 默认返回
     */
    public static abstract class CallBackDefault extends CallBackUtil<Response> {
        @Override
        public Response onParseResponse(Call call, Response response) {
            return response;
        }
    }

    /**
     * 返回string
     */
    public static abstract class CallBackString extends CallBackUtil<String> {
        @Override
        public String onParseResponse(Call call, Response response) {
            try {
                return response.body().string();
            } catch (IOException e) {
                new RuntimeException("failure");
                return "";
            }
        }
    }

    /**
     * 下载图片时的回调类
     */
    public static abstract class CallBackBitmap extends CallBackUtil<Bitmap> {
        private int mTargetWidth;
        private int mTargetHeight;

        public CallBackBitmap() {
        }

        public CallBackBitmap(int targetWidth, int targetHeight) {
            mTargetWidth = targetWidth;
            mTargetHeight = targetHeight;
        }

        public CallBackBitmap(ImageView imageView) {
            int width = imageView.getWidth();
            int height = imageView.getHeight();
            if (width <= 0 || height <= 0) {
                throw new RuntimeException("无法获取ImageView的width或height");
            }
            mTargetWidth = width;
            mTargetHeight = height;
        }

        @Override
        public Bitmap onParseResponse(Call call, Response response) {
            if (mTargetWidth == 0 || mTargetHeight == 0) {
                return BitmapFactory.decodeStream(response.body().byteStream());
            } else {
                return getZoomBitmap(response);
            }
        }

        /**
         * 压缩图片，避免OOM异常
         */
        private Bitmap getZoomBitmap(Response response) {
            byte[] data = null;
            try {
                data = response.body().bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            int picWidth = options.outWidth;
            int picHeight = options.outHeight;
            int sampleSize = 1;
            int heightRatio = (int) Math.floor((float) picWidth / (float) mTargetWidth);
            int widthRatio = (int) Math.floor((float) picHeight / (float) mTargetHeight);
            if (heightRatio > 1 || widthRatio > 1) {
                sampleSize = Math.max(heightRatio, widthRatio);
            }
            options.inSampleSize = sampleSize;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);

            if (bitmap == null) {
                throw new RuntimeException("Failed to decode stream.");
            }
            return bitmap;
        }
    }

    /**
     * 下载文件时的回调类
     */
    public static abstract class CallBackFile extends CallBackUtil<File> {
        private final String mDestFileDir;
        private final String mdestFileName;

        /**
         * @param destFileDir:文件目录
         * @param destFileName：文件名
         */
        public CallBackFile(String destFileDir, String destFileName) {
            mDestFileDir = destFileDir;
            mdestFileName = destFileName;
        }

        @Override
        public File onParseResponse(Call call, Response response) {
            InputStream is = null;
            byte[] buf = new byte[1024 * 8];
            int len = 0;
            FileOutputStream fos = null;
            try {
                is = response.body().byteStream();
                final long total = response.body().contentLength();

                long sum = 0;

                File dir = new File(mDestFileDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, mdestFileName);
                fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1) {
                    sum += len;
                    fos.write(buf, 0, len);
                    final long finalSum = sum;
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onProgress(finalSum * 100.0f / total, total);
                        }
                    });
                }
                fos.flush();

                return file;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    response.body().close();
                    if (is != null) is.close();
                } catch (IOException e) {
                }
                try {
                    if (fos != null) fos.close();
                } catch (IOException e) {
                }
            }
            return null;
        }
    }

}
