package com.example.lims.view;

import static com.example.lims.utils.Constant.SUCCESS;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentChangeEmailBinding;
import com.example.lims.model.bean.ResultData;
import com.example.lims.repository.SharedPreferencesUtil;
import com.example.lims.utils.CountDownTimerUtils;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;
import com.example.lims.view.base.BaseDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/2/17 17:06
 * Describe:  绑定邮箱
 */
public class ChangeEmailFragment extends BaseDialogFragment<FragmentChangeEmailBinding> {

    private static final String TAG = "ChangeEmailFragment";

    FragmentChangeEmailBinding binding;
    private RetrofitService service;
    private CountDownTimerUtils countDownTimerUtils;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentChangeEmailBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        service = RetrofitUtil.getRetrofit().create(RetrofitService.class);

    }

    @Override
    public void initEvent() {
        binding.getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = binding.email1.getText() + "";
                String email2 = binding.email2.getText() + "";
                if (TextUtils.isEmpty(email1)) {
                    ToastUtils.show("请输入邮箱！");
                } else if (TextUtils.isEmpty(email2)) {
                    ToastUtils.show("请再次输入邮箱！");
                }else if(!email1.equals(email2)){
                    ToastUtils.show("两次输入邮箱不一致！");
                } else {
                    getEmailCode(email1);
                    countDownTimerUtils = new CountDownTimerUtils(binding.getCode, 60000, 1000);
                    countDownTimerUtils.start();
                }
            }
        });

        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myCode = binding.code.getText() + "";
                String localCode = SharedPreferencesUtil.getData("bindingCode", "-1") + "";
                Log.d(TAG, "onClick: " + localCode);
                if (TextUtils.isEmpty(myCode)) {
                    ToastUtils.show("请输入验证码！");
                } else if (myCode.equals(localCode)) {
                    service.updateEmail(binding.email1.getText() + "").enqueue(new Callback<ResultData>() {
                        @Override
                        public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                            if (response.body() != null) {
                                if (response.body().getStatus() == SUCCESS) {
                                    ToastUtils.show("绑定成功！");
                                    NavController navController = Navigation.findNavController(getView());
                                    navController.popBackStack();
                                } else {
                                    ToastUtils.show("绑定失败！");
                                }
                            } else {
                                ToastUtils.show("连接服务器失败！");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultData> call, Throwable t) {
                            Log.d(TAG, "onFailure: ");
                            ToastUtils.show("连接服务器失败！");
                        }
                    });
                } else {
                    ToastUtils.show("验证码错误！");
                }
            }
        });

        if (!"null".equals(userEmail) && !"-1".equals(userEmail)) {
            binding.confirmButton.setClickable(false);
            binding.getCode.setClickable(false);
            binding.email1.setFocusable(false);
            binding.email2.setFocusable(false);
            binding.code.setFocusable(false);
            binding.confirmButton.setBackgroundColor(Color.GRAY);
            ToastUtils.show("您已绑定过邮箱！");
        }
    }

    private void getEmailCode(String email) {
        service.getEmailCode(email).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
//                int status = 0;
                if (response.body() != null) {
                    String code = ("" + response.body().getData()).substring(0, 6);
                    Log.d(TAG, "onResponse: " + code);
                    SharedPreferencesUtil.putData("bindingCode", code);
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                ToastUtils.show("连接服务器失败！");
            }
        });
    }
}
