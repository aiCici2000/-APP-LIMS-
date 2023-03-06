package com.example.lims.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.lims.databinding.FragmentOtherReportBinding;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.OtherReportViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 15:45
 * Describe: 其他报备一级页面
 */
public class OtherReportFragment extends BaseDialogFragment<FragmentOtherReportBinding> {

    private static final String TAG = "OtherReportFragment";
    private FragmentOtherReportBinding binding;
    private final List<LaboratoryData.DataBean> list = new ArrayList<>();
    private OtherReportViewModel viewModel;
    private LaboratoryAdapter adapter;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentOtherReportBinding.inflate(inflater, container, false);
        return binding;
    }


    @Override
    public void init() {
        Log.d(TAG, "init: ");
        showLoading();
        initObserver();
        networkRequest();
        initRecycleView();
    }

    @Override
    public void initEvent() {

    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(manager);
        adapter = new LaboratoryAdapter(list, true);
        adapter.setListener(new LaboratoryAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                initItemListener(position);
            }
        });
        binding.rv.setAdapter(adapter);
    }

    private void initItemListener(int position) {
        Log.d(TAG, "initItemListener: ");
        LaboratoryData.DataBean item = list.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("lab_number", item.getNumber());
        bundle.putInt("lab_id", item.getId());
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_nav_other_report_to_nav_report, bundle);
    }

    private void initObserver() {
        //TODO ViewModel生命周期观察错了，不应该是Activity，而是this
        viewModel = new ViewModelProvider(this).get(OtherReportViewModel.class);
        viewModel.getLabBeanList().observe(this, new Observer<List<LaboratoryData.DataBean>>() {
            @Override
            public void onChanged(List<LaboratoryData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
                dismissLoading();
            }
        });
    }

    private void networkRequest() {
        viewModel.request();
    }
}
