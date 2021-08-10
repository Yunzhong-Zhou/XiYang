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
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cy.dialog.BaseDialog;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.CommonModel;
import com.xiyang.xiyang.model.IndustryModel;
import com.xiyang.xiyang.model.StoreDetailModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.FileUtil;
import com.xiyang.xiyang.utils.MyChooseImages;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by Mr.Z on 2021/3/28.
 * 修改门店
 */
public class ChangeStoreActivity extends BaseActivity {
    StoreDetailModel model_sdm;
    EditText textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;
    ImageView imageView1;

    String name = "", childName = "", contactName = "", contactPhone = "", industryId = "",
            address = "", businessHours = "", isTrsanfter = "-1",
            image = "", provinceId = "", cityId = "", areaId = "";

    List<String> list_truefalse = new ArrayList<>();
    int itme_truefalse = 1;
    List<String> list_yingye = new ArrayList<>();

    File imgfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changestore);
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView10 = findViewByID_My(R.id.textView10);
        imageView1 = findViewByID_My(R.id.imageView1);


    }

    @Override
    protected void initData() {
        model_sdm = (StoreDetailModel) getIntent().getSerializableExtra("StoreDetailModel");
        textView1.setText(model_sdm.getStoreInfo().getName());
        textView2.setText(model_sdm.getStoreInfo().getChildName());
        textView3.setHint(model_sdm.getStoreInfo().getAccount());
        textView4.setText(model_sdm.getStoreInfo().getContactName());
        textView5.setText(model_sdm.getStoreInfo().getContactPhone());

        industryId = model_sdm.getStoreInfo().getIndustryId();
        textView6.setText(model_sdm.getStoreInfo().getIndustryName());
        provinceId = model_sdm.getStoreInfo().getProvinceId();
        cityId = model_sdm.getStoreInfo().getCityId();
        areaId = model_sdm.getStoreInfo().getAreaId();
        textView7.setText(model_sdm.getStoreInfo().getProvinceName()+model_sdm.getStoreInfo().getCityName()+model_sdm.getStoreInfo().getAreaName());

        textView8.setText(model_sdm.getStoreInfo().getAddress());
        textView9.setText(model_sdm.getStoreInfo().getBusinessHours());

        isTrsanfter = model_sdm.getStoreInfo().getTransferStatus();
        textView10.setText(model_sdm.getStoreInfo().getTransferStatusStr());

        Glide.with(ChangeStoreActivity.this)
                .load(model_sdm.getStoreInfo().getImage())
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new
                        RoundedCorners(CommonUtil.dip2px(ChangeStoreActivity.this, 10))))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.headimg)//加载失败
                .into(imageView1);//加载图片

        list_truefalse.add("是");
        list_truefalse.add("否");

        list_yingye.add("00:00");
        list_yingye.add("00:30");
        list_yingye.add("01:00");
        list_yingye.add("01:30");
        list_yingye.add("02:00");
        list_yingye.add("02:30");
        list_yingye.add("03:00");
        list_yingye.add("03:30");
        list_yingye.add("04:00");
        list_yingye.add("04:30");
        list_yingye.add("05:00");
        list_yingye.add("05:30");
        list_yingye.add("06:00");
        list_yingye.add("06:30");
        list_yingye.add("07:00");
        list_yingye.add("07:30");
        list_yingye.add("08:00");
        list_yingye.add("08:30");
        list_yingye.add("09:00");
        list_yingye.add("09:30");
        list_yingye.add("10:00");
        list_yingye.add("10:30");
        list_yingye.add("11:00");
        list_yingye.add("11:30");
        list_yingye.add("12:00");
        list_yingye.add("12:30");
        list_yingye.add("13:00");
        list_yingye.add("13:30");
        list_yingye.add("14:00");
        list_yingye.add("14:30");
        list_yingye.add("15:00");
        list_yingye.add("15:30");
        list_yingye.add("16:00");
        list_yingye.add("16:30");
        list_yingye.add("17:00");
        list_yingye.add("17:30");
        list_yingye.add("18:00");
        list_yingye.add("18:30");
        list_yingye.add("19:00");
        list_yingye.add("19:30");
        list_yingye.add("20:00");
        list_yingye.add("20:30");
        list_yingye.add("21:00");
        list_yingye.add("21:30");
        list_yingye.add("22:00");
        list_yingye.add("22:30");
        list_yingye.add("23:00");
        list_yingye.add("23:30");
        list_yingye.add("24:00");


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.imageView1:
                //上传封面
                MyChooseImages.showPhotoDialog(ChangeStoreActivity.this);
                break;
            case R.id.textView6:
                //商户行业
                dialogList_hangye("0");
                break;
            case R.id.textView7:
                //所在城市
                dialogList_chengshi("0");
                break;
            case R.id.textView10:
                //是否划转
                dialogList_TrueFalse();
                break;
            case R.id.textView9:
                //营业时间
                dialogList_yingye();
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    if (imgfile != null) {
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
                                image = response;
                                params.clear();
                                params.put("compulsoryNormalUserToStoreUserType", "true");//是否强制更改为门店用户,默认false
                                params.put("id", model_sdm.getStoreInfo().getId());
//                                params.put("merchantId", model_sdm.getStoreInfo().getMerchantId());//商户id
//                                params.put("storeSn", model_sdm.getStoreInfo().getStoreSn());//门店编号
                                params.put("name", name);//门店名称
                                params.put("childName", childName);//分店名称
//                                params.put("account", model_sdm.getStoreInfo().getAccount());//门店账号
                                params.put("contactName", contactName);//门店联系人
                                params.put("contactPhone", contactPhone);//门店联系电话
                                params.put("industryId", industryId);//行业
                                params.put("provinceId", provinceId);//省
                                params.put("cityId", cityId);//市
                                params.put("areaId", areaId);//区
//                                params.put("latitude", latitude);
//                                params.put("longitude", longitude);
                                params.put("address", address);//详细地址
                                params.put("businessHours", businessHours);//营业时间
                                params.put("isTrsanfter", isTrsanfter);//是否划转
                                params.put("image", image);//门头照片
                                requestUpData(params);
                            }
                        });
                    } else {
                        params.clear();
                        params.put("compulsoryNormalUserToStoreUserType", "true");//是否强制更改为门店用户,默认false
                        params.put("id", model_sdm.getStoreInfo().getId());
//                                params.put("merchantId", model_sdm.getStoreInfo().getMerchantId());//商户id
//                                params.put("storeSn", model_sdm.getStoreInfo().getStoreSn());//门店编号
                        params.put("name", name);//门店名称
                        params.put("childName", childName);//分店名称
//                                params.put("account", model_sdm.getStoreInfo().getAccount());//门店账号
                        params.put("contactName", contactName);//门店联系人
                        params.put("contactPhone", contactPhone);//门店联系电话
                        params.put("industryId", industryId);//行业
                        params.put("provinceId", provinceId);//省
                        params.put("cityId", cityId);//市
                        params.put("areaId", areaId);//区
//                                params.put("latitude", latitude);
//                                params.put("longitude", longitude);
                        params.put("address", address);//详细地址
                        params.put("businessHours", businessHours);//营业时间
                        params.put("isTrsanfter", isTrsanfter);//是否划转
//                                params.put("image", model_sdm.getStoreInfo().getImage());//门头照片
                        requestUpData(params);
                    }

                }
                break;
        }
    }

    private boolean match() {
        name = textView1.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            myToast("请输入门店名称");
            return false;
        }
        childName = textView2.getText().toString().trim();
        if (TextUtils.isEmpty(childName)) {
            myToast("请输入分店名称");
            return false;
        }
        contactName = textView4.getText().toString().trim();
        if (TextUtils.isEmpty(contactName)) {
            myToast("请输入门店联系人");
            return false;
        }
        contactPhone = textView5.getText().toString().trim();
        if (TextUtils.isEmpty(contactPhone)) {
            myToast("请输入门店联系电话");
            return false;
        }
        if (TextUtils.isEmpty(industryId)) {
            myToast("请选择分类");
            return false;
        }
        if (TextUtils.isEmpty(provinceId)) {
            myToast("请选择省");
            return false;
        }
        if (TextUtils.isEmpty(cityId)) {
            myToast("请选择市");
            return false;
        }
        if (TextUtils.isEmpty(areaId)) {
            myToast("请选择区");
            return false;
        }

        address = textView8.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            myToast("请输入详细地址");
            return false;
        }

        businessHours = textView9.getText().toString().trim();
        if (TextUtils.isEmpty(businessHours)) {
            myToast("请选择营业时间");
            return false;
        }
        /*if (TextUtils.isEmpty(isTrsanfter)) {
            myToast("请选择是否划转");
            return false;
        }*/

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("修改门店信息");
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
//                imgfile = new File(uri.getPath());
                //压缩
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //如果是拍照，则旋转
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }
                imgfile = FileUtil.bytesToImageFile(ChangeStoreActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));
                Glide.with(ChangeStoreActivity.this)
                        .load(imgfile)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(ChangeStoreActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片
            }
        }

    }

    /**
     * 提交数据
     *
     * @param params
     */
    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPutJson(URLs.ChangeStore, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
                myToast("修改成功");
                hideProgress();
                finish();
//                CommonUtil.gotoActivity(ChangeStoreActivity.this, MyStoreListActivity.class, true);
            }
        });
    }

    /**
     * 选择行业
     */
    List<IndustryModel.ListBean> list_hangye = new ArrayList<>();
    int maxIdex_hangye = 2;//两级选择
    String string_hangye = "";

    private void dialogList_hangye(String parentId) {
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
//        params.put("parentId", parentId);
        OkhttpUtil.okHttpGet(URLs.Industry + parentId, params, headerMap, new CallBackUtil<IndustryModel>() {
            @Override
            public IndustryModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(IndustryModel response) {
                hideProgress();
                list_hangye = response.getList();
                dialog.contentView(R.layout.dialog_list_center)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(ChangeStoreActivity.this));
                CommonAdapter<IndustryModel.ListBean> adapter = new CommonAdapter<IndustryModel.ListBean>
                        (ChangeStoreActivity.this, R.layout.item_help, list_hangye) {
                    @Override
                    protected void convert(ViewHolder holder, IndustryModel.ListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model.getName());
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        maxIdex_hangye--;
                        string_hangye = string_hangye + list_hangye.get(position).getName() + "-";
                        if (maxIdex_hangye == 0) {
                            //最后一个，赋值
                            if (!string_hangye.equals("")) {
                                string_hangye = string_hangye.substring(0, string_hangye.length() - 1);
                            }
                            textView6.setText(string_hangye);
                            industryId = list_hangye.get(position).getId();
                            //初始化
                            string_hangye = "";
                            maxIdex_hangye = 2;

                            dialog.dismiss();
                        } else {
                            dialogList_hangye(list_hangye.get(position).getId());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_list.setAdapter(adapter);

            }
        });
    }

    /**
     * 选择城市
     */
    List<CommonModel.ListBean> list_chengshi = new ArrayList<>();
    int maxIdex_chengshi = 1;
    String string_chengshi = "";

    private void dialogList_chengshi(String parentId) {
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
        params.put("id", parentId);
        params.put("level", maxIdex_chengshi + "");
        OkhttpUtil.okHttpPostJson(URLs.Region, GsonUtils.toJson(params), headerMap, new CallBackUtil<CommonModel>() {
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
                list_chengshi = response.getList();
                dialog.contentView(R.layout.dialog_list_center)
//                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                CommonUtil.dip2px(ChangeStoreActivity.this, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(ChangeStoreActivity.this));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (ChangeStoreActivity.this, R.layout.item_help, list_chengshi) {
                    @Override
                    protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model.getName());
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        string_chengshi = string_chengshi + list_chengshi.get(position).getName() + "-";
                        switch (maxIdex_chengshi) {
                            case 3:
                                //区 -最后一个，赋值
                                if (!string_chengshi.equals("")) {
                                    string_chengshi = string_chengshi.substring(0, string_chengshi.length() - 1);
                                }
                                textView7.setText(string_chengshi);
                                areaId = list_chengshi.get(position).getId();
                                //初始化
                                string_chengshi = "";
                                maxIdex_chengshi = 1;

                                dialog.dismiss();
                                break;
                            case 2:
                                //市
                                cityId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 3;
                                dialogList_chengshi(list_chengshi.get(position).getId());
                                break;
                            case 1:
                                //省
                                provinceId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 2;
                                dialogList_chengshi(list_chengshi.get(position).getId());
                                break;
                        }

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_list.setAdapter(adapter);

            }
        });
    }

    /**
     * 选择是否
     */
    private void dialogList_TrueFalse() {
        dialog.contentView(R.layout.dialog_list_center)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.CENTER)
                .dimAmount(0.5f)
                .show();
        RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<String> adapter = new CommonAdapter<String>
                (ChangeStoreActivity.this, R.layout.item_help, list_truefalse) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (itme_truefalse == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                itme_truefalse = position;
                textView10.setText(list_truefalse.get(position));

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
     * 选择营业时间
     */
    String start = "", stop = "";

    private void dialogList_yingye() {
//        View outerView = LayoutInflater.from(this).inflate(R.layout.dialog_yingyehsijian, null);
        dialog.contentView(R.layout.dialog_yingyehsijian)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.CENTER)
                .dimAmount(0.5f)
                .show();

        //样式
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.textSize = 12;
        style.textColor = getResources().getColor(R.color.black3);
        style.selectedTextSize = 16;
        style.selectedTextColor = getResources().getColor(R.color.black1);
        style.holoBorderColor = getResources().getColor(R.color.xian);

        WheelView wheelView1 = dialog.findViewById(R.id.wheelView1);
        wheelView1.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wheelView1.setSkin(WheelView.Skin.Holo); // common皮肤
        wheelView1.setStyle(style);
        wheelView1.setWheelData(list_yingye);  // 数据集合

        WheelView wheelView2 = dialog.findViewById(R.id.wheelView2);
        wheelView2.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wheelView2.setSkin(WheelView.Skin.Holo); // common皮肤
        wheelView2.setStyle(style);
        wheelView2.setWheelData(list_yingye);  // 数据集合
        wheelView1.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                start = list_yingye.get(position);
            }
        });
        wheelView2.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                stop = list_yingye.get(position);
            }
        });
        dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView9.setText(start + "~" + stop);
                dialog.dismiss();
            }
        });
    }
}
