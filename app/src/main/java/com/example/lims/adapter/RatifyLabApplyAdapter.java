package com.example.lims.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.utils.Constant;
import com.example.lims.model.bean.ReservationData;
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

    private static final String TAG = "RatifyLabApplyAdapter";
    private final List<ReservationData.DataBean> list;
    ItemOnClickListener listener = null;

    public void setItemOnClickListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public interface ItemOnClickListener {
        void help(int position);
    }

    public RatifyLabApplyAdapter(List<ReservationData.DataBean> list) {
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
            ReservationData.DataBean item = list.get(position);
            holder.labNum.setText(item.getLabNumber());
            holder.date.setText(DateUtil.getTimeShort(item.getTime()));
            holder.courseNum.setText(CourseUtil.getCourseNum(item.getCourseNumber()));
            holder.courseName.setText(item.getCourseName());
            holder.applicantName.setText(item.getApplicantName());
            holder.time.setText(DateUtil.getTime(item.getTime()));
            Log.d(TAG,"-- onBindViewHolder --" + item.getStatus());
            switch (item.getStatus()) {
                case 0:
                    holder.passed_or_not.setBackgroundResource(R.drawable.no_pass);
                    holder.to_approve.setVisibility(View.GONE);
                    holder.passed_or_not.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    holder.passed_or_not.setBackgroundResource(R.drawable.pass);
                    holder.to_approve.setVisibility(View.GONE);
                    holder.passed_or_not.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    holder.passed_or_not.setVisibility(View.GONE);
                    holder.to_approve.setVisibility(View.VISIBLE);
                    holder.to_approve.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.help(holder.getAdapterPosition());
                        }
                    });
                    break;
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
        TextView applicantName;
        TextView time;

        TextView to_approve;
        ImageView passed_or_not;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            labNum = itemView.findViewById(R.id.tv_7);
            date = itemView.findViewById(R.id.tv_8);
            courseNum = itemView.findViewById(R.id.tv_9);
            courseName = itemView.findViewById(R.id.tv_10);
            applicantName = itemView.findViewById(R.id.tv_11);
            time = itemView.findViewById(R.id.tv_6);
            to_approve = itemView.findViewById(R.id.tv_12);
            passed_or_not = itemView.findViewById(R.id.iv_1);
        }
    }

}
