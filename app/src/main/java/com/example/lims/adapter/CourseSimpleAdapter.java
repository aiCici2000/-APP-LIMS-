package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.bean.CourseNumberData;
import com.example.lims.utils.CourseUtil;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/2/20 14:53
 * Describe:
 */
public class CourseSimpleAdapter extends RecyclerView.Adapter<CourseSimpleAdapter.ViewHolder> {

    private static final String TAG = "CourseSimpleAdapter";

    private final List<CourseNumberData.DataBean> list;
    public static final int OPTIONAL = 1;
    public static final int NOT_OPTIONAL = 0;
    private ItemOnClickListener listener = null;
    private boolean flag;

    public interface ItemOnClickListener{
        void help(int position);
    }

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public CourseSimpleAdapter(List<CourseNumberData.DataBean> list, boolean flag) {
        this.list = list;
        this.flag = flag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_simple_course_item, parent, false);
        return new ViewHolder(view);
    }

    private ConstraintLayout layout;
    private TextView textView;
    private boolean isFirst = true;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseNumberData.DataBean courseNumber = list.get(position);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirst) {
                    layout = holder.view;
                    textView = holder.textView;
                    isFirst = false;
                } else {
                    layout.setSelected(false);
                    textView.setSelected(false);
                }
                holder.view.setSelected(true);
                holder.textView.setSelected(true);
                layout = holder.view;
                textView = holder.textView;

                listener.help(holder.getAdapterPosition());
            }
        });
        holder.textView.setText(CourseUtil.getCourseNum(courseNumber.getCourseNumber()));
        if (flag) {
            if (courseNumber.getStatus() == NOT_OPTIONAL) {
                holder.textView.setClickable(false);
                holder.textView.setEnabled(false);
            } else {
                holder.textView.setSelected(false);
                holder.textView.setEnabled(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout view;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = (ConstraintLayout) itemView;
            textView = itemView.findViewById(R.id.text_name);
        }
    }
}
