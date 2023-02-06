package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.Constant;
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

    public CourseAdapter(List<CourseData.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_course_item, parent, false);
        View noneView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.no_data_item, parent, false);
        if (viewType == Constant.HAVE_DATA) {
            return new CourseAdapter.ViewHolder(dataView);
        } else {
            return new CourseAdapter.ViewHolder(noneView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return Constant.NO_DATA;
        }else{
            return Constant.HAVE_DATA;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position != list.size()) {
            CourseData.DataBean item = list.get(position);
            holder.name.setText(item.getName());
            holder.teacherName.setText(item.getTeacherName());
            holder.time.setText(DateUtil.getTime(item.getTime()));
            holder.courseNum.setText(CourseUtil.getCourseNum(item.getCourseNumber()));
            holder.labNumber.setText(item.getLaboratoryNumber());
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
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
