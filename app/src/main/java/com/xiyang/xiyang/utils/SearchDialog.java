package com.xiyang.xiyang.utils;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;

import java.util.ArrayList;

/**
 * Created by Mr.Z on 2021/7/29.
 */
public abstract class SearchDialog {
    ArrayList<String> searchList = new ArrayList<>();
    String keys = "";

    public SearchDialog(Activity activity, BaseDialog dialog) {
        if (!activity.isFinishing()) {
            if (dialog.isShowing() == false) {
                dialog.contentView(R.layout.dialog_search)
                        /*.layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))*/
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ScreenUtils.getScreenHeight() / 2))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .gravity(Gravity.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                EditText et_search = dialog.findViewById(R.id.editText_search);
                ImageView iv_delete = dialog.findViewById(R.id.iv_delete);
                TextView tv_search = dialog.findViewById(R.id.tv_search);
                FlowLayout flowLayout = dialog.findViewById(R.id.flowLayout);
                ShowHistory(activity, flowLayout, dialog);
                et_search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length() == 0) {
                            iv_delete.setVisibility(View.GONE);
                        } else {
                            iv_delete.setVisibility(View.VISIBLE);
                        }
                    }
                });
                et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            MyLogger.i(">>>>>>>>输入后：" + et_search.getText().toString().trim());
                            //关闭软键盘
                            KeyboardUtils.hideSoftInput(et_search);
                            //do something
                            if (!et_search.getText().toString().trim().equals("")) {
                                keys = et_search.getText().toString().trim();
                                SaveArrayListUtil.saveArrayList(activity, searchList, keys);
//                                ShowHistory(activity,flowLayout);
                                onFailure(keys);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(activity, "请输入需要搜索的内容", Toast.LENGTH_SHORT);
                            }
                            return false;   //返回true，保留软键盘;false，隐藏软键盘
                        }
                        return false;
                    }
                });
                iv_delete.setOnClickListener(v -> {
                    et_search.setText("");
                    /*//再次获取焦点
                    et_search.setFocusable(true);
                    et_search.setFocusableInTouchMode(true);
                    et_search.requestFocus();
                    et_search.findFocus();
                    KeyboardUtils.showSoftInput();//弹出键盘*/
                });
                tv_search.setOnClickListener(v -> {
                    //关闭软键盘
                    KeyboardUtils.hideSoftInput(tv_search);
                    //do something
                    if (!et_search.getText().toString().trim().equals("")) {
                        keys = et_search.getText().toString().trim();
                        SaveArrayListUtil.saveArrayList(activity, searchList, keys);
//                        ShowHistory(activity,flowLayout);
                        onFailure(keys);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(activity, "请输入需要搜索的内容", Toast.LENGTH_SHORT);
                    }

                });
                dialog.findViewById(R.id.ic_delete_history).setOnClickListener(v -> {
                    //清空搜索历史
                    searchList.clear();
                    SaveArrayListUtil.removeArrayList(activity, searchList);
                    ShowHistory(activity, flowLayout, dialog);
                });
            }
        }
    }

    /**
     * 展示历史数据
     */
    private void ShowHistory(Activity activity, FlowLayout flowLayout, BaseDialog dialog) {
        searchList = SaveArrayListUtil.getSearchArrayList(activity);
        FlowLayoutAdapter<String> flowLayoutAdapter1 =
                new FlowLayoutAdapter<String>
                        (searchList) {
                    @Override
                    public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position,
                                               String bean) {
                        TextView tv1 = holder.getView(R.id.tv1);
                        tv1.setText(bean);
                    }

                    @Override
                    public void onItemClick(int position, String bean) {
//                        showToast("点击" + position);
                        keys = bean;
                        SaveArrayListUtil.saveArrayList(activity, searchList, keys);
//                        ShowHistory(activity,flowLayout);
                        onFailure(keys);
                        dialog.dismiss();
                    }

                    @Override
                    public int getItemLayoutID(int position, String bean) {
                        return R.layout.item_searchhistory;
                    }
                };
        flowLayout.setAdapter(flowLayoutAdapter1);
    }

    public abstract void onFailure(String keys);
}
