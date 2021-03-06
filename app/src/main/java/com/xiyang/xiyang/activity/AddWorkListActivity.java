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
import com.blankj.utilcode.util.GsonUtils;
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
import com.xiyang.xiyang.model.DeviceInfoModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.Constant;
import com.xiyang.xiyang.utils.FileUtil;
import com.xiyang.xiyang.utils.MyChooseImages;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
 * ????????????
 */
public class AddWorkListActivity extends BaseActivity {
    List<String> list_work = new ArrayList<>();
    List<CommonModel.ListBean> list_guzhang = new ArrayList<>();
    int type = 0, i_guzhang = -1;
    RelativeLayout rl_gongdanleixing, rl_xuanzedingdan, rl_shebeiguzhang, rl_xuanzemendian, rl_dingdanwenti,
            rl_guzhangleixing, rl_qitashuoming;
    EditText tv_gongdanleixing, tv_xuanzedingdan, tv_shebeiguzhang, tv_xuanzemendian, tv_dingdanwenti,
            tv_guzhangleixing, tv_qitashuoming;
    ImageView imageView1;

    String workOrderType = "", deviceName = "", storeId = "", orderId = "", remark = "", images = "", deviceId = "";

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
        list_work.add("????????????");
//        list_work.add("????????????");
        list_work.add("????????????");

        type = getIntent().getIntExtra("type", 0);
        i_guzhang = getIntent().getIntExtra("guzhang", -1);

        deviceName = getIntent().getStringExtra("deviceName");
        tv_shebeiguzhang.setText(deviceName);
        storeId = getIntent().getStringExtra("storeId");
        tv_xuanzemendian.setText(getIntent().getStringExtra("storeName"));

        tv_gongdanleixing.setText(list_work.get(type));
        titleView.setTitle(list_work.get(type));
        if (type == 1) {
            type = 2;
        }
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
                list_guzhang = response.getList();

