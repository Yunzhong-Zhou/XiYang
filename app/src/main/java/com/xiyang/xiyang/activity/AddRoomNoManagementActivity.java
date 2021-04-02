package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lihang.ShadowLayout;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.xiyang.xiyang.model.Fragment1Model;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2021/4/2.
 */
public class AddRoomNoManagementActivity extends BaseActivity {
    int type = 1;
    //信息筛选
    ShadowLayout shadowLayout;
    RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3;
    EditText textView1, textView2, textView3;
    TextView tv_title, tv_add;
    EditText editText1;
    RecyclerView recyclerView;
    List<Fragment1Model> list = new ArrayList<>();
    CommonAdapter<Fragment1Model> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroomnomanagement);
    }

    @Override
    protected void initView() {
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
        for (int i = 0; i < 5; i++) {
            list.add(new Fragment1Model());
        }
        mAdapter = new CommonAdapter<Fragment1Model>
                (AddRoomNoManagementActivity.this, R.layout.item_addroomnomanagement, list) {
            @Override
            protected void convert(ViewHolder holder, Fragment1Model model, int position) {
                /*LinearLayout ll = holder.getView(R.id.ll);
                TextView tv = holder.getView(R.id.tv);
                ImageView iv_edit = holder.getView(R.id.iv_edit);
                ImageView iv_delete = holder.getView(R.id.iv_delete);
                ImageView iv_device = holder.getView(R.id.iv_device);

                if (item == position){
                    ll.setBackgroundResource(R.color.green);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    iv_edit.setImageResource(R.mipmap.ic_edit_white);
                    iv_delete.setImageResource(R.mipmap.ic_delete_white);
                    iv_device.setImageResource(R.mipmap.ic_delete_white);
                }else {
                    ll.setBackgroundResource(R.color.transparent);
                    tv.setTextColor(getResources().getColor(R.color.black2));
                    iv_edit.setImageResource(R.mipmap.ic_edit);
                    iv_delete.setImageResource(R.mipmap.ic_delete);
                    iv_device.setImageResource(R.mipmap.ic_delete);
                }*/

//                        holder.setText(R.id.tv1, model.getTitle());
//                        holder.setText(R.id.tv2, model.getProvince() + model.getCity() + model.getDistrict());
                /*holder.getView(R.id.ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item = position;
                        mAdapter.notifyDataSetChanged();

                    }
                });*/

            }
        };
        recyclerView.setAdapter(mAdapter);

        type = getIntent().getIntExtra("type", 1);
        switch (type) {
            case 1:
                titleView.setTitle("添加区域");
                shadowLayout.setVisibility(View.GONE);
                tv_title.setText("区域添加");
                editText1.setHint("请输入区域名称");
                break;
            case 2:
                titleView.setTitle("添加楼栋");
                shadowLayout.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.GONE);
                relativeLayout3.setVisibility(View.GONE);
                tv_title.setText("楼栋添加");
                editText1.setHint("请输入楼栋");
                break;
            case 3:
                titleView.setTitle("添加楼层");
                shadowLayout.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.VISIBLE);
                relativeLayout3.setVisibility(View.GONE);
                tv_title.setText("楼层添加");
                editText1.setHint("请输入楼层");
                break;
            case 4:
                titleView.setTitle("添加房号");
                shadowLayout.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.VISIBLE);
                relativeLayout3.setVisibility(View.VISIBLE);
                tv_title.setText("房号添加");
                editText1.setHint("请输入房号");
                break;
        }
    }

    @Override
    protected void updateView() {

    }
}
