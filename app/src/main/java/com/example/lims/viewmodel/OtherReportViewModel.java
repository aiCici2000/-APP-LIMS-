package com.example.lims.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.viewmodel
 * @Date：2023/2/6 16:39
 * Describe:
 */
public class OtherReportViewModel extends ViewModel {

    private static final String TAG = "OtherReportViewModel";

    private MutableLiveData<List<LaboratoryData.DataBean>> labBeanList;
    private Call<LaboratoryData> allLabList;

    public MutableLiveData<List<LaboratoryData.DataBean>> getLabBeanList() {
        if (labBeanList == null) {
            labBeanList = new MutableLiveData<>();
        }
        return labBeanList;
    }

    private void getDataBeanList() {
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        allLabList = service.getAllLabList();
        allLabList.enqueue(new Callback<LaboratoryData>() {
            @Override
            public void onResponse(Call<LaboratoryData> call, Response<LaboratoryData> response) {
                LaboratoryData body = response.body();
                try {
                    List<LaboratoryData.DataBean> data = body.getData();
                    labBeanList.postValue(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LaboratoryData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    public void request() {
        getDataBeanList();
    }

}
