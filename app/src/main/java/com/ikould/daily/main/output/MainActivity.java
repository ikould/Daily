package com.ikould.daily.main.output;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.ikould.daily.R;
import com.ikould.daily.article.output.ArticleListFragment;
import com.ikould.daily.main.output.assist.MainAnimAssist;
import com.ikould.daily.material.output.MaterialManager;
import com.ikould.daily.set.output.SetFragment;
import com.ikould.frame.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * describe
 * Created by ikould on 2017/6/20.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_main_slide)
    FrameLayout flMainSet;
    @BindView(R.id.fl_main_content)
    FrameLayout flMainContent;

    // 设置界面
    private SetFragment         mSetFragment;
    // 文章列表
    private ArticleListFragment mArticleListFragment;

    @Override
    protected void onBaseCreate(Bundle savedInstanceState) {
        setBaseContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initConfig();
        initView();
        initListener();
    }

    private void initConfig() {
        MaterialManager.getInstance().initMaterial(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        initFragment();
        MainAnimAssist.getInstance().bindView(this, flMainSet, flMainContent);
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        mArticleListFragment.setOnDoAnimListener(() -> {
            MainAnimAssist.getInstance().doAnim();
        });
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        mSetFragment = new SetFragment();
        mArticleListFragment = new ArticleListFragment();
        replaceFragment(R.id.fl_main_slide, mSetFragment, null);
        replaceFragment(R.id.fl_main_content, mArticleListFragment, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainAnimAssist.getInstance().clearAll();
    }
}
