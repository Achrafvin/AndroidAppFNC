package com.ambapharm.ui.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambapharm.ui.adapters.clickListners.OnDeleteClickListener;
import com.ambapharm.ui.adapters.clickListners.OnEditClickListener;
import com.ambapharm.ui.adapters.clickListners.OnViewClickListener;

import java.util.List;

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnViewClickListener, OnDeleteClickListener, OnEditClickListener {
    private List<ListItem> items;
    private int selectedItemPosition = -1;
    private final OnItemClickListener listener;
    private final Context context;

    private final OnItemDeleteListener itemDeleteListener;


    public GenericAdapter(Context context,List<ListItem> items, OnItemClickListener listener,OnItemDeleteListener itemDeleteListener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
        this.itemDeleteListener = itemDeleteListener;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolderFactory.createViewHolder(parent, viewType, this,this,this);
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

    @SuppressLint("NotifyDataSetChanged")
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

    @Override
    public void onViewClick(int position) {
        String message = "Position: " + position + ", Button: View Button";
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        listener.onItemClick(position);
    }

    @Override
    public void onDeleteClick(int position) {
        if (position >= 0 && position < items.size()) {
            itemDeleteListener.onDeleteItem(position);
        }
    }

    @Override
    public void onEditClick(int position) {
        String message = "Position: " + position + ", Button: Edit Button";
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        listener.onItemClick(position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemDeleteListener {
        void onDeleteItem(int position);
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
