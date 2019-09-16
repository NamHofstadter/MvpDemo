package com.namcooper.mvp_master.http;

import com.namcooper.mvp_master.http.model.HttpBaseModel;
import com.namcooper.mvp_master.http.model.SplashModel;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/8
 * Copyright notice:
 */
public interface ApiHelper {

    @GET("appconfig/cover")
    Flowable<HttpBaseModel<SplashModel>> getSplashCover();
}
