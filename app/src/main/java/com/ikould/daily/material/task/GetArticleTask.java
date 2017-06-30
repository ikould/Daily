package com.ikould.daily.material.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.ikould.frame.config.BaseAppConfig;
import com.ikould.frame.utils.BitmapUtil;
import com.ikould.frame.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetArticleTask {

    /**
     * 图标的大小
     */
    private static final int ICO_WIDTH    = 160;
    /**
     * 图标和图片间距
     */
    private static final int OFFSET_ICO   = 20;
    /**
     * 阴影的宽度
     */
    private static final int SHADE_WIDTH  = 7;
    /**
     * 边宽度
     */
    private static final int STROKE_WIDTH = SHADE_WIDTH + 10;

    private static volatile GetArticleTask singleton;

    private GetArticleTask() {
    }

    public static GetArticleTask getInstance() {
        if (singleton == null) {
            synchronized (GetArticleTask.class) {
                if (singleton == null) {
                    singleton = new GetArticleTask();
                }
            }
        }
        return singleton;
    }

    // ========= 操作 =========

    /**
     * 初始化素材
     */
    public void initMaterialTest(Context context) {
        long time1 = System.currentTimeMillis();
        try {
            // Ico
            moveIcoImg(context);
            Log.d("GetArticleTask", "initMaterialTest: time = " + (System.currentTimeMillis() - time1));
            // Show Img
            moveShowImg(context);
            // Html
            moveHtml(context);
        } catch (IOException e) {
            Log.d("GetArticleTask", "initMaterialTest: e = " + e);
            e.printStackTrace();
        }
    }

    /**
     * 移动Ico图片到临时文件夹
     *
     * @param context
     * @throws IOException
     */
    private void moveIcoImg(Context context) throws IOException {
        InputStream inputStream = context.getAssets().open("default/ic_head_default.png");
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        bitmap = BitmapUtil.makeRoundCorner(bitmap, ICO_WIDTH);
        BitmapUtil.saveBitmap(bitmap, BaseAppConfig.TEMP_DIR, "ico.png", Bitmap.CompressFormat.PNG);
    }

    /**
     * 移动Ico图片到临时文件夹
     *
     * @param context
     * @throws IOException
     */
    private void moveShowImg(Context context) throws IOException {
        InputStream inputStream = context.getAssets().open("default/img_article_default.png");
        FileUtil.copyFile(inputStream, "showImg.png", BaseAppConfig.TEMP_DIR);
       /* Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        bitmap = drawShowPicFrame(bitmap);
        BitmapUtil.saveBitmap(bitmap, BaseAppConfig.TEMP_DIR, "showImg.png", Bitmap.CompressFormat.PNG);*/
    }


    /**
     * 画缺一个角，同时描边的Bitmap
     *
     * @return
     */
    private Bitmap drawShowPicFrame(Bitmap bitmap) {
        Log.d("GetArticleTask", "drawShowPicFrame: bitmap.getWidth = " + bitmap.getWidth() + " bitmap.getHeight() = " + bitmap.getHeight());
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rect, rect, new Paint(Paint.ANTI_ALIAS_FLAG));
        // 画白线
        output = drawFrame(canvas, output, STROKE_WIDTH, 0xffffffff);
        // 画阴影
        output = drawFrame(canvas, output, SHADE_WIDTH, 0xffdddddd);
        // 切掉上方圆弧
        output = drawCutArc(rect, output);
        return output;
    }

    /**
     * 画边线
     *
     * @param canvas
     * @param bitmap
     * @param strokeSize 边线大小
     * @param color      边线颜色
     * @return
     */
    private Bitmap drawFrame(Canvas canvas, Bitmap bitmap, int strokeSize, int color) {
        // 线条的Paint
        Paint linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(color);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(strokeSize);
        // 半径
        float r = ICO_WIDTH / 2 + OFFSET_ICO + strokeSize / 2;
        Log.d("GetArticleTask", "drawFrame: r = " + r);
        // XY启始位置
        float startXY = (float) ((2 / Math.sqrt(3) + 1 / 2) * r);
        // 弧度
        float top = -(r / 4 + r / 2);
        float left = -(r / 4 + r / 2);
        RectF lineRectF = new RectF(0, 0, 2 * r, 2 * r);
        lineRectF.offset(left, top);
        canvas.drawArc(lineRectF, -30, 150, false, linePaint);
        // 左侧
        canvas.drawLine(strokeSize / 2, startXY, strokeSize / 2, bitmap.getHeight(), linePaint);
        // 顶部
        canvas.drawLine(startXY, strokeSize / 2, bitmap.getWidth(), strokeSize / 2, linePaint);
        // 右侧
        canvas.drawLine(bitmap.getWidth() - strokeSize / 2, strokeSize, bitmap.getWidth() - strokeSize / 2, bitmap.getHeight(), linePaint);
        // 底部
        canvas.drawLine(strokeSize, bitmap.getHeight() - strokeSize / 2, bitmap.getWidth() - strokeSize, bitmap.getHeight() - strokeSize / 2, linePaint);
        return bitmap;
    }

    /**
     * 切掉左侧圆弧
     *
     * @param bitmap
     */
    private Bitmap drawCutArc(Rect rect, Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        // === 圆弧 ===
        // 半径
        float r = ICO_WIDTH / 2 + OFFSET_ICO;
        // 弧度
        float top = -(r / 4 + r / 2);
        float left = -(r / 4 + r / 2);
        RectF rectF = new RectF(0, 0, 2 * r, 2 * r);
        rectF.offset(left, top);
        canvas.drawArc(rectF, 0, 360, false, paint);
        // 设置Paint为切
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * Asset下测试数据
     *
     * @param context
     */
    private void moveHtml(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("default/testHtmlStr");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            String strResult = stringBuilder.toString();
            // 将字符串转换为html文件
            File file = new File(BaseAppConfig.TEMP_DIR + File.separator + "article.html");
            if (file.exists())
                return;
            File dirFile = new File(BaseAppConfig.TEMP_DIR);
            if (!dirFile.exists()) {
                boolean dirSuccess = dirFile.mkdirs();
                Log.d("ParseStrTask", "parseStrToHtml: dirSuccess = " + dirSuccess);
                if (!dirSuccess)
                    return;
            }
            FileOutputStream fileOut = new FileOutputStream(file);
            byte[] strBytes = strResult.getBytes();
            fileOut.write(strBytes);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}