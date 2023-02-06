package com.example.lims.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.EquipRepAdapter;
import com.example.lims.databinding.FragmentEquipRepManageBinding;
import com.example.lims.model.EquipRepItem;
import com.example.lims.view.base.BaseLoginFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:02
 * Describe:
 */
public class EquipRepManageFragment extends BaseLoginFragment<FragmentEquipRepManageBinding> {
    private static final String TAG = "EquipRepManageFragment";

    private FragmentEquipRepManageBinding binding;
    private final List<EquipRepItem> list = new ArrayList<>();

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentEquipRepManageBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        initList();
        initRecycleView();
    }

    @Override
    public void initEvent() {

    }

    private void initList() {
        list.clear();
        EquipRepItem item1 = new EquipRepItem(0, "h", "南区实训楼301-303");
        list.add(item1);
        EquipRepItem item2 = new EquipRepItem(1, "学生计算机", "南区实训楼301-303");
        list.add(item2);
        EquipRepItem item3 = new EquipRepItem(1, "教师计算机", "南区实训楼301-303");
        list.add(item3);
        EquipRepItem item4 = new EquipRepItem(0, "h", "南区实训楼442-444");
        list.add(item4);
        EquipRepItem item5 = new EquipRepItem(1, "学生计算机", "南区实训楼442-444");
        list.add(item5);
        EquipRepItem item6 = new EquipRepItem(1, "教师计算机", "南区实训楼442-444");
        list.add(item6);
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        EquipRepAdapter adapter = new EquipRepAdapter(list);
        binding.rv.setAdapter(adapter);
    }

}
