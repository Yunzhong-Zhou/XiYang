package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.bumptech.glide.Glide;
import com.cy.dialog.BaseDialog;
import com.lihang.ShadowLayout;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.RoomNoManagementModel;
import com.xiyang.xiyang.model.StoreDetailModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/4/2.
 */
public class AddRoomNoManagementActivity extends BaseActivity {
    StoreDetailModel model;
    int type = 1, item1 = -1, item2 = -1, item3 = -1, item4 = -1;
    String parentId1 = "0", parentId2 = "", parentId3 = "", parentId4 = "",
            name1 = "", name2 = "", name3 = "", name4 = "";

    String title = "", parentId = "", name = "";

    //门店信息
    ImageView imageView1, imageView2;
    TextView tv_name, tv_shop, tv_addr;

    //信息筛选
    ShadowLayout shadowLayout;
    RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3;
    EditText textView1, textView2, textView3;
    TextView tv_title, tv_add;
    EditText editText1;
    RecyclerView recyclerView;
    List<String> list = new ArrayList<>();
    CommonAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroomnomanagement);
    }

    @Override
    protected void initView() {
        //门店信息
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_shop = findViewByID_My(R.id.tv_shop);
        tv_addr = findViewByID_My(R.id.tv_addr);

        //信息筛选、添加
        shadowLayout = findViewByID_My(R.id.shadowLayout);
        relativeLayout1 = findViewByID_My(R.id.relativeLayout1);
        relativeLayout2 = findViewByID_My(R.id.relativeLayout2);
        relativeLayout3 = findViewByID_My(R.id.relativeLayout3);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        tv_title = findViewByID_My(R.id.tv_title);
        tv_add = findViewByID_My(R.id.tv_add);
        editText1 = findViewByID_My(R.id.editText1);
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 1);
        model = (StoreDetailModel) getIntent().getSerializableExtra("StoreDetailModel");
        item1 = getIntent().getIntExtra("item1", -1);
        item2 = getIntent().getIntExtra("item2", -1);
        item3 = getIntent().getIntExtra("item3", -1);
        item4 = getIntent().getIntExtra("item4", -1);
        parentId1 = getIntent().getStringExtra("parentId1");
        parentId2 = getIntent().getStringExtra("parentId2");
        parentId3 = getIntent().getStringExtra("parentId3");
        parentId4 = getIntent().getStringExtra("parentId4");
        name1 = getIntent().getStringExtra("name1");
        name2 = getIntent().getStringExtra("name2");
        name3 = getIntent().getStringExtra("name3");
        name4 = getIntent().getStringExtra("name4");

        //门店信息
        Glide.with(AddRoomNoManagementActivity.this)
                .load(model.getStoreInfo().getImage())
