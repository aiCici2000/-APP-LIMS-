package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.Constant;
import com.example.lims.model.RatifyLabApplyItem;
import com.example.lims.utils.CourseUtil;
import com.example.lims.utils.DateUtil;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/17 15:44
 * Describe: 审批实验室申请 adapter
 */
public class RatifyLabApplyAdapter extends RecyclerView.Adapter<RatifyLabApplyAdapter.ViewHolder> {

    private final List<RatifyLabApplyItem> list;

    public RatifyLabApplyAdapter(List<RatifyLabApplyItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_lab_apply_item, parent, false);
        View noneView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.no_data_item, parent, false);
        if (viewType == Constant.HAVE_DATA) {
            return new ViewHolder(dataView);
        } else {
            return new ViewHolder(noneView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return Constant.NO_DATA;
        }
        return Constant.HAVE_DATA;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position != list.size()) {
            RatifyLabApplyItem item = list.get(position);
            holder.labNum.setText(item.getLabNum());
            holder.date.setText(DateUtil.getTimeShort(item.getDate()));
            holder.courseNum.setText(CourseUtil.getCourseNum(item.getCourseNum()));
            holder.courseName.setText(item.getCourseName());
            holder.applyName.setText(item.getApplyName());
            holder.time.setText(DateUtil.getTime(item.getTime()));
            switch (item.getStatus()) {
                case 0:
                    holder.iv.setBackgroundResource(R.drawable.no_pass);
                    break;
                case 1:
                    holder.iv.setBackgroundResource(R.drawable.pass);
                    break;
                case 2:
                    holder.iv.setVisibility(View.GONE);
                    holder.tv.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView labNum;
        TextView date;
        TextView courseNum;
        TextView courseName;
        TextView applyName;
        TextView time;

        TextView tv;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            labNum = itemView.findViewById(R.id.tv_7);
            date = itemView.findViewById(R.id.tv_8);
            courseNum = itemView.findViewById(R.id.tv_9);
            courseName = itemView.findViewById(R.id.tv_10);
            applyName = itemView.findViewById(R.id.tv_11);
            time = itemView.findViewById(R.id.tv_6);
            tv = itemView.findViewById(R.id.tv_12);
            iv = itemView.findViewById(R.id.iv_1);
        }
    }

}
