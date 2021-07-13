package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
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
 * Created by fafukeji01 on 2017/5/8.
 * 添加员工
 */

public class AddStaffActivity_BD extends BaseActivity {
    ImageView imageView1;
    RelativeLayout rl_xingming, rl_xingbie, rl_zhanghao, rl_lianxidianhua, rl_bumen, rl_leixing, rl_chengshi;
    EditText tv_xingming, tv_xingbie, tv_zhanghao, tv_lianxidianhua, tv_chengshi, tv_leixing, editText1;
    TextView textView1;

    String account = "", name = "", head = "", sex = "1", contactPhone = "", postionIds = "",
            type = "1", storeId = "", department = "", code = "";

    List<String> list_sex = new ArrayList<>();
    int item_sex = 0;
    List<String> list_type = new ArrayList<>();
    int item_type = 0;

    File imgfile = null;

    private TimeCount time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstaff_bd);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initView() {
        imageView1 = findViewByID_My(R.id.imageView1);
        tv_xingming = findViewByID_My(R.id.tv_xingming);
        tv_xingbie = findViewByID_My(R.id.tv_xingbie);
        tv_zhanghao = findViewByID_My(R.id.tv_zhanghao);
        tv_lianxidianhua = findViewByID_My(R.id.tv_lianxidianhua);
        tv_chengshi = findViewByID_My(R.id.tv_chengshi);
        tv_leixing = findViewByID_My(R.id.tv_leixing);

        rl_chengshi = findViewByID_My(R.id.rl_chengshi);
        editText1 = findViewByID_My(R.id.editText1);

        tv_lianxidianhua.setText("" + localUserInfo.getMobile_State_Code() + "" + localUserInfo.getPhonenumber());
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        textView1 = findViewByID_My(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = localUserInfo.getPhonenumber();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(AddStaffActivity_BD.this, getString(R.string.settransactionpassword_h3), Toast.LENGTH_SHORT).show();
                } else {
                    /*String string = "?mobile=" + localUserInfo.getPhonenumber() +
                            "&type=" + "5" +
                            "&mobile_state_code=" + localUserInfo.getMobile_State_Code();*/
                    AddStaffActivity_BD.this.showProgress(true, getString(R.string.app_sendcode_hint1));
                    textView1.setClickable(false);

                    params.clear();
//                    params.put("mobile", localUserInfo.getPhonenumber());
//                    params.put("type", "31");
//                    params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                    RequestCode(params);//获取验证码
                }
            }
        });
    }

    @Override
    protected void initData() {
        storeId = getIntent().getStringExtra("storeId");
        list_sex.add("男");
        list_sex.add("女");

        list_type.add("直营");
//        list_type.add("自营");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                //头像
                MyChooseImages.showPhotoDialog(AddStaffActivity_BD.this);
                break;
            case R.id.tv_xingbie:
                //性别
                dialogList_Sex();
                break;
            case R.id.tv_leixing:
                //类型
                dialogList_Type();
                break;
            case R.id.tv_chengshi:
                //选择城市
                Intent intent2 = new Intent(AddStaffActivity_BD.this, SelectMyCityActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_MYCITY);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_MYCITY, bundle2);
                break;

            case R.id.tv_confirm:
                //确认添加
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
                            head = response;

                            params.clear();
                            params.put("userName", name);
                            params.put("storeId", storeId);
                            params.put("phone", account);
                            params.put("deptId", department);
                            params.put("verificationCode", code);
                            params.put("userImg", head);
                            params.put("flag", "false");
                            requestUpData(params);
                        }
                    });
                }
                break;

        }
    }

    private void RequestCode(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Code_Staff, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                textView1.setClickable(true);
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                time.start();
                myToast(getString(R.string.app_sendcode_hint));
            }
        });

    }

    private boolean match() {
        name = tv_xingming.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            myToast("请输入姓名");
            return false;
        }
        account = tv_lianxidianhua.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            myToast("请输入联系电话");
            return false;
        }
        code = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.settransactionpassword_h5));
            return false;
        }
        department = tv_leixing.getText().toString().trim();
        if (TextUtils.isEmpty(department)) {
            myToast("请输入所在部门");
            return false;
        }
        if (imgfile == null) {
            myToast("请选择上传员工头像");
            return false;
        }

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加新员工");

    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView1.setText(getString(R.string.app_reacquirecode));
            textView1.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView1.setClickable(false);
            textView1.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
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
                case Constant.SELECT_MYCITY:
                    //选择城市
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        postionIds = bundle.getString("postionIds");
                        tv_chengshi.setText(bundle.getString("postionCitys"));
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

                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //如果是拍照，则旋转
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }

                //压缩
                imgfile = FileUtil.bytesToImageFile(AddStaffActivity_BD.this,
                        ImageUtils.compressByQuality(bitmap, 50));
                Glide.with(AddStaffActivity_BD.this)
                        .load(imgfile)
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(AddStaffActivity_BD.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片
            }
        }

    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.AddStaff_BD, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
                finish();
            }
        });
    }

    /**
     * 选择性别
     */
    private void dialogList_Sex() {
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
                (AddStaffActivity_BD.this, R.layout.item_help, list_sex) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (item_sex == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_sex = position;
                tv_xingbie.setText(list_sex.get(position));
                sex = position + 1 + "";//1男2女
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
     * 选择类型
     */
    private void dialogList_Type() {
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
                (AddStaffActivity_BD.this, R.layout.item_help, list_type) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (item_type == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                item_type = position;
                tv_leixing.setText(list_type.get(position));
                type = position + 1 + "";//1直营
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
