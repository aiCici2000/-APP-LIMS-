package com.example.lims.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lims.R;
import com.example.lims.model.HomeItem;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.adapter
 * @Date：2023/1/8 23:22
 * Describe:
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private static final String TAG = "HomeAdapter";

    private final List<HomeItem> homeItemList ;
    ItemOnClickListener listener = null;

    public interface ItemOnClickListener {
        void help(int position);
    }

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    public HomeAdapter(List<HomeItem> list) {
        this.homeItemList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_home_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeItem homeItem = homeItemList.get(position);
        holder.image.setImageResource(homeItem.getImage());
        holder.text.setText(homeItem.getText());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.help(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View item;

        ImageView image;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView;
            image = itemView.findViewById(R.id.home_image);
            text = itemView.findViewById(R.id.home_text);
        }
    }
}
