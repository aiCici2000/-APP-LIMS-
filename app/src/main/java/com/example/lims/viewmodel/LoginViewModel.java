package com.example.lims.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lims.utils.Constant;
import com.example.lims.model.bean.UserData;
import com.example.lims.repository.SharedPreferencesUtil;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.viewmodel
 * @Date：2023/2/8 9:42
 * Describe:
 */
public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";

    private MutableLiveData<Boolean> isLogin;
    private MutableLiveData<String> userName;
    private MutableLiveData<String> userNumber;
    private String number;
    private String password;

    public MutableLiveData<Boolean> getIsLogin() {
        if (isLogin == null) {
            isLogin = new MutableLiveData<>();
        }
        return isLogin;
    }

    public MutableLiveData<String> getUserName() {
        if (userName == null) {
            userName = new MutableLiveData<>();
        }
        return userName;
    }

    public MutableLiveData<String> getUserNumber() {
        if (userNumber == null) {
            userNumber = new MutableLiveData<>();
        }
        return userNumber;
    }

    public void login() {
        RetrofitService retrofitService = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        retrofitService.login(number, password).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData body = response.body();
                if (body == null) {
                    ToastUtils.show("请重试！");
                    return;
                }
                int status = body.getStatus();
                switch (status) {
                    case Constant.SUCCESS:
                        isLogin.postValue(true);
                        String name = body.getData().get(0).getName();
                        String number = body.getData().get(0).getUserNumber();
                        String email = body.getData().get(0).getEmail() + "";
                        SharedPreferencesUtil.putData("userId", body.getData().get(0).getId());
                        SharedPreferencesUtil.putData("userNumber", number);
                        SharedPreferencesUtil.putData("userName", name);
                        SharedPreferencesUtil.putData("email", email);
                        SharedPreferencesUtil.putData("password", body.getData().get(0).getPassword());
                        SharedPreferencesUtil.putData("sex", body.getData().get(0).getSex());
                        SharedPreferencesUtil.putData("type", body.getData().get(0).getType());
                        SharedPreferencesUtil.putData("laboratoryId", body.getData().get(0).getLaboratoryId());
                        userName.postValue(name);
                        userNumber.postValue(number);
                        ToastUtils.show("登录成功！");
                        break;
                    case Constant.USER_NOT_EXIST_ERROR:
                        ToastUtils.show("用户不存在！");
                        break;
                    case Constant.PASSWORD_ERROR:
                        ToastUtils.show("密码错误！");
                        break;
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
                ToastUtils.show("请检查网络！");
            }
        });
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
