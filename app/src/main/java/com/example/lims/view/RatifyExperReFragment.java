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

import com.example.lims.adapter.RatifyExperApplyAdapter;
import com.example.lims.databinding.FragmentRatifyExperReBinding;
import com.example.lims.model.bean.ReservationData;
import com.example.lims.view.base.BaseLoginFragment;
import com.example.lims.viewmodel.TeacherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view.base
 * @Date：2023/1/12 17:03
 * Describe:  审批实验预约
 */
public class RatifyExperReFragment extends BaseLoginFragment {

    private FragmentRatifyExperReBinding binding;
    private final List<ReservationData.DataBean> list = new ArrayList<>();
    private RatifyExperApplyAdapter adapter;
    private TeacherViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentRatifyExperReBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        initRecycleView();
        viewModel = new ViewModelProvider(this).get(TeacherViewModel.class);
        viewModel.getExperimentApplyList().observe(this, new Observer<List<ReservationData.DataBean>>() {
            @Override
            public void onChanged(List<ReservationData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.requestApplyList(userId);
    }

    @Override
    public void initEvent() {

    }

    public void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.rv.setLayoutManager(layoutManager);
        adapter = new RatifyExperApplyAdapter(list);
        adapter.setListener(new RatifyExperApplyAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("是否同意申请？")
                        .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.updateReservation(list.get(position).getId(), FAIL, 0);
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.updateReservation(list.get(position).getId(), SUCCESS, 0);
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
}
