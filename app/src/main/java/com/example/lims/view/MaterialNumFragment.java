package com.example.lims.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.MaterialAdapter;
import com.example.lims.databinding.FragmentMaterialNumBinding;
import com.example.lims.model.MaterialItem;
import com.example.lims.view.base.BaseLoginFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:12
 * Describe:
 */
public class MaterialNumFragment extends BaseLoginFragment {

    private FragmentMaterialNumBinding binding;
    private final List<MaterialItem> list = new ArrayList<>();

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMaterialNumBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        initList();
        initAdapter();
    }

    @Override
    public void initEvent() {

    }

    private void initList() {
        MaterialItem item1 = new MaterialItem("超五类双绞线", 50, "");
        list.add(item1);
        MaterialItem item2 = new MaterialItem("5号充电电池", 40, "3000毫安以上");
        list.add(item2);
        MaterialItem item3 = new MaterialItem("7号充电电池", 40, "1000毫安以上");
        list.add(item3);
        MaterialItem item4 = new MaterialItem("5号电池充电器", 2, "带充满控制");
        list.add(item4);
        MaterialItem item5 = new MaterialItem("中性笔", 150, "红黑两色");
        list.add(item5);
        MaterialItem item6 = new MaterialItem("条扫", 20, "");
        list.add(item6);
        MaterialItem item7 = new MaterialItem("撮子", 10, "");
        list.add(item7);
        MaterialItem item8 = new MaterialItem("拖布", 20, "");
        list.add(item8);
        MaterialItem item9 = new MaterialItem("6类水晶头", 100, "维修用");
        list.add(item9);
        MaterialItem item10 = new MaterialItem("6类双绞线", 50, "维修用");
        list.add(item10);
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        MaterialAdapter adapter = new MaterialAdapter(list);
        binding.rv.setAdapter(adapter);
    }
}
