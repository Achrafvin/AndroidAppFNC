package com.ambapharm.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemIssueCardBinding;

public class MedicationViewHolder extends RecyclerView.ViewHolder {
    public ItemIssueCardBinding binding;

    public MedicationViewHolder(ItemIssueCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

}