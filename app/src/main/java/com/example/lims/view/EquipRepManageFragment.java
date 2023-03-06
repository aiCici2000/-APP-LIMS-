package com.example.lims.view;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.EquipRepAdapter;
import com.example.lims.databinding.FragmentEquipRepManageBinding;
import com.example.lims.model.bean.EquipmentRepData;
import com.example.lims.utils.DateUtil;
import com.example.lims.view.base.BaseLoginFragment;
import com.example.lims.viewmodel.EquipmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:02
 * Describe:  设备维修管理
 */
public class EquipRepManageFragment extends BaseLoginFragment<FragmentEquipRepManageBinding> {
    private static final String TAG = "EquipRepManageFragment";

    private FragmentEquipRepManageBinding binding;
    private final List<EquipmentRepData.DataBean> list = new ArrayList<>();
    private EquipmentViewModel viewModel;
    EquipmentRepData.DataBean item;
    private EquipRepAdapter adapter;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentEquipRepManageBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        viewModel = new ViewModelProvider(this).get(EquipmentViewModel.class);
        initObserver();
        viewModel.requestEquipmentRepList(userId);
        initRecycleView();
    }

    @Override
    public void initEvent() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(requireContext())
                        .setTitle("确定完成维修吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.repairEquipment(item.getId());
                                dialog.dismiss();
                                NavController controller = Navigation.findNavController(requireView());
                                controller.popBackStack();
                            }
                        }).create();
                dialog.show();
            }
        });
    }

    private void initObserver() {
        viewModel.getEquipmentRepList().observe(this, new Observer<List<EquipmentRepData.DataBean>>() {
            @Override
            public void onChanged(List<EquipmentRepData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
                if (list.size() == 0) {
                    binding.iv1.setVisibility(View.VISIBLE);
                    binding.tv1.setVisibility(View.VISIBLE);
                    binding.rv.setVisibility(View.GONE);
                } else {
                    binding.iv1.setVisibility(View.GONE);
                    binding.tv1.setVisibility(View.GONE);
                    binding.rv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private static final int STUDENT = 0;
    private static final int TEACHER = 1;
    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        adapter = new EquipRepAdapter(list);
//        if (list.size() == 0) {
//            binding.iv1.setVisibility(View.VISIBLE);
//            binding.tv1.setVisibility(View.VISIBLE);
//            binding.rv.setVisibility(View.GONE);
//        }

        adapter.setListener(new EquipRepAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                item = list.get(position);

                binding.rv.setVisibility(View.GONE);
                binding.group.setVisibility(View.VISIBLE);

                binding.tv4.setText(item.getName());
                binding.tv6.setText(item.getProviderNumber());
                binding.tv7.setText(item.getProviderName());
                binding.tv8.setText(item.getProviderType() == STUDENT ? "（学生）" : "（教师）");
                binding.tv10.setText(DateUtil.getTime(item.getTime()));
                binding.tv12.setText(item.getOpinion());
            }
        });
        binding.rv.setAdapter(adapter);
    }

}
