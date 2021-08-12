package com.xiyang.xiyang.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.model.IndustryModel;
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
public abstract class SelectIndustryDialog {
    Map<String, String> params = new HashMap<>();
    Map<String, String> headerMap = new HashMap<>();
    /**
     * 选择行业
     */
    List<IndustryModel.ListBean> list_hangye = new ArrayList<>();
    int maxIdex_hangye = 2;
    String string_hangye = "",industryId = "";

    public SelectIndustryDialog(Activity activity, BaseDialog dialog) {
        dialogList_hangye(activity, dialog, "0");
    }

    private void dialogList_hangye(Activity activity, BaseDialog dialog, String id) {
        params.clear();
//        params.put("id", id);
//        params.put("level", maxIdex_hangye + "");
        OkhttpUtil.okHttpGet(URLs.Industry + id, params, headerMap, new CallBackUtil<IndustryModel>() {
            @Override
            public IndustryModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
            }

            @Override
            public void onResponse(IndustryModel response) {
                list_hangye = response.getList();
                dialog.contentView(R.layout.dialog_list_center)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
//                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                                CommonUtil.dip2px(activity, 400)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_list = dialog.findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(activity));
                CommonAdapter<IndustryModel.ListBean> adapter = new CommonAdapter<IndustryModel.ListBean>
                        (activity, R.layout.item_help, list_hangye) {
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
                            //最后一个，赋值
                            if (!string_hangye.equals("")) {
                                string_hangye = string_hangye.substring(0, string_hangye.length() - 1);
                            }
                            industryId = list_hangye.get(position).getId();
                            onCallBack(string_hangye,industryId);
                            //初始化
                            string_hangye = "";
                            maxIdex_hangye = 2;

                            dialog.dismiss();
                        } else {
                            dialogList_hangye(activity, dialog, list_hangye.get(position).getId());
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


    public abstract void onCallBack(String string_hangye, String industryId);
}
