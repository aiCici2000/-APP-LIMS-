package com.example.lims.view;

import static com.example.lims.view.HomeFragment.All_STATE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.adapter.LaboratoryAdapter;
import com.example.lims.databinding.FragmentLaboratoryBinding;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;
import com.example.lims.view.base.BaseDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 9:22
 * Describe: 实验室列表
 */
public class LaboratoryFragment extends BaseDialogFragment<FragmentLaboratoryBinding> {

    private static final String TAG = "LaboratoryFragment";
    private FragmentLaboratoryBinding binding;
    private final List<LaboratoryData.DataBean> list = new ArrayList<>();
    private LaboratoryAdapter adapter;
    private Call<LaboratoryData> dataList;
    private LaboratoryData.DataBean item;
    private RetrofitService service;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentLaboratoryBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        Log.d(TAG, "init: ");
        showLoading();
        service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        initLaboratoryItem(getArguments().getInt("status"));
        initRecycleView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataList.cancel();
    }

    @Override
    public void initEvent() {

    }

    private void initLaboratoryItem(int status) {
        if (status == All_STATE) {
            dataList = service.getAllLabList();
        } else {
            dataList = service.getLabList(status);
        }
        dataList.enqueue(new Callback<LaboratoryData>() {
            @Override
            public void onResponse(Call<LaboratoryData> call, Response<LaboratoryData> response) {
                LaboratoryData laboratoryData = response.body();
                try {
                    List<LaboratoryData.DataBean> dataBeans = laboratoryData.getData();
                    list.clear();
                    list.addAll(dataBeans);
                    if (list.size() == 0) {
                        setNoDataPage();
                    }
                    adapter.notifyDataSetChanged();
                    dismissLoading();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LaboratoryData> call, Throwable t) {
                dismissLoading();
                setNoDataPage();
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    private void initRecycleView() {
        Log.d(TAG, "initRecycleView: ");
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(manager);
        adapter = new LaboratoryAdapter(list, false);
        adapter.setListener(new LaboratoryAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                item = list.get(position);

                Bundle bundle = new Bundle();
                bundle.putInt("labId", item.getId());
                bundle.putString("labName", item.getName());
                bundle.putString("labNumber", item.getNumber());

                bundle.putInt("adminId", item.getAdministratorId());
                bundle.putString("summary", item.getSummary());
                bundle.putString("course", item.getCourse() + "");
                bundle.putString("equipmentNum", item.getEquipmentNum() + "");
                bundle.putString("notice", item.getNotice() + "");
                NavController controller = Navigation.findNavController(requireView());
                controller.navigate(R.id.action_nav_laboratory_to_nav_laboratory_2, bundle);
            }
        });
        binding.rv.setAdapter(adapter);
    }

    public void setNoDataPage() {
        binding.rv.setVisibility(View.GONE);
        binding.ivNoData.setVisibility(View.VISIBLE);
    }

}
