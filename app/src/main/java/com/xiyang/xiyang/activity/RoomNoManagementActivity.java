package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.RoomNoManagementModel;
import com.xiyang.xiyang.model.StoreDetailModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
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
 * 房号管理
 */
public class RoomNoManagementActivity extends BaseActivity {
    StoreDetailModel model_sdm;
    RoomNoManagementModel model_rnm;
    String parentId = "0", parentId1 = "0", parentId2 = "", parentId3 = "", parentId4 = "",
            name1 = "", name2 = "", name3 = "", name4 = "";
    TextView textView1, textView2;
    RecyclerView recyclerView;
    List<RoomNoManagementModel.ListBean> list = new ArrayList<>();
    CommonAdapter<RoomNoManagementModel.ListBean> mAdapter;
    int type = 1, item1 = -1, item2 = -1, item3 = -1, item4 = -1;
    TextView tv_tab1, tv_tab2, tv_tab3, tv_tab4;
    LinearLayout ll_tab1, ll_tab2, ll_tab3, ll_tab4;
    View view1, view2, view3, view4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomnomanagement);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                params.put("parentId", parentId);
                params.put("storeId", model_sdm.getId());
//        params.put("type", "all");
                request(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        ll_tab3 = findViewByID_My(R.id.ll_tab3);
        ll_tab4 = findViewByID_My(R.id.ll_tab4);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_tab3.setOnClickListener(this);
        ll_tab4.setOnClickListener(this);
        tv_tab1 = findViewByID_My(R.id.tv_tab1);
        tv_tab2 = findViewByID_My(R.id.tv_tab2);
        tv_tab3 = findViewByID_My(R.id.tv_tab3);
        tv_tab4 = findViewByID_My(R.id.tv_tab4);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        view4 = findViewByID_My(R.id.view4);
    }

    @Override
    protected void initData() {
        model_sdm = (StoreDetailModel) getIntent().getSerializableExtra("StoreDetailModel");

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        params.put("parentId", parentId);
        params.put("storeId", model_sdm.getId());
//        params.put("type", "all");
        request(params);
    }

    private void request(HashMap<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.RoomNoManagement, params, headerMap, new CallBackUtil<RoomNoManagementModel>() {
            @Override
            public RoomNoManagementModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showErrorPage();
                myToast(err);
            }

            @Override
            public void onResponse(RoomNoManagementModel response) {
                hideProgress();
                showContentPage();
                model_rnm = response;

                list = response.getList();
                if (list.size() > 0) {
                    mAdapter = new CommonAdapter<RoomNoManagementModel.ListBean>
                            (RoomNoManagementActivity.this, R.layout.item_roomnomanagement, list) {
                        @Override
                        protected void convert(ViewHolder holder, RoomNoManagementModel.ListBean model, int position) {
                            LinearLayout ll = holder.getView(R.id.ll);
                            TextView tv = holder.getView(R.id.tv);
                            tv.setText(model.getName());

                            ImageView iv_edit = holder.getView(R.id.iv_edit);
                            ImageView iv_delete = holder.getView(R.id.iv_delete);
                            ImageView iv_device = holder.getView(R.id.iv_device);

                            //选择
                            int item = -1;
                            switch (type) {
                                case 1:
                                    item = item1;
                                    break;
                                case 2:
                                    item = item2;
                                    break;
                                case 3:
                                    item = item3;
                                    break;
                                case 4:
                                    item = item4;
                                    break;
                            }
                            if (item == position) {
                                ll.setBackgroundResource(R.color.green);
                                tv.setTextColor(getResources().getColor(R.color.white));
                                iv_edit.setImageResource(R.mipmap.ic_edit_white);
                                iv_delete.setImageResource(R.mipmap.ic_delete_white);
                                iv_device.setImageResource(R.mipmap.ic_device_white);
                            } else {
                                ll.setBackgroundResource(R.color.transparent);
                                tv.setTextColor(getResources().getColor(R.color.black2));
                                iv_edit.setImageResource(R.mipmap.ic_edit);
                                iv_delete.setImageResource(R.mipmap.ic_delete);
                                iv_device.setImageResource(R.mipmap.ic_device);
                            }

                            //点击
                            ll.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //点击后保存下一级的parentId
                                    switch (type) {
                                        case 1:
                                            item1 = position;
                                            name1 = list.get(position).getName();

                                            parentId2 = list.get(position).getId();
                                            type = 2;
                                            break;
                                        case 2:
                                            item2 = position;
                                            name2 = list.get(position).getName();

                                            parentId3 = list.get(position).getId();
                                            type = 3;
                                            break;
                                        case 3:
                                            item3 = position;
                                            name3 = list.get(position).getName();

                                            parentId4 = list.get(position).getId();
                                            type = 4;
                                            break;
                                        /*case 4:
                                            item4 = position;
                                            name4 = list.get(position).getName();

//                                            parentId5 = list.get(position).getId();
                                            break;*/
                                    }
                                    mAdapter.notifyDataSetChanged();

//                                    parentId = list.get(position).getId();
                                    changeUI();
                                }
                            });

                            //修改
                            holder.getView(R.id.iv_edit).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog_edit("编辑", list.get(position).getName(), list.get(position).getId(), list.get(position).getParentId());
                                }
                            });
                            //删除
                            holder.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认删除吗？", "确定", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(false, getString(R.string.app_loading1));
                                            params.clear();
                                            params.put("id", list.get(position).getId());
                                            params.put("storeId", model_sdm.getId());
                                            requestChange(params, URLs.DeleteRoom, 2);//删除
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("房号管理");
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.textView2:
                //添加
                bundle.putInt("type", type);
                bundle.putSerializable("StoreDetailModel", model_sdm);
                bundle.putInt("item1", item1);
                bundle.putInt("item2", item2);
                bundle.putInt("item3", item3);
                bundle.putInt("item4", item4);
                bundle.putString("parentId1", parentId1);
                bundle.putString("parentId2", parentId2);
                bundle.putString("parentId3", parentId3);
                bundle.putString("parentId4", parentId4);
                bundle.putString("name1", name1);
                bundle.putString("name2", name2);
                bundle.putString("name3", name3);
                bundle.putString("name4", name4);
                CommonUtil.gotoActivityWithData(RoomNoManagementActivity.this, AddRoomNoManagementActivity.class, bundle);
                break;
            case R.id.ll_tab1:
                //区域
                type = 1;
                parentId1 = "0";
                changeUI();
                break;
            case R.id.ll_tab2:
                //楼栋
                if (item1 != -1) {
                    type = 2;
                    changeUI();
                } else myToast("请" + textView1.getText().toString());

                break;
            case R.id.ll_tab3:
                //楼层
                if (item2 != -1) {
                    type = 3;
                    changeUI();
                } else myToast("请" + textView1.getText().toString());
                break;
            case R.id.ll_tab4:
                //房号
                if (item3 != -1) {
                    type = 4;
                    changeUI();
                } else myToast("请" + textView1.getText().toString());
                break;

        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                textView1.setText("选择区域");
                textView2.setText("添加新的区域");

                parentId = parentId1;

                //清空四级选择的数据
                item1 = -1;
