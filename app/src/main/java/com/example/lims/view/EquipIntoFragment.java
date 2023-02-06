package com.example.lims.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.adapter.LaboratorySimpleAdapter;
import com.example.lims.databinding.FragmentEquipIntoBinding;
import com.example.lims.model.LaboratoryItem;
import com.example.lims.utils.ToastUtils;
import com.example.lims.view.base.BaseLoginFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/1/12 16:05
 * Describe:
 */
public class EquipIntoFragment extends BaseLoginFragment<FragmentEquipIntoBinding> {

    private FragmentEquipIntoBinding binding;
    private List<LaboratoryItem> list = new ArrayList<>();

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentEquipIntoBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        initList();
        initRecycleView();
        initListener();
    }

    @Override
    public void initEvent() {

    }

    private String type = "学生计算机";
    private void initListener() {
        binding.tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String items[] = {"学生计算机", "教师计算机", "其他"};

                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("请选择类型")//设置对话框的标题
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                ToastUtils.show(items[which]);
                                type = items[which];
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.tvButton.setText(type);
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });
    }

    private void initList() {
        LaboratoryItem l1 = new LaboratoryItem("信息系统与理论实验室", "南区实训楼301-303", R.drawable.kongxianzhong,76);
        list.add(l1);

        LaboratoryItem l2 = new LaboratoryItem("计算机软件与理论实验室", "南区实训楼302-304", R.drawable.kongxianzhong,81);
        list.add(l2);

        LaboratoryItem l4 = new LaboratoryItem("虚拟现实技术实验室", "南区实训楼340-342", R.drawable.kongxianzhong,76);
        list.add(l4);
    }

    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rv.setLayoutManager(gridLayoutManager);
        LaboratorySimpleAdapter adapter = new LaboratorySimpleAdapter(list);
        adapter.setListener(new LaboratorySimpleAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                initItemListener(position);
            }
        });
        binding.rv.setAdapter(adapter);
    }

    private void initItemListener(int position) {
        LaboratoryItem item = list.get(position);

    }
}
