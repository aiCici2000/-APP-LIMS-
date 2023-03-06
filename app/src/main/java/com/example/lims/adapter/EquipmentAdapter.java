package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.bean.EquipmentData;

import java.util.List;
import java.util.zip.Inflater;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/2/16 10:02
 * Describe:
 */
public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {

    private List<EquipmentData.DataBean> list;
    ItemOnClickListener listener = null;

    public interface ItemOnClickListener {
        void help(int position);
    }

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public EquipmentAdapter(List<EquipmentData.DataBean> list) {
        this.list = list;
    }

    public void setList(List<EquipmentData.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_equipment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EquipmentData.DataBean item = list.get(position);
        holder.name.setText(item.getName());
        holder.number.setText(item.getNumber());
        String s = item.getSummary() + "";
        holder.summary.setText("null".equals(s) ? "无" : s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.help(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView number;
        TextView summary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_1);
            number = itemView.findViewById(R.id.tv_4);
            summary = itemView.findViewById(R.id.tv_6);
        }
    }
}
