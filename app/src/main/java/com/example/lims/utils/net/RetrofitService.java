package com.example.lims.utils.net;

import com.example.lims.model.bean.Application;
import com.example.lims.model.bean.ApplicationData;
import com.example.lims.model.bean.CourseData;
import com.example.lims.model.bean.CourseInfoData;
import com.example.lims.model.bean.CourseNumberData;
import com.example.lims.model.bean.Equipment;
import com.example.lims.model.bean.EquipmentData;
import com.example.lims.model.bean.EquipmentRepData;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.model.bean.Material;
import com.example.lims.model.bean.MaterialData;
import com.example.lims.model.bean.MessageData;
import com.example.lims.model.bean.MessageStatusData;
import com.example.lims.model.bean.Opinion;
import com.example.lims.model.bean.Report;
import com.example.lims.model.bean.Reservation;
import com.example.lims.model.bean.ReservationData;
import com.example.lims.model.bean.ResultData;
import com.example.lims.model.bean.SoftwareApply;
import com.example.lims.model.bean.SoftwareApplyData;
import com.example.lims.model.bean.SoftwareData;
import com.example.lims.model.bean.User;
import com.example.lims.model.bean.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Author：李壮
 * @Package：com.example.lims.utils.net
 * @Date：2023/2/1 16:44
 * Describe:
 */
public interface RetrofitService {

    //用户相关
    @FormUrlEncoded
    @POST("lims/user/login")
    Call<UserData> login(@Field("number") String number, @Field("password") String password);

    @GET("lims/user/email-code")
    Call<ResultData> getEmailCode(@Query("userId") int userId);

    @GET("lims/user/update-password")
    Call<ResultData> updatePassword(@Query("password") String password, @Query("userId") int userId);

    @GET("lims/user/email-code-binding")
    Call<ResultData> getEmailCode(@Query("email") String email);

    @GET("lims/user/update-email")
    Call<ResultData> updateEmail(@Query("email") String email);

    @GET("lims/user/get-by-id")
    Call<User> getUserById(@Query("id") int id);


    //首页相关
    @GET("lims/home/all-teacher-list")
    Call<UserData> getTeacherList(@Query("type") int type);

    @GET("lims/home/all-course-list")
    Call<CourseData> getAllCourse();

    @GET("lims/laboratory/list")
    Call<LaboratoryData> getLabList(@Query("status") int status);

    @GET("lims/laboratory/all-list")
    Call<LaboratoryData> getAllLabList();


    //审批相关
    @GET("lims/apply/lab-apply-list")
    Call<ReservationData> getAllLabApplyList(@Query("type") int type, @Query("handleId") int handleId);

    @GET("lims/apply/update-reservation")
    Call<ResultData> updateReservation(@Query("id") int id, @Query("status") int status, @Query("type") int type);

    @GET("lims/apply/material-apply-list")
    Call<ApplicationData> getAllMaterialApply(@Query("handleId") int handleId);

    @GET("lims/apply/update-material-apply")
    Call<ResultData> updateMaterialApply(@Query("id") int id, @Query("status") int status);

    @GET("lims/apply/software-apply-list")
    Call<SoftwareApplyData> getAllSoftApply(@Query("handleId") int handleId);

    @GET("lims/apply/update-software-apply")
    Call<ResultData> updateSoftwareApply(@Query("id") int id, @Query("status") int status);


    //设备管理相关
    @POST("lims/equipment/add")
    Call<ResultData> addEquipment(@Body Equipment equipment, @Query("num") int num);

    @GET("lims/laboratory/list/{id}")
    Call<LaboratoryData> getLabListByAdminId(@Path("id") int id);

    @GET("lims/equipment/repair-list")
    Call<EquipmentRepData> getEquipmentRepaired(@Query("adminId") int adminId);

    @GET("lims/equipment/list")
    Call<EquipmentData> getEquipmentByLabId(@Query("labId") int labId);

    @DELETE("lims/equipment/delete")
    Call<EquipmentData> removeEquipmentById(@Query("id") int id);

    @GET("lims/equipment/repair-equipment")
    Call<ResultData> repairEquipment(@Query("equipId") int equipId);

    @POST("lims/equipment/add-opinion")
    Call<ResultData> addOpinion(@Body Opinion opinion);


    //其他
    @GET("lims/material/all-list")
    Call<MaterialData> getAllMaterial();

    @POST("lims/laboratory/update-notice")
    Call<ResultData> addNotice(@Body LaboratoryData.DataBean laboratory);

    @POST("lims/material/add")
    Call<ResultData> addMaterial(@Body Material material);

    @POST("lims/laboratory/add-report")
    Call<ResultData> addReport(@Body Report report);

    @GET("lims/material/update-num")
    Call<ResultData> updateMaterialNum(@Query("id") int id, @Query("num") int num);


    // 教师相关
    @GET("lims/apply/course-status")
    Call<CourseNumberData> getCourseNumber(@Query("type") int type,
                                           @Query("labId") int labId,
                                           @Query("date") String date,
                                           @Query("status") int status);

    @GET("lims/apply/course-information")
    Call<CourseInfoData> getCourseInformation(@Query("type") int type,
                                              @Query("labId") int labId,
                                              @Query("date") String date,
                                              @Query("status") int status);

    @POST("lims/apply/add-reservation")
    Call<ResultData> addReservation(@Body Reservation reservation);

    @POST("lims/apply/add-material_apply")
    Call<ResultData> addMaterialApply(@Body Application application);

    @GET("lims/apply/teacher-course")
    Call<CourseData> getCourseByTeacher(@Query("teacherId") int teacherId);

    @GET("lims/apply/all-software")
    Call<SoftwareData> getAllSoftware();

    @POST("lims/apply/software-apply")
    Call<ResultData> addSoftwareApply(@Body SoftwareApply softwareApply);

    // 学生相关
    @POST("lims/apply/experiment-apply")
    Call<ResultData> experimentApply(@Body Reservation reservation);

    @GET("lims/user/get-my-course")
    Call<CourseData> getMyCourseList(@Query("id") int id);

    @GET("lims/user/get-my-reservation")
    Call<ReservationData> getMyReservation(@Query("userId") int userId);


    // 消息相关
    @GET("lims/message/get-message")
    Call<MessageData> getMessage(@Query("toId") int toId, @Query("type") int type);

    @GET("lims/message/get-status")
    Call<MessageStatusData> getMessageStatus(@Query("toId") int toId, @Query("userType") int userType);
}
