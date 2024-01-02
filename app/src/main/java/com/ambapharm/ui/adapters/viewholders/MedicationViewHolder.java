package com.ambapharm.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ambapharm.databinding.ItemIssueCardBinding;
import com.ambapharm.ui.adapters.clickListners.OnDeleteClickListener;
import com.ambapharm.ui.adapters.clickListners.OnEditClickListener;


/**
 * ViewHolder for medication items in a RecyclerView.
 * This class is responsible for binding medication data to the layout defined in ItemIssueCardBinding.
 * It handles click events for editing and deleting medication items.
 */
public class MedicationViewHolder extends RecyclerView.ViewHolder {
    public ItemIssueCardBinding binding;

    /**
     * Constructor for MedicationViewHolder.
     *
     * @param binding            Binding instance for the medication item layout.
     * @param editClickListener  Listener for handling edit actions on the medication item.
     * @param deleteClickListener Listener for handling delete actions on the medication item.
     */
    public MedicationViewHolder(ItemIssueCardBinding binding, OnEditClickListener editClickListener, OnDeleteClickListener deleteClickListener) {
        super(binding.getRoot());
        this.binding = binding;

        binding.edit.setOnClickListener(v -> editClickListener.onEditClick(getAdapterPosition()));
        binding.delete.setOnClickListener(v -> deleteClickListener.onDeleteClick(getAdapterPosition()));
    }

}