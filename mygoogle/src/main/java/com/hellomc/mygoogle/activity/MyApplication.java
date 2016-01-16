package com.hellomc.mygoogle.activity;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by hellomc on 2015/12/25.
 */
public class MyApplication extends Application {
    public static Handler mHandler;
    public static long mainThread;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mHandler = new Handler();
        mainThread = Thread.currentThread().getId();

    }
}
