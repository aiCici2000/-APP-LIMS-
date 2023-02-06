package com.example.lims.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.RatifyLabApplyAdapter;
import com.example.lims.databinding.FragmentRatifyLabApplyBinding;
import com.example.lims.model.RatifyLabApplyItem;
import com.example.lims.view.base.BaseLoginFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 15:50
 * Describe: 审批实验室申请
 */
public class RatifyLabApplyFragment extends BaseLoginFragment<FragmentRatifyLabApplyBinding> {
    private static final String TAG = "RatifyLabApplyFragment";

    private FragmentRatifyLabApplyBinding binding;
    private final List<RatifyLabApplyItem> list = new ArrayList<>();

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentRatifyLabApplyBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        Log.d(TAG, "init: ");
        initItem();
        initRecycleView();
    }

    @Override
    public void initEvent() {
        Log.d(TAG, "initEvent: ");

    }

    private void initItem() {
        Log.d(TAG, "initItem: ");
        list.clear();
        RatifyLabApplyItem item1 = new RatifyLabApplyItem("南区实训楼301-303", new Date(),
                2, "数据结构与算法", "李誌", 2, new Date());
        list.add(item1);
        RatifyLabApplyItem item2 = new RatifyLabApplyItem("南区实训楼301-303", new Date(),
                2, "计算机网络", "李誌", 0, new Date());
        list.add(item2);
        RatifyLabApplyItem item3 = new RatifyLabApplyItem("南区实训楼301-303", new Date(),
                2, "操作系统", "李誌", 1, new Date());
        list.add(item3);
    }

    private void initRecycleView() {
        Log.d(TAG, "initRecycleView: ");
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        RatifyLabApplyAdapter adapter = new RatifyLabApplyAdapter(list);
        binding.rv.setAdapter(adapter);
    }
}
