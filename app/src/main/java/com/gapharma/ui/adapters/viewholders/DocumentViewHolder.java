package com.gapharma.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.gapharma.ui.adapters.clickListners.OnDeleteClickListener;
import com.gapharma.ui.adapters.clickListners.OnViewClickListener;
import com.gapharma.databinding.ItemDocumentCardBinding;

/**
 * ViewHolder for document items in a RecyclerView.
 * This class is responsible for binding document data to the layout defined in ItemDocumentCardBinding.
 * It also handles click events for viewing and deleting documents.
 */
public class DocumentViewHolder extends RecyclerView.ViewHolder{
    public ItemDocumentCardBinding binding;

    /**
     * Constructor for DocumentViewHolder.
     *
     * @param binding           Binding instance for the document item layout.
     * @param viewClickListener Listener for handling view (read) actions on the document.
     * @param deleteClickListener Listener for handling delete actions on the document.
     */
    public DocumentViewHolder(ItemDocumentCardBinding binding, OnViewClickListener viewClickListener, OnDeleteClickListener deleteClickListener) {
        super(binding.getRoot());
        this.binding = binding;

        binding.find.setOnClickListener(v -> viewClickListener.onViewClick(getAdapterPosition()));
        binding.deleteDoc.setOnClickListener(v -> deleteClickListener.onDeleteClick(getAdapterPosition()));
    }


}
