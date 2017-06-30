package com.ikould.daily.material.output;

import android.content.Context;
import android.content.Intent;

import com.ikould.daily.material.database.ArticleHelper;
import com.ikould.daily.material.output.bean.Article;
import com.ikould.daily.material.output.listener.OnMaterialInitListener;
import com.ikould.daily.material.service.MaterialService;

import java.util.List;

/**
 * 素材管理
 */
public class MaterialManager {

    private static volatile MaterialManager singleton;

    private MaterialManager() {
    }

    public static MaterialManager getInstance() {
        if (singleton == null) {
            synchronized (MaterialManager.class) {
                if (singleton == null) {
                    singleton = new MaterialManager();
                }
            }
        }
        return singleton;
    }

    // ========== 操作 ==========

    /**
     * 素材初始化
     */
    public void initMaterial(Context context) {
        Intent intent = new Intent(context, MaterialService.class);
        context.startService(intent);
    }

    /**
     * 设置初始化监听
     */
    public void setOnInitListener(OnMaterialInitListener onInitListener) {
        MaterialService.setOnMaterialInitListener(onInitListener);
    }

    /**
     * 获取所有的Article信息
     *
     * @return
     */
    public List <Article> getAllArticle() {
        return ArticleHelper.getInstance().findAll();
    }

    /**
     * 清除初始化监听
     */
    public void clearOnInitListener() {
        MaterialService.clearOnMaterialInitListener();
    }
}