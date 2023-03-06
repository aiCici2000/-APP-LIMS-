package com.example.lims.viewmodel;

import static com.example.lims.utils.Constant.SUCCESS;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.model.bean.Material;
import com.example.lims.model.bean.MaterialData;
import com.example.lims.model.bean.ResultData;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.viewmodel
 * @Date：2023/2/17 10:39
 * Describe:
 */
public class OtherViewModel extends ViewModel {

    private static final String TAG = "OtherViewModel";

    private MutableLiveData<List<MaterialData.DataBean>> materialList;
    private MutableLiveData<List<LaboratoryData.DataBean>> laboratoryList;

    RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);

    public MutableLiveData<List<MaterialData.DataBean>> getMaterialList() {
        if (materialList == null) {
            materialList = new MutableLiveData<>();
        }
        return materialList;
    }

    public MutableLiveData<List<LaboratoryData.DataBean>> getLaboratoryList() {
        if (laboratoryList == null) {
            laboratoryList = new MutableLiveData<>();
        }
        return laboratoryList;
    }

    public void requestAllMaterial() {
        service.getAllMaterial().enqueue(new Callback<MaterialData>() {
            @Override
            public void onResponse(Call<MaterialData> call, Response<MaterialData> response) {
                try {
                    List<MaterialData.DataBean> data = response.body().getData();
                    materialList.postValue(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<MaterialData> call, Throwable t) {
                ToastUtils.show("连接服务器失败！");
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void requestLaboratoryList(int id) {
        service.getLabListByAdminId(id).enqueue(new Callback<LaboratoryData>() {
            @Override
            public void onResponse(Call<LaboratoryData> call, Response<LaboratoryData> response) {
                try {
                    List<LaboratoryData.DataBean> data = response.body().getData();
                    laboratoryList.postValue(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<LaboratoryData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                ToastUtils.show("连接服务器失败！");
            }
        });
    }

    public void addNotice(LaboratoryData.DataBean laboratory) {
        service.addNotice(laboratory).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                try {
                    int status = response.body().getStatus();
                    if (status == SUCCESS) {
                        ToastUtils.show("发布成功！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                ToastUtils.show("连接服务器失败！");
            }
        });
    }

    public void addMaterial(Material material) {
        service.addMaterial(material).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() == SUCCESS) {
                        ToastUtils.show("添加成功！");
                    } else {
                        ToastUtils.show("添加失败！");
                    }
                } else {
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                ToastUtils.show("连接服务器失败！");
            }
        });
    }

    public void updateMaterialNum(int id, int num) {
        service.updateMaterialNum(id, num).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() == SUCCESS) {
                        ToastUtils.show("修改成功！");
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