//                                .fitCenter()
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(CommonUtil.dip2px(StoreDetailActivity.this, 10))))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(imageView1);//加载图片
        tv_name.setText(model.getStoreInfo().getName());
        tv_shop.setText(model.getStoreInfo().getNeedInstallDeviceNum());
        tv_addr.setText(model.getStoreInfo().getAddress());

        ChangeUI();


        mAdapter = new CommonAdapter<String>
                (AddRoomNoManagementActivity.this, R.layout.item_addroomnomanagement, list) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                holder.setText(R.id.tv, model);
                //删除
                holder.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //区域
                dialogList(textView1, "0", 1);
                break;
            case R.id.textView2:
                //楼栋
                dialogList(textView2, parentId2, 2);
                break;
            case R.id.textView3:
                //楼层
                dialogList(textView3, parentId3, 3);
                break;
            case R.id.tv_add:
                //添加
                if (!editText1.getText().toString().trim().equals("")) {
                    KeyboardUtils.hideSoftInput(AddRoomNoManagementActivity.this);
                    list.add(editText1.getText().toString().trim());
                    editText1.setText("");
                    mAdapter.notifyDataSetChanged();
                } else myToast(editText1.getHint().toString());

                break;
            case R.id.tv_confirm:
                //保存
                MyLogger.i(">>>>>"+parentId);
                if (!parentId.equals("")) {
                    name = "";
                    for (String s : list) {
                        name = name + s + ",";
                    }
                    if (!name.equals("")) {
                        name = name.substring(0, name.length() - 1);
                        showProgress(true, getString(R.string.app_loading1));
                        params.clear();
                        params.put("name", name);
                        params.put("fullName", "");
                        params.put("level", type + "");
                        params.put("parentId", parentId);
                        params.put("storeId", model.getStoreInfo().getId());
                        requestUpData(params);

                    } else myToast("请" + title);
                } else {
                    switch (type) {
                        case 1:
                            //添加区域
                            break;
                        case 2:
                            //添加楼栋
                            myToast(textView1.getHint().toString());
                            break;
                        case 3:
                            //添加楼层
                            myToast(textView2.getHint().toString());
                            break;
                        case 4:
                            //添加房号
                            myToast(textView3.getHint().toString());
                            break;
                    }
                }

                break;
        }
    }

    @Override
    protected void updateView() {

    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.AddRoom, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
     * 选择区域
     */
    private void dialogList(TextView textView, String s, int i) {
        showProgress(true, getString(R.string.app_loading2));
        params.clear();
        params.put("parentId", s);
        params.put("storeId", model.getStoreInfo().getId());
        params.put("level", i + "");
        OkhttpUtil.okHttpPostJson(URLs.RoomNoManagement, GsonUtils.toJson(params), headerMap, new CallBackUtil<RoomNoManagementModel>() {
            @Override
            public RoomNoManagementModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(RoomNoManagementModel response) {
                hideProgress();
                dialog.contentView(R.layout.dialog_list_center)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
//                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                                CommonUtil.dip2px(AddRoomNoManagementActivity.this, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(AddRoomNoManagementActivity.this));
                CommonAdapter<RoomNoManagementModel.StoreRoomsListBean> adapter = new CommonAdapter<RoomNoManagementModel.StoreRoomsListBean>
                        (AddRoomNoManagementActivity.this, R.layout.item_help, response.getStoreRoomsList()) {
                    @Override
                    protected void convert(ViewHolder holder, RoomNoManagementModel.StoreRoomsListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model.getName());
                        int item = -1;
                        switch (i) {
                            case 1:
                                //选择的区域
                                item = item1;
                                break;
                            case 2:
                                //选择的楼栋
                                item = item2;
                                break;
                            case 3:
                                //选择的楼层
                                item = item3;
                                break;
                        }
                        if (position == item) {
                            tv.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            tv.setTextColor(getResources().getColor(R.color.black1));
                        }
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        textView.setText(response.getStoreRoomsList().get(position).getName());
                        switch (i) {
                            case 1://区域
                                parentId1 = response.getStoreRoomsList().get(position).getParentId();
                                name1 = response.getStoreRoomsList().get(position).getName();
                                item1 = position;

                                //选择的区域-清空后三项
                                name2 = "";
                                textView2.setText("");
                                parentId2 = response.getStoreRoomsList().get(position).getId();
                                item2 = -1;

                                name3 = "";
                                textView3.setText("");
                                parentId3 = "";
                                item3 = -1;

                                name4 = "";
//                                textView4.setText("");
                                parentId4 = "";
                                item4 = -1;
                                break;
                            case 2://楼栋
                                parentId2 = response.getStoreRoomsList().get(position).getParentId();
                                name2 = response.getStoreRoomsList().get(position).getName();
                                item2 = position;

                                //选择的楼栋-清空后两项
                                name3 = "";
                                textView3.setText("");
                                parentId3 = response.getStoreRoomsList().get(position).getId();
                                item3 = -1;

                                name4 = "";
//                                textView4.setText("");
                                parentId4 = "";
                                item4 = -1;
                                break;
                            case 3://房层
                                //选择的楼层
                                parentId3 = response.getStoreRoomsList().get(position).getParentId();
                                name3 = response.getStoreRoomsList().get(position).getName();
                                item3 = position;

                                //选择的楼栋-清空后一项
                                name4 = "";
//                                textView4.setText("");
                                parentId4 = response.getStoreRoomsList().get(position).getId();
                                item4 = -1;
                                break;
                        }
                        //获取下一级
                        showProgress(true, getString(R.string.app_loading2));
                        params.clear();
                        params.put("parentId", response.getStoreRoomsList().get(position).getId());
                        params.put("storeId", model.getStoreInfo().getId());
                        params.put("level", i + 1 + "");
                        requestNext(params, i);

                        dialog.dismiss();

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

    //获取下一级
    private void requestNext(HashMap<String, String> params, int i) {
        OkhttpUtil.okHttpPostJson(URLs.RoomNoManagement, GsonUtils.toJson(params), headerMap, new CallBackUtil<RoomNoManagementModel>() {
            @Override
            public RoomNoManagementModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(RoomNoManagementModel response) {
                hideProgress();
                if (response.getStoreRoomsList().size() == 0) {//没有数据
                    switch (i) {
                        case 1:
                            //区域
                            type = 2;
                            break;
                        case 2:
                            //楼栋
                            type = 3;
                            break;
                        case 3:
                            //楼层
                            type = 4;
                            break;
                    }
                } else {
                    //有数据
                    switch (i) {
                        case 1:
                            //区域
                            type = 3;
                            break;
                        case 2:
                            //楼栋
                            type = 4;
                            break;
                        case 3:
                            //楼层
//                            type = 4;
                            break;
                    }
                }
                ChangeUI();
            }
        });
    }

    private void ChangeUI() {
        switch (type) {
            case 1:
                title = "添加区域";
                shadowLayout.setVisibility(View.GONE);
                tv_title.setText("区域添加");
                editText1.setHint("请输入区域名称");

                parentId = parentId1;

                break;
            case 2:
                title = "添加楼栋";
                shadowLayout.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.GONE);
                relativeLayout3.setVisibility(View.GONE);
                tv_title.setText("楼栋添加");
                editText1.setHint("请输入楼栋");

                parentId = parentId2;
                textView1.setText(name1);//显示选择的区域

                break;
            case 3:
                title = "添加楼层";
                shadowLayout.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.VISIBLE);
                relativeLayout3.setVisibility(View.GONE);
                tv_title.setText("楼层添加");
                editText1.setHint("请输入楼层");

                parentId = parentId3;
                textView1.setText(name1);//显示选择的区域
                textView2.setText(name2);//显示选择的楼栋
                break;
            case 4:
                title = "添加房号";
                shadowLayout.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.VISIBLE);
                relativeLayout3.setVisibility(View.VISIBLE);
                tv_title.setText("房号添加");
                editText1.setHint("请输入房号");

                parentId = parentId4;
                textView1.setText(name1);//显示选择的区域
                textView2.setText(name2);//显示选择的楼栋
                textView3.setText(name3);//显示选择的楼层
                break;
        }
        titleView.setTitle(title);
    }
}
