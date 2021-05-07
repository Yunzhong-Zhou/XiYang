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

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.FileUtils;
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

import java.io.File;
import java.util.ArrayList;
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
 * 添加门店
 */
public class AddStoreActivity extends BaseActivity {
    EditText textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8,
            textView9, textView10, textView11, textView12, textView13, textView14;
    ImageView imageView1;
    String merchantId = "", name = "", childName = "", account = "", contactName = "", contactPhone = "", instudryId = "",
            postionId = "", address = "", businessHours = "", isTrsanfter = "1", storeScale = "", workerScale = "", deviceScale = "",
            image = "", latitude = "", longitude = "";
    List<String> list_truefalse = new ArrayList<>();
    int itme_truefalse = 1;
    List<String> list_yingye = new ArrayList<>();
    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;

    File imgfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstore);
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
        textView11 = findViewByID_My(R.id.textView11);
        textView12 = findViewByID_My(R.id.textView12);
        textView13 = findViewByID_My(R.id.textView13);
        textView14 = findViewByID_My(R.id.textView14);

        imageView1 = findViewByID_My(R.id.imageView1);
    }

    @Override
    protected void initData() {
        merchantId = getIntent().getStringExtra("shopId");
        textView1.setText(getIntent().getStringExtra("shopName"));

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


        getMyLocation();//获取当前位置

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //选择商户
                Intent intent2 = new Intent(AddStoreActivity.this, MyShopListActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_SHOP);
                bundle2.putString("status", "");//状态 0 => '待指派',1 => '待签约',2 => '待审核',3 => '正常',4 => '待续约'
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_SHOP, bundle2);
                break;
            case R.id.imageView1:
                //上传封面
                MyChooseImages.showPhotoDialog(AddStoreActivity.this);
                break;
            case R.id.textView7:
                //商户行业
                dialogList_hangye("");
                break;
            case R.id.textView8:
                //所在城市
                dialogList_chengshi("");
                break;
            case R.id.textView11:
                //是否划转
                dialogList_TrueFalse();
                break;
            case R.id.textView10:
                //营业时间
                dialogList_yingye();
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    new UpFileToQiNiuUtil(AddStoreActivity.this, imgfile, FileUtils.getFileExtension(imgfile)) {
                        @Override
                        public void complete(boolean isok, String result, String url) {
                            if (isok) {
                                MyLogger.i(">>>>上传文件路径：" + url);
                                image = url;
                                params.clear();
                                params.put("name", name);
                                params.put("merchantId", merchantId);
                                params.put("contactName", contactName);
                                params.put("contactPhone", contactPhone);
                                params.put("childName", childName);
                                params.put("businessHours", businessHours);
                                params.put("isTrsanfter", isTrsanfter);
                                params.put("storeScale", storeScale);
                                params.put("account", account);
                                params.put("workerScale", workerScale);
                                params.put("deviceScale", deviceScale);
                                params.put("address", address);
                                params.put("image", image);
                                params.put("instudryId", instudryId);
                                params.put("postionId", postionId);
                                params.put("latitude", latitude);
                                params.put("longitude", longitude);
                                requestUpData(params);

                            } else {
                                hideProgress();
                                myToast("图片上传失败"+result);
                            }
                        }
                    };
                }
                break;
        }
    }


    private boolean match() {
        if (TextUtils.isEmpty(merchantId)) {
            myToast("请选择商户");
            return false;
        }
        name = textView2.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            myToast("请输入门店名称");
            return false;
        }
        childName = textView3.getText().toString().trim();
        if (TextUtils.isEmpty(childName)) {
            myToast("请输入分店名称");
            return false;
        }
        account = textView4.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            myToast("请输入门店账号");
            return false;
        }
        contactName = textView5.getText().toString().trim();
        if (TextUtils.isEmpty(contactName)) {
            myToast("请输入门店联系人");
            return false;
        }
        contactPhone = textView6.getText().toString().trim();
        if (TextUtils.isEmpty(contactPhone)) {
            myToast("请输入门店联系电话");
            return false;
        }
        if (TextUtils.isEmpty(instudryId)) {
            myToast("请选择行业");
            return false;
        }
       /* if (TextUtils.isEmpty(provinceId)) {
            myToast("请选择省");
            return false;
        }
        if (TextUtils.isEmpty(cityId)) {
            myToast("请选择市");
            return false;
        }*/
        if (TextUtils.isEmpty(postionId)) {
            myToast("请选择省市区");
            return false;
        }

        address = textView9.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            myToast("请输入详细地址");
            return false;
        }

        businessHours = textView10.getText().toString().trim();
        if (TextUtils.isEmpty(businessHours)) {
            myToast("请选择营业时间");
            return false;
        }
        /*if (TextUtils.isEmpty(isTrsanfter)) {
            myToast("请选择是否划转");
            return false;
        }*/
        storeScale = textView12.getText().toString().trim();
        if (TextUtils.isEmpty(storeScale)) {
            myToast("请输入门店分成");
            return false;
        }
        workerScale = textView13.getText().toString().trim();
        if (TextUtils.isEmpty(workerScale)) {
            myToast("请输入员工分成");
            return false;
        }
        deviceScale = textView14.getText().toString().trim();
        if (TextUtils.isEmpty(deviceScale)) {
            myToast("请输入设备分成");
            return false;
        }
        if (imgfile == null) {
            myToast("请选择门头照片");
            return false;
        }
        if (TextUtils.isEmpty(longitude)) {
            showToast("暂未获取到经纬度,点击确定重新获取", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getMyLocation();
                }
            });
            return false;
        }

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加门店");
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
                case Constant.SELECT_SHOP:
                    //选择商户
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        merchantId = bundle.getString("shopId");
                        textView1.setText(bundle.getString("shopName"));
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
//                imgfile = new File(uri.getPath());
                //压缩
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //如果是拍照，则旋转
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }
                imgfile = FileUtil.bytesToImageFile(AddStoreActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));
                Glide.with(AddStoreActivity.this)
                        .load(imgfile)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AddStoreActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片
            }
        }

    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddStore, params, headerMap, new CallBackUtil<String>() {
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
                myToast("添加成功");
                hideProgress();
//                finish();
                CommonUtil.gotoActivity(AddStoreActivity.this, MyStoreListActivity.class, true);
            }
        });
    }

    /**
     * 选择行业
     */
    List<CommonModel.ListBean> list_hangye = new ArrayList<>();
    int maxIdex_hangye = 2;
    String string_hangye = "";

    private void dialogList_hangye(String parentId) {
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
        params.put("parentId", parentId);
        OkhttpUtil.okHttpGet(URLs.Industry, params, headerMap, new CallBackUtil<CommonModel>() {
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
                rv_list.setLayoutManager(new LinearLayoutManager(AddStoreActivity.this));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (AddStoreActivity.this, R.layout.item_help, list_hangye) {
                    @Override
                    protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
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
                            textView7.setText(string_hangye);
                            instudryId = list_hangye.get(position).getId();
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
    int maxIdex_chengshi = 3;
    String string_chengshi = "";

    private void dialogList_chengshi(String parentId) {
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
        params.put("parentId", parentId);
        OkhttpUtil.okHttpGet(URLs.Region, params, headerMap, new CallBackUtil<CommonModel>() {
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
                                CommonUtil.dip2px(AddStoreActivity.this, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(AddStoreActivity.this));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (AddStoreActivity.this, R.layout.item_help, list_chengshi) {
                    @Override
                    protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model.getName());
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        maxIdex_chengshi--;
                        string_chengshi = string_chengshi + list_chengshi.get(position).getName() + "-";
                        switch (maxIdex_chengshi) {
                            case 0:
                                //区
                                //最后一个，赋值
                                if (!string_chengshi.equals("")) {
                                    string_chengshi = string_chengshi.substring(0, string_chengshi.length() - 1);
                                }
                                textView8.setText(string_chengshi);
                                postionId = list_chengshi.get(position).getId();
                                //初始化
                                string_chengshi = "";
                                maxIdex_chengshi = 3;

                                dialog.dismiss();
                                break;
                            case 1:
                                //市
                                postionId = list_chengshi.get(position).getId();
                                dialogList_chengshi(list_chengshi.get(position).getId());
                                break;
                            case 2:
                                //省
                                postionId = list_chengshi.get(position).getId();
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
                (AddStoreActivity.this, R.layout.item_help, list_truefalse) {
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
                textView11.setText(list_truefalse.get(position));

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
                textView10.setText(start + "~" + stop);
                dialog.dismiss();
            }
        });
    }

    /**
     * 获取我的当前地址
     */
    private void getMyLocation() {
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。AMapLocationMode.Battery_Saving，低功耗模式。AMapLocationMode.Device_Sensors，仅设备模式。
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //获取一次定位结果：默认为false。
        option.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        option.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        option.setInterval(5 * 1000);
        //设置是否返回地址信息（默认返回地址信息）
        option.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        option.setMockEnable(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        option.setHttpTimeOut(30000);
        //是否开启定位缓存机制
        option.setLocationCacheEnable(false);

        mLocationClient.setLocationOption(option);

        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {

                        MyLogger.i("定位信息", "\n纬度：" + aMapLocation.getLatitude()
                                + "\n经度:" + aMapLocation.getLongitude()
                                + "\n地址:" + aMapLocation.getAddress());
//                        register_addr = aMapLocation.getAddress();

                       /* localUserInfo.setCityname(aMapLocation.getCity());
                        localUserInfo.setLongitude(aMapLocation.getLongitude() + "");
                        localUserInfo.setLatitude(aMapLocation.getLatitude() + "");

                        editText3.setText(aMapLocation.getAddress() + "");*/

                        longitude = aMapLocation.getLatitude() + "";
                        latitude = aMapLocation.getLatitude() + "";


                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        MyLogger.e("定位失败：", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        myToast("" + aMapLocation.getErrorInfo());
                    }
                }
            }
        });

        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//        mLocationClient.stopLocation();
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除

//        if (localUserInfo.getCityname().equals("")) {
        mLocationClient.startLocation();
//        }
    }

}
