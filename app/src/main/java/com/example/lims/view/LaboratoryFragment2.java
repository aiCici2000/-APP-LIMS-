package com.example.lims.view;

import static com.example.lims.utils.Constant.SUCCESS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.lims.R;
import com.example.lims.databinding.FragmentLaboratory2Binding;
import com.example.lims.model.bean.User;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;
import com.example.lims.view.base.BaseDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/2/24 14:22
 * Describe: 实验室列表 二级
 */
public class LaboratoryFragment2 extends BaseDialogFragment<FragmentLaboratory2Binding> {

    private FragmentLaboratory2Binding binding;
    private Bundle bundle;
    private RetrofitService service;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentLaboratory2Binding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        bundle = getArguments();
        int labId = bundle.getInt("labId");
        String labName = bundle.getString("labName");
        String labNumber = bundle.getString("labNumber");

        int adminId = bundle.getInt("adminId");
        requestData(adminId);

        String summary = bundle.getString("summary");
        String course = "null".equals(bundle.getString("course")) ? "无" : bundle.getString("course");
        String equipmentNum = bundle.getString("equipmentNum");
        String notice = "null".equals(bundle.getString("notice")) ? "无" : bundle.getString("notice");

        binding.tv4.setText(labName);
        binding.tv6.setText(labNumber);
        binding.tv12.setText(summary);
        binding.tv14.setText(course);
        binding.tv18.setText(equipmentNum);
        binding.tv20.setText(notice);

    }

    @Override
    public void initEvent() {
        binding.tv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId != -1) {
                    NavController controller = Navigation.findNavController(requireView());
                    controller.navigate(R.id.action_nav_laboratory_2_to_nav_laboratory_3, bundle);
                } else {
                    ToastUtils.show("请登录！");
                }
            }
        });
    }

    User.DataBean user;

    public void requestData(int id) {
        service.getUserById(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    User body = response.body();
                    if (body.getStatus() == SUCCESS) {
                        user = body.getData();
                        binding.tv10.setText(user.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
