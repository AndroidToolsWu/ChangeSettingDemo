package com.alan.changesettingdemo.core;

import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by tiny on 9/14/2018.
 */
@SuppressWarnings("All")
public class HookViewClick {

    /**
     * 排除连点时间、实现Exclude接口后排除防抖动
     */
    public interface Exclude {
    }

    public static HookViewClick getInstance() {
        return UtilHolder.mHookViewClickUtil;
    }

    private static class UtilHolder {
        private static HookViewClick mHookViewClickUtil = new HookViewClick();
    }

    public static void hookView(View view) {
        if (view instanceof Exclude) return;

        try {
            Class viewClazz = Class.forName("android.view.View");
            Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);


            Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

            Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");

            if (!onClickListenerField.isAccessible()) {
                onClickListenerField.setAccessible(true);
            }
            View.OnClickListener mOnClickListener = (View.OnClickListener) onClickListenerField.get(listenerInfoObj);
            View.OnClickListener onClickListenerProxy = new OnClickListenerProxy(mOnClickListener);
            onClickListenerField.set(listenerInfoObj, onClickListenerProxy);
        } catch (Exception e) {
        }
    }

    public static void hookAllView(View view) {
        if (view instanceof Exclude) return;

        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            hookView(vp);
            for (int i = 0; i < vp.getChildCount(); i++) {
                View child = vp.getChildAt(i);
                if (child instanceof ViewGroup) {
                    hookAllView(child);
                } else {
                    hookView(child);
                }
            }

        } else {
            hookView(view);
        }
    }

    private static class OnClickListenerProxy implements View.OnClickListener {

        private View.OnClickListener object;

        private int TIME_INTERVAL = 500;

        private long mLastClickTime = 0;

        private OnClickListenerProxy(View.OnClickListener object) {
            this.object = object;
        }

        @Override
        public void onClick(View v) {
            long nowTime = System.currentTimeMillis();
            if (nowTime - mLastClickTime > TIME_INTERVAL) {
                mLastClickTime = nowTime;
                if (object != null) object.onClick(v);
            }
        }
    }
}
