package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.utils.Constant;
import com.example.lims.model.bean.EquipmentRepData;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/18 11:04
 * Describe:
 */
public class EquipRepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<EquipmentRepData.DataBean> list;

    private ItemOnClickListener listener = null;

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public interface ItemOnClickListener {
        void help(int position);
    }

    public EquipRepAdapter(List<EquipmentRepData.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_equip_rep_item, parent, false);
        View titleView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_equip_rep_title, parent, false);
        if (viewType == Constant.TITLE) {
            return new TitleViewHolder(titleView);
        } else {
            return new ContentViewHolder(dataView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != list.size()) {
            EquipmentRepData.DataBean item = list.get(position);
            if (holder instanceof ContentViewHolder) {
                ((ContentViewHolder) holder).name.setText(item.getName());
                ((ContentViewHolder) holder).viewDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.help(holder.getAdapterPosition());
                    }
                });
            } else if (holder instanceof TitleViewHolder) {
                ((TitleViewHolder) holder).title.setText(item.getName());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static final int TITLE = 0;
    @Override
    public int getItemViewType(int position) {
        EquipmentRepData.DataBean item = list.get(position);
        if (item.getType() == TITLE) {
            return Constant.TITLE;
        } else {
            return Constant.HAVE_DATA;
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView viewDetails;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_1);
            viewDetails = itemView.findViewById(R.id.tv_2);
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
