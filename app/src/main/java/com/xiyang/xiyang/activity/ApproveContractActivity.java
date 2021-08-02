package com.xiyang.xiyang.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.cy.dialog.BaseDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.SdkVersionUtils;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.MyLogger;
import com.xiyang.xiyang.view.pictureselector.FullyGridLayoutManager;
import com.xiyang.xiyang.view.pictureselector.GlideCacheEngine;
import com.xiyang.xiyang.view.pictureselector.GlideEngine;
import com.xiyang.xiyang.view.pictureselector.GridImageAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/4/6.
 * 审批合同
 */
public class ApproveContractActivity extends BaseActivity {
    List<String> list_jieguo = new ArrayList<>();
    int i_jieguo = -1;
    RelativeLayout rl_shuliang;
    EditText tv_jieguo, tv_shuoming, tv_shuliang;
    ImageView imageView1;

    String id = "", status = "", remark = "", images = "", type = "", deviceNum = "", type_shenhe = "1", sn = "";

    /**
     * 选择图片
     */
    RecyclerView mRecyclerView;
    GridImageAdapter mAdapter;
    int maxSelectNum = 3;//选择最多图片数量
    int spanCount = 3;//一行显示张数
    ArrayList<File> listFiles = new ArrayList<>();
    int num = 0;

