package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cretin.tools.scancode.CaptureActivity;
import com.cretin.tools.scancode.config.ScanConfig;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.CommonModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.Constant;
import com.xiyang.xiyang.utils.FileUtil;
import com.xiyang.xiyang.utils.MyChooseImages;
import com.xiyang.xiyang.utils.MyLogger;
import com.xiyang.xiyang.utils.UpFileToQiNiuUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

import static com.xiyang.xiyang.utils.Constant.SELECT_PDF_FILE;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by Mr.Z on 2021/3/28.
 * 创建工单
 */
public class AddWorkListActivity extends BaseActivity {
    List<String> list_work = new ArrayList<>();
    List<CommonModel.WorkOrderTypeBean> list_guzhang = new ArrayList<>();
    int type = 0, i_guzhang = -1;
    RelativeLayout rl_gongdanleixing, rl_xuanzedingdan, rl_shebeiguzhang, rl_xuanzemendian, rl_dingdanwenti,
            rl_guzhangleixing, rl_qitashuoming;
    EditText tv_gongdanleixing, tv_xuanzedingdan, tv_shebeiguzhang, tv_xuanzemendian, tv_dingdanwenti,
            tv_guzhangleixing, tv_qitashuoming;
    ImageView imageView1;

    String workOrderType = "", deviceName = "", storeId = "", orderId = "", remark = "", images = "";