//                parentId1 = "";
                name1 = "";

                item2 = -1;
//                parentId2 = "";
                name2 = "";

                item3 = -1;
//                parentId3 = "";
                name3 = "";

                item4 = -1;
//                parentId4 = "";
                name4 = "";

                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                textView1.setText("选择楼栋");
                textView2.setText("添加新的楼栋");

                parentId = parentId2;

                //清空后三级选择的数据
                item2 = -1;
//                parentId2 = "";
                name2 = "";

                item3 = -1;
//                parentId3 = "";
                name3 = "";

                item4 = -1;
//                parentId4 = "";
                name4 = "";
                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.INVISIBLE);
                textView1.setText("选择楼层");
                textView2.setText("添加新的楼层");

                parentId = parentId3;

                //清空后二级的数据
                item3 = -1;
//                parentId3 = "";
                name3 = "";

                item4 = -1;
//                parentId4 = "";
                name4 = "";
                break;
            case 4:
                tv_tab1.setTextColor(getResources().getColor(R.color.black1));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                tv_tab4.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.VISIBLE);
                textView1.setText("选择房号");
                textView2.setText("添加新的房号");

                parentId = parentId4;

                item4 = -1;
//                parentId4 = "";
                name4 = "";
                break;
        }
        requestServer();
    }

    //编辑
    private void dialog_edit(String txt, String hint, String id, String parentId) {
        dialog.contentView(R.layout.dialog_edit)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.CENTER)
                .gravity(Gravity.CENTER)
                .canceledOnTouchOutside(true)
                .dimAmount(0.8f)
                .show();
        TextView textView1 = dialog.findViewById(R.id.textView1);
        textView1.setText(txt);
        EditText textView2 = dialog.findViewById(R.id.textView2);
        textView2.setHint(hint);
        TextView textView3 = dialog.findViewById(R.id.textView3);
        TextView textView4 = dialog.findViewById(R.id.textView4);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView2.getText().toString().trim().equals("")) {
                    KeyboardUtils.hideSoftInput(textView2);

                    dialog.dismiss();

                    showProgress(false, getString(R.string.app_loading1));
                    params.clear();
                    params.put("id", id);
                    params.put("name", textView2.getText().toString().trim());
                    params.put("parentId", parentId);
                    params.put("storeId", model_sdm.getId());
                    requestChange(params, URLs.ChageRoom, 1);//修改
                } else {
                    myToast(hint);
                }
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideSoftInput(RoomNoManagementActivity.this);
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideSoftInput(RoomNoManagementActivity.this);
                dialog.dismiss();
            }
        });
    }

    private void requestChange(Map<String, String> params, String url, int i) {
        OkhttpUtil.okHttpPost(url, params, headerMap, new CallBackUtil<String>() {
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
                if (i == 1) myToast("修改成功");
                else{
                    myToast("删除成功");
                    switch (type) {
                        case 1:
                            item1 = -1;
                            parentId1 = "0";
                            name1 = "";
                            break;
                        case 2:
                            item2 = -1;
                            parentId2 = "";
                            name2 = "";
                            break;
                        case 3:
                            item3 = -1;
                            parentId3 = "";
                            name3 = "";
                            break;
                        case 4:
                            item4 = -1;
                            parentId4 = "";
                            name4 = "";
                            break;
                    }
                }



                hideProgress();
                requestServer();
            }
        });
    }
}
