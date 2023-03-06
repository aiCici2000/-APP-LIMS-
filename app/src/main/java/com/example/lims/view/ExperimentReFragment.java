package com.example.lims.view;

import static com.example.lims.utils.Constant.SUCCESS;
import static com.example.lims.view.LabApply2Fragment.TO_BE_REVIEWED;
import static com.example.lims.viewmodel.ApplyViewModel.STUDENT_RESERVATION;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.adapter.CourseAdapter;
import com.example.lims.adapter.LaboratoryAdapter;
import com.example.lims.databinding.FragmentExperimentReBinding;
import com.example.lims.model.bean.CourseData;
import com.example.lims.model.bean.LaboratoryData;
import com.example.lims.model.bean.Reservation;
import com.example.lims.model.bean.ResultData;
import com.example.lims.utils.DateUtil;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.TeacherViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:58
 * Describe: 实验预约
 */
public class ExperimentReFragment extends BaseDialogFragment<FragmentExperimentReBinding> {

    private static final String TAG = "ExperimentReFragment";

    private FragmentExperimentReBinding binding;
    private final List<CourseData.DataBean> list = new ArrayList<>();
    private CourseAdapter adapter;
    private LaboratoryAdapter labAdapter;
    private final List<LaboratoryData.DataBean> labList = new ArrayList<>();
    private CourseData.DataBean courseItem = null;
    private RetrofitService service;
    private LaboratoryData.DataBean item = null;
    private TeacherViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentExperimentReBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        networkRequest();
        initRecycleView();
        initObserver();
    }

    @Override
    public void initEvent() {
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.group1.setVisibility(View.GONE);
                binding.tv2.setVisibility(View.VISIBLE);
                binding.courseRv.setVisibility(View.VISIBLE);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.group1.setVisibility(View.GONE);
                binding.tv3.setVisibility(View.VISIBLE);
                binding.labRv.setVisibility(View.VISIBLE);
            }
        });
    }

    private void networkRequest() {
        Call<CourseData> courseList = service.getAllCourse();
        courseList.enqueue(new Callback<CourseData>() {
            @Override
            public void onResponse(Call<CourseData> call, Response<CourseData> response) {
                CourseData courseData = response.body();
                try {
                    List<CourseData.DataBean> dataBeans = courseData.getData();
                    list.clear();
                    list.addAll(dataBeans);
                    if (list.size() == 0) {
                        setNoDataPage();
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CourseData> call, Throwable t) {
                setNoDataPage();
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.courseRv.setLayoutManager(layoutManager);
        adapter = new CourseAdapter(list);
        adapter.setListener(new CourseAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                courseItem = list.get(position);
                AlertDialog dialog = new AlertDialog.Builder(requireView().getContext())
                        .setTitle("确定要预约该课程吗")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Reservation reservation = new Reservation();
                                reservation.setType(STUDENT_RESERVATION);
                                reservation.setApplicantId(userId);
                                reservation.setApplicantName(userName);
                                reservation.setCourseNumber(courseItem.getCourseNumber());
                                reservation.setCourseTime(DateUtil.getTime(courseItem.getTime()));
                                reservation.setStatus(TO_BE_REVIEWED);
                                reservation.setTeacherId(courseItem.getTeacherId());
                                reservation.setLaboratoryId(courseItem.getLaboratoryId());
                                reservation.setLabName("");
                                reservation.setLabNumber(courseItem.getLaboratoryNumber());
                                reservation.setCourseName(courseItem.getName());
                                reservation.setTime(DateUtil.getTime());

                                bookingCourses(reservation);
                            }
                        }).create();
                dialog.show();
            }
        });
        binding.courseRv.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        binding.labRv.setLayoutManager(manager);
        labAdapter = new LaboratoryAdapter(labList, true);
        labAdapter.setListener(new LaboratoryAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                item = labList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("lab_number", item.getNumber());
                bundle.putInt("lab_id", item.getId());
                bundle.putString("lab_name", item.getName());
                bundle.putInt("administratorId", item.getAdministratorId());
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.action_nav_experiment_re_to_nav_experiment_re_2, bundle);
            }
        });
        binding.labRv.setAdapter(labAdapter);
    }

    public void setNoDataPage() {
        binding.courseRv.setVisibility(View.GONE);
        binding.noneIv.setVisibility(View.VISIBLE);
        binding.noneTv.setVisibility(View.VISIBLE);
    }

    public void bookingCourses(Reservation reservation){

        showLoading();
        service.experimentApply(reservation).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                if (response.body() != null) {
                    ResultData body = response.body();
                    if (body.getStatus() == SUCCESS) {
                        ToastUtils.show("预约成功！");
                        dismissLoading();
                    } else {
                        ToastUtils.show("预约失败！");
                        dismissLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                ToastUtils.show("预约失败！");
                dismissLoading();
            }
        });
    }

    private void initObserver() {
        viewModel = new ViewModelProvider(this).get(TeacherViewModel.class);
        viewModel.getAllLabList().observe(this, new Observer<List<LaboratoryData.DataBean>>() {
            @Override
            public void onChanged(List<LaboratoryData.DataBean> dataBeans) {
                labList.clear();
                labList.addAll(dataBeans);
                adapter.notifyDataSetChanged();
                dismissLoading();
            }
        });
        viewModel.requestAllLabList();
    }
}
