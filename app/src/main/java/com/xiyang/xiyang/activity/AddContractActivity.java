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
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.CommonModel;
import com.xiyang.xiyang.model.IndustryModel;
import com.xiyang.xiyang.model.ShopDetailModel;
import com.xiyang.xiyang.model.StoreInfoModel;
import com.xiyang.xiyang.model.WarehouseModel;
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

import static com.xiyang.xiyang.utils.Constant.SELECT_PDF_FILE;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by Mr.Z on 2021/3/28.
 * ????????????
 */
public class AddContractActivity extends BaseActivity {
    List<String> list_hetong = new ArrayList<>();
    List<String> list_truefalse = new ArrayList<>();
    List<CommonModel.ListBean> list_qixian = new ArrayList<>();
    List<CommonModel.ListBean> list_jianshaoyuanyin = new ArrayList<>();
    List<CommonModel.ListBean> list_quxiaoyuanyin = new ArrayList<>();
    List<CommonModel.StatusBean> list_mianfeishichang = new ArrayList<>();

    List<String> list_jifeidanyuan = new ArrayList<>();

    List<WarehouseModel.ListBean> list_cangku = new ArrayList<>();
    List<String> list_huishou = new ArrayList<>();
    int item_hetong = 0, itme_truefalse = 1, item_qixian = -1, storetype = 0, item_cangku = -1,
            item_huishou = 0, item_jianshaoyuanyin = -1, item_quxiaoyuanyin = -1, item_jifeidanyuan = -1,item_mianfeishichang = -1;
    RelativeLayout rl_hetongleixing, rl_xuanzeshanghu, rl_xuanzemendian, rl_shanghumingcheng, rl_shanghuzhanghao,
            rl_shanghulianxiren, rl_lianxirendianhua, rl_gongsimingcheng, rl_yinyezhizhaohao, rl_shanghuhangye,
            rl_suozaichengshi, rl_xiangxidizhi, rl_shougexiaoshi, rl_jichujijia, rl_meirifengding, rl_mianfeishichang,
            rl_jifeidanyuan, rl_mendianjiajia, rl_mendianfengding, rl_tiaojialiyou, rl_zhuanchumendian,
            rl_zhuanrumendian, rl_zhuanchushebei, rl_shebeishuliang, rl_jianshaoyuanyin, rl_tuihuicangku,
            rl_huishoufangshi, rl_shoujianren, rl_shoujiandianhua, rl_shoujiandizhi, rl_xinzengshuliang,
            rl_qianyueqixian, rl_xuqiannianxian, rl_shifoudujia, rl_qianyueshijian, rl_xuqianshijian,
            rl_xuanzeyuanyin, rl_hetongwenjian;
    EditText tv_hetongleixing, tv_xuanzeshanghu, tv_xuanzemendian, tv_shanghumingcheng, tv_shanghuzhanghao,
            tv_shanghulianxiren, tv_lianxirendianhua, tv_gongsimingcheng, tv_yinyezhizhaohao, tv_shanghuhangye,
            tv_suozaichengshi, tv_xiangxidizhi, tv_shougexiaoshi, tv_jichujijia, tv_meirifengding, tv_mianfeishichang,
            tv_jifeidanyuan, tv_mendianjiajia, tv_mendianfengding, tv_tiaojialiyou, tv_zhuanchumendian,
            tv_zhuanrumendian, tv_zhuanchushebei, tv_shebeishuliang, tv_jianshaoyuanyin, tv_tuihuicangku,
            tv_huishoufangshi, tv_shoujianren, tv_shoujiandianhua, tv_shoujiandizhi, tv_xinzengshuliang,
            tv_qianyueqixian, tv_xuqiannianxian, tv_shifoudujia, tv_qianyueshijian, tv_xuqianshijian,
            tv_xuanzeyuanyin, tv_hetongwenjian;

    TextView tv_img, tv_confirm;
    ImageView iv_add;

    String storeId = "", shopId = "", contractType = "", sole = "1", renewalPeriod = "", signTime = "", file = "",
            licenseNo = "", licenseNoImage = "", deviceNum = "", outStoreId = "", inStoreId = "", renewalTime = "",
            reasonId = "", warehouseId = "", recyleType = "1", industryId = "", provinceId = "", cityId = "", areaId = "",
            logoUrl = "", address = "", name = "", account = "", contactName = "", contactPhone = "", companyName = "",
            storeUnitPrice = "", storeCapping = "", storeUnit = "", reason = "",storeFreeTime="";


