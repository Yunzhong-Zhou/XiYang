package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.GsonUtils;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.AddBuyModel;
import com.xiyang.xiyang.net.URLs;
import com.xiyang.xiyang.okhttp.CallBackUtil;
import com.xiyang.xiyang.okhttp.OkhttpUtil;
import com.xiyang.xiyang.utils.CommonUtil;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2021/3/28.
 * 采购申请
 */
public class AddBuyActivity extends BaseActivity {
    //    List<String> list_cangku = new ArrayList<>();
//    int i_cangku = -1;
    EditText editText1, editText2, editText3;
    String num = "", time = "", cangku = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbuy);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

    }

    @Override
    protected void initData() {
        params.clear();
        request(params);
    }

    private void request(Map<String, String> params) {
        OkhttpUtil.okHttpGet(URLs.CangKu, params, headerMap, new CallBackUtil<AddBuyModel>() {
            @Override
            public AddBuyModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(AddBuyModel response) {
                hideProgress();
//                i_cangku = -1;
                editText3.setText(response.getWarehouse().getName());
            }
        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.editText2:
                //选择提货时间
                CommonUtil.selectDate2YMD(AddBuyActivity.this,
                        "请选择提货时间", editText2, editText2.getText().toString().trim());
                break;
            case R.id.editText3:
                //选择仓库
//                dialogList_cangku(editText3);
                break;

            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    params.clear();
                    params.put("num", num);
                    params.put("fetchAt", time);
//                    params.put("cangku", cangku);
                    requestUpData(params);
                }
                break;
        }
    }

    private boolean match() {
        num = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            myToast("请输入采购数量");
            return false;
        }
        time = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(time)) {
            myToast("请选择提货时间");
            return false;
        }
        /*if (TextUtils.isEmpty(cangku)) {
            myToast("请选择提货仓库");
            return false;
        }*/
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("采购申请");
    }

    /**
     * 提交数据
     */
    private void requestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPostJson(URLs.AddBuy, GsonUtils.toJson(params), headerMap, new CallBackUtil<String>() {
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
//                finish();
                Bundle bundle = new Bundle();
                bundle.putInt("type", 4);
                CommonUtil.gotoActivityWithData(AddBuyActivity.this, PersonnelListActivity.class, bundle, false);
            }
        });
    }

    /**
     * 选择仓库
     */
    /*private void dialogList_cangku(TextView textView) {
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
        CommonAdapter<String> adapter = new CommonAdapter<String>
                (AddBuyActivity.this, R.layout.item_help, list_cangku) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                TextView tv = holder.getView(R.id.textView1);
                tv.setText(model);
                if (i_cangku == position)
                    tv.setTextColor(getResources().getColor(R.color.green));
                else
                    tv.setTextColor(getResources().getColor(R.color.black1));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                i_cangku = position;
                textView.setText(list_cangku.get(position));
                cangku = list_cangku.get(position);
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_list.setAdapter(adapter);
    }*/

}
