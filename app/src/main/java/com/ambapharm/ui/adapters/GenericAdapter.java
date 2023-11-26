package com.ambapharm.ui.adapters;


import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ListItem> items;
    private int selectedItemPosition = -1;
    private final OnItemClickListener listener;

    public GenericAdapter(List<ListItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolderFactory.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BindableItem item = items.get(position);
        item.bind(holder);
        holder.itemView.setOnClickListener(v->{
            listener.onItemClick(position);
        });

    }


    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setItems(List<ListItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    public ListItem getItem(int position) {
        if (position >= 0 && position < items.size()) {
            return items.get(position);
        }
        return null;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setSelectedItem(int position) {
        if (selectedItemPosition >= 0 && selectedItemPosition < items.size()) {
            items.get(selectedItemPosition).setSelected(false);
            notifyItemChanged(selectedItemPosition);
        }

        selectedItemPosition = position;
        if (position >= 0 && position < items.size()) {
            items.get(position).setSelected(true);
            notifyItemChanged(position);
        }
    }

}
