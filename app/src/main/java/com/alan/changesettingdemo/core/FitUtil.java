package com.alan.changesettingdemo.core;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * android 适配方法
 * Created by tiny on 9/10/2018.
 */
public class FitUtil {
    private final static float WIDTH = 1080;
    private final static int DPI = (int) (960 / 1.5);
    private static float nativeWidth = 0f;

    /**
     * 在Activity的onCreate中调用,修改该Activity的density,即可完成适配,使用宽高直接使用设计图上px相等的dp值
     *
     * @param activity 需要改变的Activity
     */
    public static void autoFit(Activity activity) {
        if (nativeWidth == 0) {
            nativeWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
        }
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        displayMetrics.density = nativeWidth / WIDTH;
        displayMetrics.densityDpi = (int) (displayMetrics.density * 160);
    }

    public static void autoFit(Context context, WindowManager wm, boolean isPxEqualsDp) {
        if (nativeWidth == 0) {
            nativeWidth = wm.getDefaultDisplay().getWidth();
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        displayMetrics.density = isPxEqualsDp ? nativeWidth / DPI / (WIDTH / DPI) : nativeWidth / DPI;
        displayMetrics.densityDpi = (int) (displayMetrics.density * 160);
    }
}
