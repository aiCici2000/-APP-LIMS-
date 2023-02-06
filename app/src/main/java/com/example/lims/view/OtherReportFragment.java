package com.example.lims.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.adapter.LaboratoryAdapter;
import com.example.lims.databinding.FragmentOtherReportBinding;
import com.example.lims.model.LaboratoryItem;
import com.example.lims.view.base.BaseLoginFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 15:45
 * Describe: 其他报备一级页面
 */
public class OtherReportFragment extends BaseLoginFragment<FragmentOtherReportBinding> {

    private static final String TAG = "OtherReportFragment";
    private FragmentOtherReportBinding binding;
    private final List<LaboratoryItem> list = new ArrayList<>();

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentOtherReportBinding.inflate(inflater, container, false);
        return binding;
    }


    @Override
    public void init() {
        Log.d(TAG, "init: ");
        initLaboratoryItem();
        initRecycleView();
    }

    @Override
    public void initEvent() {

    }

    private void initLaboratoryItem() {
        list.clear();
        LaboratoryItem l1 = new LaboratoryItem("信息系统与理论实验室", "南区实训楼301-303", R.drawable.kongxianzhong,76);
        list.add(l1);

        LaboratoryItem l2 = new LaboratoryItem("计算机软件与理论实验室", "南区实训楼302-304", R.drawable.kongxianzhong,81);
        list.add(l2);

        LaboratoryItem l3 = new LaboratoryItem("数字媒体技术实验室", "南区实训楼317-319", R.drawable.kongxianzhong,44);
        list.add(l3);

        LaboratoryItem l4 = new LaboratoryItem("虚拟现实技术实验室", "南区实训楼340-342", R.drawable.kongxianzhong,76);
        list.add(l4);

        LaboratoryItem l5 = new LaboratoryItem("计算机技术与应用实验室", "南区实训楼414-416", R.drawable.kongxianzhong,74);
        list.add(l5);

        LaboratoryItem l6 = new LaboratoryItem("虚拟化实验室", "南区实训楼420-422", R.drawable.kongxianzhong,22);
        list.add(l6);

        LaboratoryItem l7 = new LaboratoryItem("计算机网络原理实验室", "南区实训楼424-426", R.drawable.kongxianzhong,74);
        list.add(l7);

        LaboratoryItem l8 = new LaboratoryItem("云计算实验室", "南区实训楼430-432", R.drawable.kongxianzhong,73);
        list.add(l8);

        LaboratoryItem l9 = new LaboratoryItem("计算机多媒体技术实验室", "南区实训楼436-438", R.drawable.kongxianzhong,74);
        list.add(l9);

        LaboratoryItem l10 = new LaboratoryItem("大数据实验室", "南区实训楼442-444", R.drawable.kongxianzhong,81);
        list.add(l10);

        LaboratoryItem l11 = new LaboratoryItem("微机原理与接口技术实验室", "南区实训楼321-323", R.drawable.kongxianzhong,81);
        list.add(l11);

        LaboratoryItem l12 = new LaboratoryItem("计算机组成原理实验室", "南区实训楼348-350", R.drawable.kongxianzhong,81);
        list.add(l12);

        LaboratoryItem l13 = new LaboratoryItem("虚拟仿真实验室", "南区实训楼217-219", R.drawable.kongxianzhong,40);
        list.add(l13);

        LaboratoryItem l14 = new LaboratoryItem("大学生创新创业教室", "南区实训楼434", R.drawable.kongxianzhong,1);
        list.add(l14);

        LaboratoryItem l15 = new LaboratoryItem("计算机维修间", "南区实训楼440", R.drawable.kongxianzhong,1);
        list.add(l15);
    }

    private void initRecycleView() {
//        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
//        binding.rv.setLayoutManager(manager);
//        LaboratoryAdapter adapter = new LaboratoryAdapter(list, true);
//        adapter.setListener(new LaboratoryAdapter.ItemOnClickListener() {
//            @Override
//            public void help(int position) {
//                initItemListener(position);
//            }
//        });
//        binding.rv.setAdapter(adapter);
    }

    private void initItemListener(int position) {
        Log.d(TAG, "initItemListener: ");
        LaboratoryItem item = list.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("lab_number", item.getTopText());
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_nav_other_report_to_nav_report, bundle);
    }
}
