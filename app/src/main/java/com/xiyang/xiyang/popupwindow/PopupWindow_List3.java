package com.xiyang.xiyang.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.adapter.Pop_ListAdapter;
import com.xiyang.xiyang.view.FixedPopupWindow;

import java.util.List;

/**
 * Created by Mr.Z on 2021/7/29.
 */
public abstract class PopupWindow_List3 {
    /**
     *
     * @param activity
     * @param i  第几个列表展示
     * @param list 显示数据列表
     * @param item 历史选择的item
     * @param v 在view下展示
     */
    public PopupWindow_List3(Activity activity, int i, List<String> list, int item, View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(activity).inflate(
                R.layout.pop_list3, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,

                true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        ListView[] listViews = {(ListView) contentView.findViewById(R.id.pop_listView1),
                (ListView) contentView.findViewById(R.id.pop_listView2),
                (ListView) contentView.findViewById(R.id.pop_listView3)};
        for (int j = 0; j < listViews.length; j++) {
            if (j == i){
                listViews[j].setVisibility(View.VISIBLE);
            }else {
                listViews[j].setVisibility(View.INVISIBLE);
            }
        }

        final Pop_ListAdapter adapter = new Pop_ListAdapter(activity, list);
        adapter.setSelectItem(item);
        listViews[i].setAdapter(adapter);
        listViews[i].setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();

                onFailure(list.get(i),i);
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(activity.getResources().getColor(R.color.transparentblack2));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }

    public abstract void onFailure(String keys, int item);
}
