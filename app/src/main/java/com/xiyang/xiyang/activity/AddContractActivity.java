package com.xiyang.xiyang.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.base.BaseActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2021/3/28.
 * 添加合同
 */
public class AddContractActivity extends BaseActivity {
    List<String> list_hetong = new ArrayList<>();
    int item_hetong;
    RelativeLayout rl_hetongleixing, rl_xuanzeshanghu, rl_xuanzemendian, rl_shanghumingcheng, rl_shanghuzhanghao,
            rl_shanghulianxiren, rl_lianxirendianhua, rl_gongsimingcheng, rl_yinyezhizhaohao, rl_shanghuhangye,
            rl_suozaichengshi, rl_xiangxidizhi, rl_shougexiaoshi, rl_jichujijia, rl_meirifengding, rl_mianfeishichang,
            rl_jifeidanyuan, rl_mendianjiajia, rl_mendianfengding, rl_tiaojialiyou, rl_zhuanchumendian,
            rl_zhuanrumendian, rl_zhuanchushebei, rl_shebeishuliang, rl_jianshaoyuanyin, rl_tuihuicangku,
            rl_huishoufangshi, rl_shoujianren, rl_shoujiandianhua, rl_shoujiandizhi, rl_xinzengshuliang,
            rl_qianyueqixian, rl_xuqiannianxian, rl_shifoudujia, rl_qianyueshijian, rl_xuqianshijian,
            rl_xuanzeyuanyin, rl_hetongwenjian;
    EditText tv_hetongleixing, tv_xuanzeshanghu, tv_xuanzemendian, tv_shanghumingcheng, tv_shanghuzhanghao,
            tv_shanghulianxiren, tv_lianxirendianhua, tv_gongsimingcheng, tv_yinyezhizhaohao, tv_shanghuhangye,
            tv_suozaichengshi, tv_xiangxidizhi, tv_shougexiaoshi, tv_jichujijia, tv_meirifengding, tv_mianfeishichang,
            tv_jifeidanyuan, tv_mendianjiajia, tv_mendianfengding, tv_tiaojialiyou, tv_zhuanchumendian,
            tv_zhuanrumendian, tv_zhuanchushebei, tv_shebeishuliang, tv_jianshaoyuanyin, tv_tuihuicangku,
            tv_huishoufangshi, tv_shoujianren, tv_shoujiandianhua, tv_shoujiandizhi, tv_xinzengshuliang,
            tv_qianyueqixian, tv_xuqiannianxian, tv_shifoudujia, tv_qianyueshijian, tv_xuqianshijian,
            tv_xuanzeyuanyin, tv_hetongwenjian;