    File imgfile = null;
    File pdffile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontract);
    }

    @Override
    protected void initView() {
        rl_hetongleixing = findViewByID_My(R.id.rl_hetongleixing);
        rl_xuanzeshanghu = findViewByID_My(R.id.rl_xuanzeshanghu);
        rl_xuanzemendian = findViewByID_My(R.id.rl_xuanzemendian);
        rl_shanghumingcheng = findViewByID_My(R.id.rl_shanghumingcheng);
        rl_shanghuzhanghao = findViewByID_My(R.id.rl_shanghuzhanghao);
        rl_shanghulianxiren = findViewByID_My(R.id.rl_shanghulianxiren);
        rl_lianxirendianhua = findViewByID_My(R.id.rl_lianxirendianhua);
        rl_gongsimingcheng = findViewByID_My(R.id.rl_gongsimingcheng);
        rl_yinyezhizhaohao = findViewByID_My(R.id.rl_yinyezhizhaohao);
        rl_shanghuhangye = findViewByID_My(R.id.rl_shanghuhangye);
        rl_suozaichengshi = findViewByID_My(R.id.rl_suozaichengshi);
        rl_xiangxidizhi = findViewByID_My(R.id.rl_xiangxidizhi);
        rl_shougexiaoshi = findViewByID_My(R.id.rl_shougexiaoshi);
        rl_jichujijia = findViewByID_My(R.id.rl_jichujijia);
        rl_meirifengding = findViewByID_My(R.id.rl_meirifengding);
        rl_mianfeishichang = findViewByID_My(R.id.rl_mianfeishichang);
        rl_jifeidanyuan = findViewByID_My(R.id.rl_jifeidanyuan);
        rl_mendianjiajia = findViewByID_My(R.id.rl_mendianjiajia);
        rl_mendianfengding = findViewByID_My(R.id.rl_mendianfengding);
        rl_tiaojialiyou = findViewByID_My(R.id.rl_tiaojialiyou);
        rl_zhuanchumendian = findViewByID_My(R.id.rl_zhuanchumendian);
        rl_zhuanrumendian = findViewByID_My(R.id.rl_zhuanrumendian);
        rl_zhuanchushebei = findViewByID_My(R.id.rl_zhuanchushebei);
        rl_shebeishuliang = findViewByID_My(R.id.rl_shebeishuliang);
        rl_jianshaoyuanyin = findViewByID_My(R.id.rl_jianshaoyuanyin);
        rl_tuihuicangku = findViewByID_My(R.id.rl_tuihuicangku);
        rl_huishoufangshi = findViewByID_My(R.id.rl_huishoufangshi);
        rl_shoujianren = findViewByID_My(R.id.rl_shoujianren);
        rl_shoujiandianhua = findViewByID_My(R.id.rl_shoujiandianhua);
        rl_xinzengshuliang = findViewByID_My(R.id.rl_xinzengshuliang);
        rl_qianyueqixian = findViewByID_My(R.id.rl_qianyueqixian);
        rl_xuqiannianxian = findViewByID_My(R.id.rl_xuqiannianxian);
        rl_shoujiandizhi = findViewByID_My(R.id.rl_shoujiandizhi);
        rl_shifoudujia = findViewByID_My(R.id.rl_shifoudujia);
        rl_qianyueshijian = findViewByID_My(R.id.rl_qianyueshijian);
        rl_xuqianshijian = findViewByID_My(R.id.rl_xuqianshijian);
        rl_xuanzeyuanyin = findViewByID_My(R.id.rl_xuanzeyuanyin);
        rl_hetongwenjian = findViewByID_My(R.id.rl_hetongwenjian);

        tv_hetongleixing = findViewByID_My(R.id.tv_hetongleixing);
        tv_xuanzeshanghu = findViewByID_My(R.id.tv_xuanzeshanghu);
        tv_xuanzemendian = findViewByID_My(R.id.tv_xuanzemendian);
        tv_shanghumingcheng = findViewByID_My(R.id.tv_shanghumingcheng);
        tv_shanghuzhanghao = findViewByID_My(R.id.tv_shanghuzhanghao);
        tv_shanghulianxiren = findViewByID_My(R.id.tv_shanghulianxiren);
        tv_lianxirendianhua = findViewByID_My(R.id.tv_lianxirendianhua);
        tv_gongsimingcheng = findViewByID_My(R.id.tv_gongsimingcheng);
        tv_yinyezhizhaohao = findViewByID_My(R.id.tv_yinyezhizhaohao);
        tv_shanghuhangye = findViewByID_My(R.id.tv_shanghuhangye);
        tv_suozaichengshi = findViewByID_My(R.id.tv_suozaichengshi);
        tv_xiangxidizhi = findViewByID_My(R.id.tv_xiangxidizhi);
        tv_shougexiaoshi = findViewByID_My(R.id.tv_shougexiaoshi);
        tv_jichujijia = findViewByID_My(R.id.tv_jichujijia);
        tv_meirifengding = findViewByID_My(R.id.tv_meirifengding);
        tv_mianfeishichang = findViewByID_My(R.id.tv_mianfeishichang);
        tv_jifeidanyuan = findViewByID_My(R.id.tv_jifeidanyuan);
        tv_mendianjiajia = findViewByID_My(R.id.tv_mendianjiajia);
        tv_mendianfengding = findViewByID_My(R.id.tv_mendianfengding);
        tv_tiaojialiyou = findViewByID_My(R.id.tv_tiaojialiyou);
        tv_zhuanchumendian = findViewByID_My(R.id.tv_zhuanchumendian);
        tv_zhuanrumendian = findViewByID_My(R.id.tv_zhuanrumendian);
        tv_zhuanchushebei = findViewByID_My(R.id.tv_zhuanchushebei);
        tv_shebeishuliang = findViewByID_My(R.id.tv_shebeishuliang);
        tv_jianshaoyuanyin = findViewByID_My(R.id.tv_jianshaoyuanyin);
        tv_tuihuicangku = findViewByID_My(R.id.tv_tuihuicangku);
        tv_huishoufangshi = findViewByID_My(R.id.tv_huishoufangshi);
        tv_shoujianren = findViewByID_My(R.id.tv_shoujianren);
        tv_shoujiandianhua = findViewByID_My(R.id.tv_shoujiandianhua);
        tv_xinzengshuliang = findViewByID_My(R.id.tv_xinzengshuliang);
        tv_qianyueqixian = findViewByID_My(R.id.tv_qianyueqixian);
        tv_xuqiannianxian = findViewByID_My(R.id.tv_xuqiannianxian);
        tv_shoujiandizhi = findViewByID_My(R.id.tv_shoujiandizhi);
        tv_shifoudujia = findViewByID_My(R.id.tv_shifoudujia);
        tv_qianyueshijian = findViewByID_My(R.id.tv_qianyueshijian);
        tv_xuqianshijian = findViewByID_My(R.id.tv_xuqianshijian);
        tv_xuanzeyuanyin = findViewByID_My(R.id.tv_xuanzeyuanyin);
        tv_hetongwenjian = findViewByID_My(R.id.tv_hetongwenjian);

        tv_img = findViewByID_My(R.id.tv_img);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        iv_add = findViewByID_My(R.id.iv_add);

    }

    @Override
    protected void initData() {
        list_hetong.add("????????????");
        list_hetong.add("????????????");
        list_hetong.add("????????????");
        list_hetong.add("????????????");
        list_hetong.add("????????????");
        list_hetong.add("????????????");
        list_hetong.add("????????????");
        list_hetong.add("????????????");

        list_truefalse.add("???");
        list_truefalse.add("???");

        list_huishou.add("??????");
        list_huishou.add("??????");

        /*list_qixian.add("??????");
        list_qixian.add("??????");
        list_qixian.add("??????");*/

        list_jifeidanyuan.add("30??????");
        list_jifeidanyuan.add("1??????");

        list_mianfeishichang.add(new CommonModel.StatusBean("300","5??????"));
        list_mianfeishichang.add(new CommonModel.StatusBean("600","10??????"));
        list_mianfeishichang.add(new CommonModel.StatusBean("1200","20??????"));
        list_mianfeishichang.add(new CommonModel.StatusBean("1800","30??????"));
        list_mianfeishichang.add(new CommonModel.StatusBean("3600","60??????"));

        item_hetong = getIntent().getIntExtra("item_hetong", 0);
        tv_hetongleixing.setText(list_hetong.get(item_hetong));
        titleView.setTitle(list_hetong.get(item_hetong));
        changeUI();

        shopId = getIntent().getStringExtra("shopId");
        tv_xuanzeshanghu.setText(getIntent().getStringExtra("shopName"));
    }


    @Override
    protected void updateView() {

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_hetongleixing:
                //??????????????????
                dialogList_hetong();
                break;
            case R.id.tv_shifoudujia:
                //????????????
                dialogList_TrueFalse();
                break;
            case R.id.tv_xuanzemendian:
                //????????????
                storetype = 0;
                Intent intent1 = new Intent(AddContractActivity.this, MyStoreListActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("requestCode", Constant.SELECT_STORE);
                bundle1.putString("status", "");
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, Constant.SELECT_STORE, bundle1);
                break;
            case R.id.tv_xuanzeshanghu:
                //????????????
                Intent intent2 = new Intent(AddContractActivity.this, MyShopListActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_SHOP);
                switch (item_hetong){
                    case 0://????????????
                        bundle2.putString("status", "2");
                        break;
                    default:
                        bundle2.putString("status", "");
                        break;
                }

                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_SHOP, bundle2);
                break;
            case R.id.tv_zhuanchumendian:
                //????????????
                storetype = 1;
                Intent intent3 = new Intent(AddContractActivity.this, MyStoreListActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("requestCode", Constant.SELECT_STORE);
                bundle3.putString("status", "");
                intent3.putExtras(bundle3);
                startActivityForResult(intent3, Constant.SELECT_STORE, bundle3);
                break;
            case R.id.tv_zhuanrumendian:
                //????????????
                storetype = 2;
                Intent intent4 = new Intent(AddContractActivity.this, MyStoreListActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putInt("requestCode", Constant.SELECT_STORE);
                bundle4.putString("status", "");
                intent4.putExtras(bundle4);
                startActivityForResult(intent4, Constant.SELECT_STORE, bundle4);
                break;
            case R.id.tv_qianyueshijian:
                //????????????
                CommonUtil.selectDate2YMD(AddContractActivity.this,
                        "?????????????????????", tv_qianyueshijian, tv_qianyueshijian.getText().toString().trim());
                break;
            case R.id.tv_xuqianshijian:
                //????????????
                CommonUtil.selectDate2YMD(AddContractActivity.this,
                        "?????????????????????", tv_xuqianshijian, tv_xuqianshijian.getText().toString().trim());
                break;
            case R.id.tv_qianyueqixian:
                //????????????
                dialogList_qixian(tv_qianyueqixian);
                break;
            case R.id.tv_xuqiannianxian:
                //????????????
                dialogList_qixian(tv_xuqiannianxian);
                break;
            case R.id.tv_huishoufangshi:
                //????????????
                dialogList_huishou();
                break;
            case R.id.tv_jianshaoyuanyin:
                //????????????
                dialogList_jianshaoyuanyin(tv_jianshaoyuanyin);
                break;
            case R.id.tv_tuihuicangku:
                //????????????
                dialogList_cangku();
                break;
            case R.id.tv_xuanzeyuanyin:
                //????????????
                dialogList_quxiaoyuanyin(tv_xuanzeyuanyin);
                break;
            case R.id.tv_mianfeishichang:
                //????????????
                dialogList_mianfeishichang(tv_mianfeishichang);
                break;
            case R.id.tv_jifeidanyuan:
                //????????????
                dialogList_jifeidanyuan();
                break;
            case R.id.tv_shanghuhangye:
                //????????????
                dialogList_hangye("0");
                break;
            case R.id.tv_suozaichengshi:
                //????????????
                dialogList_chengshi("0");
                break;
            case R.id.tv_hetongwenjian:
                //??????????????????
                FileUtil.selectPDFFile(AddContractActivity.this, list_hetong.get(item_hetong));
                break;
            case R.id.iv_add:
                //????????????
                MyChooseImages.showPhotoDialog(AddContractActivity.this);
                break;
            case R.id.tv_confirm:
                //??????
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, File> fileMap = new HashMap<>();
                    fileMap.put("file", pdffile);
                    params.clear();
                    switch (item_hetong) {
                        case 0:
                            //????????????
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
                                    file = response;//????????????
                                    fileMap.clear();
                                    fileMap.put("file", imgfile);
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
                                            licenseNoImage = response;//????????????

                                            params.put("merchantId", shopId);
                                            params.put("contractType", contractType);
                                            params.put("file", file);
                                            params.put("sole", sole);
                                            params.put("renewalPeriod", renewalPeriod);
                                            params.put("signTime", signTime);
                                            params.put("licenseNo", licenseNo);
                                            params.put("certificateUrl", licenseNoImage);
                                            requestUpData(URLs.AddContract_qianyue, params);
                                        }
                                    });
                                }
                            });
                            break;
                        case 1:
                            //????????????
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
                                    file = response;//????????????

                                    params.put("storeId", storeId);
                                    params.put("contractType", contractType);
                                    params.put("file", file);
                                    params.put("deviceNum", deviceNum);
                                    requestUpData(URLs.AddContract_xinzeng, params);
                                }
                            });

                            break;
                        case 2:
                            //????????????
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
                                    file = response;//????????????

                                    params.put("merchantId", shopId);
                                    params.put("storeId", storeId);
                                    params.put("deviceNum", deviceNum);
                                    params.put("contractType", contractType);
                                    params.put("file", file);
                                    params.put("reasonId", reasonId);
                                    params.put("warehouseId", warehouseId);//??????id
                                    requestUpData(URLs.AddContract_huishou, params);

                                }
                            });
                            break;
                        case 3:
                            //????????????
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
                                    file = response;//????????????
                                    params.put("storeId", outStoreId);
                                    params.put("outStoreId", outStoreId);
                                    params.put("inStoreId", inStoreId);
                                    params.put("contractType", contractType);
                                    params.put("file", file);
                                    params.put("deviceNum", deviceNum);
                                    requestUpData(URLs.AddContract_huanbang, params);
                                }
                            });
                            break;
                        case 4:
                            //????????????
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
                                    file = response;//????????????
                                    fileMap.clear();
                                    fileMap.put("file", imgfile);
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
                                            licenseNoImage = response;//????????????
                                            logoUrl = response;

                                            params.put("merchantId", shopId);
                                            params.put("contractType", contractType);
                                            params.put("name",name);
                                            params.put("companyName",companyName);
                                            params.put("contactName",contactName);
                                            params.put("contactPhone",contactPhone);
                                            params.put("provinceId",provinceId);
                                            params.put("cityId",cityId);
                                            params.put("areaId",areaId);
                                            params.put("logoUrl",logoUrl);
                                            params.put("address",address);
                                            params.put("industryId",industryId);
                                            params.put("file", file);
                                            params.put("licenseNo", licenseNo);
                                            params.put("certificateUrl", licenseNoImage);
                                            requestUpData(URLs.AddContract_xiugai, params);
                                        }
                                    });
                                }
                            });
                            break;
                        case 5:
                            //????????????
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
                                    file = response;//????????????

                                    params.put("merchantId", shopId);
                                    params.put("sole", sole);
                                    params.put("contractType", contractType);
                                    params.put("file", file);
                                    params.put("renewalPeriod", renewalPeriod);
                                    params.put("renewalTime", renewalTime);
                                    requestUpData(URLs.AddContract_xuqian, params);
                                }
                            });
                            break;
                        case 6:
                            //????????????
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
                                    file = response;//????????????

                                    params.put("merchantId", shopId);
                                    params.put("reasonId", reasonId);
                                    params.put("contractType", contractType);
                                    params.put("file", file);
                                    requestUpData(URLs.AddContract_quxiao, params);
                                }
                            });
                            break;
                        case 7:
                            //????????????
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
                                    file = response;//????????????
                                    params.put("storeId", storeId);
                                    params.put("contractType", contractType);
                                    params.put("file", file);
                                    params.put("storeUnit", storeUnit);
                                    params.put("storeFreeTime",storeFreeTime);
                                    params.put("storeUnitPrice", storeUnitPrice);
                                    params.put("storeCapping", storeCapping);
                                    params.put("reason", reason);
                                    requestUpData(URLs.AddContract_tiaojia, params);
                                }
                            });
                            break;
                    }
                }

                break;
        }
    }

    private boolean match() {
        switch (item_hetong) {
            case 0:
                //????????????
                contractType = "merchant_sign";
                if (TextUtils.isEmpty(shopId)) {
                    myToast("???????????????");
                    return false;
                }
                licenseNo = tv_yinyezhizhaohao.getText().toString().trim();
                if (TextUtils.isEmpty(licenseNo)) {
                    myToast("????????????????????????");
                    return false;
                }
                if (TextUtils.isEmpty(renewalPeriod)) {
                    myToast("?????????????????????");
                    return false;
                }
                sole = itme_truefalse + "";
                if (TextUtils.isEmpty(sole)) {
                    myToast("?????????????????????");
                    return false;
                }
                signTime = tv_qianyueshijian.getText().toString().trim();
                if (TextUtils.isEmpty(signTime)) {
                    myToast("?????????????????????");
                    return false;
                } else {
                    signTime = TimeUtils.string2Millis(signTime, "yyyy-MM-dd")+ "";
//                    signTime = TimeUtils.string2Date(signTime, "yyyy-MM-dd") + "";
                }
                if (pdffile == null) {
                    myToast("?????????????????????");
                    return false;
                }
                if (imgfile == null) {
                    myToast("???????????????");
                    return false;
                }
                break;
            case 1:
                //????????????
                contractType = "device_add";
                if (TextUtils.isEmpty(storeId)) {
                    myToast("???????????????");
                    return false;
                }
                deviceNum = tv_xinzengshuliang.getText().toString().trim();
                if (TextUtils.isEmpty(deviceNum)) {
                    myToast("?????????????????????");
                    return false;
                }
                if (pdffile == null) {
                    myToast("?????????????????????");
                    return false;
                }
                break;
            case 2:
                //????????????
                contractType = "device_recover";
                if (TextUtils.isEmpty(shopId)) {
                    myToast("???????????????");
                    return false;
                }
                if (TextUtils.isEmpty(storeId)) {
                    myToast("???????????????");
                    return false;
                }
                deviceNum = tv_shebeishuliang.getText().toString().trim();
                if (TextUtils.isEmpty(deviceNum)) {
                    myToast("?????????????????????");
                    return false;
                }
                if (TextUtils.isEmpty(reasonId)) {
                    myToast("?????????????????????");
                    return false;
                }
                /*if (TextUtils.isEmpty(warehouseId)) {
                    myToast("?????????????????????");
                    return false;
                }*/
                if (pdffile == null) {
                    myToast("?????????????????????");
                    return false;
                }
                break;
            case 3:
                //????????????
                contractType = "device_exchange";
                if (outStoreId.equals(inStoreId)) {
                    myToast("???????????????????????????????????????");
                    return false;
                }
                deviceNum = tv_zhuanchushebei.getText().toString().trim();
                if (TextUtils.isEmpty(deviceNum)) {
                    myToast("?????????????????????");
                    return false;
                }
                if (pdffile == null) {
                    myToast("?????????????????????");
                    return false;
                }
                break;
            case 4:
                //????????????
                contractType = "merchant_update";
                if (TextUtils.isEmpty(shopId)) {
                    myToast("???????????????");
                    return false;
                }
                name = tv_shanghumingcheng.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    myToast("?????????????????????");
                    return false;
                }
                account = tv_shanghuzhanghao.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    myToast("?????????????????????");
                    return false;
                }
                contactName = tv_shanghulianxiren.getText().toString().trim();
                if (TextUtils.isEmpty(contactName)) {
                    myToast("????????????????????????");
                    return false;
                }
                contactPhone = tv_lianxirendianhua.getText().toString().trim();
                if (TextUtils.isEmpty(contactPhone)) {
                    myToast("??????????????????????????????");
                    return false;
                }
                companyName = tv_gongsimingcheng.getText().toString().trim();
                if (TextUtils.isEmpty(companyName)) {
                    myToast("?????????????????????");
                    return false;
                }
                licenseNo = tv_yinyezhizhaohao.getText().toString().trim();
                if (TextUtils.isEmpty(licenseNo)) {
                    myToast("????????????????????????");
                    return false;
                }
                if (TextUtils.isEmpty(industryId)) {
                    myToast("?????????????????????");
                    return false;
                }
                if (TextUtils.isEmpty(areaId)) {
                    myToast("?????????????????????");
                    return false;
                }
                address = tv_xiangxidizhi.getText().toString().trim();
                if (TextUtils.isEmpty(address)) {
                    myToast("?????????????????????");
                    return false;
                }
                if (pdffile == null) {
                    myToast("?????????????????????");
                    return false;
                }
                if (imgfile == null) {
                    myToast("???????????????");
                    return false;
                }
                break;
            case 5:
                //????????????
                contractType = "merchant_extend";
                if (TextUtils.isEmpty(shopId)) {
                    myToast("???????????????");
                    return false;
                }
                if (TextUtils.isEmpty(renewalPeriod)) {
                    myToast("?????????????????????");
                    return false;
                }
                sole = itme_truefalse + "";
                if (TextUtils.isEmpty(sole)) {
                    myToast("?????????????????????");
                    return false;
                }
                renewalTime = tv_xuqianshijian.getText().toString().trim();
                if (TextUtils.isEmpty(renewalTime)) {
                    myToast("?????????????????????");
                    return false;
                } else {
                    renewalTime = TimeUtils.string2Millis(renewalTime, "yyyy-MM-dd") + "";
//                    renewalTime = TimeUtils.string2Date(TimeUtils.string2Millis(renewalTime, "yyyy-MM-dd") + "") + "";
                }
                if (pdffile == null) {
                    myToast("?????????????????????");
                    return false;
                }
                break;
            case 6:
                //????????????
                contractType = "merchant_cancel";
                if (TextUtils.isEmpty(shopId)) {
                    myToast("???????????????");
                    return false;
                }
                if (TextUtils.isEmpty(reasonId)) {
                    myToast("???????????????");
                    return false;
                }
                if (pdffile == null) {
                    myToast("?????????????????????");
                    return false;
                }
                break;
            case 7:
                //????????????
                contractType = "change_price";
                if (TextUtils.isEmpty(storeId)) {
                    myToast("???????????????");
                    return false;
                }
                if (TextUtils.isEmpty(storeUnit)) {
                    myToast("?????????????????????");
                    return false;
                }
                if (TextUtils.isEmpty(storeFreeTime)) {
                    myToast("?????????????????????");
                    return false;
                }
                storeUnitPrice = tv_mendianjiajia.getText().toString().trim();
                if (TextUtils.isEmpty(storeUnitPrice)) {
                    myToast("?????????????????????");
                    return false;
                }
                storeCapping = tv_mendianfengding.getText().toString().trim();
                if (TextUtils.isEmpty(storeCapping)) {
                    myToast("?????????????????????");
                    return false;
                }
                reason = tv_tiaojialiyou.getText().toString().trim();
                if (TextUtils.isEmpty(reason)) {
                    myToast("?????????????????????");
                    return false;
                }
                if (pdffile == null) {
                    myToast("?????????????????????");
                    return false;
                }
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String imgpath = null;
            Uri uri = null;
            switch (requestCode) {
                case Constant.SELECT_STORE:
                    //????????????
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        switch (storetype) {
                            case 0:
                                //????????????
                                storeId = bundle.getString("storeId");
                                tv_xuanzemendian.setText(bundle.getString("storeName"));
                                if (item_hetong == 7) {
                                    //????????????-?????????????????????
                                    showProgress(true, getString(R.string.app_loading2));
                                    params.clear();
                                    params.put("storeId", storeId);
                                    requestStore(params);
                                }
                                break;
                            case 1:
                                //????????????
                                outStoreId = bundle.getString("storeId");
                                tv_zhuanchumendian.setText(bundle.getString("storeName"));
                                break;
                            case 2:
                                //????????????
                                inStoreId = bundle.getString("storeId");
                                tv_zhuanrumendian.setText(bundle.getString("storeName"));
                                break;
                        }

                    }
                    break;
                case Constant.SELECT_SHOP:
                    //????????????
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        shopId = bundle.getString("shopId");
                        tv_xuanzeshanghu.setText(bundle.getString("shopName"));
                        if (item_hetong == 4) {
                            //????????????-?????????????????????
                            showProgress(true, getString(R.string.app_loading2));
                            params.clear();
//                            params.put("shopId", shopId);
                            requestShop(params,shopId);
                        }
                    }
                    break;
                case SELECT_PDF_FILE:
                    //??????PDF??????
                    uri = data.getData();
                    String pdfpath = FileUtil.getPath(this, uri);
                    MyLogger.i(">>>>>>>>>????????????????????????" + pdfpath + ">>>>>????????????" + FileUtils.getFileExtension(pdfpath));
                    if (pdfpath != null) {
                        tv_hetongwenjian.setText(FileUtils.getFileName(pdfpath));
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
//                imgfile = new File(uri.getPath());
                //??????
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //???????????????????????????
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }

                imgfile = FileUtil.bytesToImageFile(AddContractActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));

                Glide.with(AddContractActivity.this)
                        .load(imgfile)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AddContractActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//???????????????
                        .error(R.mipmap.zanwutupian)//????????????
                        .into(iv_add);//????????????
            }
        }
    }

    private void requestUpData(String url, Map<String, String> params) {
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
                CommonUtil.gotoActivity(AddContractActivity.this, MyContractActivity.class, true);
            }
        });
    }

    /**
     * ??????UI??????
     */
    private void changeUI() {
        params.clear();
        rl_hetongleixing.setVisibility(View.VISIBLE);
        rl_xuanzeshanghu.setVisibility(View.GONE);
        rl_xuanzemendian.setVisibility(View.GONE);
        rl_shanghumingcheng.setVisibility(View.GONE);
        rl_shanghuzhanghao.setVisibility(View.GONE);
        rl_shanghulianxiren.setVisibility(View.GONE);
        rl_lianxirendianhua.setVisibility(View.GONE);
        rl_gongsimingcheng.setVisibility(View.GONE);
        rl_yinyezhizhaohao.setVisibility(View.GONE);
        rl_shanghuhangye.setVisibility(View.GONE);
        rl_suozaichengshi.setVisibility(View.GONE);
        rl_xiangxidizhi.setVisibility(View.GONE);
        rl_shougexiaoshi.setVisibility(View.GONE);
        rl_jichujijia.setVisibility(View.GONE);
        rl_meirifengding.setVisibility(View.GONE);
        rl_mianfeishichang.setVisibility(View.GONE);
        rl_jifeidanyuan.setVisibility(View.GONE);
        rl_mendianjiajia.setVisibility(View.GONE);
        rl_mendianfengding.setVisibility(View.GONE);
        rl_tiaojialiyou.setVisibility(View.GONE);
        rl_zhuanchumendian.setVisibility(View.GONE);
        rl_zhuanrumendian.setVisibility(View.GONE);
        rl_zhuanchushebei.setVisibility(View.GONE);
        rl_shebeishuliang.setVisibility(View.GONE);
        rl_jianshaoyuanyin.setVisibility(View.GONE);
        rl_tuihuicangku.setVisibility(View.GONE);
        rl_huishoufangshi.setVisibility(View.GONE);
        rl_shoujianren.setVisibility(View.GONE);
        rl_shoujiandianhua.setVisibility(View.GONE);
        rl_xinzengshuliang.setVisibility(View.GONE);
        rl_qianyueqixian.setVisibility(View.GONE);
        rl_xuqiannianxian.setVisibility(View.GONE);
        rl_shoujiandizhi.setVisibility(View.GONE);
        rl_shifoudujia.setVisibility(View.GONE);
        rl_qianyueshijian.setVisibility(View.GONE);
        rl_xuqianshijian.setVisibility(View.GONE);
        rl_xuanzeyuanyin.setVisibility(View.GONE);
        rl_hetongwenjian.setVisibility(View.GONE);
        tv_img.setVisibility(View.GONE);
        iv_add.setVisibility(View.GONE);
        switch (item_hetong) {
            case 0:
                //????????????
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_qianyueqixian.setVisibility(View.VISIBLE);
                rl_shifoudujia.setVisibility(View.VISIBLE);
                rl_qianyueshijian.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                rl_yinyezhizhaohao.setVisibility(View.VISIBLE);
                tv_img.setVisibility(View.VISIBLE);
                tv_img.setText("????????????");
                iv_add.setVisibility(View.VISIBLE);

//                params.put("type", "renewalPeriod");
                request(URLs.AddContract_qianyue, params);//??????

                break;
            case 1:
                //????????????
                rl_xuanzemendian.setVisibility(View.VISIBLE);
                rl_xinzengshuliang.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                break;
            case 2:
                //????????????
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_xuanzemendian.setVisibility(View.VISIBLE);
                rl_shebeishuliang.setVisibility(View.VISIBLE);
                rl_jianshaoyuanyin.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);

//                requestChangKu(params);
//                params.put("type", "merchantRecoverReason");
                request(URLs.Common + "CONTRACT_DEVICE_RECOVER_REASON", params);//????????????

                break;
            case 3:
                //????????????
                rl_zhuanchumendian.setVisibility(View.VISIBLE);
                rl_zhuanrumendian.setVisibility(View.VISIBLE);
                rl_zhuanchushebei.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);

                break;
            case 4:
                //????????????
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_shanghumingcheng.setVisibility(View.VISIBLE);
                rl_shanghuzhanghao.setVisibility(View.VISIBLE);
                rl_shanghulianxiren.setVisibility(View.VISIBLE);
                rl_lianxirendianhua.setVisibility(View.VISIBLE);
                rl_gongsimingcheng.setVisibility(View.VISIBLE);
                rl_yinyezhizhaohao.setVisibility(View.VISIBLE);
                rl_shanghuhangye.setVisibility(View.VISIBLE);
                rl_suozaichengshi.setVisibility(View.VISIBLE);
                rl_xiangxidizhi.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                tv_img.setVisibility(View.VISIBLE);
                tv_img.setText("????????????");
                iv_add.setVisibility(View.VISIBLE);

