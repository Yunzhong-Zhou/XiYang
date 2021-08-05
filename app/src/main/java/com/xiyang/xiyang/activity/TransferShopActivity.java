package com.xiyang.xiyang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.CommonModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.Constant;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
 * 划转商户
 */
public class TransferShopActivity extends BaseActivity {
    EditText textView1, textView2;
    String shopId = "", reason = "", reasonId = "";
    int i_liyou = -1;
    List<CommonModel.ListBean> list_liyou = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfershop);
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
    }

    @Override
    protected void initData() {
        params.clear();
        request(params, URLs.Common + "MERCHANT_TRANSFER_REASON");
    }

    private void request(Map<String, String> params, String url) {
        OkhttpUtil.okHttpGet(url, params, headerMap, new CallBackUtil<List<CommonModel.ListBean>>() {
            @Override
            public List<CommonModel.ListBean> onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(List<CommonModel.ListBean> response) {
                hideProgress();
                i_liyou = -1;
                reason = "";
                list_liyou = response;
            }
        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //选择商户
                Intent intent2 = new Intent(TransferShopActivity.this, MyShopListActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("requestCode", Constant.SELECT_SHOP);
                bundle2.putString("status", "4");
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, Constant.SELECT_SHOP, bundle2);
                break;
            case R.id.textView2:
                //选择理由
                dialogList_liyou();
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showToast("确认划转该商户吗？", "确定", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            showProgress(true, getString(R.string.app_loading1));
                            params.put("merchantId", shopId);
                            params.put("reasonId", reasonId);
                            requestUpData(params);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
                break;
        }
    }

    private boolean match() {
        if (TextUtils.isEmpty(shopId)) {
            myToast("请选择商户");
            return false;
        }
//        reason = textView2.getText().toString().trim();
        if (TextUtils.isEmpty(reasonId)) {
            myToast("请选择划转理由");
            return false;
        }
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("申请商户划转");
    }

    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.TransferShop, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.SELECT_SHOP:
                    //选择商户
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        shopId = bundle.getString("shopId");
                        textView1.setText(bundle.getString("shopName"));
                    }
                    break;
            }

        }
    }

    /**
     * 选择理由
     */
    private void dialogList_liyou() {
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
        CommonAdapter<CommonModel.ListBean> adapter = new CommonAdapter<CommonModel.ListBean>
                (TransferShopActivity.this, R.layout.item_help, list_liyou) {
            @Override
            protected void convert(ViewHolder holder, CommonModel.ListBean model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model.getName());
                if (i_liyou == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                i_liyou = position;
                textView2.setText(list_liyou.get(position).getName());
                reasonId = list_liyou.get(position).getId();
                reason = list_liyou.get(position).getName();
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
