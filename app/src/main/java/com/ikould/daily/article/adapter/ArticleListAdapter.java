package com.ikould.daily.article.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikould.daily.R;
import com.ikould.daily.material.output.bean.Article;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文章列表适配器
 * <p>
 * Created by ikould on 2017/6/20.
 */

public class ArticleListAdapter extends RecyclerView.Adapter <ArticleListAdapter.ArticleHolder> {

    private Context        mContext;
    private List <Article> mArticleList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    public ArticleListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 设置数据
     *
     * @param mArticleList
     */
    public void setArticleData(List <Article> mArticleList) {
        this.mArticleList = mArticleList;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article_list, parent, false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {
        holder.tvType.setText(mArticleList.get(position).getType());
        holder.tvTitle.setText(mArticleList.get(position).getTitle());
        holder.tvSubtitle.setText(mArticleList.get(position).getSubtitle());
        holder.tvDate.setText(sdf.format(new Date(mArticleList.get(position).getDate())));
        holder.ivHeadImg.setImageBitmap(BitmapFactory.decodeFile(mArticleList.get(position).getIcoImgPath()));
        holder.ivShowImg.setImageBitmap(BitmapFactory.decodeFile(mArticleList.get(position).getShowImgPath()));
    }

    @Override
    public int getItemCount() {
        return mArticleList == null ? 0 : mArticleList.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_type)
        TextView  tvType;
        @BindView(R.id.tv_date)
        TextView  tvDate;
        @BindView(R.id.iv_show_img)
        ImageView ivShowImg;
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_title)
        TextView  tvTitle;
        @BindView(R.id.tv_subtitle)
        TextView  tvSubtitle;

        ArticleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
