package com.xiyang.xiyang.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.model.CommonModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
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
 * Created by Mr.Z on 2021/7/29.
 */
public abstract class SelectCityDialog {
    Map<String, String> params = new HashMap<>();
    Map<String, String> headerMap = new HashMap<>();
    /**
     * 选择城市
     */
    List<CommonModel.ListBean> list_chengshi = new ArrayList<>();
    int maxIdex_chengshi = 1;
    String province = "", city = "", district = "", provinceId = "", cityId = "", areaId = "";

    public SelectCityDialog(Activity activity, BaseDialog dialog) {
        dialogList_chengshi(activity, dialog, "");
    }

    private void dialogList_chengshi(Activity activity, BaseDialog dialog, String id) {
        params.clear();
        params.put("id", id);
        params.put("level", maxIdex_chengshi + "");
        OkhttpUtil.okHttpPostJson(URLs.Region, GsonUtils.toJson(params), headerMap, new CallBackUtil<CommonModel>() {
            @Override
            public CommonModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
            }

            @Override
            public void onResponse(CommonModel response) {
                list_chengshi = response.getList();
                dialog.contentView(R.layout.dialog_selectcity)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
//                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                                CommonUtil.dip2px(CooperativeShopActivity.this, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.5f)
                        .show();
                TextView tv1 = dialog.findViewById(R.id.textView1);
                TextView tv2 = dialog.findViewById(R.id.textView2);
                TextView tv3 = dialog.findViewById(R.id.textView3);

                if (!province.equals("")){
                    tv1.setText(province);
                    tv1.setTextColor(activity.getResources().getColor(R.color.green));
                }
                if (!city.equals("")){
                    tv2.setText(city);
                    tv2.setTextColor(activity.getResources().getColor(R.color.green));
                }
                if (!district.equals("")){
                    tv3.setText(district);
                    tv3.setTextColor(activity.getResources().getColor(R.color.green));
                }

                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(activity));
                CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                        (activity, R.layout.item_help, list_chengshi) {
                    @Override
                    protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model.getName());
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
//                        string_chengshi = string_chengshi + list_chengshi.get(position).getName() + "-";
                        switch (maxIdex_chengshi) {
                            case 3:
                                //区
                                //最后一个，赋值
                                tv3.setText(list_chengshi.get(position).getName());
                                tv3.setTextColor(activity.getResources().getColor(R.color.green));
                                district = list_chengshi.get(position).getName();
                                areaId = list_chengshi.get(position).getId();
                                //初始化
                                maxIdex_chengshi = 1;
                                onCallBack(province, city, district, provinceId, cityId, areaId);
                                dialog.dismiss();
                                break;
                            case 2:
                                //市
                                tv2.setText(list_chengshi.get(position).getName());
                                tv2.setTextColor(activity.getResources().getColor(R.color.green));
                                city = list_chengshi.get(position).getName();
                                cityId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 3;
                                dialogList_chengshi(activity, dialog, list_chengshi.get(position).getId());
                                break;
                            case 1:
                                //省
                                tv1.setText(list_chengshi.get(position).getName());
                                tv1.setTextColor(activity.getResources().getColor(R.color.green));
                                province = list_chengshi.get(position).getName();
                                provinceId = list_chengshi.get(position).getId();
                                maxIdex_chengshi = 2;
                                dialogList_chengshi(activity, dialog, list_chengshi.get(position).getId());
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


    public abstract void onCallBack(String province, String city, String district, String provinceId, String cityId, String areaId);
}