    Map<String, File> fileMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvecontract);

        /**
         * 选择图片
         */
        mRecyclerView = findViewByID_My(R.id.rv_addimg);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                spanCount, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount,
                ScreenUtils.dip2px(this, 5), false));//中间值是设置间距
        mAdapter = new GridImageAdapter(ApproveContractActivity.this, onAddPicClickListener);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mAdapter.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mAdapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(mAdapter);

        /*// 注册广播
        BroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                BroadcastAction.ACTION_DELETE_PREVIEW_POSITION);*/
    }

    /*private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (BroadcastAction.ACTION_DELETE_PREVIEW_POSITION.equals(action)) {
                // 外部预览删除按钮回调
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    int position = extras.getInt(PictureConfig.EXTRA_PREVIEW_DELETE_POSITION);
                    mAdapter.remove(position);
                    mAdapter.notifyItemRemoved(position);
                }
            }
        }
    };*/
    @Override
    protected void initView() {
//        rl_jieguo = findViewByID_My(R.id.rl_jieguo);
//        rl_shuoming = findViewByID_My(R.id.rl_shuoming);
        rl_shuliang = findViewByID_My(R.id.rl_shuliang);

        tv_jieguo = findViewByID_My(R.id.tv_jieguo);
        tv_shuliang = findViewByID_My(R.id.tv_shuliang);
        tv_shuoming = findViewByID_My(R.id.tv_shuoming);


        imageView1 = findViewByID_My(R.id.imageView1);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        type_shenhe = getIntent().getStringExtra("type_shenhe");
        rl_shuliang.setVisibility(View.GONE);
        switch (type_shenhe){
            case "0":
                titleView.setTitle("审批合同");
                if (type.equals("device_add")){
                    //添加设备
                    rl_shuliang.setVisibility(View.VISIBLE);
                    tv_shuliang.setText(getIntent().getStringExtra("num"));
                }
                break;
            case "1":
                titleView.setTitle("调整上级审核");
                break;
            case "2":
                titleView.setTitle("调整市场审核");
                break;
            case "3":
                titleView.setTitle("升职降职审核");
                break;
            case "4":
                titleView.setTitle("审批采购审核");
                if (type.equals("device_add")){
                    //添加设备
                    rl_shuliang.setVisibility(View.VISIBLE);
                    tv_shuliang.setText(getIntent().getStringExtra("num"));
                }
                break;
        }

        list_jieguo.add("通过");
        list_jieguo.add("不通过");
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_jieguo:
                //故障类型
                dialogList_jieguo(tv_jieguo);
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    images = "";
                    showProgress(true, getString(R.string.app_loading1));
                    for (int i = 0; i < listFiles.size(); i++) {
                        params.clear();
                        fileMap.clear();
                        fileMap.put("file", listFiles.get(i));
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
                                images = images + response + ",";
                                num--;
                                MyLogger.i(">>>>>>" + num);
                                if (num == 0) {
                                    if (!images.equals("")) {
                                        images = images.substring(0, images.length() - 1);
                                    }
                                    params.clear();
                                    switch (type_shenhe){
                                        case "0":
                                            //合同审批
                                            params.put("reason", remark);
                                            params.put("images", images);
                                            params.put("id", id);
                                            params.put("status", status);
                                            params.put("num", deviceNum);
                                            requestUpData(params, URLs.ApproveContract);
                                            break;
                                        case "1":
                                        case "2":
                                        case "3":
                                            //人事审核
                                            params.put("auditDescription", remark);
                                            params.put("images", images);
                                            params.put("applyId", id);
                                            params.put("status", Integer.valueOf(status) + 1 + "");//审批结果 1:通过; 2:驳回
                                            params.put("sn", sn);
                                            requestUpData(params, URLs.ApproveContract_RenShi);
                                            break;
                                        case "4":
                                            //采购审批
                                            params.put("remark", remark);
                                            params.put("images", images);
                                            params.put("purchaseApplyLogId", id);
                                            params.put("handleResult", Integer.valueOf(status) + 1 + "");//审批结果 1:通过; 2:驳回
                                            params.put("approvedQuantity", deviceNum);
                                            requestUpData(params, URLs.ApproveContract_CaiGou);
                                            break;
                                    }

                                }
                            }
                        });
                    }

                }
                break;
        }
    }

    private boolean match() {
        if (TextUtils.isEmpty(status)) {
            myToast("请选择处理结果");
            return false;
        }
        remark = tv_shuoming.getText().toString().trim();
        if (TextUtils.isEmpty(remark)) {
            myToast("请输入处理说明");
            return false;
        }
        if (type != null && type.equals("device_add")) {
            deviceNum = tv_shuliang.getText().toString().trim();
            if (TextUtils.isEmpty(deviceNum)) {
                myToast("请输入审批数量");
                return false;
            }
        }

        listFiles.clear();
        for (LocalMedia media : mAdapter.getData()) {
            MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
            File file = new File(media.getCompressPath());
            listFiles.add(file);
            // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
        }
        num = listFiles.size();
        if (num == 0) {
            myToast("请选择上传照片");
            return false;
        }
        return true;
    }

    /**
     * 提交数据
     */
    private void requestUpData(Map<String, String> params, String url) {
        OkhttpUtil.okHttpPostJson(url, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
                myToast("提交成功");
                hideProgress();
                finish();
            }
        });
    }

    /**
     * 选择故障类型
     */
    private void dialogList_jieguo(TextView textView) {
        dialog.contentView(R.layout.dialog_list_top)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.TOP)
                .dimAmount(0.5f)
                .show();
        RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<String> adapter = new CommonAdapter<String>
                (ApproveContractActivity.this, R.layout.item_help, list_jieguo) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (i_jieguo == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                i_jieguo = position;
                textView.setText(list_jieguo.get(position));
                status = position + 1 + "";
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_list.setAdapter(adapter);
    }


    /**
     * *****************************************选择图片********************************************
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册
            PictureSelector.create(ApproveContractActivity.this)
//                    .openCamera(PictureMimeType.ofImage())//打开相机
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                    .isWeChatStyle(false)// 是否开启微信图片选择风格
                    .isUseCustomCamera(false)// 是否使用自定义相机
//                        .setLanguage(language)// 设置语言，默认中文
                    .isPageStrategy(true)// 是否开启分页策略 & 每页多少条；默认开启
                    .isWithVideoImage(true)// 图片和视频是否可以同选,只在ofAll模式下有效
                    .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                    .loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
//                        .setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                    //.setButtonFeatures(CustomCameraView.BUTTON_STATE_BOTH)// 设置自定义相机按钮状态
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    //.maxVideoSelectNum(1) // 视频最大选择数量
                    //.minVideoSelectNum(1)// 视频最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .isReturnEmpty(true)// 未选择数据时点击按钮是否可以返回
                    .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高,默认为true
                    .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高,默认为false
                    //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                    .isOriginalImageControl(false)// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                    .selectionMode(PictureConfig.MULTIPLE)// 多选PictureConfig.MULTIPLE or 单选 PictureConfig.SINGLE
                    .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                    .isPreviewImage(true)// 是否可预览图片
                    .isPreviewVideo(true)// 是否可预览视频
                    //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg,Android Q使用PictureMimeType.PNG_Q
                    .isEnableCrop(false)// 是否裁剪
                    .isCompress(true)// 是否压缩
                    .compressQuality(60)// 图片压缩后输出质量 0~ 100
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                    //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                    .isGif(true)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    //.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
                    //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .isOpenClickSound(false)// 是否开启点击声音
                    .selectionData(mAdapter.getData())// 是否传入已选图片
                    .isDragFrame(true)// 是否可拖动裁剪框(固定)
                    //.videoMinSecond(10)// 查询多少秒以内的视频
                    //.videoMaxSecond(15)// 查询多少秒以内的视频
                    //.recordVideoSecond(10)//录制视频秒数 默认60s
                    .cutOutQuality(90)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于多少kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    .rotateEnabled(true) // 裁剪是否可旋转图片
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片
                    .forResult(new MyResultCallback(mAdapter));
        }
    };

    /**
     * 返回结果回调
     */
    private class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            /*
            //以下代码应该写在提交，否则删除的图片会提交
            listFiles.clear();
            for (LocalMedia media : result) {
                MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
                File file = new File(media.getCompressPath());
                listFiles.add(file);
                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
            }*/
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
        }

        @Override
        public void onCancel() {
//            MyLogger.i("图片选择取消");
        }
    }
}
