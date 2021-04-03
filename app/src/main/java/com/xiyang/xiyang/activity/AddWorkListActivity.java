package com.xiyang.xiyang.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.MyChooseImages;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.zelory.compressor.Compressor;
import okhttp3.Call;
import okhttp3.Response;

import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by Mr.Z on 2021/3/28.
 * 创建工单
 */
public class AddWorkListActivity extends BaseActivity {
    List<String> list_work = new ArrayList<>();
    int type = 0;
    RelativeLayout rl_gongdanleixing, rl_xuanzedingdan, rl_shebeiguzhang, rl_xuanzemendian, rl_dingdanwenti,
            rl_guzhangleixing, rl_qitashuoming;
    EditText tv_gongdanleixing, tv_xuanzedingdan, tv_shebeiguzhang, tv_xuanzemendian, tv_dingdanwenti,
            tv_guzhangleixing, tv_qitashuoming;
    ImageView imageView1;


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

    @Override
    protected void updateView() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_gongdanleixing:
                //选择工单类型
                dialog.contentView(R.layout.dialog_list)
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
                break;

            case R.id.tv_baifangjilu:
                //拜访记录
                CommonUtil.gotoActivity(AddWorkListActivity.this,MyVisitListActivity.class);
                break;

        }
    }

    /**
     * 上传文件 list 方式
     *
     * @param params
     * @param fileList
     * @param fileKey
     */
    private void RequestUpFile(Map<String, String> params, List<File> fileList, String fileKey) {
        OkhttpUtil.okHttpUploadListFile(URLs.AddMessage, params, fileList, fileKey, "image", headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(String response) {
//                myToast("头像修改成功");
//                editText.setText("");
//                page = 1;
                /*String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestOnlineService(string);*/
            }
        });
    }

    /**
     * *****************************************选择图片********************************************
     */
    //选择图片及上传
    ArrayList<String> listFileNames;
    ArrayList<File> listFiles;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = null;
            String imagePath = null;
            switch (requestCode) {
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imagePath = uri.getPath();
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    //处理得到的url
                    ContentResolver cr = this.getContentResolver();
                    Cursor cursor = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            try {
                                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            } catch (Exception e) {
                                e.printStackTrace();
                                myToast(getString(R.string.app_error));
                            } finally {
                                if (cursor != null)
                                    cursor.close();
                            }
                        }

                    } else {
                        imagePath = uri.getPath();
                    }
                    break;
            }
            if (uri != null) {
                MyLogger.i(">>>>>>>>>>获取到的图片路径1：" + imagePath);
                //图片过大解决方法
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

//                imageView1.setImageBitmap(bitmap);
//                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);

//                listFileNames = new ArrayList<>();
//                listFileNames.add("head");

                Uri uri1 = Uri.parse("");
                /*uri1 = Uri.fromFile(new File(imagePath));
                File file1 = new File(FileUtil.getPath(this, uri1));*/
                File file1 = new File(imagePath);
                listFiles = new ArrayList<>();
                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
//                    MyLogger.i(">>>>>选择图片结果>>>>>>>>>" + listFileNames.toString() + ">>>>>>" + listFiles.toString());

                    Map<String, File> fileMap = new HashMap<>();
//                    fileMap.put("picture", newFile);
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
//                    RequestUpFile(fileMap, params);
                    RequestUpFile(params, listFiles, "picture");

                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }
            }
        }

    }


    private void changeUI() {
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
                break;
            case 1:
                //订单工单
                rl_xuanzedingdan.setVisibility(View.VISIBLE);
                rl_dingdanwenti.setVisibility(View.VISIBLE);
                break;
            case 2:
                //其他工单

                break;
        }
    }
}
