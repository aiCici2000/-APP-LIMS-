package com.example.lims.view;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.adapter.LaboratorySimpleAdapter;
import com.example.lims.databinding.FragmentAnnouncementBinding;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.utils.ToastUtils;
import com.example.lims.view.base.BaseLoginFragment;
import com.example.lims.viewmodel.OtherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:16
 * Describe:  发布公告
 */
public class AnnouncementFragment extends BaseLoginFragment {

    private FragmentAnnouncementBinding binding;
    private List<LaboratoryData.DataBean> labList = new ArrayList<>();
    private LaboratorySimpleAdapter adapter;
    private LaboratoryData.DataBean chooseItem;
    private OtherViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentAnnouncementBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        viewModel = new ViewModelProvider(this).get(OtherViewModel.class);
        adapter = new LaboratorySimpleAdapter(labList);
        initObserver();
        initRV();
    }

    @Override
    public void initEvent() {
        binding.commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notice = binding.edit1.getText() + "";
                if (chooseItem == null) {
                    ToastUtils.show("请选择实验室！");
                } else if (TextUtils.isEmpty(notice)) {
                    ToastUtils.show("公告不能为空！");
                } else {
                    chooseItem.setNotice(notice);
                    viewModel.addNotice(chooseItem);
                    binding.edit1.setText("");
                }
            }
        });
    }

    private void initObserver() {
        viewModel.getLaboratoryList().observe(this, new Observer<List<LaboratoryData.DataBean>>() {
            @Override
            public void onChanged(List<LaboratoryData.DataBean> dataBeans) {
                labList.clear();
                labList.addAll(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.requestLaboratoryList(userId);
    }

    private void initRV() {
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(),2);
        binding.rv.setLayoutManager(layoutManager);
        adapter.setListener(new LaboratorySimpleAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                chooseItem = labList.get(position);
            }
        });
        binding.rv.setAdapter(adapter);
    }
}
