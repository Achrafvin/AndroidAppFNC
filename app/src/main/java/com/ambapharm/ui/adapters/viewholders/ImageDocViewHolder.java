package com.ambapharm.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemImageCardBinding;
import com.ambapharm.ui.adapters.clickListners.OnDeleteClickListener;
import com.ambapharm.ui.adapters.clickListners.OnViewClickListener;


/**
 * ViewHolder for image document items in a RecyclerView.
 * This class is responsible for binding image document data to the layout defined in ItemImageCardBinding.
 * It also handles click events for viewing and deleting image documents.
 */
public class ImageDocViewHolder extends RecyclerView.ViewHolder{



    public ItemImageCardBinding binding;

    /**
     * Constructor for ImageDocViewHolder.
     *
     * @param binding           Binding instance for the image document item layout.
     * @param viewClickListener Listener for handling view actions on the image document.
     * @param deleteClickListener Listener for handling delete actions on the image document.
     */
    public ImageDocViewHolder(ItemImageCardBinding binding, OnViewClickListener viewClickListener, OnDeleteClickListener deleteClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        binding.findImg.setOnClickListener(v -> viewClickListener.onViewClick(getAdapterPosition()));
        binding.deleteImg.setOnClickListener(v -> deleteClickListener.onDeleteClick(getAdapterPosition()));

    }

}
