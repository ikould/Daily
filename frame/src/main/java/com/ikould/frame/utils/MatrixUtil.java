package com.ikould.frame.utils;

import android.graphics.Point;

/**
 * Created by ALiu on 2017/3/22.
 */

public class MatrixUtil {

    /**
     * 判断返回左上角坐标
     *
     * @return
     */
    public static Point getLT(float[] dst) {
//        Point a = dst[]

        return null;
    }

    //冒泡排序
    public static float[] getBubbleSort(float[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//外层循环控制排序趟数
            for (int j = 0; j < arr.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
                if (arr[j] > arr[j + 1]) {
                    float temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    }
                }
           }
        return arr;
    }

}
