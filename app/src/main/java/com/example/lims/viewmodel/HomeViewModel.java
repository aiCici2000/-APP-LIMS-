package com.example.lims.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lims.model.bean.CourseData;
import com.example.lims.model.bean.LaboratoryData;
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
 * @Date：2023/2/1 14:12
 * Describe:
 */
public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";
    private MutableLiveData<Integer> idleLabNumber;
    private MutableLiveData<Integer> nonIdleLabNumber;
    private MutableLiveData<Integer> allCoursesNumber;

    public MutableLiveData<Integer> getIdleLabNumber() {
        if (idleLabNumber == null) {
            idleLabNumber = new MutableLiveData<>();
        }
        return idleLabNumber;
    }

    public MutableLiveData<Integer> getNonIdleLabNumber() {
        if (nonIdleLabNumber == null) {
            nonIdleLabNumber = new MutableLiveData<>();
        }
        return nonIdleLabNumber;
    }

    public MutableLiveData<Integer> getAllCoursesNumber() {
        if (allCoursesNumber == null) {
            allCoursesNumber = new MutableLiveData<>();
        }
        return allCoursesNumber;
    }

    private void getIdleLab() {
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        Call<LaboratoryData> list = service.getLabList(0);
        list.enqueue(new Callback<LaboratoryData>() {
            @Override
            public void onResponse(Call<LaboratoryData> call, Response<LaboratoryData> response) {
                LaboratoryData laboratoryData = response.body();
                try {
                    int num = laboratoryData.getData().size();
                    idleLabNumber.postValue(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LaboratoryData> call, Throwable t) {

            }
        });
    }

    private void getNonIdleLab(){
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        Call<LaboratoryData> list = service.getLabList(1);
        list.enqueue(new Callback<LaboratoryData>() {
            @Override
            public void onResponse(Call<LaboratoryData> call, Response<LaboratoryData> response) {
                LaboratoryData laboratoryData = response.body();
                try {
                    int num = laboratoryData.getData().size();
                    nonIdleLabNumber.postValue(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LaboratoryData> call, Throwable t) {

            }
        });
    }

    private void getAllCourses() {
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        Call<CourseData> list = service.getAllCourse();
        list.enqueue(new Callback<CourseData>() {
            @Override
            public void onResponse(Call<CourseData> call, Response<CourseData> response) {
                CourseData courseData = response.body();
                try {
                    int num = courseData.getData().size();
                    allCoursesNumber.postValue(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CourseData> call, Throwable t) {

            }
        });
    }

    /**
     *
     */
    public void request() {
        getIdleLab();
        getNonIdleLab();
        getAllCourses();
    }
}
