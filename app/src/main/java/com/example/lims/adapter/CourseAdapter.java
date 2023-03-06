package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.bean.CourseData;
import com.example.lims.utils.CourseUtil;
import com.example.lims.utils.DateUtil;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/16 23:16
 * Describe:
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private final List<CourseData.DataBean> list;
    private ItemOnClickListener listener = null;

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public interface ItemOnClickListener {
        void help(int position);
    }

    public CourseAdapter(List<CourseData.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_course_item, parent, false);
            return new CourseAdapter.ViewHolder(dataView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position != list.size()) {
            CourseData.DataBean item = list.get(position);
            holder.name.setText(item.getName());
            holder.teacherName.setText(item.getTeacherName());
            holder.time.setText(DateUtil.getTimeShort(item.getTime()));
            holder.courseNum.setText(CourseUtil.getCourseNum(item.getCourseNumber()));
            holder.labNumber.setText(item.getLaboratoryNumber());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.help(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView teacherName;
        TextView time;
        TextView courseNum;
        TextView labNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_5);
            teacherName = itemView.findViewById(R.id.tv_6);
            time = itemView.findViewById(R.id.tv_7);
            courseNum = itemView.findViewById(R.id.tv_8);
            labNumber = itemView.findViewById(R.id.tv_9);
        }
    }

}
