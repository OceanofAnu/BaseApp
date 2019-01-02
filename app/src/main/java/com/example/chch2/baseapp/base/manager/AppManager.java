/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：AppManager.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-8-6
 */
package com.example.chch2.baseapp.base.manager;

import android.app.Activity;
import android.content.Context;
import android.os.UserManager;


import java.util.Stack;

//import net.nineninelu.playticketbar.login.view.impl.LoginHomeActivity;

/**
 * @author panjichang
 *         应用程序Activity管理类：用于Activity管理和应用程序退出
 * @date 2014-8-6 下午6:04:25
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;
    private static Stack<Activity> activityStackLogin;//登录专用

    private static Context mcontext;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        mcontext = activity;
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public static void finishAllActivityAndExit(Context context) {
        if (null != activityStack) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }




    //登录模块专用
    //添加Activity到容器中
    public static void addActivityLogin(Activity activity) {
        if (activityStackLogin == null) {
            activityStackLogin = new Stack<Activity>();
        }
        activityStackLogin.add(activity);
    }

    //遍历所有activity并调用finish()方法退出
    public static void exitLogin() {
        for (Activity ac : activityStackLogin) {
            ac.finish();
        }
        activityStackLogin.clear();
    }
}