package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.utils.Constant;
import com.example.lims.model.bean.MaterialData;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/30 16:39
 * Describe:
 */
public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {

    private static final String TAG = "MaterialAdapter";

    private final List<MaterialData.DataBean> itemList;
    private ItemOnClickListener listener = null;

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public interface ItemOnClickListener {
        void help(int position);
    }

    public MaterialAdapter(List<MaterialData.DataBean> list) {
        this.itemList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View noneView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.no_data_item, parent, false);
        View dataView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_material_item, parent, false);
        if (viewType == Constant.HAVE_DATA) {
            return new ViewHolder(dataView);
        } else {
            return new ViewHolder(noneView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position != itemList.size()) {
            MaterialData.DataBean item = itemList.get(position);
            holder.name.setText(item.getName());
            holder.num.setText(item.getNum()+"");
            String other = item.getOther() + "";
            holder.other.setText("null".equals(other) ? "无" : other);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.help(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == itemList.size()) {
            return Constant.NO_DATA;
        } else {
            return Constant.HAVE_DATA;
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView num;
        TextView other;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_1);
            num = itemView.findViewById(R.id.tv_2);
            other = itemView.findViewById(R.id.tv_3);
        }
    }


}
