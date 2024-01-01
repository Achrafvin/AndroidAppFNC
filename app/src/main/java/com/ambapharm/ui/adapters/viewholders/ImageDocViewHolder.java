package com.ambapharm.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemImageCardBinding;
import com.ambapharm.ui.adapters.clickListners.OnDeleteClickListener;
import com.ambapharm.ui.adapters.clickListners.OnViewClickListener;

public class ImageDocViewHolder extends RecyclerView.ViewHolder{

    public ItemImageCardBinding binding;
    public ImageDocViewHolder(ItemImageCardBinding binding, OnViewClickListener viewClickListener, OnDeleteClickListener deleteClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        binding.findImg.setOnClickListener(v -> viewClickListener.onViewClick(getAdapterPosition()));
        binding.deleteImg.setOnClickListener(v -> deleteClickListener.onDeleteClick(getAdapterPosition()));

    }

}
