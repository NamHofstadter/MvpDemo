package com.namcooper.mvpdemo;

import com.namcooper.mvp_master.http.model.SplashModel;
import com.namcooper.mvp_master.mvp.MvpView;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/7
 * Copyright notice:
 */
public interface MainView extends MvpView {
    void showClickLog();

    void getDataSucceed(SplashModel data);

    void getDataFailed(String msg);
}
