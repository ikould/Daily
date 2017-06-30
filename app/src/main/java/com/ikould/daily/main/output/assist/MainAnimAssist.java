package com.ikould.daily.main.output.assist;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.ikould.frame.utils.ScreenUtils;

/**
 * 主界面动画协助类
 */
public class MainAnimAssist {

    /**
     * 左侧宽度占正屏幕比例
     */
    private static final float SLIDE_WIDTH_RATIO = 0.25f;

    @SuppressWarnings(value = {
            "all",
            "boxing",
            "cast",
            "dep-ann",
            "deprecation",
            "fallthrough",
            "finally",
            "hiding",
            "incomplete-switch",
            "nls",
            "null",
            "rawtypes",
            "restriction",
            "serial",
            "static-access",
            "synthetic-access",
            "unchecked",
            "unqualified-field-access",
            "unused"})
    private static volatile MainAnimAssist singleton;

    public static MainAnimAssist getInstance() {
        if (singleton == null) {
            synchronized (MainAnimAssist.class) {
                if (singleton == null) {
                    singleton = new MainAnimAssist();
                }
            }
        }
        return singleton;
    }

    private MainAnimAssist() {
    }

    // ==========  操作 ==========
    // 左侧展开宽度
    private float         mSlideWidth;
    private Activity      mActivity;
    // 左侧View
    private View          mSlideView;
    // 主View
    private View          mContentView;
    // 是否展开
    private boolean       mIsOpen;
    // 动画
    private ValueAnimator mValueAnimator;

    private void init() {
        mSlideWidth = ScreenUtils.getScreenWidth(mActivity) * SLIDE_WIDTH_RATIO;
    }

    /**
     * 绑定需要动画的View
     *
     * @param activity
     * @param slideView
     * @param contentView
     */
    public void bindView(Activity activity, View slideView, View contentView) {
        this.mActivity = activity;
        this.mSlideView = slideView;
        this.mContentView = contentView;
        init();
    }


    /**
     * 动画反转
     */
    public void doAnim() {
        if (mSlideView == null || mContentView == null)
            return;
        // 反转
        boolean isOpen = mIsOpen;
        mIsOpen = !mIsOpen;
        clearAnim();
        mValueAnimator = ValueAnimator.ofFloat(0, 1.0f);
        mValueAnimator.setDuration(500);
        mValueAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            value = isOpen ? 1 - value : value;
            value = (float) Math.sin(value * Math.PI / 2);
            Log.d("MainAnimAssist", "doAnim: value = " + value);
            float slideWidth = value * mSlideWidth;
            mContentView.setTranslationX(slideWidth);
        });
        mValueAnimator.start();
    }

    /**
     * 清除动画
     */
    private void clearAnim() {
        if (mValueAnimator != null && mValueAnimator.isRunning()) {
            mValueAnimator.cancel();
        }
        mValueAnimator = null;
    }

    /***
     * 动画是否展开
     *
     * @return
     */
    public boolean isIsOpen() {
        return mIsOpen;
    }

    /**
     * 清除所有
     */
    public void clearAll() {
        mActivity = null;
        mSlideView = null;
        mContentView = null;
        clearAnim();
        mIsOpen = false;
    }
}