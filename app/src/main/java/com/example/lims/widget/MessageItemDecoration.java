package com.example.lims.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.utils.PxUtils;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/3/1 14:15
 * Describe:
 */
public class MessageItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = PxUtils.dp2px(15);
    }

}
