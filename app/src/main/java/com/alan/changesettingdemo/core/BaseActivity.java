package com.alan.changesettingdemo.core;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

public abstract class BaseActivity<B extends ViewBinding, P extends BasePresenter> extends
        RxAppCompatActivity implements BaseContract.View {

    protected B mBinding;
    protected P mPresenter;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FitUtil.autoFit(this);
        // 屏幕常量
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mBinding = injectViewBinding(LayoutInflater.from(this));
        setContentView(mBinding.getRoot());
        mPresenter = injectPresenter();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FitUtil.autoFit(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainHandler.postDelayed(() -> HookViewClick.hookAllView(mBinding.getRoot()), 300);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void error(Throwable throwable) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.release();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FitUtil.autoFit(this);
    }

    protected abstract P injectPresenter();

    protected abstract B injectViewBinding(@NonNull LayoutInflater inflater);

    @Override
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) throws RuntimeException {
        throw new RuntimeException("This is an activity, can't use fragment event from lifecycle");
    }
}
