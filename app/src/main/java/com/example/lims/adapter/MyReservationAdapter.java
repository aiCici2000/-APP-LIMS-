package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.bean.ReservationData;
import com.example.lims.utils.DateUtil;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/2/27 13:48
 * Describe:
 */
public class MyReservationAdapter extends RecyclerView.Adapter<MyReservationAdapter.ViewHolder> {

    private final List<ReservationData.DataBean> list;

    public MyReservationAdapter(List<ReservationData.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_my_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReservationData.DataBean dataBean = list.get(position);
        holder.labNumber.setText(dataBean.getLabNumber());
        holder.applyTime.setText(DateUtil.getTimeShort(dataBean.getCourseTime()));
        holder.courseNumber.setText(dataBean.getCourseNumber() + "");
        holder.courseName.setText(dataBean.getCourseName());
        holder.applyName.setText(dataBean.getApplicantName());
        switch (dataBean.getStatus()) {
            case 0:
                holder.passed_or_not.setBackgroundResource(R.drawable.no_pass);
                holder.passed_or_not.setVisibility(View.VISIBLE);
                break;
            case 1:
                holder.passed_or_not.setBackgroundResource(R.drawable.pass);
                holder.passed_or_not.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.passed_or_not.setVisibility(View.VISIBLE);
                holder.passed_or_not.setBackgroundResource(R.drawable.shenhezhong);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView labNumber;
        TextView applyTime;
        TextView courseNumber;
        TextView courseName;
        TextView applyName;
        ImageView passed_or_not;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            labNumber = itemView.findViewById(R.id.tv_7);
            applyTime = itemView.findViewById(R.id.tv_8);
            courseNumber = itemView.findViewById(R.id.tv_9);
            courseName = itemView.findViewById(R.id.tv_10);
            applyName = itemView.findViewById(R.id.tv_11);
            passed_or_not = itemView.findViewById(R.id.iv_1);
        }
    }

}
