package com.example.lims.utils;

import android.util.Log;

/**
 * @Author：李壮
 * @Package：com.example.lims.utils
 * @Date：2023/1/16 16:35
 * Describe:
 */
public class Help {
    private static final String TAG = "Help";

    public static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        Log.d(TAG, "isFastClick: " + currentClickTime);
        Log.d(TAG, "lastClickTime: " + lastClickTime);
        if ((currentClickTime - lastClickTime) >= 50_000) {
            flag = false;
        }
        if (flag == false) {
            lastClickTime = currentClickTime;
        }
        Log.d(TAG, "lastClickTime----2: " + lastClickTime);
        return flag;
    }

}
