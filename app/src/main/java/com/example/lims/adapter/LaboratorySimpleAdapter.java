package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.bean.LaboratoryData;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/31 9:27
 * Describe:
 */
public class LaboratorySimpleAdapter extends RecyclerView.Adapter<LaboratorySimpleAdapter.ViewHolder> {
    private static final String TAG = "LaboratorySimpleAdapter";

    private final List<LaboratoryData.DataBean> itemList;
    ItemOnClickListener listener = null;

    public interface ItemOnClickListener {
        void help(int position);
    }

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public LaboratorySimpleAdapter(List<LaboratoryData.DataBean> list) {
        this.itemList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_laboratory_item_s, parent, false);
        return new ViewHolder(view);
    }

    private ConstraintLayout layout;
    private TextView textView;
    private boolean isFirst = true;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LaboratoryData.DataBean item = itemList.get(position);
        holder.name.setText(item.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFirst) {
                    layout = holder.view;
                    textView = holder.name;
                    isFirst = false;
                } else {
                    layout.setSelected(false);
                    textView.setSelected(false);
                }
                holder.view.setSelected(true);
                holder.name.setSelected(true);
                layout = holder.view;
                textView = holder.name;

                listener.help(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout view;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.layout_1);
            name = itemView.findViewById(R.id.text_name);
        }
    }
}
