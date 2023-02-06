package com.example.lims.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.databinding.FragmentReportBinding;
import com.example.lims.view.base.BaseLoginFragment;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/13 17:03
 * Describe: 其他报备二级页面
 */
public class ReportFragment extends BaseLoginFragment<FragmentReportBinding> {

    private FragmentReportBinding binding;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentReportBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        binding.tv2.setText(getBundle());
    }

    @Override
    public void initEvent() {

    }

    private String getBundle() {
        Bundle bundle = getArguments();
        return bundle.getString("lab_number");
    }
}
