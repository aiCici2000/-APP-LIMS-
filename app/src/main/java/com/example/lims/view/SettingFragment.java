package com.example.lims.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentSettingBinding;
import com.example.lims.view.base.BaseLoginFragment;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/5 14:05
 * Describe: 设置页
 */
public class SettingFragment extends BaseLoginFragment<FragmentSettingBinding> {

    private FragmentSettingBinding binding;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding =  FragmentSettingBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
//        ((SetToorBar)getActivity()).setToorBar("设置");
    }

    @Override
    public void initEvent() {

    }

}
