package com.example.chch2.baseapp;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App instance;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static App getInstance(){
        return instance;
    }
}
