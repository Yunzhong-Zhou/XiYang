package com.xiyang.xiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.adapter.OnlineServiceAdapter;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.OnlineServiceModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.FileUtil;
import com.xiyang.xiyang.utils.MyChooseImages;
import com.xiyang.xiyang.utils.MyLogger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


/**
 * Created by fafukeji01 on 2017/5/11.
 * 在线客服
 */

public class OnlineServiceActivity extends BaseActivity {
    private ListView listView;
    List<OnlineServiceModel.RecordsBean> list;
    OnlineServiceAdapter adapter;
    EditText editText;
    ImageView imageView;
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineservice);
    }

    @Override
    protected void initView() {

        listView = findViewByID_My(R.id.listView);
        editText = findViewByID_My(R.id.editText);
        imageView = findViewByID_My(R.id.imageView);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“发送”键*/
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    MyLogger.i(">>>>>>>输入的内容" + editText.getText().toString().trim());
                    if (!editText.getText().toString().trim().equals("")) {
                        /*隐藏软键盘*/
                        InputMethodManager imm = (InputMethodManager) v
                                .getContext().getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                        }

                        showProgress(true, getString(R.string.app_loading1));
                        params.clear();
                        params.put("type", "2");//1图片2文本
                        params.put("message", editText.getText().toString().trim());
                        RequestAddMessage(params);
                        /*params.put("software_version", CommonUtil.getVersionName(OnlineServiceActivity.this));
                        params.put("facility", getString(R.string.onlineservice_system1) + CommonUtil.getDeviceBrand()
                                + getString(R.string.onlineservice_system2) + CommonUtil.getSystemModel()
                                + getString(R.string.onlineservice_system3) + CommonUtil.getSystemVersion() +
                                getString(R.string.onlineservice_system4) + CommonUtil.getVersionName(OnlineServiceActivity.this));*/
                    } else {
                        myToast(getString(R.string.onlineservice_hint));
                    }
                    return true;
                }
                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyChooseImages.showPhotoDialog(OnlineServiceActivity.this);
            }
        });

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = page + 1;
                params.clear();
                params.put("type", "");
                params.put("keyword", "");
                params.put("page", page + "");
                params.put("pageSize", "10");
                RequestOnlineServiceMore(params);
            }

            @Override
            public void onLoadmore() {
                //加载更多
                page = 0;
                params.clear();
                params.put("type", "");
                params.put("keyword", "");
                params.put("page", page + "");
                params.put("pageSize", "10");
                RequestOnlineService(params);
            }
        });
    }

    @Override
    protected void initData() {
        requestServer();
        //帮助列表
//        RequestHelpList("?token=" + localUserInfo.getToken());
    }

    /*private void RequestHelpList(String string) {
        OkHttpClientManager.getAsyn(OnlineServiceActivity.this, URLs.Help + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
//                onHttpResult();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
//                showContentPage();
//                onHttpResult();
                MyLogger.i(">>>>>>>>>帮助列表" + response);

                JSONObject jObj;
                List<HelpModel> list_h = new ArrayList<HelpModel>();
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list_h = JSON.parseArray(jsonArray.toString(), HelpModel.class);
                    if (list_h.size() == 0) {
//                        showEmptyPage();//空数据
                    } else {
//                        adapter = new MyRechargeAdapter(OnlineServiceActivity.this, list_h);
//                        listView.setAdapter(adapter);
                        dialog = new BaseDialog(OnlineServiceActivity.this);
                        dialog.contentView(R.layout.dialog_help)
                                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT))
                                .animType(BaseDialog.AnimInType.CENTER)
                                .canceledOnTouchOutside(true)
                                .dimAmount(0.8f)
                                .show();
                        ListView listView_h = dialog.findViewById(R.id.listView);
                        HelpAdapter adapter_h = new HelpAdapter(OnlineServiceActivity.this,list_h);
                        listView_h.setAdapter(adapter_h);
                        final List<HelpModel> finalList_h = list_h;
                        listView_h.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                dialog.dismiss();
                                Bundle bundle = new Bundle();
                                bundle.putString("url", finalList_h.get(position).getUrl());
                                CommonUtil.gotoActivityWithData(OnlineServiceActivity.this, WebContentActivity.class, bundle, false);

                            }
                        });
                        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }*/


    //对话列表
    private void RequestOnlineService(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.OnlineService, GsonUtils.toJson(params), headerMap, new CallBackUtil<OnlineServiceModel>() {
            @Override
            public OnlineServiceModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(OnlineServiceModel response) {
                hideProgress();
                showContentPage();
                list = response.getRecords();
                if (list.size() == 0) {
                    showEmptyPage();
                } else {
                    //反转数据
//                    Collections.reverse(list);
                    adapter = new OnlineServiceAdapter(OnlineServiceActivity.this, list);
                    listView.setAdapter(adapter);
                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                            // Select the last row so it will scroll into view...
                            listView.setSelection(adapter.getCount() - 1);
                        }
                    });
                }
            }
        });
    }

    private void RequestOnlineServiceMore(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.OnlineService, params, headerMap, new CallBackUtil<OnlineServiceModel>() {
            @Override
            public OnlineServiceModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                showErrorPage();
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(OnlineServiceModel response) {
//                hideProgress();
                showContentPage();
                List<OnlineServiceModel.RecordsBean> list1 = new ArrayList<>();
                list1 = response.getRecords();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    //反转数据
//                    Collections.reverse(list1);
                    list.addAll(list1);
                    adapter.notifyDataSetChanged();
                        /*listView.post(new Runnable() {
                            @Override
                            public void run() {
                                // Select the last row so it will scroll into view...
                                listView.setSelection(adapter.getCount() - 1);
                            }
                        });*/
                }
            }
        });
    }


    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.onlineservice_title));
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        page = 0;
        params.clear();
        params.put("type", "");
        params.put("keyword", "");
        params.put("page", page + "");
        params.put("pageSize", "10");
        RequestOnlineService(params);
    }

    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            File imgfile = null;
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
                showProgress(true, getString(R.string.app_loading1));
                //压缩
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                //如果是拍照，则旋转
                if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                    bitmap = FileUtil.rotaingImageView(ImageUtils.getRotateDegree(imgpath), bitmap);
                }
                imgfile = FileUtil.bytesToImageFile(OnlineServiceActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));

                //上传头像
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
//                        hideProgress();
                        params.clear();
                        params.put("type", "1");//1图片2文本
                        params.put("message", response);
                        RequestAddMessage(params);
                    }
                });
            }
        }

    }

    private void RequestAddMessage(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.AddMessage, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
                hideProgress();
                editText.setText("");
                requestServer();
            }
        });
    }

}
