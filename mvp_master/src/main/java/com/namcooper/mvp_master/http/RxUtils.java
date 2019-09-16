package com.namcooper.mvp_master.http;

import com.namcooper.mvp_master.http.model.HttpBaseModel;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/9
 * Copyright notice:
 */
public class RxUtils {
    public static <T> Flowable<HttpBaseModel<T>> buildRxFlowable(Flowable<HttpBaseModel<T>> flowable) {
        return flowable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
