package com.example.lims.view;

import static com.example.lims.view.LabApply2Fragment.FAIL;
import static com.example.lims.view.LabApply2Fragment.SUCCESS;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.RatifySoApplyAdapter;
import com.example.lims.databinding.FragmentRatifySoApplyBinding;
import com.example.lims.model.bean.SoftwareApplyData;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.ApplyViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 15:58
 * Describe:  审批软件申请
 */
public class RatifySoApplyFragment extends BaseDialogFragment<FragmentRatifySoApplyBinding> {
    private static final String TAG = "RatifySoApplyFragment";

    private FragmentRatifySoApplyBinding binding;
    private final List<SoftwareApplyData.DataBean> list = new ArrayList<>();
    private ApplyViewModel viewModel;
    private RatifySoApplyAdapter adapter;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentRatifySoApplyBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        adapter = new RatifySoApplyAdapter(list);
        showLoading();
        initObserver();
        networkRequest();
        initRecycleView();
    }

    @Override
    public void initEvent() {

    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        adapter.setItemOnClickListener(new RatifySoApplyAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("是否同意申请？")
                        .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.updateSoftwareApply(list.get(position).getId(), FAIL);
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.updateSoftwareApply(list.get(position).getId(), SUCCESS);
                                dialog.dismiss();
                            }
                        }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });
        binding.rv.setAdapter(adapter);
    }

    private void initObserver() {
        viewModel = new ViewModelProvider(this).get(ApplyViewModel.class);
        viewModel.getAllSoftApply().observe(this, new Observer<List<SoftwareApplyData.DataBean>>() {
            @Override
            public void onChanged(List<SoftwareApplyData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
                dismissLoading();
            }
        });
    }

    private void networkRequest() {
        viewModel.requestAllSoftApply(userId);
    }
}
