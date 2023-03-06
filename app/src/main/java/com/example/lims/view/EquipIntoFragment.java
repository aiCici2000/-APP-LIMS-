package com.example.lims.view;


import static com.example.lims.utils.Constant.SUCCESS;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.adapter.LaboratorySimpleAdapter;
import com.example.lims.databinding.FragmentEquipIntoBinding;
import com.example.lims.model.bean.Equipment;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.utils.DateUtil;
import com.example.lims.utils.ToastUtils;
import com.example.lims.view.base.BaseLoginFragment;
import com.example.lims.viewmodel.EquipmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:05
 * Describe:  设备入库
 */
public class EquipIntoFragment extends BaseLoginFragment<FragmentEquipIntoBinding> {

    private static final String TAG = "EquipIntoFragment";

    private FragmentEquipIntoBinding binding;
    private List<LaboratoryData.DataBean> list = new ArrayList<>();
    private LaboratoryData.DataBean chooseItem;
    private LaboratorySimpleAdapter adapter;
    private EquipmentViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentEquipIntoBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        initList();
        initRecycleView();
        initListener();
    }

    @Override
    public void initEvent() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.et1.getText() + "";
                String number = binding.et2.getText() + "";
                String summary = binding.et3.getText() + "";
                String s = binding.tvButton.getText() + "";
                if (chooseItem == null) {
                    ToastUtils.show("请选择实验室！");
                } else if (TextUtils.isEmpty(name)) {
                    ToastUtils.show("请填写名称！");
                } else if (TextUtils.isEmpty(number)) {
                    ToastUtils.show("请填写型号！");
                } else if (TextUtils.isEmpty(s)) {
                    ToastUtils.show("请选择类型！");
                } else {
                    // 构建添加的设备
                    Equipment equipment = new Equipment();
                    equipment.setName(name);
                    equipment.setNumber(number);
                    equipment.setSummary(summary);
                    equipment.setLabId(chooseItem.getId());
                    equipment.setStatus(1);
                    equipment.setType(type);
                    equipment.setInTime(DateUtil.getTime());

                    viewModel.setEquipment(equipment);
                    MutableLiveData<Integer> addStatus = viewModel.getAddStatus();
                    addStatus.observe(EquipIntoFragment.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {
                            if (integer == SUCCESS) {
                                ToastUtils.show("添加成功！");
                                binding.et1.setText("");
                                binding.et2.setText("");
                                binding.et3.setText("");
                                binding.tvButton.setText("点击选择");
                                binding.countView.setCurrCount(1);
                            } else {
                                ToastUtils.show("添加失败！");
                            }
                        }
                    });
                    // 参数为数量，后台循环添加
                    viewModel.addEquipment(binding.countView.getCount());
                }
            }
        });
    }

    private int type = 0;
    String[] items = {"学生计算机", "教师计算机", "其他"};
    private void initListener() {
        binding.tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("请选择类型")//设置对话框的标题
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                type = which;
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.tvButton.setText(items[type]);
                                dialog.dismiss();

                            }
                        }).create();
                dialog.show();
            }
        });
    }

    private void initList() {
        viewModel = new ViewModelProvider(this).get(EquipmentViewModel.class);
        MutableLiveData<List<LaboratoryData.DataBean>> laboratoryList = viewModel.getLaboratoryList();
        laboratoryList.observe(this, new Observer<List<LaboratoryData.DataBean>>() {
            @Override
            public void onChanged(List<LaboratoryData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.requestLaboratoryList(userId);
    }

    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rv.setLayoutManager(gridLayoutManager);
        adapter = new LaboratorySimpleAdapter(list);
        adapter.setListener(new LaboratorySimpleAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                chooseItem = list.get(position);
            }
        });
        binding.rv.setAdapter(adapter);
    }
}