//                params.put("type", "renewalPeriod");
//                request(URLs.Common + "MERCHANT_CANCEL_REASON", params);//

                break;
            case 5:
                //????????????
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_xuqiannianxian.setVisibility(View.VISIBLE);
                rl_shifoudujia.setVisibility(View.VISIBLE);
                rl_xuqianshijian.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                request(URLs.AddContract_qianyue, params);//??????

                break;
            case 6:
                //????????????
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_xuanzeyuanyin.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
//                params.put("type", "merchantCancelReason");
                request(URLs.Common + "MERCHANT_CANCEL_REASON", params);//????????????
                break;
            case 7:
                //????????????
                rl_xuanzemendian.setVisibility(View.VISIBLE);
                rl_shougexiaoshi.setVisibility(View.VISIBLE);
                rl_jichujijia.setVisibility(View.VISIBLE);
                rl_meirifengding.setVisibility(View.VISIBLE);
                rl_mianfeishichang.setVisibility(View.VISIBLE);
                rl_jifeidanyuan.setVisibility(View.VISIBLE);
                rl_mendianjiajia.setVisibility(View.VISIBLE);
                rl_mendianfengding.setVisibility(View.VISIBLE);
                rl_tiaojialiyou.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
//                params.put("type", "storeUnit");
//                request(URLs.Common + "MERCHANT_CANCEL_REASON", params);//????????????
                break;
        }
    }

    /**
     * ??????????????????
     *
     * @param params
     */
    private void request(String url, Map<String, String> params) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<List<CommonModel.ListBean>>() {
            @Override
            public List<CommonModel.ListBean> onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(List<CommonModel.ListBean> response) {
                hideProgress();
                //??????
                item_qixian = -1;
                item_jianshaoyuanyin = -1;
                renewalPeriod = "";
                list_qixian = response;
                list_jianshaoyuanyin = response;
                list_quxiaoyuanyin = response;
//                list_jifeidanyuan = response.get;
            }
        });

    }

    /**
     * ??????????????????
     *
     * @param params
     */
    private void requestChangKu(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.Warehouse, params, headerMap, new CallBackUtil<WarehouseModel>() {
            @Override
            public WarehouseModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(WarehouseModel response) {
                hideProgress();
                list_cangku = response.getList();
            }
        });
    }

    /**
     * ??????????????????
     *
     * @param params
     */
    private void requestStore(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.StoreInfo, params, headerMap, new CallBackUtil<StoreInfoModel>() {
            @Override
            public StoreInfoModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(StoreInfoModel response) {
                hideProgress();
                tv_shougexiaoshi.setText(response.getSysStartPrice()+"???");
                tv_jichujijia.setText(response.getSysOverTimeUnit());
                tv_meirifengding.setText(response.getSysMaxPrice()+"???");
                tv_mianfeishichang.setText(Integer.valueOf(response.getSysFreeTime())/60+"??????");
                storeFreeTime = response.getSysFreeTime();
            }
        });
    }
    /**
     * ??????????????????
     *
     * @param params
     */
    private void requestShop(HashMap<String, String> params,String id) {
        OkhttpUtil.okHttpGet(URLs.ShopDetail +id, params, headerMap, new CallBackUtil<ShopDetailModel>() {
            @Override
            public ShopDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(ShopDetailModel response) {
                hideProgress();
                tv_shanghumingcheng.setText(response.getBase().getName());
                tv_shanghuzhanghao.setText(response.getBase().getAccount());
                tv_shanghulianxiren.setText(response.getBase().getContactName());
                tv_lianxirendianhua.setText(response.getBase().getContactPhone());
                tv_gongsimingcheng.setText(response.getBase().getCompanyName());
                tv_yinyezhizhaohao.setText(response.getBase().getLicenseNo());
                tv_shanghuhangye.setText(response.getBase().getInsduty());
                tv_suozaichengshi.setText(response.getBase().getCity());
                tv_xiangxidizhi.setText(response.getBase().getAddress());

                provinceId = response.getBase().getProvinceId();
                cityId = response.getBase().getCityId();
                areaId = response.getBase().getAreaId();
                industryId = response.getBase().getIndustryId();
            }
        });
    }

    /**
     * ????????????
     */
    private void dialogList_hetong() {
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
                (AddContractActivity.this, R.layout.item_help, list_hetong) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (item_hetong == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_hetong = position;
                tv_hetongleixing.setText(list_hetong.get(position));
                titleView.setTitle(list_hetong.get(position));
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
     * ????????????
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
                (AddContractActivity.this, R.layout.item_help, list_truefalse) {
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
                tv_shifoudujia.setText(list_truefalse.get(position));

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
     * ??????????????????
     */
    private void dialogList_huishou() {
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
                (AddContractActivity.this, R.layout.item_help, list_huishou) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (item_huishou == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_huishou = position;
                tv_huishoufangshi.setText(list_huishou.get(position));
                recyleType = position + 1 + "";

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
     * ??????????????????
     */
    private void dialogList_qixian(TextView textView) {
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
        CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                (AddContractActivity.this, R.layout.item_help, list_qixian) {
            @Override
            protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getValue());
                if (item_qixian == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_qixian = position;
                textView.setText(list_qixian.get(position).getValue());
                renewalPeriod = list_qixian.get(position).getKey();
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
     * ??????????????????
     */
    private void dialogList_jianshaoyuanyin(TextView textView) {
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
        CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                (AddContractActivity.this, R.layout.item_help, list_jianshaoyuanyin) {
            @Override
            protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getName());
                if (item_jianshaoyuanyin == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_jianshaoyuanyin = position;
                textView.setText(list_jianshaoyuanyin.get(position).getName());
                reasonId = list_jianshaoyuanyin.get(position).getId();
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
     * ??????????????????
     */
    private void dialogList_quxiaoyuanyin(TextView textView) {
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
        CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                (AddContractActivity.this, R.layout.item_help, list_quxiaoyuanyin) {
            @Override
            protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getName());
                if (item_quxiaoyuanyin == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_quxiaoyuanyin = position;
                textView.setText(list_quxiaoyuanyin.get(position).getName());
                reasonId = list_quxiaoyuanyin.get(position).getId();
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
     * ??????????????????
     */
    private void dialogList_mianfeishichang(TextView textView) {
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
        CommonAdapter<CommonModel.StatusBean> adapter = new CommonAdapter<CommonModel.StatusBean>
                (AddContractActivity.this, R.layout.item_help, list_mianfeishichang) {
            @Override
            protected void convert(ViewHolder holder, CommonModel.StatusBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getVal());
                if (item_mianfeishichang == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_mianfeishichang = position;
                textView.setText(list_mianfeishichang.get(position).getVal());
                storeFreeTime = list_mianfeishichang.get(position).getKey();
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
     * ????????????
     */
    private void dialogList_cangku() {
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
        CommonAdapter<WarehouseModel.ListBean> adapter = new CommonAdapter<WarehouseModel.ListBean>
                (AddContractActivity.this, R.layout.item_help, list_cangku) {
            @Override
            protected void convert(ViewHolder holder, WarehouseModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getName());
                if (item_cangku == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_cangku = position;
                tv_tuihuicangku.setText(list_cangku.get(position).getName());
                tv_shoujianren.setText(list_cangku.get(position).getContactName());
                tv_shoujiandianhua.setText(list_cangku.get(position).getContactPhone());
                tv_shoujiandizhi.setText(list_cangku.get(position).getAddress());

                warehouseId = list_cangku.get(position).getId();
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
     * ????????????
     */
    private void dialogList_jifeidanyuan() {
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
                (AddContractActivity.this, R.layout.item_help, list_jifeidanyuan) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (item_jifeidanyuan == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_jifeidanyuan = position;
                tv_jifeidanyuan.setText(list_jifeidanyuan.get(position));
                storeUnit = position+"";
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
     * ????????????
     */
    List<IndustryModel.ListBean> list_hangye = new ArrayList<>();
    int maxIdex_hangye = 2;
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
                rv_list.setLayoutManager(new LinearLayoutManager(AddContractActivity.this));
                CommonAdapter<IndustryModel.ListBean> adapter = new CommonAdapter<IndustryModel.ListBean>
                        (AddContractActivity.this, R.layout.item_help, list_hangye) {
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
                            //?????????????????????
                            if (!string_hangye.equals("")) {
                                string_hangye = string_hangye.substring(0, string_hangye.length() - 1);
                            }
                            tv_shanghuhangye.setText(string_hangye);
                            industryId = list_hangye.get(position).getId();
                            //?????????
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
     * ????????????
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
                                CommonUtil.dip2px(AddContractActivity.this, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(false)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(AddContractActivity.this));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (AddContractActivity.this, R.layout.item_help, list_chengshi) {
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
                                //???
                                //?????????????????????
                                if (!string_chengshi.equals("")) {
                                    string_chengshi = string_chengshi.substring(0, string_chengshi.length() - 1);
                                }
                                tv_suozaichengshi.setText(string_chengshi);
                                areaId = list_chengshi.get(position).getId();
                                //?????????
                                string_chengshi = "";
                                maxIdex_chengshi = 1;

                                dialog.dismiss();
                                break;
                            case 2:
                                //???
                                cityId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 3;
                                dialogList_chengshi(list_chengshi.get(position).getId());
                                break;
                            case 1:
                                //???
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

}
