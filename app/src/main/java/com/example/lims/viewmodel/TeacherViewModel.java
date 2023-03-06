package com.example.lims.viewmodel;

import static com.example.lims.adapter.CourseSimpleAdapter.OPTIONAL;
import static com.example.lims.utils.Constant.SUCCESS;
import static com.example.lims.viewmodel.ApplyViewModel.STUDENT_RESERVATION;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lims.model.bean.CourseInfoData;
import com.example.lims.model.bean.CourseNumberData;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.model.bean.MaterialData;
import com.example.lims.model.bean.Reservation;
import com.example.lims.model.bean.ReservationData;
import com.example.lims.model.bean.ResultData;
import com.example.lims.repository.SharedPreferencesUtil;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.viewmodel
 * @Date：2023/2/20 9:56
 * Describe:
 */
public class TeacherViewModel extends ViewModel {

    private static final String TAG = "TeacherViewModel";

    private MutableLiveData<List<ReservationData.DataBean>> experimentApplyList;
    private MutableLiveData<List<LaboratoryData.DataBean>> allLabList;
    private MutableLiveData<List<CourseNumberData.DataBean>> courseNumberList;
    private MutableLiveData<List<Integer>> courseInfo;
    private final List<CourseNumberData.DataBean> list = new ArrayList<>();

    MutableLiveData<List<MaterialData.DataBean>> materialList;

    private final RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);

    public MutableLiveData<List<ReservationData.DataBean>> getExperimentApplyList() {
        if (experimentApplyList == null) {
            experimentApplyList = new MutableLiveData<>();
        }
        return experimentApplyList;
    }

    public MutableLiveData<List<LaboratoryData.DataBean>> getAllLabList() {
        if (allLabList == null) {
            allLabList = new MutableLiveData<>();
        }
        return allLabList;
    }

    public MutableLiveData<List<CourseNumberData.DataBean>> getCourseNumberList() {
        if (courseNumberList == null) {
            courseNumberList = new MutableLiveData<>();
        }
        resetList();
        return courseNumberList;
    }

    public MutableLiveData<List<Integer>> getCourseInfo() {
        if (courseInfo == null) {
            courseInfo = new MutableLiveData<>();
        }
        return courseInfo;
    }

    public MutableLiveData<List<MaterialData.DataBean>> getMaterialList() {
        if (materialList == null) {
            materialList = new MutableLiveData<>();
        }
        return materialList;
    }

    private void resetList() {
        list.clear();
        for (int i = 0; i < 6; i++) {
            CourseNumberData.DataBean dataBean = new CourseNumberData.DataBean();
            dataBean.setCourseNumber(i + 1);
            dataBean.setStatus(OPTIONAL);
            list.add(dataBean);
        }
    }

    public void requestApplyList(int handleId) {
        service.getAllLabApplyList(STUDENT_RESERVATION, handleId).enqueue(new Callback<ReservationData>() {
            @Override
            public void onResponse(Call<ReservationData> call, Response<ReservationData> response) {
                ReservationData body = response.body();
                if (body != null) {
                    List<ReservationData.DataBean> data = body.getData();
                    experimentApplyList.postValue(data);
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

    public void requestAllLabList() {
        service.getAllLabList().enqueue(new Callback<LaboratoryData>() {
            @Override
            public void onResponse(Call<LaboratoryData> call, Response<LaboratoryData> response) {
                LaboratoryData body = response.body();
                if (body != null) {
                    List<LaboratoryData.DataBean> data = body.getData();
                    allLabList.postValue(data);
                }
            }

            @Override
            public void onFailure(Call<LaboratoryData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    public void requestCourseNumber(int type, int labId, String date, int status) {
        service.getCourseNumber(type, labId, date, status).enqueue(new Callback<CourseNumberData>() {
            @Override
            public void onResponse(Call<CourseNumberData> call, Response<CourseNumberData> response) {
                CourseNumberData body = response.body();
                if (body != null) {
                    List<CourseNumberData.DataBean> data = body.getData();
                    resetList();
                    for (CourseNumberData.DataBean dataBean : data) {
                        list.set(dataBean.getCourseNumber() - 1, dataBean);
                    }
                    Log.d(TAG, "onResponse: " + list.toString());
                    courseNumberList.postValue(list);
                }
            }

            @Override
            public void onFailure(Call<CourseNumberData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    public void requestCourseInfo(int type, int labId, String date, int status) {
        service.getCourseInformation(type, labId, date, status).enqueue(new Callback<CourseInfoData>() {
            @Override
            public void onResponse(Call<CourseInfoData> call, Response<CourseInfoData> response) {
                CourseInfoData body = response.body();
                if (body != null && body.getStatus() == SUCCESS) {
                    Log.d(TAG, "onResponse: ");
                    List<Integer> data = body.getData();
                    courseInfo.postValue(data);
                    requestCourseNumber(type, labId, date, status);
                }
            }

            @Override
            public void onFailure(Call<CourseInfoData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    private ResultInterface resultInterface = null;

    public void setResultInterface(ResultInterface resultInterface) {
        this.resultInterface = resultInterface;
    }

    public interface ResultInterface{
        void help();
    }

    public void addReservation(Reservation reservation) {
        service.addReservation(reservation).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() == SUCCESS) {
                        ToastUtils.show("申请成功！");
                        resultInterface.help();
                    } else {
                        ToastUtils.show("添加失败！");
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

    public void updateReservation(int id, int status, int type) {
        service.updateReservation(id, status, type).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                ResultData body = response.body();
                if (body != null) {
                    if (body.getStatus() != SUCCESS) {
                        ToastUtils.show("请重试！");
                    } else {
                        requestApplyList((int) SharedPreferencesUtil.getData("userId", -1));
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
}
