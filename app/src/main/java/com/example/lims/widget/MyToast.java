package com.example.lims.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lims.R;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/2/5 22:54
 * Describe: 自定义Toast, 用于网络加载提示
 */
public class MyToast extends Toast {

    private Toast toast;
    private final LVCircularRing loadingView;
    View view;

    /**
     *  自定义Toast构造
     *
     * @param context 上下文
     * @param str 提示信息
     */
    public MyToast(Context context, String str) {
        super(context);
        view = View.inflate(context, R.layout.dialog_loading, null);
        TextView textView = view.findViewById(R.id.loading_text);
        textView.setText(str);
        loadingView = view.findViewById(R.id.lvcr_loading);
        loadingView.startAnim();

        //避免toast长时间显示
        if (toast != null)
        {
            toast.cancel();
        }

        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);

        toast.setView(view);
    }

    public void closeToast() {
        if (toast != null) {
            loadingView.stopAnim();
            toast.cancel();
        }
    }

    public void showToast() {
        loadingView.startAnim();
        toast.show();
    }

}
