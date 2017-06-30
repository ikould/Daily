package com.ikould.daily;

import android.os.Handler;

import com.ikould.frame.application.BaseApplication;
import com.ikould.frame.config.BaseAppConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * describe
 * Created by ikould on 2017/6/19.
 */

public class CoreApplication extends BaseApplication {

    public Handler  handler  = new Handler();
    public Executor executor = Executors.newFixedThreadPool(5);

    private static CoreApplication instance;

    public static CoreApplication getInstance() {
        return instance;
    }

    @Override
    protected void onBaseCreate() {
        // SharedPreference 初始化
        instance = this;
        BaseAppConfig.getInstance().init(instance);
    }
}
