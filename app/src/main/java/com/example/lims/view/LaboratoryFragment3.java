package com.example.lims.view;

import static com.example.lims.utils.Constant.SUCCESS;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.adapter.EquipmentAdapter;
import com.example.lims.databinding.FragmentLaboratory3Binding;
import com.example.lims.model.bean.EquipmentData;
import com.example.lims.model.bean.Opinion;
import com.example.lims.model.bean.ResultData;
import com.example.lims.utils.DateUtil;
import com.example.lims.utils.ToastUtils;
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
 * @Date：2023/2/24 15:06
 * Describe: 实验室列表 三级
 */
public class LaboratoryFragment3 extends BaseDialogFragment<FragmentLaboratory3Binding> {

    private static final String TAG = "LaboratoryFragment3";

    private FragmentLaboratory3Binding binding;
    private final List<EquipmentData.DataBean> list = new ArrayList<>();
    private EquipmentAdapter adapter;
    private RetrofitService service;
    EquipmentData.DataBean item = null;
    private int labId;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentLaboratory3Binding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        Bundle bundle = getArguments();
        labId = bundle.getInt("labId");
        initRV();
        networkRequest();
    }

    @Override
    public void initEvent() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.et.getText() + "";
                if (item == null) {
                    ToastUtils.show("请选择设备！");
                } else if (TextUtils.isEmpty(text)) {
                    ToastUtils.show("请输入报备详情！");
                } else {
                    Opinion opinion = new Opinion();
                    opinion.setOpinion(text);
                    opinion.setTime(DateUtil.getTime());
                    opinion.setUserId(userId);
                    opinion.setEquipmentId(item.getId());

                    addOpinion(opinion);
                }
            }
        });
    }

    private void initRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.rv.setLayoutManager(layoutManager);
        adapter = new EquipmentAdapter(list);
        adapter.setListener(new EquipmentAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                if (list.size() == 1) {
                    item = null;
                    list.clear();
                    adapter.notifyDataSetChanged();
                    networkRequest();
                } else {
                    item = list.get(position);
                    list.clear();
                    list.add(item);
                    adapter.setList(list);
                }
            }
        });
        binding.rv.setAdapter(adapter);
    }

    private void networkRequest() {
        service.getEquipmentByLabId(labId).enqueue(new Callback<EquipmentData>() {
            @Override
            public void onResponse(Call<EquipmentData> call, Response<EquipmentData> response) {
                if (response.body() != null) {
                    EquipmentData body = response.body();
                    Log.d(TAG, "onResponse: " + body.getStatus());
                    if (body.getStatus() == SUCCESS) {
                        List<EquipmentData.DataBean> data = body.getData();
                        Log.d(TAG, "onResponse: " + data);
                        list.clear();
                        list.addAll(data);
                        adapter.notifyDataSetChanged();
                        if (data.size() == 0) {
                            binding.iv1.setVisibility(View.VISIBLE);
                        } else {
                            binding.iv1.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<EquipmentData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    private void addOpinion(Opinion opinion) {
        service.addOpinion(opinion).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                if (response.body() != null) {
                    ResultData body = response.body();
                    if (body.getStatus() == SUCCESS) {
                        ToastUtils.show("报备成功！");
                        binding.et.setText("");
                    } else {
                        ToastUtils.show("报备失败！");
                    }
                } else {
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }
}
