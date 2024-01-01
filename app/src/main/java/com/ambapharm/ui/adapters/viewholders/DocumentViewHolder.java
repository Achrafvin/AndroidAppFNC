package com.ambapharm.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemDocumentCardBinding;
import com.ambapharm.ui.adapters.clickListners.OnDeleteClickListener;
import com.ambapharm.ui.adapters.clickListners.OnViewClickListener;

public class DocumentViewHolder extends RecyclerView.ViewHolder{
    public ItemDocumentCardBinding binding;

    public DocumentViewHolder(ItemDocumentCardBinding binding, OnViewClickListener viewClickListener, OnDeleteClickListener deleteClickListener) {
        super(binding.getRoot());
        this.binding = binding;

        binding.find.setOnClickListener(v -> viewClickListener.onViewClick(getAdapterPosition()));
        binding.deleteDoc.setOnClickListener(v -> deleteClickListener.onDeleteClick(getAdapterPosition()));
    }


}
