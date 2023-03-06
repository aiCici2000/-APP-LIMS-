package com.example.lims.viewmodel;

import static com.example.lims.utils.Constant.SUCCESS;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lims.model.bean.MessageData;
import com.example.lims.model.bean.MessageStatusData;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.viewmodel
 * @Date：2023/3/1 12:43
 * Describe:
 */
public class MessageViewModel extends ViewModel {

    private static final String TAG = "MessageViewModel";

    private MutableLiveData<List<MessageData.DataBean>> messageData;

    public MutableLiveData<List<MessageData.DataBean>> getMessageData() {
        if (messageData == null) {
            messageData = new MutableLiveData<>();
        }
        return messageData;
    }

    public void requestMessage(int toId, int type) {
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        service.getMessage(toId, type).enqueue(new Callback<MessageData>() {
            @Override
            public void onResponse(Call<MessageData> call, Response<MessageData> response) {
                if (response.body() != null) {
                    MessageData body = response.body();
                    if (body.getStatus() == SUCCESS) {
                        messageData.postValue(body.getData());
                    }
                } else {
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<MessageData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private ResultInterface resultInterface = null;

    public void setResultInterface(ResultInterface resultInterface) {
        this.resultInterface = resultInterface;
    }

    public interface ResultInterface{
        void help(int[] data);
    }

    public void requestStatus(int toId, int userType){
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        service.getMessageStatus(toId, userType).enqueue(new Callback<MessageStatusData>() {
            @Override
            public void onResponse(Call<MessageStatusData> call, Response<MessageStatusData> response) {
                if (response.body() != null) {
                    MessageStatusData body = response.body();
                    if (body.getStatus() == SUCCESS) {
                        List<Integer> list = body.getData();
                        Log.d(TAG, "onResponse: " + list);
                        int[] data = new int[list.size()];
                        for (int i = 0; i < data.length; i++) {
                            data[i] = list.get(i);
                        }
                        resultInterface.help(data);
                    }
                } else {
                    ToastUtils.show("连接服务器失败！");
                }
            }

            @Override
            public void onFailure(Call<MessageStatusData> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
