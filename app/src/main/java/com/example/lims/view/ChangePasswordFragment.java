package com.example.lims.view;

import static com.example.lims.utils.Constant.SUCCESS;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.lims.databinding.FragmentChangePasswordBinding;
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
 * @Date：2023/2/17 15:57
 * Describe:  修改密码
 */
public class ChangePasswordFragment extends BaseDialogFragment<FragmentChangePasswordBinding> {

    private static final String TAG = "ChangePasswordFragment";

    FragmentChangePasswordBinding binding;
    private RetrofitService service;
    private CountDownTimerUtils countDownTimerUtils;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
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
                String password1 = binding.password1.getText() + "";
                String password2 = binding.password2.getText() + "";
                if (TextUtils.isEmpty(password1)) {
                    ToastUtils.show("请设置新密码！");
                } else if (TextUtils.isEmpty(password2)) {
                    ToastUtils.show("请再次输入新密码！");
                } else if ("null".equals(userEmail) || "-1".equals(userEmail)) {
                    ToastUtils.show("您还未绑定邮箱，请绑定邮箱！");
                }else if(!password1.equals(password2)){
                    ToastUtils.show("两次输入密码不一致！");
                } else {
                    getEmailCode();
                    countDownTimerUtils = new CountDownTimerUtils(binding.getCode, 60000, 1000);
                    countDownTimerUtils.start();
                }
            }
        });
        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO BUG,密码就算为空，也可成功修改
                String myCode = binding.code.getText() + "";
                //TODO BUG,本地emailCode未改变，再次输入仍可成功修改密码
                String localCode = SharedPreferencesUtil.getData("emailCode", "-1") + "";
                Log.d(TAG, "onClick: " + localCode);
                if (TextUtils.isEmpty(myCode)) {
                    ToastUtils.show("请输入验证码！");
                } else if (myCode.equals(localCode)) {
                    service.updatePassword(binding.password1.getText() + "", userId).enqueue(new Callback<ResultData>() {
                        @Override
                        public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                            if (response.body() != null) {
                                if (response.body().getStatus() == SUCCESS) {
                                    ToastUtils.show("修改成功！");
                                    NavController navController = Navigation.findNavController(getView());
                                    navController.popBackStack();
                                } else {
                                    ToastUtils.show("修改失败！");
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
    }

    private void getEmailCode() {
        service.getEmailCode(userId).enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
//                int status = 0;
                if (response.body() != null) {
//                    status = response.body().getStatus();
                    String code = ("" + response.body().getData()).substring(0, 6);
                    Log.d(TAG, "onResponse: " + code);
                    SharedPreferencesUtil.putData("emailCode", code);
                }
                //TODO 替换Thread sleep 不同try/catch
//                SystemClock.sleep(222);
                //TODO 明明请求成功了，却走了onFailure回调，检查Retrofit配置
//                if (status == SUCCESS) {
//
//                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                ToastUtils.show("连接服务器失败！");
            }
        });
    }
}
