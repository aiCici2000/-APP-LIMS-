package com.example.lims.utils.net;

import com.example.lims.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author：李壮
 * @Package：com.example.lims.utils
 * @Date：2023/2/1 16:34
 * Describe:
 */
public class RetrofitUtil {

    private static Retrofit retrofit;

    private RetrofitUtil() {

    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    OkHttpClient client = new OkHttpClient.Builder()
                            //TODO 问题 配置
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();

                }
            }
        }
        return retrofit;
    }
}
