package com.gapharma.ui.adapters;

import androidx.recyclerview.widget.RecyclerView;


/**
 * An abstract class representing a generic item in a RecyclerView. This class is intended
 * to be extended by specific types of items (like documents, descriptions, FNCs, images, etc.)
 * for use in a polymorphic RecyclerView adapter. It implements the BindableItem interface
 * for binding logic and maintains a selection state.
 */
public abstract class ListItem implements BindableItem {
    private boolean isSelected = false;
    public static final int TYPE_DOCUMENT = 0;
    public static final int TYPE_DESCRIPTION = 1;
    public static final int TYPE_FNC = 2;
    public static final int TYPE_IMAGE = 3;


    /**
     * Checks if the item is currently selected.
     *
     * @return True if the item is selected, false otherwise.
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Sets the selection state of the item.
     *
     * @param selected The new selection state to be set.
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Gets the type of the item. This method should be overridden by subclasses to return
     * a specific type identifier (e.g., TYPE_DOCUMENT, TYPE_DESCRIPTION).
     *
     * @return An integer representing the type of the item.
     */
    public abstract int getType();


    /**
     * Abstract method to bind the current item to a RecyclerView ViewHolder.
     * This method must be implemented by subclasses to define how item data is bound to the view.
     *
     * @param viewHolder The ViewHolder to which the item should be bound.
     */
    @Override
    public abstract void bind(RecyclerView.ViewHolder viewHolder);

    }

