package com.namcooper.mvp_master.http;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/8
 * Copyright notice:
 */
public class ApiRetrofit {

    private ApiHelper mApi;

    public ApiRetrofit() {

        Interceptor clientInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                HttpUrl originalHttpUrl = originalRequest.url();
                HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                        .build();

                Request.Builder builder = originalRequest.newBuilder()
                        .url(newHttpUrl);
//                String userId = AppUtil.getLoginUserId();
//                String token = AppUtil.getLoginUserToken();
//                if (!TextUtils.isEmpty(userId)) {
//                    builder.addHeader("userId", userId);
//                }
//                if (!TextUtils.isEmpty(token)) {
//                    builder.addHeader("token", token);
//                }
//                builder.addHeader("serverVersion", Const.DEFAULT_SERVER_VERSION);
//                builder.addHeader("appVersion", AppUtil.getAppVersionName());
//                builder.addHeader("channel", AppUtil.getAndroidChannel());
//                builder.addHeader("bundleId", AppUtil.getPackgeName());
                builder.addHeader("deviceType", "0");//0-Android,1-iOS
//                builder.addHeader("checkStatus", String.valueOf(
//                        PreferenceUtil.getInt(App.getInstance(),
//                                Const.APP_STATUS, 1)));//0-默认配置,1-审核配置，无此字段默认为审核配置
                Request newRequest = builder.build();

                return chain.proceed(newRequest);
            }
        };

        /**
         * http log 打印
         */
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(clientInterceptor)
                .addInterceptor(logInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testapi.poipoi.fun/poipoi/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mApi = retrofit.create(ApiHelper.class);
    }

    public ApiHelper getApi() {
        return mApi;
    }

}
