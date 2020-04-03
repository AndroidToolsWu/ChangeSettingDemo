package com.alan.changesettingdemo.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tiny on 8/22/2018.
 */
public class RxSchedulers {
    private final static ExecutorService dbThreadPool = Executors.newSingleThreadExecutor();

    /**
     * 可用作网络请求
     */
    public static <T> ObservableTransformer<T, T> io() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 单线程池，可完成对程序的初始化操作
     */
    public static <T> ObservableTransformer<T, T> single() {
        return upstream -> upstream.subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 可用于对数据库的操作
     */
    public static <T> ObservableTransformer<T, T> db() {
        return upstream -> upstream.subscribeOn(Schedulers.from(dbThreadPool))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
