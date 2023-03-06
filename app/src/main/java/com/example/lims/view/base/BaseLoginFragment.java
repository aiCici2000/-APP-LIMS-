package com.example.lims.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.lims.repository.SharedPreferencesUtil;
import com.example.lims.view.MainActivity;

public abstract class BaseLoginFragment<T extends ViewBinding> extends BaseFragment{
    private static final String TAG = "BaseLoginFragment";

    private MainActivity activity;
    public int userId;
    public String userNumber;
    public String userName;
    public String userEmail;
    public int userType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userNumber = (String) SharedPreferencesUtil.getData("userNumber", "-1");
        userName = (String) SharedPreferencesUtil.getData("userName", "-1");
        userId = (int) SharedPreferencesUtil.getData("userId", -1);
        userEmail = SharedPreferencesUtil.getData("email", "-1") + "";
        userType = (int) SharedPreferencesUtil.getData("type", -1);
        if (!userNumber.equals("-1") && !userName.equals("-1")) {
            setLoginFlag(true);
        } else {
            setLoginFlag(false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public boolean getLoginFlag() {
        return activity.getLoginFlag();
    }

    public void setLoginFlag(boolean loginFlag) {
        activity.setLoginFlag(loginFlag);
    }
}
