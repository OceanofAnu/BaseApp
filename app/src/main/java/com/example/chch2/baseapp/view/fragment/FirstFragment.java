package com.example.chch2.baseapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.chch2.baseapp.R;
import com.example.chch2.baseapp.base.manager.TitleManager;
import com.example.chch2.baseapp.base.presenter.BasePresenter;
import com.example.chch2.baseapp.base.view.BaseFragment;
import com.example.chch2.baseapp.demo.DemoActivity;

import butterknife.BindView;

public class FirstFragment extends BaseFragment {
    @BindView(R.id.tv_main)
    TextView tv_main;
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        mTitleView.setVisibility(View.VISIBLE);
        tv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DemoActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_first;
    }

    @Override
    protected int getTitleId() {
        return R.layout.layout_first_title;
    }
}
