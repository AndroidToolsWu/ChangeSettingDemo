package com.alan.changesettingdemo.core;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

/**
 * Created by tiny on 2/25/20.
 */
public abstract class BaseApplication extends MultiDexApplication {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    /**
     * 获取application上下文
     */
    public static Context getContext() {
        return mContext;
    }
}
