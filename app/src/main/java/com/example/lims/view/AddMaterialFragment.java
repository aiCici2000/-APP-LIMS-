package com.example.lims.view;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentAddMaterialBinding;
import com.example.lims.model.bean.Material;
import com.example.lims.utils.ToastUtils;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.OtherViewModel;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/2/22 17:07
 * Describe:  添加耗材
 */
public class AddMaterialFragment extends BaseDialogFragment<FragmentAddMaterialBinding> {

    private FragmentAddMaterialBinding binding;
    private OtherViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentAddMaterialBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        viewModel = new ViewModelProvider(this).get(OtherViewModel.class);
    }

    @Override
    public void initEvent() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.et1.getText() + "";
                String summary = binding.et2.getText() + "";
                if (TextUtils.isEmpty(summary)) {
                    summary = "无";
                }
                int count = binding.countView.getCount();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.show("请填写名称！");
                } else {
                    // 构建添加的材料
                    Material material = new Material();
                    material.setName(name);
                    material.setNum(count+"");
                    material.setOther(summary);

                    viewModel.addMaterial(material);
                    binding.et1.setText("");
                    binding.et2.setText("");
                    binding.countView.setCurrCount(1);
                }
            }
        });
    }
}
