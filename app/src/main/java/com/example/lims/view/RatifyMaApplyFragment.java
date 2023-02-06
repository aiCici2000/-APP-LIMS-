package com.example.lims.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.RatifyMaApplyAdapter;
import com.example.lims.databinding.FragmentRatifyMaApplyBinding;
import com.example.lims.model.RatifyMaApplyItem;
import com.example.lims.view.base.BaseLoginFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 15:55
 * Describe:
 */
public class RatifyMaApplyFragment extends BaseLoginFragment<FragmentRatifyMaApplyBinding> {

    private static final String TAG = "RatifyMaApplyFragment";

    private FragmentRatifyMaApplyBinding binding;
    private final List<RatifyMaApplyItem> list = new ArrayList<>();

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentRatifyMaApplyBinding.inflate(inflater, container, false);
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

    }

    private void initItem() {
        Log.d(TAG, "initItem: ");
        list.clear();
        RatifyMaApplyItem item1 = new RatifyMaApplyItem("5类水晶头", 20, "李誌", new Date(), 0);
        list.add(item1);
        RatifyMaApplyItem item2 = new RatifyMaApplyItem("5类水晶头", 20, "李誌", new Date(), 1);
        list.add(item2);
        RatifyMaApplyItem item3 = new RatifyMaApplyItem("5类水晶头", 20, "李誌", new Date(), 2);
        list.add(item3);
    }

    private void initRecycleView() {
        Log.d(TAG, "initRecycleView: ");
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        RatifyMaApplyAdapter adapter = new RatifyMaApplyAdapter(list);
        binding.rv.setAdapter(adapter);
    }
}
