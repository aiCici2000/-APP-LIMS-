package com.example.lims.view;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.adapter.EquipmentAdapter;
import com.example.lims.adapter.LaboratorySimpleAdapter;
import com.example.lims.databinding.FragmentEquipGotoBinding;
import com.example.lims.model.bean.EquipmentData;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.repository.SharedPreferencesUtil;
import com.example.lims.view.base.BaseLoginFragment;
import com.example.lims.viewmodel.EquipmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:08
 * Describe:  设备出库
 */
public class EquipGotoFragment extends BaseLoginFragment {

    private static final String TAG = "EquipGotoFragment";

    private FragmentEquipGotoBinding binding;
    private List<LaboratoryData.DataBean> labList = new ArrayList<>();
    private List<EquipmentData.DataBean> equipList = new ArrayList<>();
    private EquipmentViewModel viewModel;
    private LaboratoryData.DataBean chooseItem;
    private LaboratorySimpleAdapter labAdapter;
    private EquipmentAdapter equipmentAdapter;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentEquipGotoBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        labAdapter = new LaboratorySimpleAdapter(labList);
        equipmentAdapter = new EquipmentAdapter(equipList);
        initList();
        initRecycleView();
    }

    @Override
    public void initEvent() {

    }

    private void initList() {
        viewModel = new ViewModelProvider(this).get(EquipmentViewModel.class);
        MutableLiveData<List<EquipmentData.DataBean>> equipmentList = viewModel.getEquipmentList();
        equipmentList.observe(this, new Observer<List<EquipmentData.DataBean>>() {
            @Override
            public void onChanged(List<EquipmentData.DataBean> dataBeans) {
                equipList.clear();
                equipList.addAll(dataBeans);
                equipmentAdapter.notifyDataSetChanged();
            }
        });
        MutableLiveData<List<LaboratoryData.DataBean>> laboratoryList = viewModel.getLaboratoryList();
        laboratoryList.observe(this, new Observer<List<LaboratoryData.DataBean>>() {
            @Override
            public void onChanged(List<LaboratoryData.DataBean> dataBeans) {
                labList.clear();
                labList.addAll(dataBeans);
                labAdapter.notifyDataSetChanged();
            }
        });

        viewModel.requestLaboratoryList(userId);
    }

    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rv.setLayoutManager(gridLayoutManager);
        labAdapter.setListener(new LaboratorySimpleAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                chooseItem = labList.get(position);
                if (chooseItem != null) {
                    viewModel.requestEquipmentList(chooseItem.getId());
                }
            }
        });
        binding.rv.setAdapter(labAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rv2.setLayoutManager(layoutManager);
        equipmentAdapter.setListener(new EquipmentAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("确定要将设备出库吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.deleteEquipment(equipList.get(position).getId(), chooseItem.getId());
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });
        binding.rv2.setAdapter(equipmentAdapter);
    }
}
