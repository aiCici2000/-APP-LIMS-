package com.example.lims.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.adapter.LaboratoryAdapter;
import com.example.lims.adapter.MaterialAdapter;
import com.example.lims.databinding.FragmentMaterialApplyBinding;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.model.bean.MaterialData;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.TeacherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 17:10
 * Describe:  耗材申请
 */
public class MaterialApplyFragment extends BaseDialogFragment<FragmentMaterialApplyBinding> {

    private static final String TAG = "MaterialApplyFragment";

    private FragmentMaterialApplyBinding binding;
    private final List<LaboratoryData.DataBean> labList = new ArrayList<>();
    private final List<MaterialData.DataBean> materialList = new ArrayList<>();
    private LaboratoryAdapter laboratoryAdapter;
    private MaterialAdapter materialAdapter;
    private TeacherViewModel viewModel;
    private LaboratoryData.DataBean labItem;
    private MaterialData.DataBean materialItem;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMaterialApplyBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        Log.d(TAG, "init: ");
        showLoading();
        initObserver();
        initRecycleView();
    }

    @Override
    public void initEvent() {
        binding.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText("点击选择您要申请的实验室");
                binding.tv.setTextColor(Color.parseColor("#13227a"));
                viewModel.requestAllLabList();
            }
        });
    }

    private void initRecycleView() {
        LinearLayoutManager manager1 = new LinearLayoutManager(MyApplication.getContext());
        LinearLayoutManager manager2 = new LinearLayoutManager(MyApplication.getContext());
        binding.rv1.setLayoutManager(manager1);
        binding.rv2.setLayoutManager(manager2);
        laboratoryAdapter = new LaboratoryAdapter(labList, true);
        materialAdapter = new MaterialAdapter(materialList);

        laboratoryAdapter.setListener(new LaboratoryAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                initItemListener(position);
            }
        });
        materialAdapter.setListener(new MaterialAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                materialItem = materialList.get(position);

                Bundle bundle = new Bundle();
                bundle.putString("lab_number", labItem.getNumber());
                bundle.putInt("lab_id", labItem.getId());
                bundle.putString("lab_name", labItem.getName());
                bundle.putInt("administratorId", labItem.getAdministratorId());

                bundle.putString("mat_name", materialItem.getName());
                bundle.putString("mat_other", materialItem.getOther()+"");
                bundle.putString("mat_num", materialItem.getNum());
                bundle.putInt("mat_id", materialItem.getId());
                NavController controller = Navigation.findNavController(requireView());
                controller.navigate(R.id.action_nav_material_apply_to_nav_material_apply_2, bundle);
            }
        });
        binding.rv1.setAdapter(laboratoryAdapter);
        binding.rv2.setAdapter(materialAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void initItemListener(int position) {
        labItem = labList.get(position);

        binding.tv.setText("点击重新选择");
        binding.tv.setTextColor(Color.RED);
        labList.clear();
        labList.add(labItem);
        laboratoryAdapter.setData(labList);
        binding.tv1.setVisibility(View.VISIBLE);
        binding.v1.setVisibility(View.VISIBLE);
        binding.rv2.setVisibility(View.VISIBLE);
    }

    private void initObserver() {
        viewModel = new ViewModelProvider(this).get(TeacherViewModel.class);
        viewModel.getAllLabList().observe(this, new Observer<List<LaboratoryData.DataBean>>() {
            @Override
            public void onChanged(List<LaboratoryData.DataBean> dataBeans) {
                labList.clear();
                labList.addAll(dataBeans);
                laboratoryAdapter.notifyDataSetChanged();
                dismissLoading();
            }
        });
        viewModel.requestAllLabList();

        viewModel.getMaterialList().observe(this, new Observer<List<MaterialData.DataBean>>() {
            @Override
            public void onChanged(List<MaterialData.DataBean> dataBeans) {
                materialList.clear();
                materialList.addAll(dataBeans);
                materialAdapter.notifyDataSetChanged();
                dismissLoading();
            }
        });
        viewModel.requestAllMaterial();
    }
}
