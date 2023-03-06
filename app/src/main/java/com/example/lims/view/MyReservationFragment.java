package com.example.lims.view;

import static com.example.lims.utils.Constant.SUCCESS;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.adapter.MyReservationAdapter;
import com.example.lims.databinding.FragmentMyReserveBinding;
import com.example.lims.model.bean.ReservationData;
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
 * @Date：2023/2/24 17:02
 * Describe:  我的预约
 */
public class MyReservationFragment extends BaseDialogFragment<FragmentMyReserveBinding> {

    private static final String TAG = "MyReservationFragment";

    private FragmentMyReserveBinding binding;
    private final List<ReservationData.DataBean> list = new ArrayList<>();
    private RetrofitService service;
    private MyReservationAdapter adapter;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMyReserveBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        initRV();
        initData();
    }

    @Override
    public void initEvent() {

    }

    private void initRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.rv.setLayoutManager(layoutManager);
        adapter = new MyReservationAdapter(list);
        binding.rv.setAdapter(adapter);
    }

    private void initData() {
        service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        service.getMyReservation(userId).enqueue(new Callback<ReservationData>() {
            @Override
            public void onResponse(Call<ReservationData> call, Response<ReservationData> response) {
                if (response.body() != null) {
                    ReservationData body = response.body();
                    if (body.getStatus() == SUCCESS) {
                        List<ReservationData.DataBean> data = body.getData();
                        list.clear();
                        list.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReservationData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
