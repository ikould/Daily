package com.ikould.daily.article.output;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ikould.daily.CoreApplication;
import com.ikould.daily.R;
import com.ikould.daily.article.adapter.ArticleListAdapter;
import com.ikould.daily.article.output.listener.OnDoAnimListener;
import com.ikould.daily.material.output.MaterialManager;
import com.ikould.daily.material.output.bean.Article;
import com.ikould.frame.fragment.BaseFragment;
import com.ikould.frame.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * describe
 * Created by ikould on 2017/6/20.
 */

public class ArticleListFragment extends BaseFragment {

    @BindView(R.id.btn_test)
    ImageView    btnTest;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    private OnDoAnimListener   onDoAnimListener;
    private ArticleListAdapter articleListAdapter;
    private List <Article>     mArticleList;

    @Override
    protected void onBaseCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBaseContentView(R.layout.fragment_article_list);
        ButterKnife.bind(this, contentView);
        initView();
        initListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        initRecyclerView();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        btnTest.setOnClickListener(v -> {
            if (onDoAnimListener != null) {
                onDoAnimListener.onDoAnim();
            }
        });
        MaterialManager.getInstance().setOnInitListener(() -> {
            // 初始化完成
            Log.d("MainActivity", "initListener: 素材初始化完成");
            mArticleList = MaterialManager.getInstance().getAllArticle();
            CoreApplication.getInstance().handler.post(() -> {
                ToastUtils.show(getContext(), "素材初始化完成");
                articleListAdapter.setArticleData(mArticleList);
                articleListAdapter.notifyDataSetChanged();
            });
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        rvMain.setLayoutManager(new LinearLayoutManager(getContext()));
        articleListAdapter = new ArticleListAdapter(getContext());
        rvMain.setAdapter(articleListAdapter);
    }

    /**
     * 设置监听
     *
     * @param onDoAnimListener
     */
    public void setOnDoAnimListener(OnDoAnimListener onDoAnimListener) {
        this.onDoAnimListener = onDoAnimListener;
    }
}
