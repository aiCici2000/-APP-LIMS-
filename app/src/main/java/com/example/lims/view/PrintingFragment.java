package com.example.lims.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentPrintingBinding;
import com.example.lims.view.base.BaseLoginFragment;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:39
 * Describe:
 */
public class PrintingFragment extends BaseLoginFragment {

    private FragmentPrintingBinding binding;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentPrintingBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {

    }

    @Override
    public void initEvent() {

    }
}