    TextView tv_img, tv_confirm;
    ImageView iv_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontract);
    }

    @Override
    protected void initView() {
        rl_hetongleixing = findViewByID_My(R.id.rl_hetongleixing);
        rl_xuanzeshanghu = findViewByID_My(R.id.rl_xuanzeshanghu);
        rl_xuanzemendian = findViewByID_My(R.id.rl_xuanzemendian);
        rl_shanghumingcheng = findViewByID_My(R.id.rl_shanghumingcheng);
        rl_shanghuzhanghao = findViewByID_My(R.id.rl_shanghuzhanghao);
        rl_shanghulianxiren = findViewByID_My(R.id.rl_shanghulianxiren);
        rl_lianxirendianhua = findViewByID_My(R.id.rl_lianxirendianhua);
        rl_gongsimingcheng = findViewByID_My(R.id.rl_gongsimingcheng);
        rl_yinyezhizhaohao = findViewByID_My(R.id.rl_yinyezhizhaohao);
        rl_shanghuhangye = findViewByID_My(R.id.rl_shanghuhangye);
        rl_suozaichengshi = findViewByID_My(R.id.rl_suozaichengshi);
        rl_xiangxidizhi = findViewByID_My(R.id.rl_xiangxidizhi);
        rl_shougexiaoshi = findViewByID_My(R.id.rl_shougexiaoshi);
        rl_jichujijia = findViewByID_My(R.id.rl_jichujijia);
        rl_meirifengding = findViewByID_My(R.id.rl_meirifengding);
        rl_mianfeishichang = findViewByID_My(R.id.rl_mianfeishichang);
        rl_jifeidanyuan = findViewByID_My(R.id.rl_jifeidanyuan);
        rl_mendianjiajia = findViewByID_My(R.id.rl_mendianjiajia);
        rl_mendianfengding = findViewByID_My(R.id.rl_mendianfengding);
        rl_tiaojialiyou = findViewByID_My(R.id.rl_tiaojialiyou);
        rl_zhuanchumendian = findViewByID_My(R.id.rl_zhuanchumendian);
        rl_zhuanrumendian = findViewByID_My(R.id.rl_zhuanrumendian);
        rl_zhuanchushebei = findViewByID_My(R.id.rl_zhuanchushebei);
        rl_shebeishuliang = findViewByID_My(R.id.rl_shebeishuliang);
        rl_jianshaoyuanyin = findViewByID_My(R.id.rl_jianshaoyuanyin);
        rl_tuihuicangku = findViewByID_My(R.id.rl_tuihuicangku);
        rl_huishoufangshi = findViewByID_My(R.id.rl_huishoufangshi);
        rl_shoujianren = findViewByID_My(R.id.rl_shoujianren);
        rl_shoujiandianhua = findViewByID_My(R.id.rl_shoujiandianhua);
        rl_xinzengshuliang = findViewByID_My(R.id.rl_xinzengshuliang);
        rl_qianyueqixian = findViewByID_My(R.id.rl_qianyueqixian);
        rl_xuqiannianxian = findViewByID_My(R.id.rl_xuqiannianxian);
        rl_shoujiandizhi = findViewByID_My(R.id.rl_shoujiandizhi);
        rl_shifoudujia = findViewByID_My(R.id.rl_shifoudujia);
        rl_qianyueshijian = findViewByID_My(R.id.rl_qianyueshijian);
        rl_xuqianshijian = findViewByID_My(R.id.rl_xuqianshijian);
        rl_xuanzeyuanyin = findViewByID_My(R.id.rl_xuanzeyuanyin);
        rl_hetongwenjian = findViewByID_My(R.id.rl_hetongwenjian);

        tv_hetongleixing = findViewByID_My(R.id.tv_hetongleixing);
        tv_xuanzeshanghu = findViewByID_My(R.id.tv_xuanzeshanghu);
        tv_xuanzemendian = findViewByID_My(R.id.tv_xuanzemendian);
        tv_shanghumingcheng = findViewByID_My(R.id.tv_shanghumingcheng);
        tv_shanghuzhanghao = findViewByID_My(R.id.tv_shanghuzhanghao);
        tv_shanghulianxiren = findViewByID_My(R.id.tv_shanghulianxiren);
        tv_lianxirendianhua = findViewByID_My(R.id.tv_lianxirendianhua);
        tv_gongsimingcheng = findViewByID_My(R.id.tv_gongsimingcheng);
        tv_yinyezhizhaohao = findViewByID_My(R.id.tv_yinyezhizhaohao);
        tv_shanghuhangye = findViewByID_My(R.id.tv_shanghuhangye);
        tv_suozaichengshi = findViewByID_My(R.id.tv_suozaichengshi);
        tv_xiangxidizhi = findViewByID_My(R.id.tv_xiangxidizhi);
        tv_shougexiaoshi = findViewByID_My(R.id.tv_shougexiaoshi);
        tv_jichujijia = findViewByID_My(R.id.tv_jichujijia);
        tv_meirifengding = findViewByID_My(R.id.tv_meirifengding);
        tv_mianfeishichang = findViewByID_My(R.id.tv_mianfeishichang);
        tv_jifeidanyuan = findViewByID_My(R.id.tv_jifeidanyuan);
        tv_mendianjiajia = findViewByID_My(R.id.tv_mendianjiajia);
        tv_mendianfengding = findViewByID_My(R.id.tv_mendianfengding);
        tv_tiaojialiyou = findViewByID_My(R.id.tv_tiaojialiyou);
        tv_zhuanchumendian = findViewByID_My(R.id.tv_zhuanchumendian);
        tv_zhuanrumendian = findViewByID_My(R.id.tv_zhuanrumendian);
        tv_zhuanchushebei = findViewByID_My(R.id.tv_zhuanchushebei);
        tv_shebeishuliang = findViewByID_My(R.id.tv_shebeishuliang);
        tv_jianshaoyuanyin = findViewByID_My(R.id.tv_jianshaoyuanyin);
        tv_tuihuicangku = findViewByID_My(R.id.tv_tuihuicangku);
        tv_huishoufangshi = findViewByID_My(R.id.tv_huishoufangshi);
        tv_shoujianren = findViewByID_My(R.id.tv_shoujianren);
        tv_shoujiandianhua = findViewByID_My(R.id.tv_shoujiandianhua);
        tv_xinzengshuliang = findViewByID_My(R.id.tv_xinzengshuliang);
        tv_qianyueqixian = findViewByID_My(R.id.tv_qianyueqixian);
        tv_xuqiannianxian = findViewByID_My(R.id.tv_xuqiannianxian);
        tv_shoujiandizhi = findViewByID_My(R.id.tv_shoujiandizhi);
        tv_shifoudujia = findViewByID_My(R.id.tv_shifoudujia);
        tv_qianyueshijian = findViewByID_My(R.id.tv_qianyueshijian);
        tv_xuqianshijian = findViewByID_My(R.id.tv_xuqianshijian);
        tv_xuanzeyuanyin = findViewByID_My(R.id.tv_xuanzeyuanyin);
        tv_hetongwenjian = findViewByID_My(R.id.tv_hetongwenjian);

        tv_img = findViewByID_My(R.id.tv_img);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        iv_add = findViewByID_My(R.id.iv_add);


    }

    @Override
    protected void initData() {
        list_hetong.add("签约合同");
        list_hetong.add("新增合同");
        list_hetong.add("回收合同");
        list_hetong.add("换绑合同");
        list_hetong.add("修改合同");
        list_hetong.add("续签合同");
        list_hetong.add("取消合同");
        list_hetong.add("调价合同");

        item_hetong = getIntent().getIntExtra("item_hetong",1);
        changeUI();
    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加合同");
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_hetongleixing:
                //选择合同类型
                dialog.contentView(R.layout.dialog_list)
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
                        (AddContractActivity.this, R.layout.item_help, list_hetong) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        TextView tv = holder.getView(R.id.textView1);
                        tv.setText(model);
                        if (item_hetong == position)
                            tv.setTextColor(getResources().getColor(R.color.green));
                        else
                            tv.setTextColor(getResources().getColor(R.color.black1));
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        item_hetong = position;
                        tv_hetongleixing.setText(list_hetong.get(position));
                        adapter.notifyDataSetChanged();
                        changeUI();
                        dialog.dismiss();

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_list.setAdapter(adapter);
                break;
        }
    }

    private void changeUI() {
        rl_hetongleixing.setVisibility(View.VISIBLE);
        rl_xuanzeshanghu.setVisibility(View.GONE);
        rl_xuanzemendian.setVisibility(View.GONE);
        rl_shanghumingcheng.setVisibility(View.GONE);
        rl_shanghuzhanghao.setVisibility(View.GONE);
        rl_shanghulianxiren.setVisibility(View.GONE);
        rl_lianxirendianhua.setVisibility(View.GONE);
        rl_gongsimingcheng.setVisibility(View.GONE);
        rl_yinyezhizhaohao.setVisibility(View.GONE);
        rl_shanghuhangye.setVisibility(View.GONE);
        rl_suozaichengshi.setVisibility(View.GONE);
        rl_xiangxidizhi.setVisibility(View.GONE);
        rl_shougexiaoshi.setVisibility(View.GONE);
        rl_jichujijia.setVisibility(View.GONE);
        rl_meirifengding.setVisibility(View.GONE);
        rl_mianfeishichang.setVisibility(View.GONE);
        rl_jifeidanyuan.setVisibility(View.GONE);
        rl_mendianjiajia.setVisibility(View.GONE);
        rl_mendianfengding.setVisibility(View.GONE);
        rl_tiaojialiyou.setVisibility(View.GONE);
        rl_zhuanchumendian.setVisibility(View.GONE);
        rl_zhuanrumendian.setVisibility(View.GONE);
        rl_zhuanchushebei.setVisibility(View.GONE);
        rl_shebeishuliang.setVisibility(View.GONE);
        rl_jianshaoyuanyin.setVisibility(View.GONE);
        rl_tuihuicangku.setVisibility(View.GONE);
        rl_huishoufangshi.setVisibility(View.GONE);
        rl_shoujianren.setVisibility(View.GONE);
        rl_shoujiandianhua.setVisibility(View.GONE);
        rl_xinzengshuliang.setVisibility(View.GONE);
        rl_qianyueqixian.setVisibility(View.GONE);
        rl_xuqiannianxian.setVisibility(View.GONE);
        rl_shoujiandizhi.setVisibility(View.GONE);
        rl_shifoudujia.setVisibility(View.GONE);
        rl_qianyueshijian.setVisibility(View.GONE);
        rl_xuqianshijian.setVisibility(View.GONE);
        rl_xuanzeyuanyin.setVisibility(View.GONE);
        rl_hetongwenjian.setVisibility(View.GONE);
        tv_img.setVisibility(View.GONE);
        iv_add.setVisibility(View.GONE);
        switch (item_hetong) {
            case 0:
                //签约合同
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_qianyueqixian.setVisibility(View.VISIBLE);
                rl_shifoudujia.setVisibility(View.VISIBLE);
                rl_qianyueshijian.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                tv_img.setVisibility(View.VISIBLE);
                tv_img.setText("执照上传");
                iv_add.setVisibility(View.VISIBLE);
                break;
            case 1:
                //新增合同
                rl_xuanzemendian.setVisibility(View.VISIBLE);
                rl_xinzengshuliang.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                break;
            case 2:
                //回收合同
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_xuanzemendian.setVisibility(View.VISIBLE);
                rl_shebeishuliang.setVisibility(View.VISIBLE);
                rl_jianshaoyuanyin.setVisibility(View.VISIBLE);
                rl_tuihuicangku.setVisibility(View.VISIBLE);
                rl_huishoufangshi.setVisibility(View.VISIBLE);
                rl_shoujianren.setVisibility(View.VISIBLE);
                rl_shoujiandianhua.setVisibility(View.VISIBLE);
                rl_shoujiandizhi.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                break;
            case 3:
                //换绑合同
                rl_zhuanchumendian.setVisibility(View.VISIBLE);
                rl_zhuanrumendian.setVisibility(View.VISIBLE);
                rl_zhuanchushebei.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                break;
            case 4:
                //修改合同
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_shanghumingcheng.setVisibility(View.VISIBLE);
                rl_shanghuzhanghao.setVisibility(View.VISIBLE);
                rl_shanghulianxiren.setVisibility(View.VISIBLE);
                rl_lianxirendianhua.setVisibility(View.VISIBLE);
                rl_gongsimingcheng.setVisibility(View.VISIBLE);
                rl_yinyezhizhaohao.setVisibility(View.VISIBLE);
                rl_shanghuhangye.setVisibility(View.VISIBLE);
                rl_suozaichengshi.setVisibility(View.VISIBLE);
                rl_xiangxidizhi.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                tv_img.setVisibility(View.VISIBLE);
                tv_img.setText("执照上传");
                iv_add.setVisibility(View.VISIBLE);
                break;
            case 5:
                //续签合同
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_xuqiannianxian.setVisibility(View.VISIBLE);
                rl_shifoudujia.setVisibility(View.VISIBLE);
                rl_xuqianshijian.setVisibility(View.VISIBLE);
                tv_img.setVisibility(View.VISIBLE);
                tv_img.setText("上传合同");
                iv_add.setVisibility(View.VISIBLE);
                break;
            case 6:
                //取消合同
                rl_xuanzeshanghu.setVisibility(View.VISIBLE);
                rl_xuanzeyuanyin.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                break;
            case 7:
                //调价合同
                rl_xuanzemendian.setVisibility(View.VISIBLE);
                rl_shougexiaoshi.setVisibility(View.VISIBLE);
                rl_jichujijia.setVisibility(View.VISIBLE);
                rl_meirifengding.setVisibility(View.VISIBLE);
                rl_mianfeishichang.setVisibility(View.VISIBLE);
                rl_jifeidanyuan.setVisibility(View.VISIBLE);
                rl_mendianjiajia.setVisibility(View.VISIBLE);
                rl_mendianfengding.setVisibility(View.VISIBLE);
                rl_tiaojialiyou.setVisibility(View.VISIBLE);
                rl_hetongwenjian.setVisibility(View.VISIBLE);
                break;
        }
    }

}
