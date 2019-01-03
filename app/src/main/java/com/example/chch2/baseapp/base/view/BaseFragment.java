package com.example.chch2.baseapp.base.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.chch2.baseapp.R;
import com.example.chch2.baseapp.base.inter.IBase;

import butterknife.ButterKnife;

public abstract class BaseFragment extends android.support.v4.app.Fragment implements IBase{

    protected View mRootView;//根view
    protected View mTitleView;//标题view
    protected View mContentView;//内容布局

    public ViewStub contentStub;//内容区域
    public ViewStub exceptionStub;//异常区域
    protected Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = getContext();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //2018.9.18---因界面整改，修改主界面的5个fragment的沉浸式，修改标题根布局,之前是R.layout.ly_base
        mRootView = inflater.inflate(R.layout.lay_base, container, false);
        //标题栏
        ViewStub titleStub = (ViewStub) mRootView.findViewById(R.id.title_stub);
        if (titleStub != null) {
            titleStub.setLayoutResource(getTitleId());
            mTitleView = titleStub.inflate();
        }
        //内容区域
        contentStub = (ViewStub) mRootView.findViewById(R.id.content_stub);
        if (contentStub != null) {
            contentStub.setLayoutResource(getContentId());
            mContentView = contentStub.inflate();
        }
        //异常区域
        exceptionStub = (ViewStub) mRootView.findViewById(R.id.exception_stub);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    protected int getTitleId() {
        return R.layout.layout_title;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(savedInstanceState);
    }
}
