package com.example.lims;

import android.app.Application;
import android.content.Context;

import com.example.lims.utils.ToastUtils;

public class MyApplication extends Application {

    // 全局上下文
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        ToastUtils.init(this);
    }

    //获取全局的上下文
    public static Context getContext() {
        return mContext;
    }
}
