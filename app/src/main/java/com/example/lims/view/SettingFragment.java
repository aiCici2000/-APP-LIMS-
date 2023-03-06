package com.example.lims.view;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.lims.R;
import com.example.lims.databinding.FragmentSettingBinding;
import com.example.lims.repository.SharedPreferencesUtil;
import com.example.lims.utils.ToastUtils;
import com.example.lims.view.base.BaseLoginFragment;
import com.example.lims.viewmodel.LoginViewModel;
import com.example.lims.widget.SpecialItem;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/5 14:05
 * Describe: 设置页
 */
public class SettingFragment extends BaseLoginFragment<FragmentSettingBinding> {

    private static final String TAG = "SettingFragment";

    private FragmentSettingBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding =  FragmentSettingBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        if (getLoginFlag()) {
            binding.fSettingLogin.setVisibility(View.GONE);
            binding.loginGroup.setVisibility(View.VISIBLE);
            binding.logoutButton.setVisibility(View.VISIBLE);
            binding.fSettingName.setText(SharedPreferencesUtil.getData("userName", "###")+ "");
            binding.fSettingNumber.setText(SharedPreferencesUtil.getData("userNumber", "#") + "");
        } else {
            binding.fSettingLogin.setVisibility(View.VISIBLE);
            binding.loginGroup.setVisibility(View.GONE);
            binding.logoutButton.setVisibility(View.GONE);
        }
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        initObserver();
    }


    @Override
    public void initEvent() {
        NavController navController = Navigation.findNavController(getView());
        binding.fSettingLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getLoginFlag()) {
                    navController.navigate(R.id.action_nav_message_to_nav_login);
                }
            }
        });
        binding.special1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoginFlag()) {
                    navController.navigate(R.id.action_nav_setting_to_nav_change_password);
                } else {
                    ToastUtils.show("请登录！");
                }
            }
        });
        binding.special2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoginFlag()) {
                    navController.navigate(R.id.action_nav_setting_to_nav_change_email);
                } else {
                    ToastUtils.show("请登录");
                }
            }
        });
        binding.special3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nav_setting_to_nav_about);
            }
        });
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("确定要退出登录吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NavController navController = Navigation.findNavController(getView());
                                SharedPreferencesUtil.putData("userNumber", "-1");
                                SharedPreferencesUtil.putData("userName", "-1");
                                SharedPreferencesUtil.putData("userId", -1);
                                SharedPreferencesUtil.putData("type", -1);
                                dialog.dismiss();
                                navController.popBackStack();
                            }
                        }).create();
                        dialog.show();
            }
        });
    }

    private void initObserver() {
        viewModel.getUserName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.fSettingName.setText(s);
            }
        });
        viewModel.getUserNumber().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.fSettingNumber.setText(s);
            }
        });
    }
}
