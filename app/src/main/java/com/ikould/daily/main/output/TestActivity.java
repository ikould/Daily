package com.ikould.daily.main.output;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ikould.daily.main.config.MainConfig;

import com.ikould.daily.R;
import com.ikould.daily.main.task.ParseStrTask;

import java.io.File;


public class TestActivity extends AppCompatActivity {

    private WebView mMainWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFindId();
        initConfig();

    }

    private void initFindId() {
        // mMainWebView = (WebView) findViewById(R.id.wv_main);
    }

    private void initConfig() {
        initWebView();
    }


    private void initWebView() {
        ParseStrTask.getInstance().parseAssetsStr(this);
        mMainWebView.setWebChromeClient(new WebChromeClient());
        mMainWebView.setWebViewClient(new WebViewClient());
        WebSettings settings = mMainWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCacheMaxSize(10 * 1024 * 1024);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        String fileUrl = MainConfig.TEST_DIR + File.separator + "index.html";
        Log.d("TestActivity", "initConfig: file = " + (new File(fileUrl).exists()));
        mMainWebView.loadUrl("file:/sdcard/Android/data/com.ikould.daily/Files/Temp/test/index.html");
    }
}
