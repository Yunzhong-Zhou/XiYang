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
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.CommonModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
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
 * 添加商户
 */
public class AddShopActivity extends BaseActivity {
    EditText textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    ImageView imageView1;
    String name = "", companyName = "", contactName = "", contactPhone = "", provinceId = "", cityId = "", areaId = "",
            logoUrl = "", account = "", address = "", industryId = "";

    File imgfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshop);
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
        imageView1 = findViewByID_My(R.id.imageView1);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.imageView1:
                //上传封面
                MyChooseImages.showPhotoDialog(AddShopActivity.this);
                break;
            case R.id.textView6:
                //商户行业
                dialogList_hangye("");
                break;
            case R.id.textView7:
                //所在城市
                dialogList_chengshi("");
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    new UpFileToQiNiuUtil(AddShopActivity.this, imgfile, FileUtils.getFileExtension(imgfile)) {
                        @Override
                        public void complete(boolean isok, String result, String url) {
                            hideProgress();
                            if (isok) {
                                MyLogger.i(">>>>上传文件路径：" + url);
                                logoUrl = url;
                                params.clear();
                                params.put("name", name);
                                params.put("companyName", companyName);
                                params.put("contactName", contactName);
                                params.put("contactPhone", contactPhone);
                                params.put("provinceId", provinceId);
                                params.put("cityId", cityId);
                                params.put("areaId", areaId);
                                params.put("logoUrl", logoUrl);
                                params.put("account", account);
//                    params.put("licenseNo", licenseNo);
//                    params.put("licenseNoImage", licenseNoImage);
                                params.put("address", address);
                                params.put("industryId", industryId);
                                requestUpData(params);

                            } else {
                                myToast(result);
                            }
                        }
                    };
                }
                break;
        }
    }

    private boolean match() {
        name = textView5.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            myToast("请输入商户名称");
            return false;
        }
        account = textView1.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            myToast("请输入商户账号");
            return false;
        }
        contactName = textView2.getText().toString().trim();
        if (TextUtils.isEmpty(contactName)) {
            myToast("请输入商户联系人");
            return false;
        }
        contactPhone = textView3.getText().toString().trim();
        if (TextUtils.isEmpty(contactPhone)) {
            myToast("请输入联系人电话");
            return false;
        }
        companyName = textView4.getText().toString().trim();
        if (TextUtils.isEmpty(companyName)) {
            myToast("请输入公司名称");
            return false;
        }
        if (TextUtils.isEmpty(industryId)) {
            myToast("请选择商户行业");
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
        if (imgfile == null) {
            myToast("请选择封面");
            return false;
        }

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加商户");
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
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //如果是拍照，则旋转
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }

                //压缩
                imgfile = FileUtil.bytesToImageFile(AddShopActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));

                Glide.with(AddShopActivity.this)
                        .load(imgfile)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AddShopActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片
            }
        }

    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddShop, params, headerMap, new CallBackUtil<String>() {
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
                CommonUtil.gotoActivity(AddShopActivity.this, MyShopListActivity.class, true);
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
                rv_list.setLayoutManager(new LinearLayoutManager(AddShopActivity.this));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (AddShopActivity.this, R.layout.item_help, list_hangye) {
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
                            textView6.setText(string_hangye);
                            industryId = list_hangye.get(position).getId();
                            //初始化
                            string_hangye = "";
                            maxIdex_hangye = 2;

                            dialog.dismiss();
                        } else {
                            dialogList_hangye(list_hangye.get(position).getParentId());
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
                                CommonUtil.dip2px(AddShopActivity.this, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(AddShopActivity.this));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (AddShopActivity.this, R.layout.item_help, list_chengshi) {
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
                                textView7.setText(string_chengshi);
                                areaId = list_chengshi.get(position).getId();
                                //初始化
                                string_chengshi = "";
                                maxIdex_chengshi = 3;

                                dialog.dismiss();
                                break;
                            case 1:
                                //市
                                cityId = list_chengshi.get(position).getId();
                                dialogList_chengshi(list_chengshi.get(position).getParentId());
                                break;
                            case 2:
                                //省
                                provinceId = list_chengshi.get(position).getId();
                                dialogList_chengshi(list_chengshi.get(position).getParentId());
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
}
