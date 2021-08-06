package com.xiyang.xiyang.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.PersonnelListModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.popupwindow.PopupWindow_List2;
import com.xiyang.xiyang.utils.CommonUtil;
import com.xiyang.xiyang.utils.SearchDialog;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 人事记录列表
 */
public class PersonnelListActivity extends BaseActivity {
    int type = 1;//1、调整上级 2、调整市场 3、升职降职 4、采购申请
    private RecyclerView recyclerView;
    List<PersonnelListModel.RecordsBean> list = new ArrayList<>();
    CommonAdapter<PersonnelListModel.RecordsBean> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;
    private LinearLayout pop_view;
    int page = 1;
    List<String> list_time = new ArrayList<>();
    String sort = "desc", status = "", keyWord = "";
    int i1 = 0;
    int i2 = 0;

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnellist);
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                params.clear();
                switch (type) {
                    case 1:
                        //调整上级
                        params.put("type", "6");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                        params.put("current", page + "");
                        params.put("pageSize", "10");
                        params.put("keyWord", keyWord);
                        params.put("above", "");//是否越级
                        params.put("crossRegional", "");//是否跨区
                        requestList(params, URLs.PersonnelList);
                        break;
                    case 2:
                        //调整市场
                        params.put("type", "7");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                        params.put("current", page + "");
                        params.put("pageSize", "10");
                        params.put("keyWord", keyWord);
                        params.put("above", "");//是否越级
                        params.put("crossRegional", "");//是否跨区
                        requestList(params, URLs.PersonnelList);
                        break;
                    case 3:
                        //升职降职
                        params.put("type", "4");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                        params.put("current", page + "");
                        params.put("pageSize", "10");
                        params.put("keyWord", keyWord);
                        params.put("above", "");//是否越级
                        params.put("crossRegional", "");//是否跨区
                        requestList(params, URLs.PersonnelList);
                        break;
                    case 4:
                        //采购审批
                        params.put("page", page + "");
                        params.put("size", "10");
                        params.put("keyWord", keyWord);
                        requestList(params, URLs.CaiGouList);
                        break;
                }
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                params.clear();
                switch (type) {
                    case 1:
                        //调整上级
                        params.put("type", "6");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                        params.put("current", page + "");
                        params.put("pageSize", "10");
                        params.put("keyWord", keyWord);
                        params.put("above", "");//是否越级
                        params.put("crossRegional", "");//是否跨区
                        requestListMore(params, URLs.PersonnelList);
                        break;
                    case 2:
                        //调整市场
                        params.put("type", "7");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                        params.put("current", page + "");
                        params.put("pageSize", "10");
                        params.put("keyWord", keyWord);
                        params.put("above", "");//是否越级
                        params.put("crossRegional", "");//是否跨区
                        requestListMore(params, URLs.PersonnelList);
                        break;
                    case 3:
                        //升职降职
                        params.put("type", "4");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                        params.put("current", page + "");
                        params.put("pageSize", "10");
                        params.put("keyWord", keyWord);
                        params.put("above", "");//是否越级
                        params.put("crossRegional", "");//是否跨区
                        requestListMore(params, URLs.PersonnelList);
                        break;
                    case 4:
                        //采购审批
                        params.put("page", page + "");
                        params.put("size", "10");
                        params.put("keyWord", keyWord);
                        requestListMore(params, URLs.CaiGouList);
                        break;
                }
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        pop_view = findViewByID_My(R.id.pop_view);
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 1);
        switch (type) {
            case 1:
                //调整上级
                titleView.setTitle("调整上级列表");
                break;
            case 2:
                //调整市场
                titleView.setTitle("调整市场列表");
                break;
            case 3:
                //升职降职
                titleView.setTitle("升职降职列表");
                break;
            case 4:
                //采购审批
                titleView.setTitle("采购审批列表");
                break;
        }
        list_time.add(getString(R.string.app_type_jiangxu));
        list_time.add(getString(R.string.app_type_shengxu));
    }

    private void requestList(Map<String, String> params, String url) {
        OkhttpUtil.okHttpPostJson(url, GsonUtils.toJson(params), headerMap, new CallBackUtil<PersonnelListModel>() {
            @Override
            public PersonnelListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                showErrorPage();
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(PersonnelListModel response) {
                showContentPage();
                hideProgress();
                list = response.getRecords();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<PersonnelListModel.RecordsBean>
                            (PersonnelListActivity.this, R.layout.item_personnellist, list) {
                        @Override
                        protected void convert(ViewHolder holder, PersonnelListModel.RecordsBean model, int position) {
                            holder.setText(R.id.tv_id, model.getSn());
                            TextView tv_type = holder.getView(R.id.tv_type);
                            switch (model.getStatus()) {//1:待审核; 2:未通过; 3:已通过;
                                case "1":
                                    tv_type.setText("处理中");
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    break;
                                case "2":
                                    tv_type.setText("驳回");
                                    tv_type.setTextColor(getResources().getColor(R.color.red));
                                    break;
                                case "3":
                                    tv_type.setText("已完成");
                                    tv_type.setTextColor(getResources().getColor(R.color.green));
                                    break;
                            }
                            TextView tv_key1 = holder.getView(R.id.tv_key1);
                            TextView tv_key2 = holder.getView(R.id.tv_key2);
                            TextView tv_key3 = holder.getView(R.id.tv_key3);
                            switch (type) {
                                case 1:
                                    //调整上级
                                    tv_key1.setText("调整人");
                                    tv_key2.setText("调整前上级");
                                    tv_key3.setText("调整后上级");
                                    holder.setText(R.id.tv_value1, model.getAdminName());
                                    holder.setText(R.id.tv_value2, model.getOldParentName());
                                    holder.setText(R.id.tv_value3, model.getNewParentName());
                                    break;
                                case 2:
                                    //调整市场
                                    String oldcity = "";
                                    if (model.getOldRegions() !=null){
                                        for (PersonnelListModel.RecordsBean.OldRegionsBean bean : model.getOldRegions()) {
                                            oldcity = oldcity + bean.getNameX() + "、";
                                        }
                                    }

                                    String newcity = "";
                                    if (model.getNewRegions() !=null) {
                                        for (PersonnelListModel.RecordsBean.NewRegionsBean bean : model.getNewRegions()) {
                                            newcity = newcity + bean.getNameX() + "、";
                                        }
                                    }
                                    if (!oldcity.equals("")) {
                                        oldcity = oldcity.substring(0, oldcity.length() - 1);
                                    }
                                    if (!newcity.equals("")) {
                                        newcity = newcity.substring(0, newcity.length() - 1);
                                    }
                                    tv_key1.setText("调整人");
                                    tv_key2.setText("调整前省市");
                                    tv_key3.setText("调整后省市");
                                    holder.setText(R.id.tv_value1, model.getAdminName());
                                    holder.setText(R.id.tv_value2, oldcity);
                                    holder.setText(R.id.tv_value3, newcity);
                                    break;
                                case 3:
                                    //升职降职
                                    tv_key1.setText("调整人");
                                    tv_key2.setText("调整前职位");
                                    tv_key3.setText("调整后职位");
                                    holder.setText(R.id.tv_value1, model.getAdminName());
                                    holder.setText(R.id.tv_value2, model.getOldOrganCode());
                                    holder.setText(R.id.tv_value3, model.getNewOrganCode());
                                    break;
                                case 4:
                                    //采购审批
                                    tv_key1.setText("申请人");
                                    tv_key2.setText("申请数量");
                                    tv_key3.setText("提货时间");
                                    holder.setText(R.id.tv_value1, model.getName());
                                    holder.setText(R.id.tv_value2, model.getNum() + "台");
                                    holder.setText(R.id.tv_value3, model.getFetchAt());
                                    break;
                            }

                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    bundle.putInt("PersonnelType", type);
                                    CommonUtil.gotoActivityWithData(PersonnelListActivity.this, PersonnelDetailActivity.class, bundle, false);
                                }
                            });

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                }

            }
        });

    }

    private void requestListMore(Map<String, String> params, String url) {
        OkhttpUtil.okHttpPostJson(url, GsonUtils.toJson(params), headerMap, new CallBackUtil<PersonnelListModel>() {
            @Override
            public PersonnelListModel onParseResponse(Call call, Response response) {
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
            public void onResponse(PersonnelListModel response) {
//                showContentPage();
                hideProgress();
                List<PersonnelListModel.RecordsBean> list1 = new ArrayList<>();
                list1 = response.getRecords();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }


            }
        });

    }

    @Override
    public void onClick(View v) {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (v.getId()) {
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
//                showPopupWindow1(pop_view);
                new PopupWindow_List2(PersonnelListActivity.this, 1, list_time, i2, pop_view) {
                    @Override
                    public void onFailure(String keys, int item) {
                        if (item == 0) {
                            sort = "desc";
                        } else {
                            sort = "asc";
                        }
                        requestServer();
                    }
                };
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
//                textView1.setCompoundDrawables(null, null, drawable2, null);
//                textView2.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                showPopupWindow2(pop_view);
                new SearchDialog(PersonnelListActivity.this, dialog) {
                    @Override
                    public void onFailure(String keys) {
                        keyWord = keys;
                        requestServer();
                    }
                };
                break;
        }
    }

    @Override
    protected void updateView() {
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        params.clear();
        switch (type) {
            case 1:
                //调整上级
                params.put("type", "6");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                params.put("current", page + "");
                params.put("pageSize", "10");
                params.put("keyWord", keyWord);
                params.put("above", "");//是否越级
                params.put("crossRegional", "");//是否跨区
                requestList(params, URLs.PersonnelList);
                break;
            case 2:
                //调整市场
                params.put("type", "7");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                params.put("current", page + "");
                params.put("pageSize", "10");
                params.put("keyWord", keyWord);
                params.put("above", "");//是否越级
                params.put("crossRegional", "");//是否跨区
                requestList(params, URLs.PersonnelList);
                break;
            case 3:
                //升职降职
                params.put("type", "4");//类型 1-解除,2-绑定,3-换绑,4-升职,5-降职,6-调整上级,7-调整运维市场
                params.put("current", page + "");
                params.put("pageSize", "10");
                params.put("keyWord", keyWord);
                params.put("above", "");//是否越级
                params.put("crossRegional", "");//是否跨区
                requestList(params, URLs.PersonnelList);
                break;
            case 4:
                //采购审批
                params.put("page", page + "");
                params.put("size", "10");
                params.put("keyWord", keyWord);
                requestList(params, URLs.CaiGouList);
                break;
        }

    }
}
