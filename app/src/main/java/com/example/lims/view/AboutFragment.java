package com.example.lims.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lims.databinding.FragmentAboutBinding;
import com.example.lims.view.base.BaseFragment;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/2/17 17:11
 * Describe:  关于
 */
public class AboutFragment extends BaseFragment<FragmentAboutBinding> {

    FragmentAboutBinding binding;

    @Override
    protected FragmentAboutBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {

    }

    @Override
    public void initEvent() {

    }
}
