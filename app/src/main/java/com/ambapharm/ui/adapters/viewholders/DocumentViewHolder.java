package com.ambapharm.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemIssueDocBinding;

public class DocumentViewHolder extends RecyclerView.ViewHolder{
    public ItemIssueDocBinding binding;

    public DocumentViewHolder(ItemIssueDocBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


}
