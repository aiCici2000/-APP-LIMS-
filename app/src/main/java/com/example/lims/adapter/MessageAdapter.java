package com.example.lims.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.bean.MessageData;
import com.example.lims.utils.DateUtil;
import com.example.lims.utils.MessageUtil;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/3/1 9:25
 * Describe:
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private final List<MessageData.DataBean> list;
    //    0-未读;1-已读;2-删除;
    public static final int UNREAD = 0;
    public static final int READ = 1;
    private static final int DELETE = 2;

    public MessageAdapter(List<MessageData.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_message_simple_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageData.DataBean item = list.get(position);
        holder.message.setText(MessageUtil.getMessage(item));
        if (item.getStatus() == UNREAD) {
            holder.status.setText(R.string.unread);
            holder.status.setTextColor(Color.parseColor("#c30f0f"));
        } else if (item.getStatus() == READ) {
            holder.status.setText(R.string.read);
            holder.status.setTextColor(Color.parseColor("#55bb0a"));
        }
        holder.time.setText(DateUtil.getTime(item.getCreateTime()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView message;
        TextView status;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.tv_message);
            status = itemView.findViewById(R.id.tv_status);
            time = itemView.findViewById(R.id.tv_time);
        }
    }

}
