package com.ikould.frame.net;

import android.util.Log;

import com.ikould.frame.net.converter.ToStringConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit基础类
 * <p>
 * Created by ikould on 2017/6/2.
 */

public class RetrofitHelper {

    private static RetrofitHelper instance;
    private static final String BASE_URL = "http://www.poco.cn/";
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message ->
            Log.i("RetrofitLog", "retrofitBack = " + message));

    private RetrofitHelper() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            instance = new RetrofitHelper();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
