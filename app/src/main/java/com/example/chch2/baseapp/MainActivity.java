package com.example.chch2.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.chch2.baseapp.base.manager.AppManager;
import com.example.chch2.baseapp.base.util.StatusBarUtil;
import com.example.chch2.baseapp.view.fragment.FirstFragment;
import com.example.chch2.baseapp.view.fragment.FourFragment;
import com.example.chch2.baseapp.view.fragment.SecondFragment;
import com.example.chch2.baseapp.view.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements View.OnClickListener
,ViewPager.OnPageChangeListener{



    @BindView(R.id.vp_main)
    ViewPager vp_main;

    @BindView(R.id.tv_first)
            TextView tv_first;
    @BindView(R.id.tv_second)
            TextView tv_second;
    @BindView(R.id.tv_third)
            TextView tv_third;
    @BindView(R.id.tv_four)
            TextView tv_four;


    FirstFragment firstFragment;
    SecondFragment secondFragment;
    ThirdFragment thirdFragment;
    FourFragment fourFragment;
    List<Fragment> fragmentList = new ArrayList<>();
    TextView[] textViews;

    private int currentTabIndex = 0;//当前fragment

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        StatusBarUtil.setStatus(this);
        initViewPager();
    }


    private void initViewPager(){
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        fourFragment = new FourFragment();

        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourFragment);
        textViews = new TextView[]{tv_first,tv_second,tv_third,tv_four};
        tv_first.setOnClickListener(this);
        tv_second.setOnClickListener(this);
        tv_third.setOnClickListener(this);
        tv_four.setOnClickListener(this);

        vp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        vp_main.setCurrentItem(currentTabIndex);
        textViews[currentTabIndex].setActivated(true);
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_main:

                break;
            case R.id.tv_first:
                clickStatus(0);
                break;
            case R.id.tv_second:
                clickStatus(1);
                break;
            case R.id.tv_third:
                clickStatus(2);
                break;
            case R.id.tv_four:
                clickStatus(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        textViews[i].setActivated(true);
        currentTabIndex = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    private void clickStatus(int index){
        for (int i = 0;i < textViews.length;i ++){
            if(i == index){
                textViews[i].setActivated(true);
            }else {
                textViews[i].setActivated(false);
            }
            currentTabIndex = index;
            vp_main.setCurrentItem(currentTabIndex);
        }

    }
}
