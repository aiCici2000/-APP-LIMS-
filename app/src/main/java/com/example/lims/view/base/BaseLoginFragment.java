package com.example.lims.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

public abstract class BaseLoginFragment<T extends ViewBinding> extends BaseFragment{

    public boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isLogin = judgeLogin();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //TODO 判断是否登录
    public boolean judgeLogin() {
        return true;
    }

}
