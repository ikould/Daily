package com.ikould.daily.material.output.bean;

import org.litepal.crud.DataSupport;

/**
 * describe
 * Created by ikould on 2017/6/20.
 */

public class Article extends DataSupport {

    // 主键
    private int    id;
    // 题目
    private String title;
    // 类型
    private String type;
    // 时间
    private long   date;
    // 副标题（摘要）
    private String subtitle;
    // 显示的图片
    private String showImgPath;
    // 图标图片
    private String icoImgPath;
    // 文本Html路径地址
    private String contentPath;

    public Article() {
    }

    public Article(int id, String title, String type, long date, String subtitle, String showImgPath, String icoImgPath, String contentPath) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.date = date;
        this.subtitle = subtitle;
        this.showImgPath = showImgPath;
        this.icoImgPath = icoImgPath;
        this.contentPath = contentPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getShowImgPath() {
        return showImgPath;
    }

    public void setShowImgPath(String showImgPath) {
        this.showImgPath = showImgPath;
    }

    public String getIcoImgPath() {
        return icoImgPath;
    }

    public void setIcoImgPath(String icoImgPath) {
        this.icoImgPath = icoImgPath;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", subtitle='" + subtitle + '\'' +
                ", showImgPath='" + showImgPath + '\'' +
                ", icoImgPath='" + icoImgPath + '\'' +
                ", contentPath='" + contentPath + '\'' +
                '}';
    }
}
