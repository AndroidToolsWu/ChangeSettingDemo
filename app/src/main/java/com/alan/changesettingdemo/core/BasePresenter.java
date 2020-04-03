package com.alan.changesettingdemo.core;


import androidx.annotation.StringRes;

@SuppressWarnings({"UnusedReturnValue", "ResultOfMethodCallIgnored"})
public class BasePresenter<V extends BaseContract.View> extends BaseRepository implements BaseContract.Presenter {

    protected V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    protected String getString(@StringRes int id) {
        return BaseApplication.getContext().getResources().getString(id);
    }

    public void release() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
