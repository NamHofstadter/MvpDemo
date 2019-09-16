package com.namcooper.mvp_master.http;

import com.namcooper.mvp_master.http.model.HttpBaseModel;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/9
 * Copyright notice:
 */
public abstract class HttpSubscriber<T extends HttpBaseModel> extends ResourceSubscriber<T> {

    private static final int STATUS_OK = 200;

    private static final int STATUS_NET_CONNECT_FAILED = -100;

    @Override
    public void onNext(T t) {
        int status = t.getStatus();
        if (status == STATUS_OK) {
            onSucceed(t);
        } else {
            // 此处可以取做一些特殊的错误码的处理逻辑
            ApiException e = new ApiException(status, t.getMessage());
            onFailed(e);
        }
    }

    @Override
    public void onError(Throwable e) {
        ApiException ex = new ApiException(STATUS_NET_CONNECT_FAILED, e.getMessage());
        onFailed(ex);
    }

    @Override
    public void onComplete() {

    }


    //*************自定义回调实现*****************
    public abstract void onSucceed(T t);

    public abstract void onFailed(ApiException e);

}
