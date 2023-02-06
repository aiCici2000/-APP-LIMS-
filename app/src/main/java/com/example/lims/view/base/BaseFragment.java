package com.example.lims.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<T extends ViewBinding> extends Fragment {

    protected T viewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = getViewBinding( inflater, container);
        View view = viewBinding.getRoot();
        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initEvent();
        event();
    }

    protected abstract T getViewBinding(LayoutInflater inflater, ViewGroup container);

    public abstract void init();

    public abstract void initEvent();

    public void event() {
    }

}
