package com.ambapharm.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemIssueCardBinding;
import com.ambapharm.ui.adapters.clickListners.OnDeleteClickListener;
import com.ambapharm.ui.adapters.clickListners.OnEditClickListener;
import com.ambapharm.ui.adapters.clickListners.OnViewClickListener;

public class MedicationViewHolder extends RecyclerView.ViewHolder {
    public ItemIssueCardBinding binding;

    public MedicationViewHolder(ItemIssueCardBinding binding, OnEditClickListener editClickListener, OnDeleteClickListener deleteClickListener) {
        super(binding.getRoot());
        this.binding = binding;

        binding.edit.setOnClickListener(v -> editClickListener.onEditClick(getAdapterPosition()));
        binding.delete.setOnClickListener(v -> deleteClickListener.onDeleteClick(getAdapterPosition()));
    }

}