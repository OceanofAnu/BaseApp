package com.example.chch2.baseapp.base.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chch2.baseapp.R;
import com.example.chch2.baseapp.base.constant.LoadingState;
import com.example.chch2.baseapp.base.inter.OnRetryListener;

import butterknife.BindView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gyg on 2016/1/21.
 */
public class ExceptionView extends FrameLayout {

    private Context mContext;
    private View rootView;
    private static ExceptionView mInstance;
    private OnRetryListener mRetryListener;

    @BindView(R.id.ll_over)
    LinearLayout ll_over;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;
    @BindView(R.id.tv_loaded)
    TextView tv_loaded;
    @BindView(R.id.tv_loading)
    TextView tv_loading;
    @BindView(R.id.btn_loaded)
    Button btn_loaded;
    @BindView(R.id.iv_loading)
    ProgressBar iv_loading;
    @BindView(R.id.iv_loaded)
    ImageView iv_loaded;

    //构建实例
    public static ExceptionView Builder(Context context){
        if (mInstance==null){
            mInstance=new ExceptionView(context);
        }
        return mInstance;
    }

    public ExceptionView(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        //if (View.GONE == visibility && mState == LoadingState.STATE_LOADING && animation != null && animation.isRunning()) {
          //  animation.stop();
        //}
    }

    public ExceptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    public ExceptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExceptionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }

    //
    public ExceptionView withRetryListener(OnRetryListener mRetryListener){
        this.mRetryListener=mRetryListener;
        return this;
    }

    /**
     * 设置btn_loaded按钮监听
     */
    @OnClick(R.id.btn_loaded)
    public void onClick(View view){
        if (mRetryListener!=null){
            mRetryListener.onRetry();
        }
    }

    /**
     * 加载中提示文字图标
     */
    private String mLoadingText;
    //private int mLoadingIco;
    public ExceptionView withLoadingIco() {
       // mLoadingIco = resId;


        return this;
    }

    /**
     * 加载数据为空提示文字图标
     */
    private String mLoaded_empty_text;
    private int mEmptyIco;

    public ExceptionView withEmptyIco(int resId) {
        mEmptyIco = resId;
        return this;
    }
    /**
     * 没网显示文字图标
     */
    private String mLoaded_not_net_text;
    private int mNoNetIco;

    public ExceptionView withNoNetIco(int resId) {
        mNoNetIco = resId;
        return this;
    }


    private LoadingState mState;//页面状态

    public void setState(LoadingState state) {
        if (mState == state) {
            return;
        } else if (state == LoadingState.STATE_LOADING) {
//            Uri uri = Uri.parse(HttpUrls.LOADINGGIF);
//
//            DraweeController draweeController =
//                    Fresco.newDraweeControllerBuilder()
//                            .setUri(uri)
//                            .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
//                            .build();
//
//            iv_loading.setController(draweeController);
            ll_over.setVisibility(GONE);
            ll_loading.setVisibility(VISIBLE);
        } else {
            ll_loading.setVisibility(GONE);
            ll_over.setVisibility(VISIBLE);
        }
        changeState(state);
    }

    public boolean btn_empty_ennable = true;
    public boolean btn_error_ennable = true;
    public boolean btn_nonet_ennable = true;


    public ExceptionView withBtnNoNetEnnable(boolean ennable) {
        btn_nonet_ennable = ennable;
        return this;
    }

    public ExceptionView withBtnErrorEnnable(boolean ennable) {
        btn_error_ennable = ennable;
        return this;
    }


    public ExceptionView withBtnEmptyEnnable(boolean ennable) {
        btn_empty_ennable = ennable;
        return this;
    }

   // private AnimationDrawable animation;

    private void changeState(LoadingState state) {
        switch (state) {
            case STATE_LOADING://显示加载图片
                mState = LoadingState.STATE_LOADING;
                //iv_loading.setImageResource(mLoadingIco);
               // tv_loading.setText(mLoadingText);
//                if (animation == null) {
//                    animation = (AnimationDrawable) iv_loading.getDrawable();
//                }
//                if (animation != null)
//                    animation.start();
                break;
            case STATE_EMPTY:
                mState = LoadingState.STATE_EMPTY;
                iv_loaded.setImageResource(mEmptyIco);
                tv_loaded.setText(mLoaded_empty_text);
//                if (btn_empty_ennable) {
//                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_empty_text);
               // } else {
                    btn_loaded.setVisibility(GONE);
              //  }
                break;
            case STATE_ERROR:
                mState = LoadingState.STATE_ERROR;
                iv_loaded.setImageResource(mErrorIco);
                tv_loaded.setText(mLoaded_error_text);
                if (btn_error_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_error_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
            case STATE_NO_NET:
                mState = LoadingState.STATE_NO_NET;
                iv_loaded.setImageResource(mNoNetIco);
                tv_loaded.setText(mLoaded_not_net_text);
                if (btn_nonet_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_nonet_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
        }

    }


    /**
     * 后台或者本地出现错误提示
     */
    private String mLoaded_error_text;
    private int mErrorIco;

    public ExceptionView withErrorIco(int resId) {
        mErrorIco = resId;
        return this;
    }

    public ExceptionView withLoadedEmptyText(int resId) {
        mLoaded_empty_text = getResources().getString(resId);
        return this;
    }

    public ExceptionView withLoadedEmptyText(String mLoadedemptyText) {
        this.mLoaded_empty_text = mLoadedemptyText;
        return this;
    }

    public ExceptionView withLoadedNoNetText(int resId) {
        mLoaded_not_net_text = getResources().getString(resId);
        return this;
    }

    public String btn_empty_text = "重试";
    public String btn_error_text = "重试";
    public String btn_nonet_text = "重试";

    public ExceptionView withbtnEmptyText(String text) {
        this.btn_empty_text = text;
        return this;
    }

    public ExceptionView withbtnErrorText(String text) {
        this.btn_error_text = text;
        return this;
    }

    public ExceptionView withbtnNoNetText(String text) {
        this.btn_nonet_text = text;
        return this;
    }


    public ExceptionView withLoadedNoNetText(String mLoadedNoNetText) {
        this.mLoaded_not_net_text = mLoadedNoNetText;
        return this;
    }

    public ExceptionView withLoadedErrorText(int resId) {
        mLoaded_error_text = getResources().getString(resId);
        return this;
    }

    public ExceptionView withLoadedErrorText(String mLoadedErrorText) {
        this.mLoaded_error_text = mLoadedErrorText;
        return this;
    }

    public ExceptionView withLoadingText(int resId) {
        mLoadingText = getResources().getString(resId);
        return this;
    }

    public ExceptionView withLoadingText(String mLoadingText) {
        this.mLoadingText = mLoadingText;
        return this;
    }

    //view中调用
    public void BindView() {
        rootView= View.inflate(mContext, R.layout.loading, this);
        ButterKnife.bind(this,rootView);
    }
    //adapter中调用
    public  ExceptionView setContent(View view){
        rootView=view;
        ButterKnife.bind(this,view);
        return this;
    }

    public View getView(){
        return rootView;
    }
}
