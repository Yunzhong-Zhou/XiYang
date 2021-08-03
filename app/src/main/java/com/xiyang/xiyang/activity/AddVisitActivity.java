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
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.CommonModel1;
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
 * 拜访
 */
public class AddVisitActivity extends BaseActivity {
    List<String> list_visit = new ArrayList<>();
    List<String> list_truefalse = new ArrayList<>();
    List<CommonModel1.ListBean> list_fangshi = new ArrayList<>();
    List<CommonModel1.ListBean> list_yingye = new ArrayList<>();
    List<CommonModel1.ListBean> list_fengxian = new ArrayList<>();
    List<CommonModel1.ListBean> list_fankui = new ArrayList<>();
    List<String> list_jingdui = new ArrayList<>();
    List<CommonModel1.ListBean> list_yuanyin = new ArrayList<>();

    int type = 0;//  0-远程拜访，1-上门拜访,2-陌生拜访
    int item_fangshi = -1, item_yingye = -1, item_fengxian = -1, item_fankui = -1, item_jingdui = 0, item_yuanyin = -1, itme_truefalse = 1;
    RelativeLayout rl_xuanzefangshi, rl_xuanzemendian, rl_baifangjilu, rl_yingyeqingkuang, rl_hezuofengxian,
            rl_baifangmendian, rl_baifangrenyuan, rl_lianxidianhua, rl_baifangshijian, rl_mendiandizhi,
            rl_baifangfangshi, rl_shifouyixiang, rl_baifanglianxiren, rl_baifangyuanyin, rl_baifangfankui,
            rl_shanghujingdui, rl_buchongshuoming;
    EditText tv_xuanzefangshi, tv_xuanzemendian, tv_baifangjilu, tv_yingyeqingkuang, tv_hezuofengxian,
            tv_baifangmendian, tv_baifangrenyuan, tv_lianxidianhua, tv_baifangshijian, tv_mendiandizhi,
            tv_baifangfangshi, tv_shifouyixiang, tv_baifanglianxiren, tv_baifangyuanyin, tv_baifangfankui,
            tv_shanghujingdui, tv_buchongshuoming;
    ImageView imageView1;

