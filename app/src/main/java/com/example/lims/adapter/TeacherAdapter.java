package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.model.bean.UserData;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/13 14:42
 * Describe:
 */
public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {

    private final List<UserData.DataBean> list;

    public TeacherAdapter(List<UserData.DataBean> list) {
        this.list = list;
    }

    private ItemOnClickListener listener = null;

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public interface ItemOnClickListener {
        void help(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext())
                .inflate(R.layout.layout_teacher_item, parent, false);
        return new ViewHolder(view);
    }

    private ConstraintLayout layout;
    private boolean isFirst = true;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData.DataBean teacherItem = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirst) {
                    layout = holder.view;
                    isFirst = false;
                } else {
                    layout.setSelected(false);
                }
                holder.view.setSelected(true);
                layout = holder.view;

                listener.help(holder.getAdapterPosition());
            }
        });
        holder.textView.setText(teacherItem.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
            view = itemView.findViewById(R.id.teacher_item);
        }
    }
}
