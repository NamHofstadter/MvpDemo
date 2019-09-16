package com.namcooper.mvpdemo;

import com.namcooper.mvp_master.http.ApiException;
import com.namcooper.mvp_master.http.HttpSubscriber;
import com.namcooper.mvp_master.http.RxUtils;
import com.namcooper.mvp_master.http.model.HttpBaseModel;
import com.namcooper.mvp_master.http.model.SplashModel;
import com.namcooper.mvp_master.mvp.MvpPresenter;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/7
 * Copyright notice:
 */
public class MainPresenter extends MvpPresenter<MainView> {

    public void showClickLog() {
        getView().showClickLog();
    }

    public void getData() {
        ResourceSubscriber subscriber = RxUtils.buildRxFlowable(getApi().getSplashCover())
                .subscribeWith(new HttpSubscriber<HttpBaseModel<SplashModel>>() {

                    @Override
                    public void onSucceed(HttpBaseModel<SplashModel> model) {
                        getView().getDataSucceed(model.getData());
                    }

                    @Override
                    public void onFailed(ApiException e) {
                        getView().getDataFailed(e.getErrMsg());
                    }
                });
        addSubscriber(subscriber);
    }
}
