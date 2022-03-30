package com.test.kotlin_test;

import android.app.Application;
import android.content.Context;

/**
 * @Author : lpf
 * @CreateDate : 2022/2/12
 * @Description :
 */
public class App extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }
}
