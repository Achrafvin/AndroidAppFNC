package com.gapharma.ui.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.gapharma.ui.activities.AddDescriptionActivity;
import com.gapharma.ui.activities.DocumentViewerActivity;
import com.gapharma.ui.activities.ImageViewerActivity;
import com.gapharma.ui.adapters.clickListners.OnDeleteClickListener;
import com.gapharma.ui.adapters.clickListners.OnEditClickListener;
import com.gapharma.ui.adapters.clickListners.OnViewClickListener;
import com.gapharma.ui.adapters.items.DocumentItem;
import com.gapharma.ui.adapters.items.ImageItem;
import com.gapharma.ui.adapters.items.DescriptionItem;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;


/**
 * A generic RecyclerView adapter that handles multiple types of items including images, documents,
 * and descriptions. This adapter is capable of binding different types of items to their respective
 * ViewHolders and managing user interactions like viewing, editing, and deleting items.
 */
public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnViewClickListener, OnDeleteClickListener, OnEditClickListener {
    private List<ListItem> items;
    private int selectedItemPosition = -1;
    private final Context context;

    private final OnItemDeleteListener itemDeleteListener;


    /**
     * Constructor for GenericAdapter.
     *
     * @param context            The context in which the adapter is operating.
     * @param items              The list of ListItem objects to be managed by the adapter.
     * @param itemDeleteListener The listener for item deletion events.
     */
    public GenericAdapter(Context context,List<ListItem> items,OnItemDeleteListener itemDeleteListener) {
        this.context = context;
        this.items = items;
        this.itemDeleteListener = itemDeleteListener;
        }


    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return ViewHolderFactory.createViewHolder(parent, viewType, this,this,this);
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {

        BindableItem item = items.get(position);
        item.bind(holder);
        holder.itemView.setOnClickListener(v->{
        });

    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }


    /**
     * Updates the list of items managed by the adapter and refreshes the RecyclerView.
     *
     * @param newItems The new list of items to display.
     */
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
            if (item instanceof ImageItem) {
                String imagePath = ((ImageItem) item).getImagePath();
                openImageActivity(imagePath);
            } else if (item instanceof DocumentItem) {
                String documentPath = ((DocumentItem) item).getFilePath();
                openDocumentActivity(documentPath);
            }
        }
    }

    /**
     * Opens the DocumentViewerActivity for viewing a specific document.
     *
     * @param documentPath The file path of the document to be viewed.
     */
    private void openDocumentActivity(String documentPath) {
        Intent intent = new Intent(context, DocumentViewerActivity.class);
        intent.putExtra("documentPath", documentPath);
        context.startActivity(intent);
    }

    /**
     * Opens the ImageViewerActivity for viewing a specific image.
     *
     * @param imagePath The file path of the image to be viewed.
     */
    private void openImageActivity(String imagePath) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra("imagePath", imagePath);
        context.startActivity(intent);
    }


    @Override
    public void onDeleteClick(int position) {
        if (position >= 0 && position < items.size()) {
            ListItem item = items.get(position);
            if (item instanceof ImageItem) {
                String imagePath = ((ImageItem) item).getImagePath();
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
        if (item instanceof DescriptionItem) {
            DescriptionItem descriptionItem = (DescriptionItem) item;
            Intent intent = new Intent(context, AddDescriptionActivity.class);
            intent.putExtra("EXTRA_ID", descriptionItem.getId());
            intent.putExtra("EXTRA_TITLE", descriptionItem.getMainTitle());
            intent.putExtra("EXTRA_SUBTITLE", descriptionItem.getSubtitle());
            intent.putExtra("EXTRA_COMMENT", descriptionItem.getComment());
            intent.putExtra("EXTRA_NUM", descriptionItem.getNum());
            context.startActivity(intent);
        }
    }

    public interface OnItemDeleteListener {
        void onDeleteItem(int position);
    }

    /**
     * Sets the currently selected item in the adapter.
     *
     * @param position The position of the item to be selected.
     */
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
