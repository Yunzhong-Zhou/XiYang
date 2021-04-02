package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.Fragment1Model;
import com.xiyang.xiyang.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2021/4/2.
 * 房号管理
 */
public class RoomNoManagementActivity extends BaseActivity {
    TextView textView1, textView2;
    RecyclerView recyclerView;
    List<Fragment1Model> list = new ArrayList<>();
    CommonAdapter<Fragment1Model> mAdapter;
    int type = 1, item = -1;
    TextView tv_tab1, tv_tab2, tv_tab3, tv_tab4;
    LinearLayout ll_tab1, ll_tab2, ll_tab3, ll_tab4;
    View view1, view2, view3, view4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomnomanagement);
    }

    @Override
    protected void initView() {
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
        for (int i = 0; i < 5; i++) {
            list.add(new Fragment1Model());
        }
        mAdapter = new CommonAdapter<Fragment1Model>
                (RoomNoManagementActivity.this, R.layout.item_roomnomanagement, list) {
            @Override
            protected void convert(ViewHolder holder, Fragment1Model model, int position) {
                LinearLayout ll = holder.getView(R.id.ll);
                TextView tv = holder.getView(R.id.tv);
                ImageView iv_edit = holder.getView(R.id.iv_edit);
                ImageView iv_delete = holder.getView(R.id.iv_delete);
                ImageView iv_device = holder.getView(R.id.iv_device);

                if (item == position){
                    ll.setBackgroundResource(R.color.green);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    iv_edit.setImageResource(R.mipmap.ic_edit_white);
                    iv_delete.setImageResource(R.mipmap.ic_delete_white);
                    iv_device.setImageResource(R.mipmap.ic_device_white);
                }else {
                    ll.setBackgroundResource(R.color.transparent);
                    tv.setTextColor(getResources().getColor(R.color.black2));
                    iv_edit.setImageResource(R.mipmap.ic_edit);
                    iv_delete.setImageResource(R.mipmap.ic_delete);
                    iv_device.setImageResource(R.mipmap.ic_device);
                }

//                        holder.setText(R.id.tv1, model.getTitle());
//                        holder.setText(R.id.tv2, model.getProvince() + model.getCity() + model.getDistrict());
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item = position;
                        mAdapter.notifyDataSetChanged();
                    }
                });

            }
        };
        recyclerView.setAdapter(mAdapter);
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
                bundle.putInt("type",type);
                CommonUtil.gotoActivityWithData(RoomNoManagementActivity.this,AddRoomNoManagementActivity.class,bundle);
                break;
            case R.id.ll_tab1:
                //区域
                type = 1;
                changeUI();
                break;
            case R.id.ll_tab2:
                //楼栋
                type = 2;
                changeUI();
                break;
            case R.id.ll_tab3:
                //楼层
                type = 3;
                changeUI();
                break;
            case R.id.ll_tab4:
                //房号
                type = 4;
                changeUI();
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
                /*if (list1.size() > 0) {
                    showContentPage();
                    recyclerView1.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
                } else {
                    showEmptyPage();
                }*/
                break;
            case 2:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black1));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                textView1.setText("选择楼栋");
                textView2.setText("添加新的楼栋");
                break;
            case 3:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black1));
                tv_tab4.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.INVISIBLE);
                textView1.setText("选择楼层");
                textView2.setText("添加新的楼层");
                break;
            case 4:
                tv_tab1.setTextColor(getResources().getColor(R.color.black3));
                tv_tab2.setTextColor(getResources().getColor(R.color.black3));
                tv_tab3.setTextColor(getResources().getColor(R.color.black3));
                tv_tab4.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.VISIBLE);
                textView1.setText("选择房号");
                textView2.setText("添加新的房号");
                break;

        }
    }
}
