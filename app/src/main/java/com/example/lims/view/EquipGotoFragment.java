package com.example.lims.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentEquipGotoBinding;
import com.example.lims.view.base.BaseLoginFragment;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:08
 * Describe:
 */
public class EquipGotoFragment extends BaseLoginFragment {

    private FragmentEquipGotoBinding binding;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentEquipGotoBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {

    }

    @Override
    public void initEvent() {

    }
}