                if (i_guzhang != -1 && list_guzhang.size() > i_guzhang) {
                    workOrderType = list_guzhang.get(i_guzhang).getId();
                    tv_guzhangleixing.setText(list_guzhang.get(i_guzhang).getName());
                }

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
                //????????????
                ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//???????????????????????????
                        .setShowGalary(true)//????????????????????????
                        .setNeedRing(true);//?????????????????????
                //ScanConfig ?????????????????? ??????????????????
                CaptureActivity.launch(AddWorkListActivity.this, config);
                break;
            case R.id.tv_xuanzemendian:
                //????????????
                if (type != 0) {
                    Intent intent1 = new Intent(AddWorkListActivity.this, MyStoreListActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("requestCode", Constant.SELECT_STORE);
                    intent1.putExtras(bundle1);
                    startActivityForResult(intent1, Constant.SELECT_STORE, bundle1);
                }
                break;
            case R.id.tv_xuanzedingdan:
                //????????????
                Intent intent2 = new Intent(AddWorkListActivity.this, MyContractActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_CONTRACT);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_CONTRACT, bundle2);
                break;
            case R.id.tv_gongdanleixing:
                //??????????????????
                dialogList_gongdan();
                break;
            case R.id.tv_guzhangleixing:
                //????????????
                dialogList_guzhang(tv_guzhangleixing);
                break;
            case R.id.tv_dingdanwenti:
                //????????????
                dialogList_guzhang(tv_dingdanwenti);
                break;
            case R.id.imageView1:
                //????????????
                MyChooseImages.showPhotoDialog(AddWorkListActivity.this);
                break;
            case R.id.tv_confirm:
                //??????
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
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
                            myToast("??????????????????" + err);
                        }

                        @Override
                        public void onResponse(String response) {
                            MyLogger.i(">>>>?????????????????????" + response);
                            images = response;
                            params.clear();
                            params.put("remark", remark);
                            params.put("images", images);
                            switch (type) {
                                case 0:
                                    //????????????
                                    params.put("deviceId", deviceId);
                                    params.put("failureReasonId", workOrderType);
                                    requestUpData(params, URLs.AddDeviceWorkList);
                                    break;
                                case 1:
                                    //????????????
                                    params.put("orderId", orderId);
                                    params.put("workOrderType", workOrderType);
                                    requestUpData(params, URLs.AddOrderList);
                                    break;
                                case 2:
                                    //????????????
                                    params.put("storeId", storeId);
                                    requestUpData(params, URLs.AddOtherList);
                                    break;
                            }
                        }
                    });

                }
                break;
        }
    }

    private boolean match() {
        remark = tv_qitashuoming.getText().toString().trim();
        if (TextUtils.isEmpty(remark)) {
            myToast("?????????????????????");
            return false;
        }
        if (imgfile == null) {
            myToast("?????????????????????");
            return false;
        }
        switch (type) {
            case 0:
                //????????????
                if (TextUtils.isEmpty(deviceName)) {
                    myToast("??????????????????");
                    return false;
                }
                if (TextUtils.isEmpty(workOrderType)) {
                    myToast("?????????????????????");
                    return false;
                }
                break;
            case 1:
                //????????????
                if (TextUtils.isEmpty(orderId)) {
                    myToast("???????????????");
                    return false;
                }
                if (TextUtils.isEmpty(workOrderType)) {
                    myToast("?????????????????????");
                    return false;
                }
                break;
            case 2:
                //????????????
                if (TextUtils.isEmpty(storeId)) {
                    myToast("???????????????");
                    return false;
                }
                break;
        }

        return true;
    }

    /**
     * *****************************************????????????********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String imgpath = null;
            Uri uri = null;
            switch (requestCode) {
                case CaptureActivity.REQUEST_CODE_SCAN:
                    //???????????????
                    if (data != null) {
                        //??????????????????
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                        //{"deviceName": "641708882ef84e09995d70440e12ebf9"}
                        MyLogger.i("????????????", result);
                        try {
                            JSONObject mJsonObject = new JSONObject(result);
                            deviceName = mJsonObject.getString("deviceName");
                            tv_shebeiguzhang.setText(deviceName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            myToast("????????????");
                        }
                        params.clear();
//                        params.put("deviceName",deviceName);
                        requestDeviceInfo(params);
                    }
                    break;
                case Constant.SELECT_STORE:
                    //????????????
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        storeId = bundle.getString("storeId");
                        tv_xuanzemendian.setText(bundle.getString("storeName"));
                    }
                    break;
                case Constant.SELECT_CONTRACT:
                    //????????????
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        orderId = bundle.getString("orderId");
                        tv_xuanzedingdan.setText(orderId);
                    }
                    break;
                case SELECT_PDF_FILE:
                    //??????PDF??????
                    uri = data.getData();
                    String pdfpath = FileUtil.getPath(this, uri);
                    MyLogger.i(">>>>>>>>>????????????????????????" + pdfpath + ">>>>>????????????" + FileUtils.getFileExtension(pdfpath));
                    if (pdfpath != null) {
                        if (FileUtils.getFileExtension(pdfpath).equals("pdf")) {
                            pdffile = new File(pdfpath);
                        } else {
                            myToast("?????????PDF????????????");
                            return;
                        }
                    }
                    break;
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //??????
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imgpath = uri.getPath();
                    MyLogger.i(">>>>>>>>>????????????????????????" + imgpath + ">>>>>????????????" + FileUtils.getFileExtension(imgpath));
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //??????
                    uri = data.getData();
                    imgpath = FileUtil.getPath(this, uri);
                    MyLogger.i(">>>>>>>>>????????????????????????" + imgpath + ">>>>>????????????" + FileUtils.getFileExtension(imgpath));
                    break;

            }
            if (imgpath != null) {
//                showProgress(true, getString(R.string.app_loading1));
//                imgfile = new File(uri.getPath());
                //??????
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //???????????????????????????
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
                        .placeholder(R.mipmap.loading)//???????????????
                        .error(R.mipmap.headimg)//????????????
                        .into(imageView1);//????????????
            }
        }

    }

    /**
     * ??????????????????(???????????????????????????????????????)
     *
     * @param params
     */
    private void requestDeviceInfo(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.DeviceInfo + deviceName, params, headerMap, new CallBackUtil<DeviceInfoModel>() {
            @Override
            public DeviceInfoModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(DeviceInfoModel response) {
                hideProgress();
                deviceId = response.getId();
                tv_xuanzemendian.setText(response.getStoreName());
            }
        });
    }

    /**
     * ????????????
     *
     * @param params
     * @param url
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
                myToast("????????????");
                hideProgress();
                if (localUserInfo.getUserJob().equals("BD")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fetch", "1");//1????????????2????????????
                    CommonUtil.gotoActivityWithData(AddWorkListActivity.this, MyWorkListActivity.class, bundle, true);
                } else finish();

            }
        });
    }

    /**
     * ????????????
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
                //????????????
                rl_shebeiguzhang.setVisibility(View.VISIBLE);
                rl_guzhangleixing.setVisibility(View.VISIBLE);
                request(params, URLs.AddWorkList_GuZhang + 1);//???????????? 1-????????????,2-????????????
                break;
            case 1:
                //????????????
                /*rl_xuanzedingdan.setVisibility(View.VISIBLE);
                rl_dingdanwenti.setVisibility(View.VISIBLE);
                request(params, URLs.AddWorkList_GuZhang + 2);
                break;*/
            case 2:
                //????????????
//                request(params, URLs.AddOtherList);
                hideProgress();
                break;
        }
    }

    /**
     * ????????????
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
                if (position == 1) {
                    type = 2;
                } else {
                    type = position;
                }
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
     * ??????????????????
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
        CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                (AddWorkListActivity.this, R.layout.item_help, list_guzhang) {
            @Override
            protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getName());
                if (i_guzhang == position) {
                    textView.setText(model.getName());
                    tv.setTextColor(getResources().getColor(R.color.green));
                } else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                i_guzhang = position;
                textView.setText(list_guzhang.get(position).getName());
                workOrderType = list_guzhang.get(position).getId();
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
