package com.namcooper.mvp_master.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/7
 * Copyright notice:
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends AppCompatActivity{
    private CompositeDisposable mComposite;
    public P mPresenter;

    public abstract P getPresenter();

    public abstract V getMvpView();

    protected void addSubscriber(Disposable subscriber) {
        if (mComposite == null) {
            mComposite = new CompositeDisposable();
        }
        mComposite.add(subscriber);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = getPresenter();
        mPresenter.attachView(getMvpView());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mComposite != null) {
            mComposite.clear();
            mComposite = null;
        }
        mPresenter.detachView();
        mPresenter = null;
        super.onDestroy();
    }
}
