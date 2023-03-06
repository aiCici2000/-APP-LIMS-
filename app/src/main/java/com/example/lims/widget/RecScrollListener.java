package com.example.lims.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：李壮
 * @Package：com.example.lims.utils
 * @Date：2023/2/17 9:40
 * Describe: 实现 FAB 结合 RecycleView 滑动隐藏显示
 */
public class RecScrollListener extends RecyclerView.OnScrollListener {

    // 滑动的距离
    private static final int THRESHOLD = 10;
    // 是否可见
    private boolean visible = true;
    // 记录滑动距离的累加值
    private int distance = 0;
    private FABStateListener fcbStateListener;

    public RecScrollListener(FABStateListener hideScrollListener) {
        this.fcbStateListener = hideScrollListener;
    }

    public interface FABStateListener {
        void onFABHide();

        void onFABShow();
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (distance > THRESHOLD && visible) {
            //隐藏
            visible = false;
            fcbStateListener.onFABHide();
            distance = 0;
        } else if (distance < -20 && !visible) {
            //显示
            visible = true;
            fcbStateListener.onFABShow();
            distance = 0;
        }
        if (visible && dy > 0 || (!visible && dy < 0)) {
            distance += dy;
        }
    }
}
