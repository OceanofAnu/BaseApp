package com.example.chch2.baseapp.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.chch2.baseapp.R;
import com.example.chch2.baseapp.base.manager.TitleManager;
import com.example.chch2.baseapp.base.presenter.BasePresenter;
import com.example.chch2.baseapp.base.view.BaseFragment;

public class FourFragment extends BaseFragment {
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        mTitleView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_four;
    }

    @Override
    protected int getTitleId() {
        return R.layout.layout_four_title;
    }
}
