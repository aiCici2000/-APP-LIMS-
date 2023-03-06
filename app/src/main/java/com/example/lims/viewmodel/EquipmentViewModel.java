package com.example.lims.viewmodel;

import static com.example.lims.utils.Constant.FAILED;
import static com.example.lims.utils.Constant.SUCCESS;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lims.model.bean.Equipment;
import com.example.lims.model.bean.EquipmentData;
import com.example.lims.model.bean.EquipmentRepData;
import com.example.lims.model.bean.LaboratoryData;
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
 * @Date：2023/2/14 15:38
 * Describe:
 */
public class EquipmentViewModel extends ViewModel {

    private static final String TAG = "EquipmentViewModel";

    private MutableLiveData<List<LaboratoryData.DataBean>> laboratoryList;
    private MutableLiveData<List<EquipmentRepData.DataBean>> equipmentRepList;
    private MutableLiveData<List<EquipmentData.DataBean>> equipmentList;

    MutableLiveData<Integer> addStatus;
    private Equipment equipment;

    private final RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);

    public MutableLiveData<List<LaboratoryData.DataBean>> getLaboratoryList() {
        if (laboratoryList == null) {
            laboratoryList = new MutableLiveData<>();
        }
        return laboratoryList;
    }

    public MutableLiveData<List<EquipmentRepData.DataBean>> getEquipmentRepList() {
        if (equipmentRepList == null) {
            equipmentRepList = new MutableLiveData<>();
        }
        return equipmentRepList;
    }

    public MutableLiveData<List<EquipmentData.DataBean>> getEquipmentList() {
        if (equipmentList == null) {
            equipmentList = new MutableLiveData<>();
        }
        return equipmentList;
    }

    public MutableLiveData<Integer> getAddStatus() {
        if (addStatus == null) {
            addStatus = new MutableLiveData<>();
        }
        return addStatus;
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
                }
            }

            @Override
            public void onFailure(Call<LaboratoryData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                ToastUtils.show("连接服务器失败！");
            }
        });
    }

    public void addEquipment(int num) {
        service.addEquipment(equipment, num).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                try {
                    ResultData body = response.body();
                    int status = body.getStatus();
                    if (status == SUCCESS) {
                        addStatus.postValue(SUCCESS);
                    } else {
                        addStatus.postValue(FAILED);
                    }
                } catch (Exception e) {
                    ToastUtils.show("添加失败！");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                ToastUtils.show("连接服务器失败！");
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void requestEquipmentRepList(int adminId) {
        service.getEquipmentRepaired(adminId).enqueue(new Callback<EquipmentRepData>() {
            @Override
            public void onResponse(Call<EquipmentRepData> call, Response<EquipmentRepData> response) {
                try {
                    List<EquipmentRepData.DataBean> data = response.body().getData();
                    equipmentRepList.postValue(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<EquipmentRepData> call, Throwable t) {
                ToastUtils.show("连接服务器失败！");
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void requestEquipmentList(int labId) {

        service.getEquipmentByLabId(labId).enqueue(new Callback<EquipmentData>() {
            @Override
            public void onResponse(Call<EquipmentData> call, Response<EquipmentData> response) {
                try {
                    List<EquipmentData.DataBean> data = response.body().getData();
                    equipmentList.postValue(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<EquipmentData> call, Throwable t) {
                ToastUtils.show("连接服务器失败！");
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    public void deleteEquipment(int id, int labId) {
        service.removeEquipmentById(id).enqueue(new Callback<EquipmentData>() {
            @Override
            public void onResponse(Call<EquipmentData> call, Response<EquipmentData> response) {
                try {
                    int status = response.body().getStatus();
                    if (status == SUCCESS) {
                        ToastUtils.show("出库成功！");
                        requestEquipmentList(labId);
                    } else {
                        ToastUtils.show("出库失败！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<EquipmentData> call, Throwable t) {
                ToastUtils.show("连接服务器失败！");
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void repairEquipment(int equipId) {
        service.repairEquipment(equipId).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                if (response.body() != null) {
                    ResultData body = response.body();
                    if (body.getStatus() == SUCCESS) {
                        ToastUtils.show("操作成功！");
                    } else {
                        ToastUtils.show("操作失败！");
                    }
                } else {
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                ToastUtils.show("连接服务器失败！");
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
