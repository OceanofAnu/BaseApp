package com.example.chch2.baseapp.base.inter;

import android.os.Bundle;

import com.example.chch2.baseapp.base.presenter.BasePresenter;



/** activity fragment 实现接口
 * Created by sysadminl on 2015/12/10
 */
public interface IBase {

    BasePresenter getPresenter();//获取关联view的presenter

   // View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);//构建view 实例

    void bindView(Bundle savedInstanceState);//进行一些初始化操作

    int getContentId();//获取view 资源文件
}
