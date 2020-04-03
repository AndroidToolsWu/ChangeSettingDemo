package com.alan.changesettingdemo.core;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.components.support.RxFragment;

/**
 * Created by tiny on 6/19/2018.
 */
public abstract class BaseFragment<B extends ViewBinding, P extends BaseContract.Presenter> extends RxFragment implements
        BaseContract.View, View.OnTouchListener {

    protected B mBinding;
    protected P mPresenter;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = injectPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            FitUtil.autoFit(getActivity());
        }
        if (mBinding == null) {
            mBinding = injectViewBinding(inflater, container, false);
        }
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mMainHandler.postDelayed(() -> HookViewClick.hookAllView(mBinding.getRoot()), 300);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            FitUtil.autoFit(getActivity());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getActivity() != null) {
            FitUtil.autoFit(getActivity());
        }
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
    public void onStop() {
        super.onResume();
        if (getActivity() != null) {
            FitUtil.autoFit(getActivity());
        }
        super.onStop();
        mMainHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.release();
        }
    }

    protected abstract P injectPresenter();

    protected abstract B injectViewBinding(@NonNull LayoutInflater inflater,
                                           @Nullable ViewGroup parent, boolean attachToParent);

    @Override
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) throws RuntimeException {
        throw new RuntimeException("This is an fragment, can't use activity event from lifecycle");
    }
}


