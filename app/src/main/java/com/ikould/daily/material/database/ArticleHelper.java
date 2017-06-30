package com.ikould.daily.material.database;

import com.ikould.daily.material.output.bean.Article;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Article 数据库帮助类
 * <p>
 * Created by ikould on 2017/6/7.
 */

public class ArticleHelper {

    // ======== 单例 ==========
    private static ArticleHelper instance;

    public static ArticleHelper getInstance() {
        if (instance == null)
            synchronized (ArticleHelper.class) {
                if (instance == null)
                    instance = new ArticleHelper();
            }
        return instance;
    }

    private ArticleHelper() {
    }

    // ======== 操作 =======

    /**
     * 插入
     */
    public void insert(Article article) {
        // 当前业务 -> 若有 playId path 相同的则不插入
        Article same = DataSupport.where("title = ?", article.getTitle()).findFirst(Article.class);
        if (same == null)
            article.save();
    }

    /**
     * 查询所有
     *
     * @return 查询结果
     */
    public List <Article> findAll() {
        return DataSupport.findAll(Article.class);
    }
}
