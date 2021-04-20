package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
 * 拜访
 */
public class AddVisitActivity extends BaseActivity {
    List<String> list_visit = new ArrayList<>();
    int type = 0;// 1-远程拜访，2-上门拜访，3-陌生拜访
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
            feedback = "", isAdver = "", remark = "", images = "";

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

        type = getIntent().getIntExtra("type", 0);
        tv_xuanzefangshi.setText(list_visit.get(type));
        titleView.setTitle(list_visit.get(type));

        changeUI();

        request(params);
    }

    private void request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.AddVisit, params, headerMap, new CallBackUtil<CommonModel>() {
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
                /*i_jieguo = -1;
                status = "";
                list_jieguo = response.getStatus();*/
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
            case R.id.tv_xuanzemendian:
                //选择门店
                Intent intent1 = new Intent(AddVisitActivity.this, MyShopListActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("requestCode", Constant.SELECT_SHOP);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, Constant.SELECT_SHOP, bundle1);
                break;
            case R.id.tv_baifangjilu:
                //拜访记录
                CommonUtil.gotoActivity(AddVisitActivity.this, MyVisitListActivity.class);
                break;





        }
    }


    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            File pdffile = null;
            File imgfile = null;
            String imgpath = null;
            Uri uri = null;
            switch (requestCode) {
                case Constant.SELECT_SHOP:
                    //选择门店
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        storeId = bundle.getString("storeId");
                        tv_xuanzemendian.setText(storeId);
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
                imgfile = FileUtil.bytesToImageFile(AddVisitActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));

                new UpFileToQiNiuUtil(AddVisitActivity.this, imgfile, FileUtils.getFileExtension(imgfile)) {
                    @Override
                    public void complete(boolean isok, String result, String url) {
//                        hideProgress();
                        if (isok) {
                            MyLogger.i(">>>>上传文件路径：" + url);
                            Glide.with(AddVisitActivity.this)
                                    .load(url)
                                    .centerCrop()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(AddVisitActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                            images = url;
                           /* Map<String, String> params = new HashMap<>();
                            params.put("head",url);
                            RequestUpFile(params);*/

                        } else {
                            myToast(result);
                        }
                    }
                };
            }
        }

    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddVisit, params, headerMap, new CallBackUtil<String>() {
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
}
