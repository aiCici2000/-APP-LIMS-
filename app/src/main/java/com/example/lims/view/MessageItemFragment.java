package com.example.lims.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.adapter.MessageAdapter;
import com.example.lims.databinding.FragmentMessageItemBinding;
import com.example.lims.model.bean.MessageData;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.MessageViewModel;
import com.example.lims.widget.MessageItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/2/20 9:25
 * Describe:  消息详情页
 */
public class MessageItemFragment extends BaseDialogFragment<FragmentMessageItemBinding> {

    private FragmentMessageItemBinding binding;
    private List<MessageData.DataBean> list = new ArrayList<>();
    private MessageAdapter adapter;
    private MessageViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMessageItemBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        Bundle bundle = getArguments();
        int itemType = bundle.getInt("itemType");
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        initRV();
        initObserver();
        viewModel.requestMessage(userId, itemType);
    }

    @Override
    public void initEvent() {

    }

    private void initObserver() {
        viewModel.getMessageData().observe(this, new Observer<List<MessageData.DataBean>>() {
            @Override
            public void onChanged(List<MessageData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initRV() {
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        binding.rv.setLayoutManager(manager);
        adapter = new MessageAdapter(list);
        binding.rv.setAdapter(adapter);
        MessageItemDecoration decoration = new MessageItemDecoration();
        binding.rv.addItemDecoration(decoration);
    }
}
