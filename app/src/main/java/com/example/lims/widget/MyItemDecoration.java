package com.example.lims.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.MyApplication;
import com.example.lims.utils.PxUtils;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/2/23 10:20
 * Describe: recycleView 两侧留边
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        outRect.left = -10;
//        outRect.right = -10;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = PxUtils.getScreenWidth(MyApplication.getContext()) - 150;
    }
}
