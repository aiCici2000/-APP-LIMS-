package com.example.lims.view;

import static com.example.lims.view.LabApply2Fragment.FAIL;
import static com.example.lims.view.LabApply2Fragment.SUCCESS;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.RatifyLabApplyAdapter;
import com.example.lims.databinding.FragmentRatifyLabApplyBinding;
import com.example.lims.model.bean.ReservationData;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.ApplyViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 15:50
 * Describe: 审批实验室申请
 */
public class RatifyLabApplyFragment extends BaseDialogFragment<FragmentRatifyLabApplyBinding> {
    private static final String TAG = "RatifyLabApplyFragment";

    private FragmentRatifyLabApplyBinding binding;
    private final List<ReservationData.DataBean> list = new ArrayList<>();
    private RatifyLabApplyAdapter adapter;
    private ApplyViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentRatifyLabApplyBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        Log.d(TAG, "init: ");
        adapter = new RatifyLabApplyAdapter(list);
        showLoading();
        initObserver();
        networkRequest();
        initRecycleView();
    }

    @Override
    public void initEvent() {
        Log.d(TAG, "initEvent: ");
    }

    private void initRecycleView() {
        Log.d(TAG, "initRecycleView: ");
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);

        adapter.setItemOnClickListener(new RatifyLabApplyAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("是否同意申请？")
                        .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.updateReservation(list.get(position).getId(), FAIL,1);
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.updateReservation(list.get(position).getId(), SUCCESS, 1);
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
        viewModel.getAllLabApplyList().observe(this, new Observer<List<ReservationData.DataBean>>() {
            @Override
            public void onChanged(List<ReservationData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
                dismissLoading();
            }
        });
    }

    private void networkRequest() {
        viewModel.requestAllLabApplyList(userId);
    }
}
