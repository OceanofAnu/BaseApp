package com.example.chch2.baseapp.base.inter;

/**
 * 网络访问回调接口
 * Created by gyg on 2016/8/28.
 */
public interface ApiCallBack<T>{

    void onSucc(T data);//网络访问成功

    void onFail();//网络访问失败

    void onMessage(int code, String message);//网络访问返回的code 和message

}
