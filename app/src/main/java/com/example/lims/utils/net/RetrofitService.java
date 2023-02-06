package com.example.lims.utils.net;

import com.example.lims.model.bean.CourseData;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.model.bean.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author：李壮
 * @Package：com.example.lims.utils.net
 * @Date：2023/2/1 16:44
 * Describe:
 */
public interface RetrofitService {

    @GET("lims/home/all-teacher-list")
    Call<UserData> getTeacherList(@Query("type") Integer type);

    @GET("lims/home/all-course-list")
    Call<CourseData> getAllCourse();

    @GET("lims/laboratory/list")
    Call<LaboratoryData> getLabList(@Query("status") Integer status);

    @GET("lims/laboratory/all-list")
    Call<LaboratoryData> getAllLabList();
}
