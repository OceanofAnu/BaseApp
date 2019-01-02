package com.example.chch2.baseapp.base.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;


import com.example.chch2.baseapp.R;
import com.example.chch2.baseapp.base.inter.IBase;
import com.example.chch2.baseapp.base.manager.AppManager;
import com.example.chch2.baseapp.base.util.StatusBarUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements IBase{


    protected Context mContext;
    protected View mRootView;//根布局
    protected View mTitleView;//标题区域
    protected View mContentView;//内容区域
    public   ViewStub exceptionStub;//异常区域
    public ViewStub contentStub;//内容区域

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.getAppManager().addActivity(this);

        mContext = this;
        mRootView= LayoutInflater.from(mContext).inflate(R.layout.lay_base,null);
        //标题栏
        ViewStub titleStub= (ViewStub) mRootView.findViewById(R.id.title_stub);
        if (titleStub!=null){
            titleStub.setLayoutResource(getTitleId());
            mTitleView=titleStub.inflate();
        }
        //内容区域
        contentStub= (ViewStub) mRootView.findViewById(R.id.content_stub);
        if (contentStub!=null){
            contentStub.setLayoutResource(getContentId());
            mContentView=contentStub.inflate();
        }
        //异常区域
        exceptionStub = (ViewStub) mRootView.findViewById(R.id.exception_stub);

        setContentView(mRootView);//将mRootView设置为Activity的视图
        ButterKnife.bind(this);
        StatusBarUtil.setStatus(this);
        bindView(savedInstanceState);

    }

    protected int getTitleId(){
        return R.layout.layout_title;
    }



}