    String storeId = "", isBusiness = "", reportStatus = "", visitChannel = "", contactName = "", reason = "",
            feedback = "", remark = "", images = "", intention = "1", visitTime = "", storeName = "",
            contractMobile = "", address = "";
    File imgfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvisit);
    }

    @Override
    protected void initView() {
        rl_xuanzefangshi = findViewByID_My(R.id.rl_xuanzefangshi);
        rl_xuanzemendian = findViewByID_My(R.id.rl_xuanzemendian);
        rl_baifangjilu = findViewByID_My(R.id.rl_baifangjilu);
        rl_yingyeqingkuang = findViewByID_My(R.id.rl_yingyeqingkuang);
        rl_hezuofengxian = findViewByID_My(R.id.rl_hezuofengxian);
        rl_baifangmendian = findViewByID_My(R.id.rl_baifangmendian);
        rl_baifangrenyuan = findViewByID_My(R.id.rl_baifangrenyuan);
        rl_lianxidianhua = findViewByID_My(R.id.rl_lianxidianhua);
        rl_baifangshijian = findViewByID_My(R.id.rl_baifangshijian);
        rl_baifangfangshi = findViewByID_My(R.id.rl_baifangfangshi);
        rl_shifouyixiang = findViewByID_My(R.id.rl_shifouyixiang);
        rl_baifanglianxiren = findViewByID_My(R.id.rl_baifanglianxiren);
        rl_mendiandizhi = findViewByID_My(R.id.rl_mendiandizhi);
        rl_baifangyuanyin = findViewByID_My(R.id.rl_baifangyuanyin);
        rl_baifangfankui = findViewByID_My(R.id.rl_baifangfankui);
        rl_shanghujingdui = findViewByID_My(R.id.rl_shanghujingdui);
        rl_buchongshuoming = findViewByID_My(R.id.rl_buchongshuoming);

        tv_xuanzefangshi = findViewByID_My(R.id.tv_xuanzefangshi);
        tv_xuanzemendian = findViewByID_My(R.id.tv_xuanzemendian);
        tv_baifangjilu = findViewByID_My(R.id.tv_baifangjilu);
        tv_yingyeqingkuang = findViewByID_My(R.id.tv_yingyeqingkuang);
        tv_hezuofengxian = findViewByID_My(R.id.tv_hezuofengxian);
        tv_baifangmendian = findViewByID_My(R.id.tv_baifangmendian);
        tv_baifangrenyuan = findViewByID_My(R.id.tv_baifangrenyuan);
        tv_lianxidianhua = findViewByID_My(R.id.tv_lianxidianhua);
        tv_baifangshijian = findViewByID_My(R.id.tv_baifangshijian);
        tv_baifangfangshi = findViewByID_My(R.id.tv_baifangfangshi);
        tv_shifouyixiang = findViewByID_My(R.id.tv_shifouyixiang);
        tv_baifanglianxiren = findViewByID_My(R.id.tv_baifanglianxiren);
        tv_mendiandizhi = findViewByID_My(R.id.tv_mendiandizhi);
        tv_baifangyuanyin = findViewByID_My(R.id.tv_baifangyuanyin);
        tv_baifangfankui = findViewByID_My(R.id.tv_baifangfankui);
        tv_shanghujingdui = findViewByID_My(R.id.tv_shanghujingdui);
        tv_buchongshuoming = findViewByID_My(R.id.tv_buchongshuoming);
        imageView1 = findViewByID_My(R.id.imageView1);


    }

    @Override
    protected void initData() {
        list_visit.add("远程拜访");
        list_visit.add("上门拜访");
        list_visit.add("陌生拜访");


        list_truefalse.add("是");
        list_truefalse.add("否");

        list_jingdui.add("否");
        list_jingdui.add("是");


        type = getIntent().getIntExtra("type", 0);
        tv_xuanzefangshi.setText(list_visit.get(type));
        titleView.setTitle(list_visit.get(type));

        changeUI();

        request(params);
    }

    private void request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.MeiJuList, params, headerMap, new CallBackUtil<CommonModel1>() {
            @Override
            public CommonModel1 onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(CommonModel1 response) {
                hideProgress();
                list_fangshi = response.getVisitTypeEnum().getList();//拜访方式
                list_yingye = response.getBusinessSituationEnum().getList();//营业情况
                list_fengxian = response.getCooperationRiskEnum().getList();//合作风险
                list_fankui = response.getVisitFeedbackEnum().getList();//拜访反馈
                list_yuanyin = response.getVisitReasonEnum().getList();//拜访原因
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
            case R.id.tv_xuanzefangshi:
                //选择方式
                dialogList_visit();
                break;
            case R.id.tv_yingyeqingkuang:
                //营业情况
                dialogList_yingye();
                break;
            case R.id.tv_baifangfangshi:
                //拜访方式
                dialogList_fangshi();
                break;
            case R.id.tv_hezuofengxian:
                //合作风险
                dialogList_fengxian();
                break;
            case R.id.tv_baifangyuanyin:
                //拜访原因
                dialogList_yuanyin();
                break;
            case R.id.tv_baifangfankui:
                //拜访反馈
                dialogList_fankui();
                break;
            case R.id.tv_shanghujingdui:
                //商户竞对
                dialogList_jingdui();
                break;
            case R.id.tv_shifouyixiang:
                //是否独家
                dialogList_TrueFalse();
                break;
            case R.id.tv_baifangshijian:
                //拜访时间
                CommonUtil.selectDate2YMD(AddVisitActivity.this,
                        "请选择拜访时间", tv_baifangshijian, tv_baifangshijian.getText().toString().trim());
                break;
            case R.id.tv_xuanzemendian:
                //选择门店
                Intent intent1 = new Intent(AddVisitActivity.this, MyStoreListActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("requestCode", Constant.SELECT_STORE);
                bundle1.putString("status", "");//状态 0 => '待指派',1 => '待签约',2 => '待审核',3 => '正常',4 => '待续约'
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, Constant.SELECT_STORE, bundle1);
                break;
            case R.id.tv_baifangjilu:
                //拜访记录
                Bundle bundle = new Bundle();
                bundle.putString("type", type + 1 + "");
                /*switch (type){
                    case 1:
                        bundle.putString("type","2");
                        break;
                    case 2:
                        bundle.putString("type","3");
                        break;
                    case 3:
                        bundle.putString("type","1");
                        break;
                }*/
                CommonUtil.gotoActivityWithData(AddVisitActivity.this, MyVisitListActivity.class, bundle, true);
                break;
            case R.id.imageView1:
                //上传图片
                MyChooseImages.showPhotoDialog(AddVisitActivity.this);
                break;
            case R.id.tv_confirm:
                //提交
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
                            myToast("图片上传失败" + err);
                        }

                        @Override
                        public void onResponse(String response) {
                            MyLogger.i(">>>>上传文件路径：" + response);
                            images = response;//文件地址
                            switch (type) {
                                case 0:
                                    //远程拜访
//                                    params.put("type", Integer.valueOf(type) + 1 + "");//拜访方式 1-远程拜访，2-上门拜访,3-陌生拜访
                                    params.put("storeId", storeId);//门店id 陌生拜访为0
                                    params.put("storeName", tv_xuanzemendian.getText().toString());//门店名称
                                    params.put("isBusiness", isBusiness);//当前营业状况 1-是，2-否
                                    params.put("reportStatus", reportStatus);//合作风险上报
                                    params.put("way", visitChannel);//拜访方式
                                    params.put("contractName", contactName);//联系人
                                    params.put("reason", reason);//拜访原因
                                    params.put("feedback", feedback);//拜访反馈
                                    params.put("isAdver", item_jingdui + "");//商户存在竟对 0否1是
                                    params.put("remark", remark);//补充说明
                                    params.put("images", images);
                                    requestUpData(params, URLs.AddVisit_YuanCheng);
                                    break;
                                case 1:
                                    //上门拜访
//                                    params.put("type", Integer.valueOf(type) + 1 + "");//拜访方式 1-远程拜访，2-上门拜访,3-陌生拜访
                                    params.put("storeId", storeId);//门店id 陌生拜访为0
                                    params.put("storeName", tv_xuanzemendian.getText().toString());//门店名称
                                    params.put("isBusiness", isBusiness);//当前营业状况 1-是，2-否
                                    params.put("reportStatus", reportStatus);//合作风险上报
                                    params.put("way", visitChannel);//拜访方式
                                    params.put("contractName", contactName);//联系人
                                    params.put("reason", reason);//拜访原因
                                    params.put("feedback", feedback);//拜访反馈
                                    params.put("isAdver", item_jingdui + "");//商户存在竟对 0否1是
                                    params.put("remark", remark);//补充说明
                                    params.put("images", images);
                                    requestUpData(params, URLs.AddVisit_ShangMen);

                                    break;
                                case 2:
                                    //陌生拜访
//                                    params.put("type", Integer.valueOf(type) + 1  + "");
//                                    params.put("storeId", "0");
                                    params.put("storeName", storeName);//门店名称
                                    params.put("contractName", contactName);//联系人
                                    params.put("contractMobile", contractMobile);//联系人电话
                                    params.put("visitTime", visitTime);//拜访时间
                                    params.put("address", address);//拜访地址
                                    params.put("way", visitChannel);//拜访方式
                                    params.put("isIntention", itme_truefalse + "");//是否意向0默认1是2否
                                    params.put("remark", remark);
                                    params.put("images", images);
                                    requestUpData(params, URLs.AddVisit_MoSheng);

                                    break;
                            }
                        }
                    });

                }

                break;

        }
    }

    private boolean match() {
        switch (type) {
            case 0:
                //远程拜访
            case 1:
                //上门拜访
                if (TextUtils.isEmpty(storeId)) {
                    myToast("请选择门店");
                    return false;
                }
                if (TextUtils.isEmpty(isBusiness)) {
                    myToast("请选择营业情况");
                    return false;
                }
                if (TextUtils.isEmpty(reportStatus)) {
                    myToast("请选择合作风险");
                    return false;
                }
                if (TextUtils.isEmpty(visitChannel)) {
                    myToast("请选择拜访方式");
                    return false;
                }
                contactName = tv_baifanglianxiren.getText().toString().trim();
                if (TextUtils.isEmpty(contactName)) {
                    myToast("请输入拜访联系人");
                    return false;
                }
                if (TextUtils.isEmpty(reason)) {
                    myToast("请选择拜访原因");
                    return false;
                }
                if (TextUtils.isEmpty(feedback)) {
                    myToast("请选择拜访反馈");
                    return false;
                }
                /*if (TextUtils.isEmpty(isAdver)) {
                    myToast("请选择是否存在竞对");
                    return false;
                }*/
                remark = tv_buchongshuoming.getText().toString().trim();
                if (TextUtils.isEmpty(remark)) {
                    myToast("请输入补充说明");
                    return false;
                }
                if (imgfile == null) {
                    myToast("请选择拜访照片");
                    return false;
                }

                break;
            case 2:
                //陌生拜访
                storeName = tv_baifangmendian.getText().toString().trim();
                if (TextUtils.isEmpty(storeName)) {
                    myToast("请输入拜访门店");
                    return false;
                }
                contactName = tv_baifangrenyuan.getText().toString().trim();
                if (TextUtils.isEmpty(contactName)) {
                    myToast("请输入拜访人员姓名");
                    return false;
                }
                contractMobile = tv_lianxidianhua.getText().toString().trim();
                if (TextUtils.isEmpty(contractMobile)) {
                    myToast("请输入联系电话");
                    return false;
                }
                visitTime = tv_baifangshijian.getText().toString().trim();
                if (TextUtils.isEmpty(visitTime)) {
                    myToast("请选择拜访时间");
                    return false;
                } else {
                    visitTime = visitTime + " 00:00:00";
//                    visitTime = TimeUtils.string2Millis(visitTime, "yyyy-MM-dd")/1000 + "";
//                    renewalTime = TimeUtils.string2Date(TimeUtils.string2Millis(renewalTime, "yyyy-MM-dd") + "") + "";
                }
                address = tv_mendiandizhi.getText().toString().trim();
                if (TextUtils.isEmpty(address)) {
                    myToast("请输入门店地址");
                    return false;
                }

                if (TextUtils.isEmpty(visitChannel)) {
                    myToast("请选择拜访方式");
                    return false;
                }

                remark = tv_buchongshuoming.getText().toString().trim();
                if (TextUtils.isEmpty(remark)) {
                    myToast("请输入补充说明");
                    return false;
                }
                if (imgfile == null) {
                    myToast("请选择拜访照片");
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
                case Constant.SELECT_STORE:
                    //选择门店
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        storeId = bundle.getString("storeId");
                        tv_xuanzemendian.setText(bundle.getString("storeName"));
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
                imgfile = FileUtil.bytesToImageFile(AddVisitActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));

                Glide.with(AddVisitActivity.this)
                        .load(imgfile)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AddVisitActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片
            }
        }

    }

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
                Bundle bundle = new Bundle();
                bundle.putString("type", type + 1 + "");
                /*switch (type){
                    case 0:
                        bundle.putString("type","2");
                        break;
                    case 1:
                        bundle.putString("type","3");
                        break;
                    case 2:
                        bundle.putString("type","1");
                        break;
                }*/
                CommonUtil.gotoActivityWithData(AddVisitActivity.this, MyVisitListActivity.class, bundle, false);
            }
        });
    }

    private void changeUI() {
        rl_xuanzefangshi.setVisibility(View.VISIBLE);
        rl_xuanzemendian.setVisibility(View.GONE);
        rl_baifangjilu.setVisibility(View.GONE);
        rl_yingyeqingkuang.setVisibility(View.GONE);
        rl_hezuofengxian.setVisibility(View.GONE);
        rl_baifangmendian.setVisibility(View.GONE);
        rl_baifangrenyuan.setVisibility(View.GONE);
        rl_lianxidianhua.setVisibility(View.GONE);
        rl_baifangshijian.setVisibility(View.GONE);
        rl_baifangfangshi.setVisibility(View.GONE);
        rl_shifouyixiang.setVisibility(View.GONE);
        rl_baifanglianxiren.setVisibility(View.GONE);
        rl_mendiandizhi.setVisibility(View.GONE);
        rl_baifangyuanyin.setVisibility(View.GONE);
        rl_baifangfankui.setVisibility(View.GONE);
        rl_shanghujingdui.setVisibility(View.GONE);
        rl_buchongshuoming.setVisibility(View.VISIBLE);
        switch (type) {
            case 0:
                //远程拜访
            case 1:
                //上门拜访
                rl_xuanzemendian.setVisibility(View.VISIBLE);
                rl_baifangjilu.setVisibility(View.VISIBLE);
                rl_yingyeqingkuang.setVisibility(View.VISIBLE);
                rl_hezuofengxian.setVisibility(View.VISIBLE);
                rl_baifangfangshi.setVisibility(View.VISIBLE);

                rl_baifanglianxiren.setVisibility(View.VISIBLE);
                rl_baifangyuanyin.setVisibility(View.VISIBLE);
                rl_baifangfankui.setVisibility(View.VISIBLE);
                rl_shanghujingdui.setVisibility(View.VISIBLE);
                break;
            case 2:
                //陌生拜访
                rl_baifangmendian.setVisibility(View.VISIBLE);
                rl_baifangrenyuan.setVisibility(View.VISIBLE);
                rl_lianxidianhua.setVisibility(View.VISIBLE);
                rl_baifangshijian.setVisibility(View.VISIBLE);
                rl_mendiandizhi.setVisibility(View.VISIBLE);
                rl_baifangfangshi.setVisibility(View.VISIBLE);
                rl_shifouyixiang.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 选择拜访
     */
    private void dialogList_visit() {
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
                (AddVisitActivity.this, R.layout.item_help, list_visit) {
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
                tv_xuanzefangshi.setText(list_visit.get(position));
                titleView.setTitle(list_visit.get(position));
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
     * 选择拜访方式
     */
    private void dialogList_fangshi() {
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
        CommonAdapter<CommonModel1.ListBean> adapter = new CommonAdapter<CommonModel1.ListBean>
                (AddVisitActivity.this, R.layout.item_help, list_fangshi) {
            @Override
            protected void convert(ViewHolder holder, CommonModel1.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getDescription());
                if (item_fangshi == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_fangshi = position;
                tv_baifangfangshi.setText(list_fangshi.get(position).getDescription());
                visitChannel = list_fangshi.get(position).getCode();

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
     * 选择营业情况
     */
    private void dialogList_yingye() {
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
        CommonAdapter<CommonModel1.ListBean> adapter = new CommonAdapter<CommonModel1.ListBean>
                (AddVisitActivity.this, R.layout.item_help, list_yingye) {
            @Override
            protected void convert(ViewHolder holder, CommonModel1.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getDescription());
                if (item_yingye == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_yingye = position;
                tv_yingyeqingkuang.setText(list_yingye.get(position).getDescription());

                isBusiness = list_yingye.get(position).getCode();

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
     * 选择风险上报
     */
    private void dialogList_fengxian() {
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
        CommonAdapter<CommonModel1.ListBean> adapter = new CommonAdapter<CommonModel1.ListBean>
                (AddVisitActivity.this, R.layout.item_help, list_fengxian) {
            @Override
            protected void convert(ViewHolder holder, CommonModel1.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getDescription());
                if (item_fengxian == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_fengxian = position;
                tv_hezuofengxian.setText(list_fengxian.get(position).getDescription());

                reportStatus = list_fengxian.get(position).getCode();

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
     * 选择拜访原因
     */
    private void dialogList_yuanyin() {
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
        CommonAdapter<CommonModel1.ListBean> adapter = new CommonAdapter<CommonModel1.ListBean>
                (AddVisitActivity.this, R.layout.item_help, list_yuanyin) {
            @Override
            protected void convert(ViewHolder holder, CommonModel1.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getDescription());
                if (item_yuanyin == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_yuanyin = position;
                tv_baifangyuanyin.setText(list_yuanyin.get(position).getDescription());

                reason = list_yuanyin.get(position).getCode();

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
     * 选择拜访反馈
     */
    private void dialogList_fankui() {
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
        CommonAdapter<CommonModel1.ListBean> adapter = new CommonAdapter<CommonModel1.ListBean>
                (AddVisitActivity.this, R.layout.item_help, list_fankui) {
            @Override
            protected void convert(ViewHolder holder, CommonModel1.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getDescription());
                if (item_fankui == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_fankui = position;
                tv_baifangfankui.setText(list_fankui.get(position).getDescription());

                feedback = list_fankui.get(position).getCode();

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
     * 选择商户竞对
     */
    private void dialogList_jingdui() {
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
                (AddVisitActivity.this, R.layout.item_help, list_jingdui) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (item_jingdui == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_jingdui = position;
                tv_shanghujingdui.setText(list_jingdui.get(position));

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
     * 选择是否意向
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
                (AddVisitActivity.this, R.layout.item_help, list_truefalse) {
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
                itme_truefalse = position + 1;
                tv_shifouyixiang.setText(list_truefalse.get(position));
                intention = position + "";

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
