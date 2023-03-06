package com.example.lims.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.model.bean.LaboratoryData;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/12 9:11
 * Describe:
 */
public class LaboratoryAdapter extends RecyclerView.Adapter<LaboratoryAdapter.ViewHolder> {

    private List<LaboratoryData.DataBean> itemList;
    // 标记是否显示实验室name，区分实验室列表与其他报备: true其他报备，false实验室列表
    private final boolean flag;

    ItemOnClickListener listener = null;

    public interface ItemOnClickListener{
        void help(int position);
    }
    public void setListener(ItemOnClickListener listener){
        this.listener = listener;
    }

    public LaboratoryAdapter(List<LaboratoryData.DataBean> list, boolean flag) {
        this.itemList = list;
        this.flag = flag;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<LaboratoryData.DataBean> list) {
        this.itemList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataView = LayoutInflater.from(MyApplication.getContext())
                .inflate(R.layout.layout_laboratory_item, parent, false);
            return new ViewHolder(dataView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position != itemList.size()) {
            LaboratoryData.DataBean laboratoryItem = itemList.get(position);
            holder.textTop.setText(laboratoryItem.getName());
            holder.textBottom.setText(laboratoryItem.getNumber());
            holder.imageView.setImageResource(laboratoryItem.getStatus() == 0? R.drawable.kongxianzhong:R.drawable.shiyongzhong);
            holder.num.setText(laboratoryItem.getEquipmentNum() + "");
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.help(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View item;

        TextView textBottom;
        TextView textTop;
        ImageView imageView;
        TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView;
            textBottom = itemView.findViewById(R.id.tv_1);
            textTop = itemView.findViewById(R.id.tv_2);
            imageView = itemView.findViewById(R.id.iv_1);
            num = itemView.findViewById(R.id.tv_3);
        }
    }

}
