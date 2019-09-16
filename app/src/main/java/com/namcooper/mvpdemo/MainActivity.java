package com.namcooper.mvpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.namcooper.mvp_master.http.model.SplashModel;
import com.namcooper.mvp_master.mvp.MvpActivity;
import com.namcooper.mvp_master.rxbus.RxBus;
import com.namcooper.mvp_master.rxbus.event.TestEvent;

import io.reactivex.disposables.Disposable;

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MainView {

    private TextView tvTxt;

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public MainView getMvpView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTxt = findViewById(R.id.tv_txt);
        mPresenter.getData();

        //退出登录事件
        Disposable subscriber = RxBus.getInstance()
                .toObservable(TestEvent.class)
                .subscribe(event -> {
                    Toast.makeText(MainActivity.this, "收到了事件", Toast.LENGTH_SHORT).show();
                });
        addSubscriber(subscriber);
    }

    public void testMvp(View view) {
        mPresenter.showClickLog();
    }

    @Override
    public void showClickLog() {
        RxBus.getInstance().postEvent(new TestEvent());
    }

    @Override
    public void getDataSucceed(SplashModel data) {
        tvTxt.setText(data.toString());
    }

    @Override
    public void getDataFailed(String msg) {
        tvTxt.setText(msg);
    }
}
