package com.example.lims.view;

import static com.example.lims.utils.Constant.ADMIN_ITEM_1;
import static com.example.lims.utils.Constant.ADMIN_ITEM_2;
import static com.example.lims.utils.Constant.ADMIN_ITEM_3;
import static com.example.lims.utils.Constant.ADMIN_ITEM_4;
import static com.example.lims.utils.Constant.STUDENT_ITEM_1;
import static com.example.lims.utils.Constant.TEACHER_ITEM_1;
import static com.example.lims.utils.Constant.TEACHER_ITEM_2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.lims.R;
import com.example.lims.databinding.FragmentMessageBinding;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.MessageViewModel;
import com.example.lims.widget.MessageItem;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/4 14:05
 * Describe: 消息页
 */
public class MessageFragment extends BaseDialogFragment<FragmentMessageBinding> {

    private static final String TAG = "MessageFragment";

    private FragmentMessageBinding binding;
    private NavController navController;
    private MessageViewModel viewModel;
    int[] status;


    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMessageBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void onPause() {
        super.onPause();
        dismissLoading();
    }

    @Override
    public void init() {
        showLoading();
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        viewModel.setResultInterface(new MessageViewModel.ResultInterface() {
            @Override
            public void help(int[] data) {
                status = data;
                binding.fMessageNone.setVisibility(View.GONE);
                binding.tv1.setVisibility(View.GONE);
                if (userType == 0) {
                    binding.fMessageI4.setVisibility(View.GONE);
                    binding.fMessageI3.setVisibility(View.GONE);
                    binding.fMessageI2.setVisibility(View.GONE);
                    binding.fMessageI1.setVisibility(View.VISIBLE);
                    MessageItem messageItem = data[0] == 1 ? binding.fMessageI1.setShowSpot(true) : binding.fMessageI1.setShowSpot(false);
                } else if (userType == 1) {
                    binding.fMessageI4.setVisibility(View.GONE);
                    binding.fMessageI3.setVisibility(View.GONE);
                    binding.fMessageI2.setVisibility(View.VISIBLE);
                    binding.fMessageI1.setVisibility(View.VISIBLE);
                    MessageItem messageItem = data[0] == 1 ? binding.fMessageI1.setShowSpot(true) : binding.fMessageI1.setShowSpot(false);
                    MessageItem messageItem1 = data[1] == 1 ? binding.fMessageI2.setShowSpot(true) : binding.fMessageI2.setShowSpot(false);
                } else if (userType == 2) {
                    binding.fMessageI4.setVisibility(View.VISIBLE);
                    binding.fMessageI3.setVisibility(View.VISIBLE);
                    binding.fMessageI2.setVisibility(View.VISIBLE);
                    binding.fMessageI1.setVisibility(View.VISIBLE);
                    Log.d(TAG, "help: " + data.toString());
                    MessageItem messageItem = data[0] == 1 ? binding.fMessageI1.setShowSpot(true) : binding.fMessageI1.setShowSpot(false);
                    MessageItem messageItem1 = data[1] == 1 ? binding.fMessageI2.setShowSpot(true) : binding.fMessageI2.setShowSpot(false);
                    MessageItem messageItem3 = data[2] == 1 ? binding.fMessageI3.setShowSpot(true) : binding.fMessageI3.setShowSpot(false);
                    MessageItem messageItem4 = data[3] == 1 ? binding.fMessageI4.setShowSpot(true) : binding.fMessageI4.setShowSpot(false);
                }
                dismissLoading();
            }
        });
        viewModel.requestStatus(userId, userType);
    }

    @Override
    public void initEvent() {
        Bundle bundle = new Bundle();
        navController = Navigation.findNavController(getView());
        binding.fMessageI1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType == 0) {
                    bundle.putInt("itemType", STUDENT_ITEM_1);
                } else if (userType == 1) {
                    bundle.putInt("itemType", TEACHER_ITEM_1);
                } else if (userType == 2) {
                    bundle.putInt("itemType", ADMIN_ITEM_1);
                }
                navController.navigate(R.id.action_nav_message_to_nav_message_item, bundle);
            }
        });
        binding.fMessageI2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType == 1) {
                    bundle.putInt("itemType", TEACHER_ITEM_2);
                } else if (userType == 2) {
                    bundle.putInt("itemType", ADMIN_ITEM_2);
                }
                navController.navigate(R.id.action_nav_message_to_nav_message_item, bundle);
            }
        });
        binding.fMessageI3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType == 2) {
                    bundle.putInt("itemType", ADMIN_ITEM_3);
                }
                navController.navigate(R.id.action_nav_message_to_nav_message_item, bundle);
            }
        });
        binding.fMessageI4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType == 2) {
                    bundle.putInt("itemType", ADMIN_ITEM_4);
                }
                navController.navigate(R.id.action_nav_message_to_nav_message_item, bundle);
            }
        });
    }

}
