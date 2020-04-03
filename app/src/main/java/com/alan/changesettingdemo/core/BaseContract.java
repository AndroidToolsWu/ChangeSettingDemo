package com.alan.changesettingdemo.core;

import androidx.annotation.NonNull;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;


/**
 * Created by tiny on 10/30/2018.
 */
public class BaseContract {
    public interface View {
        void showLoading();

        void hideLoading();

        void error(Throwable throwable);

        <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) throws RuntimeException;

        <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) throws RuntimeException;
    }

    public interface Presenter {
        void release();
    }
}
