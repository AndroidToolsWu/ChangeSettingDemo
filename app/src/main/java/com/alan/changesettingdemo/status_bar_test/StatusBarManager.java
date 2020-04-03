package com.alan.changesettingdemo.status_bar_test;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alan
 * Date: 2020/3/9
 */
public class StatusBarManager {

    private static StatusBarManager mInstance;
    private static Context mContext;

    private StatusBarManager(){ }

    public static void init(Context context){
        mContext = context.getApplicationContext();
    }

    public static StatusBarManager getInstance() {
        if (mInstance == null){
            synchronized (StatusBarManager.class){
                if (mInstance == null){
                    mInstance = new StatusBarManager();
                }
            }
        }
        return mInstance;
    }

    public String getTimeFormat(){
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm", Locale.CHINA);
        return sdf.format(new Date(time));
    }

    public int getSignalLevel(){
        return 2;
    }

    public int getBatteryInfo(){
        return 86;
    }



}
