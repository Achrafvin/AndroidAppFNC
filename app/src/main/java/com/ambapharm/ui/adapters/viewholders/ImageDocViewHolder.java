package com.ambapharm.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemImageDocBinding;

public class ImageDocViewHolder extends RecyclerView.ViewHolder{

    public ItemImageDocBinding binding;
    public ImageDocViewHolder(ItemImageDocBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

}
