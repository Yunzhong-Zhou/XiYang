package com.xiyang.xiyang.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.hjq.toast.ToastUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.xiyang.xiyang.R;
import com.xiyang.xiyang.activity.MainActivity;
import com.xiyang.xiyang.utils.LocalUserInfo;
import com.xiyang.xiyang.view.LoadingLayout;
import com.xiyang.xiyang.view.MyDefaultFooter;
import com.xiyang.xiyang.view.MyDefaultHeader;
import com.xiyang.xiyang.view.TitleView;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;


/**
 * Created by ling on 2015/7/31.
 * last:2015/7/31
 * description:
 */
public abstract class BaseFragment extends Fragment implements IBaseView_Response, View.OnClickListener {
    private ProgressDialog pd;
    protected View mParent;
    protected BaseActivity mActivity;
    protected TitleView titleView;
    protected SpringView springView;
    protected LoadingLayout loadingLayout;
    protected LocalUserInfo localUserInfo;
    protected Map<String, String> maps = new HashMap<>();
    public Map<String, String> headerMap = new HashMap<>();

    //    protected ImmersionBar mImmersionBar;
    protected BaseDialog dialog;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i("BaseFragment", getClass().getSimpleName());
        mParent = getView();

        if (!(getActivity() instanceof MainActivity)) {//这里转换
            mActivity = (BaseActivity) getActivity();
        }

        titleView = (TitleView) getView().findViewById(R.id.title_view);
        localUserInfo = LocalUserInfo.getInstance(getActivity());

        /*headerMap.put("Accept", URLs.Accept);//客户端接受响应参数类型，接口调用设置成application/json
        headerMap.put("Authorization", localUserInfo.getTokenType()+" "+localUserInfo.getToken());//登录token,如果没有此值为空，如果设备端获取证书，此项可不填
        headerMap.put("Client-Type", URLs.ClientType);//客户端类型，h5-web,wechat-微信小程序,zhifubao-支付宝小程序,android-安卓，ios-苹果
        headerMap.put("Clinet-Os", CommonUtil.getSystemVersion());//客户端系统或内核版本
        headerMap.put("X-Version", URLs.XVERSION);//当前版本号（后台定）*/

        dialog = new BaseDialog(getActivity());

//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.init();   //所有子类都将继承这些相同的属性

