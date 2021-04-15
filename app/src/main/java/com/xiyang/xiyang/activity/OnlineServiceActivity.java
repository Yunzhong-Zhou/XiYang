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
import com.xiyang.xiyang.utils.UpFileToQiNiuUtil;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static com.xiyang.xiyang.utils.Constant.SELECT_PDF_FILE;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.xiyang.xiyang.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


/**
 * Created by fafukeji01 on 2017/5/11.
 * 在线客服
 */

public class OnlineServiceActivity extends BaseActivity {
    private ListView listView;
    List<OnlineServiceModel.LeaveMessageListBean> list;
    OnlineServiceAdapter adapter;
    EditText editText;
    ImageView imageView;
    int page = 1;

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
                        Map<String, String> params = new HashMap<>();
                        params.put("type","2");//1图片2文本
                        params.put("message",editText.getText().toString().trim());
                        RequestAddMessage(params);
                        /*params.put("software_version", CommonUtil.getVersionName(OnlineServiceActivity.this));
                        params.put("facility", getString(R.string.onlineservice_system1) + CommonUtil.getDeviceBrand()
                                + getString(R.string.onlineservice_system2) + CommonUtil.getSystemModel()
                                + getString(R.string.onlineservice_system3) + CommonUtil.getSystemVersion() +
                                getString(R.string.onlineservice_system4) + CommonUtil.getVersionName(OnlineServiceActivity.this));*/
                        RequestAddMessage(params);//创建留言
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
                params.put("nextId","");
                params.put("page",page+"");
                params.put("count","10");
                RequestOnlineServiceMore(params);
            }

            @Override
            public void onLoadmore() {
                //加载更多
                page = 1;
                params.clear();
                params.put("nextId","");
                params.put("page",page+"");
                params.put("count","10");
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
        OkhttpUtil.okHttpGet(URLs.OnlineService, params, headerMap, new CallBackUtil<OnlineServiceModel>() {
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
                JSONObject jObj;
                List<OnlineServiceModel.LeaveMessageListBean> list1 = new ArrayList<OnlineServiceModel.LeaveMessageListBean>();
                list = new ArrayList<>();
                list1 = response.getLeave_message_list();
                if (list1.size() == 0) {
                    showEmptyPage();
                } else {
                    //反转数据
                    for (int i = list1.size() - 1; i >= 0; i--) {
                        list.add(list1.get(i));
                    }
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
                showErrorPage();
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(OnlineServiceModel response) {
                hideProgress();
                showContentPage();
                JSONObject jObj;
                List<OnlineServiceModel.LeaveMessageListBean> list1_1 = new ArrayList<OnlineServiceModel.LeaveMessageListBean>();
                list1_1 = response.getLeave_message_list();
                if (list1_1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    //反转数据
                    List<OnlineServiceModel.LeaveMessageListBean> list1 = new ArrayList<OnlineServiceModel.LeaveMessageListBean>();
                    for (int i = list1_1.size() - 1; i >= 0; i--) {
                        list1.add(list1_1.get(i));
                    }
                    list1.addAll(list);
                    adapter = new OnlineServiceAdapter(OnlineServiceActivity.this, list1);
                    listView.setAdapter(adapter);
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
        page = 1;
        params.clear();
        params.put("nextId","");
        params.put("page",page+"");
        params.put("count","10");
        RequestOnlineService(params);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

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
                showProgress(true, getString(R.string.app_loading1));
//                imgfile = new File(uri.getPath());
                //压缩
                Bitmap bitmap = BitmapFactory.decodeFile(imgpath);
                imgfile = FileUtil.bytesToImageFile(OnlineServiceActivity.this,
                        ImageUtils.compressByQuality(bitmap, 50));

                new UpFileToQiNiuUtil(OnlineServiceActivity.this, imgfile, FileUtils.getFileExtension(imgfile)) {
                    @Override
                    public void complete(boolean isok, String result, String url) {
//                        hideProgress();
                        if (isok) {
                            MyLogger.i(">>>>上传文件路径：" + url);
                            Map<String, String> params = new HashMap<>();
                            params.put("type","1");//1图片2文本
                            params.put("message",url);
                            RequestAddMessage(params);

                        } else {
                            myToast(result);
                        }
                    }
                };
            }
        }

    }
    private void RequestAddMessage(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddMessage, params, headerMap, new CallBackUtil<String>() {
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
                requestServer();
            }
        });
    }

}
