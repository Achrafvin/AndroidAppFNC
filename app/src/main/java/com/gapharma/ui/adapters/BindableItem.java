package com.gapharma.ui.adapters;

import androidx.recyclerview.widget.RecyclerView;


/**
 * An interface defining the contract for items that can be bound to RecyclerView ViewHolders.
 * This interface facilitates the implementation of a polymorphic adapter pattern where different
 * types of items can be managed within a single RecyclerView.
 */
public interface BindableItem {

    /**
     * Binds the current item to a RecyclerView ViewHolder.
     * Implementations of this method should cast the passed ViewHolder to the appropriate type
     * and bind the item's data to the ViewHolder's views.
     *
     * @param viewHolder The ViewHolder to which the item should be bound. It is expected that
     *                   this ViewHolder is of a type compatible with the item.
     */
    void bind(RecyclerView.ViewHolder viewHolder);
}
