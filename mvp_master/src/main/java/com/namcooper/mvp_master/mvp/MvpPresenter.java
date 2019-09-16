package com.namcooper.mvp_master.mvp;

import com.namcooper.mvp_master.http.ApiHelper;
import com.namcooper.mvp_master.http.ApiRetrofit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/7
 * Copyright notice:
 */
public class MvpPresenter<V extends MvpView> {
    private CompositeDisposable mComposite;

    private ApiHelper mApi;

    private V mView;

    void attachView(V view) {
        mView = view;
    }

    protected V getView() {
        return mView;
    }

    protected ApiHelper getApi() {
        if (mApi == null) {
            mApi = new ApiRetrofit().getApi();
        }
        return mApi;
    }

    protected void addSubscriber(ResourceSubscriber subscriber) {
        if (mComposite == null) {
            mComposite = new CompositeDisposable();
        }
        mComposite.add(subscriber);
    }

    void detachView() {
        if (mComposite != null) {
            mComposite.clear();
            mComposite = null;
        }
    }

}
