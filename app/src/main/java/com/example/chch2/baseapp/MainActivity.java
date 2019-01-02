package com.example.chch2.baseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chch2.baseapp.base.api.ApiMannger;
import com.example.chch2.baseapp.base.bean.BaseEntity;
import com.example.chch2.baseapp.base.inter.ApiCallBack;
import com.example.chch2.baseapp.base.manager.AppManager;
import com.example.chch2.baseapp.base.manager.TitleManager;
import com.example.chch2.baseapp.base.presenter.BasePresenter;
import com.example.chch2.baseapp.base.view.BaseActivity;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tv_main)
    TextView tv_main;


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        TitleManager.showDefaultTitle(this,"这是主页");
        tv_main.setOnClickListener(this);
    }

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_main:
                Map<String ,String> map = new HashMap<>();
                map.put("status","1");
                getHighwayconditon(map, new ApiCallBack<List<JsonObject>>() {
                    @Override
                    public void onSucc(List<JsonObject> data) {
                        Toast.makeText(mContext,"onsucc",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFail() {
                        Toast.makeText(mContext,"fail",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onMessage(int code, String message) {
                        Toast.makeText(mContext,"message",Toast.LENGTH_LONG).show();
                    }
                });

                break;
        }
    }

    public void getHighwayconditon(final Map<String, String> params, final ApiCallBack<List<JsonObject>> callBack) {
        ApiMannger.getHighwayconditon(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseEntity<List<JsonObject>>>() {
                    @Override
                    public void call(BaseEntity<List<JsonObject>> industryEntityBaseEntity) {
                        if (industryEntityBaseEntity.getCode() == 1000) {
                            callBack.onSucc(industryEntityBaseEntity.getData());
                        } else {
                            callBack.onMessage(industryEntityBaseEntity.getCode(), industryEntityBaseEntity.getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callBack.onFail();
                    }
                });
    }
}
