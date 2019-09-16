package com.namcooper.mvp_master.rxbus;


import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.processors.PublishProcessor;

public class RxBus {
    private static final String TAG = RxBus.class.getSimpleName();

    private static volatile RxBus defaultInstance;

    private PublishProcessor<Object> rxBus;

    private RxBus() {
        rxBus = PublishProcessor.create();
    }

    public static synchronized RxBus getInstance() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    public void postEvent(Object event) {
        if (this.hasSubscribers()) rxBus.onNext(event);
    }

    public <T> Flowable<T> toObservable(Class<T> type) {
        //在主线程接受事件
        return rxBus.ofType(type).observeOn(AndroidSchedulers.mainThread());
    }

    private boolean hasSubscribers() {
        return rxBus.hasSubscribers();
    }

}