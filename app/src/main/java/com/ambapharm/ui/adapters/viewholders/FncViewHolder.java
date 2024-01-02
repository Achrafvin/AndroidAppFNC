package com.ambapharm.ui.adapters.viewholders;


import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemFncListBinding;

/**
 * ViewHolder for FNC (pharmaceutical product anomaly report) items in a RecyclerView.
 * This class is responsible for binding FNC data to the layout defined in ItemFncListBinding.
 * It primarily serves to link the FNC data with the corresponding view elements in the layout.
 */
public class FncViewHolder extends RecyclerView.ViewHolder{
    public ItemFncListBinding binding;


    /**
     * Constructor for FncViewHolder.
     *
     * @param binding Binding instance for the FNC item layout. This binding links the layout
     *                with the data handling logic of the FNC items.
     */
    public FncViewHolder(ItemFncListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


}
