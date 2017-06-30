package com.ikould.daily.material.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.ikould.daily.material.database.ArticleHelper;
import com.ikould.daily.material.output.bean.Article;
import com.ikould.daily.material.output.listener.OnMaterialInitListener;
import com.ikould.daily.material.task.GetArticleTask;
import com.ikould.frame.config.BaseAppConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * describe
 * Created by ikould on 2017/6/20.
 */

public class MaterialService extends IntentService {

    private static boolean isInitFinish;
    // 是否失败
    private        boolean isWorkFail;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MaterialService() {
        super("MaterialService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        isInitFinish = false;
        isWorkFail = false;
        GetArticleTask.getInstance().initMaterialTest(this);
        List <Article> articleList = testMsg();
        insertDB(articleList);
        materialInitComplete();
    }

    /**
     * 测试数据
     *
     * @return
     */
    private List <Article> testMsg() {
        List <Article> articles = new ArrayList <>();
        for (int i = 0; i < 2; i++) {
            Article article = new Article(0, "shanghai baozha le", "标签1", new Date().getTime(), "副标题",
                    BaseAppConfig.TEMP_DIR + File.separator + "showImg.png",
                    BaseAppConfig.TEMP_DIR + File.separator + "ico.png",
                    BaseAppConfig.TEMP_DIR + File.separator + "article.html");
            articles.add(article);
        }
        return articles;
    }

    /**
     * 插入到数据库中
     */
    public void insertDB(List <Article> articles) {
        for (Article article : articles) {
            ArticleHelper.getInstance().insert(article);
        }
    }

    /**
     * 素材初始化完成
     */
    private void materialInitComplete() {
       /* if (!isWorkFail) {
            // 解析完成，设置sp，防止第二次启动继续解析
            // MaterialConfig.getInstance().setInitMaterial(true);
        }*/
        isInitFinish = true;
        // 监听
        if (onInitListener != null)
            onInitListener.onInitComplete();
    }

    // 监听
    private static OnMaterialInitListener onInitListener;

    public static void setOnMaterialInitListener(OnMaterialInitListener onMaterialInitListener) {
        if (isInitFinish) {
            onMaterialInitListener.onInitComplete();
            return;
        }
        onInitListener = onMaterialInitListener;
    }

    public static void clearOnMaterialInitListener() {
        onInitListener = null;
    }
}
