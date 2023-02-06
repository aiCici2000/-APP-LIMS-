package com.example.lims.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentMessageBinding;
import com.example.lims.view.base.BaseLoginFragment;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/4 14:05
 * Describe: 消息页
 */
public class MessageFragment extends BaseLoginFragment<FragmentMessageBinding> {

    private FragmentMessageBinding binding;


    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMessageBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
//        binding.toolbar.setTitle("");
//        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
//        ((SetToorBar)getActivity()).setToorBar("消息");
    }

    @Override
    public void initEvent() {

    }

}
