package com.example.chch2.baseapp.base.presenter;



/**
 * Created by gyg on 2016/8/28.
 */
public abstract class BasePresenter<T extends IBaseView>{

    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }//关联view

    public void detachView() {//解除关联view
        if (mView != null) {
            mView = null;
        }
    }

}
