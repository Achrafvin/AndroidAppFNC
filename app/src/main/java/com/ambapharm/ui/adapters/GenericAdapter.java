package com.ambapharm.ui.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ambapharm.ui.activities.AddDescriptionActivity;
import com.ambapharm.ui.activities.DocumentViewerActivity;
import com.ambapharm.ui.activities.ImageViewerActivity;
import com.ambapharm.ui.adapters.clickListners.OnDeleteClickListener;
import com.ambapharm.ui.adapters.clickListners.OnEditClickListener;
import com.ambapharm.ui.adapters.clickListners.OnViewClickListener;
import com.ambapharm.ui.adapters.items.DocumentItem;
import com.ambapharm.ui.adapters.items.ImageDocItem;
import com.ambapharm.ui.adapters.items.MedicationItem;

import java.io.File;
import java.util.List;

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnViewClickListener, OnDeleteClickListener, OnEditClickListener {
    private List<ListItem> items;
    private int selectedItemPosition = -1;
    private final Context context;

    private final OnItemDeleteListener itemDeleteListener;


    public GenericAdapter(Context context,List<ListItem> items,OnItemDeleteListener itemDeleteListener) {
        this.context = context;
        this.items = items;
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
        if (items != null && position < items.size()) {
            ListItem item = items.get(position);
            if (item instanceof ImageDocItem) {
                String imagePath = ((ImageDocItem) item).getImagePath();
                openImageActivity(imagePath);
            } else if (item instanceof DocumentItem) {
                String documentPath = ((DocumentItem) item).getFilePath();
                openDocumentActivity(documentPath);
            }
        }
    }

    private void openDocumentActivity(String documentPath) {
        Intent intent = new Intent(context, DocumentViewerActivity.class);
        intent.putExtra("documentPath", documentPath);
        context.startActivity(intent);
    }


    private void openImageActivity(String imagePath) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra("imagePath", imagePath);
        context.startActivity(intent);
    }


    @Override
    public void onDeleteClick(int position) {
        if (position >= 0 && position < items.size()) {
            ListItem item = items.get(position);
            if (item instanceof ImageDocItem) {
                String imagePath = ((ImageDocItem) item).getImagePath();
                deleteImageFile(imagePath);
            }else if (item instanceof DocumentItem) {
                String documentPath = ((DocumentItem) item).getFilePath();
                deleteImageFile(documentPath);
            }
            itemDeleteListener.onDeleteItem(position);
        }
    }

    private void deleteImageFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.delete()) {
                Log.e("Delete Operation", "Failed to delete " + filePath);
            }
        }
    }

    @Override
    public void onEditClick(int position) {
        ListItem item = getItem(position);
        if (item instanceof MedicationItem) {
            MedicationItem medicationItem = (MedicationItem) item;
            Intent intent = new Intent(context, AddDescriptionActivity.class);
            intent.putExtra("EXTRA_TITLE", medicationItem.getMainTitle());
            intent.putExtra("EXTRA_SUBTITLE", medicationItem.getSubtitle());
            intent.putExtra("EXTRA_COMMENT", medicationItem.getComment());
            intent.putExtra("EXTRA_NUM", medicationItem.getNum());
            context.startActivity(intent);
        }
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
