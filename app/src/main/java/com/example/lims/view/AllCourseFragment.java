package com.example.lims.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.CourseAdapter;
import com.example.lims.databinding.FragmentAllCourseBinding;
import com.example.lims.model.bean.CourseData;
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
 * @Package：com.example.lims.view.base
 * @Date：2023/1/16 17:10
 * Describe:  所有课程
 */
public class AllCourseFragment extends BaseDialogFragment<FragmentAllCourseBinding> {

    private static final String TAG = "AllCourseFragment";

    private FragmentAllCourseBinding binding;
    private final List<CourseData.DataBean> list = new ArrayList<>();
    private CourseAdapter adapter;
    private Call<CourseData> courseList;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentAllCourseBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        showLoading();
        networkRequest();
        initRecycleView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        courseList.cancel();
    }

    @Override
    public void initEvent() {

    }

    private void networkRequest() {
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        courseList = service.getAllCourse();
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
                    dismissLoading();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CourseData> call, Throwable t) {
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

            }
        });
        binding.rv.setAdapter(adapter);
    }

    public void setNoDataPage() {
        binding.rv.setVisibility(View.GONE);
        binding.iv1.setVisibility(View.VISIBLE);
    }
}