        initCommonView();
        initView(mParent);
        initData();
        updateView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (mImmersionBar != null)
            mImmersionBar.destroy();*/
        if (dialog != null) {
            dialog.dismiss();
        }
        if (pd != null) {
            pd.dismiss();
        }
    }

    protected void initCommonView() {
        springView = findViewByID_My(R.id.springView);
        if (springView != null) {
            //默认风格
        /*springView.setHeader(new DefaultHeader(getActivity(),R.drawable.progress_circular,R.mipmap.arrow));
          springView.setFooter(new DefaultFooter(getActivity(),R.drawable.progress_circular));*/
            springView.setHeader(new MyDefaultHeader(getActivity()));
            springView.setFooter(new MyDefaultFooter(getActivity()));
            //阿里风格
//        springView.setHeader(new AliHeader(getActivity(), true));
//        springView.setFooter(new AliFooter(getActivity(), true));

            //美团风格
//        springView.setHeader(new MeituanHeader(getActivity()));
//        springView.setFooter(new MeituanFooter(getActivity()));

            //机械齿轮
//        springView.setHeader(new RotationHeader(getActivity()));
//        springView.setFooter(new RotationFooter(getActivity()));
        }


        loadingLayout = findViewByID_My(R.id.loading_layout);
        if (loadingLayout != null) {
            loadingLayout.setOnRetryClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadingLayout.showLoading();
                    requestServer();
                }
            });
        }
    }

    protected <T extends View> T findViewByID_My(int id) {
        return (T) mParent.findViewById(id);
    }

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void updateView();

    protected void myToast(String content) {
//        Toast.makeText(this.getActivity(),content, Toast.LENGTH_SHORT).show();
        /*Toast toast = Toast.makeText(this.getActivity(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();*/
        ToastUtils.show(content);
    }

    public void requestServer() {

    }


    @Override
    /**
     * @param flag 是否加载更多
     */
    public void setSpringViewMore(boolean flag) {
        if (springView != null) {
            springView.setEnableFooter(flag);
        }
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (!getActivity().isFinishing()) {
            if (pd == null) {
                pd = new ProgressDialog(getActivity());
            }
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(flag);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage(message);
            pd.show();
        }
    }

    @Override
    public void hideProgress() {
        try {
            if (springView != null) {
                springView.onFinishFreshAndLoad();
            }
            if (pd == null) {
                return;
            } else {
                if (pd.isShowing()) {
                    if (!getActivity().isFinishing()) {
                        pd.dismiss();
                    }
                }
            }
        } catch (Exception e) {

        }

        /*if (dialog != null) {
            if (dialog.isShowing() == true) {
                dialog.dismiss();
            }
        }*/
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    /*@Override
    public void showToast(String msg) {
        if (!getActivity().isFinishing()) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }*/
    @Override
    public void showToast(String msg) {
//        if (!getActivity().isFinishing()) {
//            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//        }
       /* if (!getActivity().isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(msg);
            builder.setTitle(getString(R.string.app_prompt));
            builder.setPositiveButton(getString(R.string.app_confirm), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }*/
        if (!getActivity().isFinishing()) {
            if (dialog.isShowing() == false) {
                dialog.contentView(R.layout.dialog_base1)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .gravity(Gravity.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                TextView textView2 = dialog.findViewById(R.id.textView2);
                textView2.setText(msg);
                dialog.findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        }

    }

    public void showToast(String msg, View.OnClickListener listener) {
        //        if (!getActivity().isFinishing()) {
//            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        }
        /*if (!getActivity().isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(msg);
            builder.setTitle(getString(R.string.app_prompt));
            builder.setPositiveButton(getString(R.string.app_confirm), listener);
            builder.create().show();
        }*/
        if (!getActivity().isFinishing()) {
            if (dialog.isShowing() == false) {
                dialog.contentView(R.layout.dialog_base1)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .gravity(Gravity.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                TextView textView2 = dialog.findViewById(R.id.textView2);
                textView2.setText(msg);
                dialog.findViewById(R.id.textView3).setOnClickListener(listener);
                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        }
    }

    public void showToast(String msg, String btntxt1, String btntxt2, View.OnClickListener listener1, View.OnClickListener listener2) {
        //        if (!isFinishing()) {
//            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        }
        /*if (!isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(msg);
            builder.setTitle(getString(R.string.app_prompt));
            builder.setPositiveButton(getString(R.string.app_confirm), listener);
            builder.create().show();
        }*/
        if (!getActivity().isFinishing()) {
            if (dialog.isShowing() == false) {
                dialog.contentView(R.layout.dialog_base2)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .gravity(Gravity.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                TextView textView2 = dialog.findViewById(R.id.textView2);
                textView2.setText(msg);
                TextView textView3 = dialog.findViewById(R.id.textView3);
                TextView textView4 = dialog.findViewById(R.id.textView4);
                textView3.setText(btntxt1);
                textView4.setText(btntxt2);
                textView3.setOnClickListener(listener1);
                textView4.setOnClickListener(listener2);

                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        }
    }

    @Override
    public String getStringbyid(int resId) {
        return getString(resId);
    }


    @Override
    public void showLoadingPage() {
        if (loadingLayout != null) {
            loadingLayout.showLoading();
        }
    }

    @Override
    public void showErrorPage() {
        if (loadingLayout != null) {
            loadingLayout.showError();
        }
    }

    @Override
    public void showEmptyPage() {
        if (loadingLayout != null) {
            loadingLayout.showEmpty();
        }
    }

    @Override
    public void showContentPage() {
        if (loadingLayout != null) {
            loadingLayout.showContent();
        }
    }

    @Override
    public void onClick(View v) {

    }

}
