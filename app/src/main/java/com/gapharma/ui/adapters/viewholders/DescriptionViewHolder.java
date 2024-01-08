package com.gapharma.ui.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.gapharma.ui.adapters.clickListners.OnDeleteClickListener;
import com.gapharma.ui.adapters.clickListners.OnEditClickListener;
import com.gapharma.databinding.ItemDescriptionCardBinding;


/**
 * ViewHolder for description items in a RecyclerView.
 * This class is responsible for binding description data to the layout defined in ItemIssueCardBinding.
 * It handles click events for editing and deleting description items.
 */
public class DescriptionViewHolder extends RecyclerView.ViewHolder {
    public ItemDescriptionCardBinding binding;

    /**
     * Constructor for DescriptionViewHolder.
     *
     * @param binding            Binding instance for the description item layout.
     * @param editClickListener  Listener for handling edit actions on the description item.
     * @param deleteClickListener Listener for handling delete actions on the description item.
     */
    public DescriptionViewHolder(ItemDescriptionCardBinding binding, OnEditClickListener editClickListener, OnDeleteClickListener deleteClickListener) {
        super(binding.getRoot());
        this.binding = binding;

        binding.edit.setOnClickListener(v -> editClickListener.onEditClick(getAdapterPosition()));
        binding.delete.setOnClickListener(v -> deleteClickListener.onDeleteClick(getAdapterPosition()));
    }

}