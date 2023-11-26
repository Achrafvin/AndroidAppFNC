package com.ambapharm.ui.adapters.viewholders;


import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemFncListBinding;

public class FncViewHolder extends RecyclerView.ViewHolder{
    public ItemFncListBinding binding;

    public FncViewHolder(ItemFncListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


}
