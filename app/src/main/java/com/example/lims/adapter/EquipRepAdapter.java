package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.Constant;
import com.example.lims.model.EquipRepItem;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/18 11:04
 * Describe:
 */
public class EquipRepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<EquipRepItem> list;

    public EquipRepAdapter(List<EquipRepItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_equip_rep_item, parent, false);
        View noneView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.no_data_item, parent, false);
        View titleView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_equip_rep_title, parent, false);
        if (viewType == Constant.TITLE) {
            return new TitleViewHolder(titleView);
        } else if (viewType == Constant.NO_DATA) {
            return new ContentViewHolder(noneView);
        } else {
            return new ContentViewHolder(dataView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != list.size()) {
            EquipRepItem item = list.get(position);
            if (holder instanceof ContentViewHolder) {
                ((ContentViewHolder) holder).name.setText(item.getName());
            } else if (holder instanceof TitleViewHolder) {
                ((TitleViewHolder) holder).title.setText(item.getLabName());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return Constant.NO_DATA;
        }
        EquipRepItem item = list.get(position);
        if (item.getType() == 0) {
            return Constant.TITLE;
        } else {
            return Constant.HAVE_DATA;
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_1);
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv);
        }
    }
}
