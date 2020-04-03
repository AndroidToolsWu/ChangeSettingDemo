package com.alan.changesettingdemo.core;

import androidx.annotation.StringRes;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tiny on 10/16/2018.
 */
public abstract class BaseRepository {
    protected final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * 处理后台请求的错误码
     * 并切换线程
     */
    @SuppressWarnings("All")
    final protected <M> ObservableTransformer<NetModel<M>, M> applyFetchModel() {
        return applyFetchModel(true);
    }

    /**
     * @param model 网络返回数据
     * @param <M>   数据解析类型
     * @throws HttpServerException 网络异常
     */
    private <M> M handleResponse(NetModel<M> model) throws HttpServerException {
        if (200 == model.getCode()) {
            M data = model.getData();
            if (data == null) return (M) new String();
            return data;
        } else {
            throw new HttpServerException(model.getCode(), model.getMessage());
        }
    }

    /**
     * 处理后台请求的错误码
     *
     * @param isScheduler 是否进行数据切换
     * @param <M>         业务逻辑实例化数据
     */
    final protected <M> ObservableTransformer<NetModel<M>, M> applyFetchModel(boolean isScheduler) {
        return upstream -> {
            Observable<M> compose = upstream.map(this::handleResponse);
            if (isScheduler) compose = compose.compose(RxSchedulers.io());
            return compose;
        };
    }

    protected String getString(@StringRes int id) {
        return BaseApplication.getContext().getResources().getString(id);
    }
}
