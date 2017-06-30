package com.ikould.daily.main.task;

import android.content.Context;
import android.util.Log;

import com.ikould.daily.main.config.MainConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 解析Str字符串为html
 */
public class ParseStrTask {

    private static volatile ParseStrTask singleton;

    private ParseStrTask() {
    }

    public static ParseStrTask getInstance() {
        if (singleton == null) {
            synchronized (ParseStrTask.class) {
                if (singleton == null) {
                    singleton = new ParseStrTask();
                }
            }
        }
        return singleton;
    }

    /**
     * Asset下测试数据
     *
     * @param context
     */
    public void parseAssetsStr(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("default/testHtmlStr");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            String strResult = stringBuilder.toString();
            parseStrToHtml(strResult, "index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串转换为html文件
     *
     * @param strContent
     * @param fileName
     */
    public void parseStrToHtml(String strContent, String fileName) throws IOException {
        File file = new File(MainConfig.TEST_DIR + File.separator + fileName);
        if (file.exists())
            return;
        File dirFile = new File(MainConfig.TEST_DIR);
        if (!dirFile.exists()) {
            boolean dirSuccess = dirFile.mkdirs();
            Log.d("ParseStrTask", "parseStrToHtml: dirSuccess = " + dirSuccess);
            if (!dirSuccess)
                return;
        }
        FileOutputStream fileOut = new FileOutputStream(file);
        byte[] strBytes = strContent.getBytes();
        fileOut.write(strBytes);
        fileOut.close();
    }
}








