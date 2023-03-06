package com.example.lims.view;

import static com.example.lims.utils.Constant.SUCCESS;
import static com.example.lims.view.LabApply2Fragment.TO_BE_REVIEWED;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.CourseAdapter;
import com.example.lims.adapter.SoftwareAdapter;
import com.example.lims.databinding.FragmentSoftApplyBinding;
import com.example.lims.model.bean.CourseData;
import com.example.lims.model.bean.ResultData;
import com.example.lims.model.bean.SoftwareApply;
import com.example.lims.model.bean.SoftwareData;
import com.example.lims.utils.CourseUtil;
import com.example.lims.utils.DateUtil;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;
import com.example.lims.view.base.BaseDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 17:13
 * Describe: 申请设备软件
 */
public class SoftApplyFragment extends BaseDialogFragment<FragmentSoftApplyBinding> {

    private static final String TAG = "SoftApplyFragment";

    private FragmentSoftApplyBinding binding;
    private final List<CourseData.DataBean> list = new ArrayList<>();
    private final List<SoftwareData.DataBean> softList = new ArrayList<>();
    private CourseAdapter adapter;
    private RetrofitService service;
    private CourseData.DataBean course;
    private SoftwareData.DataBean software;
    private SoftwareAdapter adapter1;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentSoftApplyBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        showLoading();
        networkRequest();
        initRecycleView();
    }

    @Override
    public void initEvent() {
        binding.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.group.setVisibility(View.GONE);
                binding.tv.setTextColor(Color.parseColor("#13227a"));
                binding.tv.setText("点击选择您要申请的实验室");
                binding.rv.setVisibility(View.VISIBLE);
            }
        });

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (software == null) {
                    ToastUtils.show("请选择软件！");
                } else {
                    int softwareId = software.getId();

                    SoftwareApply softwareApply = new SoftwareApply();
                    softwareApply.setSoftwareId(softwareId);
                    softwareApply.setApplicantId(userId);
                    softwareApply.setAdminId(course.getLaboratoryId());
                    softwareApply.setStatus(TO_BE_REVIEWED);
                    softwareApply.setLabId(course.getLaboratoryId());
                    softwareApply.setTime(DateUtil.getTime());
                    Log.d(TAG, "onClick: " + softwareApply.toString());
                    showLoading();
                    service.addSoftwareApply(softwareApply).enqueue(new Callback<ResultData>() {
                        @Override
                        public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                            if (response.body() != null) {
                                ResultData body = response.body();
                                if (body.getStatus() == SUCCESS) {
                                    ToastUtils.show("申请成功！");
                                    software = null;
                                    softList.clear();
                                    adapter1.notifyDataSetChanged();
                                    networkRequest();
                                    dismissLoading();
                                }
                            } else {
                                ToastUtils.show("连接服务器失败！");
                                dismissLoading();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultData> call, Throwable t) {
                            ToastUtils.show("连接服务器失败！");
                            dismissLoading();
                        }
                    });
                }
            }
        });
    }


    private void networkRequest() {
        service.getCourseByTeacher(userId).enqueue(new Callback<CourseData>() {
            @Override
            public void onResponse(Call<CourseData> call, Response<CourseData> response) {
                CourseData body = response.body();
                if (body != null) {
                    List<CourseData.DataBean> data = body.getData();
                    list.clear();
                    list.addAll(data);
                    if (list.size() == 0) {
                        setNoDataPage();
                        ToastUtils.show("您当前没有成功开课的课程！");
                        dismissLoading();
                    }
                    adapter.notifyDataSetChanged();
                    dismissLoading();
                } else {
                    dismissLoading();
                    setNoDataPage();
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<CourseData> call, Throwable t) {
                dismissLoading();
                setNoDataPage();
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });

        service.getAllSoftware().enqueue(new Callback<SoftwareData>() {
            @Override
            public void onResponse(Call<SoftwareData> call, Response<SoftwareData> response) {
                SoftwareData body = response.body();
                if (body != null) {
                    List<SoftwareData.DataBean> data = body.getData();
                    softList.clear();
                    softList.addAll(data);
                    adapter.notifyDataSetChanged();
                    dismissLoading();
                } else {
                    dismissLoading();
                    setNoDataPage();
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<SoftwareData> call, Throwable t) {
                dismissLoading();
                setNoDataPage();
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        adapter = new CourseAdapter(list);
        adapter.setListener(new CourseAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                course = list.get(position);
                binding.tv4.setText(course.getName());
                binding.tv6.setText(course.getTeacherName());
                binding.tv8.setText(DateUtil.getTimeShort(course.getTime()));
                binding.tv9.setText(CourseUtil.getCourseNum(course.getCourseNumber()));
                binding.tv11.setText(course.getLaboratoryNumber());
                binding.tv13.setText(course.getNum() + "");
                binding.rv.setVisibility(View.GONE);
                binding.tv.setText("点击重新选择");
                binding.tv.setTextColor(Color.RED);
                binding.group.setVisibility(View.VISIBLE);
            }
        });
        binding.rv.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        binding.rv1.setLayoutManager(manager);
        adapter1 = new SoftwareAdapter(softList);
        adapter1.setListener(new SoftwareAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                if (softList.size() == 1) {
                    software = null;
                    softList.clear();
                    adapter1.notifyDataSetChanged();
                    networkRequest();
                } else {
                    software = softList.get(position);
                    softList.clear();
                    softList.add(software);
                    adapter1.setList(softList);
                }
            }
        });
        binding.rv1.setAdapter(adapter1);
    }

    public void setNoDataPage() {
        binding.rv.setVisibility(View.GONE);
        binding.iv1.setVisibility(View.VISIBLE);
    }
}
