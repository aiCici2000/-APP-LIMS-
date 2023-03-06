package com.example.lims.view;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.adapter.MaterialAdapter;
import com.example.lims.databinding.FragmentMaterialNumBinding;
import com.example.lims.model.bean.MaterialData;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.Utils;
import com.example.lims.widget.RecScrollListener;
import com.example.lims.view.base.BaseLoginFragment;
import com.example.lims.viewmodel.OtherViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:12
 * Describe:  耗材数量
 */
public class MaterialNumFragment extends BaseLoginFragment implements RecScrollListener.FABStateListener {

    private FragmentMaterialNumBinding binding;
    private final List<MaterialData.DataBean> list = new ArrayList<>();
    private FloatingActionButton fab;
    private OtherViewModel viewModel;
    private MaterialAdapter adapter;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMaterialNumBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        fab = binding.fab;
        initList();
        initAdapter();
    }

    @Override
    public void initEvent() {
        binding.rv.addOnScrollListener(new RecScrollListener(this));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_nav_material_num_to_nav_add_material);
            }
        });
    }

    private void initList() {
        viewModel = new ViewModelProvider(this).get(OtherViewModel.class);
        viewModel.getMaterialList().observe(this, new Observer<List<MaterialData.DataBean>>() {
            @Override
            public void onChanged(List<MaterialData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.requestAllMaterial();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        binding.rv.setLayoutManager(layoutManager);
        adapter = new MaterialAdapter(list);
        adapter.setListener(new MaterialAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                MaterialData.DataBean item = list.get(position);
                View v = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.dialog_view, null);
                final EditText editText = v.findViewById(R.id.dialog_edit);
                editText.setText(1 + "");
                AlertDialog dialog = new AlertDialog.Builder(requireContext())
                        .setTitle("修改耗材数量")
                        .setView(v)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String content = editText.getText().toString();
                                int count = 0;
                                if (!content.equals("")) {
                                    if (Utils.isNumber(content)) {
                                        count = Integer.parseInt(content);
                                        if (count > 500) {
                                            editText.setText(500 + "");
                                            ToastUtils.show("超过了设置的最大值：500");
                                        } else if (count < 0) {
                                            editText.setText(0 + "");
                                            ToastUtils.show("超过了设置的最小值：0");
                                        } else {
                                            viewModel.updateMaterialNum(item.getId(), count);
                                            dialog.dismiss();
                                        }
                                    }
                                }
                            }
                        })
                        .create();
                dialog.show();
            }
        });
        binding.rv.setAdapter(adapter);
    }

    @Override
    public void onFABHide() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
    }

    @Override
    public void onFABShow() {
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
    }
}
