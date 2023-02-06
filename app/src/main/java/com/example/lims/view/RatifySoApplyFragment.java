package com.example.lims.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.RatifySoApplyAdapter;
import com.example.lims.databinding.FragmentRatifySoApplyBinding;
import com.example.lims.model.RatifySoApplyItem;
import com.example.lims.view.base.BaseLoginFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 15:58
 * Describe:
 */
public class RatifySoApplyFragment extends BaseLoginFragment<FragmentRatifySoApplyBinding> {
    private static final String TAG = "RatifySoApplyFragment";

    private FragmentRatifySoApplyBinding binding;
    private final List<RatifySoApplyItem> list = new ArrayList<>();

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentRatifySoApplyBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        initItem();
        initRecycleView();
    }

    @Override
    public void initEvent() {

    }

    private void initItem() {
        list.clear();
        RatifySoApplyItem item1 = new RatifySoApplyItem("南区实训楼301-303", "Eclipse", "李誌", new Date(), 2);
        list.add(item1);
        RatifySoApplyItem item2 = new RatifySoApplyItem("南区实训楼301-303", "Eclipse", "李誌", new Date(), 1);
        list.add(item2);
        RatifySoApplyItem item3 = new RatifySoApplyItem("南区实训楼301-303", "Eclipse", "李誌", new Date(), 0);
        list.add(item3);
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        RatifySoApplyAdapter adapter = new RatifySoApplyAdapter(list);
        binding.rv.setAdapter(adapter);
    }
}
