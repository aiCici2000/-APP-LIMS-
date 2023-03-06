package com.example.lims.view;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.lims.R;
import com.example.lims.databinding.FragmentLoginBinding;
import com.example.lims.utils.ToastUtils;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.LoginViewModel;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/2/8 11:07
 * Describe: 登录页
 */
public class LoginFragment extends BaseDialogFragment<FragmentLoginBinding> {

    private static final String TAG = "LoginFragment";

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        initObserver();
    }

    @Override
    public void initEvent() {
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = binding.number.getText().toString();
                String password = binding.password.getText().toString();
                viewModel.setNumber(number);
                viewModel.setPassword(password);
                if (TextUtils.isEmpty(number)) {
                    ToastUtils.show("账号不能为空！");
                } else if (TextUtils.isEmpty(password)) {
                    ToastUtils.show("密码不能为空！");
                } else {
                    viewModel.login();
                }
            }
        });
    }

    public void initObserver() {
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        viewModel.getIsLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
//                setLoginFlag(aBoolean);
                if (aBoolean) {
                    NavController navController = Navigation.findNavController(getView());
                    //TODO navigateUp()与popBackStack()区别，导航与popStack页面是否重建的区别
//                    navController.navigate(R.id.action_nav_login_report_to_nav_setting);
//                    navController.navigateUp();
                    navController.popBackStack();
                }
            }
        });
    }
}
