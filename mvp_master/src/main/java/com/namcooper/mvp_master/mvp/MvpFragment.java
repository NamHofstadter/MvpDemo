package com.namcooper.mvp_master.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019-09-16
 * Copyright notice:
 */
public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends Fragment {

    private CompositeDisposable mComposite;
    public P mPresenter;

    public abstract P getPresenter();

    public abstract V getMvpView();

    protected void addSubscriber(ResourceSubscriber subscriber) {
        if (mComposite == null) {
            mComposite = new CompositeDisposable();
        }
        mComposite.add(subscriber);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = getPresenter();
        mPresenter.attachView(getMvpView());
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        if (mComposite != null) {
            mComposite.clear();
            mComposite = null;
        }
        mPresenter.detachView();
        mPresenter = null;
        super.onDestroy();
    }

}
