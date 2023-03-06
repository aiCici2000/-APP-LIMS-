package com.example.lims.view;

import static com.example.lims.utils.Constant.SUCCESS;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentReportBinding;
import com.example.lims.model.bean.Report;
import com.example.lims.model.bean.ResultData;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;
import com.example.lims.view.base.BaseLoginFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/13 17:03
 * Describe: 其他报备二级页面
 */
public class ReportFragment extends BaseLoginFragment<FragmentReportBinding> {

    private static final String TAG = "ReportFragment";

    private FragmentReportBinding binding;
    private int labId;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentReportBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        Bundle bundle = getArguments();
        String labNumber = bundle.getString("lab_number");
        labId = bundle.getInt("lab_id");
        binding.tv2.setText(labNumber);
    }

    @Override
    public void initEvent() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId != -1) {
                    Report report = new Report();
                    report.setFromId(userId);
                    report.setToId(labId);
                    report.setContent(binding.edit1.getText()+"");

                    submitReport(report);
                } else {
                    ToastUtils.show("请先登录！");
                }
            }
        });
    }

    private void submitReport(Report report) {
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        service.addReport(report).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() == SUCCESS) {
                        ToastUtils.show("报备成功！");
                        binding.edit1.setText("");
                    }
                } else {
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

}
