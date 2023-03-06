package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.bean.SoftwareData;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/2/23 17:28
 * Describe:
 */
public class SoftwareAdapter extends RecyclerView.Adapter<SoftwareAdapter.ViewHolder> {

    private List<SoftwareData.DataBean> list;
    private ItemOnClickListener listener = null;

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public interface ItemOnClickListener {
        void help(int position);
    }

    public SoftwareAdapter(List<SoftwareData.DataBean> list) {
        this.list = list;
    }

    public void setList(List<SoftwareData.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_software_item, parent, false);
        return new ViewHolder(view);
    }

    private boolean isSelect = false;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.help(holder.getAdapterPosition());
//                    holder.name.setSelected(true);
//                    holder.itemView.setSelected(true);
//                    holder.imageView.setSelected(true);
            }
        });
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