    File pdffile = null;
    File imgfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwroklist);
    }

    @Override
    protected void initView() {
        rl_gongdanleixing = findViewByID_My(R.id.rl_gongdanleixing);
        rl_xuanzemendian = findViewByID_My(R.id.rl_xuanzemendian);
        rl_xuanzedingdan = findViewByID_My(R.id.rl_xuanzedingdan);
        rl_shebeiguzhang = findViewByID_My(R.id.rl_shebeiguzhang);
        rl_dingdanwenti = findViewByID_My(R.id.rl_dingdanwenti);
        rl_guzhangleixing = findViewByID_My(R.id.rl_guzhangleixing);
        rl_qitashuoming = findViewByID_My(R.id.rl_qitashuoming);

        tv_gongdanleixing = findViewByID_My(R.id.tv_gongdanleixing);
        tv_xuanzemendian = findViewByID_My(R.id.tv_xuanzemendian);
        tv_xuanzedingdan = findViewByID_My(R.id.tv_xuanzedingdan);
        tv_shebeiguzhang = findViewByID_My(R.id.tv_shebeiguzhang);
        tv_dingdanwenti = findViewByID_My(R.id.tv_dingdanwenti);
        tv_guzhangleixing = findViewByID_My(R.id.tv_guzhangleixing);
        tv_qitashuoming = findViewByID_My(R.id.tv_qitashuoming);
        imageView1 = findViewByID_My(R.id.imageView1);


    }

    @Override
    protected void initData() {
        list_work.add("设备工单");
        list_work.add("订单工单");
        list_work.add("其他工单");

        type = getIntent().getIntExtra("type", 0);
        tv_gongdanleixing.setText(list_work.get(type));
        titleView.setTitle(list_work.get(type));
        changeUI();

    }

    private void request(Map<String, String> params, String url) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<CommonModel>() {
            @Override
            public CommonModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(CommonModel response) {
                hideProgress();
                i_guzhang = -1;
                workOrderType = "";
                list_guzhang = response.getWorkOrderType();
            }
        });

    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_shebeiguzhang:
                //设备扫码
                ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(AddWorkListActivity.this, config);
                break;
            case R.id.tv_xuanzemendian:
                //选择门店
                Intent intent1 = new Intent(AddWorkListActivity.this, MyStoreListActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("requestCode", Constant.SELECT_STORE);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, Constant.SELECT_STORE, bundle1);
                break;
            case R.id.tv_xuanzedingdan:
                //选择订单
                Intent intent2 = new Intent(AddWorkListActivity.this, MyContractActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_CONTRACT);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_CONTRACT, bundle2);
                break;
            case R.id.tv_gongdanleixing:
                //选择工单类型
                dialogList_gongdan();
                break;
            case R.id.tv_guzhangleixing:
                //故障类型
                dialogList_guzhang(tv_guzhangleixing);
                break;
            case R.id.tv_dingdanwenti:
                //订单类型
                dialogList_guzhang(tv_dingdanwenti);
                break;
            case R.id.imageView1:
                //上传图片
                MyChooseImages.showPhotoDialog(AddWorkListActivity.this);
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    new UpFileToQiNiuUtil(AddWorkListActivity.this, imgfile, FileUtils.getFileExtension(imgfile)) {
                        @Override
                        public void complete(boolean isok, String result, String url) {
                            if (isok) {
                                MyLogger.i(">>>>上传文件路径：" + url);
                                images = url;

                                params.clear();
                                params.put("remark", remark);
                                params.put("images", images);
                                switch (type) {
                                    case 0:
                                        //设备工单
                                        params.put("deviceName", deviceName);
                                        params.put("workOrderType", workOrderType);
                                        requestUpData(params, URLs.AddDeviceWorkList);
                                        break;
                                    case 1:
                                        //订单工单
                                        params.put("orderId", orderId);
                                        params.put("workOrderType", workOrderType);
                                        requestUpData(params, URLs.AddOrderList);
                                        break;
                                    case 2:
                                        //其他工单
                                        params.put("storeId", storeId);
                                        requestUpData(params, URLs.AddOtherList);
                                        break;
                                }

                            } else {
                                hideProgress();
                                myToast("图片上传失败");
                            }
                        }
                    };

                }
                break;
        }
    }

    private boolean match() {
        remark = tv_qitashuoming.getText().toString().trim();
        if (TextUtils.isEmpty(remark)) {
            myToast("请输入其他说明");
            return false;
        }
        if (imgfile == null) {
            myToast("请选择上传照片");
            return false;
        }
        switch (type) {
            case 0:
                //设备工单
                if (TextUtils.isEmpty(deviceName)) {
                    myToast("请扫描设备码");
                    return false;
                }
                if (TextUtils.isEmpty(workOrderType)) {
                    myToast("请选择故障类型");
                    return false;
                }
                break;
            case 1:
                //订单工单
                if (TextUtils.isEmpty(orderId)) {
                    myToast("请选择订单");
                    return false;
                }
                if (TextUtils.isEmpty(workOrderType)) {
                    myToast("请选择订单问题");
                    return false;
                }
                break;
            case 2:
                //其他工单
                if (TextUtils.isEmpty(storeId)) {
                    myToast("请选择门店");
                    return false;
                }
                break;
        }

        return true;
    }

    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String imgpath = null;
            Uri uri = null;
            switch (requestCode) {
                case CaptureActivity.REQUEST_CODE_SCAN:
                    //二维码扫码
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                        //{"deviceName": "641708882ef84e09995d70440e12ebf9"}
                        MyLogger.i("扫码返回", result);
                        try {
                            JSONObject mJsonObject = new JSONObject(result);
                            deviceName = mJsonObject.getString("deviceName");
                            tv_shebeiguzhang.setText(deviceName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            myToast("解析出错");
                        }
                    }
                    break;
                case Constant.SELECT_STORE:
                    //选择门店
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        storeId = bundle.getString("storeId");
                        tv_xuanzemendian.setText(storeId);
                    }
                    break;
                case Constant.SELECT_CONTRACT:
                    //选择合同
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        orderId = bundle.getString("orderId");
                        tv_xuanzedingdan.setText(orderId);
                    }
                    break;
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
//                showProgress(true, getString(R.string.app_loading1));
//                imgfile = new File(uri.getPath());
                //压缩
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //如果是拍照，则旋转
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }
                imgfile = FileUtil.bytesToImageFile(AddWorkListActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));
                Glide.with(AddWorkListActivity.this)
                        .load(imgfile)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AddWorkListActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片
            }
        }

    }

    private void requestUpData(Map<String, String> params, String url) {
        OkhttpUtil.okHttpPost(url, params, headerMap, new CallBackUtil<String>() {
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
                Bundle bundle = new Bundle();
                bundle.putString("fetch", "1");//1待接工单2我的工单
                CommonUtil.gotoActivityWithData(AddWorkListActivity.this, MyWorkListActivity.class, bundle, false);
            }
        });
    }


    /**
     * 修改布局
     */
    private void changeUI() {
        showProgress(true, getString(R.string.app_loading2));
        rl_gongdanleixing.setVisibility(View.VISIBLE);
        rl_xuanzemendian.setVisibility(View.VISIBLE);
        rl_xuanzedingdan.setVisibility(View.GONE);
        rl_shebeiguzhang.setVisibility(View.GONE);
        rl_dingdanwenti.setVisibility(View.GONE);
        rl_guzhangleixing.setVisibility(View.GONE);
        rl_qitashuoming.setVisibility(View.VISIBLE);
        switch (type) {
            case 0:
                //设备工单
                rl_shebeiguzhang.setVisibility(View.VISIBLE);
                rl_guzhangleixing.setVisibility(View.VISIBLE);
                request(params, URLs.AddDeviceWorkList);
                break;
            case 1:
                //订单工单
                rl_xuanzedingdan.setVisibility(View.VISIBLE);
                rl_dingdanwenti.setVisibility(View.VISIBLE);
                request(params, URLs.AddOrderList);
                break;
            case 2:
                //其他工单
                request(params, URLs.AddOtherList);
                break;
        }
    }

    /**
     * 选择工单
     */
    private void dialogList_gongdan() {
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
                (AddWorkListActivity.this, R.layout.item_help, list_work) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (type == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                type = position;
                tv_gongdanleixing.setText(list_work.get(position));
                titleView.setTitle(list_work.get(position));
                adapter.notifyDataSetChanged();
                changeUI();
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
     * 选择故障类型
     */
    private void dialogList_guzhang(TextView textView) {
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
        CommonAdapter<CommonModel.WorkOrderTypeBean> adapter = new CommonAdapter<CommonModel.WorkOrderTypeBean>
                (AddWorkListActivity.this, R.layout.item_help, list_guzhang) {
            @Override
            protected void convert(ViewHolder holder, CommonModel.WorkOrderTypeBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getVal());
                if (i_guzhang == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                i_guzhang = position;
                textView.setText(list_guzhang.get(position).getVal());
                workOrderType = list_guzhang.get(position).getKey();
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
}
