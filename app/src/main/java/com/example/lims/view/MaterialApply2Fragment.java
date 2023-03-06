package com.example.lims.view;

import static com.example.lims.view.LabApply2Fragment.TO_BE_REVIEWED;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentMaterialApply2Binding;
import com.example.lims.model.bean.Application;
import com.example.lims.utils.DateUtil;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.ApplyViewModel;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/2/23 9:23
 * Describe: 耗材申请 二级
 */
public class MaterialApply2Fragment extends BaseDialogFragment<FragmentMaterialApply2Binding> {

    private static final String TAG = "MaterialApply2Fragment";

    private FragmentMaterialApply2Binding binding;
    private int administratorId;
    private String matNum;
    private int matId;
    private ApplyViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMaterialApply2Binding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        viewModel = new ViewModelProvider(this).get(ApplyViewModel.class);
        initView();
    }

    @Override
    public void initEvent() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application application = new Application();
                application.setApplicantId(userId);
                application.setMaterialId(matId);
                application.setStatus(TO_BE_REVIEWED);
                application.setNum(binding.countView.getCount());
                application.setAdminId(administratorId);
                application.setTime(DateUtil.getTime());
                viewModel.addMaterialApply(application);
            }
        });
    }

    private void initView() {
        Bundle bundle = getArguments();
        String labNumber = bundle.getString("lab_number");
        administratorId = bundle.getInt("administratorId");
        String labName = bundle.getString("lab_name");

        String matName = bundle.getString("mat_name");
        String matOther = bundle.getString("mat_other");
        matId = bundle.getInt("mat_id");
        matNum = bundle.getString("mat_num");

        binding.labName.setText(labName);
        binding.labNumber.setText(labNumber);
        binding.matName.setText(matName);
        binding.matOther.setText("null".equals(matOther) ? "无" : matOther);
        binding.matNum.setText(matNum);

    }
}
