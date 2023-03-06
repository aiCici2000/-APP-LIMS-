package com.example.lims.view.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.lims.view.MainActivity;
import com.example.lims.widget.MyToast;

/**
 * @Author：李壮
 * @Package：com.example.lims.view.base
 * @Date：2023/2/3 15:08
 * Describe:
 */
public abstract class BaseDialogFragment<T extends ViewBinding> extends BaseLoginFragment{

    private MyToast myToast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        myToast = activity.myToast;
    }

    /**
     * 显示加载框
     */
    public void showLoading() {
        if (myToast != null) {
            myToast.showToast();
        }
    }

    /**
     * 隐藏加载框
     */
    public void dismissLoading() {
        if (myToast != null) {
            myToast.closeToast();
        }
    }
}
