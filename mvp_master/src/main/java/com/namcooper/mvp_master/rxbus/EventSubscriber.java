package com.namcooper.mvp_master.rxbus;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019-09-16
 * Copyright notice:
 */
public abstract class EventSubscriber<T> extends ResourceSubscriber<T> {

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
