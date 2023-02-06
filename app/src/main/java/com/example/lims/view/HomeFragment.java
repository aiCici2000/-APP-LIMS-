package com.example.lims.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.adapter.HomeAdapter;
import com.example.lims.databinding.FragmentHomeBinding;
import com.example.lims.model.Constant;
import com.example.lims.model.HomeItem;
import com.example.lims.view.base.BaseLoginFragment;
import com.example.lims.viewmodel.HomeViewModel;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/4 14:05
 * Describe: 首页
 */
public class HomeFragment extends BaseLoginFragment<FragmentHomeBinding> {
    private static final String TAG = "HomeFragment";

    private FragmentHomeBinding binding;
    private List<HomeItem> itemList;
    private HomeViewModel homeViewModel;

    @Override
    protected FragmentHomeBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        Log.d(TAG, "init: ");
        setSentence();
        initObserver();
        networkRequest();
//        refresh();
        Constant.initHomeItemAdmin();
        this.itemList = Constant.itemList;
        initRV();
    }

    @Override
    public void initEvent() {
        binding.ivButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networkRequest();
            }
        });
    }

    @Override
    public void event() {
        NavController navController = Navigation.findNavController(getView());
        Bundle bundle = new Bundle();
        binding.fHomeTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("status", 0);
                navController.navigate(R.id.action_nav_home_to_nav_laboratory, bundle);
            }
        });
        binding.fHomeTv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("status", 0);
                navController.navigate(R.id.action_nav_home_to_nav_laboratory, bundle);
            }
        });
        binding.fHomeTv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("status", 1);
                navController.navigate(R.id.action_nav_home_to_nav_laboratory, bundle);
            }
        });
        binding.fHomeTv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("status", 1);
                navController.navigate(R.id.action_nav_home_to_nav_laboratory, bundle);
            }
        });
        binding.fHomeTv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_home_to_nav_all_course);
            }
        });
        binding.fHomeTv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_home_to_nav_all_course);
            }
        });
    }

    // 刷新台词
    private void setSentence() {
        int index = (int) (Math.random() * 10);
        String cur = Constant.SENTENCES[index];
        binding.fHomeTv1.setText(cur);
    }

    private void initRV() {
        GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.getContext(), 3);
        binding.recyclerview.setLayoutManager(layoutManager);
        HomeAdapter adapter = new HomeAdapter(itemList);
        adapter.setListener(new HomeAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                initItemListener(position);
            }
        });
        binding.recyclerview.setAdapter(adapter);
    }

    private void initItemListener(int position) {
        int action = itemList.get(position).getAction();
        Bundle bundle = new Bundle();
        bundle.putInt("status", 2);
        Navigation.findNavController(getView()).navigate(action, bundle);
    }

    private void initObserver() {
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        homeViewModel.getIdleLabNumber().observe(getViewLifecycleOwner(), num -> {
            binding.fHomeTv3.setText(String.valueOf(num));
        });
//
//        MutableLiveData<Integer> idleLabNumber = homeViewModel.getIdleLabNumber();
//        idleLabNumber.observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                binding.fHomeTv3.setText(integer + "");
//            }
//        });

        MutableLiveData<Integer> allCoursesNumber = homeViewModel.getAllCoursesNumber();
        allCoursesNumber.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.fHomeTv7.setText(integer + "");
            }
        });

        MutableLiveData<Integer> nonIdleLabNumber = homeViewModel.getNonIdleLabNumber();
        nonIdleLabNumber.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.fHomeTv5.setText(integer + "");
            }
        });
    }

    private void networkRequest() {
        Log.d(TAG, "networkRequest: ");
        homeViewModel.request();
    }
}
