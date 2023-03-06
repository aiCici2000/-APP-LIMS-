package com.example.lims.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.adapter.TeacherAdapter;
import com.example.lims.databinding.FragmentTeacherListBinding;
import com.example.lims.model.bean.UserData;
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
 * @Date：2023/1/12 15:05
 * Describe:  师资团队
 */
public class TeacherListFragment extends BaseDialogFragment<FragmentTeacherListBinding> {
    private static final String TAG = "TeacherListFragment";

    private FragmentTeacherListBinding binding;
    private final List<UserData.DataBean> list = new ArrayList<>();
    private TeacherAdapter adapter;
    private Call<UserData> teacherList;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentTeacherListBinding.inflate(inflater, container, false);
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
        teacherList.cancel();
    }

    @Override
    public void initEvent() {

    }

    public void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(manager);
        Log.d(TAG, "initRecycleView: " + list);
        adapter = new TeacherAdapter(list);
        adapter.setListener(new TeacherAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {

            }
        });
        binding.rv.setAdapter(adapter);
    }

    private void networkRequest() {
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        teacherList = service.getTeacherList(2);
        teacherList.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData userData = response.body();
                try {
                    List<UserData.DataBean> dataBeans = userData.getData();
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
            public void onFailure(Call<UserData> call, Throwable t) {
                dismissLoading();
                setNoDataPage();
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    public void setNoDataPage() {
        binding.rv.setVisibility(View.GONE);
        binding.iv1.setVisibility(View.VISIBLE);
    }
}
