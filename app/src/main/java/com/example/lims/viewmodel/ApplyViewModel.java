package com.example.lims.viewmodel;

import static com.example.lims.utils.Constant.INSUFFICIENT_QUANTITY;
import static com.example.lims.utils.Constant.SUCCESS;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lims.model.bean.Application;
import com.example.lims.model.bean.ApplicationData;
import com.example.lims.model.bean.ReservationData;
import com.example.lims.model.bean.ResultData;
import com.example.lims.model.bean.SoftwareApplyData;
import com.example.lims.repository.SharedPreferencesUtil;
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
 * @Date：2023/2/7 17:18
 * Describe: 预约申请 viewModel
 */
public class ApplyViewModel extends ViewModel {

    private static final String TAG = "ApplyViewModel";
    public static final int STUDENT_RESERVATION = 0;
    public static final int TEACHER_APPLIES_FOR_CLASS = 1;

    private MutableLiveData<List<ReservationData.DataBean>> allLabApplyList;
    private MutableLiveData<List<ApplicationData.DataBean>> allMaterialApply;
    private MutableLiveData<List<SoftwareApplyData.DataBean>> allSoftApply;
    private RetrofitService retrofitService = RetrofitUtil.getRetrofit().create(RetrofitService.class);;

    public MutableLiveData<List<ReservationData.DataBean>> getAllLabApplyList() {
        if (allLabApplyList == null) {
            allLabApplyList = new MutableLiveData<>();
        }
        return allLabApplyList;
    }

    public MutableLiveData<List<ApplicationData.DataBean>> getAllMaterialApply() {
        if (allMaterialApply == null) {
            allMaterialApply = new MutableLiveData<>();
        }
        return allMaterialApply;
    }

    public MutableLiveData<List<SoftwareApplyData.DataBean>> getAllSoftApply() {
        if (allSoftApply == null) {
            allSoftApply = new MutableLiveData<>();
        }
        return allSoftApply;
    }

    public void requestAllLabApplyList(int handleId) {
        retrofitService.getAllLabApplyList(TEACHER_APPLIES_FOR_CLASS, handleId).enqueue(new Callback<ReservationData>() {
            @Override
            public void onResponse(Call<ReservationData> call, Response<ReservationData> response) {
                ReservationData body = response.body();
                if (body != null) {
                    List<ReservationData.DataBean> data = body.getData();
                    allLabApplyList.postValue(data);
                }else {
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<ReservationData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    public void requestAllMaterialApply(int handleId) {
        retrofitService.getAllMaterialApply(handleId).enqueue(new Callback<ApplicationData>() {
            @Override
            public void onResponse(Call<ApplicationData> call, Response<ApplicationData> response) {
                ApplicationData body = response.body();
                try {
                    List<ApplicationData.DataBean> data = body.getData();
                    allMaterialApply.postValue(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApplicationData> call, Throwable t) {

            }
        });
    }

    public void requestAllSoftApply(int handleId) {
        retrofitService.getAllSoftApply(handleId).enqueue(new Callback<SoftwareApplyData>() {
            @Override
            public void onResponse(Call<SoftwareApplyData> call, Response<SoftwareApplyData> response) {
                SoftwareApplyData body = response.body();
                try {
                    List<SoftwareApplyData.DataBean> data = body.getData();
                    allSoftApply.postValue(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SoftwareApplyData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    public void updateReservation(int id, int status, int type) {
        retrofitService.updateReservation(id, status, type).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() != SUCCESS) {
                        ToastUtils.show("请重试！");
                    } else {
                        requestAllLabApplyList((int) SharedPreferencesUtil.getData("userId", -1));
                    }
                } else {
                    ToastUtils.show("请重试！");
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    public void updateMaterialApply(int id, int status) {
        retrofitService.updateMaterialApply(id, status).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() == SUCCESS) {
                        requestAllMaterialApply((int) SharedPreferencesUtil.getData("userId", -1));
                    } else if (body.getStatus() == INSUFFICIENT_QUANTITY) {
                        ToastUtils.show(body.getMsg());
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

    public void updateSoftwareApply(int id, int status) {
        retrofitService.updateSoftwareApply(id, status).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() != SUCCESS) {
                        ToastUtils.show("请重试！");
                    } else {
                        requestAllSoftApply((int) SharedPreferencesUtil.getData("userId", -1));
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

    public void addMaterialApply(Application application) {
        retrofitService.addMaterialApply(application).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() != SUCCESS) {
                        ToastUtils.show("请重试！");
                    } else {
                        ToastUtils.show("申请成功！");
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
